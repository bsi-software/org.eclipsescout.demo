/*******************************************************************************
 * Copyright (c) 2010 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipsescout.demo.bahbah.server;

import java.util.UUID;

import javax.security.auth.Subject;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.scout.commons.job.IRunnable;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.commons.security.SimplePrincipal;
import org.eclipse.scout.rt.platform.cdi.OBJ;
import org.eclipse.scout.rt.server.IServerSession;
import org.eclipse.scout.rt.server.job.IServerJobManager;
import org.eclipse.scout.rt.server.job.ServerJobInput;
import org.eclipse.scout.rt.server.services.common.clustersync.IClusterSynchronizationService;
import org.eclipse.scout.rt.server.services.common.session.IServerSessionRegistryService;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.demo.bahbah.server.services.db.IDbSetupService;
import org.eclipsescout.demo.bahbah.server.services.notification.RegisterUserNotificationListener;
import org.eclipsescout.demo.bahbah.server.services.notification.UnregisterUserNotificationListener;

public class ServerApplication implements IApplication {
  private static IScoutLogger LOG = ScoutLogManager.getLogger(ServerApplication.class);

  @Override
  public Object start(IApplicationContext context) throws Exception {
    // TODO [dwi/jgu]: remove once having a consolidated concept for creating default subjects/sessions.

    // Create the Subject
    Subject subject = new Subject();
    subject.getPrincipals().add(new SimplePrincipal("anonymous"));
    subject.setReadOnly();

    // Create the ServerSession
    IServerSession session = SERVICES.getService(IServerSessionRegistryService.class).newServerSession(ServerSession.class, subject);
    session.setIdInternal(ServerSession.class.getName() + UUID.randomUUID().toString());

    // Run initialization jobs.
    OBJ.one(IServerJobManager.class).runNow(new IRunnable() {

      @Override
      public void run() throws Exception {
        SERVICES.getService(IDbSetupService.class).installDb();
        SERVICES.getService(IClusterSynchronizationService.class).addListener(new RegisterUserNotificationListener());
        SERVICES.getService(IClusterSynchronizationService.class).addListener(new UnregisterUserNotificationListener());
      }
    }, ServerJobInput.empty().name("Install Db schema if necessary").session(session).subject(subject));

    LOG.info("bahbah server initialized");
    return EXIT_OK;
  }

  @Override
  public void stop() {
  }
}

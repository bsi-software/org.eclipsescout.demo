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

import javax.security.auth.Subject;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.scout.commons.IRunnable;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.commons.security.SimplePrincipal;
import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.OBJ;
import org.eclipse.scout.rt.server.job.ServerJobInput;
import org.eclipse.scout.rt.server.job.ServerJobs;
import org.eclipse.scout.rt.server.services.common.clustersync.IClusterSynchronizationService;
import org.eclipse.scout.rt.server.session.ServerSessionProviderWithCache;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.demo.bahbah.server.services.db.IDbSetupService;
import org.eclipsescout.demo.bahbah.server.services.notification.RegisterUserNotificationListener;
import org.eclipsescout.demo.bahbah.server.services.notification.UnregisterUserNotificationListener;

@Bean
public class ServerApplication implements IApplication {
  private static IScoutLogger LOG = ScoutLogManager.getLogger(ServerApplication.class);

  public static Subject s_subject;
  static {
    s_subject = new Subject();
    s_subject.getPrincipals().add(new SimplePrincipal("bahbah"));
    s_subject.setReadOnly();
  }

  @Override
  public Object start(IApplicationContext context) throws Exception {
    ServerJobInput input = ServerJobInput.fillEmpty();
    input.name("Install Db schema if necessary");
    input.subject(s_subject);
    input.session(OBJ.get(ServerSessionProviderWithCache.class).provide(input.copy()));

    // Run initialization jobs.
    ServerJobs.runNow(new IRunnable() {

      @Override
      public void run() throws Exception {
        SERVICES.getService(IDbSetupService.class).installDb();
        SERVICES.getService(IClusterSynchronizationService.class).addListener(new RegisterUserNotificationListener());
        SERVICES.getService(IClusterSynchronizationService.class).addListener(new UnregisterUserNotificationListener());
      }
    }, input);

    LOG.info("bahbah server initialized");
    return EXIT_OK;
  }

  @Override
  public void stop() {
  }

  public static Subject getSubject() {
    return s_subject;
  }
}

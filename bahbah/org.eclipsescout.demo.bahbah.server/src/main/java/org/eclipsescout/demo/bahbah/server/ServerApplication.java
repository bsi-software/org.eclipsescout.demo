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

import org.eclipse.scout.commons.IRunnable;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.commons.security.SimplePrincipal;
import org.eclipse.scout.rt.platform.AbstractApplication;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.PlatformException;
import org.eclipse.scout.rt.server.context.ServerRunContext;
import org.eclipse.scout.rt.server.context.ServerRunContexts;
import org.eclipse.scout.rt.server.services.common.clustersync.IClusterSynchronizationService;
import org.eclipse.scout.rt.server.session.ServerSessionProviderWithCache;
import org.eclipsescout.demo.bahbah.server.services.db.IDbSetupService;
import org.eclipsescout.demo.bahbah.server.services.notification.RegisterUserNotificationListener;
import org.eclipsescout.demo.bahbah.server.services.notification.UnregisterUserNotificationListener;

@Bean
public class ServerApplication extends AbstractApplication {
  private static IScoutLogger LOG = ScoutLogManager.getLogger(ServerApplication.class);

  public static Subject s_subject;
  static {
    s_subject = new Subject();
    s_subject.getPrincipals().add(new SimplePrincipal("bahbah"));
    s_subject.setReadOnly();
  }

  @Override
  public void start() throws PlatformException {
    try {
      ServerRunContext runContext = ServerRunContexts.empty();
      runContext.subject(s_subject);
      runContext.session(BEANS.get(ServerSessionProviderWithCache.class).provide(runContext.copy()));
      runContext.run(new IRunnable() {
        @Override
        public void run() throws Exception {
          BEANS.get(IDbSetupService.class).installDb();
          BEANS.get(IClusterSynchronizationService.class).addListener(new RegisterUserNotificationListener());
          BEANS.get(IClusterSynchronizationService.class).addListener(new UnregisterUserNotificationListener());
        }
      });
    }
    catch (Exception e) {
      throw new PlatformException("Unable to start server application.", e);
    }

    LOG.info("bahbah server initialized");
  }

  @Override
  public void stop() {
  }

  public static Subject getSubject() {
    return s_subject;
  }
}

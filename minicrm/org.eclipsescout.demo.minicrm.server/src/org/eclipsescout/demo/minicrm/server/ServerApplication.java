/*******************************************************************************
 * Copyright (c) 2013 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipsescout.demo.minicrm.server;

import org.eclipse.scout.commons.ConfigIniUtility;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.platform.IApplication;
import org.eclipse.scout.rt.platform.PlatformException;

/**
 * Dummy application in order to manage server side product configurations in *.product files.
 * A typical config.ini for such a product has (among others) the following properties:
 * osgi.clean=true
 * osgi.console=
 * eclipse.consoleLog=true
 * org.eclipse.equinox.http.jetty.http.port=8080
 * org.eclipse.equinox.http.jetty.context.path=/minicrm_server
 * osgi.bundles=org.eclipse.equinox.common@2:start, org.eclipse.update.configurator@start,
 * org.eclipse.equinox.http.jetty@start, org.eclipse.equinox.http.registry@start, org.eclipse.core.runtime@start
 * osgi.bundles.defaultStartLevel=4
 * osgi.noShutdown=true
 * eclipse.ignoreApp=false
 * eclipse.product=org.eclipsescout.demo.minicrm.server.product
 */
public class ServerApplication implements IApplication {
  private static IScoutLogger logger = ScoutLogManager.getLogger(ServerApplication.class);

  @Override
  public void start() throws PlatformException {
    //start the scheduler
    /*
    Scheduler scheduler=new Scheduler(Activator.getDefault().getBackendSubject(),ServerSession.class);
    scheduler.addJob(new LoadJobs());
    scheduler.start();
    Activator.getDefault().setScheduler(scheduler);
     */
    logger.info("minicrm server initialized");
  }

  @Override
  public void stop() {
  }

  @Override
  public String getName() {
    return ConfigIniUtility.getProperty(CONFIG_KEY_NAME);
  }

  @Override
  public String getVersion() {
    return ConfigIniUtility.getProperty(CONFIG_KEY_VERSION);
  }
}

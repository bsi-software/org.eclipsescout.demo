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

public class ServerApplication implements IApplication {
  private static IScoutLogger logger = ScoutLogManager.getLogger(ServerApplication.class);

  @Override
  public void start() throws PlatformException {
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

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
package org.eclipse.scout.testing.client.runner;

import org.eclipsescout.demo.minifigcreator.client.ClientSession;

public class CustomClientTestEnvironment implements IClientTestEnvironment {

  @Override
  public void setupGlobalEnvironment() {
    ScoutClientTestRunner.setDefaultClientSessionClass(ClientSession.class);
  }

  @Override
  public void setupInstanceEnvironment() {
  }
}

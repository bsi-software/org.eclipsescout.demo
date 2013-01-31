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
package org.eclipse.scout.bahbah.ui.rap;

import org.eclipse.scout.bahbah.client.ClientSession;
import org.eclipse.scout.rt.ui.rap.AbstractStandaloneRwtEnvironment;

/**
 *
 */
public class StandaloneRwtEnvironment extends AbstractStandaloneRwtEnvironment {

  public StandaloneRwtEnvironment() {
    super(Activator.getDefault().getBundle(), ClientSession.class);
  }

  @Override
  public String getLogoutLandingUri() {
    String logoutLandingUrl = super.getLogoutLandingUri();
    return logoutLandingUrl + "/../res/logout.html";
  }
}

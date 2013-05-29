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
package org.eclipsescout.demo.ibug.ui.rap;

import org.eclipsescout.demo.ibug.client.ClientSession;
import org.eclipse.scout.rt.ui.rap.mobile.AbstractMobileStandaloneRwtEnvironment;
import org.eclipse.scout.rt.ui.rap.window.BrowserWindowHandler;

public class MobileStandaloneRwtEnvironment extends AbstractMobileStandaloneRwtEnvironment {

  public MobileStandaloneRwtEnvironment() {
    super(Activator.getDefault().getBundle(), ClientSession.class);
  }

  /* (non-Javadoc)
   * @see org.eclipse.scout.rt.ui.rap.mobile.AbstractMobileStandaloneRwtEnvironment#createBrowserWindowHandler()
   */
  @Override
  protected BrowserWindowHandler createBrowserWindowHandler() {
    return new IBugMobileBrowserWindowHandler();
  }
}

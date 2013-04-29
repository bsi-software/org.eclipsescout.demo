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

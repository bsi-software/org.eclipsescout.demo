/**
 * 
 */
package org.eclipse.scout.ibug.ui.rap;

import org.eclipse.scout.rt.ui.rap.window.BrowserWindowHandler;

/**
 * @author mzi
 */
public class IBugMobileBrowserWindowHandler extends BrowserWindowHandler {
  @Override
  public void openLink(String link) {
    openLinkInSameBrowserWindow(link);
  }
}

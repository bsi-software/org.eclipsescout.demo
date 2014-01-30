/**
 *
 */
package org.eclipsescout.demo.ibug.ui.rap;

import org.eclipse.scout.rt.client.ui.desktop.IUrlTarget;
import org.eclipse.scout.rt.ui.rap.window.BrowserWindowHandler;

/**
 * @author mzi
 */
public class IBugMobileBrowserWindowHandler extends BrowserWindowHandler {
  @Override
  public void openLink(String link, IUrlTarget urlTarget) {
    openLinkInSameBrowserWindow(link);
  }
}

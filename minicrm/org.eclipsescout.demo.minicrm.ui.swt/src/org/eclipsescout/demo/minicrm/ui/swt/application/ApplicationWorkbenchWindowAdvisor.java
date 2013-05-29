package org.eclipsescout.demo.minicrm.ui.swt.application;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/**
 * <h3>ApplicationWorkbenchWindowAdvisor</h3> Used for workbench window behaviors.
 */
public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

  public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
    super(configurer);
  }

  @Override
  public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
    return new ApplicationActionBarAdvisor(configurer);
  }

  @Override
  public void preWindowOpen() {
    IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
    configurer.setInitialSize(new Point(1024, 768));
    configurer.setShowCoolBar(ApplicationActionBarAdvisor.NUM_OUTLINE_BUTTONS > 0);
    configurer.setShowStatusLine(true);
    configurer.setShowProgressIndicator(true);
    configurer.setShowMenuBar(true);
    configurer.setShowPerspectiveBar(false);
    configurer.setShowFastViewBars(false);
  }
}

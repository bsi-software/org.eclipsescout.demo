package org.eclipsescout.demo.minicrm.ui.swt.application.menu;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.ui.swt.ISwtEnvironment;
import org.eclipse.scout.rt.ui.swt.SwtMenuUtility;
import org.eclipse.ui.actions.CompoundContributionItem;
import org.eclipsescout.demo.minicrm.ui.swt.Activator;

public class DesktopMenuBar extends CompoundContributionItem {

  @Override
  protected IContributionItem[] getContributionItems() {
    ISwtEnvironment env = Activator.getDefault().getEnvironment();
    if (env != null && env.isInitialized()) {
      if (env.getClientSession() != null && env.getClientSession().getDesktop() != null) {
        IMenu[] menus = env.getClientSession().getDesktop().getMenus();
        if (menus != null && menus.length > 0) {
          return SwtMenuUtility.getMenuContribution(menus, env);
        }
      }
    }
    return new IContributionItem[0];
  }

  @Override
  public boolean isDirty() {
    boolean isDirty = super.isDirty();
    if (!isDirty && getParent() instanceof IMenuManager) {
      isDirty = ((IMenuManager) getParent()).isDirty();
    }
    return isDirty;
  }
}

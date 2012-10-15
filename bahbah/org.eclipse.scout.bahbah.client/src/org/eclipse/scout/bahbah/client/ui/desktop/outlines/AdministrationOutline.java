package org.eclipse.scout.bahbah.client.ui.desktop.outlines;

import java.util.Collection;

import org.eclipse.scout.bahbah.client.ui.desktop.outlines.pages.UserAdministrationTablePage;
import org.eclipse.scout.bahbah.shared.security.CreateUserPermission;
import org.eclipse.scout.bahbah.shared.security.DeleteUserPermission;
import org.eclipse.scout.bahbah.shared.security.ResetPasswordPermission;
import org.eclipse.scout.bahbah.shared.security.UpdateUserPermission;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.rt.shared.ui.UserAgentUtility;

public class AdministrationOutline extends AbstractOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Administration");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    UserAdministrationTablePage usersTablePage = new UserAdministrationTablePage();
    pageList.add(usersTablePage);
  }

  @Override
  protected void execInitTree() throws ProcessingException {
    setVisible(UserAgentUtility.isDesktopDevice() &&
        (ACCESS.check(new CreateUserPermission()) || ACCESS.check(new DeleteUserPermission()) ||
            ACCESS.check(new ResetPasswordPermission()) || ACCESS.check(new UpdateUserPermission())));
  }
}

package org.eclipse.scout.bahbah.client.ui.desktop.outlines;

import org.eclipse.scout.bahbah.shared.security.CreateUserPermission;
import org.eclipse.scout.bahbah.shared.security.RemoveUserPermission;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;

public class AdministrationOutline extends AbstractOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Administration");
  }

  @Override
  protected void execInitTree() throws ProcessingException {
    setVisible(ACCESS.check(new CreateUserPermission()) || ACCESS.check(new RemoveUserPermission()));
  }
}

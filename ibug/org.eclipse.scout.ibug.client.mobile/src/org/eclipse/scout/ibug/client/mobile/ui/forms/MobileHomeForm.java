package org.eclipse.scout.ibug.client.mobile.ui.forms;

import org.eclipse.scout.commons.annotations.InjectFieldTo;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.ibug.client.ui.forms.DesktopForm;
import org.eclipse.scout.rt.client.ClientJob;
import org.eclipse.scout.rt.extension.client.ui.form.fields.button.AbstractExtensibleButton;
import org.eclipse.scout.rt.shared.TEXTS;

public class MobileHomeForm extends DesktopForm {

  public MobileHomeForm() throws ProcessingException {
    super();
  }

  public LogoutButton getLogoutButton() {
    return getFieldByClass(LogoutButton.class);
  }

  @InjectFieldTo(DesktopForm.MainBox.class)
  @Order(10.0)
  public class LogoutButton extends AbstractExtensibleButton {

    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("Logoff");
    }

    @Override
    protected void execClickAction() throws ProcessingException {
      ClientJob.getCurrentSession().stopSession();
    }
  }
}

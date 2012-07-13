package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.WizardsForm.MainBox.OpenLabelwizardButton;
import org.eclipse.scout.rt.demo.client.ui.wizards.LabelWizard;
import org.eclipse.scout.rt.shared.TEXTS;

public class WizardsForm extends AbstractForm implements IPageForm {

  public WizardsForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Wizards");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public OpenLabelwizardButton getOpenLabelwizardButton() {
    return getFieldByClass(OpenLabelwizardButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class OpenLabelwizardButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("OpenLabelwizard");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        LabelWizard wizard = new LabelWizard();
        wizard.start();
      }
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

package org.eclipse.scout.rt.demo.client.ui.wizards;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.fields.GridData;
import org.eclipse.scout.rt.client.ui.wizard.AbstractWizard;
import org.eclipse.scout.rt.client.ui.wizard.AbstractWizardStep;
import org.eclipse.scout.rt.client.ui.wizard.IWizardContainerForm;
import org.eclipse.scout.rt.demo.client.ui.forms.DynamicFormWizardChooseAForm;
import org.eclipse.scout.rt.demo.client.ui.forms.IPageForm;
import org.eclipse.scout.rt.shared.TEXTS;

public class DynamicFormWizard extends AbstractWizard {

  public DynamicFormWizard() {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("DynamicFormWizard");
  }

  @Override
  protected IWizardContainerForm execCreateContainerForm() throws ProcessingException {
    IWizardContainerForm f = super.execCreateContainerForm();
    GridData gd = f.getRootGroupBox().getGridData();
    gd.widthInPixel = 900;
    gd.heightInPixel = 800;
    f.getRootGroupBox().setGridDataInternal(gd);
    return f;
  }

  @Order(10.0)
  public class ChooseAFormStep extends AbstractWizardStep<DynamicFormWizardChooseAForm> {

    @Override
    protected String getConfiguredTitle() {
      return TEXTS.get("ChooseAForm");
    }

    @Override
    protected void execActivate(int stepKind) throws ProcessingException {
      DynamicFormWizardChooseAForm form = getForm();
      if (form == null) {
        form = new DynamicFormWizardChooseAForm();
        form.startWizardStep(this, DynamicFormWizardChooseAForm.WizardHandler.class);
        setForm(form);
      }
      setWizardForm(form);
    }
  }

  public ChooseAFormStep getChooseAFormStep() {
    return getStep(DynamicFormWizard.ChooseAFormStep.class);
  }

  @Order(20.0)
  public class FormStep extends AbstractWizardStep<IPageForm> {

    @Override
    protected String getConfiguredTitle() {
      return TEXTS.get("Form");
    }

    @Override
    protected void execActivate(int stepKind) throws ProcessingException {
      IPageForm form = getChooseAFormStep().getForm().getFormField().getValue();
      setForm(form);
      setWizardForm(form);
    }
  }

  public FormStep getFormStep() {
    return getStep(DynamicFormWizard.FormStep.class);
  }
}

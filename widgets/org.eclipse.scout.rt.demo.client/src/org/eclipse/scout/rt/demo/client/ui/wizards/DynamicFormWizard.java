/*******************************************************************************
 * Copyright (c) 2013 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.rt.demo.client.ui.wizards;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.fields.GridData;
import org.eclipse.scout.rt.client.ui.wizard.AbstractWizardStep;
import org.eclipse.scout.rt.client.ui.wizard.DefaultWizardContainerForm;
import org.eclipse.scout.rt.client.ui.wizard.IWizardContainerForm;
import org.eclipse.scout.rt.client.ui.wizard.IWizardStep;
import org.eclipse.scout.rt.demo.client.ui.forms.DynamicFormWizardChooseAForm;
import org.eclipse.scout.rt.demo.client.ui.forms.IPageForm;
import org.eclipse.scout.rt.shared.TEXTS;

public class DynamicFormWizard extends AbstractPageWizard {

  public DynamicFormWizard() {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("DynamicFormWizard");
  }

  @Override
  protected IWizardContainerForm execCreateContainerForm() throws ProcessingException {
    DefaultWizardContainerForm f = (DefaultWizardContainerForm) super.execCreateContainerForm();

    // The wizard is 800x900px
    GridData gd = f.getRootGroupBox().getGridData();
    gd.widthInPixel = 800;
    gd.heightInPixel = 900;
    f.getRootGroupBox().setGridDataInternal(gd);

    // Position of the splitter will not be cached
    f.getSplitBox().setCacheSplitterPosition(false);
    // The splitter gives the left field 80% of the hole place
    f.getSplitBox().setSplitterPosition(0.8);
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

    @Override
    protected void execDeactivate(int stepKind) throws ProcessingException {
      if (stepKind == IWizardStep.STEP_NEXT) {
        getForm().validateForm();
      }
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

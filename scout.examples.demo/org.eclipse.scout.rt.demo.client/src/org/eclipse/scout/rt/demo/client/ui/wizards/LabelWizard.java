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
import org.eclipse.scout.rt.demo.client.ui.forms.LabelWizardFontForm;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelWizardForegroundColorForm;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelWizardLabelForm;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelWizardSizeForm;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.data.basic.FontSpec;

public class LabelWizard extends AbstractPageWizard {

  public LabelWizard() {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("LabelWizard");
  }

  @Override
  protected IWizardContainerForm execCreateContainerForm() throws ProcessingException {
    DefaultWizardContainerForm f = (DefaultWizardContainerForm) super.execCreateContainerForm();

    // The wizard is 600x400px
    GridData gd = f.getRootGroupBox().getGridData();
    gd.widthInPixel = 600;
    gd.heightInPixel = 400;
    f.getRootGroupBox().setGridDataInternal(gd);

    // Position of the splitter will not be cached
    f.getSplitBox().setCacheSplitterPosition(false);
    // The splitter gives the left field 70% of the hole place
    f.getSplitBox().setSplitterPosition(0.7);
    return f;
  }

  @Order(10.0)
  public class FontStep extends AbstractWizardStep<LabelWizardFontForm> {

    @Override
    protected String getConfiguredTitle() {
      return TEXTS.get("Font");
    }

    @Override
    protected void execActivate(int stepKind) throws ProcessingException {
      LabelWizardFontForm form = getForm();
      if (form == null) {
        form = new LabelWizardFontForm();
        form.startWizardStep(this, LabelWizardFontForm.WizardHandler.class);
        setForm(form);
      }
      getWizard().setWizardForm(form);
    }
  }

  public FontStep getFontStep() {
    return getStep(LabelWizard.FontStep.class);
  }

  @Order(20.0)
  public class SizeStep extends AbstractWizardStep<LabelWizardSizeForm> {

    @Override
    protected String getConfiguredTitle() {
      return TEXTS.get("Size");
    }

    @Override
    protected void execActivate(int stepKind) throws ProcessingException {
      LabelWizardSizeForm form = getForm();
      if (form == null) {
        form = new LabelWizardSizeForm();
        form.startWizardStep(this, LabelWizardSizeForm.WizardHandler.class);
        setForm(form);
      }
      getWizard().setWizardForm(form);
    }
  }

  public SizeStep getSizeStep() {
    return getStep(LabelWizard.SizeStep.class);
  }

  @Order(30.0)
  public class ForegroundColorStep extends AbstractWizardStep<LabelWizardForegroundColorForm> {

    @Override
    protected String getConfiguredTitle() {
      return TEXTS.get("ForegroundColor");
    }

    @Override
    protected void execActivate(int stepKind) throws ProcessingException {
      LabelWizardForegroundColorForm form = getForm();
      if (form == null) {
        form = new LabelWizardForegroundColorForm();
        form.startWizardStep(this, LabelWizardForegroundColorForm.WizardHandler.class);
        setForm(form);
      }
      getWizard().setWizardForm(form);
    }
  }

  public ForegroundColorStep getForegroundColorStep() {
    return getStep(LabelWizard.ForegroundColorStep.class);
  }

  @Order(40.0)
  public class LabelStep extends AbstractWizardStep<LabelWizardLabelForm> {

    @Override
    protected String getConfiguredTitle() {
      return TEXTS.get("Label");
    }

    @Override
    protected void execActivate(int stepKind) throws ProcessingException {
      LabelWizardLabelForm form = new LabelWizardLabelForm();
      form.getLoremField().setFont(new FontSpec(getFontStep().getForm().getFontField().getValue(), 0, (getSizeStep().getForm().getSizeField().getValue() != null) ? getSizeStep().getForm().getSizeField().getValue() : 12));
      form.getLoremField().setForegroundColor((getForegroundColorStep().getForm().getForegroundColorField().getValue() != null) ? getForegroundColorStep().getForm().getForegroundColorField().getValue() : "000000");
      form.startWizardStep(this, LabelWizardLabelForm.WizardHandler.class);
      setForm(form);
      getWizard().setWizardForm(form);
    }
  }

  public LabelStep getLabelStep() {
    return getStep(LabelWizard.LabelStep.class);
  }
}

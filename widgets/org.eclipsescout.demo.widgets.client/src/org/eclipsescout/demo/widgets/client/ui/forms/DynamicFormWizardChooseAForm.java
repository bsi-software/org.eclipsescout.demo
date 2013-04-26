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
package org.eclipsescout.demo.widgets.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipsescout.demo.widgets.client.services.lookup.FormLookupCall;
import org.eclipsescout.demo.widgets.client.ui.forms.DynamicFormWizardChooseAForm.MainBox.GroupBox;
import org.eclipsescout.demo.widgets.client.ui.forms.DynamicFormWizardChooseAForm.MainBox.GroupBox.FormField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

public class DynamicFormWizardChooseAForm extends AbstractForm {

  public DynamicFormWizardChooseAForm() throws ProcessingException {
    super();
  }

  public void startWizard() throws ProcessingException {
    startInternal(new WizardHandler());
  }

  public FormField getFormField() {
    return getFieldByClass(FormField.class);
  }

  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Override
      protected boolean getConfiguredGridUseUiWidth() {
        return true;
      }

      @Order(10.0)
      public class FormField extends AbstractSmartField<IPageForm> {

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Form");
        }

        @Override
        protected Class<? extends LookupCall> getConfiguredLookupCall() {
          return FormLookupCall.class;

        }

        @Override
        protected boolean getConfiguredMandatory() {
          return true;
        }
      }
    }
  }

  public class WizardHandler extends AbstractFormHandler {
  }
}

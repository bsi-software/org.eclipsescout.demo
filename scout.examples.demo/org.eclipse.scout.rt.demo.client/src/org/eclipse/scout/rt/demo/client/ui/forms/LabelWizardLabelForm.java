package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelWizardLabelForm.MainBox.GroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelWizardLabelForm.MainBox.GroupBox.LoremField;
import org.eclipse.scout.rt.shared.TEXTS;

public class LabelWizardLabelForm extends AbstractForm {

  public LabelWizardLabelForm() throws ProcessingException {
    super();
  }

  public void startWizard() throws ProcessingException {
    startInternal(new WizardHandler());
  }

  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  public LoremField getLoremField() {
    return getFieldByClass(LoremField.class);
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
      public class LoremField extends AbstractLabelField {

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected boolean getConfiguredWrapText() {
          return true;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue("<html>" + TEXTS.get("Lorem") + "</html>");
        }
      }
    }
  }

  public class WizardHandler extends AbstractFormHandler {
  }
}

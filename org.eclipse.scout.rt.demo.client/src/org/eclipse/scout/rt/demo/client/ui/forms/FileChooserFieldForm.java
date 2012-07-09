package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm.MainBox.GroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm.MainBox.GroupBox.ChooseAnImageField;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm.MainBox.GroupBox.OpenFileButton;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.shell.IShellService;
import org.eclipse.scout.service.SERVICES;

public class FileChooserFieldForm extends AbstractForm implements IPageForm {

  public FileChooserFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("FileChooserField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public ChooseAnImageField getChooseAnImageField() {
    return getFieldByClass(ChooseAnImageField.class);
  }

  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public OpenFileButton getOpenFileButton() {
    return getFieldByClass(OpenFileButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Order(10.0)
      public class ChooseAnImageField extends AbstractFileChooserField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ChooseAnImage");
        }

        @Override
        protected boolean getConfiguredTypeLoad() {
          return true;
        }

        @Override
        protected String[] getConfiguredFileExtensions() {
          return new String[]{"png", "bmp", "jpg", "jpeg", "gif"};
        }
      }

      @Order(20.0)
      public class OpenFileButton extends AbstractButton {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("OpenFile");
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          SERVICES.getService(IShellService.class).shellOpen(getChooseAnImageField().getValue());
        }
      }
    }

    @Order(20.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

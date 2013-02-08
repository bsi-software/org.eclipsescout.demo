package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.IOUtility;
import org.eclipse.scout.commons.annotations.InjectFieldTo;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.imagebox.AbstractImageField;
import org.eclipse.scout.rt.shared.TEXTS;

public class InjectionForm extends FileChooserFieldForm {

  public InjectionForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Injection");
  }

  public ImageField getShowImageField() {
    return getFieldByClass(ImageField.class);
  }

  @Order(15.0)
  @InjectFieldTo(FileChooserFieldForm.MainBox.TabBox.OpenImageBox.class)
  public class ImageField extends AbstractImageField {

    @Override
    protected boolean getConfiguredScrollBarEnabled() {
      return true;
    }

    @Override
    protected int getConfiguredGridW() {
      return 2;
    }

    @Override
    protected int getConfiguredGridH() {
      return 7;
    }
  }

  @Order(25.0)
  @InjectFieldTo(FileChooserFieldForm.MainBox.TabBox.OpenImageBox.class)
  public class ShowImageButton extends AbstractButton {

    @Override
    protected String getConfiguredLabel() {
      return TEXTS.get("ShowImage");
    }

    @Override
    protected void execClickAction() throws ProcessingException {
      if (IOUtility.fileExists(getChooseAnImageField().getValue())) {
        getShowImageField().setImage(IOUtility.getContent(getChooseAnImageField().getValue()));
        System.out.println(getShowImageField().getImageId());
      }
    }
  }
}

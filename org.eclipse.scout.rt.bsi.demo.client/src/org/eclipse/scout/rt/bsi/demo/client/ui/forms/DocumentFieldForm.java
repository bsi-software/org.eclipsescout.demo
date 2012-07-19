package org.eclipse.scout.rt.bsi.demo.client.ui.forms;

import java.net.URL;

import org.eclipse.scout.commons.IOUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.bsi.demo.client.Activator;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.DocumentFieldForm.MainBox.GroupBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.DocumentFieldForm.MainBox.GroupBox.DocumentField;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.documentfield.AbstractDocumentField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.file.RemoteFile;

public class DocumentFieldForm extends AbstractForm implements IPageForm {

  public DocumentFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("DocumentField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public DocumentField getDocumentField() {
    return getFieldByClass(DocumentField.class);
  }

  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  private void loadFile(String simpleName) throws ProcessingException {
    try {
      RemoteFile r = new RemoteFile(simpleName, 0L);
      URL resource = Activator.getDefault().getBundle().getResource("resources/html/" + simpleName);
      r.setContentTypeByExtension(IOUtility.getFileExtension(resource.getFile()));
      r.readData(resource.openStream());

      getDocumentField().setValue(r);
    }
    catch (Exception e) {
      throw new ProcessingException("load File", e);
    }
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Order(10.0)
      public class DocumentField extends AbstractDocumentField {

        @Override
        protected int getConfiguredGridH() {
          return 12;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }
      }
    }

    @Order(20.0)
    public class WizardStatusButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("WizardStatus");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        loadFile("wizardStatus.html");
      }
    }

    @Order(30.0)
    public class LeShopButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("LeShop");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        loadFile("LeShop.html");
      }
    }

    @Order(40.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

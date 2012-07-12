package org.eclipse.scout.rt.demo.client.ui.forms;

import java.io.IOException;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.browserfield.AbstractBrowserField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.BrowserFieldForm.MainBox.BrowserField;
import org.eclipse.scout.rt.demo.client.ui.forms.BrowserFieldForm.MainBox.BsiagButton;
import org.eclipse.scout.rt.demo.client.ui.forms.BrowserFieldForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.BrowserFieldForm.MainBox.WizardStatusButton;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.file.RemoteFile;

public class BrowserFieldForm extends AbstractForm implements IPageForm {

  public BrowserFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("BrowserField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public BrowserField getBrowserField() {
    return getFieldByClass(BrowserField.class);
  }

  public BsiagButton getBsiagButton() {
    return getFieldByClass(BsiagButton.class);
  }

  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public WizardStatusButton getWizardStatusButton() {
    return getFieldByClass(WizardStatusButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class BrowserField extends AbstractBrowserField {

      @Override
      protected boolean getConfiguredAutoDisplayText() {
        return false;
      }

      @Override
      protected int getConfiguredGridH() {
        return 20;
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
      protected boolean getConfiguredScrollBarEnabled() {
        return true;
      }

      @Override
      protected void execInitField() throws ProcessingException {
        try {
          RemoteFile f = new RemoteFile("wizardStatus.html", 0L);
          f.readData(org.eclipse.scout.rt.demo.client.Activator.getDefault().getBundle().getResource("resources/html/wizardStatus.html").openStream());

          setValue(f);
        }
        catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }

    @Order(20.0)
    public class CloseButton extends AbstractCloseButton {
    }

    @Order(30.0)
    public class BsiagButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Www.bsiag.com");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        getBrowserField().setValue(null);
        getBrowserField().setLocation("www.bsiag.com");
      }
    }

    @Order(40.0)
    public class WizardStatusButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("WizardStatus");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        try {
          RemoteFile f = new RemoteFile("wizardStatus.html", 0L);
          f.readData(org.eclipse.scout.rt.demo.client.Activator.getDefault().getBundle().getResource("resources/html/wizardStatus.html").openStream());

          getBrowserField().setValue(f);
        }
        catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

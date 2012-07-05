package org.eclipse.scout.rt.demo.client.ui.forms;

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
import org.eclipse.scout.rt.shared.TEXTS;

public class BrowserFieldForm extends AbstractForm implements IPageForm {

  public BrowserFieldForm() throws ProcessingException {
    super();
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
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

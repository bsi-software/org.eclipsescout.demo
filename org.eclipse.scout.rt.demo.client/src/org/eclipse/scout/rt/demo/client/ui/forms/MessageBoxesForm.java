package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBox;
import org.eclipse.scout.rt.demo.client.ui.forms.MessageBoxesForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.MessageBoxesForm.MainBox.MessageBoxWithOkButton;
import org.eclipse.scout.rt.demo.client.ui.forms.MessageBoxesForm.MainBox.MessageBoxWithYesNoCancelOptionButton;
import org.eclipse.scout.rt.demo.client.ui.forms.MessageBoxesForm.MainBox.MessageBoxWithYesNoOptionButton;
import org.eclipse.scout.rt.shared.TEXTS;

public class MessageBoxesForm extends AbstractForm implements IPageForm {

  public MessageBoxesForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("MessageBoxes");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public MessageBoxWithOkButton getMessageBoxWithOkButton() {
    return getFieldByClass(MessageBoxWithOkButton.class);
  }

  public MessageBoxWithYesNoCancelOptionButton getMessageBoxWithYesNoCancelOptionButton() {
    return getFieldByClass(MessageBoxWithYesNoCancelOptionButton.class);
  }

  public MessageBoxWithYesNoOptionButton getMessageBoxWithYesNoOptionButton() {
    return getFieldByClass(MessageBoxWithYesNoOptionButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class MessageBoxWithOkButton extends AbstractLinkButton {

      @Override
      protected int getConfiguredGridW() {
        return 0;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("MessageBoxWithOkButton");
      }

      @Override
      protected boolean getConfiguredProcessButton() {
        return false;
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        MessageBox.showOkMessage("Ok", null, "This is a MessageBox with an Ok-Button");
      }
    }

    @Order(20.0)
    public class MessageBoxWithYesNoOptionButton extends AbstractLinkButton {

      @Override
      protected int getConfiguredGridW() {
        return 0;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("MessageBoxWithYesNoOption");
      }

      @Override
      protected boolean getConfiguredProcessButton() {
        return false;
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        MessageBox.showYesNoMessage("Yes-No-Option", "This is a MessageBox with a Yes-No-Option", "Press \"Yes\" or \"No\"");
      }
    }

    @Order(30.0)
    public class MessageBoxWithYesNoCancelOptionButton extends AbstractLinkButton {

      @Override
      protected int getConfiguredGridW() {
        return 0;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("MessageBoxWithYesNoCancelOption");
      }

      @Override
      protected boolean getConfiguredProcessButton() {
        return false;
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        MessageBox.showYesNoCancelMessage("Yes-No-Cancel-Option", "This is a MessageBox with a Yes-No-Option", "Press \"Yes\", \"No\" or \"Cancel\"");
      }
    }

    @Order(40.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

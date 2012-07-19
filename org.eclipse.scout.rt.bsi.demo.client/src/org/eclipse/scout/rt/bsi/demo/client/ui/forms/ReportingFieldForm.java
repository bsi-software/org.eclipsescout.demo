package org.eclipse.scout.rt.bsi.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.ReportingFieldForm.MainBox.CloseButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.ReportingFieldForm.MainBox.InsertTextButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.ReportingFieldForm.MainBox.ReportingField;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.reportingfield.AbstractReportingFieldWithWord;
import org.eclipse.scout.rt.client.ui.form.fields.reportingfield.NativeWordUtility;
import org.eclipse.scout.rt.shared.TEXTS;

public class ReportingFieldForm extends AbstractForm implements IPageForm {

  public ReportingFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ReportingField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public InsertTextButton getInsertTextButton() {
    return getFieldByClass(InsertTextButton.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public ReportingField getReportingField() {
    return getFieldByClass(ReportingField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class ReportingField extends AbstractReportingFieldWithWord {

      @Override
      protected int getConfiguredGridH() {
        return 10;
      }

      @Override
      protected int getConfiguredGridW() {
        return 2;
      }
    }

    @Order(20.0)
    public class InsertTextButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("InsertText");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        NativeWordUtility.insertText(getReportingField().getNativeAdapter().getWordDocument(), TEXTS.get("Lorem"));
      }
    }

    @Order(30.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

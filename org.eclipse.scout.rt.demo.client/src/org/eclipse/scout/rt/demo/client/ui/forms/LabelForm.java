package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.integerfield.AbstractIntegerField;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.demo.client.services.lookup.FontstyleLookupCall;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelForm.MainBox.FieldBox;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelForm.MainBox.FieldBox.BackgroundColorField;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelForm.MainBox.FieldBox.FontField;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelForm.MainBox.FieldBox.FontstyleField;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelForm.MainBox.FieldBox.ForegroundColorField;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelForm.MainBox.FieldBox.LoremField;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelForm.MainBox.FieldBox.SizeField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.data.basic.FontSpec;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

public class LabelForm extends AbstractForm implements IPageForm {

  public LabelForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Label");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public ForegroundColorField getColorField() {
    return getFieldByClass(ForegroundColorField.class);
  }

  public BackgroundColorField getBackgroundColorField() {
    return getFieldByClass(BackgroundColorField.class);
  }

  public FieldBox getFieldBox() {
    return getFieldByClass(FieldBox.class);
  }

  public FontField getFontField() {
    return getFieldByClass(FontField.class);
  }

  public FontstyleField getFontstyleField() {
    return getFieldByClass(FontstyleField.class);
  }

  public LoremField getLoremField() {
    return getFieldByClass(LoremField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public SizeField getSizeField() {
    return getFieldByClass(SizeField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class FieldBox extends AbstractGroupBox {

      @Order(20.0)
      public class SizeField extends AbstractIntegerField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Size");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getLoremField().setFont(new FontSpec(
              getFontField().getValue() != null ? getFontField().getValue() : "",
              getFontstyleField().getValue() != null ? getFontstyleField().getValue() : 0,
              getValue()));
        }
      }

      @Order(30.0)
      public class ForegroundColorField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ForegroundColor");
        }

        @Override
        protected String execValidateValue(String rawValue) throws ProcessingException {
          clearErrorStatus();
          if (!rawValue.matches("[0-9A-Fa-f]{6}")) {
            this.setErrorStatus("\"" + rawValue + "\" " + TEXTS.get("NoColor"));
          }
          else {
            getLoremField().setForegroundColor(rawValue);
          }
          return rawValue;
        }
      }

      @Order(10.0)
      public class FontField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Font");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getLoremField().setFont(new FontSpec(
              getValue(),
              getFontstyleField().getValue() != null ? getFontstyleField().getValue() : 0,
              getSizeField().getValue() != null ? getSizeField().getValue() : 0));
        }
      }

      @Order(50.0)
      public class LoremField extends AbstractLabelField {

        @Override
        protected int getConfiguredGridH() {
          return 7;
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
        protected void execInitField() {
          this.setValue("<html>" + TEXTS.get("Lorem") + "</html>");
        }
      }

      @Order(40.0)
      public class BackgroundColorField extends AbstractStringField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("BackgroundColor");
        }

        @Override
        protected String execValidateValue(String rawValue) throws ProcessingException {
          clearErrorStatus();
          if (!rawValue.matches("[0-9A-Fa-f]{6}")) {
            this.setErrorStatus("\"" + rawValue + "\" " + TEXTS.get("NoColor"));
          }
          else {
            getLoremField().setBackgroundColor(rawValue);
          }
          return rawValue;
        }
      }

      @Order(15.0)
      public class FontstyleField extends AbstractSmartField<Integer> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Fontstyle");
        }

        @Override
        protected Class<? extends LookupCall> getConfiguredLookupCall() {
          return FontstyleLookupCall.class;
        }

        @Override
        protected boolean getConfiguredTreat0AsNull() {
          return false;
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getLoremField().setFont(new FontSpec(
              getFontField().getValue() != null ? getFontField().getValue() : "",
              getValue() != null ? getValue() : 0,
              getSizeField().getValue() != null ? getSizeField().getValue() : 0));
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

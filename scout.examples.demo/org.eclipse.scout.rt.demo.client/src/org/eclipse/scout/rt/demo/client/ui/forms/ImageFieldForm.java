package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.keystroke.AbstractKeyStroke;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractRadioButton;
import org.eclipse.scout.rt.client.ui.form.fields.doublefield.AbstractDoubleField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagebox.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.HorizontalAlignmentBox;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.HorizontalAlignmentBox.CenterField;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.HorizontalAlignmentBox.LeftField;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.HorizontalAlignmentBox.RightField;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.ImageSampleGroup;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.ImageSampleGroup.HorizontalAlignmentButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.ImageSampleGroup.RotateButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.ImageSampleGroup.ZoomButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.RotateBox;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.RotateBox.DegreeOfRotationField;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.RotateBox.RotateField;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.ZoomBox;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.ZoomBox.LabelField;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.ZoomBox.ZoomField;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.ZoomBox.ZoomvalueField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.ui.UserAgentUtility;

public class ImageFieldForm extends AbstractForm implements IPageForm {

  public ImageFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ImageField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public CenterField getCenterField() {
    return getFieldByClass(CenterField.class);
  }

  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public DegreeOfRotationField getDegreeOfRotationField() {
    return getFieldByClass(DegreeOfRotationField.class);
  }

  public HorizontalAlignmentBox getHorizontalAlignmentBox() {
    return getFieldByClass(HorizontalAlignmentBox.class);
  }

  public HorizontalAlignmentButton getHorizontalAlignmentButton() {
    return getFieldByClass(HorizontalAlignmentButton.class);
  }

  public ImageSampleGroup getImageSampleGroup() {
    return getFieldByClass(ImageSampleGroup.class);
  }

  public LeftField getLeftField() {
    return getFieldByClass(LeftField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public RightField getRightField() {
    return getFieldByClass(RightField.class);
  }

  public RotateBox getRotateBox() {
    return getFieldByClass(RotateBox.class);
  }

  public RotateButton getRotateButton() {
    return getFieldByClass(RotateButton.class);
  }

  public RotateField getRotateField() {
    return getFieldByClass(RotateField.class);
  }

  public ZoomButton getZoomButton() {
    return getFieldByClass(ZoomButton.class);
  }

  public LabelField getLabelField() {
    return getFieldByClass(LabelField.class);
  }

  public ZoomBox getZoomBox() {
    return getFieldByClass(ZoomBox.class);
  }

  public ZoomField getZoomField() {
    return getFieldByClass(ZoomField.class);
  }

  public ZoomvalueField getZoomvalueField() {
    return getFieldByClass(ZoomvalueField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class ImageSampleGroup extends AbstractRadioButtonGroup<Long> {

      @Override
      protected int getConfiguredGridW() {
        return 0;
      }

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Order(10.0)
      public class HorizontalAlignmentButton extends AbstractRadioButton {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("HorizontalAlignment");
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          getHorizontalAlignmentBox().setVisible(true);
          getZoomBox().setVisible(false);
          getRotateBox().setVisible(false);
        }
      }

      @Order(20.0)
      public class ZoomButton extends AbstractRadioButton {

        @Override
        protected boolean getConfiguredEnabled() {
          return UserAgentUtility.isRichClient();
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Zoom");
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          getHorizontalAlignmentBox().setVisible(false);
          getZoomBox().setVisible(true);
          getRotateBox().setVisible(false);
        }
      }

      @Order(30.0)
      public class RotateButton extends AbstractRadioButton {

        @Override
        protected boolean getConfiguredEnabled() {
          return UserAgentUtility.isRichClient();
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Rotate");
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          getHorizontalAlignmentBox().setVisible(false);
          getZoomBox().setVisible(false);
          getRotateBox().setVisible(true);
        }
      }
    }

    @Order(20.0)
    public class HorizontalAlignmentBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredBorderDecoration() {
        return "section";
      }

      @Override
      protected boolean getConfiguredExpandable() {
        return true;
      }

      @Override
      protected int getConfiguredGridH() {
        return 2;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("HorizontalAlignment");
      }

      @Order(10.0)
      public class LeftField extends AbstractImageField {

        @Override
        protected int getConfiguredGridH() {
          return 7;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected int getConfiguredHorizontalAlignment() {
          return -1;
        }

        @Override
        protected String getConfiguredImageId() {
          return "windsurf.jpg";
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Left");
        }
      }

      @Order(20.0)
      public class CenterField extends AbstractImageField {

        @Override
        protected int getConfiguredGridH() {
          return 7;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected int getConfiguredHorizontalAlignment() {
          return 0;
        }

        @Override
        protected String getConfiguredImageId() {
          return "windsurf.jpg";
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Center");
        }
      }

      @Order(30.0)
      public class RightField extends AbstractImageField {

        @Override
        protected int getConfiguredGridH() {
          return 7;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected int getConfiguredHorizontalAlignment() {
          return 1;
        }

        @Override
        protected String getConfiguredImageId() {
          return "windsurf.jpg";
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Right");
        }
      }
    }

    @Order(30.0)
    public class ZoomBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredBorderDecoration() {
        return "section";
      }

      @Override
      protected boolean getConfiguredExpandable() {
        return true;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Zoom");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }

      @Order(10.0)
      public class ZoomField extends AbstractImageField {

        @Override
        protected boolean getConfiguredGridUseUiHeight() {
          return true;
        }

        @Override
        protected boolean getConfiguredGridUseUiWidth() {
          return true;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected int getConfiguredHorizontalAlignment() {
          return -1;
        }

        @Override
        protected String getConfiguredImageId() {
          return "windsurf.jpg";
        }

        @Override
        protected int getConfiguredVerticalAlignment() {
          return -1;
        }
      }

      @Order(20.0)
      public class ZoomvalueField extends AbstractDoubleField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Zoom");
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getZoomField().doZoom(getValue(), getValue());
        }
      }

      @Order(30.0)
      public class LabelField extends AbstractLabelField {

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setValue(TEXTS.get("UseWASAndDToMoveTheImage"));
        }
      }
    }

    @Order(40.0)
    public class RotateBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredBorderDecoration() {
        return "section";
      }

      @Override
      protected boolean getConfiguredExpandable() {
        return true;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Rotate");
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }

      @Order(10.0)
      public class RotateField extends AbstractImageField {

        @Override
        protected boolean getConfiguredGridUseUiHeight() {
          return true;
        }

        @Override
        protected boolean getConfiguredGridUseUiWidth() {
          return true;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected int getConfiguredHorizontalAlignment() {
          return -1;
        }

        @Override
        protected String getConfiguredImageId() {
          return "windsurf.jpg";
        }

        @Override
        protected int getConfiguredVerticalAlignment() {
          return -1;
        }
      }

      @Order(20.0)
      public class DegreeOfRotationField extends AbstractDoubleField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DegreeOfRotation");
        }

        @Override
        protected Double getConfiguredMaximumValue() {
          return 360.0;
        }

        @Override
        protected Double getConfiguredMinimumValue() {
          return -360.0;
        }

        @Override
        protected void execChangedValue() throws ProcessingException {
          getRotateField().doRotate(getValue() / 57.3);
        }
      }
    }

    @Order(50.0)
    public class CloseButton extends AbstractCloseButton {
    }

    @Order(10.0)
    public class DownKeyStroke extends AbstractKeyStroke {

      @Override
      protected String getConfiguredKeyStroke() {
        return "S";
      }

      @Override
      protected void execAction() throws ProcessingException {
        if (getZoomButton().isSelected()) {
          getZoomField().doRelativePan(0, -10);
        }
      }
    }

    @Order(20.0)
    public class LeftKeyStroke extends AbstractKeyStroke {

      @Override
      protected String getConfiguredKeyStroke() {
        return "A";
      }

      @Override
      protected void execAction() throws ProcessingException {
        if (getZoomButton().isSelected()) {
          getZoomField().doRelativePan(10, 0);
        }
      }
    }

    @Order(30.0)
    public class RightKeyStroke extends AbstractKeyStroke {

      @Override
      protected String getConfiguredKeyStroke() {
        return "D";
      }

      @Override
      protected void execAction() throws ProcessingException {
        if (getZoomButton().isSelected()) {
          getZoomField().doRelativePan(-10, 0);
        }
      }
    }

    @Order(40.0)
    public class UpKeyStroke extends AbstractKeyStroke {

      @Override
      protected String getConfiguredKeyStroke() {
        return "W";
      }

      @Override
      protected void execAction() throws ProcessingException {
        if (getZoomButton().isSelected()) {
          getZoomField().doRelativePan(0, 10);
        }
      }
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagebox.AbstractImageField;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.HorizontalAlignmentBox;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.HorizontalAlignmentBox.LeftField;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.ZoomBox;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm.MainBox.ZoomBox.ZoomField;
import org.eclipse.scout.rt.shared.TEXTS;

public class ImageFieldForm extends AbstractForm implements IPageForm {

  public ImageFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ImageField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public HorizontalAlignmentBox getHorizontalAlignmentBox() {
    return getFieldByClass(HorizontalAlignmentBox.class);
  }

  public LeftField getLeftField() {
    return getFieldByClass(LeftField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public ZoomBox getZoomBox() {
    return getFieldByClass(ZoomBox.class);
  }

  public ZoomField getZoomField() {
    return getFieldByClass(ZoomField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
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
      protected String getConfiguredLabel() {
        return TEXTS.get("HorizontalAlignment");
      }

      @Order(10.0)
      public class LeftField extends AbstractImageField {

        @Override
        protected int getConfiguredGridH() {
          return 2;
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
          return 2;
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
          return 2;
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

    @Order(20.0)
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
      protected boolean getConfiguredExpanded() {
        return false;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Zoom");
      }

      @Order(20.0)
      public class ZoomField extends AbstractImageField {

        @Override
        protected int getConfiguredGridH() {
          return 2;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected String getConfiguredImageId() {
          return "windsurf.jpg";
        }
      }
    }

    @Order(30.0)
    public class CloseButton extends AbstractCloseButton {
  }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

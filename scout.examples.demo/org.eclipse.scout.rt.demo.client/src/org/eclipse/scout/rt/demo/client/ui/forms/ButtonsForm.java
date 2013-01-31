package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm.MainBox.EnabledButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm.MainBox.GroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm.MainBox.GroupBox.ButtonWithMenusButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm.MainBox.GroupBox.DefaultButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm.MainBox.GroupBox.DefaultButtonSelectedButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm.MainBox.GroupBox.LinkButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm.MainBox.GroupBox.LinkButtonSelectedButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm.MainBox.GroupBox.RadioButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm.MainBox.GroupBox.RadioButtonSelectedButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm.MainBox.GroupBox.ToggleButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm.MainBox.GroupBox.ToggleButtonSelectedButton;
import org.eclipse.scout.rt.shared.TEXTS;

public class ButtonsForm extends AbstractForm implements IPageForm {

  public ButtonsForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Buttons");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public ButtonWithMenusButton getButtonWithMenusButton() {
    return getFieldByClass(ButtonWithMenusButton.class);
  }

  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public DefaultButton getDefaultButton() {
    return getFieldByClass(DefaultButton.class);
  }

  public DefaultButtonSelectedButton getDefaultButtonSelectedButton() {
    return getFieldByClass(DefaultButtonSelectedButton.class);
  }

  public EnabledButton getEnabledButton() {
    return getFieldByClass(EnabledButton.class);
  }

  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  public LinkButton getLinkButton() {
    return getFieldByClass(LinkButton.class);
  }

  public LinkButtonSelectedButton getLinkButtonSelectedButton() {
    return getFieldByClass(LinkButtonSelectedButton.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public RadioButton getRadioButton() {
    return getFieldByClass(RadioButton.class);
  }

  public RadioButtonSelectedButton getRadioButtonSelectedButton() {
    return getFieldByClass(RadioButtonSelectedButton.class);
  }

  public ToggleButton getToggleButton() {
    return getFieldByClass(ToggleButton.class);
  }

  public ToggleButtonSelectedButton getToggleButtonSelectedButton() {
    return getFieldByClass(ToggleButtonSelectedButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Order(10.0)
      public class DefaultButton extends AbstractButton {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DefaultButton");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }
      }

      @Order(20.0)
      public class ToggleButton extends AbstractButton {

        @Override
        protected int getConfiguredDisplayStyle() {
          return DISPLAY_STYLE_TOGGLE;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ToggleButton");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }
      }

      @Order(30.0)
      public class LinkButton extends AbstractButton {

        @Override
        protected int getConfiguredDisplayStyle() {
          return DISPLAY_STYLE_LINK;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("LinkButton");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }
      }

      @Order(40.0)
      public class RadioButton extends AbstractButton {

        @Override
        protected int getConfiguredDisplayStyle() {
          return DISPLAY_STYLE_RADIO;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("RadioButton");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }
      }

      @Order(50.0)
      public class ButtonWithMenusButton extends AbstractButton {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ButtonWithMenus");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected void execClickAction() throws ProcessingException {
          requestPopup();
        }

        @Order(30.0)
        public class Menu3Menu extends AbstractMenu {

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("Menu3");
          }
        }

        @Order(20.0)
        public class Menu2Menu extends AbstractMenu {

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("Menu2");
          }
        }

        @Order(10.0)
        public class Menu1Menu extends AbstractMenu {

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("Menu1");
          }
        }
      }

      @Order(60.0)
      public class DefaultButtonSelectedButton extends AbstractButton {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("DefaultButtonSelected");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setSelected(true);
        }
      }

      @Order(70.0)
      public class ToggleButtonSelectedButton extends AbstractButton {

        @Override
        protected int getConfiguredDisplayStyle() {
          return DISPLAY_STYLE_TOGGLE;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ToggleButtonSelected");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setSelected(true);
        }
      }

      @Order(80.0)
      public class LinkButtonSelectedButton extends AbstractButton {

        @Override
        protected int getConfiguredDisplayStyle() {
          return DISPLAY_STYLE_LINK;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("LinkButtonSelected");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setSelected(true);
        }
      }

      @Order(90.0)
      public class RadioButtonSelectedButton extends AbstractButton {

        @Override
        protected int getConfiguredDisplayStyle() {
          return DISPLAY_STYLE_RADIO;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("RadioButtonSelected");
        }

        @Override
        protected boolean getConfiguredProcessButton() {
          return false;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          setSelected(true);
        }
      }
    }

    @Order(30.0)
    public class EnabledButton extends AbstractButton {

      @Override
      protected int getConfiguredDisplayStyle() {
        return DISPLAY_STYLE_TOGGLE;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Enabled");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        getGroupBox().setEnabled(isSelected());
      }

      @Override
      protected void execInitField() throws ProcessingException {
        setSelected(true);
      }
    }

    @Order(40.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

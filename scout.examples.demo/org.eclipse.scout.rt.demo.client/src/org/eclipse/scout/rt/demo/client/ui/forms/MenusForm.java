package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.checkbox.AbstractCheckBoxMenu;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.MenusForm.MainBox.MenusButton;
import org.eclipse.scout.rt.shared.TEXTS;

public class MenusForm extends AbstractForm implements IPageForm {

  public MenusForm() throws ProcessingException {
    super();
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public MenusButton getMenusButton() {
    return getFieldByClass(MenusButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class MenusButton extends AbstractButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Menus");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        requestPopup();
      }

      @Order(10.0)
      public class MenuWithTextMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("MenuWithText");
        }
      }

      @Order(20.0)
      public class MenuWithIconMenu extends AbstractMenu {

        @Override
        protected String getConfiguredIconId() {
          return org.eclipse.scout.rt.shared.AbstractIcons.Gears;
        }

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("MenuWithIcon");
        }
      }

      @Order(30.0)
      public class CheckableMenu extends AbstractCheckBoxMenu {

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("CheckableMenu");
        }
      }

      @Order(40.0)
      public class MenuWithMenusMenu extends AbstractMenu {

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("MenuWithMenus");
        }

        @Order(10.0)
        public class Menu1Menu extends AbstractMenu {

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("Menu1");
          }
        }

        @Order(20.0)
        public class Menu2Menu extends AbstractMenu {

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("Menu2");
          }
        }

        @Order(30.0)
        public class Menu3Menu extends AbstractMenu {

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("Menu3");
          }
        }
      }

      @Order(50.0)
      public class MenuWithKeyStrokeMenu extends AbstractMenu {

        @Override
        protected String getConfiguredKeyStroke() {
          return "m";
        }

        @Override
        protected String getConfiguredText() {
          return TEXTS.get("MenuWithKeyStroke");
        }
      }
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

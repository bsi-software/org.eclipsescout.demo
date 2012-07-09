package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.demo.client.ui.forms.TabBoxForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.TabBoxForm.MainBox.TabBox;
import org.eclipse.scout.rt.demo.client.ui.forms.TabBoxForm.MainBox.TabBox.Tab1Box;
import org.eclipse.scout.rt.demo.client.ui.forms.TabBoxForm.MainBox.TabBox.Tab2Box;
import org.eclipse.scout.rt.demo.client.ui.forms.TabBoxForm.MainBox.TabBox.Tab3Box;
import org.eclipse.scout.rt.shared.TEXTS;

public class TabBoxForm extends AbstractForm implements IPageForm {

  public TabBoxForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("TabBox");
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

  public Tab1Box getTab1Box() {
    return getFieldByClass(Tab1Box.class);
  }

  public Tab2Box getTab2Box() {
    return getFieldByClass(Tab2Box.class);
  }

  public Tab3Box getTab3Box() {
    return getFieldByClass(Tab3Box.class);
  }

  public TabBox getTabBox() {
    return getFieldByClass(TabBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class TabBox extends AbstractTabBox {

      @Order(10.0)
      public class Tab1Box extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Tab1");
        }
      }

      @Order(20.0)
      public class Tab2Box extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Tab2");
        }
      }

      @Order(30.0)
      public class Tab3Box extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Tab3");
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

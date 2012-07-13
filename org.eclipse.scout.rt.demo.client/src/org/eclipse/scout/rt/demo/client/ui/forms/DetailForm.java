package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.doublefield.AbstractDoubleField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.DetailForm.MainBox.GroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.DetailForm.MainBox.GroupBox.ValueHighField;
import org.eclipse.scout.rt.demo.client.ui.forms.DetailForm.MainBox.GroupBox.ValueLastField;
import org.eclipse.scout.rt.demo.client.ui.forms.DetailForm.MainBox.GroupBox.ValueLowField;
import org.eclipse.scout.rt.demo.client.ui.forms.DetailForm.MainBox.GroupBox.ValueOpenField;
import org.eclipse.scout.rt.demo.shared.services.process.IJaxWsProcessService;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

public class DetailForm extends AbstractForm {

  public DetailForm() throws ProcessingException {
    super();
  }

  public void setDataFields(String symbol) throws ProcessingException {
    double[] values = SERVICES.getService(IJaxWsProcessService.class).getDetailFormValues(symbol);
    getValueLastField().setValue(values[0]);
    getValueOpenField().setValue(values[1]);
    getValueLowField().setValue(values[2]);
    getValueHighField().setValue(values[3]);
  }

  public ValueHighField getValueHighField() {
    return getFieldByClass(ValueHighField.class);
  }

  public ValueLowField getValueLowField() {
    return getFieldByClass(ValueLowField.class);
  }

  public ValueOpenField getValueOpenField() {
    return getFieldByClass(ValueOpenField.class);
  }

  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  public ValueLastField getValueLastField() {
    return getFieldByClass(ValueLastField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Order(10.0)
      public class ValueLastField extends AbstractDoubleField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ValueLast");
        }
      }

      @Order(20.0)
      public class ValueOpenField extends AbstractDoubleField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ValueOpen");
        }
      }

      @Order(30.0)
      public class ValueLowField extends AbstractDoubleField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ValueLow");
        }
      }

      @Order(40.0)
      public class ValueHighField extends AbstractDoubleField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ValueHigh");
        }
      }
    }
  }

  public class NewHandler extends AbstractFormHandler {
  }
}

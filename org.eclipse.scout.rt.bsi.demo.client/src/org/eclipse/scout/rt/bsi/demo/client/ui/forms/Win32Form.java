package org.eclipse.scout.rt.bsi.demo.client.ui.forms;

import java.util.List;

import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.Win32Form.MainBox.CloseButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.Win32Form.MainBox.OutlookAppointmentsBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.Win32Form.MainBox.OutlookAppointmentsBox.OutlookAppointmentsField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.Win32Form.MainBox.OutlookContactsBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.Win32Form.MainBox.OutlookContactsBox.OutlookContactsField;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.win32.x86.outlook.OutlookAppointmentProxy;
import org.eclipse.scout.rt.shared.win32.x86.outlook.OutlookPersonProxy;
import org.eclipse.scout.rt.shared.win32.x86.outlook.OutlookProxy;

public class Win32Form extends AbstractForm implements IPageForm {

  public Win32Form() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Win32");
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

  public OutlookAppointmentsBox getOutlookAppointmentsBox() {
    return getFieldByClass(OutlookAppointmentsBox.class);
  }

  public OutlookAppointmentsField getOutlookAppointmentsField() {
    return getFieldByClass(OutlookAppointmentsField.class);
  }

  public OutlookContactsBox getOutlookContactsBox() {
    return getFieldByClass(OutlookContactsBox.class);
  }

  public OutlookContactsField getOutlookContactsField() {
    return getFieldByClass(OutlookContactsField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class OutlookAppointmentsBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("OutlookAppointments");
      }

      @Order(10.0)
      public class OutlookAppointmentsField extends AbstractTableField<OutlookAppointmentsField.Table> {

        @Override
        protected void execInitField() throws ProcessingException {
          OutlookProxy outlookProxy = new OutlookProxy();
          List<OutlookAppointmentProxy> appointments = outlookProxy.getCalendarFolder().getAllItems();
          Table table = getOutlookAppointmentsField().getTable();
          for (int i = 0; i < Math.min(appointments.size(), 20); i++) {
            OutlookAppointmentProxy appointment = appointments.get(i);
            ITableRow row = table.createRow();
            table.getAppointmentColumn().setValue(row, appointment.getSubject());
            table.getCategoryColumn().setValue(row, appointment.getCategories());
            table.addRow(row);
          }
        }

        @Order(10.0)
        public class Table extends AbstractTable {

          @Override
          protected boolean getConfiguredAutoResizeColumns() {
            return true;
          }

          public AppointmentColumn getAppointmentColumn() {
            return getColumnSet().getColumnByClass(AppointmentColumn.class);
          }

          public CategoryColumn getCategoryColumn() {
            return getColumnSet().getColumnByClass(CategoryColumn.class);
          }

          @Order(10.0)
          public class AppointmentColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Appointment");
            }
          }

          @Order(20.0)
          public class CategoryColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Category");
            }
          }
        }
      }

    }

    @Order(20.0)
    public class OutlookContactsBox extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("OutlookContacts");
      }

      @Order(10.0)
      public class OutlookContactsField extends AbstractTableField<OutlookContactsField.Table> {

        @Override
        protected void execInitField() throws ProcessingException {
          OutlookProxy outlookProxy = new OutlookProxy();
          List<OutlookPersonProxy> contacts = outlookProxy.getContactFolder().getAllItems();
          Table table = getOutlookContactsField().getTable();
          for (int i = 0; i < Math.min(contacts.size(), 20); i++) {
            OutlookPersonProxy contact = contacts.get(i);
            ITableRow row = table.createRow();
            table.getContactColumn().setValue(row, StringUtility.join(", ", contact.getLastName(), contact.getFirstName()));
            table.getCategoryColumn().setValue(row, contact.getCategories());
            table.addRow(row);
          }
        }

        @Order(10.0)
        public class Table extends AbstractTable {

          @Override
          protected boolean getConfiguredAutoResizeColumns() {
            return true;
          }

          public CategoryColumn getCategoryColumn() {
            return getColumnSet().getColumnByClass(CategoryColumn.class);
          }

          public ContactColumn getContactColumn() {
            return getColumnSet().getColumnByClass(ContactColumn.class);
          }

          @Order(10.0)
          public class ContactColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Contact");
            }
          }

          @Order(20.0)
          public class CategoryColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Category");
            }
          }
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

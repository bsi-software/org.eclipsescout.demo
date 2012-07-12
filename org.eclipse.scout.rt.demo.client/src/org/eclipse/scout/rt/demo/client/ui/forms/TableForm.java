package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.doublefield.AbstractDoubleField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBox;
import org.eclipse.scout.rt.demo.client.ui.forms.TableForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.TableForm.MainBox.GroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.TableForm.MainBox.GroupBox.EditableTableField;
import org.eclipse.scout.rt.demo.client.ui.forms.TableForm.MainBox.GroupBox.LastValueField;
import org.eclipse.scout.rt.demo.shared.services.process.IJaxWsProcessService;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

public class TableForm extends AbstractForm implements IPageForm {

  public TableForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Table");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public EditableTableField getEditableTableField() {
    return getFieldByClass(EditableTableField.class);
  }

  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  public LastValueField getLastValueField() {
    return getFieldByClass(LastValueField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Order(10.0)
      public class EditableTableField extends AbstractTableField<EditableTableField.Table> {

        @Override
        protected int getConfiguredGridH() {
          return 8;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected void execInitField() throws ProcessingException {
          Object data[][] = {
              {1, "Exxon Mobil Corporation", "XOM"},
              {2, "IBM", "IBM"},
              {3, "UBS", "UBS"},
              {4, "Coca-Cola Company", "KO"},
              {5, "Other Company", ""}};
          getTable().addRowsByMatrix(data);
        }

        @Order(10.0)
        public class Table extends AbstractTable {

          public NameColumn getNameColumn() {
            return getColumnSet().getColumnByClass(NameColumn.class);
          }

          public SymbolColumn getSymbolColumn() {
            return getColumnSet().getColumnByClass(SymbolColumn.class);
          }

          @Override
          protected boolean getConfiguredAutoResizeColumns() {
            return true;
          }

          @Override
          protected boolean getConfiguredMultiSelect() {
            return false;
          }

          @Override
          protected void execRowsSelected(ITableRow[] rows) throws ProcessingException {
            if (!rows[0].getCellValue(2).equals("") && rows[0].getCellValue(2) != null) {
              getLastValueField().setValue(SERVICES.getService(IJaxWsProcessService.class).getCompanyLastValue((String) rows[0].getCellValue(2)));
            }
          }

          public CompanyNrColumn getCompanyNrColumn() {
            return getColumnSet().getColumnByClass(CompanyNrColumn.class);
          }

          @Order(10.0)
          public class CompanyNrColumn extends AbstractLongColumn {

            @Override
            protected boolean getConfiguredDisplayable() {
              return false;
            }
          }

          @Order(20.0)
          public class NameColumn extends AbstractStringColumn {

            @Override
            protected boolean getConfiguredEditable() {
              return true;
            }

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Name");
            }

            @Override
            protected boolean execIsEditable(ITableRow row) throws ProcessingException {
              if (getCompanyNrColumn().getValue(row) == 5) {
                return super.execIsEditable(row);
              }
              return false;
            }
          }

          @Order(30.0)
          public class SymbolColumn extends AbstractStringColumn {

            @Override
            protected boolean getConfiguredEditable() {
              return true;
            }

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Symbol");
            }

            @Override
            protected boolean execIsEditable(ITableRow row) throws ProcessingException {
              if (getCompanyNrColumn().getValue(row) == 5) {
                return super.execIsEditable(row);
              }
              return false;
            }
          }

          @Order(10.0)
          public class GetValueMenu extends AbstractMenu {

            @Override
            protected String getConfiguredText() {
              return TEXTS.get("GetValue");
            }

            @Override
            protected void execAction() throws ProcessingException {
              try {
                MessageBox.showOkMessage("Value of " + getNameColumn().getSelectedValue(), null, "The value of " + getNameColumn().getSelectedValue() + " is " + SERVICES.getService(IJaxWsProcessService.class).getCompanyLastValue((String) getSymbolColumn().getSelectedValue()));
              }
              catch (ProcessingException e) {
                MessageBox.showOkMessage("No value found", null, "No value found for " + getNameColumn().getSelectedValue());
              }
            }
          }
        }
      }

      @Order(20.0)
      public class LastValueField extends AbstractDoubleField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("LastValue");
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

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
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.demo.client.ui.forms.DetailForm.MainBox.GroupBox.ValueLastField;
import org.eclipse.scout.rt.demo.client.ui.forms.TableFieldForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.TableFieldForm.MainBox.GroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.TableFieldForm.MainBox.GroupBox.EditableTableField;
import org.eclipse.scout.rt.shared.TEXTS;

public class TableFieldForm extends AbstractForm implements IPageForm {

  public TableFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("TableField");
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
          Object data[][] = new Object[][]{
              {1, "Exxon Mobil Corporation", "XOM"},
              {2, "IBM", "IBM"},
              {3, "UBS", "UBS"},
              {4, "Coca-Cola Company", "KO"}};
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
              if (getCompanyNrColumn().getValue(row) >= 5) {
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
              if (getCompanyNrColumn().getValue(row) >= 5) {
                return super.execIsEditable(row);
              }
              return false;
            }
          }

          @Order(10.0)
          public class NewCompanyMenu extends AbstractMenu {

            @Override
            protected boolean getConfiguredEmptySpaceAction() {
              return true;
            }

            @Override
            protected boolean getConfiguredSingleSelectionAction() {
              return false;
            }

            @Override
            protected String getConfiguredText() {
              return TEXTS.get("NewCompany");
            }

            @Override
            protected void execAction() throws ProcessingException {
              getEditableTableField().getTable().addRowByArray(new Object[]{getEditableTableField().getTable().getCompanyNrColumn().getValues().length + 1, "New Company", ""});
            }
          }
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

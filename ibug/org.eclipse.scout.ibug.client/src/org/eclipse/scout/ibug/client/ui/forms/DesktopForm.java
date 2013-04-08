package org.eclipse.scout.ibug.client.ui.forms;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.ibug.client.ui.forms.DesktopForm.MainBox.DesktopBox;
import org.eclipse.scout.ibug.client.ui.forms.DesktopForm.MainBox.DesktopBox.AssigneeBox;
import org.eclipse.scout.ibug.client.ui.forms.DesktopForm.MainBox.DesktopBox.AssigneeBox.AssigneeField;
import org.eclipse.scout.ibug.client.ui.forms.DesktopForm.MainBox.DesktopBox.AssigneeBox.RefreshButton;
import org.eclipse.scout.ibug.client.ui.forms.DesktopForm.MainBox.DesktopBox.BugsField;
import org.eclipse.scout.ibug.shared.Icons;
import org.eclipse.scout.ibug.shared.services.DesktopFormData;
import org.eclipse.scout.ibug.shared.services.IDesktopService;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.shell.IShellService;
import org.eclipse.scout.service.SERVICES;

@FormData(value = DesktopFormData.class, sdkCommand = SdkCommand.CREATE)
public class DesktopForm extends AbstractForm {

  public DesktopForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  @Override
  protected String getConfiguredDisplayViewId() {
    return VIEW_ID_CENTER;
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.EclipseScout;
  }

  public AssigneeBox getAssigneeBox() {
    return getFieldByClass(AssigneeBox.class);
  }

  public AssigneeField getAssigneeField() {
    return getFieldByClass(AssigneeField.class);
  }

  public BugsField getBugsField() {
    return getFieldByClass(BugsField.class);
  }

  public DesktopBox getDesktopBox() {
    return getFieldByClass(DesktopBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public RefreshButton getRefreshButton() {
    return getFieldByClass(RefreshButton.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Override
    protected int getConfiguredGridColumnCount() {
      return 1;
    }

    @Order(10.0)
    public class DesktopBox extends AbstractGroupBox {

      @Order(10.0)
      public class AssigneeBox extends AbstractSequenceBox {

        @Order(10.0)
        public class AssigneeField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Assignee");
          }
        }

        @Order(20.0)
        public class RefreshButton extends AbstractButton {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Refresh");
          }

          @Override
          protected void execClickAction() throws ProcessingException {
            ((ViewHandler) getHandler()).execLoad();
          }
        }
      }

      @Order(30.0)
      public class BugsField extends AbstractTableField<BugsField.Table> {

        @Override
        protected int getConfiguredGridH() {
          return 4;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MostRecentBugs");
        }

        @Order(10.0)
        public class Table extends AbstractExtensibleTable {

          public SeveretyColumn getSeveretyColumn() {
            return getColumnSet().getColumnByClass(SeveretyColumn.class);
          }

          public PriorityColumn getPriorityColumn() {
            return getColumnSet().getColumnByClass(PriorityColumn.class);
          }

          public TargetMilestoneColumn getTargetMilestoneColumn() {
            return getColumnSet().getColumnByClass(TargetMilestoneColumn.class);
          }

          public StatusColumn getStatusColumn() {
            return getColumnSet().getColumnByClass(StatusColumn.class);
          }

          public ResolutionColumn getResolutionColumn() {
            return getColumnSet().getColumnByClass(ResolutionColumn.class);
          }

          public SortValueColumn getSortValueColumn() {
            return getColumnSet().getColumnByClass(SortValueColumn.class);
          }

          public SummaryColumn getSummaryColumn() {
            return getColumnSet().getColumnByClass(SummaryColumn.class);
          }

          public LastChangedColumn getLastChangedColumn() {
            return getColumnSet().getColumnByClass(LastChangedColumn.class);
          }

          @Override
          protected void execRowAction(ITableRow row) throws ProcessingException {
            getMenu(OpenBugMenu.class).execAction();
          }

          public AssigneeColumn getAssigneeColumn() {
            return getColumnSet().getColumnByClass(AssigneeColumn.class);
          }

          public ComponentColumn getComponentColumn() {
            return getColumnSet().getColumnByClass(ComponentColumn.class);
          }

          public IDColumn getIDColumn() {
            return getColumnSet().getColumnByClass(IDColumn.class);
          }

          @Order(10.0)
          public class IDColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("ID");
            }

            @Override
            protected boolean getConfiguredPrimaryKey() {
              return true;
            }
          }

          @Order(20.0)
          public class SummaryColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Summary");
            }
          }

          @Order(30.0)
          public class LastChangedColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("LastChanged");
            }
          }

          @Order(40.0)
          public class SeveretyColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Severety");
            }
          }

          @Order(50.0)
          public class PriorityColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Priority");
            }
          }

          @Order(60.0)
          public class TargetMilestoneColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("TargetMilestone");
            }
          }

          @Order(70.0)
          public class StatusColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Status");
            }
          }

          @Order(80.0)
          public class ResolutionColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Resolution");
            }
          }

          @Order(90.0)
          public class ComponentColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Component");
            }
          }

          @Order(100.0)
          public class AssigneeColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Assignee");
            }
          }

          @Order(110.0)
          public class SortValueColumn extends AbstractIntegerColumn {

            @Override
            protected boolean getConfiguredDisplayable() {
              return false;
            }

            @Override
            protected int getConfiguredSortIndex() {
              return 0;
            }

            @Override
            protected boolean getConfiguredVisible() {
              return false;
            }
          }

          @Order(10.0)
          public class OpenBugMenu extends AbstractExtensibleMenu {

            @Override
            protected String getConfiguredText() {
              return TEXTS.get("OpenBug");
            }

            @Override
            protected void execAction() throws ProcessingException {
              IShellService shell = SERVICES.getService(IShellService.class);
              shell.shellOpen("https://bugs.eclipse.org/bugs/show_bug.cgi?id=" + getTable().getSelectedRow().getKeyValues()[0]);
            }
          }
        }
      }
    }
  }

  public class ViewHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      IDesktopService service = SERVICES.getService(IDesktopService.class);
      DesktopFormData formData = new DesktopFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);

    }
  }

  public void startView() throws ProcessingException {
    startInternal(new ViewHandler());
  }
}

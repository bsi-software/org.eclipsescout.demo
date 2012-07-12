package org.eclipse.scout.rt.demo.client.ui.forms;

import java.io.File;

import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.filechooserfield.AbstractFileChooserField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm.MainBox.TabBox;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm.MainBox.TabBox.FolderContentsBox;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm.MainBox.TabBox.FolderContentsBox.ContentField;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm.MainBox.TabBox.FolderContentsBox.SelectAFolderField;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm.MainBox.TabBox.OpenImageBox;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm.MainBox.TabBox.OpenImageBox.ChooseAnImageField;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm.MainBox.TabBox.OpenImageBox.OpenFileButton;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.shell.IShellService;
import org.eclipse.scout.service.SERVICES;

public class FileChooserFieldForm extends AbstractForm implements IPageForm {

  public FileChooserFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("FileChooserField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public ChooseAnImageField getChooseAnImageField() {
    return getFieldByClass(ChooseAnImageField.class);
  }

  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public ContentField getContentField() {
    return getFieldByClass(ContentField.class);
  }

  public FolderContentsBox getFolderContentsBox() {
    return getFieldByClass(FolderContentsBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public OpenFileButton getOpenFileButton() {
    return getFieldByClass(OpenFileButton.class);
  }

  public OpenImageBox getOpenImageBox() {
    return getFieldByClass(OpenImageBox.class);
  }

  public SelectAFolderField getSelectAFolderField() {
    return getFieldByClass(SelectAFolderField.class);
  }

  public TabBox getTabBox() {
    return getFieldByClass(TabBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(20.0)
    public class TabBox extends AbstractTabBox {

      @Order(10.0)
      public class OpenImageBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("OpenImage");
        }

        @Order(10.0)
        public class ChooseAnImageField extends AbstractFileChooserField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("ChooseAnImage");
          }

          @Override
          protected boolean getConfiguredTypeLoad() {
            return true;
          }

          @Override
          protected String[] getConfiguredFileExtensions() {
            return new String[]{"png", "bmp", "jpg", "jpeg", "gif"};
          }
        }

        @Order(20.0)
        public class OpenFileButton extends AbstractButton {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("OpenFile");
          }

          @Override
          protected void execClickAction() throws ProcessingException {
            SERVICES.getService(IShellService.class).shellOpen(getChooseAnImageField().getValue());
          }
        }
      }

      @Order(20.0)
      public class FolderContentsBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("FolderContents");
        }

        @Order(10.0)
        public class SelectAFolderField extends AbstractFileChooserField {

          @Override
          protected boolean getConfiguredFolderMode() {
            return true;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("SelectAFolder");
          }

          @Override
          protected boolean getConfiguredTypeLoad() {
            return true;
          }

          @Override
          protected void execChangedValue() throws ProcessingException {
            String folderName = getValue();
            getContentField().getTable().deleteAllRows();
            if (!StringUtility.isNullOrEmpty(folderName)) {
              File folder = new File(folderName);
              for (File f : folder.listFiles()) {
                String fileName = f.getName();
                String filePath = f.getPath();
                getContentField().getTable().addRowByArray(new Object[]{f, fileName, filePath});
              }
            }
          }
        }

        @Order(20.0)
        public class ContentField extends AbstractTableField<ContentField.Table> {

          @Override
          protected int getConfiguredGridH() {
            return 5;
          }

          @Override
          protected int getConfiguredGridW() {
            return 2;
          }

          @Order(10.0)
          public class Table extends AbstractTable {

            public FileNameColumn getFileNameColumn() {
              return getColumnSet().getColumnByClass(FileNameColumn.class);
            }

            public PathColumn getPathColumn() {
              return getColumnSet().getColumnByClass(PathColumn.class);
            }

            @Override
            protected boolean getConfiguredAutoResizeColumns() {
              return true;
            }

            public FileColumn getFileColumn() {
              return getColumnSet().getColumnByClass(FileColumn.class);
            }

            @Order(10.0)
            public class FileColumn extends AbstractColumn<File> {

              @Override
              protected boolean getConfiguredDisplayable() {
                return false;
              }
            }

            @Order(20.0)
            public class FileNameColumn extends AbstractStringColumn {

              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("FileName");
              }
            }

            @Order(30.0)
            public class PathColumn extends AbstractStringColumn {

              @Override
              protected String getConfiguredHeaderText() {
                return TEXTS.get("Path");
              }
            }

            @Order(10.0)
            public class FormStateLoadMenu extends AbstractMenu {

              @Override
              protected String getConfiguredText() {
                return TEXTS.get("FormStateLoad");
              }

              @Override
              protected void execAction() throws ProcessingException {
                if (getContentField().getTable().getFileColumn().getSelectedValue().isDirectory()) {
                  getSelectAFolderField().setValue(getContentField().getTable().getPathColumn().getSelectedValue());
                }
                else {
                  SERVICES.getService(IShellService.class).shellOpen(getContentField().getTable().getPathColumn().getSelectedValue());
                }
              }
            }

            @Order(20.0)
            public class UpOneLevelMenu extends AbstractMenu {

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
                return TEXTS.get("UpOneLevel");
              }

              @Override
              protected void execAction() throws ProcessingException {
                if (getSelectAFolderField().getValue() != null) {
                  getSelectAFolderField().setValue(new File(getSelectAFolderField().getValue()).getParent());
                }
              }
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

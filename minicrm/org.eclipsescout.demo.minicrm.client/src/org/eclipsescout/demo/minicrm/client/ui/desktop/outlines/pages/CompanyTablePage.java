package org.eclipsescout.demo.minicrm.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractSmartColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.extension.client.ui.action.menu.AbstractExtensibleMenu;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm;
import org.eclipsescout.demo.minicrm.client.ui.desktop.outlines.pages.searchform.CompanySearchForm;
import org.eclipsescout.demo.minicrm.shared.services.IStandardOutlineService;
import org.eclipsescout.demo.minicrm.shared.services.code.CompanyTypeCodeType;
import org.eclipsescout.demo.minicrm.shared.ui.desktop.outlines.pages.searchform.CompanySearchFormData;

public class CompanyTablePage extends AbstractPageWithTable<CompanyTablePage.Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Company");
  }

  @Override
  protected IPage execCreateChildPage(ITableRow row) throws ProcessingException {
    CompanyDetailsNodePage childPage = new CompanyDetailsNodePage();
    childPage.setCompanyNr(getTable().getCompanyNrColumn().getValue(row));
    return childPage;
  }

  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    CompanySearchFormData formData = (CompanySearchFormData) filter.getFormData();
    if (formData == null) {
      formData = new CompanySearchFormData();
    }
    return SERVICES.getService(IStandardOutlineService.class).getCompanyTableData(formData);
  }

  @Order(10.0)
  public class Table extends AbstractExtensibleTable {

    public ShortNameColumn getShortNameColumn() {
      return getColumnSet().getColumnByClass(ShortNameColumn.class);
    }

    public NameColumn getNameColumn() {
      return getColumnSet().getColumnByClass(NameColumn.class);
    }

    public CompanyTypeColumn getCompanyTypeColumn() {
      return getColumnSet().getColumnByClass(CompanyTypeColumn.class);
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

      @Override
      protected boolean getConfiguredPrimaryKey() {
        return true;
      }
    }

    @Order(20.0)
    public class ShortNameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("ShortName");
      }

      @Override
      protected int getConfiguredWidth() {
        return 200;
      }
    }

    @Order(30.0)
    public class NameColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Name");
      }
    }

    @Order(40.0)
    public class CompanyTypeColumn extends AbstractSmartColumn<Long> {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("CompanyType");
      }

      @Override
      protected Class<? extends ICodeType> getConfiguredCodeType() {
        return CompanyTypeCodeType.class;

      }
    }

    @Order(10.0)
    public class NewCompanyMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("NewCompany");
      }

      @Override
      protected boolean getConfiguredEmptySpaceAction() {
        return true;
      }

      @Override
      protected boolean getConfiguredSingleSelectionAction() {
        return false;
      }

      @Override
      protected void execAction() throws ProcessingException {
        CompanyForm form = new CompanyForm();
        form.startNew();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }

    @Order(20.0)
    public class EditCompanyMenu extends AbstractExtensibleMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("EditCompany");
      }

      @Override
      protected void execAction() throws ProcessingException {
        CompanyForm form = new CompanyForm();
        form.setCompanyNr(getCompanyNrColumn().getSelectedValue());
        form.startModify();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }
  }

  @Override
  protected Class<? extends ISearchForm> getConfiguredSearchForm() {
    return CompanySearchForm.class;
  }
}

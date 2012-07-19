package org.eclipse.scout.rt.bsi.demo.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.win32.x86.excel.ScoutExcelAdapter;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class ExportToExcelTablePage extends AbstractPageWithTable<ExportToExcelTablePage.Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ExportToExcel");
  }

  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    Object[][] data = new Object[(int) (Math.random() * 100) + 1][3];
    for (int i = 0; i < data.length; i++) {
      Object o = new Object();
      data[i][0] = i + 1;
      data[i][1] = o.toString();
      data[i][2] = o.hashCode();
    }
    return data;
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    reloadPage();
    ScoutExcelAdapter sea = new ScoutExcelAdapter();
    sea.exportPage(null, 1, 1, this);
  }

  @Order(10.0)
  public class Table extends AbstractTable {

    public RandomHashColumn getRandomHashColumn() {
      return getColumnSet().getColumnByClass(RandomHashColumn.class);
    }

    public RandomNumberColumn getRandomNumberColumn() {
      return getColumnSet().getColumnByClass(RandomNumberColumn.class);
    }

    @Override
    protected boolean getConfiguredAutoResizeColumns() {
      return true;
    }

    public NrColumn getNrColumn() {
      return getColumnSet().getColumnByClass(NrColumn.class);
    }

    @Order(10.0)
    public class NrColumn extends AbstractLongColumn {

      @Override
      protected boolean getConfiguredDisplayable() {
        return false;
      }
    }

    @Order(20.0)
    public class RandomHashColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Objectname");
      }
    }

    @Order(30.0)
    public class RandomNumberColumn extends AbstractLongColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Hashcode");
      }
    }
  }
}

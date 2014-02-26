package org.eclipsescout.demo.minicrm.spec.ui.swing.tablepages;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.table.ITable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPageWithTable;
import org.eclipse.scout.rt.spec.client.AbstractTablePageSpecGen;
import org.eclipsescout.demo.minicrm.client.ui.desktop.outlines.pages.CompanyTablePage;

public class CompanyTablePageSpecTest extends AbstractTablePageSpecGen {

  @Override
  public IPageWithTable<? extends ITable> createAndInitTablePage() throws ProcessingException {
    CompanyTablePage page = new CompanyTablePage();
    page.initPage();
    return page;
  }

}

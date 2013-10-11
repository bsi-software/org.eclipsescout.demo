/*******************************************************************************
 * Copyright (c) 2013 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipsescout.demo.minicrm.shared.ui.desktop.outlines.pages;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated, no manual modifications recommended.
 * 
 * @generated
 */
public class CompanyTablePageData extends AbstractTablePageData {

  private static final long serialVersionUID = 1L;

  public CompanyTablePageData() {
  }

  @Override
  public CompanyTablePageRowData addRow() {
    return (CompanyTablePageRowData) super.addRow();
  }

  @Override
  public CompanyTablePageRowData addRow(int rowState) {
    return (CompanyTablePageRowData) super.addRow(rowState);
  }

  @Override
  public CompanyTablePageRowData createRow() {
    return new CompanyTablePageRowData();
  }

  @Override
  public Class<? extends AbstractTableRowData> getRowType() {
    return CompanyTablePageRowData.class;
  }

  @Override
  public CompanyTablePageRowData[] getRows() {
    return (CompanyTablePageRowData[]) super.getRows();
  }

  @Override
  public CompanyTablePageRowData rowAt(int index) {
    return (CompanyTablePageRowData) super.rowAt(index);
  }

  public void setRows(CompanyTablePageRowData[] rows) {
    super.setRows(rows);
  }

  public static class CompanyTablePageRowData extends AbstractTableRowData {

    private static final long serialVersionUID = 1L;
    public static final String companyNr = "companyNr";
    public static final String shortName = "shortName";
    public static final String name = "name";
    public static final String companyType = "companyType";
    private Long m_companyNr;
    private String m_shortName;
    private String m_name;
    private Long m_companyType;

    public CompanyTablePageRowData() {
    }

    public Long getCompanyNr() {
      return m_companyNr;
    }

    public void setCompanyNr(Long companyNr) {
      m_companyNr = companyNr;
    }

    public String getShortName() {
      return m_shortName;
    }

    public void setShortName(String shortName) {
      m_shortName = shortName;
    }

    public String getName() {
      return m_name;
    }

    public void setName(String name) {
      m_name = name;
    }

    public Long getCompanyType() {
      return m_companyType;
    }

    public void setCompanyType(Long companyType) {
      m_companyType = companyType;
    }
  }
}

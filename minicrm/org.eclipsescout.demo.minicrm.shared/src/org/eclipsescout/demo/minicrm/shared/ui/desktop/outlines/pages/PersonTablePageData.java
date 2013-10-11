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
public class PersonTablePageData extends AbstractTablePageData {

  private static final long serialVersionUID = 1L;

  public PersonTablePageData() {
  }

  @Override
  public PersonTablePageRowData addRow() {
    return (PersonTablePageRowData) super.addRow();
  }

  @Override
  public PersonTablePageRowData addRow(int rowState) {
    return (PersonTablePageRowData) super.addRow(rowState);
  }

  @Override
  public PersonTablePageRowData createRow() {
    return new PersonTablePageRowData();
  }

  @Override
  public Class<? extends AbstractTableRowData> getRowType() {
    return PersonTablePageRowData.class;
  }

  @Override
  public PersonTablePageRowData[] getRows() {
    return (PersonTablePageRowData[]) super.getRows();
  }

  @Override
  public PersonTablePageRowData rowAt(int index) {
    return (PersonTablePageRowData) super.rowAt(index);
  }

  public void setRows(PersonTablePageRowData[] rows) {
    super.setRows(rows);
  }

  public static class PersonTablePageRowData extends AbstractTableRowData {

    private static final long serialVersionUID = 1L;
    public static final String personNr = "personNr";
    public static final String lastName = "lastName";
    public static final String firstName = "firstName";
    private Long m_personNr;
    private String m_lastName;
    private String m_firstName;

    public PersonTablePageRowData() {
    }

    public Long getPersonNr() {
      return m_personNr;
    }

    public void setPersonNr(Long personNr) {
      m_personNr = personNr;
    }

    public String getLastName() {
      return m_lastName;
    }

    public void setLastName(String lastName) {
      m_lastName = lastName;
    }

    public String getFirstName() {
      return m_firstName;
    }

    public void setFirstName(String firstName) {
      m_firstName = firstName;
    }
  }
}

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
package org.eclipsescout.demo.minifigcreator.shared.services.process;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.tablefield.AbstractTableFieldData;
import org.eclipsescout.demo.minifigcreator.shared.minifig.part.Part;

public class ServerFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public ServerFormData() {
  }

  public Table getTable() {
    return getFieldByClass(Table.class);
  }

  public static class Table extends AbstractTableFieldData {
    private static final long serialVersionUID = 1L;

    public Table() {
    }

    public static final int PART_COLUMN_ID = 0;
    public static final int TYPE_COLUMN_ID = 1;
    public static final int NAME_COLUMN_ID = 2;
    public static final int QUANTITY_COLUMN_ID = 3;

    public void setPart(int row, Part part) {
      setValueInternal(row, PART_COLUMN_ID, part);
    }

    public Part getPart(int row) {
      return (Part) getValueInternal(row, PART_COLUMN_ID);
    }

    public void setType(int row, String type) {
      setValueInternal(row, TYPE_COLUMN_ID, type);
    }

    public String getType(int row) {
      return (String) getValueInternal(row, TYPE_COLUMN_ID);
    }

    public void setName(int row, String name) {
      setValueInternal(row, NAME_COLUMN_ID, name);
    }

    public String getName(int row) {
      return (String) getValueInternal(row, NAME_COLUMN_ID);
    }

    public void setQuantity(int row, Integer quantity) {
      setValueInternal(row, QUANTITY_COLUMN_ID, quantity);
    }

    public Integer getQuantity(int row) {
      return (Integer) getValueInternal(row, QUANTITY_COLUMN_ID);
    }

    @Override
    public int getColumnCount() {
      return 4;
    }

    @Override
    public Object getValueAt(int row, int column) {
      switch (column) {
        case PART_COLUMN_ID:
          return getPart(row);
        case TYPE_COLUMN_ID:
          return getType(row);
        case NAME_COLUMN_ID:
          return getName(row);
        case QUANTITY_COLUMN_ID:
          return getQuantity(row);
        default:
          return null;
      }
    }

    @Override
    public void setValueAt(int row, int column, Object value) {
      switch (column) {
        case PART_COLUMN_ID:
          setPart(row, (Part) value);
          break;
        case TYPE_COLUMN_ID:
          setType(row, (String) value);
          break;
        case NAME_COLUMN_ID:
          setName(row, (String) value);
          break;
        case QUANTITY_COLUMN_ID:
          setQuantity(row, (Integer) value);
          break;
      }
    }
  }
}

/*******************************************************************************
 * Copyright (c) 2011 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Daniel Wiehl (Business Systems Integration AG) - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.tutorial.jaxws.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.service.SERVICES;
import org.eclipse.scout.tutorial.jaxws.client.ui.forms.WsLogForm;
import org.eclipse.scout.tutorial.jaxws.shared.services.outline.IMainOutlineService;

public class WsLogTablePage extends AbstractPageWithTable<WsLogTablePage.Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("WsLog");
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    reloadPage();
  }

  @Override
  protected Object[][] execLoadTableData(SearchFilter filter) throws ProcessingException {
    return SERVICES.getService(IMainOutlineService.class).getWsLogTableData();
  }

  @Order(10.0)
  public class Table extends AbstractTable {

    public ServiceColumn getServiceColumn() {
      return getColumnSet().getColumnByClass(ServiceColumn.class);
    }

    public PortColumn getPortColumn() {
      return getColumnSet().getColumnByClass(PortColumn.class);
    }

    public OperationColumn getOperationColumn() {
      return getColumnSet().getColumnByClass(OperationColumn.class);
    }

    @Override
    protected boolean getConfiguredAutoResizeColumns() {
      return true;
    }

    public DateColumn getDateColumn() {
      return getColumnSet().getColumnByClass(DateColumn.class);
    }

    public WsLogNrColumn getWsLogNrColumn() {
      return getColumnSet().getColumnByClass(WsLogNrColumn.class);
    }

    @Order(10.0)
    public class WsLogNrColumn extends AbstractLongColumn {

      @Override
      protected boolean getConfiguredDisplayable() {
        return false;
      }
    }

    @Order(20.0)
    public class DateColumn extends AbstractDateColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Date");
      }

      @Override
      protected boolean getConfiguredHasTime() {
        return true;
      }
    }

    @Order(30.0)
    public class ServiceColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Service");
      }
    }

    @Order(40.0)
    public class PortColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Port");
      }
    }

    @Order(50.0)
    public class OperationColumn extends AbstractStringColumn {

      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Operation");
      }
    }

    @Order(10.0)
    public class ViewWsLogMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("ViewWsLog");
      }

      @Override
      protected void execAction() throws ProcessingException {
        WsLogForm form = new WsLogForm();

        // Add the following line to set the primary key of the selected log record to the form
        form.setWsLogNr(getWsLogNrColumn().getSelectedValue());

        form.startModify();
        form.waitFor();
        if (form.isFormStored()) {
          reloadPage();
        }
      }
    }
  }
}

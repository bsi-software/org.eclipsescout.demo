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
package org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.shared.TEXTS;

public class SearchFormTablePage extends AbstractPageWithTable<SearchFormTablePage.Table> {
  private Class<? extends ISearchForm> m_searchFormClass;

  public SearchFormTablePage(Class<? extends ISearchForm> searchFormClass) {
    super();
    m_searchFormClass = searchFormClass;
    getCellForUpdate().setText(TEXTS.get(m_searchFormClass.getSimpleName().substring(0, m_searchFormClass.getSimpleName().length() - 10)));
  }

  @Override
  protected String getConfiguredIconId() {
    return org.eclipse.scout.rt.shared.AbstractIcons.SmartFieldBrowse;
  }

  @Override
  protected Class<? extends ISearchForm> getConfiguredSearchForm() {
    return m_searchFormClass;
  }

  @Order(10.0)
  public class Table extends AbstractTable {
  }
}

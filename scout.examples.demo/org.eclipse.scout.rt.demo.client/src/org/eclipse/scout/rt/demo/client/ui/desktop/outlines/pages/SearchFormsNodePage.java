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

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.demo.client.ui.searchforms.DefaultSearchForm;
import org.eclipse.scout.rt.demo.client.ui.searchforms.EastSearchForm;
import org.eclipse.scout.rt.demo.client.ui.searchforms.NorthEastSearchForm;
import org.eclipse.scout.rt.demo.client.ui.searchforms.NorthSearchForm;
import org.eclipse.scout.rt.demo.client.ui.searchforms.NorthWestSearchForm;
import org.eclipse.scout.rt.demo.client.ui.searchforms.OutlineSearchForm;
import org.eclipse.scout.rt.demo.client.ui.searchforms.OutlineSelectorSearchForm;
import org.eclipse.scout.rt.demo.client.ui.searchforms.PageDetailSearchForm;
import org.eclipse.scout.rt.demo.client.ui.searchforms.PageSearchSearchForm;
import org.eclipse.scout.rt.demo.client.ui.searchforms.PageTableSearchForm;
import org.eclipse.scout.rt.demo.client.ui.searchforms.SouthEastSearchForm;
import org.eclipse.scout.rt.demo.client.ui.searchforms.SouthSearchForm;
import org.eclipse.scout.rt.demo.client.ui.searchforms.SouthWestSearchForm;
import org.eclipse.scout.rt.demo.client.ui.searchforms.WestSearchForm;
import org.eclipse.scout.rt.shared.TEXTS;

public class SearchFormsNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredIconId() {
    return org.eclipse.scout.rt.shared.AbstractIcons.TreeNode;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("SearchForms");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    SearchFormTablePage searchFormTablePage = new SearchFormTablePage(DefaultSearchForm.class);
    pageList.add(searchFormTablePage);
    SearchFormTablePage searchFormTablePage0 = new SearchFormTablePage(OutlineSearchForm.class);
    pageList.add(searchFormTablePage0);
    SearchFormTablePage searchFormTablePage1 = new SearchFormTablePage(OutlineSelectorSearchForm.class);
    pageList.add(searchFormTablePage1);
    SearchFormTablePage searchFormTablePage2 = new SearchFormTablePage(PageTableSearchForm.class);
    pageList.add(searchFormTablePage2);
    SearchFormTablePage searchFormTablePage3 = new SearchFormTablePage(PageDetailSearchForm.class);
    pageList.add(searchFormTablePage3);
    SearchFormTablePage searchFormTablePage4 = new SearchFormTablePage(PageSearchSearchForm.class);
    pageList.add(searchFormTablePage4);
    SearchFormTablePage searchFormTablePage5 = new SearchFormTablePage(NorthSearchForm.class);
    pageList.add(searchFormTablePage5);
    SearchFormTablePage searchFormTablePage6 = new SearchFormTablePage(NorthEastSearchForm.class);
    pageList.add(searchFormTablePage6);
    SearchFormTablePage searchFormTablePage7 = new SearchFormTablePage(EastSearchForm.class);
    pageList.add(searchFormTablePage7);
    SearchFormTablePage searchFormTablePage8 = new SearchFormTablePage(SouthEastSearchForm.class);
    pageList.add(searchFormTablePage8);
    SearchFormTablePage searchFormTablePage9 = new SearchFormTablePage(SouthSearchForm.class);
    pageList.add(searchFormTablePage9);
    SearchFormTablePage searchFormTablePage10 = new SearchFormTablePage(SouthWestSearchForm.class);
    pageList.add(searchFormTablePage10);
    SearchFormTablePage searchFormTablePage11 = new SearchFormTablePage(WestSearchForm.class);
    pageList.add(searchFormTablePage11);
    SearchFormTablePage searchFormTablePage12 = new SearchFormTablePage(NorthWestSearchForm.class);
    pageList.add(searchFormTablePage12);
  }
}

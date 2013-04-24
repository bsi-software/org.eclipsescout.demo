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
package org.eclipse.scout.rt.demo.client.ui.desktop.outlines;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages.PagesNodePage;
import org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages.SearchFormsNodePage;
import org.eclipse.scout.rt.shared.TEXTS;

public class PagesSearchFormsOutline extends AbstractOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PagesSearchForms");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    PagesNodePage pagesNodePage = new PagesNodePage();
    pageList.add(pagesNodePage);
    pageList.add(new SearchFormsNodePage());

  }
}

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

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.demo.client.ui.template.menu.AbstractViewSourceOnGitHubMenu;
import org.eclipse.scout.rt.shared.TEXTS;

public class PageWithNodesNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredIconId() {
    return org.eclipse.scout.rt.shared.AbstractIcons.TreeNode;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("PageWithNodes");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    PageWithTableTablePage pageWithTableTablePage = new PageWithTableTablePage(TEXTS.get("PageWithTable") + " 1");
    pageList.add(pageWithTableTablePage);

    PageWithTableTablePage pageWithTableTablePage0 = new PageWithTableTablePage(TEXTS.get("PageWithTable") + " 2");
    pageList.add(pageWithTableTablePage0);

    PageWithTableTablePage pageWithTableTablePage1 = new PageWithTableTablePage(TEXTS.get("PageWithTable") + " 3");
    pageList.add(pageWithTableTablePage1);

    PageWithTableTablePage pageWithTableTablePage2 = new PageWithTableTablePage(TEXTS.get("PageWithTable") + " 4");
    pageList.add(pageWithTableTablePage2);

    PageWithTableTablePage pageWithTableTablePage3 = new PageWithTableTablePage(TEXTS.get("PageWithTable") + " 5");
    pageList.add(pageWithTableTablePage3);
  }

  @Order(10.0)
  public class ViewSourceOnGitHubMenu extends AbstractViewSourceOnGitHubMenu {

    @Override
    protected Class<?> provideSourceClass() {
      return PageWithNodesNodePage.class;
    }
  }
}

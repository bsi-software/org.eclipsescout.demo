package org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

public class PagesNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredIconId() {
    return org.eclipse.scout.rt.shared.AbstractIcons.TreeNode;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Pages");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    PageWithTableTablePage pageWithTableTablePage = new PageWithTableTablePage();
    pageList.add(pageWithTableTablePage);

    PageWithNodesNodePage pageWithNodesNodePage = new PageWithNodesNodePage();
    pageList.add(pageWithNodesNodePage);
  }
}

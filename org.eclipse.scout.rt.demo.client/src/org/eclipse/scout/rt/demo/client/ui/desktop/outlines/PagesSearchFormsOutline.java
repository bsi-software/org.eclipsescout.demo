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

package org.eclipse.scout.tutorial.jaxws.client.ui.desktop.outlines;

import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.shared.TEXTS;
import java.util.Collection;
import org.eclipse.scout.tutorial.jaxws.client.ui.desktop.outlines.pages.CompanyTablePage;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.tutorial.jaxws.client.ui.desktop.outlines.pages.WSLogTablePage;

public class StandardOutline extends AbstractOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("StandardOutline");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
  CompanyTablePage companyTablePage = new CompanyTablePage();
    pageList.add(companyTablePage);
pageList.add(new WSLogTablePage());
  
  }
}

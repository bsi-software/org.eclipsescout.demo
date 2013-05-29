package org.eclipsescout.demo.minicrm.client.ui.desktop.outlines.pages;

import java.util.Collection;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;

public class CompanyDetailsNodePage extends AbstractPageWithNodes {

  private Long m_companyNr;

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    PersonTablePage personTablePage = new PersonTablePage();
    personTablePage.setCompanyNr(getCompanyNr());
    pageList.add(personTablePage);
  }

  @FormData
  public Long getCompanyNr() {
    return m_companyNr;
  }

  @FormData
  public void setCompanyNr(Long companyNr) {
    m_companyNr = companyNr;
  }
}

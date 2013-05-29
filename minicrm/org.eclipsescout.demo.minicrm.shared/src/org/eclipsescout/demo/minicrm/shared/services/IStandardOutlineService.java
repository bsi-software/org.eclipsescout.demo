package org.eclipsescout.demo.minicrm.shared.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.IService2;
import org.eclipsescout.demo.minicrm.shared.ui.desktop.outlines.pages.searchform.CompanySearchFormData;
import org.eclipsescout.demo.minicrm.shared.ui.desktop.outlines.pages.searchform.PersonSearchFormData;

public interface IStandardOutlineService extends IService2 {

  public Object[][] getCompanyTableData(CompanySearchFormData formData) throws ProcessingException;

  public Object[][] getPersonTableData(PersonSearchFormData formData) throws ProcessingException;
}

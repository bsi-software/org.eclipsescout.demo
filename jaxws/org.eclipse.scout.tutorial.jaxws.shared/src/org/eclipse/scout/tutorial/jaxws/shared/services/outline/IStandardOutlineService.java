package org.eclipse.scout.tutorial.jaxws.shared.services.outline;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.IService2;

public interface IStandardOutlineService extends IService2 {

  /**
   * @return
   * @throws ProcessingException
   */
  Object[][] getCompanyTableData() throws ProcessingException;

  /**
   * @return
   * @throws ProcessingException
   */
  Object[][] getWsLogTableData() throws ProcessingException;
}

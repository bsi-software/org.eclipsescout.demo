package org.eclipse.scout.rt.demo.server.services.outline;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.demo.shared.services.outline.IStandardOutlineService;
import org.eclipse.scout.service.AbstractService;

public class StandardOutlineService extends AbstractService implements IStandardOutlineService {

  @Override
  public Object[][] getPageWithADetailformTableData() throws ProcessingException {
    return new Object[][]{
        {1, "Exxon Mobil Corporation", "XOM"},
        {2, "IBM", "IBM"},
        {3, "UBS", "UBS"},
        {4, "Coca-Cola Company", "KO"}};
  }
}

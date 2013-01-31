package org.eclipse.scout.rt.demo.shared.services.process;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.IService;

public interface IJaxWsProcessService extends IService {

  public double getCompanyLastValue(String symbol) throws ProcessingException;

  public double[] getDetailFormValues(String symbol) throws ProcessingException;
}

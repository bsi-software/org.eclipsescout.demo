package org.eclipse.scout.tutorial.jaxws.shared.services.process;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.IService;

public interface IWSLogProcessService extends IService {

  WSLogFormData load(WSLogFormData formData) throws ProcessingException;

}

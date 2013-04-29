package org.eclipsescout.demo.ibug.shared.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface IDesktopService extends IService {

  public DesktopFormData load(DesktopFormData formData) throws ProcessingException;
}

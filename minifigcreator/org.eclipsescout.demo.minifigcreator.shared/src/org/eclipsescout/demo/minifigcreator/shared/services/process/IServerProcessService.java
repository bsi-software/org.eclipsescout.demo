package org.eclipsescout.demo.minifigcreator.shared.services.process;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService2;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface IServerProcessService extends IService2 {

  ServerFormData load(ServerFormData formData) throws ProcessingException;

  ServerFormData store(ServerFormData formData) throws ProcessingException;
}

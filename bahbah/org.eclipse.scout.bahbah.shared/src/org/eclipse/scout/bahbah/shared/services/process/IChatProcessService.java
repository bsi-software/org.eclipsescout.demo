package org.eclipse.scout.bahbah.shared.services.process;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface IChatProcessService extends IService {

  ChatFormData load(ChatFormData formData) throws ProcessingException;

  ChatFormData store(ChatFormData formData) throws ProcessingException;
}

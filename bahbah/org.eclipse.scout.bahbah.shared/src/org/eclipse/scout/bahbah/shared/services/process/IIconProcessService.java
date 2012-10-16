package org.eclipse.scout.bahbah.shared.services.process;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

@InputValidation(IValidationStrategy.NO_CHECK.class)
public interface IIconProcessService extends IService {

  public byte[] loadIcon(String name) throws ProcessingException;

  public void saveIcon(String name, byte[] icon) throws ProcessingException;
}

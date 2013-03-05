package org.eclipsescout.demo.minifigcreator.shared.services.process;

import org.eclipse.scout.service.IService;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipsescout.demo.minifigcreator.shared.services.process.DesktopFormData;

public interface IDesktopProcessService extends IService{

  public DesktopFormData load(DesktopFormData formData) throws ProcessingException;
}

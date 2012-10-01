package org.eclipse.scout.bahbah.shared.services.process;

import org.eclipse.scout.service.IService;
import org.eclipse.scout.commons.exception.ProcessingException;

public interface IIconProcessService extends IService{

  public byte [] loadIcon(String name) throws ProcessingException;

  public void saveIcon(String name, byte [] icon) throws ProcessingException;
}

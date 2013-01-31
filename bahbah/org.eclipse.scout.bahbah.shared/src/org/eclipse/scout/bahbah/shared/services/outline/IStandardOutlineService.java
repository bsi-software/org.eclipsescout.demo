package org.eclipse.scout.bahbah.shared.services.outline;

import org.eclipse.scout.service.IService;
import org.eclipse.scout.commons.exception.ProcessingException;

public interface IStandardOutlineService extends IService{

  public String [] getOnlineUsers() throws ProcessingException;
}

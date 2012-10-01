package org.eclipse.scout.bahbah.shared.services.process;

import org.eclipse.scout.service.IService;
import org.eclipse.scout.commons.exception.ProcessingException;

public interface INotificationProcessService extends IService{

  public void sendRefreshBuddies() throws ProcessingException;

  public void sendMessage(String buddyName, String message) throws ProcessingException;
}

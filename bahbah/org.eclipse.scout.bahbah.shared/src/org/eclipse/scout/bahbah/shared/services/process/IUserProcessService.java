package org.eclipse.scout.bahbah.shared.services.process;

import org.eclipse.scout.service.IService;
import org.eclipse.scout.commons.exception.ProcessingException;
import java.util.Set;

public interface IUserProcessService extends IService{

  public void addUser() throws ProcessingException;

  public void removeUser() throws ProcessingException;

  public Set<String> getUsers() throws ProcessingException;
}

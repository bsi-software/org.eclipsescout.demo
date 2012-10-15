package org.eclipse.scout.bahbah.shared.services.process;

import java.util.Set;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.service.IService;

public interface IUserProcessService extends IService {

  static final String PERMISSION_KEY = "permission_id";

  public void addUser() throws ProcessingException;

  public void removeUser() throws ProcessingException;

  public Set<String> getUsers() throws ProcessingException;

  ICode<Integer> getUserPermission(String userName) throws ProcessingException;
}

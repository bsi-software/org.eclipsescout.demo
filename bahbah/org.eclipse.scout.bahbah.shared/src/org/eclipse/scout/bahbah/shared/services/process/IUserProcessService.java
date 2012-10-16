package org.eclipse.scout.bahbah.shared.services.process;

import java.util.Set;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.validate.IValidationStrategy;
import org.eclipse.scout.rt.shared.validate.InputValidation;
import org.eclipse.scout.service.IService;

@InputValidation(IValidationStrategy.PROCESS.class)
public interface IUserProcessService extends IService {

  static final int MIN_USERNAME_LENGTH = 3;

  static final String PERMISSION_KEY = "permission_id";

  public void registerUser() throws ProcessingException;

  public void unregisterUser() throws ProcessingException;

  public Set<String> getUsersOnline() throws ProcessingException;

  ICode<Integer> getUserPermission(String userName) throws ProcessingException;

  Object[][] getUsers() throws ProcessingException;

  void deleteUser(Long[] u_id) throws ProcessingException;

  void createUser(UserFormData formData) throws ProcessingException;

  void updateUser(UserFormData formData) throws ProcessingException;
}

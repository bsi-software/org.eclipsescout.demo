package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class RegisterUserPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public RegisterUserPermission() {
    super(RegisterUserPermission.class.getName());
  }
}

package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class CreateUserPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public CreateUserPermission() {
    super("CreateUser");
  }
}

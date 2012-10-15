package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class ReadUsersPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadUsersPermission() {
    super("ReadChat");
  }
}

package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class ReadUserPermission extends BasicPermission{

  private static final long serialVersionUID = 0L;

  public ReadUserPermission() {
  super("ReadUser");
  }
}

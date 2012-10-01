package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class UpdateIconPermission extends BasicPermission{

  private static final long serialVersionUID = 0L;

  public UpdateIconPermission() {
  super("UpdateIcon");
  }
}

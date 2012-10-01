package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class CreateIconPermission extends BasicPermission{

  private static final long serialVersionUID = 0L;

  public CreateIconPermission() {
  super("CreateIcon");
  }
}

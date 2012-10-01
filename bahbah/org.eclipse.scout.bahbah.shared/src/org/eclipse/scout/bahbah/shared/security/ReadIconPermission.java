package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class ReadIconPermission extends BasicPermission{

  private static final long serialVersionUID = 0L;

  public ReadIconPermission() {
  super("ReadIcon");
  }
}

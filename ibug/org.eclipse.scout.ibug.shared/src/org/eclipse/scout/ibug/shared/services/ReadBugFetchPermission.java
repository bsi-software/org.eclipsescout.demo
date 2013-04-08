package org.eclipse.scout.ibug.shared.services;

import java.security.BasicPermission;

public class ReadBugFetchPermission extends BasicPermission{

  private static final long serialVersionUID = 0L;

  public ReadBugFetchPermission() {
  super("ReadBugFetch");
  }
}

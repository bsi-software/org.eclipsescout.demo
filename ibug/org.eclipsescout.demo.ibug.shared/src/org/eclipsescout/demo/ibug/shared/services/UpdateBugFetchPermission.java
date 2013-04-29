package org.eclipsescout.demo.ibug.shared.services;

import java.security.BasicPermission;

public class UpdateBugFetchPermission extends BasicPermission{

  private static final long serialVersionUID = 0L;

  public UpdateBugFetchPermission() {
  super("UpdateBugFetch");
  }
}

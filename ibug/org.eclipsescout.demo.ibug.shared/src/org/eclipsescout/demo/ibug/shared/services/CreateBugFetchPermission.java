package org.eclipsescout.demo.ibug.shared.services;

import java.security.BasicPermission;

public class CreateBugFetchPermission extends BasicPermission{

  private static final long serialVersionUID = 0L;

  public CreateBugFetchPermission() {
  super("CreateBugFetch");
  }
}

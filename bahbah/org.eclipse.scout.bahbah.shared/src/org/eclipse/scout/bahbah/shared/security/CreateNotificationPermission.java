package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class CreateNotificationPermission extends BasicPermission{

  private static final long serialVersionUID = 0L;

  public CreateNotificationPermission() {
  super("CreateNotification");
  }
}

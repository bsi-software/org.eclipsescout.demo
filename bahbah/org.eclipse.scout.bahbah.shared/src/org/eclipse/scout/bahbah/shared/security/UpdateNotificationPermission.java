package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class UpdateNotificationPermission extends BasicPermission{

  private static final long serialVersionUID = 0L;

  public UpdateNotificationPermission() {
  super("UpdateNotification");
  }
}

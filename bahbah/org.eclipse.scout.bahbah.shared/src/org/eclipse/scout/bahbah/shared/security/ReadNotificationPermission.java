package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class ReadNotificationPermission extends BasicPermission{

  private static final long serialVersionUID = 0L;

  public ReadNotificationPermission() {
  super("ReadNotification");
  }
}

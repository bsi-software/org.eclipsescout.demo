package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class ReadChatPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadChatPermission() {
    super(ReadChatPermission.class.getName());
  }
}

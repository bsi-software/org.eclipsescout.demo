package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class CreateChatPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public CreateChatPermission() {
    super("CreateChat");
  }
}

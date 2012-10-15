package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class UpdateChatPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public UpdateChatPermission() {
    super(UpdateChatPermission.class.getName());
  }
}

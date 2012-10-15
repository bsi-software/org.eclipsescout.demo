/*******************************************************************************
 * Copyright (c) 2010 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.bahbah.server.services.custom.security;

import java.security.Permissions;

import org.eclipse.scout.bahbah.server.ServerSession;
import org.eclipse.scout.bahbah.shared.security.CreateNotificationPermission;
import org.eclipse.scout.bahbah.shared.security.CreateUserPermission;
import org.eclipse.scout.bahbah.shared.security.ReadChatPermission;
import org.eclipse.scout.bahbah.shared.security.ReadUsersPermission;
import org.eclipse.scout.bahbah.shared.security.RegisterUserPermission;
import org.eclipse.scout.bahbah.shared.security.RemoveUserPermission;
import org.eclipse.scout.bahbah.shared.security.UnregisterUserPermission;
import org.eclipse.scout.bahbah.shared.security.UpdateChatPermission;
import org.eclipse.scout.bahbah.shared.security.UpdateIconPermission;
import org.eclipse.scout.bahbah.shared.services.code.UserRoleCodeType.AdministratorCode;
import org.eclipse.scout.bahbah.shared.services.code.UserRoleCodeType.UserCode;
import org.eclipse.scout.rt.server.services.common.security.AbstractAccessControlService;
import org.eclipse.scout.rt.shared.security.RemoteServiceAccessPermission;
import org.eclipse.scout.rt.shared.services.common.code.ICode;

public class AccessControlService extends AbstractAccessControlService {

  @Override
  protected Permissions execLoadPermissions() {
    Permissions permissions = new Permissions();

    ICode<Integer> permission = ServerSession.get().getPermission();
    if (permission != null) {
      // USERS
      if (permission.getId() >= UserCode.ID) {
        permissions.add(new RemoteServiceAccessPermission("*.shared.*", "*"));

        permissions.add(new CreateNotificationPermission());
        permissions.add(new ReadChatPermission());
        permissions.add(new ReadUsersPermission());
        permissions.add(new RegisterUserPermission());
        permissions.add(new UnregisterUserPermission());
        permissions.add(new UpdateChatPermission());
        permissions.add(new UpdateIconPermission());
      }

      // ADMIN
      if (permission.getId() >= AdministratorCode.ID) {
        permissions.add(new CreateUserPermission());
        permissions.add(new RemoveUserPermission());
      }
    }
    return permissions;
  }
}

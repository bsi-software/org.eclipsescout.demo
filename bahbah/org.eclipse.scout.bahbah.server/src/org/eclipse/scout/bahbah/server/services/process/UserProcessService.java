package org.eclipse.scout.bahbah.server.services.process;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.scout.bahbah.server.ServerSession;
import org.eclipse.scout.bahbah.shared.security.ReadUsersPermission;
import org.eclipse.scout.bahbah.shared.security.RegisterUserPermission;
import org.eclipse.scout.bahbah.shared.security.UnregisterUserPermission;
import org.eclipse.scout.bahbah.shared.services.code.UserRoleCodeType;
import org.eclipse.scout.bahbah.shared.services.process.INotificationProcessService;
import org.eclipse.scout.bahbah.shared.services.process.IUserProcessService;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.holders.IntegerHolder;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.CODES;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.rt.shared.services.common.security.ACCESS;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;
import org.osgi.framework.ServiceRegistration;

public class UserProcessService extends AbstractService implements IUserProcessService {
  private Set<String> m_users;

  @Override
  public void initializeService(ServiceRegistration registration) {
    super.initializeService(registration);
    m_users = Collections.synchronizedSet(new HashSet<String>());
  }

  @Override
  public void addUser() throws ProcessingException {
    if (!ACCESS.check(new RegisterUserPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }

    m_users.add(ServerSession.get().getUserId());
    SERVICES.getService(INotificationProcessService.class).sendRefreshBuddies();
  }

  @Override
  public void removeUser() throws ProcessingException {
    if (!ACCESS.check(new UnregisterUserPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }

    m_users.remove(ServerSession.get().getUserId());
    SERVICES.getService(INotificationProcessService.class).sendRefreshBuddies();
  }

  @Override
  public Set<String> getUsers() throws ProcessingException {
    if (!ACCESS.check(new ReadUsersPermission())) {
      throw new VetoException(TEXTS.get("AuthorizationFailed"));
    }

    return m_users;
  }

  @SuppressWarnings("unchecked")
  @Override
  public ICode<Integer> getUserPermission(String userName) throws ProcessingException {
    IntegerHolder ih = new IntegerHolder(0);
    SQL.selectInto("SELECT permission_id FROM TABUSERS WHERE username = :username INTO :permission", new NVPair("username", userName), new NVPair("permission", ih));

    return CODES.getCodeType(UserRoleCodeType.class).getCode(ih.getValue());
  }
}

package org.eclipse.scout.bahbah.server.services.process;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.scout.bahbah.server.ServerSession;
import org.eclipse.scout.bahbah.shared.services.process.INotificationProcessService;
import org.eclipse.scout.bahbah.shared.services.process.IUserProcessService;
import org.eclipse.scout.commons.exception.ProcessingException;
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
    m_users.add(ServerSession.get().getUserId());
    SERVICES.getService(INotificationProcessService.class).sendRefreshBuddies();
  }

  @Override
  public void removeUser() throws ProcessingException {
    m_users.remove(ServerSession.get().getUserId());
    SERVICES.getService(INotificationProcessService.class).sendRefreshBuddies();
  }

  @Override
  public Set<String> getUsers() throws ProcessingException {
    return m_users;
  }
}

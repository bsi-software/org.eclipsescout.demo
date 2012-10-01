package org.eclipse.scout.bahbah.server.services.outline;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.scout.bahbah.server.ServerSession;
import org.eclipse.scout.bahbah.shared.services.outline.IStandardOutlineService;
import org.eclipse.scout.bahbah.shared.services.process.IUserProcessService;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

public class StandardOutlineService extends AbstractService implements IStandardOutlineService {

  @Override
  public String[] getOnlineUsers() throws ProcessingException {
    Set<String> allUsers = SERVICES.getService(IUserProcessService.class).getUsers();
    Set<String> users = new HashSet<String>(allUsers);
    // remove myself
    users.remove(ServerSession.get().getUserId());
    return users.toArray(new String[users.size()]);
  }
}

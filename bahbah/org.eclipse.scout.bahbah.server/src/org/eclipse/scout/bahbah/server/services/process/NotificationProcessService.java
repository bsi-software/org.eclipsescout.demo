package org.eclipse.scout.bahbah.server.services.process;

import org.eclipse.scout.bahbah.server.ServerSession;
import org.eclipse.scout.bahbah.shared.notification.MessageNotification;
import org.eclipse.scout.bahbah.shared.notification.RefreshBuddiesNotification;
import org.eclipse.scout.bahbah.shared.services.process.INotificationProcessService;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.services.common.clientnotification.AllUserFilter;
import org.eclipse.scout.rt.server.services.common.clientnotification.IClientNotificationService;
import org.eclipse.scout.rt.server.services.common.clientnotification.SingleUserFilter;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;

public class NotificationProcessService extends AbstractService implements INotificationProcessService {
  private static IScoutLogger logger = ScoutLogManager.getLogger(NotificationProcessService.class);

  private final static long TIMEOUT = 1000 * 60; // 1min

  @Override
  public void sendRefreshBuddies() throws ProcessingException {
    logger.info("queue 'update buddies' notification on server");
    IClientNotificationService service = SERVICES.getService(IClientNotificationService.class);
    service.putNotification(new RefreshBuddiesNotification(), new AllUserFilter(TIMEOUT));
  }

  @Override
  public void sendMessage(String buddyName, String message) throws ProcessingException {
    IClientNotificationService service = SERVICES.getService(IClientNotificationService.class);
    service.putNotification(new MessageNotification(ServerSession.get().getUserId(), message), new SingleUserFilter(buddyName, TIMEOUT));
  }
}

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
package org.eclipsescout.demo.bahbah.client;

import org.eclipsescout.demo.bahbah.client.services.IBahBahNotificationConsumerService;
import org.eclipsescout.demo.bahbah.client.ui.desktop.Desktop;
import org.eclipsescout.demo.bahbah.shared.services.process.IUserProcessService;
import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.ClientJob;
import org.eclipse.scout.rt.client.services.common.clientnotification.IClientNotificationConsumerService;
import org.eclipse.scout.rt.client.servicetunnel.http.HttpServiceTunnel;
import org.eclipse.scout.rt.shared.services.common.code.CODES;
import org.eclipse.scout.rt.shared.services.common.code.ICode;
import org.eclipse.scout.service.SERVICES;

public class ClientSession extends AbstractClientSession {
  private static IScoutLogger logger = ScoutLogManager.getLogger(ClientSession.class);

  public ClientSession() {
    super(true);
  }

  /**
   * @return session in current ThreadContext
   */
  public static ClientSession get() {
    return ClientJob.getCurrentSession(ClientSession.class);
  }

  @SuppressWarnings("unchecked")
  @FormData
  public ICode<Integer> getPermission() {
    return getSharedContextVariable(IUserProcessService.PERMISSION_KEY, ICode.class);
  }

  @Override
  public void execLoadSession() throws ProcessingException {
    setServiceTunnel(new HttpServiceTunnel(this, getBundle().getBundleContext().getProperty("server.url")));

    //pre-load all known code types
    CODES.getAllCodeTypes(org.eclipsescout.demo.bahbah.shared.Activator.PLUGIN_ID);

    // turn client notification polling on
    getServiceTunnel().setClientNotificationPollInterval(1000L);

    // set the notification listener service (this service will be called when the client receives a notification)
    IBahBahNotificationConsumerService notificationHandlerService = SERVICES.getService(IBahBahNotificationConsumerService.class);
    SERVICES.getService(IClientNotificationConsumerService.class).addClientNotificationConsumerListener(this, notificationHandlerService);

    setDesktop(new Desktop());
  }

  @Override
  public void execStoreSession() throws ProcessingException {
    // disable notification polling with -1
    ClientSession.get().getServiceTunnel().setClientNotificationPollInterval(-1);
    SERVICES.getService(IUserProcessService.class).unregisterUser();
  }
}

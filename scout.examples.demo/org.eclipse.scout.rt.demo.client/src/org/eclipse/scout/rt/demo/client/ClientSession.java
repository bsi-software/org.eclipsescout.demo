/*******************************************************************************
 * Copyright (c) 2013 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.rt.demo.client;

import java.lang.reflect.UndeclaredThrowableException;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.ClientJob;
import org.eclipse.scout.rt.client.servicetunnel.http.HttpServiceTunnel;
import org.eclipse.scout.rt.demo.client.ui.desktop.Desktop;
import org.eclipse.scout.rt.shared.services.common.code.CODES;

public class ClientSession extends AbstractClientSession {
  private boolean m_serverAvailable;
  private static IScoutLogger logger = ScoutLogManager.getLogger(ClientSession.class);

  private String m_product;

  public ClientSession() {
    super(true);
  }

  /**
   * @return session in current ThreadContext
   */
  public static ClientSession get() {
    return ClientJob.getCurrentSession(ClientSession.class);
  }

  @FormData
  public Long getPersonNr() {
    return getSharedContextVariable("personNr", Long.class);
  }

  @Override
  public void execLoadSession() throws ProcessingException {
    // Trying to connect to the Server
    try {
      setServiceTunnel(new HttpServiceTunnel(this, getBundle().getBundleContext().getProperty("server.url"), (String) getBundle().getHeaders().get("Bundle-Version")));
      CODES.getAllCodeTypes(org.eclipse.scout.rt.demo.shared.Activator.PLUGIN_ID);
      m_serverAvailable = true;
    }
    // If client can't reach the server, go offline
    catch (UndeclaredThrowableException ex) {
      goOffline();
      m_serverAvailable = false;
    }
    setDesktop(new Desktop());
  }

  @Override
  public void execStoreSession() throws ProcessingException {
  }

  public boolean isServerAvailable() {
    return m_serverAvailable;
  }
}

/*******************************************************************************
 * Copyright (c) 2001 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Daniel Wiehl (Business Systems Integration AG) - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.tutorial.jaxws.server;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.AbstractServerSession;
import org.eclipse.scout.rt.server.IServerSession;
import org.eclipse.scout.rt.server.ThreadContext;

public class ServerSession extends AbstractServerSession {
  private static IScoutLogger logger = ScoutLogManager.getLogger(ServerSession.class);

  public ServerSession() {
    super(true);
  }

  /**
   * @return session in current ThreadContext
   */
  public static IServerSession get() {
    return ThreadContext.getServerSession();
  }

  @FormData
  public Long getPersonNr() {
    return getSharedContextVariable("personNr", Long.class);
  }

  @FormData
  public void setPersonNr(Long newValue) {
    setSharedContextVariable("personNr", Long.class, newValue);
  }

  @Override
  protected void execLoadSession() throws ProcessingException {
    logger.info("created a new session for " + getUserId());
  }
}
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
package org.eclipse.scout.tutorial.jaxws.server.services.ws.handler;

import java.util.Date;
import java.util.UUID;

import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.jaxws216.annotation.ScoutTransaction;
import org.eclipse.scout.jaxws216.handler.LogHandler;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;

@ScoutTransaction
public class DatabaseLogHandler extends LogHandler {

  private static final IScoutLogger LOG = ScoutLogManager.getLogger(DatabaseLogHandler.class);

  public static final String PROP_WS_LOG_NR = UUID.randomUUID().toString();

  @Override
  protected void handleLogMessage(DirectionType directionType, String soapMessage, SOAPMessageContext context) {
    try {
      if (directionType == DirectionType.Out) {
        Long wsLogNr = SQL.getSequenceNextval("GLOBAL_SEQ");
        context.put(PROP_WS_LOG_NR, wsLogNr);
        SQL.insert("" +
            "INSERT INTO   WS_LOG " +
            "             (WS_LOG_NR, " +
            "              EVT_DATE, " +
            "              SERVICE, " +
            "              PORT, " +
            "              OPERATION, " +
            "              REQUEST) " +
            "VALUES       (:wsLogNr, " +
            "              :evtDate, " +
            "              :service, " +
            "              :port, " +
            "              :operation, " +
            "              :request)"
            , new NVPair("wsLogNr", wsLogNr)
            , new NVPair("service", StringUtility.nvl(context.get(SOAPMessageContext.WSDL_SERVICE), "?"))
            , new NVPair("port", StringUtility.nvl(context.get(SOAPMessageContext.WSDL_PORT), "?"))
            , new NVPair("operation", StringUtility.nvl(context.get(SOAPMessageContext.WSDL_OPERATION), "?"))
            , new NVPair("request", soapMessage)
            , new NVPair("evtDate", new Date())
            );

      }
      else {
        Long wsLogNr = (Long) context.get(PROP_WS_LOG_NR);

        SQL.update("" +
            "UPDATE   WS_LOG " +
            "SET      SERVICE = :service, " +
            "         PORT = :port, " +
            "         OPERATION = :operation, " +
            "         RESPONSE = :response " +
            "WHERE    WS_LOG_NR = :wsLogNr"
            , new NVPair("wsLogNr", wsLogNr)
            , new NVPair("service", StringUtility.nvl(context.get(SOAPMessageContext.WSDL_SERVICE), "?"))
            , new NVPair("port", StringUtility.nvl(context.get(SOAPMessageContext.WSDL_PORT), "?"))
            , new NVPair("operation", StringUtility.nvl(context.get(SOAPMessageContext.WSDL_OPERATION), "?"))
            , new NVPair("response", soapMessage)
            );
      }
    }
    catch (ProcessingException e) {
      LOG.error("failed to persist webservice-log", e);
    }
  }
}

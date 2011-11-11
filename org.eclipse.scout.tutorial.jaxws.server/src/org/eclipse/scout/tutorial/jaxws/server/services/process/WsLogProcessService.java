/*******************************************************************************
 * Copyright (c) 2011 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Daniel Wiehl (Business Systems Integration AG) - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.tutorial.jaxws.server.services.process;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.tutorial.jaxws.shared.services.process.IWsLogProcessService;
import org.eclipse.scout.tutorial.jaxws.shared.services.process.WsLogFormData;

public class WsLogProcessService extends AbstractService implements IWsLogProcessService {

  @Override
  public WsLogFormData load(WsLogFormData formData) throws ProcessingException {
    SQL.selectInto("" +
        "SELECT EVT_DATE, " +
        "       SERVICE, " +
        "       PORT, " +
        "       OPERATION, " +
        "       REQUEST, " +
        "       RESPONSE " +
        "FROM   WS_LOG " +
        "WHERE  WS_LOG_NR = :wsLogNr " +
        "INTO   :date, " +
        "       :service, " +
        "       :port, " +
        "       :operation, " +
        "       :request, " +
        "       :response"
        , formData);
    return formData;
  }
}

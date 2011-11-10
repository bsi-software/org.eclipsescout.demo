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
package org.eclipse.scout.tutorial.jaxws.server.services.outline;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.tutorial.jaxws.shared.services.outline.IMainOutlineService;

public class MainOutlineService extends AbstractService implements IMainOutlineService {

  @Override
  public Object[][] getCompanyTableData() throws ProcessingException {
    return SQL.select("" +
        "SELECT   COMPANY_NR, " +
        "         NAME, " +
        "         SYMBOL " +
        "FROM     COMPANY ");
  }

  @Override
  public Object[][] getWsLogTableData() throws ProcessingException {
    return SQL.select("" +
        "SELECT   WS_LOG_NR, " +
        "         EVT_DATE, " +
        "         SERVICE, " +
        "         PORT, " +
        "         OPERATION " +
        "FROM     WS_LOG ");
  }
}

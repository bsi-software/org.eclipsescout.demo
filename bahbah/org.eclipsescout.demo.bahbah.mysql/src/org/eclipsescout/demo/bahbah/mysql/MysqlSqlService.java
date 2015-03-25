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
package org.eclipsescout.demo.bahbah.mysql;

import org.eclipse.scout.commons.annotations.Priority;
import org.eclipse.scout.rt.server.Server;
import org.eclipsescout.demo.bahbah.server.ServerSession;

import com.bsiag.scout.rt.server.jdbc.AbstractMySqlSqlService;

/**
 *
 */
@Priority(100)
@Server(ServerSession.class) /* XXX mvi check requirement of session class */
public class MysqlSqlService extends AbstractMySqlSqlService {

  @Override
  protected String getConfiguredJdbcMappingName() {
    return "jdbc:mysql://localhost:3306/bahbah";
  }

  @Override
  protected String getConfiguredUsername() {
    return "root";
  }

  @Override
  protected String getConfiguredPassword() {
    return "";
  }

}

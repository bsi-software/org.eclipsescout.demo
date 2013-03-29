package org.eclipsescout.demo.minifigcreator.server.services.common.sql;

import org.eclipse.scout.rt.server.services.common.jdbc.AbstractSqlService;
import org.eclipse.scout.rt.server.services.common.jdbc.style.ISqlStyle;
import org.eclipse.scout.service.IService2;

import com.bsiag.scout.rt.server.jdbc.style.PostgresSqlStyle;

public class PGSqlService extends AbstractSqlService implements IService2 {

  @Override
  protected Class<? extends ISqlStyle> getConfiguredSqlStyle() {
    return PostgresSqlStyle.class;
  }
}

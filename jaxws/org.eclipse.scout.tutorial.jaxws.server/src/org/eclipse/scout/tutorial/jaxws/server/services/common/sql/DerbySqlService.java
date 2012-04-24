package org.eclipse.scout.tutorial.jaxws.server.services.common.sql;

import org.eclipse.scout.rt.services.common.jdbc.AbstractDerbySqlService;
import org.eclipse.scout.service.IService2;

public class DerbySqlService extends AbstractDerbySqlService implements IService2{

  @Override
  protected String getConfiguredJdbcMappingName() {  return "jdbc:derby:C:\\eclipse\\scout\\tutorial\\org.eclipse.scout.tutorial.jaxws.database";}

  @Override
  protected String getConfiguredPassword() {
    return "scout";
  }

  @Override
  protected String getConfiguredUsername() {
    return "tutorial";
  }
}

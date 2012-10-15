package org.eclipse.scout.bahbah.server;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.scout.bahbah.server.services.custom.security.BahBahAuthenticationUtility;
import org.eclipse.scout.bahbah.shared.services.code.UserRoleCodeType;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;

/**
 * class that installs the bahbah DB schema
 */
public class DbSetup {
  public static void installDb() throws ProcessingException {
    Set<String> existingTables = getExistingTables();

    if (!existingTables.contains("TABUSERS")) {
      SQL.insert(" CREATE TABLE TABUSERS (" +
          " u_id BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT USERS_PK PRIMARY KEY, " +
          " username VARCHAR(32) NOT NULL, " +
          " pass VARCHAR(256) NOT NULL, " +
          " salt VARCHAR(64) NOT NULL, " +
          " permission_id INT NOT NULL, " +
          " icon BLOB " +
          ")");

      SQL.insert(" CREATE UNIQUE INDEX IX_USERNAME ON TABUSERS (username) ");

      // create first admin account
      BahBahAuthenticationUtility.createNewUser("admin", "admin", UserRoleCodeType.AdministratorCode.ID);
    }

    SQL.commit();
  }

  private static Set<String> getExistingTables() throws ProcessingException {
    Object[][] existingTables = SQL.select("SELECT tablename FROM sys.systables");
    HashSet<String> result = new HashSet<String>(existingTables.length);
    for (Object[] row : existingTables) {
      result.add(row[0] + "");
    }
    return result;
  }
}

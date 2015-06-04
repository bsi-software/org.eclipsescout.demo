/**
 *
 */
package org.eclipsescout.contacts.server.premium.services;

import java.util.Set;
import java.util.UUID;

import org.eclipse.scout.commons.DateUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipsescout.contacts.server.services.DBInstallService;
import org.eclipsescout.contacts.shared.services.IDBInstallService;

/**
 * @author mzi
 */
public class PremiumDBInstallService extends DBInstallService implements IDBInstallService {
  private static final IScoutLogger LOG = ScoutLogManager.getLogger(PremiumDBInstallService.class);

  @Override
  public void installStorage() throws ProcessingException {
    if (doSetup()) {
      Set<String> tables = getExistingTables();
      createEventTable(tables, addInitialData());
      createParticipantTable(tables, addInitialData());
    }

    LOG.info("DB install completed");
  }

  private void createEventTable(Set<String> tables, boolean addInitialData) throws ProcessingException {
    if (!tables.contains("EVENT")) {
      SQL.insert(TEXTS.get("SqlEventCreateTable"));

      if (addInitialData) {
        SQL.insert(TEXTS.get("SqlEventInsertExample1"), new NVPair("eventId", UUID.randomUUID().toString()), new NVPair("starts", DateUtility.parse("09.03.2015 09:00", "dd.MM.yyyy HH:mm")), new NVPair("ends", DateUtility.parse("12.03.2015 16:45", "dd.MM.yyyy HH:mm")));
        SQL.insert(TEXTS.get("SqlEventInsertExample2"), new NVPair("eventId", UUID.randomUUID().toString()), new NVPair("starts", DateUtility.parse("24.03.2015 09:00", "dd.MM.yyyy HH:mm")), new NVPair("ends", DateUtility.parse("26.03.2015 17:00", "dd.MM.yyyy HH:mm")));
        SQL.insert(TEXTS.get("SqlEventInsertExample3"), new NVPair("eventId", UUID.randomUUID().toString()), new NVPair("starts", DateUtility.parse("23.06.2015 17:30", "dd.MM.yyyy HH:mm")), new NVPair("ends", DateUtility.parse("23.06.2015 23:00", "dd.MM.yyyy HH:mm")));
      }
    }
  }

  private void createParticipantTable(Set<String> tables, boolean addInitialData) throws ProcessingException {
    if (!tables.contains("PARTICIPANT")) {
      SQL.insert(TEXTS.get("SqlParticipantCreateTable"));

      if (addInitialData) {
        SQL.insert(TEXTS.get("SqlParticipantInsertExample1"));
        SQL.insert(TEXTS.get("SqlParticipantInsertExample2"));
      }
    }
  }
}

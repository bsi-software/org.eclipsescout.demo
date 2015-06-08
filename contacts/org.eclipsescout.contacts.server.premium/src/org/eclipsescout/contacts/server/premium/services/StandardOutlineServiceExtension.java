/**
 *
 */
package org.eclipsescout.contacts.server.premium.services;

import java.util.HashMap;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.holders.BeanArrayHolder;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipsescout.contacts.server.services.StandardOutlineService;
import org.eclipsescout.contacts.shared.premium.ui.desktop.outlines.ContactsTableDataExtension;
import org.eclipsescout.contacts.shared.ui.desktop.outlines.ContactsTablePageData;
import org.eclipsescout.contacts.shared.ui.desktop.outlines.ContactsTablePageData.ContactsTableRowData;

/**
 * @author mzi
 */
public class StandardOutlineServiceExtension extends StandardOutlineService {
  private static final IScoutLogger LOG = ScoutLogManager.getLogger(StandardOutlineServiceExtension.class);

  @Override
  public ContactsTablePageData getContactsTableData(SearchFilter filter, String pageCompanyId) throws ProcessingException {
    ContactsTablePageData pageData = super.getContactsTableData(filter, pageCompanyId);

    // more information: https://www.eclipse.org/forums/index.php/t/310526/
    BeanArrayHolder<EventCounterBean> arrayHolder = new BeanArrayHolder<EventCounterBean>(EventCounterBean.class);
    SQL.selectInto("SELECT contact_id, count(event_id) FROM PARTICIPANT GROUP BY contact_id INTO :{h.contactId}, :{h.events}", new NVPair("h", arrayHolder));

    // create map from bean array
    HashMap<String, Long> eventCounts = new HashMap<>();
    for (EventCounterBean counter : arrayHolder.getBeans()) {
      eventCounts.put(counter.getContactId(), counter.getEvents());
    }

    // pub event counts into target rows
    for (ContactsTableRowData row : pageData.getRows()) {
      String contactId = row.getContactId();

      if (eventCounts.containsKey(contactId)) {
        row.getContribution(ContactsTableDataExtension.class).setEvents(eventCounts.get(contactId));
      }
      else {
        row.getContribution(ContactsTableDataExtension.class).setEvents(Long.valueOf(0));
      }
    }

    return pageData;
  }
}

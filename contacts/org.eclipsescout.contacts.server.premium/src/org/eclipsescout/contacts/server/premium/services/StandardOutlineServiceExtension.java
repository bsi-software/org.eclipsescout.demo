/**
 *
 */
package org.eclipsescout.contacts.server.premium.services;

import java.util.HashMap;
import java.util.Random;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.holders.BeanArrayHolder;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
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

  class EventCounter {
    private String m_contactId;
    private Integer m_events;

    Integer getEvents() {
      return m_events;
    }

    void setEvents(Integer events) {
      m_events = events;
    }

    String getContactId() {
      return m_contactId;
    }

    void setContactId(String contactId) {
      m_contactId = contactId;
    }
  }

  @Override
  public ContactsTablePageData getContactsTableData(SearchFilter filter, String pageCompanyId) throws ProcessingException {
    ContactsTablePageData pageData = super.getContactsTableData(filter, pageCompanyId);

    // TODO: fix this / check if there is a different/better way to do this
    // doesn't this exactly match the documentation of IBeanArrayHolder???
    BeanArrayHolder<EventCounter> result = new BeanArrayHolder<>(EventCounter.class);
//    SQL.selectInto("SELECT contact_id, count(event_id) FROM PARTICIPANT GROUP BY contact_id " + "INTO :contactId, :events", result);

    // create map from bean array
    HashMap<String, Integer> eventCounts = new HashMap<>();
    for (EventCounter counter : result.getBeans()) {
      eventCounts.put(counter.getContactId(), counter.getEvents());
    }

    // pub event counts into target rows
    for (ContactsTableRowData row : pageData.getRows()) {
      String contactId = row.getContactId();

      if (eventCounts.containsKey(contactId)) {
        row.getContribution(ContactsTableDataExtension.class).setEvents(eventCounts.get(contactId));
      }
      else {
        row.getContribution(ContactsTableDataExtension.class).setEvents(Integer.valueOf(0));
      }

      Random random = new Random();
      row.getContribution(ContactsTableDataExtension.class).setEvents(Integer.valueOf(random.nextInt(5)));
    }

    return pageData;
  }
}

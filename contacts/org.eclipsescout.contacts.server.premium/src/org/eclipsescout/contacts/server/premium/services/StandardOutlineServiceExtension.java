/**
 *
 */
package org.eclipsescout.contacts.server.premium.services;

import java.util.HashMap;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.holders.BeanArrayHolder;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipsescout.contacts.server.services.StandardOutlineService;
import org.eclipsescout.contacts.shared.premium.ui.desktop.outlines.ContactsTableDataExtension;
import org.eclipsescout.contacts.shared.ui.desktop.outlines.ContactsTablePageData;
import org.eclipsescout.contacts.shared.ui.desktop.outlines.ContactsTablePageData.ContactsTableRowData;

/**
 * @author mzi
 */
public class StandardOutlineServiceExtension extends StandardOutlineService {

  @Override
  public ContactsTablePageData getContactsTableData(SearchFilter filter, String pageCompanyId) throws ProcessingException {
    ContactsTablePageData pageData = super.getContactsTableData(filter, pageCompanyId);
    addEventCounts(pageData);

    return pageData;
  }

  private void addEventCounts(ContactsTablePageData pageData) throws ProcessingException {
    // background info: https://www.eclipse.org/forums/index.php/t/310526/
    BeanArrayHolder<EventCounterBean> arrayHolder = new BeanArrayHolder<EventCounterBean>(EventCounterBean.class);
    SQL.selectInto(TEXTS.get("SqlContactPageEventCounts"), new NVPair("h", arrayHolder));

    // create map: contactId -> events
    HashMap<String, Long> eventCounts = new HashMap<>();
    for (EventCounterBean counter : arrayHolder.getBeans()) {
      eventCounts.put(counter.getContactId(), counter.getEvents());
    }

    // copy event counts into target rows
    for (ContactsTableRowData row : pageData.getRows()) {
      String contactId = row.getContactId();

      if (eventCounts.containsKey(contactId)) {
        row.getContribution(ContactsTableDataExtension.class).setEvents(eventCounts.get(contactId));
      }
      else {
        row.getContribution(ContactsTableDataExtension.class).setEvents(Long.valueOf(0));
      }
    }
  }
}

/**
 *
 */
package org.eclipsescout.contacts.server.premium.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipsescout.contacts.server.services.ContactService;
import org.eclipsescout.contacts.shared.ContactFormData;
import org.eclipsescout.contacts.shared.premium.ui.forms.ContactFormTabExtensionData;
import org.eclipsescout.contacts.shared.services.IContactService;

/**
 * @author mzi
 */
public class ContactExtensionService extends ContactService implements IContactService {

  public ContactFormTabExtensionData getExtensionData(ContactFormData formData) {
    return formData.getContribution(ContactFormTabExtensionData.class);
  }

  @Override
  public ContactFormData load(ContactFormData formData) throws ProcessingException {
    formData = super.load(formData);

    SQL.selectInto(TEXTS.get("SqlContactEventsSelect"), getExtensionData(formData), formData);

    return formData;
  }

}

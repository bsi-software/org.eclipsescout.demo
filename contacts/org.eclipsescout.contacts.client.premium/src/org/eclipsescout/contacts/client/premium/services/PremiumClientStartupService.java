/**
 *
 */
package org.eclipsescout.contacts.client.premium.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.extension.IExtensionRegistry;
import org.eclipse.scout.service.AbstractService;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.contacts.client.premium.ui.desktop.NewMenuExtension;
import org.eclipsescout.contacts.client.premium.ui.desktop.outlines.ContactsTableExtension;
import org.eclipsescout.contacts.client.premium.ui.forms.ContactFormTabExtension;
import org.eclipsescout.contacts.client.services.IClientStartupService;
import org.eclipsescout.contacts.shared.premium.ui.desktop.outlines.ContactsTableDataExtension;
import org.eclipsescout.contacts.shared.premium.ui.forms.ContactFormTabExtensionData;

/**
 * @author mzi
 */
public class PremiumClientStartupService extends AbstractService implements IClientStartupService {

  @Override
  public void init() throws ProcessingException {

    // register dto core extensions
    SERVICES.getService(IExtensionRegistry.class).register(ContactFormTabExtension.class);
    SERVICES.getService(IExtensionRegistry.class).register(ContactFormTabExtensionData.class);

    SERVICES.getService(IExtensionRegistry.class).register(ContactsTableExtension.class);
    SERVICES.getService(IExtensionRegistry.class).register(ContactsTableDataExtension.class);

    SERVICES.getService(IExtensionRegistry.class).register(NewMenuExtension.class);
  }

}

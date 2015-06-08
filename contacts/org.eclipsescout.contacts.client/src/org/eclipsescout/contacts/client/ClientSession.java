package org.eclipsescout.contacts.client;

import org.eclipse.scout.commons.UriUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.ClientJob;
import org.eclipse.scout.rt.client.servicetunnel.http.ClientHttpServiceTunnel;
import org.eclipse.scout.rt.shared.services.common.code.CODES;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.contacts.client.services.IClientStartupService;
import org.eclipsescout.contacts.client.ui.desktop.Desktop;

public class ClientSession extends AbstractClientSession {

  public ClientSession() {
    super(true);
  }

  /**
   * @return session in current ThreadContext
   */
  public static ClientSession get() {
    return ClientJob.getCurrentSession(ClientSession.class);
  }

  @Override
  public void execLoadSession() throws ProcessingException {
    setServiceTunnel(new ClientHttpServiceTunnel(this, UriUtility.toUrl(getBundle().getBundleContext().getProperty("server.url"))));

    // pre-load all known code types
    CODES.getAllCodeTypes(org.eclipsescout.contacts.shared.Activator.PLUGIN_ID);

    // call all startup services to get all potential model extensions
    for (IClientStartupService service : SERVICES.getServices(IClientStartupService.class)) {
      service.init();
    }

    setDesktop(new Desktop());
  }

  @Override
  public void execStoreSession() throws ProcessingException {
  }
}

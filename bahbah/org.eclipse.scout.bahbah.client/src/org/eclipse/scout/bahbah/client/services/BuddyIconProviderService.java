package org.eclipse.scout.bahbah.client.services;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.scout.bahbah.client.ClientSession;
import org.eclipse.scout.bahbah.shared.services.process.IIconProcessService;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ClientJob;
import org.eclipse.scout.rt.client.services.common.icon.IconProviderService;
import org.eclipse.scout.rt.client.services.common.icon.IconSpec;
import org.eclipse.scout.service.SERVICES;
import org.osgi.framework.ServiceRegistration;

/**
 * provider of buddy icons, extends IconProviderService from scout runtime client classes
 */
public class BuddyIconProviderService extends IconProviderService implements IBuddyIconProviderService {
  private static IScoutLogger logger = ScoutLogManager.getLogger(BuddyIconProviderService.class);
  private ClientSession m_session;

  @Override
  public void initializeService(ServiceRegistration registration) {
    super.initializeService(registration);

    // remember the client session because the icon lookup (getIconSpec()) is invoked from the UI thread where no session is present.
    m_session = ClientSession.get();
  }

  @Override
  public IconSpec getIconSpec(String iconName) {
    P_LoadDbIconJob job = new P_LoadDbIconJob(m_session, iconName);
    job.schedule();
    try {
      job.join();
    }
    catch (InterruptedException e1) {
    }

    if (job.getIconSpec() != null) {
      // it is a buddy icon
      if (job.getIconSpec().getContent() == null) {
        // but the user has no icon uploaded yet
        return super.getIconSpec(BUDDY_DEFAULT_ICON);
      }
      else {
        // return the icon from the database
        return job.getIconSpec();
      }
    }
    else {
      return super.getIconSpec(iconName);
    }
  }

  private static class P_LoadDbIconJob extends ClientJob {
    private IconSpec m_result;
    private final String m_iconName;

    public P_LoadDbIconJob(ClientSession session, String iconName) {
      super("get buddy icon image", session, false, true);
      m_iconName = iconName;
    }

    @Override
    protected void runVoid(IProgressMonitor monitor) throws Throwable {
      try {
        byte[] data = SERVICES.getService(IIconProcessService.class).loadIcon(m_iconName);
        m_result = new IconSpec(m_iconName, data);
      }
      catch (ProcessingException e) {
        logger.error("unable to get buddy icon '" + m_iconName + "' from the database", e);
      }
    }

    public IconSpec getIconSpec() {
      return m_result;
    }
  }
}

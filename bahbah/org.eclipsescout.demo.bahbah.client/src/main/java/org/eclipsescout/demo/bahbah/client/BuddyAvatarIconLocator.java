/*******************************************************************************
 * Copyright (c) 2015 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipsescout.demo.bahbah.client;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.scout.commons.Assertions;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ClientJob;
import org.eclipse.scout.rt.client.services.common.icon.IconSpec;
import org.eclipse.scout.rt.client.ui.IIconLocator;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.demo.bahbah.shared.services.process.IIconProcessService;

/**
 * Icon locator wrapper that loads buddy icons from a session-scoped service.
 */
public class BuddyAvatarIconLocator implements IIconLocator {

  private static final IScoutLogger logger = ScoutLogManager.getLogger(BuddyAvatarIconLocator.class);

  /**
   * the default buddy icon used when the user has not uploaded an icon yet. icon must be located in client plugin
   * under resources/icons
   */
  public static final String BUDDY_DEFAULT_ICON = "default_buddy_icon";
  public static final String BUDDY_ICON_PREFIX = "@@BUDDY_ICON@@_";
  public static final String OPT_BUDDY_ICON_SUFFIX = "_open";

  private final IIconLocator m_delegate;
  private final ClientSession m_session;

  public BuddyAvatarIconLocator(ClientSession clientSession, IIconLocator delegate) {
    Assertions.assertNotNull(delegate);
    Assertions.assertNotNull(clientSession);
    m_session = clientSession;
    m_delegate = delegate;
  }

  @Override
  public IconSpec getIconSpec(String iconName) {
    if (iconName.startsWith(BUDDY_ICON_PREFIX)) {
      return getBuddyAvatarIconSpec(iconName);
    }
    return m_delegate.getIconSpec(iconName);
  }

  protected IconSpec getBuddyAvatarIconSpec(String iconName) {
    // it is a buddy icon
    if (iconName.endsWith(OPT_BUDDY_ICON_SUFFIX)) {
      // special case for tables: they may add a suffix for open tree nodes -> remove as we only have one icon for expanded & not expanded folders
      iconName = iconName.substring(0, iconName.length() - OPT_BUDDY_ICON_SUFFIX.length());
    }

    P_LoadDbIconJob job = new P_LoadDbIconJob(m_session, iconName.substring(BUDDY_ICON_PREFIX.length()));
    job.runNow(null);
    if (job.getIconSpec().getContent() == null) {
      // but the user has no icon uploaded yet
      return m_delegate.getIconSpec(BUDDY_DEFAULT_ICON);
    }
    else {
      // return the icon from the database
      return job.getIconSpec();
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

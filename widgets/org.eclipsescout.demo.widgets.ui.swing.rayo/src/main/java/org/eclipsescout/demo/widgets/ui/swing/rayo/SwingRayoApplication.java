/*******************************************************************************
 * Copyright (c) 2013 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipsescout.demo.widgets.ui.swing.rayo;

import javax.security.auth.Subject;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.commons.security.SimplePrincipal;
import org.eclipse.scout.rt.client.IClientSession;
import org.eclipse.scout.rt.client.context.ClientRunContexts;
import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.platform.OBJ;
import org.eclipse.scout.rt.platform.PlatformException;
import org.eclipse.scout.rt.shared.ScoutTexts;
import org.eclipse.scout.rt.ui.swing.AbstractSwingApplication;
import org.eclipse.scout.rt.ui.swing.ISwingEnvironment;
import org.eclipse.scout.rt.ui.swing.SwingUtility;

@Priority(10)
public class SwingRayoApplication extends AbstractSwingApplication {

  private static final IScoutLogger LOG = ScoutLogManager.getLogger(SwingRayoApplication.class);

  private final Subject m_subject;
  private volatile IClientSession m_clientSession;

  public SwingRayoApplication() {
    m_subject = new Subject();
    m_subject.getPrincipals().add(new SimplePrincipal(StringUtility.nvl(System.getProperty("user.name"), "anonymous")));
    m_subject.setReadOnly();
  }

  @Override
  protected void startInSubject() throws Exception {
    LOG.info("Starting widget application...");
    super.startInSubject();
  }

  @Override
  protected IClientSession getClientSession() {
    if (m_clientSession == null) {
      synchronized (this) {
        if (m_clientSession == null) {
          m_clientSession = createClientSession();
        }
      }
    }
    return m_clientSession;
  }

  @Override
  protected ISwingEnvironment createSwingEnvironment() {
    SwingUtility.setNlsTexts(ScoutTexts.getInstance());
    return new SwingEnvironment();
  }

  private IClientSession createClientSession() {
    try {
      return OBJ.get(ClientSessionProvider.class).provide(ClientRunContexts.empty().subject(m_subject).userAgent(initUserAgent()));
    }
    catch (ProcessingException e) {
      throw new PlatformException("Failed to create ClientSession", e);
    }
  }
}

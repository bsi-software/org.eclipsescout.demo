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
package org.eclipsescout.demo.widgets.ui.swing;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.IClientSession;
import org.eclipse.scout.rt.client.job.ClientJobInput;
import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.platform.cdi.OBJ;
import org.eclipse.scout.rt.shared.ScoutTexts;
import org.eclipse.scout.rt.ui.swing.AbstractSwingApplication;
import org.eclipse.scout.rt.ui.swing.ISwingEnvironment;
import org.eclipse.scout.rt.ui.swing.SwingUtility;
import org.eclipsescout.demo.widgets.ui.swing.defaultlaf.SwingEnvironment;

/**
 * Runs the SwingApplication with the highest priority only
 */
public class WidgetSwingApplication extends AbstractSwingApplication {

  private static final IScoutLogger LOG = ScoutLogManager.getLogger(WidgetSwingApplication.class);

  @Override
  protected Object startInSubject(IApplicationContext context) throws Exception {
    LOG.info("Starting {0} {1} application...", Platform.getProduct().getName(), Platform.getProduct().getDefiningBundle().getVersion().toString());
    return super.startInSubject(context);
  }

  @Override
  protected IClientSession getClientSession() {
    try {
      return OBJ.one(ClientSessionProvider.class).provide(ClientJobInput.empty().userAgent(initUserAgent()));
    }
    catch (ProcessingException e) {
      LOG.error("Unable to load client session", e);
      return null;
    }
  }

  @Override
  protected ISwingEnvironment createSwingEnvironment() {
    SwingUtility.setNlsTexts(ScoutTexts.getInstance());
    return new SwingEnvironment();
  }
}

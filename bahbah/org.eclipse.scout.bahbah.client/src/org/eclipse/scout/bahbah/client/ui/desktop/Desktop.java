/*******************************************************************************
 * Copyright (c) 2010 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.bahbah.client.ui.desktop;

import java.util.ArrayList;

import org.eclipse.scout.bahbah.client.ClientSession;
import org.eclipse.scout.bahbah.client.ui.desktop.outlines.StandardOutline;
import org.eclipse.scout.bahbah.client.ui.desktop.outlines.pages.UserNodePage;
import org.eclipse.scout.bahbah.shared.Icons;
import org.eclipse.scout.bahbah.shared.services.process.IUserProcessService;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ClientSyncJob;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.ScoutInfoForm;
import org.eclipse.scout.rt.client.ui.form.outline.DefaultOutlineTableForm;
import org.eclipse.scout.rt.client.ui.form.outline.DefaultOutlineTreeForm;
import org.eclipse.scout.rt.extension.client.ui.desktop.AbstractExtensibleDesktop;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.service.SERVICES;

public class Desktop extends AbstractExtensibleDesktop implements IDesktop {
  private static IScoutLogger logger = ScoutLogManager.getLogger(Desktop.class);

  public Desktop() {
  }

  @SuppressWarnings("unchecked")
  @Override
  protected Class<? extends IOutline>[] getConfiguredOutlines() {
    ArrayList<Class> outlines = new ArrayList<Class>();
    outlines.add(StandardOutline.class);
    return outlines.toArray(new Class[outlines.size()]);
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("BahBah");
  }

  @Override
  protected void execOpened() throws ProcessingException {
    // outline tree
    DefaultOutlineTreeForm treeForm = new DefaultOutlineTreeForm();
    treeForm.setIconId(Icons.EclipseScout);
    treeForm.startView();

    //outline table
    DefaultOutlineTableForm tableForm = new DefaultOutlineTableForm();
    tableForm.setIconId(Icons.EclipseScout);
    tableForm.startView();

    if (getAvailableOutlines().length > 0) {
      setOutline(getAvailableOutlines()[0]);
    }

  }

  public static Desktop get() {
    return (Desktop) ClientSyncJob.getCurrentSession().getDesktop();
  }

  public UserNodePage getUserNodePage() {
    IPage invisibleRootPage = getOutline().getRootPage();
    if (invisibleRootPage != null && invisibleRootPage.getChildNodeCount() > 0) {
      IPage p = invisibleRootPage.getChildPage(0);
      if (p instanceof UserNodePage) {
        return (UserNodePage) p;
      }
    }
    return null;
  }

  @Order(10.0)
  public class AboutMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("AboutMenu");
    }

    @Override
    public void execAction() throws ProcessingException {
      ScoutInfoForm form = new ScoutInfoForm();
      form.startModify();
    }
  }

  @Order(20.0)
  public class LogoutMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Logout");
    }

    @Override
    protected void execAction() throws ProcessingException {
      // disable notification polling with -1
      ClientSession.get().getServiceTunnel().setClientNotificationPollInterval(-1);
      SERVICES.getService(IUserProcessService.class).removeUser();
      ClientSession.get().stopSession();
    }
  }

  @Order(10.0)
  public class StandardOutlineViewButton extends AbstractOutlineViewButton {
    public StandardOutlineViewButton() {
      super(Desktop.this, StandardOutline.class);
    }
  }
}

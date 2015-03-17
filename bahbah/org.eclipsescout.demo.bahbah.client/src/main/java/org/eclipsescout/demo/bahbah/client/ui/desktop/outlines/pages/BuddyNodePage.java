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
package org.eclipsescout.demo.bahbah.client.ui.desktop.outlines.pages;

import java.util.concurrent.TimeUnit;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.job.IRunnable;
import org.eclipse.scout.rt.client.job.ClientJobInput;
import org.eclipse.scout.rt.client.job.IModelJobManager;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.platform.cdi.OBJ;
import org.eclipsescout.demo.bahbah.client.ClientSession;
import org.eclipsescout.demo.bahbah.client.services.BuddyAvatarIconProviderService;
import org.eclipsescout.demo.bahbah.client.ui.forms.ChatForm;

public class BuddyNodePage extends AbstractPageWithNodes {

  private String m_name;
  private ChatForm m_form;

  @Override
  protected boolean getConfiguredExpanded() {
    return true;
  }

  @Override
  protected String getConfiguredIconId() {
    return BuddyAvatarIconProviderService.BUDDY_ICON_PREFIX + getName();
  }

  @Override
  protected boolean getConfiguredLeaf() {
    return true;
  }

  @Override
  protected boolean getConfiguredTableVisible() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return getName();
  }

  public void setDefaultFocus() {
    OBJ.get(IModelJobManager.class).schedule(new IRunnable() {
      @Override
      public void run() throws Exception {
        getChatForm().getMessageField().requestFocus();
      }
    }, 200, TimeUnit.MILLISECONDS, ClientJobInput.defaults().name("set focus to message field"));
  }

  public ChatForm getChatForm() throws ProcessingException {
    if (m_form == null) {
      m_form = new ChatForm();
      m_form.setAutoAddRemoveOnDesktop(false);
      m_form.setUserName(ClientSession.get().getUserId());
      m_form.setBuddyName(getName());
      m_form.startNew();
    }
    return m_form;
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    super.execPageActivated();

    // after buddy page activation the buddy's chat history is displayed on the right side
    ChatForm chatForm = getChatForm();
    setDetailForm(chatForm);
    setDefaultFocus();
  }

  @FormData
  public String getName() {
    return m_name;
  }

  @FormData
  public void setName(String name) {
    m_name = name;
  }
}

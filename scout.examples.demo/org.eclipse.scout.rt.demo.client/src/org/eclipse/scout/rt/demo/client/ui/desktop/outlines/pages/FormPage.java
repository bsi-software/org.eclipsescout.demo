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
package org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.services.common.shell.DefaultShellService;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.demo.client.ClientSession;
import org.eclipse.scout.rt.demo.client.ui.forms.IPageForm;
import org.eclipse.scout.rt.shared.TEXTS;

public class FormPage extends AbstractPageWithNodes {

  private Class<? extends IPageForm> m_formType;
  private boolean m_enabled = true;

  public FormPage(Class<? extends IPageForm> formType) {
    super(false, formType.getName());
    m_formType = formType;
    callInitializer();
  }

  public FormPage(Class<? extends IPageForm> c, boolean enabled) {
    super(false, c.getName());
    m_formType = c;
    m_enabled = enabled;
    callInitializer();
  }

  @Override
  protected boolean getConfiguredEnabled() {
    return m_enabled;
  }

  @Override
  protected String getConfiguredIconId() {
    return org.eclipse.scout.rt.shared.AbstractIcons.TreeNodeOpen;
  }

  @Override
  protected boolean getConfiguredLeaf() {
    return true;
  }

  @Override
  protected void execInitPage() throws ProcessingException {
    String s = m_formType.getSimpleName();
    s = s.substring(0, s.length() - 4);
    getCellForUpdate().setText(s);
    setTableVisible(false);
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    if (getDetailForm() == null && m_enabled) {
      IPageForm form = execCreateDetailForm();
      setDetailForm(form);
      form.startPageForm();
    }
  }

  protected IPageForm execCreateDetailForm() throws ProcessingException {
    try {
      return m_formType.newInstance();
    }
    catch (Exception e) {
      throw new ProcessingException("create " + m_formType, e);
    }
  }

  @Override
  protected void execPageDeactivated() throws ProcessingException {
    if (getDetailForm() != null) {
      getDetailForm().doClose();
      setDetailForm(null);
    }
  }

  @Order(10.0)
  public class OpenInADialogMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("OpenInADialog");
    }

    @Override
    protected void execAction() throws ProcessingException {
      IPageForm form = execCreateDetailForm();
      form.setDisplayHint(IForm.DISPLAY_HINT_DIALOG);
      form.setAskIfNeedSave(false);
      form.startPageForm();
      form.waitFor();
    }
  }

  @Order(20.0)
  public class ViewSourceOnGitHubMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ViewSourceOnGitHub");
    }

    @Override
    protected void execAction() throws ProcessingException {
      String linkaddress = "https://github.com/BSI-Business-Systems-Integration-AG/org.eclipse.scout.example/tree/" +
          ClientSession.get().getBundle().getBundleContext().getProperty("git.branch") +
          "/scout.examples.demo/org.eclipse.scout.rt.demo.client/src/org/eclipse/scout/rt/demo/client/ui/forms/" +
          m_formType.getSimpleName() + ".java";

      new DefaultShellService().shellOpen(linkaddress);
    }
  }
}

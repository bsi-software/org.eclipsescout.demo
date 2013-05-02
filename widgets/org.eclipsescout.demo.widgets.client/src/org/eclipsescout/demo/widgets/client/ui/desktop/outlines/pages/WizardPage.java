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
package org.eclipsescout.demo.widgets.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.wizard.IWizard;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipsescout.demo.widgets.client.ui.template.menu.AbstractViewSourceOnGitHubMenu;
import org.eclipsescout.demo.widgets.client.ui.wizards.AbstractPageWizard;

public class WizardPage extends AbstractPageWithNodes {
  private Class<? extends AbstractPageWizard> m_wizardType;
  private AbstractPageWizard m_wizard;

  public WizardPage(Class<? extends AbstractPageWizard> wizardType) throws ProcessingException {
    super(false, wizardType.getName());
    m_wizardType = wizardType;
    callInitializer();
  }

  @Override
  protected String getConfiguredIconId() {
    return org.eclipsescout.demo.widgets.shared.Icons.Wizard;
  }

  @Override
  protected boolean getConfiguredLeaf() {
    return true;
  }

  @Override
  protected void execInitPage() throws ProcessingException {
    String s = m_wizardType.getSimpleName();
    getCellForUpdate().setText(TEXTS.get(s));
    setTableVisible(false);
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    m_wizard = execCreateWizard();
    m_wizard.setDisplayHint(IWizard.DISPLAY_HINT_VIEW);
    m_wizard.setDisplayViewId(IWizard.VIEW_ID_PAGE_TABLE);
    m_wizard.start();
  }

  @Override
  protected void execPageDeactivated() throws ProcessingException {
    m_wizard.close();
  }

  protected AbstractPageWizard execCreateWizard() throws ProcessingException {
    try {
      return m_wizardType.newInstance();
    }
    catch (Exception e) {
      throw new ProcessingException("create " + m_wizardType, e);
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
      execCreateWizard().start();
    }
  }

  @Order(20.0)
  public class ViewSourceOnGitHubMenu extends AbstractViewSourceOnGitHubMenu {

    @Override
    protected Class<?> provideSourceClass() {
      return m_wizardType;
    }

  }
}

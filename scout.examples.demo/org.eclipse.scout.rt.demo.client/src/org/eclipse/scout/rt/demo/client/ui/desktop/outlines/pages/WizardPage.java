package org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.services.common.shell.DefaultShellService;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.wizard.IWizard;
import org.eclipse.scout.rt.demo.client.ClientSession;
import org.eclipse.scout.rt.demo.client.ui.wizards.AbstractPageWizard;
import org.eclipse.scout.rt.shared.TEXTS;

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
    return org.eclipse.scout.rt.demo.shared.Icons.Wizard;
  }

  @Override
  protected boolean getConfiguredLeaf() {
    return true;
  }

  @Override
  protected void execInitPage() throws ProcessingException {
    String s = m_wizardType.getSimpleName();
    getCellForUpdate().setText(s);
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
  public class ViewSourceOnGitHubMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ViewSourceOnGitHub");
    }

    @Override
    protected void execAction() throws ProcessingException {
      String linkaddress = "https://github.com/BSI-Business-Systems-Integration-AG/org.eclipse.scout.example/tree/" +
          ClientSession.get().getBundle().getBundleContext().getProperty("git.branch") +
          "/scout.examples.demo/org.eclipse.scout.rt.demo.client/src/org/eclipse/scout/rt/demo/client/ui/wizards/" +
          m_wizardType.getSimpleName() + ".java";

      new DefaultShellService().shellOpen(linkaddress);
    }
  }
}

package org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.demo.client.ui.forms.ITestForm;
import org.eclipse.scout.rt.shared.TEXTS;

public class TestPage extends AbstractPageWithNodes {

  private Class<? extends ITestForm> m_testFormType;

  public TestPage() {
    super();
  }

  public TestPage(Class<? extends ITestForm> c) {
    super(false, c.getName());
    m_testFormType = c;
    callInitializer();
  }

  @Override
  protected String getConfiguredIconId() {
    return org.eclipse.scout.rt.shared.AbstractIcons.TreeNodeOpen;
  }

  @Override
  protected void execInitPage() throws ProcessingException {
    String s = m_testFormType.getSimpleName();
    s = s.replace("Form", "");
    getCellForUpdate().setText(s);
    setTableVisible(false);
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    if (getDetailForm() == null) {
      ITestForm form = execCreateDetailForm();
      setDetailForm(form);
      form.startTest();
    }
  }

  protected ITestForm execCreateDetailForm() throws ProcessingException {
    try {
      return m_testFormType.newInstance();
    }
    catch (Exception e) {
      throw new ProcessingException("create " + m_testFormType, e);
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
      ITestForm form = execCreateDetailForm();
      form.setDisplayHint(IForm.DISPLAY_HINT_DIALOG);
      form.setAskIfNeedSave(false);
      form.startTest();
      form.waitFor();
    }
  }
}

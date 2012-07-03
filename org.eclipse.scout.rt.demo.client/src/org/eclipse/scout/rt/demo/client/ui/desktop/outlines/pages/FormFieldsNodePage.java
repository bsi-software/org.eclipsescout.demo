package org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.demo.client.ui.forms.AllFieldsForm;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm;
import org.eclipse.scout.rt.shared.TEXTS;

public class FormFieldsNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredIconId() {
    return org.eclipse.scout.rt.shared.AbstractIcons.TreeNode;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("FormFields");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    TestPage testPage = new TestPage(AllFieldsForm.class);
    pageList.add(testPage);
    TestPage testPage0 = new TestPage(SmartFieldForm.class);
    pageList.add(testPage0);
  }
}

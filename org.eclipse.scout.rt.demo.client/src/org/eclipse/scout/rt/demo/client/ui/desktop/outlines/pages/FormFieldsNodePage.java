package org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.demo.client.ui.forms.AllFieldsForm;
import org.eclipse.scout.rt.demo.client.ui.forms.BrowserForm;
import org.eclipse.scout.rt.demo.client.ui.forms.CalendarForm;
import org.eclipse.scout.rt.demo.client.ui.forms.CheckboxForm;
import org.eclipse.scout.rt.demo.client.ui.forms.DateFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelForm;
import org.eclipse.scout.rt.demo.client.ui.forms.SVGFieldForm;
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
    TestPage testPage1 = new TestPage(LabelForm.class);
    pageList.add(testPage1);
    TestPage testPage2 = new TestPage(CalendarForm.class);
    pageList.add(testPage2);
    TestPage testPage3 = new TestPage(CheckboxForm.class);
    pageList.add(testPage3);
    TestPage testPage4 = new TestPage(DateFieldForm.class);
    pageList.add(testPage4);
    TestPage testPage5 = new TestPage(SVGFieldForm.class);
    pageList.add(testPage5);
    TestPage testPage6 = new TestPage(BrowserForm.class);
    pageList.add(testPage6);
  }
}

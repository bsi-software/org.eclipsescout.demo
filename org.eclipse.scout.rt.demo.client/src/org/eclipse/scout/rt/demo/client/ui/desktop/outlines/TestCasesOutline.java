package org.eclipse.scout.rt.demo.client.ui.desktop.outlines;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages.FormFieldsNodePage;
import org.eclipse.scout.rt.shared.TEXTS;

public class TestCasesOutline extends AbstractOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("TestCases");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    FormFieldsNodePage formFieldsNodePage = new FormFieldsNodePage();
    pageList.add(formFieldsNodePage);

  }
}

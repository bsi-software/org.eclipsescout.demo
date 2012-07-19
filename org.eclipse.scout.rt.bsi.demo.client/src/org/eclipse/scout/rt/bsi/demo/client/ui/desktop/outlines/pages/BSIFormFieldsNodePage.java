package org.eclipse.scout.rt.bsi.demo.client.ui.desktop.outlines.pages;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.DocumentFieldForm;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.HTMLEditorForm;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.ReportingFieldForm;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.Win32Form;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

public class BSIFormFieldsNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("BSIFormFields");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    FormNodePage formNodePage = new FormNodePage(OcrForm.class);
    pageList.add(formNodePage);
    FormNodePage formNodePage0 = new FormNodePage(DocumentFieldForm.class);
    pageList.add(formNodePage0);
    FormNodePage formNodePage1 = new FormNodePage(HTMLEditorForm.class);
    pageList.add(formNodePage1);
    FormNodePage formNodePage2 = new FormNodePage(ReportingFieldForm.class);
    pageList.add(formNodePage2);
    FormNodePage formNodePage3 = new FormNodePage(Win32Form.class);
    pageList.add(formNodePage3);

  }
}

package org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages;

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.demo.client.ui.forms.AllFieldsForm;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm;
import org.eclipse.scout.rt.demo.client.ui.forms.CalendarForm;
import org.eclipse.scout.rt.demo.client.ui.forms.CheckboxForm;
import org.eclipse.scout.rt.demo.client.ui.forms.ComposerFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.DateFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.GroupBoxForm;
import org.eclipse.scout.rt.demo.client.ui.forms.HTMLFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.KeyStrokeForm;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelForm;
import org.eclipse.scout.rt.demo.client.ui.forms.ListBoxForm;
import org.eclipse.scout.rt.demo.client.ui.forms.MessageBoxesForm;
import org.eclipse.scout.rt.demo.client.ui.forms.SVGFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.StatusForm;
import org.eclipse.scout.rt.demo.client.ui.forms.StringFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.TabBoxForm;
import org.eclipse.scout.rt.demo.client.ui.forms.TableForm;
import org.eclipse.scout.rt.demo.client.ui.forms.TreeBoxForm;
import org.eclipse.scout.rt.demo.client.ui.forms.TreeFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.WrappedFormFieldForm;
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
    FormPage formPage = new FormPage(AllFieldsForm.class);
    pageList.add(formPage);
//    FormPage formPage0 = new FormPage(BrowserFieldForm.class);
//    pageList.add(formPage0);
    FormPage formPage1 = new FormPage(ButtonsForm.class);
    pageList.add(formPage1);
    FormPage formPage2 = new FormPage(CalendarForm.class);
    pageList.add(formPage2);
    FormPage formPage3 = new FormPage(CheckboxForm.class);
    pageList.add(formPage3);
    FormPage formPage4 = new FormPage(ComposerFieldForm.class);
    pageList.add(formPage4);
    FormPage formPage5 = new FormPage(DateFieldForm.class);
    pageList.add(formPage5);
    FormPage formPage6 = new FormPage(FileChooserFieldForm.class);
    pageList.add(formPage6);
    FormPage formPage7 = new FormPage(GroupBoxForm.class);
    pageList.add(formPage7);
    FormPage formPage8 = new FormPage(HTMLFieldForm.class);
    pageList.add(formPage8);
    FormPage formPage9 = new FormPage(ImageFieldForm.class);
    pageList.add(formPage9);
    FormPage formPage10 = new FormPage(KeyStrokeForm.class);
    pageList.add(formPage10);
    FormPage formPage11 = new FormPage(LabelForm.class);
    pageList.add(formPage11);
    FormPage formPage12 = new FormPage(ListBoxForm.class);
    pageList.add(formPage12);
    FormPage formPage13 = new FormPage(MessageBoxesForm.class);
    pageList.add(formPage13);
    FormPage formPage14 = new FormPage(SVGFieldForm.class);
    pageList.add(formPage14);
    FormPage formPage15 = new FormPage(SmartFieldForm.class);
    pageList.add(formPage15);
    FormPage formPage16 = new FormPage(StatusForm.class);
    pageList.add(formPage16);
    FormPage formPage17 = new FormPage(TabBoxForm.class);
    pageList.add(formPage17);
    FormPage formPage18 = new FormPage(TableForm.class);
    pageList.add(formPage18);
    FormPage formPage19 = new FormPage(TreeBoxForm.class);
    pageList.add(formPage19);
    FormPage formPage20 = new FormPage(TreeFieldForm.class);
    pageList.add(formPage20);
    FormPage formPage21 = new FormPage(WrappedFormFieldForm.class);
    pageList.add(formPage21);

    FormPage formPage22 = new FormPage(StringFieldForm.class);
    pageList.add(formPage22);
  }
}

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

import java.util.Collection;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.demo.client.ui.forms.BrowserFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.ButtonsForm;
import org.eclipse.scout.rt.demo.client.ui.forms.CalendarFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.CheckboxForm;
import org.eclipse.scout.rt.demo.client.ui.forms.ComposerFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.DateFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.FileChooserFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.FormFieldTreeForm;
import org.eclipse.scout.rt.demo.client.ui.forms.GroupBoxForm;
import org.eclipse.scout.rt.demo.client.ui.forms.HTMLFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.InjectionFileChooserFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.KeyStrokeForm;
import org.eclipse.scout.rt.demo.client.ui.forms.LabelFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.ListBoxForm;
import org.eclipse.scout.rt.demo.client.ui.forms.MailFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.MessageBoxesForm;
import org.eclipse.scout.rt.demo.client.ui.forms.NumberFieldsDecimalFieldsForm;
import org.eclipse.scout.rt.demo.client.ui.forms.OverviewForm;
import org.eclipse.scout.rt.demo.client.ui.forms.PageFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.PlannerFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.RadioButtonGroupForm;
import org.eclipse.scout.rt.demo.client.ui.forms.SVGFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.SplitBoxForm;
import org.eclipse.scout.rt.demo.client.ui.forms.StatusForm;
import org.eclipse.scout.rt.demo.client.ui.forms.StringFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.TabBoxForm;
import org.eclipse.scout.rt.demo.client.ui.forms.TableFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.TreeBoxForm;
import org.eclipse.scout.rt.demo.client.ui.forms.TreeFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.WrappedFormFieldForm;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.ui.UserAgentUtility;

public class FormFieldsNodePage extends AbstractPageWithNodes {

  @Override
  protected String getConfiguredIconId() {
    return org.eclipse.scout.rt.demo.shared.Icons.Forms;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("FormFields");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    FormPage formPage = new FormPage(OverviewForm.class);
    pageList.add(formPage);
    FormPage formPage0 = new FormPage(BrowserFieldForm.class);
    pageList.add(formPage0);
    FormPage formPage1 = new FormPage(ButtonsForm.class);
    pageList.add(formPage1);
    FormPage formPage2 = new FormPage(CalendarFieldForm.class);
    pageList.add(formPage2);
    FormPage formPage3 = new FormPage(CheckboxForm.class);
    pageList.add(formPage3);
    FormPage formPage4 = new FormPage(ComposerFieldForm.class);
    pageList.add(formPage4);
    FormPage formPage5 = new FormPage(DateFieldForm.class);
    pageList.add(formPage5);
    FormPage formPage6 = new FormPage(FileChooserFieldForm.class);
    pageList.add(formPage6);
    FormPage formPage6extended = new FormPage(InjectionFileChooserFieldForm.class);
    pageList.add(formPage6extended);
    FormPage formPage7 = new FormPage(GroupBoxForm.class);
    pageList.add(formPage7);
    FormPage formPage8 = new FormPage(HTMLFieldForm.class);
    pageList.add(formPage8);
    FormPage formPage9 = new FormPage(ImageFieldForm.class);
    pageList.add(formPage9);
    FormPage formPage10 = new FormPage(KeyStrokeForm.class, UserAgentUtility.isRichClient());
    pageList.add(formPage10);
    FormPage formPage11 = new FormPage(LabelFieldForm.class);
    pageList.add(formPage11);
    FormPage formPage12 = new FormPage(ListBoxForm.class);
    pageList.add(formPage12);
    FormPage formPage13 = new FormPage(MailFieldForm.class, UserAgentUtility.isSwingUi());
    pageList.add(formPage13);
    FormPage formPage14 = new FormPage(MessageBoxesForm.class);
    pageList.add(formPage14);
    FormPage formPage15 = new FormPage(NumberFieldsDecimalFieldsForm.class);
    pageList.add(formPage15);
    FormPage formPage16 = new FormPage(PageFieldForm.class);
    pageList.add(formPage16);
    FormPage formPage17 = new FormPage(PlannerFieldForm.class, UserAgentUtility.isSwingUi());
    pageList.add(formPage17);
    FormPage formPage18 = new FormPage(RadioButtonGroupForm.class);
    pageList.add(formPage18);
    FormPage formPage19 = new FormPage(SmartFieldForm.class);
    pageList.add(formPage19);
    FormPage formPage20 = new FormPage(SplitBoxForm.class);
    pageList.add(formPage20);
    FormPage formPage21 = new FormPage(StatusForm.class);
    pageList.add(formPage21);
    FormPage formPage22 = new FormPage(StringFieldForm.class);
    pageList.add(formPage22);
    FormPage formPage23 = new FormPage(SVGFieldForm.class);
    pageList.add(formPage23);
    FormPage formPage24 = new FormPage(TabBoxForm.class);
    pageList.add(formPage24);
    FormPage formPage25 = new FormPage(TableFieldForm.class);
    pageList.add(formPage25);
    FormPage formPage26 = new FormPage(TreeBoxForm.class);
    pageList.add(formPage26);
    FormPage formPage27 = new FormPage(TreeFieldForm.class);
    pageList.add(formPage27);
    FormPage formPage28 = new FormPage(WrappedFormFieldForm.class);
    pageList.add(formPage28);
  }

  @Override
  protected void execInitPage() throws ProcessingException {
  }

  @Override
  protected void execPageActivated() throws ProcessingException {
    FormFieldTreeForm form = new FormFieldTreeForm(this);
    setDetailForm(form);
    form.startPageForm();
    setTableVisible(false);
  }
}

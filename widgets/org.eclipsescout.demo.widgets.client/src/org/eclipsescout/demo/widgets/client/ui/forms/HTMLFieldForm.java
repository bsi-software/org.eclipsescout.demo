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
package org.eclipsescout.demo.widgets.client.ui.forms;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

import org.eclipse.scout.commons.IOUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ClientSyncJob;
import org.eclipse.scout.rt.client.IClientSession;
import org.eclipse.scout.rt.client.services.common.icon.IconSpec;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.htmlfield.AbstractHtmlField;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.file.RemoteFile;
import org.eclipsescout.demo.widgets.client.Activator;
import org.eclipsescout.demo.widgets.client.ui.forms.HTMLFieldForm.MainBox.BlankButton;
import org.eclipsescout.demo.widgets.client.ui.forms.HTMLFieldForm.MainBox.CloseButton;
import org.eclipsescout.demo.widgets.client.ui.forms.HTMLFieldForm.MainBox.HTMLField;
import org.eclipsescout.demo.widgets.client.ui.forms.HTMLFieldForm.MainBox.ScoutHtmlButton;
import org.eclipsescout.demo.widgets.client.ui.forms.HTMLFieldForm.MainBox.WizardStatusButton;

public class HTMLFieldForm extends AbstractForm implements IPageForm {

  public HTMLFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("HTMLField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public BlankButton getBlankButton() {
    return getFieldByClass(BlankButton.class);
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public HTMLField getHTMLField() {
    return getFieldByClass(HTMLField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public ScoutHtmlButton getScoutHtmlButton() {
    return getFieldByClass(ScoutHtmlButton.class);
  }

  public WizardStatusButton getWizardStatusButton() {
    return getFieldByClass(WizardStatusButton.class);
  }

  private void loadFile(String simpleName, RemoteFile... attachments) throws ProcessingException {
    try {
      String s = IOUtility.getContent(new InputStreamReader(Activator.getDefault().getBundle().getResource("resources/html/" + simpleName).openStream()));
      getHTMLField().setValue(null);
      getHTMLField().setAttachments(attachments);
      getHTMLField().setValue(s);
    }
    catch (Exception e) {
      throw new ProcessingException("Html-Field can't load file ", e);
    }
  }

  private RemoteFile loadIcon(String iconName) throws ProcessingException {
    try {
      // determine file format
      int index = iconName.lastIndexOf(".");
      String format = iconName.substring(iconName.lastIndexOf("."));
      // determine icon name
      iconName = iconName.substring(0, iconName.lastIndexOf("."));
      // determine icon base name
      String baseIconName = iconName;
      index = iconName.lastIndexOf("_");
      if (index > 0) {
        baseIconName = iconName.substring(0, index);
      }

      // load icon
      IClientSession clientSession = ClientSyncJob.getCurrentSession();
      IconSpec iconSpec = clientSession.getIconLocator().getIconSpec(iconName);
      if (iconSpec == null && !iconName.equals(baseIconName)) {
        iconSpec = clientSession.getIconLocator().getIconSpec(baseIconName);
      }

      if (iconSpec != null) {
        RemoteFile iconFile = new RemoteFile(iconName + format, 0);
        ByteArrayInputStream is = new ByteArrayInputStream(iconSpec.getContent());
        iconFile.readData(is);
        is.close();
        return iconFile;
      }
    }
    catch (Exception e) {
      throw new ProcessingException("failed to load image for " + iconName, e);
    }
    return null;
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class HTMLField extends AbstractHtmlField {

      @Override
      protected int getConfiguredGridH() {
        return 12;
      }

      @Override
      protected int getConfiguredGridW() {
        return 2;
      }

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }

      @Override
      protected boolean getConfiguredScrollBarEnabled() {
        return true;
      }
    }

    @Order(20.0)
    public class BlankButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Blank");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        getHTMLField().setAttachments(null);
        getHTMLField().setValue(null);
      }
    }

    @Order(30.0)
    public class WizardStatusButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("WizardStatus");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        loadFile(
            "wizardStatus.html",
            loadIcon(AbstractIcons.Empty + ".png"),
            loadIcon(AbstractIcons.WizardBullet + ".png"),
            loadIcon(AbstractIcons.WizardBullet + "_disabled.png"),
            loadIcon(AbstractIcons.WizardBullet + "_selected.png"));
      }
    }

    @Order(40.0)
    public class ScoutHtmlButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("ScoutHtml");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        loadFile("ScoutHtml.html");
      }
    }

    @Order(50.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

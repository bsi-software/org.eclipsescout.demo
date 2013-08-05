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

//TODO: check how maven should handle javax.mail.internet.MimeMessage.

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractLinkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipsescout.demo.widgets.client.ui.forms.MailFieldForm.MainBox.CloseButton;
import org.eclipsescout.demo.widgets.client.ui.forms.MailFieldForm.MainBox.HTMLButton;
import org.eclipsescout.demo.widgets.client.ui.forms.MailFieldForm.MainBox.HTMLWithAttachmentsButton;
import org.eclipsescout.demo.widgets.client.ui.forms.MailFieldForm.MainBox.HTMLWithInnerImagesButton;
import org.eclipsescout.demo.widgets.client.ui.forms.MailFieldForm.MainBox.TextButton;

//import javax.mail.internet.MimeMessage;

public class MailFieldForm extends AbstractForm implements IPageForm {

  public MailFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("MailField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public HTMLButton getHTMLButton() {
    return getFieldByClass(HTMLButton.class);
  }

  public HTMLWithAttachmentsButton getHTMLWithAttachmentsButton() {
    return getFieldByClass(HTMLWithAttachmentsButton.class);
  }

  public HTMLWithInnerImagesButton getHTMLWithInnerImagesButton() {
    return getFieldByClass(HTMLWithInnerImagesButton.class);
  }

//  public MailField getMailField() {
//    return getFieldByClass(MailField.class);
//  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public TextButton getTextButton() {
    return getFieldByClass(TextButton.class);
  }

//  public void loadFile(String fileName) throws ProcessingException {
//    try {
//      InputStream inStream = FileLocator.openStream(Activator.getDefault().getBundle(), new Path("resources/mails/" + fileName), true);
//      MimeMessage message = new MimeMessage(null, inStream);
//      getMailField().setValue(message);
//    }
//    catch (Exception e) {
//      throw new ProcessingException(null, e);
//    }
//  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

//    @Order(10.0)
//    public class MailField extends AbstractMailField {
//
//      @Override
//      protected int getConfiguredGridH() {
//        return 20;
//      }
//
//      @Override
//      protected int getConfiguredGridW() {
//        return 2;
//      }
//
//      @Override
//      protected String getConfiguredLabel() {
//        return TEXTS.get("MailField");
//      }
//
//      @Override
//      protected boolean getConfiguredLabelVisible() {
//        return false;
//      }
//    }

    @Order(20.0)
    public class TextButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Text");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
//        loadFile("textMail.mail");
      }

      @Override
      protected boolean getConfiguredEnabled() {
        return false;
      }
    }

    @Order(30.0)
    public class HTMLButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("HTML");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
//        loadFile("htmlMail.mail");
      }

      @Override
      protected boolean getConfiguredEnabled() {
        return false;
      }
    }

    @Order(40.0)
    public class HTMLWithInnerImagesButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("HTMLWithInnerImages");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
//        loadFile("htmlMailWithInnerImages.mail");
      }

      @Override
      protected boolean getConfiguredEnabled() {
        return false;
      }
    }

    @Order(50.0)
    public class HTMLWithAttachmentsButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("HTMLWithAttachments");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
//        loadFile("htmlMailWithAttachments.mail");
      }

      @Override
      protected boolean getConfiguredEnabled() {
        return false;
      }
    }

    @Order(60.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

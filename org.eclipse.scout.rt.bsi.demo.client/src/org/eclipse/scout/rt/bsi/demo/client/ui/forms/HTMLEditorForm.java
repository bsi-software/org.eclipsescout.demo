package org.eclipse.scout.rt.bsi.demo.client.ui.forms;

import java.io.ByteArrayInputStream;

import org.eclipse.scout.commons.IOUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.bsi.demo.client.Activator;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.HTMLEditorForm.MainBox.HTMLField;
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

public class HTMLEditorForm extends AbstractForm implements IPageForm {

  public HTMLEditorForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("HTMLEditor");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public HTMLField getHTMLField() {
    return getFieldByClass(HTMLField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  private void loadFile(String simpleName, RemoteFile... attachments) throws ProcessingException {
    try {
      String s = new String(IOUtility.getContent(Activator.getDefault().getBundle().getResource("resources/html/" + simpleName).openStream()), "iso-8859-1");
      getHTMLField().setValue(null);
      getHTMLField().setAttachments(attachments);
      getHTMLField().setValue(s);
    }
    catch (Exception e) {
      throw new ProcessingException(null, e);
    }
  }

  /**
   * To load an icon into the given attachments live list
   * 
   * @param attachments
   * @param iconName
   */
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
        return 20;
      }

      @Override
      protected int getConfiguredGridW() {
        return 2;
      }

      @Override
      protected boolean getConfiguredHtmlEditor() {
        return true;
      }

      @Override
      protected boolean getConfiguredLabelVisible() {
        return false;
      }
    }

    @Order(20.0)
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

    @Order(30.0)
    public class LeShopButton extends AbstractLinkButton {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("LeShop");
      }

      @Override
      protected void execClickAction() throws ProcessingException {
        loadFile("LeShop.html");
      }
    }

    @Order(40.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

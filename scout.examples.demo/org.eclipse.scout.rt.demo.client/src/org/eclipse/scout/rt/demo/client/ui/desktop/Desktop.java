/*******************************************************************************
 * Copyright (c) 2010 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.rt.demo.client.ui.desktop;

import java.util.ArrayList;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.ClientSyncJob;
import org.eclipse.scout.rt.client.ui.action.keystroke.AbstractKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktop;
import org.eclipse.scout.rt.client.ui.desktop.IDesktop;
import org.eclipse.scout.rt.client.ui.desktop.bookmark.menu.AbstractBookmarkMenu;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractFormToolButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutlineViewButton;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.form.ScoutInfoForm;
import org.eclipse.scout.rt.client.ui.form.outline.DefaultOutlineTableForm;
import org.eclipse.scout.rt.client.ui.form.outline.DefaultOutlineTreeForm;
import org.eclipse.scout.rt.demo.client.ClientSession;
import org.eclipse.scout.rt.demo.client.ui.desktop.outlines.FormFieldsWizardsOutline;
import org.eclipse.scout.rt.demo.client.ui.desktop.outlines.PagesSearchFormsOutline;
import org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages.FormFieldsNodePage;
import org.eclipse.scout.rt.demo.client.ui.forms.ToolButton1Form;
import org.eclipse.scout.rt.demo.client.ui.forms.ToolButton2Form;
import org.eclipse.scout.rt.demo.shared.Icons;
import org.eclipse.scout.rt.shared.TEXTS;

public class Desktop extends AbstractDesktop implements IDesktop {
  private static IScoutLogger logger = ScoutLogManager.getLogger(Desktop.class);

  public Desktop() {
  }

  @SuppressWarnings("unchecked")
  @Override
  protected Class<? extends IOutline>[] getConfiguredOutlines() {
    ArrayList<Class> outlines = new ArrayList<Class>();
    outlines.add(FormFieldsWizardsOutline.class);
    outlines.add(PagesSearchFormsOutline.class);
    return outlines.toArray(new Class[outlines.size()]);
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ScoutDemo");
  }

  @Override
  protected boolean getConfiguredTrayVisible() {
    return true;
  }

  @Override
  protected void execOpened() throws ProcessingException {
    // outline tree
    DefaultOutlineTreeForm treeForm = new DefaultOutlineTreeForm();
    treeForm.setIconId(Icons.EclipseScout);
    treeForm.startView();

    //outline table
    DefaultOutlineTableForm tableForm = new DefaultOutlineTableForm();
    tableForm.setIconId(Icons.EclipseScout);
    tableForm.startView();

    if (getAvailableOutlines().length > 0) {
      setOutline(getAvailableOutlines()[0]);
      getOutline().expandAll(getOutline().findPage(FormFieldsNodePage.class));
    }

  }

  @Order(10.0)
  public class FileMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("FileMenu");
    }

    @Order(100.0)
    public class ExitMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("ExitMenu");
      }

      @Override
      public void execAction() throws ProcessingException {
        ClientSyncJob.getCurrentSession(ClientSession.class).stopSession();
      }
    }
  }

  @Order(20.0)
  public class ToolsMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ToolsMenu");
    }
  }

  @Order(30.0)
  public class BookmarkMenu extends AbstractBookmarkMenu {
    public BookmarkMenu() {
      super(Desktop.this);
    }
  }

  @Order(40.0)
  public class HelpMenu extends AbstractMenu {

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("HelpMenu");
    }

    @Order(10.0)
    public class AboutMenu extends AbstractMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("AboutMenu");
      }

      @Override
      public void execAction() throws ProcessingException {
        ScoutInfoForm form = new ScoutInfoForm();
        form.startModify();
      }
    }
  }

  @Order(10.0)
  public class ToolButton1Tool extends AbstractFormToolButton<ToolButton1Form> {

    @Override
    protected String getConfiguredIconId() {
      return org.eclipse.scout.rt.shared.AbstractIcons.ComposerFieldAttribute;
    }

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ToolButton1");
    }

    @Override
    protected void execAction() throws ProcessingException {
      ToolButton1Form form = new ToolButton1Form();
      decorateForm(form);
      form.startTool();
      setForm(form);
    }
  }

  @Order(20.0)
  public class ToolButton2Tool extends AbstractFormToolButton<ToolButton2Form> {

    @Override
    protected String getConfiguredIconId() {
      return org.eclipse.scout.rt.shared.AbstractIcons.ComposerFieldAttribute;
    }

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("ToolButton2");
    }

    @Override
    protected void execAction() throws ProcessingException {
      ToolButton2Form form = new ToolButton2Form();
      decorateForm(form);
      form.startTool();
      setForm(form);
    }
  }

  @Order(10.0)
  public class RefreshOutlineKeyStroke extends AbstractKeyStroke {

    @Override
    protected String getConfiguredKeyStroke() {
      return "f5";
    }

    @Override
    protected void execAction() throws ProcessingException {
      if (getOutline() != null) {
        IPage page = getOutline().getActivePage();
        if (page != null) {
          page.reloadPage();
        }
      }
    }
  }

  @Order(10.0)
  public class TestCasesOutlineViewButton extends AbstractOutlineViewButton {
    public TestCasesOutlineViewButton() {
      super(Desktop.this, FormFieldsWizardsOutline.class);
    }

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("TestCases");
    }
  }

  @Order(20.0)
  public class PagesSearchFormsOutlineViewButton extends AbstractOutlineViewButton {
    public PagesSearchFormsOutlineViewButton() {
      super(Desktop.this, PagesSearchFormsOutline.class);
    }

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("PagesSearchForms");
    }
  }
}

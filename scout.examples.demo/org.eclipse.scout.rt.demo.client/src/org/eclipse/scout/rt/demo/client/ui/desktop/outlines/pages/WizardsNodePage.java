package org.eclipse.scout.rt.demo.client.ui.desktop.outlines.pages;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.demo.client.ui.forms.WizardsForm;
import org.eclipse.scout.rt.shared.TEXTS;

public class WizardsNodePage extends AbstractPageWithNodes {

  public WizardsNodePage() throws ProcessingException {
    super(true, WizardsForm.class.getName());
    WizardsForm w = new WizardsForm();
    setDetailForm(w);
    w.startPageForm();
    setTableVisible(false);
  }

  @Override
  protected String getConfiguredIconId() {
    return org.eclipse.scout.rt.shared.AbstractIcons.Bookmark;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Wizards");
  }
}

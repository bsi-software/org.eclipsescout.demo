package org.eclipse.scout.bahbah.client.ui.desktop.outlines;

import java.util.Collection;

import org.eclipse.scout.bahbah.client.ui.desktop.outlines.pages.UserNodePage;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.shared.TEXTS;

public class StandardOutline extends AbstractOutline {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Chat");
  }

  @Override
  protected void execCreateChildPages(Collection<IPage> pageList) throws ProcessingException {
    UserNodePage buddiesNodePage = new UserNodePage();
    pageList.add(buddiesNodePage);

  }
}

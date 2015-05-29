/**
 * 
 */
package org.eclipsescout.contacts.client.ui.desktop.outlines;

import org.eclipse.scout.rt.extension.client.ui.desktop.outline.pages.AbstractExtensiblePageWithNodes;
import org.eclipse.scout.rt.shared.TEXTS;

/**
 * @author mzi
 */
public class ContactDetailsNodePage extends AbstractExtensiblePageWithNodes {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ContactDetails");
  }
}
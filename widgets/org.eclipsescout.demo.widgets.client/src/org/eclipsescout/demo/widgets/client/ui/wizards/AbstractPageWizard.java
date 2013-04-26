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
package org.eclipsescout.demo.widgets.client.ui.wizards;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.wizard.AbstractWizard;
import org.eclipsescout.demo.widgets.client.ClientSession;

public abstract class AbstractPageWizard extends AbstractWizard {

  @Override
  protected void execFinish() throws ProcessingException {
    super.execFinish();
    if (getDisplayHint() == DISPLAY_HINT_VIEW) {
      ClientSession.get().getDesktop().getOutline().selectPreviousParentNode();
    }
  }
}

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
package org.eclipse.scout.rt.demo.client.ui.template.menu;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.services.common.shell.DefaultShellService;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.demo.client.ClientSession;
import org.eclipse.scout.rt.shared.TEXTS;

public abstract class AbstractViewSourceOnGitHubMenu extends AbstractMenu {

  @Override
  protected String getConfiguredText() {
    return TEXTS.get("ViewSourceOnGitHub");
  }

  @Override
  protected void execAction() throws ProcessingException {
    String linkaddress = "https://github.com/BSI-Business-Systems-Integration-AG/org.eclipse.scout.example/tree/" +
        ClientSession.get().getBundle().getBundleContext().getProperty("git.branch") +
        "/scout.examples.demo/org.eclipse.scout.rt.demo.client/src/" +
        provideSourceClass().getCanonicalName().replace(".", "/") + ".java";

    new DefaultShellService().shellOpen(linkaddress);
  }

  abstract protected Class<?> provideSourceClass();
}

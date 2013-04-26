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
package org.eclipsescout.demo.widgets.ui.swt;

import org.eclipse.scout.rt.ui.swt.AbstractSwtStartup;
import org.eclipse.scout.rt.ui.swt.ISwtEnvironment;

/**
 * <h3>SwtStartup</h3> The startup class is registered as an extension in the plugin.xml.
 * It is used to be aware that the Workbench is ready.
 * 
 * @see AbstractSwtStartup
 */
public class SwtStartup extends AbstractSwtStartup {
  @Override
  protected ISwtEnvironment getSwtEnvironment() {
    return Activator.getDefault().getEnvironment();
  }
}

/*******************************************************************************
 * Copyright (c) 2001 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Daniel Wiehl (Business Systems Integration AG) - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.tutorial.jaxws.ui.swt;

import org.eclipse.scout.rt.ui.swt.AbstractSwtStartup;
import org.eclipse.scout.rt.ui.swt.ISwtEnvironment;

/** <h3>CenterView</h3>
 *  The Startup class is registered as a view extension point in the plugin.xml.
 *  It is used to get aware that the Workbench is ready.
* @see AbstractSwtStartup
 */
public class SwtStartup extends AbstractSwtStartup{

  @Override
  protected ISwtEnvironment getSwtEnvironment(){
    return Activator.getDefault().getEnvironment();
  }
}

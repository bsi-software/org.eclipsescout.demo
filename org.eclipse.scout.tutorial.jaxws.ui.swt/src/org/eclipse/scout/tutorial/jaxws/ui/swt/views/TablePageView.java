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
package org.eclipse.scout.tutorial.jaxws.ui.swt.views;

import org.eclipse.scout.rt.ui.swt.ISwtEnvironment;
import org.eclipse.scout.rt.ui.swt.window.desktop.view.AbstractScoutView;
import org.eclipse.scout.tutorial.jaxws.ui.swt.SwtEnvironment;
import org.eclipse.scout.tutorial.jaxws.ui.swt.Activator;

/** <h3>CenterView</h3>
 *  A view used of scout is registered as a view extension point in the plugin.xml and linked to
 *  scout view id in the SwtEnvironment.
* @see SwtEnvironment
 */
public class TablePageView extends AbstractScoutView {


  @Override
  protected ISwtEnvironment getSwtEnvironment(){
    return Activator.getDefault().getEnvironment();
  }
}

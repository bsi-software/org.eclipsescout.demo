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
package org.eclipse.scout.tutorial.jaxws.ui.swt.application;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;


import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

/** <h3>ApplicationWorkbenchAdvisor</h3>
 *  Used for getting the initial perspective.
*/
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

  @Override
  public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
  	return new ApplicationWorkbenchWindowAdvisor(configurer);
  }

  @Override
	public String getInitialWindowPerspectiveId() {
		return "org.eclipse.scout.tutorial.jaxws.ui.swt.perspective.Perspective";
	}
}
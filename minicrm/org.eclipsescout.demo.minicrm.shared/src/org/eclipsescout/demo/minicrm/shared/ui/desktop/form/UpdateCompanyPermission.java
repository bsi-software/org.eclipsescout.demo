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
package org.eclipsescout.demo.minicrm.shared.ui.desktop.form;

import java.security.BasicPermission;

public class UpdateCompanyPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  public UpdateCompanyPermission() {
    super("UpdateCompany");
  }
}

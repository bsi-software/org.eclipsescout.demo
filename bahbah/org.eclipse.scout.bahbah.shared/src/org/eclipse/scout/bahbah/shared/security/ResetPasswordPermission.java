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
package org.eclipse.scout.bahbah.shared.security;

import java.security.BasicPermission;

public class ResetPasswordPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ResetPasswordPermission() {
    super(ResetPasswordPermission.class.getName());
  }
}

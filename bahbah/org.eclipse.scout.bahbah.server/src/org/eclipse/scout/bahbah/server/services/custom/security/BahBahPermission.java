/*******************************************************************************
 * Copyright (c) 2010 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.bahbah.server.services.custom.security;

/**
 * 
 */
public enum BahBahPermission {
  ADMIN(100),
  USER(10);

  private int m_val;

  private BahBahPermission(int val) {
    m_val = val;
  }

  public int getValue() {
    return m_val;
  }
}

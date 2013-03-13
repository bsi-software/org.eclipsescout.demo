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
package org.eclipsescout.demo.minifigcreator.shared.minifig.part;

import java.io.Serializable;

/**
 * 
 */
public class Part implements Serializable {
  private static final long serialVersionUID = 1L;

  private final String m_name;
  private final int m_value;
  private final int m_id;

  public Part(int id, String name, int value) {
    super();
    m_name = name;
    m_value = value;
    m_id = id;
  }

  public String getName() {
    return m_name;
  }

  public int getValue() {
    return m_value;
  }

  public int getId() {
    return m_id;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + m_id;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Part other = (Part) obj;
    if (m_id != other.m_id) return false;
    return true;
  }

}

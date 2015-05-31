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
package org.eclipsescout.demo.minifigcreator.server.data;

import org.eclipsescout.demo.minifigcreator.shared.minifig.part.Part;

public class PartQuantity {
  private final Part part;
  private final Integer quantity;

  public PartQuantity(Part part, Integer quantity) {
    this.part = part;
    this.quantity = quantity;
  }

  public Part getPart() {
    return part;
  }

  public Integer getQuantity() {
    return quantity;
  }
}
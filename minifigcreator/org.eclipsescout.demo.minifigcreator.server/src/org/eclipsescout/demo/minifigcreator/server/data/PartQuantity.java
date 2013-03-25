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

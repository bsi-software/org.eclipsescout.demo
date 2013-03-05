package org.eclipsescout.demo.minifigcreator.server.services.lookup;

import java.util.Arrays;
import java.util.List;


import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipsescout.demo.minifigcreator.shared.minifig.part.Part;
import org.eclipsescout.demo.minifigcreator.shared.services.lookup.ILegsLookupService;

public class LegsLookupService extends AbstractPartLookupService implements ILegsLookupService {

  @Override
  protected List<Part> createPartsList(LookupCall call) {
    return Arrays.asList(
        new Part(1, "Yellow", 10),
        new Part(2, "Odd", 24),
        new Part(3, "Jeans", 15),
        new Part(4, "Gray", 15),
        new Part(5, "Overall", 15),
        new Part(6, "Blue", 15)
        );
  }
}

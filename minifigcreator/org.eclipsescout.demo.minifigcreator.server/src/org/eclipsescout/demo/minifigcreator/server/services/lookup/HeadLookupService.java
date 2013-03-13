package org.eclipsescout.demo.minifigcreator.server.services.lookup;

import java.util.Arrays;
import java.util.List;


import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipsescout.demo.minifigcreator.shared.minifig.part.Part;
import org.eclipsescout.demo.minifigcreator.shared.services.lookup.IHeadLookupService;

public class HeadLookupService extends AbstractPartLookupService implements IHeadLookupService {

  @Override
  protected List<Part> createPartsList(LookupCall call) {
    return Arrays.asList(
        new Part(1, "Normal", 12),
        new Part(2, "Pirate", 13),
        new Part(4, "Talky & Walky", 8),
        new Part(5, "Bart", 16)
        );
  }
}

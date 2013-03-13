package org.eclipsescout.demo.minifigcreator.server.services.lookup;

import java.util.Arrays;
import java.util.List;


import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipsescout.demo.minifigcreator.shared.minifig.part.Part;
import org.eclipsescout.demo.minifigcreator.shared.services.lookup.ITorsoLookupService;

public class TorsoLookupService extends AbstractPartLookupService implements ITorsoLookupService {

  @Override
  protected List<Part> createPartsList(LookupCall call) {
    return Arrays.asList(
        new Part(1, "Yellow", 19),
        new Part(2, "Police", 28),
        new Part(3, "Suit", 32)
        );
  }
}

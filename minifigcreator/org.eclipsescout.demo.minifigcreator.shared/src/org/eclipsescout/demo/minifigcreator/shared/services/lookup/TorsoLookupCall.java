package org.eclipsescout.demo.minifigcreator.shared.services.lookup;

import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.rt.shared.services.lookup.ILookupService;
import org.eclipsescout.demo.minifigcreator.shared.services.lookup.ITorsoLookupService;

public class TorsoLookupCall extends LookupCall{

  private static final long serialVersionUID = 1L;

  @Override
  protected Class<? extends ILookupService> getConfiguredService() {
    return ITorsoLookupService.class;
  }
}

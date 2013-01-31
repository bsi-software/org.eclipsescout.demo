package org.eclipse.scout.rt.demo.client.services.lookup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

public class FontstyleLookupCall extends LocalLookupCall {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<LookupRow> execCreateLookupRows() throws ProcessingException {
    ArrayList<LookupRow> rows = new ArrayList<LookupRow>();
    rows.add(new LookupRow(0, TEXTS.get("Default")));
    rows.add(new LookupRow(1, TEXTS.get("Bold")));
    rows.add(new LookupRow(2, TEXTS.get("Italic")));
    rows.add(new LookupRow(3, TEXTS.get("Bold") + " " + TEXTS.get("Italic")));
    return rows;
  }
}

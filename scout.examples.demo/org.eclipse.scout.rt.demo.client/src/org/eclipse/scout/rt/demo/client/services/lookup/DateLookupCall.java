package org.eclipse.scout.rt.demo.client.services.lookup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.data.basic.FontSpec;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

public class DateLookupCall extends LocalLookupCall {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<LookupRow> execCreateLookupRows() throws ProcessingException {
    ArrayList<LookupRow> rows = new ArrayList<LookupRow>();
    for (long l = 0L; l <= 5L; l++) {
      LookupRow year = new LookupRow(l * 5, "201" + l);
      year.setEnabled(false);
      rows.add(year);
      rows.add(new LookupRow(l * 5 + 1, "Jan", null, null, "FFFFFF", "000000", new FontSpec("Courir", FontSpec.STYLE_PLAIN, 12), true, l * 5));
      rows.add(new LookupRow(l * 5 + 2, "Mar", null, null, "FFFFFF", "000000", new FontSpec("Courir", FontSpec.STYLE_PLAIN, 12), false, l * 5));
      rows.add(new LookupRow(l * 5 + 3, "Sep", null, null, "FFFFFF", "000000", new FontSpec("Courir", FontSpec.STYLE_PLAIN, 12), true, l * 5));
      rows.add(new LookupRow(l * 5 + 4, "Nov", null, null, "FFFFFF", "000000", new FontSpec("Courir", FontSpec.STYLE_PLAIN, 12), false, l * 5));
    }
    return rows;
  }
}

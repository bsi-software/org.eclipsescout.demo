package org.eclipse.scout.rt.demo.client.services.lookup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.demo.client.ui.forms.AllFieldsForm.MainBox.SimpleFieldsBox.LabelField;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

public class LabelpositionLookupCall extends LocalLookupCall {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<LookupRow> execCreateLookupRows() throws ProcessingException {
    ArrayList<LookupRow> rows = new ArrayList<LookupRow>();
    rows.add(new LookupRow(LabelField.LABEL_POSITION_DEFAULT, "Default"));
    rows.add(new LookupRow(LabelField.LABEL_POSITION_LEFT, "Left"));
    rows.add(new LookupRow(LabelField.LABEL_POSITION_RIGHT, "Right"));
    rows.add(new LookupRow(LabelField.LABEL_POSITION_ON_FIELD, "On Field"));
    rows.add(new LookupRow(LabelField.LABEL_POSITION_TOP, "Top"));
    return rows;
  }
}

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
package org.eclipse.scout.rt.demo.client.services.lookup;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.demo.client.ui.forms.AllFieldsForm;
import org.eclipse.scout.rt.demo.client.ui.forms.ImageFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm;
import org.eclipse.scout.rt.demo.client.ui.forms.StatusForm;
import org.eclipse.scout.rt.shared.services.lookup.LocalLookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;

public class FormLookupCall extends LocalLookupCall {

  private static final long serialVersionUID = 1L;

  @Override
  protected List<LookupRow> execCreateLookupRows() throws ProcessingException {
    ArrayList<LookupRow> rows = new ArrayList<LookupRow>();
    rows.add(new LookupRow(new AllFieldsForm(), "AllFields"));
    rows.add(new LookupRow(new SmartFieldForm(), "SmartField"));
    rows.add(new LookupRow(new StatusForm(), "Status"));
    rows.add(new LookupRow(new ImageFieldForm(), "ImageField"));
    return rows;
  }
}

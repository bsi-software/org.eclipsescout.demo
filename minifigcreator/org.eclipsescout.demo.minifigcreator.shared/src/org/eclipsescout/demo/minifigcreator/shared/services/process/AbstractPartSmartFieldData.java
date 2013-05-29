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
package org.eclipsescout.demo.minifigcreator.shared.services.process;

import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipsescout.demo.minifigcreator.shared.minifig.part.Part;

public abstract class AbstractPartSmartFieldData extends AbstractValueFieldData<Part> {
  private static final long serialVersionUID = 1L;

  public AbstractPartSmartFieldData() {
  }

  /**
   * list of derived validation rules.
   */
  @Override
  protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
    super.initValidationRules(ruleMap);
    ruleMap.put(ValidationRule.ZERO_NULL_EQUALITY, true);
  }
}

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
package org.eclipse.scout.tutorial.jaxws.shared.services.process;

import java.util.Date;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

public class CompanyFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public CompanyFormData() {
  }

  public CompanyNrProperty getCompanyNrProperty() {
    return getPropertyByClass(CompanyNrProperty.class);
  }

  /**
   * access method for property CompanyNr.
   */
  public Long getCompanyNr() {
    return getCompanyNrProperty().getValue();
  }

  /**
   * access method for property CompanyNr.
   */
  public void setCompanyNr(Long companyNr) {
    getCompanyNrProperty().setValue(companyNr);
  }

  public Change getChange() {
    return getFieldByClass(Change.class);
  }

  public Name getName() {
    return getFieldByClass(Name.class);
  }

  public Symbol getSymbol() {
    return getFieldByClass(Symbol.class);
  }

  public TradeTime getTradeTime() {
    return getFieldByClass(TradeTime.class);
  }

  public ValueHigh getValueHigh() {
    return getFieldByClass(ValueHigh.class);
  }

  public ValueLast getValueLast() {
    return getFieldByClass(ValueLast.class);
  }

  public ValueLow getValueLow() {
    return getFieldByClass(ValueLow.class);
  }

  public ValueOpen getValueOpen() {
    return getFieldByClass(ValueOpen.class);
  }

  public Volume getVolume() {
    return getFieldByClass(Volume.class);
  }

  public class CompanyNrProperty extends AbstractPropertyData<Long> {
    private static final long serialVersionUID = 1L;

    public CompanyNrProperty() {
    }
  }

  public static class Change extends AbstractValueFieldData<Double> {
    private static final long serialVersionUID = 1L;

    public Change() {
    }
  }

  public static class Name extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Name() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class Symbol extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Symbol() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 5);
    }
  }

  public static class TradeTime extends AbstractValueFieldData<Date> {
    private static final long serialVersionUID = 1L;

    public TradeTime() {
    }
  }

  public static class ValueHigh extends AbstractValueFieldData<Double> {
    private static final long serialVersionUID = 1L;

    public ValueHigh() {
    }
  }

  public static class ValueLast extends AbstractValueFieldData<Double> {
    private static final long serialVersionUID = 1L;

    public ValueLast() {
    }
  }

  public static class ValueLow extends AbstractValueFieldData<Double> {
    private static final long serialVersionUID = 1L;

    public ValueLow() {
    }
  }

  public static class ValueOpen extends AbstractValueFieldData<Double> {
    private static final long serialVersionUID = 1L;

    public ValueOpen() {
    }
  }

  public static class Volume extends AbstractValueFieldData<Long> {
    private static final long serialVersionUID = 1L;

    public Volume() {
    }
  }
}

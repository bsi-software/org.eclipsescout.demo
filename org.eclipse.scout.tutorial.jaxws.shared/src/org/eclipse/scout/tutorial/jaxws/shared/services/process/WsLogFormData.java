/*******************************************************************************
 * Copyright (c) 2001 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Daniel Wiehl (Business Systems Integration AG) - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.tutorial.jaxws.shared.services.process;

import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import java.util.Map;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

public class WsLogFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public WsLogFormData() {
  }

  public WsLogNrProperty getWsLogNrProperty() {
    return getPropertyByClass(WsLogNrProperty.class);
  }

  /**
   * access method for property WsLogNr.
   */
  public Long getWsLogNr() {
    return getWsLogNrProperty().getValue();
  }

  /**
   * access method for property WsLogNr.
   */
  public void setWsLogNr(Long wsLogNr) {
    getWsLogNrProperty().setValue(wsLogNr);
  }

  public Date getDate() {
    return getFieldByClass(Date.class);
  }

  public Operation getOperation() {
    return getFieldByClass(Operation.class);
  }

  public Port getPort() {
    return getFieldByClass(Port.class);
  }

  public Request getRequest() {
    return getFieldByClass(Request.class);
  }

  public Response getResponse() {
    return getFieldByClass(Response.class);
  }

  public Service getService() {
    return getFieldByClass(Service.class);
  }

  public class WsLogNrProperty extends AbstractPropertyData<Long> {
    private static final long serialVersionUID = 1L;

    public WsLogNrProperty() {
    }
  }

  public static class Date extends AbstractValueFieldData<java.util.Date> {
    private static final long serialVersionUID = 1L;

    public Date() {
    }
  }

  public static class Operation extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Operation() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class Port extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Port() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }

  public static class Request extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Request() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, Integer.MAX_VALUE);
    }
  }

  public static class Response extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Response() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, Integer.MAX_VALUE);
    }
  }

  public static class Service extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Service() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }
}

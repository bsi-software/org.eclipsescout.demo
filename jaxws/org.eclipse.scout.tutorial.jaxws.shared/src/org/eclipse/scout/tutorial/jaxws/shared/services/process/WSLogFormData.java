package org.eclipse.scout.tutorial.jaxws.shared.services.process;

import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

public class WSLogFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public WSLogFormData() {
  }

  public WSLogNrProperty getWSLogNrProperty() {
    return getPropertyByClass(WSLogNrProperty.class);
  }

  /**
   * access method for property WSLogNr.
   */
  public Long getWSLogNr() {
    return getWSLogNrProperty().getValue();
  }

  /**
   * access method for property WSLogNr.
   */
  public void setWSLogNr(Long wSLogNr) {
    getWSLogNrProperty().setValue(wSLogNr);
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

  public class WSLogNrProperty extends AbstractPropertyData<Long> {
    private static final long serialVersionUID = 1L;

    public WSLogNrProperty() {
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
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
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
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
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
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
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
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
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
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }
}

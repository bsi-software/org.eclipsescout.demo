package org.eclipsescout.demo.minicrm.shared.ui.desktop.outlines.pages.searchform;

import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;
import org.eclipsescout.demo.minicrm.shared.services.code.CompanyTypeCodeType;
import org.eclipsescout.demo.minicrm.shared.services.lookup.CompanyLookupCall;

public class PersonSearchFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public PersonSearchFormData() {
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

  public Employer getEmployer() {
    return getFieldByClass(Employer.class);
  }

  public EmployerType getEmployerType() {
    return getFieldByClass(EmployerType.class);
  }

  public FirstName getFirstName() {
    return getFieldByClass(FirstName.class);
  }

  public LastName getLastName() {
    return getFieldByClass(LastName.class);
  }

  public class CompanyNrProperty extends AbstractPropertyData<Long> {
    private static final long serialVersionUID = 1L;

    public CompanyNrProperty() {
    }
  }

  public static class Employer extends AbstractValueFieldData<Long> {
    private static final long serialVersionUID = 1L;

    public Employer() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.LOOKUP_CALL, CompanyLookupCall.class);
      ruleMap.put(ValidationRule.MASTER_VALUE_FIELD, EmployerType.class);
      ruleMap.put(ValidationRule.ZERO_NULL_EQUALITY, true);
    }
  }

  public static class EmployerType extends AbstractValueFieldData<Long> {
    private static final long serialVersionUID = 1L;

    public EmployerType() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.CODE_TYPE, CompanyTypeCodeType.class);
      ruleMap.put(ValidationRule.ZERO_NULL_EQUALITY, true);
    }
  }

  public static class FirstName extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public FirstName() {
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

  public static class LastName extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public LastName() {
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

package org.eclipse.scout.bahbah.shared.services.code;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

public class UserRoleCodeType extends AbstractCodeType<Integer> {

  private static final long serialVersionUID = 1L;
  public static final Integer ID = 1000;

  public UserRoleCodeType() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredText() {
    return TEXTS.get("UserRole");
  }

  @Override
  public Integer getId() {
    return ID;
  }

  @Order(10.0)
  public static class AdministratorCode extends AbstractCode<Integer> {

    private static final long serialVersionUID = 1L;
    public static final Integer ID = 100;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Administrator");
    }

    @Override
    public Integer getId() {
      return ID;
    }
  }

  @Order(20.0)
  public static class UserCode extends AbstractCode<Integer> {

    private static final long serialVersionUID = 1L;
    public static final Integer ID = 10;

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("User");
    }

    @Override
    public Integer getId() {
      return ID;
    }
  }
}

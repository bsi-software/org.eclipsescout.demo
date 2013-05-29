package org.eclipsescout.demo.minicrm.shared.ui.desktop.form;

import java.security.BasicPermission;

public class CreateCompanyPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public CreateCompanyPermission() {
    super("CreateCompany");
  }
}

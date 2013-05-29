package org.eclipsescout.demo.minicrm.shared.ui.desktop.form;

import java.security.BasicPermission;

public class UpdateCompanyPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public UpdateCompanyPermission() {
    super("UpdateCompany");
  }
}

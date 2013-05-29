package org.eclipsescout.demo.minicrm.shared.ui.desktop.form;

import java.security.BasicPermission;

public class ReadCompanyPermission extends BasicPermission {

  private static final long serialVersionUID = 0L;

  public ReadCompanyPermission() {
    super("ReadCompany");
  }
}

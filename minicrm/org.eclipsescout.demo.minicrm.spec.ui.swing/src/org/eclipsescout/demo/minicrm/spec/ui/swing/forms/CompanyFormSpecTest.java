package org.eclipsescout.demo.minicrm.spec.ui.swing.forms;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm;
import org.eclipsescout.demo.minicrm.spec.ui.swing.AbstractMiniCRMFormSpec;

public class CompanyFormSpecTest extends AbstractMiniCRMFormSpec {

  @Override
  protected IForm createAndStartForm() throws ProcessingException {
    CompanyForm form = new CompanyForm();
    form.startNew();
    return form;
  }

}

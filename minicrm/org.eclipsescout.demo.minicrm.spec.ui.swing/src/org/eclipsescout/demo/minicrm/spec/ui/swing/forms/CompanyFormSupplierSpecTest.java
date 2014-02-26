package org.eclipsescout.demo.minicrm.spec.ui.swing.forms;

import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.FormListener;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm;
import org.eclipsescout.demo.minicrm.spec.ui.swing.AbstractMiniCRMFormSpec;

public class CompanyFormSupplierSpecTest extends AbstractMiniCRMFormSpec {

  @Override
  protected IForm createAndStartForm(List<FormListener> formListeners) throws ProcessingException {
    CompanyForm form = new CompanyForm();
    for (FormListener listener : formListeners) {
      form.addFormListener(listener);
    }
    form.setClassId("721c3f5f-bd28-41e4-a5f0-d78891034485_supplier");
    form.startNew();
    form.getCompanyTypeGroup().setValue(10002L);
    return form;
  }

}

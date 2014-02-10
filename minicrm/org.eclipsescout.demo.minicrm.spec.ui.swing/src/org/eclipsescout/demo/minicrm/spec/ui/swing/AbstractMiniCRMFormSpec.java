package org.eclipsescout.demo.minicrm.spec.ui.swing;

import java.util.Locale;

import org.eclipse.scout.commons.LocaleThreadLocal;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.spec.client.AbstractFormSpecTest;
import org.eclipse.scout.testing.client.runner.ScoutClientGUITestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ScoutClientGUITestRunner.class)
public abstract class AbstractMiniCRMFormSpec extends AbstractFormSpecTest {

  @Test
  public void createForm() throws ProcessingException {
    LocaleThreadLocal.set(new Locale("de"));
    printForm();
    printAllFields();
  }

}

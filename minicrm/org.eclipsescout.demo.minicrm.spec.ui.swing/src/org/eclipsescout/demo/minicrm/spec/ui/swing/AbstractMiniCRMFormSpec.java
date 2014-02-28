package org.eclipsescout.demo.minicrm.spec.ui.swing;

import java.util.Locale;

import org.eclipse.scout.commons.LocaleThreadLocal;
import org.eclipse.scout.rt.spec.client.AbstractFormSpecTest;
import org.junit.After;
import org.junit.Before;

public abstract class AbstractMiniCRMFormSpec extends AbstractFormSpecTest {

  private Locale m_oldLocale;

  @Before
  public void before() {
    m_oldLocale = LocaleThreadLocal.get();
    LocaleThreadLocal.set(new Locale("de"));
  }

  @After
  public void after() {
    LocaleThreadLocal.set(m_oldLocale);
  }

}

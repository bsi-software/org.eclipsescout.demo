package org.eclipsescout.demo.minicrm.spec.ui.swing;

import java.util.List;
import java.util.Locale;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.rt.client.IClientSession;
import org.eclipse.scout.rt.client.services.common.session.IClientSessionRegistryService;
import org.eclipse.scout.rt.client.ui.form.fields.IFormField;
import org.eclipse.scout.rt.spec.client.SpecJob;
import org.eclipse.scout.rt.spec.client.SpecUtility;
import org.eclipse.scout.rt.spec.client.config.entity.DefaultFormFieldTableConfig;
import org.eclipse.scout.rt.spec.client.gen.extract.IDocTextExtractor;
import org.eclipse.scout.rt.spec.client.gen.extract.SimpleTypeTextExtractor;
import org.eclipse.scout.rt.ui.swing.AbstractSwingApplication;
import org.eclipse.scout.rt.ui.swing.ISwingEnvironment;
import org.eclipse.scout.service.SERVICES;
import org.eclipse.scout.testing.client.TestingClientSessionRegistryService;
import org.eclipse.scout.testing.client.runner.ScoutClientTestRunner;
import org.eclipsescout.demo.minicrm.client.ClientSession;
import org.eclipsescout.demo.minicrm.ui.swing.SwingEnvironment;

public class MinicrmSwingSpecApplication extends AbstractSwingApplication {
  private static IScoutLogger LOG = ScoutLogManager
      .getLogger(MinicrmSwingSpecApplication.class);
  private TestingClientSessionRegistryService m_testingClientSessionRegistryService = null;

  @Override
  protected Object startInSubject(IApplicationContext context) throws Exception {
    LOG.info("Starting App");
    Locale.setDefault(new Locale("en"));
    ScoutClientTestRunner.setDefaultClientSessionClass(ClientSession.class);
    m_testingClientSessionRegistryService = TestingClientSessionRegistryService
        .registerTestingClientSessionRegistryService();
    new SpecJob(ClientSession.class, Platform.getProduct()
        .getDefiningBundle().getSymbolicName()).schedule(200);

    SpecUtility.getDocConfigInstance().setFormFieldTableConfig(new DefaultFormFieldTableConfig() {
      @Override
      public List<IDocTextExtractor<IFormField>> getTextExtractors() {
        List<IDocTextExtractor<IFormField>> textExtractors = super.getTextExtractors();
        textExtractors.add(new SimpleTypeTextExtractor<IFormField>("#DEV# Classname"));
        return textExtractors;
      }

    });

    return super.startInSubject(context);
  }

  @Override
  protected ISwingEnvironment createSwingEnvironment() {
    return new SwingEnvironment();
  }

  @Override
  protected IClientSession getClientSession() {
    return SERVICES.getService(IClientSessionRegistryService.class)
        .newClientSession(ClientSession.class, initUserAgent());
  }

  @Override
  public void stop() {
    super.stop();
    TestingClientSessionRegistryService
        .unregisterTestingClientSessionRegistryService(m_testingClientSessionRegistryService);
  }

}

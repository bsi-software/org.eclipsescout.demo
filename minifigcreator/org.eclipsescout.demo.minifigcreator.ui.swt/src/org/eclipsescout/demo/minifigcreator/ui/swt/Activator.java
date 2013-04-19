package org.eclipsescout.demo.minifigcreator.ui.swt;

import org.eclipse.scout.rt.ui.swt.ISwtEnvironment;
import org.eclipsescout.demo.minifigcreator.client.ClientSession;
import org.eclipsescout.demo.minifigcreator.ui.swt.perspective.Perspective;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

  // the plugin id
  public static final String BUNDLE_ID = "org.eclipsescout.demo.minifigcreator.ui.swt";

  private ISwtEnvironment m_environment;

  // the shared instance
  private static Activator m_bundle;

  @Override
  public void start(BundleContext context) throws Exception {
    m_bundle = this;
    m_environment = new SwtEnvironment(context.getBundle(), Perspective.ID, ClientSession.class);
  }

  @Override
  public void stop(BundleContext context) throws Exception {
    m_bundle = null;
  }

  public static Activator getDefault() {
    return m_bundle;
  }

  public ISwtEnvironment getEnvironment() {
    return m_environment;
  }
}

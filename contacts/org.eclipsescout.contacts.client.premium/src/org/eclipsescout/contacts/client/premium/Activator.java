package org.eclipsescout.contacts.client.premium;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.scout.rt.shared.extension.IExtensionRegistry;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.contacts.client.premium.ui.forms.ContactFormTabExtension;
import org.eclipsescout.contacts.shared.premium.ui.forms.ContactFormTabExtensionData;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

  // The plug-in ID
  public static final String PLUGIN_ID = "org.eclipsescout.contacts.client.premium";

  // The shared instance
  private static Activator plugin;

  /**
   * The constructor
   */
  public Activator() {
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
   */
  @Override
  public void start(BundleContext context) throws Exception {
    super.start(context);
    plugin = this;

    new Job("register extensions") {
      @Override
      protected IStatus run(IProgressMonitor monitor) {
        // register extension so that it becomes active
        SERVICES.getService(IExtensionRegistry.class).register(ContactFormTabExtension.class);
        SERVICES.getService(IExtensionRegistry.class).register(ContactFormTabExtensionData.class);

        return Status.OK_STATUS;
      }
    }.schedule();
  }

  /*
   * (non-Javadoc)
   * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
   */
  @Override
  public void stop(BundleContext context) throws Exception {
    plugin = null;
    super.stop(context);
  }

  /**
   * Returns the shared instance
   *
   * @return the shared instance
   */
  public static Activator getDefault() {
    return plugin;
  }

}

/*******************************************************************************
 * Copyright (c) 2013 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipsescout.demo.minicrm.spec.ui.swt;

import java.security.PrivilegedExceptionAction;

import javax.security.auth.Subject;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.scout.net.NetActivator;
import org.eclipse.scout.rt.spec.client.SpecJob;
import org.eclipse.scout.testing.client.TestingClientSessionRegistryService;
import org.eclipse.scout.testing.client.runner.ScoutClientTestRunner;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipsescout.demo.minicrm.client.ClientSession;
import org.eclipsescout.demo.minicrm.ui.swt.application.ApplicationWorkbenchAdvisor;

/**
 * <h3>Activator</h3> This class controls all aspects of the application's execution
 */
public class SwtSpecApplication implements IApplication {

  private TestingClientSessionRegistryService m_testingClientSessionRegistryService;

@Override
  public Object start(final IApplicationContext context) throws Exception {
	    ScoutClientTestRunner.setDefaultClientSessionClass(ClientSession.class);
	    m_testingClientSessionRegistryService = TestingClientSessionRegistryService
	        .registerTestingClientSessionRegistryService();
    new SpecJob(ClientSession.class, Platform.getProduct()
            .getDefiningBundle().getSymbolicName()).schedule(200);
    return Subject.doAs(new Subject(), new PrivilegedExceptionAction<Object>() {
      @Override
      public Object run() throws Exception {
        return startSecure(context);
      }
    });
  }

  public Integer startSecure(final IApplicationContext context) throws Exception {
    Display display = PlatformUI.createDisplay();
    NetActivator.install();
    if (PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor()) == PlatformUI.RETURN_RESTART) {
      return EXIT_RESTART;
    }
    return EXIT_OK;
  }

  @Override
  public void stop() {
	  TestingClientSessionRegistryService
      .unregisterTestingClientSessionRegistryService(m_testingClientSessionRegistryService);
    final IWorkbench workbench = PlatformUI.getWorkbench();
    if (workbench == null) return;
    final Display display = workbench.getDisplay();
    display.syncExec(new Runnable() {
      @Override
      public void run() {
        if (!display.isDisposed())
        workbench.close();
      }
    });
  }
}

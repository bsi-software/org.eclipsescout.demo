/*******************************************************************************
 * Copyright (c) 2001 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Daniel Wiehl (Business Systems Integration AG) - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.tutorial.jaxws.ui.swt.application;

import java.security.PrivilegedExceptionAction;
import javax.security.auth.Subject;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.scout.net.NetActivator;
import org.eclipse.scout.commons.security.SimplePrincipal;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

/** <h3>Activator</h3>
 *  This class controls all aspects of the application's execution
*/
public class Application implements IApplication {

  @Override
	public Object start(final IApplicationContext context) throws Exception {
    Subject subject=new Subject();
    subject.getPrincipals().add(new SimplePrincipal(System.getProperty("user.name")));
    return Subject.doAs(subject, new PrivilegedExceptionAction<Object>(){
      @Override
      public Object run() throws Exception{
        return startSecure(context);
      }
    });
	}

	public Integer startSecure(final IApplicationContext context)
			throws Exception {
		Display display = PlatformUI.createDisplay();
		//Subject.getSubject(AccessController.getContext()).getPrincipals().add(new NetPrincipal("localhost", "admin", "manager"));
		NetActivator.install();
//    NetActivator.getDefault().addCallbackHandler(new UserPassDialogCallbackHandler(getSwingEnvironment()));
//    if (FenixUpdater.update(getProgressMonitor())==State.RestartRequired){
//      return EXIT_RESTART;
//    }
		if(PlatformUI.createAndRunWorkbench(display,new ApplicationWorkbenchAdvisor())==PlatformUI.RETURN_RESTART) {
			return EXIT_RESTART;
		}
		return EXIT_OK;
	}

	/*
	 * (non-Javadoc)
* @see org.eclipse.equinox.app.IApplication#stop()
	 */
	@Override
	public void stop() {
		final IWorkbench workbench = PlatformUI.getWorkbench();
		if (workbench == null)
			return;
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

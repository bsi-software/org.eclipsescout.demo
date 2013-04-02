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
package org.eclipsescout.demo.minifigcreator.client;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.AbstractClientSession;
import org.eclipse.scout.rt.client.ClientJob;
import org.eclipse.scout.rt.client.servicetunnel.http.HttpServiceTunnel;
import org.eclipse.scout.rt.shared.services.common.code.CODES;
import org.eclipsescout.demo.minifigcreator.client.ui.desktop.Desktop;

public class ClientSession extends AbstractClientSession {

	public ClientSession() {
		super(true);
	}

	/**
	 * @return session in current ThreadContext
	 */
	public static ClientSession get() {
		return ClientJob.getCurrentSession(ClientSession.class);
	}

	@FormData
	public Long getPersonNr() {
		return getSharedContextVariable("personNr", Long.class);
	}

	@Override
	public void execLoadSession() throws ProcessingException {
		setServiceTunnel(new HttpServiceTunnel(this, getBundle().getBundleContext().getProperty("server.url")));

		// pre-load all known code types
		CODES.getAllCodeTypes(org.eclipsescout.demo.minifigcreator.shared.Activator.PLUGIN_ID);

		setDesktop(new Desktop());

		// turn client notification polling on
		// getServiceTunnel().setClientNotificationPollInterval(2000L);
	}

	@Override
	public void execStoreSession() throws ProcessingException {
	}
}

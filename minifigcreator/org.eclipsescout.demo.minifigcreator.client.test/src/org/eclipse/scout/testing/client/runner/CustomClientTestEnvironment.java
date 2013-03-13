package org.eclipse.scout.testing.client.runner;


import org.eclipse.scout.rt.client.IClientSession;
import org.eclipsescout.demo.minifigcreator.client.ClientSession;

public class CustomClientTestEnvironment implements IClientTestEnvironment {

	@Override
	public void installCookieStore() {
	}

	@Override
	public void installNetAuthenticator() {
	}

	@Override
	public Class<? extends IClientSession> getDefaultClientSessionClass() {
		return ClientSession.class;
	}

}

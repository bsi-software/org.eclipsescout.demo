package org.eclipse.scout.testing.server.runner;


import org.eclipse.scout.rt.server.IServerSession;
import org.eclipse.scout.rt.testing.server.runner.IServerTestEnvironment;
import org.eclipsescout.demo.minifigcreator.server.ServerSession;

public class CustomServerTestEnvironment implements IServerTestEnvironment {

	@Override
	public void setup() {
	}

	@Override
	public Class<? extends IServerSession> getDefaultServerSessionClass() {
		return ServerSession.class;
	}

	@Override
	public String getDefaultPrincipalName() {
		return null;
	}

}

package org.eclipse.scout.rt.testing.server.runner;

import org.eclipsescout.demo.minifigcreator.server.ServerSession;

public class CustomServerTestEnvironment implements IServerTestEnvironment {

	@Override
	public void setupGlobalEnvironment() {
		ScoutServerTestRunner.setDefaultServerSessionClass(ServerSession.class);
	}

	@Override
	public void setupInstanceEnvironment() {
	}
}

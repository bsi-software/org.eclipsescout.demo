package org.eclipse.scout.testing.server.runner;


import org.eclipse.scout.rt.testing.server.runner.IServerTestEnvironment;
import org.eclipse.scout.rt.testing.server.runner.ScoutServerTestRunner;
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

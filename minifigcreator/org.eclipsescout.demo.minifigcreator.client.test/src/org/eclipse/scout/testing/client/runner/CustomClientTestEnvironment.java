package org.eclipse.scout.testing.client.runner;

import org.eclipsescout.demo.minifigcreator.client.ClientSession;

public class CustomClientTestEnvironment implements IClientTestEnvironment {

	@Override
	public void setupGlobalEnvironment() {
		ScoutClientTestRunner.setDefaultClientSessionClass(ClientSession.class);
	}

	@Override
	public void setupInstanceEnvironment() {
	}
}

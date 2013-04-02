package org.eclipsescout.demo.minifigcreator.server;

import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.scout.commons.TypeCastUtility;
import org.eclipse.scout.rt.testing.server.JUnitServerJob;
import org.eclipse.scout.rt.testing.shared.ScoutJUnitPluginTestExecutor;

/**
 * Application for running tests. It can be started in two different modes, one for server-side tests and the other for
 * running client-side tests. The following arguments can be set on the command line or as system property,
 * respectively.
 * <p/>
 * <b>Supported arguments</b>
 * <table border="1">
 * <tr>
 * <th>Argument</th>
 * <th>Description</th>
 * <th>Type</th>
 * <th>Default value</th>
 * </tr>
 * <tr>
 * <td>performTestsAndExit</td>
 * <td>Performs all server-side JUnit tests and exits the application.</td>
 * <td>boolean</td>
 * <td>false</td>
 * </tr>
 * </table>
 * <p/>
 * <b>Usage</b>
 * <table>
 * <tr>
 * <td>The format on the command line is</td>
 * <td><code><b>-</b><em>argumentName</em><b>=</b><em>value</em></code></td>
 * </tr>
 * <tr>
 * <td>and the format as system property is</td>
 * <td><code><b>-D</b><em>argumentName</em><b>=</b><em>value</em></code></td>
 * </tr>
 * </table>
 * 
 * @see ScoutJUnitPluginTestExecutor
 */
public class MinifigcreatorProductServerTestApplication implements IApplication {
  private static final String PERFORM_TESTS_AND_EXIT_ARG_NAME = "performTestsAndExit";

  @Override
  public Object start(IApplicationContext context) throws Exception {

    //for setup see CustomServerTestEnvironment

    // add here additional initialization
    if (isExecuteServerSideTests()) {
      new JUnitServerJob().schedule();
    }
    return IApplication.EXIT_OK;
  }

  @Override
  public void stop() {
  }

  private boolean isExecuteServerSideTests() {
    return TypeCastUtility.castValue(getConfigParameter(PERFORM_TESTS_AND_EXIT_ARG_NAME), boolean.class);
  }

  /**
   * Returns the configuration value for the given parameter that is either configured as
   * command line argument or as system property.
   * 
   * @param parameterName
   * @return
   */
  private String getConfigParameter(String parameterName) {
    String commandLineArgumentName = "-" + parameterName + "=";
    for (String arg : Platform.getCommandLineArgs()) {
      if (arg != null && arg.startsWith(commandLineArgumentName)) {
        return arg.substring(commandLineArgumentName.length());
      }
    }
    return System.getProperty(parameterName);
  }
}

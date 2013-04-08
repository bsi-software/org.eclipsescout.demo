package org.eclipse.scout.ibug.ui.rap;

import org.eclipse.scout.ibug.client.ClientSession;
import org.eclipse.scout.rt.ui.rap.AbstractStandaloneRwtEnvironment;

public class StandaloneRwtEnvironment extends AbstractStandaloneRwtEnvironment {

  public StandaloneRwtEnvironment() {
    super(Activator.getDefault().getBundle(), ClientSession.class);
  }
}

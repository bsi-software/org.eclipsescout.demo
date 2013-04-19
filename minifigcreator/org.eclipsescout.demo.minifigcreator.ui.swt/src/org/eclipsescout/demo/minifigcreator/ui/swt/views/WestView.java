package org.eclipsescout.demo.minifigcreator.ui.swt.views;

import org.eclipse.scout.rt.ui.swt.ISwtEnvironment;
import org.eclipse.scout.rt.ui.swt.window.desktop.view.AbstractScoutView;
import org.eclipsescout.demo.minifigcreator.ui.swt.Activator;

public class WestView extends AbstractScoutView {

  public WestView() {
  }

  @Override
  protected ISwtEnvironment getSwtEnvironment() {
    return Activator.getDefault().getEnvironment();
  }
}

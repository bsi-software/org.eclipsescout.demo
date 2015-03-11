package org.eclipsescout.demo.minicrm.server;

import org.eclipse.scout.rt.platform.cdi.IBeanContext;
import org.eclipse.scout.rt.platform.cdi.IBeanContributor;
import org.eclipse.scout.rt.platform.inventory.IClassInventory;

/**
 * Use this class to contribute application specific beans.
 */
public class BeanContributor implements IBeanContributor {

  @Override
  public void contributeBeans(IClassInventory classInventory, IBeanContext context) {
    context.registerClass(ServerSession.class);
  }
}

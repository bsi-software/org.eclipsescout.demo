/*******************************************************************************
 * Copyright (c) 2010 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the BSI CRM Software License v1.0
 * which accompanies this distribution as bsi-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipsescout.demo.bahbah.server.services.custom.security;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.service.AbstractService;
import org.eclipsescout.demo.bahbah.server.util.UserMd5Utility;

/**
 *
 */
public class UserMapService extends AbstractService implements IUserMapService {
  private static final IScoutLogger LOG = ScoutLogManager.getLogger(UserMapService.class);

  private static Map<String, UserBean> s_globalUserMap = null;

  @Override
  public final void publishUserMapInternal() throws ProcessingException {
    publishUserMapImpl(false);
  }

  @Override
  public final void publishUserMap() throws ProcessingException {
    publishUserMapImpl(true);
  }

  protected final synchronized void publishUserMapImpl(boolean doClusterSync) throws ProcessingException {
    s_globalUserMap = Collections.unmodifiableMap(initUserMap());
  }

  protected Map<String, UserBean> initUserMap() throws ProcessingException {
    Map<String, String> users = UserMd5Utility.getUsers();
    Map<String, UserBean> newMap = new HashMap<String, UserBean>();

    Iterator<Entry<String, String>> usersIter = users.entrySet().iterator();
    while (usersIter.hasNext()) {
      Entry<String, String> user = usersIter.next();
      String username = user.getKey();
      String password = user.getValue();
      if (username != null && password != null) {
        newMap.put(username, new UserBean(username, password, true));
      }
    }
    return newMap;
  }

  /**
   * Simply reads the cached global user map (must not load any data).
   */
  public static Map<String, UserBean> getGlobalUserMap() {
    return s_globalUserMap;
  }

}

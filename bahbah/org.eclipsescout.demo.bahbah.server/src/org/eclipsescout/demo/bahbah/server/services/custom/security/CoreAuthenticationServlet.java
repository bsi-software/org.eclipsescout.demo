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

import java.io.IOException;
import java.net.Authenticator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.scout.commons.Base64Utility;
import org.eclipse.scout.commons.CompareUtility;
import org.eclipse.scout.commons.EncryptionUtility;
import org.eclipse.scout.commons.LocaleThreadLocal;
import org.eclipse.scout.commons.logger.IScoutLogger;
import org.eclipse.scout.commons.logger.ScoutLogManager;
import org.eclipse.scout.http.servletfilter.HttpServletEx;

/**
 * This is the /auth servlet that provides for user authentication to internal/local users.
 * It is used by the osgi-external servlet filter CoreUserSecurityFilter
 * <p>
 * This servlet returns {@link HttpServletResponse#SC_OK} or {@link HttpServletResponse#SC_FORBIDDEN}. It never sends
 * status unauthorized to not accidentially trigger {@link Authenticator}s.
 */
public class CoreAuthenticationServlet extends HttpServletEx {
  private static final long serialVersionUID = 1L;
  private static final IScoutLogger LOG = ScoutLogManager.getLogger(CoreAuthenticationServlet.class);

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    res.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
    res.setHeader("Pragma", "no-cache"); //HTTP 1.0
    res.setDateHeader("Expires", 0); //prevents caching at the proxy server
    try {
      String h = req.getHeader("Authorization");
      if (h == null || !h.matches("Basic .*")) {
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return;
      }
      String[] a = new String(Base64Utility.decode(h.substring(6)), "ISO-8859-1").split(":", 2);
      String user = a[0].toLowerCase(LocaleThreadLocal.get());
      String pass = a[1];
      int checkCoreAccount = checkCoreAccount(user, pass);
      if (HttpServletResponse.SC_OK != checkCoreAccount) {
        res.setStatus(checkCoreAccount);
        return;
      }
      //OK
      return;
    }
    catch (Throwable t) {
      LOG.error("Exception in authentication", t);
      res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      return;
    }
  }

  private int checkCoreAccount(String user, String pass) throws Exception {
    if (user == null || pass == null) {
      return HttpServletResponse.SC_UNAUTHORIZED;
    }
    String pwMD5 = Base64Utility.encode(EncryptionUtility.signMD5(pass.getBytes()));
    Map<String, UserBean> userMap = getGlobalUserMap();
    UserBean userInfo = userMap.get(user);
    if (userInfo == null) {
      userInfo = userMap.get(user.toLowerCase(LocaleThreadLocal.get()));
    }
    if (userInfo == null) {
      //delay to make attacks less efficient
      Thread.sleep(1000L);
      return HttpServletResponse.SC_FORBIDDEN;
    }
    if (!userInfo.checkLocalPassword()) {
      return HttpServletResponse.SC_UNAUTHORIZED;
    }
    else if (CompareUtility.notEquals(userInfo.getPassword(), pwMD5)) {
      //delay to make attacks less efficient
      Thread.sleep(1000L);
      return HttpServletResponse.SC_UNAUTHORIZED;
    }
    return HttpServletResponse.SC_OK;

  }

  private Map<String, UserBean> getGlobalUserMap() throws InterruptedException {
    for (int i = 0; i < 60; i++) {
      Map<String, UserBean> map = UserMapService.getGlobalUserMap();
      if (map != null) {
        return map;
      }
      Thread.sleep(1000L);
    }
    throw new IllegalStateException("Global user map is not loaded.");
  }
}

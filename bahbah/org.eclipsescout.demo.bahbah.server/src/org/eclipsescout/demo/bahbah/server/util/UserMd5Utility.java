/*******************************************************************************
 * Copyright (c) 2010 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipsescout.demo.bahbah.server.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.scout.commons.Base64Utility;
import org.eclipse.scout.commons.EncryptionUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.commons.holders.StringArrayHolder;
import org.eclipse.scout.commons.holders.StringHolder;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.demo.bahbah.server.ServerSession;
import org.eclipsescout.demo.bahbah.server.services.custom.security.IUserMapService;
import org.eclipsescout.demo.bahbah.shared.services.code.UserRoleCodeType;
import org.eclipsescout.demo.bahbah.shared.util.SharedUserUtility;

public class UserMd5Utility extends SharedUserUtility {

  private static final String ENCODING = "UTF-8";

  public static boolean createNewUser(String username, String password) throws ProcessingException {
    return createNewUser(username, password, UserRoleCodeType.UserCode.ID);
  }

  public static boolean createNewUser(String username, String password, Integer permission) throws ProcessingException {
    try {
      checkUsername(username);
      checkPassword(password);
      checkPermissionId(permission);

      String pwMD5 = Base64Utility.encode(EncryptionUtility.signMD5(password.getBytes(ENCODING)));

      SQL.insert("INSERT INTO TABUSERS (username, pass, permission_id) VALUES (:username, :pass, :permission)",
          new NVPair("username", username),
          new NVPair("pass", pwMD5),
          new NVPair("permission", permission));

      SERVICES.getService(IUserMapService.class).publishUserMapInternal();

      return true;
    }
    catch (NoSuchAlgorithmException e) {
      throw new ProcessingException("hash algorithm not found", e);
    }
    catch (UnsupportedEncodingException e) {
      throw new ProcessingException("unknown string encoding: " + ENCODING, e);
    }
  }

  public static void resetPassword(Long u_Id, String newPassword) throws ProcessingException {
    try {
      checkPassword(newPassword);
      if (!UserRoleCodeType.AdministratorCode.ID.equals(ServerSession.get().getPermission().getId())) {
        // I am not an administrator -> can only reset my own password
        Long myUserId = Long.parseLong(ServerSession.get().getUserId());
        if (!myUserId.equals(u_Id)) {
          throw new VetoException();
        }
      }

      String pwMD5 = Base64Utility.encode(EncryptionUtility.signMD5(newPassword.getBytes(ENCODING)));

      SQL.update("UPDATE TABUSERS SET pass = :newPass WHERE u_id = :uid", new NVPair("newPass", pwMD5), new NVPair("uid", u_Id));

      SERVICES.getService(IUserMapService.class).publishUserMapInternal();
    }
    catch (NoSuchAlgorithmException e) {
      throw new ProcessingException("hash algorithm not found", e);
    }
    catch (UnsupportedEncodingException e) {
      throw new ProcessingException("unknown string encoding: " + ENCODING, e);
    }
  }

  public static boolean isValidUser(String username, String password) throws ProcessingException {
    try {
      StringHolder passHolder = new StringHolder();
      SQL.selectInto("SELECT pass FROM TABUSERS WHERE UPPER(USERNAME) = UPPER(:username) INTO :pass ",
          new NVPair("username", username),
          new NVPair("pass", passHolder));

      String pass = passHolder.getValue();
      if (pass == null) {
        // user was not found: to prevent time attacks even though check the passwords
        // will always return false
        pass = "c29tZXRoaW5n";
        password = "dummy";
      }
      return areEqual(pass, password);
    }
    catch (NoSuchAlgorithmException e) {
      throw new ProcessingException("hash algorithm not found", e);
    }
    catch (UnsupportedEncodingException e) {
      throw new ProcessingException("unknown string encoding: " + ENCODING, e);
    }
  }

  /**
   * Checks if the given two passwords have equal hashes using the given salt.
   * 
   * @param pass1
   *          String containing the Base64 encoded password hash to check against.
   * @param pass2
   *          String containing the clear text password to check.
   * @param salt
   *          The salt (Base64 encoded) to use for hashing.
   * @return True if the hash of pass2 is equal with pass1 using the given salt.
   * @throws UnsupportedEncodingException
   * @throws NoSuchAlgorithmException
   */
  private static boolean areEqual(String pass1, String pass2) throws UnsupportedEncodingException, NoSuchAlgorithmException {
    byte[] bPass = Base64Utility.decode(pass1);
    byte[] bInput = EncryptionUtility.signMD5(pass2.getBytes(ENCODING));

    return Arrays.equals(bInput, bPass);
  }

  public static Map<String, String> getUsers() throws ProcessingException {
    StringArrayHolder userHolder = new StringArrayHolder();
    StringArrayHolder passHolder = new StringArrayHolder();
    SQL.selectInto("SELECT username, pass FROM TABUSERS INTO :user, :pass ",
        new NVPair("user", userHolder),
        new NVPair("pass", passHolder));

    Map<String, String> users = new HashMap<String, String>();
    for (int i = 0; i < userHolder.getValue().length; i++) {
      users.put(userHolder.getValue()[i], passHolder.getValue()[i]);
    }
    return users;
  }
}

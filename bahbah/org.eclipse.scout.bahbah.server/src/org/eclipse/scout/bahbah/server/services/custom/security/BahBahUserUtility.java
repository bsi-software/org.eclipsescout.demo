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
package org.eclipse.scout.bahbah.server.services.custom.security;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.eclipse.scout.bahbah.shared.services.code.UserRoleCodeType;
import org.eclipse.scout.bahbah.shared.services.process.IPasswordProcessService;
import org.eclipse.scout.bahbah.shared.services.process.IUserProcessService;
import org.eclipse.scout.commons.Base64Utility;
import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.commons.holders.NVPair;
import org.eclipse.scout.commons.holders.StringHolder;
import org.eclipse.scout.rt.server.services.common.jdbc.SQL;
import org.eclipse.scout.rt.shared.TEXTS;

public class BahBahUserUtility {

  private static final String ENCODING = "UTF-8";

  public static boolean createNewUser(String username, String password) throws ProcessingException {
    return createNewUser(username, password, UserRoleCodeType.UserCode.ID);
  }

  public static boolean createNewUser(String username, String password, Integer permission) throws ProcessingException {
    try {
      checkUsername(username);
      checkPassword(password);

      byte[] bSalt = HashUtility.createSalt();
      byte[] bHash = HashUtility.hash(password.getBytes(ENCODING), bSalt);

      String salt = Base64Utility.encode(bSalt);
      String digest = Base64Utility.encode(bHash);

      SQL.insert("INSERT INTO TABUSERS (username, pass, salt, permission_id) VALUES (:username, :pass, :salt, :permission)",
          new NVPair("username", username),
          new NVPair("pass", digest),
          new NVPair("salt", salt),
          new NVPair("permission", permission));

      return true;
    }
    catch (NoSuchAlgorithmException e) {
      throw new ProcessingException("hash algorithm not found", e);
    }
    catch (UnsupportedEncodingException e) {
      throw new ProcessingException("unknown string encoding: " + ENCODING, e);
    }
  }

  public static void checkUsername(String username) throws VetoException {
    if (StringUtility.length(username) < IUserProcessService.MIN_USERNAME_LENGTH) {
      throw new VetoException(TEXTS.get("UsernameMinLength", "" + IUserProcessService.MIN_USERNAME_LENGTH));
    }
  }

  public static void checkPassword(String password) throws VetoException {
    if (StringUtility.length(password) < IPasswordProcessService.MIN_PASSWORD_LENGTH) {
      throw new VetoException(TEXTS.get("PasswordMinLength", "" + IPasswordProcessService.MIN_PASSWORD_LENGTH));
    }
  }

  public static void resetPassword(Long u_Id, String newPassword) throws ProcessingException {
    try {
      checkPassword(newPassword);

      byte[] bSalt = HashUtility.createSalt();
      byte[] bHash = HashUtility.hash(newPassword.getBytes(ENCODING), bSalt);

      String salt = Base64Utility.encode(bSalt);
      String digest = Base64Utility.encode(bHash);

      SQL.update("UPDATE TABUSERS SET pass = :newPass, salt = :newSalt WHERE u_id = :uid", new NVPair("newPass", digest), new NVPair("newSalt", salt), new NVPair("uid", u_Id));
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
      StringHolder saltHolder = new StringHolder();
      SQL.selectInto("SELECT pass, salt FROM TABUSERS WHERE UPPER(USERNAME) = UPPER(:username) INTO :pass, :salt",
          new NVPair("username", username),
          new NVPair("pass", passHolder),
          new NVPair("salt", saltHolder));

      String pass = passHolder.getValue();
      String salt = saltHolder.getValue();
      if (pass == null || salt == null) {
        // user was not found: to prevent time attacks even though check the passwords
        // will always return false
        return areEqual("c29tZXRoaW5n", "dummy", "c29tZXNhbHQ=");
      }
      return areEqual(pass, password, salt);
    }
    catch (NoSuchAlgorithmException e) {
      throw new ProcessingException("hash algorithm not found", e);
    }
    catch (UnsupportedEncodingException e) {
      throw new ProcessingException("unknown string encoding: " + ENCODING, e);
    }
  }

  private static boolean areEqual(String pass1, String pass2, String salt) throws UnsupportedEncodingException, NoSuchAlgorithmException {
    byte[] bPass = Base64Utility.decode(pass1);
    byte[] bSalt = Base64Utility.decode(salt);
    byte[] bInput = HashUtility.hash(pass2.getBytes(ENCODING), bSalt);

    return Arrays.equals(bInput, bPass);
  }
}

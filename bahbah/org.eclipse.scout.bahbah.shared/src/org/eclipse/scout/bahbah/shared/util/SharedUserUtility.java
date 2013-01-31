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
package org.eclipse.scout.bahbah.shared.util;

import org.eclipse.scout.commons.StringUtility;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.shared.TEXTS;

public class SharedUserUtility {

  public static final int MIN_PASSWORD_LENGTH = 3;
  public static final int MIN_USERNAME_LENGTH = 3;

  public static void checkUsername(String username) throws VetoException {
    if (StringUtility.length(username) < MIN_USERNAME_LENGTH) {
      throw new VetoException(TEXTS.get("UsernameMinLength", "" + MIN_USERNAME_LENGTH));
    }
    if (username.contains("@")) {
      throw new VetoException(TEXTS.get("UsernameSpecialChars"));
    }
  }

  public static void checkPassword(String password) throws VetoException {
    if (StringUtility.length(password) < MIN_PASSWORD_LENGTH) {
      throw new VetoException(TEXTS.get("PasswordMinLength", "" + MIN_PASSWORD_LENGTH));
    }
  }
}

package org.eclipsescout.demo.bahbah.server.services.custom.security;

public class UserBean {
  private final String m_username;
  private final String m_password;
  private final boolean m_checkLocalPassword;

  UserBean(String username, String password, boolean checkLocalPassword) {
    m_username = username;
    m_password = password;
    m_checkLocalPassword = checkLocalPassword;
  }

  public String getUsername() {
    return m_username;
  }

  public String getPassword() {
    return m_password;
  }

  public boolean checkLocalPassword() {
    return m_checkLocalPassword;
  }
}

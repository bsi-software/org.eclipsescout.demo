package org.eclipse.scout.rt.demo.shared.services.code;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCode;
import org.eclipse.scout.rt.shared.services.common.code.AbstractCodeType;

public class StatusTextCodeType extends AbstractCodeType<Long> {

  private static final long serialVersionUID = 1L;
  public static final Long ID = 30000L;

  public StatusTextCodeType() throws ProcessingException {
    super();
  }

  @Override
  public Long getId() {
    return ID;
  }

  @Order(10.0)
  public static class FatalCode extends AbstractCode<String> {

    private static final long serialVersionUID = 1L;
    public static final String ID = "Fatal";

    @Override
    protected String getConfiguredBackgroundColor() {
      return "000000";
    }

    @Override
    protected String getConfiguredFont() {
      return "bold italic";
    }

    @Override
    protected String getConfiguredForegroundColor() {
      return "ffffff";
    }

    @Override
    protected String getConfiguredIconId() {
      return org.eclipse.scout.rt.shared.AbstractIcons.StatusError;
    }

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Fatal");
    }

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(20.0)
  public static class ErrorCode extends AbstractCode<String> {

    private static final long serialVersionUID = 1L;
    public static final String ID = "Error";

    @Override
    protected String getConfiguredFont() {
      return "bold";
    }

    @Override
    protected String getConfiguredBackgroundColor() {
      return "ff0000";
    }

    @Override
    protected String getConfiguredIconId() {
      return org.eclipse.scout.rt.shared.AbstractIcons.StatusError;
    }

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Error0");
    }

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(30.0)
  public static class WarningCode extends AbstractCode<String> {

    private static final long serialVersionUID = 1L;
    public static final String ID = "Warning";

    @Override
    protected String getConfiguredFont() {
      return "italic";
    }

    @Override
    protected String getConfiguredBackgroundColor() {
      return "ffff00";
    }

    @Override
    protected String getConfiguredIconId() {
      return org.eclipse.scout.rt.shared.AbstractIcons.StatusWarning;
    }

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Warning");
    }

    @Override
    public String getId() {
      return ID;
    }
  }

  @Order(40.0)
  public static class InfoCode extends AbstractCode<String> {

    private static final long serialVersionUID = 1L;
    public static final String ID = "Info";

    @Override
    protected String getConfiguredBackgroundColor() {
      return "00ff00";
    }

    @Override
    protected String getConfiguredIconId() {
      return org.eclipse.scout.rt.shared.AbstractIcons.StatusInfo;
    }

    @Override
    protected String getConfiguredText() {
      return TEXTS.get("Info0");
    }

    @Override
    public String getId() {
      return ID;
    }
  }
}

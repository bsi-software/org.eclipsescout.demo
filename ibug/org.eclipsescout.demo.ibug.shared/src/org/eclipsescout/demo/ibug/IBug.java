package org.eclipsescout.demo.ibug;

public interface IBug {
  public String getId();

  public String getSeverety();

  public String getPriority();

  public String getTargetMilestone();

  public String getStatus();

  public String getResolution();

  public String getComponent();

  public String getAssignee();

  public String getSummary();

  public String getChanged();

  @Override
  public String toString();

  public int getSortValue();

  public void setId(String id);

  public void setSeverety(String severety);

  public void setPriority(String priority);

  public void setTargetMilestone(String targetMilestone);

  public void setStatus(String status);

  public void setResolution(String resolution);

  public void setComponent(String component);

  public void setAssignee(String assignee);

  public void setSummary(String summary);

  public void setChanged(String changed);

  public void setSortValue(int sortValue);

}

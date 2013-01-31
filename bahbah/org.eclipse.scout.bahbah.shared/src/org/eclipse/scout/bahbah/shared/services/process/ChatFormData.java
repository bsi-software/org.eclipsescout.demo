package org.eclipse.scout.bahbah.shared.services.process;

import java.util.Date;
import org.eclipse.scout.rt.shared.data.form.ValidationRule;
import org.eclipse.scout.rt.shared.data.form.fields.AbstractValueFieldData;
import org.eclipse.scout.rt.shared.data.form.AbstractFormData;
import org.eclipse.scout.rt.shared.data.form.fields.tablefield.AbstractTableFieldData;
import org.eclipse.scout.rt.shared.data.form.properties.AbstractPropertyData;

public class ChatFormData extends AbstractFormData {
  private static final long serialVersionUID = 1L;

  public ChatFormData() {
  }

  public BuddyNameProperty getBuddyNameProperty() {
    return getPropertyByClass(BuddyNameProperty.class);
  }

  /**
   * access method for property BuddyName.
   */
  public String getBuddyName() {
    return getBuddyNameProperty().getValue();
  }

  /**
   * access method for property BuddyName.
   */
  public void setBuddyName(String buddyName) {
    getBuddyNameProperty().setValue(buddyName);
  }

  public UserNameProperty getUserNameProperty() {
    return getPropertyByClass(UserNameProperty.class);
  }

  /**
   * access method for property UserName.
   */
  public String getUserName() {
    return getUserNameProperty().getValue();
  }

  /**
   * access method for property UserName.
   */
  public void setUserName(String userName) {
    getUserNameProperty().setValue(userName);
  }

  public History getHistory() {
    return getFieldByClass(History.class);
  }

  public Message getMessage() {
    return getFieldByClass(Message.class);
  }

  public class BuddyNameProperty extends AbstractPropertyData<String> {
    private static final long serialVersionUID = 1L;

    public BuddyNameProperty() {
    }
  }

  public class UserNameProperty extends AbstractPropertyData<String> {
    private static final long serialVersionUID = 1L;

    public UserNameProperty() {
    }
  }

  public static class History extends AbstractTableFieldData {
    private static final long serialVersionUID = 1L;

    public History() {
    }

    public static final int TYPE_COLUMN_ID = 0;
    public static final int SENDER_COLUMN_ID = 1;
    public static final int RECEIVER_COLUMN_ID = 2;
    public static final int MESSAGE_COLUMN_ID = 3;
    public static final int TIME_COLUMN_ID = 4;

    public void setType(int row, Integer type) {
      setValueInternal(row, TYPE_COLUMN_ID, type);
    }

    public Integer getType(int row) {
      return (Integer) getValueInternal(row, TYPE_COLUMN_ID);
    }

    public void setSender(int row, String sender) {
      setValueInternal(row, SENDER_COLUMN_ID, sender);
    }

    public String getSender(int row) {
      return (String) getValueInternal(row, SENDER_COLUMN_ID);
    }

    public void setReceiver(int row, String receiver) {
      setValueInternal(row, RECEIVER_COLUMN_ID, receiver);
    }

    public String getReceiver(int row) {
      return (String) getValueInternal(row, RECEIVER_COLUMN_ID);
    }

    public void setMessage(int row, String message) {
      setValueInternal(row, MESSAGE_COLUMN_ID, message);
    }

    public String getMessage(int row) {
      return (String) getValueInternal(row, MESSAGE_COLUMN_ID);
    }

    public void setTime(int row, Date time) {
      setValueInternal(row, TIME_COLUMN_ID, time);
    }

    public Date getTime(int row) {
      return (Date) getValueInternal(row, TIME_COLUMN_ID);
    }

    @Override
    public int getColumnCount() {
      return 5;
    }

    @Override
    public Object getValueAt(int row, int column) {
      switch (column) {
        case TYPE_COLUMN_ID:
          return getType(row);
        case SENDER_COLUMN_ID:
          return getSender(row);
        case RECEIVER_COLUMN_ID:
          return getReceiver(row);
        case MESSAGE_COLUMN_ID:
          return getMessage(row);
        case TIME_COLUMN_ID:
          return getTime(row);
        default:
          return null;
      }
    }

    @Override
    public void setValueAt(int row, int column, Object value) {
      switch (column) {
        case TYPE_COLUMN_ID:
          setType(row, (Integer) value);
          break;
        case SENDER_COLUMN_ID:
          setSender(row, (String) value);
          break;
        case RECEIVER_COLUMN_ID:
          setReceiver(row, (String) value);
          break;
        case MESSAGE_COLUMN_ID:
          setMessage(row, (String) value);
          break;
        case TIME_COLUMN_ID:
          setTime(row, (Date) value);
          break;
      }
    }
  }

  public static class Message extends AbstractValueFieldData<String> {
    private static final long serialVersionUID = 1L;

    public Message() {
    }

    /**
     * list of derived validation rules.
     */
    @Override
    protected void initValidationRules(java.util.Map<String, Object> ruleMap) {
      super.initValidationRules(ruleMap);
      ruleMap.put(ValidationRule.MAX_LENGTH, 4000);
    }
  }
}

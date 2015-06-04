/**
 * 
 */
package org.eclipsescout.contacts.shared.ui.desktop.outlines;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 * 
 * @generated
 */
@Generated(value = "org.eclipse.scout.sdk.workspace.dto.pagedata.PageDataDtoUpdateOperation", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class ContactsTablePageData extends AbstractTablePageData {

  private static final long serialVersionUID = 1L;

  public ContactsTablePageData() {
  }

  @Override
  public ContactsTableRowData addRow() {
    return (ContactsTableRowData) super.addRow();
  }

  @Override
  public ContactsTableRowData addRow(int rowState) {
    return (ContactsTableRowData) super.addRow(rowState);
  }

  @Override
  public ContactsTableRowData createRow() {
    return new ContactsTableRowData();
  }

  @Override
  public Class<? extends AbstractTableRowData> getRowType() {
    return ContactsTableRowData.class;
  }

  @Override
  public ContactsTableRowData[] getRows() {
    return (ContactsTableRowData[]) super.getRows();
  }

  @Override
  public ContactsTableRowData rowAt(int index) {
    return (ContactsTableRowData) super.rowAt(index);
  }

  public void setRows(ContactsTableRowData[] rows) {
    super.setRows(rows);
  }

  public static class ContactsTableRowData extends AbstractTableRowData {

    private static final long serialVersionUID = 1L;
    public static final String contactId = "contactId";
    public static final String firstName = "firstName";
    public static final String lastName = "lastName";
    public static final String city = "city";
    public static final String country = "country";
    public static final String phone = "phone";
    public static final String mobile = "mobile";
    public static final String email = "email";
    public static final String company = "company";
    public static final String events = "events";
    private String m_contactId;
    private String m_firstName;
    private String m_lastName;
    private String m_city;
    private String m_country;
    private String m_phone;
    private String m_mobile;
    private String m_email;
    private String m_company;
    private Integer m_events;

    public ContactsTableRowData() {
    }

    /**
     * @return the ContactId
     */
    public String getContactId() {
      return m_contactId;
    }

    /**
     * @param contactId
     *          the ContactId to set
     */
    public void setContactId(String contactId) {
      m_contactId = contactId;
    }

    /**
     * @return the FirstName
     */
    public String getFirstName() {
      return m_firstName;
    }

    /**
     * @param firstName
     *          the FirstName to set
     */
    public void setFirstName(String firstName) {
      m_firstName = firstName;
    }

    /**
     * @return the LastName
     */
    public String getLastName() {
      return m_lastName;
    }

    /**
     * @param lastName
     *          the LastName to set
     */
    public void setLastName(String lastName) {
      m_lastName = lastName;
    }

    /**
     * @return the City
     */
    public String getCity() {
      return m_city;
    }

    /**
     * @param city
     *          the City to set
     */
    public void setCity(String city) {
      m_city = city;
    }

    /**
     * @return the Country
     */
    public String getCountry() {
      return m_country;
    }

    /**
     * @param country
     *          the Country to set
     */
    public void setCountry(String country) {
      m_country = country;
    }

    /**
     * @return the Phone
     */
    public String getPhone() {
      return m_phone;
    }

    /**
     * @param phone
     *          the Phone to set
     */
    public void setPhone(String phone) {
      m_phone = phone;
    }

    /**
     * @return the Mobile
     */
    public String getMobile() {
      return m_mobile;
    }

    /**
     * @param mobile
     *          the Mobile to set
     */
    public void setMobile(String mobile) {
      m_mobile = mobile;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
      return m_email;
    }

    /**
     * @param email
     *          the Email to set
     */
    public void setEmail(String email) {
      m_email = email;
    }

    /**
     * @return the Company
     */
    public String getCompany() {
      return m_company;
    }

    /**
     * @param company
     *          the Company to set
     */
    public void setCompany(String company) {
      m_company = company;
    }

    /**
     * @return the Events
     */
    public Integer getEvents() {
      return m_events;
    }

    /**
     * @param events
     *          the Events to set
     */
    public void setEvents(Integer events) {
      m_events = events;
    }
  }
}
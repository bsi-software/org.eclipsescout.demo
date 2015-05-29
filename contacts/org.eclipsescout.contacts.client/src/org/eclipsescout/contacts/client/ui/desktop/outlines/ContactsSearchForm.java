/**
 *
 */
package org.eclipsescout.contacts.client.ui.desktop.outlines;

import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractResetButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.eclipsescout.contacts.client.ui.desktop.outlines.ContactsSearchForm.MainBox.ResetButton;
import org.eclipsescout.contacts.client.ui.desktop.outlines.ContactsSearchForm.MainBox.SearchButton;
import org.eclipsescout.contacts.client.ui.desktop.outlines.ContactsSearchForm.MainBox.TabBox;
import org.eclipsescout.contacts.client.ui.desktop.outlines.ContactsSearchForm.MainBox.TabBox.FieldBox;
import org.eclipsescout.contacts.client.ui.desktop.outlines.ContactsSearchForm.MainBox.TabBox.FieldBox.CompanyField;
import org.eclipsescout.contacts.client.ui.desktop.outlines.ContactsSearchForm.MainBox.TabBox.FieldBox.FirstNameField;
import org.eclipsescout.contacts.client.ui.desktop.outlines.ContactsSearchForm.MainBox.TabBox.FieldBox.LastNameField;
import org.eclipsescout.contacts.client.ui.desktop.outlines.ContactsSearchForm.MainBox.TabBox.FieldBox.LocationBox;
import org.eclipsescout.contacts.client.ui.desktop.outlines.ContactsSearchForm.MainBox.TabBox.FieldBox.LocationBox.CityField;
import org.eclipsescout.contacts.client.ui.desktop.outlines.ContactsSearchForm.MainBox.TabBox.FieldBox.LocationBox.CountryField;
import org.eclipsescout.contacts.client.ui.forms.ContactForm.MainBox.DetailsBox.ContactDetailsBox.EmailField;
import org.eclipsescout.contacts.client.ui.forms.ContactForm.MainBox.DetailsBox.ContactDetailsBox.MobileField;
import org.eclipsescout.contacts.client.ui.forms.ContactForm.MainBox.DetailsBox.ContactDetailsBox.PhoneField;
import org.eclipsescout.contacts.shared.services.lookup.CompanyLookupCall;
import org.eclipsescout.contacts.shared.ui.desktop.outlines.ContactsSearchFormData;

/**
 * @author mzi
 */
@FormData(value = ContactsSearchFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class ContactsSearchForm extends AbstractSearchForm {

  /**
   * @throws org.eclipse.scout.commons.exception.ProcessingException
   */
  public ContactsSearchForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Contacts");
  }

  @Override
  protected void execResetSearchFilter(SearchFilter searchFilter) throws ProcessingException {
    super.execResetSearchFilter(searchFilter);
    ContactsSearchFormData formData = new ContactsSearchFormData();
    exportFormData(formData);
    searchFilter.setFormData(formData);
  }

  @Override
  public void startSearch() throws ProcessingException {
    startInternal(new SearchHandler());
  }

  /**
   * @return the CityField
   */
  public CityField getCityField() {
    return getFieldByClass(CityField.class);
  }

  /**
   * @return the CompanyField
   */
  public CompanyField getCompanyField() {
    return getFieldByClass(CompanyField.class);
  }

  /**
   * @return the CountryField
   */
  public CountryField getCountryField() {
    return getFieldByClass(CountryField.class);
  }

  /**
   * @return the EmailField
   */
  public EmailField getEmailField() {
    return getFieldByClass(EmailField.class);
  }

  /**
   * @return the FieldBox
   */
  public FieldBox getFieldBox() {
    return getFieldByClass(FieldBox.class);
  }

  /**
   * @return the FirstNameField
   */
  public FirstNameField getFirstNameField() {
    return getFieldByClass(FirstNameField.class);
  }

  /**
   * @return the LastNameField
   */
  public LastNameField getLastNameField() {
    return getFieldByClass(LastNameField.class);
  }

  /**
   * @return the LocationBox
   */
  public LocationBox getLocationBox() {
    return getFieldByClass(LocationBox.class);
  }

  /**
   * @return the MainBox
   */
  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  /**
   * @return the MobileField
   */
  public MobileField getMobileField() {
    return getFieldByClass(MobileField.class);
  }

  /**
   * @return the PhoneField
   */
  public PhoneField getPhoneField() {
    return getFieldByClass(PhoneField.class);
  }

  /**
   * @return the ResetButton
   */
  public ResetButton getResetButton() {
    return getFieldByClass(ResetButton.class);
  }

  /**
   * @return the SearchButton
   */
  public SearchButton getSearchButton() {
    return getFieldByClass(SearchButton.class);
  }

  /**
   * @return the TabBox
   */
  public TabBox getTabBox() {
    return getFieldByClass(TabBox.class);
  }

  @Order(1000.0)
  public class MainBox extends AbstractGroupBox {

    @Order(1000.0)
    public class TabBox extends AbstractTabBox {

      @Order(1000.0)
      public class FieldBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("searchCriteria");
        }

        @Order(1000.0)
        public class FirstNameField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("FirstName");
          }
        }

        @Order(2000.0)
        public class LastNameField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("LastName");
          }
        }

        @Order(2500.0)
        public class LocationBox extends AbstractSequenceBox {

          @Override
          protected boolean getConfiguredAutoCheckFromTo() {
            return false;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Location");
          }

          @Order(1000.0)
          public class CityField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("City");
            }

            @Override
            protected int getConfiguredLabelPosition() {
              return LABEL_POSITION_ON_FIELD;
            }
          }

          @Order(2000.0)
          public class CountryField extends AbstractStringField {

            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Country");
            }

            @Override
            protected int getConfiguredLabelPosition() {
              return LABEL_POSITION_ON_FIELD;
            }
          }
        }

        @Order(3750.0)
        public class CompanyField extends AbstractSmartField<String> {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Company");
          }

          @Override
          protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
            return CompanyLookupCall.class;
          }
        }
      }
    }

    @Order(8000.0)
    public class ResetButton extends AbstractResetButton {
    }

    @Order(9000.0)
    public class SearchButton extends AbstractSearchButton {
    }
  }

  public class SearchHandler extends AbstractFormHandler {
  }
}

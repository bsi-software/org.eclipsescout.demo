package org.eclipse.scout.rt.bsi.demo.client.ui.forms;

import java.io.IOException;
import java.util.HashMap;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.scout.commons.IOUtility;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.dnd.ImageTransferObject;
import org.eclipse.scout.commons.dnd.TransferObject;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.commons.exception.VetoException;
import org.eclipse.scout.rt.bsi.demo.client.Activator;
import org.eclipse.scout.rt.bsi.demo.client.services.lookup.PersonLookupCall;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.CloseButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ExistingCompaniesBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ExistingCompaniesBox.CompanyField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ExistingCompaniesBox.ExistingCompanies1PlaceholderField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ExistingCompaniesBox.ExistingCompanies2PlaceholderField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ExistingPersonsBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ExistingPersonsBox.ExistingPersons1PlaceholderField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ExistingPersonsBox.ExistingPersons2PlaceholderField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ExistingPersonsBox.PersonField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.FirstnameBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.FirstnameBox.ExistingFirstnameField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.FirstnameBox.FirstnameGroup;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.FirstnameBox.FirstnameGroup.ExistingFirstnameButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.FirstnameBox.FirstnameGroup.NewFirstnameButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.FirstnameBox.NewFirstnameField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.HeadingBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.HeadingBox.ExistingValuesField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.HeadingBox.HeadingPlaceholderField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.HeadingBox.NewValuesField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.LastnameBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.LastnameBox.ExistingLastnameField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.LastnameBox.LastnameGroup;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.LastnameBox.LastnameGroup.ExistingLastnameButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.LastnameBox.LastnameGroup.NewLastnameButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.LastnameBox.NewLastnameField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.LogoBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.LogoBox.ExistingLogoField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.LogoBox.LogoGroup;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.LogoBox.LogoGroup.ExistingLogoButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.LogoBox.LogoGroup.NewLogoButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.LogoBox.NewLogoField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.NameBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.NameBox.ExistingNameField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.NameBox.NameGroup;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.NameBox.NameGroup.ExistingNameButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.NameBox.NameGroup.NewNameButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.NameBox.NewNameField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.SalutationBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.SalutationBox.ExistingSalutationField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.SalutationBox.NewSalutationField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.SalutationBox.SalutationGroup;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.SalutationBox.SalutationGroup.ExistingSalutationButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.SalutationBox.SalutationGroup.NewSalutationButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ShortnameBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ShortnameBox.ExistingShortnameField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ShortnameBox.NewShortnameField;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ShortnameBox.ShortnameGroup;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ShortnameBox.ShortnameGroup.ExistingShortnameButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.DataBox.ShortnameBox.ShortnameGroup.NewShortnameButton;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.OcrBox;
import org.eclipse.scout.rt.bsi.demo.client.ui.forms.OcrForm.MainBox.OcrBox.OcrField;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractRadioButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagebox.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.labelfield.AbstractLabelField;
import org.eclipse.scout.rt.client.ui.form.fields.placeholder.AbstractPlaceholderField;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.ocr.client.AbstractOcrField;
import org.eclipse.scout.rt.ocr.core.IOcrService;
import org.eclipse.scout.rt.ocr.core.OcrServiceEvent;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;
import org.eclipse.scout.rt.shared.services.lookup.LookupRow;
import org.eclipse.scout.service.SERVICES;

public class OcrForm extends AbstractForm implements IPageForm {

  public OcrServiceEvent m_ocrServiceEvent;

  public OcrForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Ocr");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public CompanyField getCompanyField() {
    return getFieldByClass(CompanyField.class);
  }

  public DataBox getDataBox() {
    return getFieldByClass(DataBox.class);
  }

  public ExistingCompanies2PlaceholderField getExistingCompanies2PlaceholderField() {
    return getFieldByClass(ExistingCompanies2PlaceholderField.class);
  }

  public ExistingCompaniesBox getExistingCompaniesBox() {
    return getFieldByClass(ExistingCompaniesBox.class);
  }

  public ExistingCompanies1PlaceholderField getExistingCompanies1PlaceholderField() {
    return getFieldByClass(ExistingCompanies1PlaceholderField.class);
  }

  public ExistingFirstnameButton getExistingFirstnameButton() {
    return getFieldByClass(ExistingFirstnameButton.class);
  }

  public ExistingFirstnameField getExistingFirstnameField() {
    return getFieldByClass(ExistingFirstnameField.class);
  }

  public ExistingLastnameButton getExistingLastnameButton() {
    return getFieldByClass(ExistingLastnameButton.class);
  }

  public ExistingLastnameField getExistingLastnameField() {
    return getFieldByClass(ExistingLastnameField.class);
  }

  public ExistingLogoButton getExistingLogoButton() {
    return getFieldByClass(ExistingLogoButton.class);
  }

  public ExistingLogoField getExistingLogoField() {
    return getFieldByClass(ExistingLogoField.class);
  }

  public ExistingNameButton getExistingNameButton() {
    return getFieldByClass(ExistingNameButton.class);
  }

  public ExistingNameField getExistingNameField() {
    return getFieldByClass(ExistingNameField.class);
  }

  public ExistingPersons2PlaceholderField getExistingPersons2PlaceholderField() {
    return getFieldByClass(ExistingPersons2PlaceholderField.class);
  }

  public ExistingPersonsBox getExistingPersonsBox() {
    return getFieldByClass(ExistingPersonsBox.class);
  }

  public ExistingPersons1PlaceholderField getExistingPersons1PlaceholderField() {
    return getFieldByClass(ExistingPersons1PlaceholderField.class);
  }

  public ExistingSalutationButton getExistingSalutationButton() {
    return getFieldByClass(ExistingSalutationButton.class);
  }

  public ExistingSalutationField getExistingSalutationField() {
    return getFieldByClass(ExistingSalutationField.class);
  }

  public ExistingShortnameButton getExistingShortnameButton() {
    return getFieldByClass(ExistingShortnameButton.class);
  }

  public ExistingShortnameField getExistingShortnameField() {
    return getFieldByClass(ExistingShortnameField.class);
  }

  public ExistingValuesField getExistingValuesField() {
    return getFieldByClass(ExistingValuesField.class);
  }

  public FirstnameBox getFirstnameBox() {
    return getFieldByClass(FirstnameBox.class);
  }

  public FirstnameGroup getFirstnameGroup() {
    return getFieldByClass(FirstnameGroup.class);
  }

  public HeadingBox getHeadingBox() {
    return getFieldByClass(HeadingBox.class);
  }

  public HeadingPlaceholderField getHeadingPlaceholderField() {
    return getFieldByClass(HeadingPlaceholderField.class);
  }

  public LastnameBox getLastnameBox() {
    return getFieldByClass(LastnameBox.class);
  }

  public LastnameGroup getLastnameGroup() {
    return getFieldByClass(LastnameGroup.class);
  }

  public LogoBox getLogoBox() {
    return getFieldByClass(LogoBox.class);
  }

  public LogoGroup getLogoGroup() {
    return getFieldByClass(LogoGroup.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public NameBox getNameBox() {
    return getFieldByClass(NameBox.class);
  }

  public NameGroup getNameGroup() {
    return getFieldByClass(NameGroup.class);
  }

  public NewFirstnameButton getNewFirstnameButton() {
    return getFieldByClass(NewFirstnameButton.class);
  }

  public NewFirstnameField getNewFirstnameField() {
    return getFieldByClass(NewFirstnameField.class);
  }

  public NewLastnameButton getNewLastnameButton() {
    return getFieldByClass(NewLastnameButton.class);
  }

  public NewLastnameField getNewLastnameField() {
    return getFieldByClass(NewLastnameField.class);
  }

  public NewLogoButton getNewLogoButton() {
    return getFieldByClass(NewLogoButton.class);
  }

  public NewLogoField getNewLogoField() {
    return getFieldByClass(NewLogoField.class);
  }

  public NewNameButton getNewNameButton() {
    return getFieldByClass(NewNameButton.class);
  }

  public NewNameField getNewNameField() {
    return getFieldByClass(NewNameField.class);
  }

  public NewSalutationButton getNewSalutationButton() {
    return getFieldByClass(NewSalutationButton.class);
  }

  public NewSalutationField getNewSalutationField() {
    return getFieldByClass(NewSalutationField.class);
  }

  public NewShortnameButton getNewShortnameButton() {
    return getFieldByClass(NewShortnameButton.class);
  }

  public NewShortnameField getNewShortnameField() {
    return getFieldByClass(NewShortnameField.class);
  }

  public NewValuesField getNewValuesField() {
    return getFieldByClass(NewValuesField.class);
  }

  public OcrBox getOcrBox() {
    return getFieldByClass(OcrBox.class);
  }

  public OcrField getOcrField() {
    return getFieldByClass(OcrField.class);
  }

  public PersonField getPersonField() {
    return getFieldByClass(PersonField.class);
  }

  public SalutationBox getSalutationBox() {
    return getFieldByClass(SalutationBox.class);
  }

  public SalutationGroup getSalutationGroup() {
    return getFieldByClass(SalutationGroup.class);
  }

  public ShortnameBox getShortnameBox() {
    return getFieldByClass(ShortnameBox.class);
  }

  public ShortnameGroup getShortnameGroup() {
    return getFieldByClass(ShortnameGroup.class);
  }

  private void updateRequiredFields() {
    // person
    boolean hasPerson = (getPersonField().getValue() != null);
    getNewLastnameField().setMandatory(!hasPerson || getNewLastnameButton().isSelected());
    getNewSalutationField().setMandatory(!hasPerson || getNewSalutationButton().isSelected());
    // company
    boolean hasCompany = (getCompanyField().getValue() != null);
    getNewShortnameField().setMandatory(!hasCompany || getNewShortnameButton().isSelected());
    getNewNameField().setMandatory(!hasCompany || getNewNameButton().isSelected());
  }

  private void handlePersonSelected() {
    // XXX fill person data
    // XXX set company
    // XXX set address
    updateRequiredFields();
  }

  private void handleCompanySelected() {
    // XXX fill company data
    // XXX set address if it is null
    updateRequiredFields();
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class DataBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 1;
      }

      @Override
      protected int getConfiguredGridW() {
        return 1;
      }

      @Order(10.0)
      public class HeadingBox extends AbstractSequenceBox {

        @Order(10.0)
        public class ExistingValuesField extends AbstractLabelField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected void execInitField() throws ProcessingException {
            this.setValue(TEXTS.get("ExistingValues"));
          }
        }

        @Order(20.0)
        public class HeadingPlaceholderField extends AbstractPlaceholderField {

          @Override
          protected double getConfiguredGridWeightX() {
            return 0.0;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected int getConfiguredWidthInPixel() {
            return 40;
          }
        }

        @Order(30.0)
        public class NewValuesField extends AbstractLabelField {

          @Override
          protected void execInitField() throws ProcessingException {
            this.setValue(TEXTS.get("NewValues"));
          }
        }
      }

      @Order(20.0)
      public class ExistingPersonsBox extends AbstractSequenceBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ExistingPersons");
        }

        @Order(10.0)
        public class PersonField extends AbstractSmartField<Long> {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected Class<? extends LookupCall> getConfiguredLookupCall() {
            return PersonLookupCall.class;
          }
        }

        @Order(20.0)
        public class ExistingPersons1PlaceholderField extends AbstractPlaceholderField {

          @Override
          protected double getConfiguredGridWeightX() {
            return 0.0;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected int getConfiguredWidthInPixel() {
            return 40;
          }
        }

        @Order(30.0)
        public class ExistingPersons2PlaceholderField extends AbstractPlaceholderField {
        }
      }

      @Order(30.0)
      public class SalutationBox extends AbstractSequenceBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Salutation");
        }

        @Order(10.0)
        public class ExistingSalutationField extends AbstractStringField {

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }
        }

        @Order(20.0)
        public class SalutationGroup extends AbstractRadioButtonGroup<Long> {

          @Override
          protected double getConfiguredGridWeightX() {
            return 0.0;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected int getConfiguredWidthInPixel() {
            return 40;
          }

          @Order(10.0)
          public class ExistingSalutationButton extends AbstractRadioButton {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }

          @Order(20.0)
          public class NewSalutationButton extends AbstractRadioButton {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }
        }

        @Order(30.0)
        public class NewSalutationField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected void execChangedValue() throws ProcessingException {
            getNewSalutationButton().setSelected(true);
          }
        }
      }

      @Order(40.0)
      public class FirstnameBox extends AbstractSequenceBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Firstname");
        }

        @Order(10.0)
        public class ExistingFirstnameField extends AbstractStringField {

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }
        }

        @Order(20.0)
        public class FirstnameGroup extends AbstractRadioButtonGroup<Long> {

          @Override
          protected double getConfiguredGridWeightX() {
            return 0.0;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected int getConfiguredWidthInPixel() {
            return 40;
          }

          @Order(10.0)
          public class ExistingFirstnameButton extends AbstractRadioButton {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }

          @Order(20.0)
          public class NewFirstnameButton extends AbstractRadioButton {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }
        }

        @Order(30.0)
        public class NewFirstnameField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected void execChangedValue() throws ProcessingException {
            getNewFirstnameButton().setSelected(true);
          }
        }
      }

      @Order(50.0)
      public class LastnameBox extends AbstractSequenceBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Lastname");
        }

        @Order(10.0)
        public class ExistingLastnameField extends AbstractStringField {

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }
        }

        @Order(20.0)
        public class LastnameGroup extends AbstractRadioButtonGroup<Long> {

          @Override
          protected double getConfiguredGridWeightX() {
            return 0.0;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected int getConfiguredWidthInPixel() {
            return 40;
          }

          @Order(10.0)
          public class ExistingLastnameButton extends AbstractRadioButton {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }

          @Order(20.0)
          public class NewLastnameButton extends AbstractRadioButton {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }
        }

        @Order(30.0)
        public class NewLastnameField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected void execChangedValue() throws ProcessingException {
            getNewLastnameButton().setSelected(true);
          }
        }
      }

      @Order(60.0)
      public class ExistingCompaniesBox extends AbstractSequenceBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("ExistingCompanies");
        }

        @Order(10.0)
        public class CompanyField extends AbstractSmartField<Long> {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }
        }

        @Order(20.0)
        public class ExistingCompanies1PlaceholderField extends AbstractPlaceholderField {

          @Override
          protected double getConfiguredGridWeightX() {
            return 0.0;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected int getConfiguredWidthInPixel() {
            return 40;
          }
        }

        @Order(30.0)
        public class ExistingCompanies2PlaceholderField extends AbstractPlaceholderField {
        }
      }

      @Order(70.0)
      public class ShortnameBox extends AbstractSequenceBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Shortname");
        }

        @Order(10.0)
        public class ExistingShortnameField extends AbstractStringField {

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }
        }

        @Order(20.0)
        public class ShortnameGroup extends AbstractRadioButtonGroup<Long> {

          @Override
          protected double getConfiguredGridWeightX() {
            return 0.0;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected int getConfiguredWidthInPixel() {
            return 40;
          }

          @Order(10.0)
          public class ExistingShortnameButton extends AbstractRadioButton {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }

          @Order(20.0)
          public class NewShortnameButton extends AbstractRadioButton {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }
        }

        @Order(30.0)
        public class NewShortnameField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected void execChangedValue() throws ProcessingException {
            getNewShortnameButton().setSelected(true);
          }
        }
      }

      @Order(80.0)
      public class NameBox extends AbstractSequenceBox {

        @Order(10.0)
        public class ExistingNameField extends AbstractStringField {

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }
        }

        @Order(20.0)
        public class NameGroup extends AbstractRadioButtonGroup<Long> {

          @Override
          protected double getConfiguredGridWeightX() {
            return 0.0;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected int getConfiguredWidthInPixel() {
            return 40;
          }

          @Order(10.0)
          public class ExistingNameButton extends AbstractRadioButton {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }

          @Order(20.0)
          public class NewNameButton extends AbstractRadioButton {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }
        }

        @Order(30.0)
        public class NewNameField extends AbstractStringField {

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected void execChangedValue() throws ProcessingException {
            getNewNameButton().setSelected(true);
          }
        }
      }

      @Order(90.0)
      public class LogoBox extends AbstractSequenceBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Logo");
        }

        @Order(10.0)
        public class ExistingLogoField extends AbstractImageField {

          @Override
          protected boolean getConfiguredAutoFit() {
            return true;
          }

          @Override
          protected boolean getConfiguredEnabled() {
            return false;
          }

          @Override
          protected int getConfiguredGridH() {
            return 2;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }
        }

        @Order(20.0)
        public class LogoGroup extends AbstractRadioButtonGroup<Long> {

          @Override
          protected double getConfiguredGridWeightX() {
            return 0.0;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected int getConfiguredWidthInPixel() {
            return 40;
          }

          @Order(10.0)
          public class NewLogoButton extends AbstractRadioButton {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }

          @Order(20.0)
          public class ExistingLogoButton extends AbstractRadioButton {

            @Override
            protected boolean getConfiguredLabelVisible() {
              return false;
            }
          }
        }

        @Order(30.0)
        public class NewLogoField extends AbstractImageField {

          @Override
          protected int getConfiguredGridH() {
            return 2;
          }

          @Override
          protected boolean getConfiguredLabelVisible() {
            return false;
          }

          @Override
          protected boolean getConfiguredAutoFit() {
            return true;
          }

          @Override
          protected int getConfiguredDropType() {
            return TYPE_IMAGE_TRANSFER;
          }

          @Override
          protected void execDropRequest(TransferObject transferObject) throws ProcessingException {
            if (transferObject.isImage()) {
              setImage(((ImageTransferObject) transferObject).getImage());
              getNewLogoButton().setSelected(true);
            }
          }
        }
      }
    }

    @Order(30.0)
    public class OcrBox extends AbstractGroupBox {

      @Override
      protected int getConfiguredGridColumnCount() {
        return 1;
      }

      @Override
      protected int getConfiguredGridW() {
        return 1;
      }

      @Order(10.0)
      public class OcrField extends AbstractOcrField {

        @Override
        protected int getConfiguredGridH() {
          return 20;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("OcrField");
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }
      }
    }

    @Order(40.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() throws ProcessingException {
      try {
        byte[] imageData = IOUtility.getContent(FileLocator.openStream(Activator.getDefault().getBundle(), new Path("resources/scans/sample.png"), true));
        String overlayXml = new String(IOUtility.getContent(FileLocator.openStream(Activator.getDefault().getBundle(), new Path("resources/scans/ocr_overlay.xml"), true)));
        HashMap<String, String> tags = new HashMap<String, String>();
        tags.put(IOcrService.TAG_FIRST_NAME, "Jonny");
        tags.put(IOcrService.TAG_LAST_NAME, "Sample");
        m_ocrServiceEvent = new OcrServiceEvent(SERVICES.getService(IOcrService.class), OcrServiceEvent.TYPE_NEW_DOCUMENT, imageData, null, overlayXml, tags);
//        m_ocrServiceEvent = new OcrServiceEvent(SERVICES.getService(IOcrService.class), OcrServiceEvent.TYPE_NEW_DOCUMENT, imageData, null, null, tags);
      }
      catch (IOException e1) {
        throw new ProcessingException("loading sample.png", e1);
      }
      //
      if (m_ocrServiceEvent == null) {
        throw new VetoException("Missing OCR data");
      }
      updateRequiredFields();
      //
      getOcrField().setImageBinaryData(m_ocrServiceEvent.getImage());
      getOcrField().setOverlayXml(m_ocrServiceEvent.getOverlayXml());
      String firstName = m_ocrServiceEvent.getTags().get(IOcrService.TAG_FIRST_NAME);
      String lastName = m_ocrServiceEvent.getTags().get(IOcrService.TAG_LAST_NAME);
      if (firstName != null && lastName != null) {
        LookupRow[] a = getPersonField().callTextLookup(firstName + "*" + lastName, 2);
        if (a.length == 1) {
          getPersonField().setValue(((Number) a[0].getKey()).longValue());
        }
      }
    }
  }
}

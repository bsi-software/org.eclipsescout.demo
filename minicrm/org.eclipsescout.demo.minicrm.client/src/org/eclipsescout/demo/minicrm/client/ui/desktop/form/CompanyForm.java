/*******************************************************************************
 * Copyright (c) 2013 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipsescout.demo.minicrm.client.ui.desktop.form;

import org.eclipse.scout.commons.CompareUtility;
import org.eclipse.scout.commons.annotations.ClassId;
import org.eclipse.scout.commons.annotations.FormData;
import org.eclipse.scout.commons.annotations.FormData.SdkCommand;
import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBooleanColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.IValueField;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.client.ui.messagebox.MessageBox;
import org.eclipse.scout.rt.extension.client.ui.basic.table.AbstractExtensibleTable;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.service.SERVICES;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm.MainBox.CancelButton;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm.MainBox.CompanyTypeGroup;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm.MainBox.DetailsBox;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm.MainBox.DetailsBox.EBox;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm.MainBox.DetailsBox.EBox.GField;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm.MainBox.DetailsBox.FBox;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm.MainBox.DetailsBox.FBox.HField;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm.MainBox.DetailsBox.FBox.IField;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm.MainBox.FooGroup.CompanyRatingField;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm.MainBox.FooGroup.TableField;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm.MainBox.NameField;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm.MainBox.OkButton;
import org.eclipsescout.demo.minicrm.client.ui.desktop.form.CompanyForm.MainBox.ShortNameField;
import org.eclipsescout.demo.minicrm.shared.services.code.CompanyRatingCodeType;
import org.eclipsescout.demo.minicrm.shared.services.code.CompanyTypeCodeType;
import org.eclipsescout.demo.minicrm.shared.ui.desktop.form.CompanyFormData;
import org.eclipsescout.demo.minicrm.shared.ui.desktop.form.ICompanyService;
import org.eclipsescout.demo.minicrm.shared.ui.desktop.form.UpdateCompanyPermission;

@ClassId("721c3f5f-bd28-41e4-a5f0-d78891034485")
@FormData(value = CompanyFormData.class, sdkCommand = SdkCommand.CREATE)
public class CompanyForm extends AbstractForm {

  private Long companyNr;

  public CompanyForm() throws ProcessingException {
    super();
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Company");
  }

  public CancelButton getCancelButton() {
    return getFieldByClass(CancelButton.class);
  }

  @FormData
  public Long getCompanyNr() {
    return companyNr;
  }

  @FormData
  public void setCompanyNr(Long companyNr) {
    this.companyNr = companyNr;
  }

  public void startModify() throws ProcessingException {
    startInternal(new ModifyHandler());
  }

  public void startNew() throws ProcessingException {
    startInternal(new NewHandler());
  }

  public CompanyRatingField getCompanyRatingField() {
    return getFieldByClass(CompanyRatingField.class);
  }

  public CompanyTypeGroup getCompanyTypeGroup() {
    return getFieldByClass(CompanyTypeGroup.class);
  }

  public DetailsBox getDetailsBox(){
    return getFieldByClass(DetailsBox.class);
  }

  public EBox getEBox(){
    return getFieldByClass(EBox.class);
  }

  public FBox getFBox(){
    return getFieldByClass(FBox.class);
  }

  public GField getGField(){
    return getFieldByClass(GField.class);
  }

  public HField getHField(){
    return getFieldByClass(HField.class);
  }

  public IField getIField(){
    return getFieldByClass(IField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public NameField getNameField() {
    return getFieldByClass(NameField.class);
  }

  public OkButton getOkButton() {
    return getFieldByClass(OkButton.class);
  }

  public ShortNameField getShortNameField() {
    return getFieldByClass(ShortNameField.class);
  }

  public TableField getTableField() {
    return getFieldByClass(TableField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class NameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Name");
      }
    }

    @Order(20.0)
    public class ShortNameField extends AbstractStringField {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("ShortName");
      }
    }

    @Order(30.0)
    public class CompanyTypeGroup extends AbstractRadioButtonGroup<Long> {

      @Override
      protected Class<? extends ICodeType> getConfiguredCodeType() {
        return CompanyTypeCodeType.class;
      }

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("CompanyType");
      }
    }

    @ClassId("e1a0d097-e8c6-4d41-b394-20d6318a27cf")
    @Order(40.0)
    public class FooGroup extends AbstractGroupBox {

      @Override
      protected String getConfiguredLabel() {
        return "Foo";
      }

      @ClassId("5e9a1c6a-3cb0-4b03-975e-c4878cf070a7")
      @Order(40.0)
      public class CompanyRatingField extends AbstractSmartField<Long> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("CompanyRating");
        }

        @Override
        protected Class<? extends ICodeType<?>> getConfiguredCodeType() {
          return CompanyRatingCodeType.class;
        }

        @Override
        protected Class<? extends IValueField> getConfiguredMasterField() {
          return CompanyTypeGroup.class;
        }

        @Override
        protected boolean getConfiguredMasterRequired() {
          return true;
        }

        @Override
        protected boolean getConfiguredVisible() {
          return false;
        }

        @Override
        protected void execChangedMasterValue(Object newMasterValue) throws ProcessingException {

          if (CompareUtility.equals(getCompanyTypeGroup().getValue(), CompanyTypeCodeType.CustomerCode.ID)) {
            setEnabled(true);
            setVisible(true);
          }
          else {
            setEnabled(false);
            setVisible(false);
            setValue(null);
          }
        }

        public class ShowCodePopupMenu extends AbstractMenu {
          @Override
          protected boolean getConfiguredInheritAccessibility() {
            return false;
          }

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("ShowCode_");
          }

          @Override
          protected void execAction() throws ProcessingException {
            MessageBox.showOkMessage("Code", "", "Code is: " + getCompanyRatingField().getValue());
          }
        }
      }

      @Order(50.0)
      public class TableField extends AbstractTableField {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("AorB");
        }

        @Override
        protected int getConfiguredGridH() {
          return 3;
        }

        @Order(10.0)
        public class Table extends AbstractExtensibleTable {

          public BColumn getBColumn() {
            return getColumnSet().getColumnByClass(BColumn.class);
          }

          public GColumn getGColumn() {
            return getColumnSet().getColumnByClass(GColumn.class);
          }

          public AColumn getAColumn() {
            return getColumnSet().getColumnByClass(AColumn.class);
          }

          @Order(10.0)
          public class AColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("A");
            }
          }

          @Order(20.0)
          public class BColumn extends AbstractStringColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("B");
            }
          }

          @Order(30.0)
          public class GColumn extends AbstractBooleanColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("G");
            }
          }
        }
      }
    }

    @Order(50.0)
    public class DetailsBox extends AbstractTabBox {

      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Details");
      }

      @Order(10.0)
      public class EBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("E");
        }

        @Order(10.0)
        public class GField extends AbstractStringField {

          @Override
          protected int getConfiguredGridH() {
            return 2;
          }

          @Override
          protected int getConfiguredGridW() {
            return 2;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("G");
          }
        }
      }

      @Order(20.0)
      public class FBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("F");
        }

        @Order(10.0)
        public class HField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("H");
          }
        }

        @Order(20.0)
        public class IField extends AbstractStringField {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("I");
          }
        }
      }
    }

    @Order(60.0)
    public class OkButton extends AbstractOkButton {
    }

    @Order(70.0)
    public class CancelButton extends AbstractCancelButton {
    }
  }

  public class ModifyHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      ICompanyService service = SERVICES.getService(ICompanyService.class);
      CompanyFormData formData = new CompanyFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
      setEnabledPermission(new UpdateCompanyPermission());
    }

    @Override
    public void execStore() throws ProcessingException {
      ICompanyService service = SERVICES.getService(ICompanyService.class);
      CompanyFormData formData = new CompanyFormData();
      exportFormData(formData);
      formData = service.store(formData);
    }
  }

  public class NewHandler extends AbstractFormHandler {

    @Override
    public void execLoad() throws ProcessingException {
      ICompanyService service = SERVICES.getService(ICompanyService.class);
      CompanyFormData formData = new CompanyFormData();
      exportFormData(formData);
      formData = service.prepareCreate(formData);
      importFormData(formData);
    }

    @Override
    public void execStore() throws ProcessingException {
      ICompanyService service = SERVICES.getService(ICompanyService.class);
      CompanyFormData formData = new CompanyFormData();
      exportFormData(formData);
      formData = service.create(formData);
    }
  }
}

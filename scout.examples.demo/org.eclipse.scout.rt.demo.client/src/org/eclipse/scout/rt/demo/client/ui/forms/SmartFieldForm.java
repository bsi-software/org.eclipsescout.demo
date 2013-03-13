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
package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.demo.client.ClientSession;
import org.eclipse.scout.rt.demo.client.services.lookup.CompanyTypeLookupCall;
import org.eclipse.scout.rt.demo.client.services.lookup.ProductLookupCall;
import org.eclipse.scout.rt.demo.client.services.lookup.StatusTextLookupCall;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm.MainBox.GroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm.MainBox.GroupBox.LeftBox;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm.MainBox.GroupBox.LeftBox.ListWithCodeTypeField;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm.MainBox.GroupBox.LeftBox.ListWithLookupField;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm.MainBox.GroupBox.LeftBox.TreeWithCodeTypeField;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm.MainBox.GroupBox.LeftBox.TreeWithLookupCallFullField;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm.MainBox.GroupBox.LeftBox.TreeWithLookupCallIncrementalField;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm.MainBox.GroupBox.RightBox;
import org.eclipse.scout.rt.demo.client.ui.forms.SmartFieldForm.MainBox.GroupBox.RightBox.CodeAssistField;
import org.eclipse.scout.rt.demo.shared.services.code.CountryCodeType;
import org.eclipse.scout.rt.demo.shared.services.code.DateCodeType;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.LookupCall;

public class SmartFieldForm extends AbstractForm implements IPageForm {

  public SmartFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("SmartField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public CodeAssistField getCodeAssistField() {
    return getFieldByClass(CodeAssistField.class);
  }

  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  public LeftBox getLeftBox() {
    return getFieldByClass(LeftBox.class);
  }

  public ListWithCodeTypeField getListWithCodeTypeField() {
    return getFieldByClass(ListWithCodeTypeField.class);
  }

  public ListWithLookupField getListWithLookupField() {
    return getFieldByClass(ListWithLookupField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public RightBox getRightBox() {
    return getFieldByClass(RightBox.class);
  }

  public TreeWithCodeTypeField getTreeWithCodeTypeField() {
    return getFieldByClass(TreeWithCodeTypeField.class);
  }

  public TreeWithLookupCallFullField getTreeWithLookupCallFullField() {
    return getFieldByClass(TreeWithLookupCallFullField.class);
  }

  public TreeWithLookupCallIncrementalField getTreeWithLookupCallIncrementalField() {
    return getFieldByClass(TreeWithLookupCallIncrementalField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Order(10.0)
      public class LeftBox extends AbstractGroupBox {

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Order(10.0)
        public class TreeWithCodeTypeField extends AbstractSmartField<Long> {

          @Override
          protected boolean getConfiguredActiveFilterEnabled() {
            return true;
          }

          @Override
          protected String getConfiguredBackgroundColor() {
            return "8080FF";
          }

          @Override
          protected boolean getConfiguredBrowseHierarchy() {
            return true;
          }

          @Override
          protected Class<? extends ICodeType<Long>> getConfiguredCodeType() {
            if (ClientSession.get().isServerAvailable()) {
              return DateCodeType.class;
            }
            return null;
          }

          @Override
          protected boolean getConfiguredEnabled() {
            return ClientSession.get().isServerAvailable();
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("TreeWithCodeType");
          }

          @Override
          protected void execInitField() throws ProcessingException {
            if (!ClientSession.get().isServerAvailable()) {
              setErrorStatus("CodeTypes are only with server available");
            }
          }
        }

        @Order(20.0)
        public class TreeWithLookupCallFullField extends AbstractSmartField<Long> {

          @Override
          protected boolean getConfiguredBrowseHierarchy() {
            return true;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("TreeWithLookupCallFull");
          }

          @Override
          protected Class<? extends LookupCall> getConfiguredLookupCall() {
            return ProductLookupCall.class;

          }
        }

        @Order(30.0)
        public class TreeWithLookupCallIncrementalField extends AbstractSmartField<Long> {

          @Override
          protected boolean getConfiguredBrowseAutoExpandAll() {
            return false;
          }

          @Override
          protected boolean getConfiguredBrowseHierarchy() {
            return true;
          }

          @Override
          protected boolean getConfiguredBrowseLoadIncremental() {
            return true;
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("TreeWithLookupCallIncremental");
          }

          @Override
          protected Class<? extends LookupCall> getConfiguredLookupCall() {
            return ProductLookupCall.class;

          }

          @Override
          protected boolean getConfiguredVisible() {
            return false;
          }

          @Override
          public boolean acceptBrowseHierarchySelection(Long value, int level, boolean leaf) {
            return leaf;
          }
        }

        @Order(40.0)
        public class ListWithCodeTypeField extends AbstractSmartField<Long> {

          @Override
          protected Class<? extends ICodeType<Long>> getConfiguredCodeType() {
            if (ClientSession.get().isServerAvailable()) {
              return CountryCodeType.class;
            }
            return null;
          }

          @Override
          protected boolean getConfiguredEnabled() {
            return ClientSession.get().isServerAvailable();
          }

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("ListWithCodeType");
          }

          @Override
          protected void execInitField() throws ProcessingException {
            if (!ClientSession.get().isServerAvailable()) {
              setErrorStatus("CodeTypes are only with server available");
            }
          }
        }

        @Order(50.0)
        public class ListWithLookupField extends AbstractSmartField<Long> {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("ListWithLookup");
          }

          @Override
          protected Class<? extends LookupCall> getConfiguredLookupCall() {
            return CompanyTypeLookupCall.class;
          }
        }
      }

      @Order(30.0)
      public class RightBox extends AbstractGroupBox {

        @Override
        protected int getConfiguredGridColumnCount() {
          return 1;
        }

        @Override
        protected int getConfiguredGridW() {
          return 1;
        }

        @Order(10.0)
        public class CodeAssistField extends AbstractSmartField<Long> {

          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("CodeAssistField");
          }

          @Override
          protected Class<? extends LookupCall> getConfiguredLookupCall() {
            return StatusTextLookupCall.class;
          }
        }
      }
    }

    @Order(30.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

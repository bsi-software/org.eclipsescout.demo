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
import org.eclipse.scout.rt.client.ui.form.fields.composer.AbstractComposerField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.ComposerFieldForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.ComposerFieldForm.MainBox.GroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.ComposerFieldForm.MainBox.GroupBox.ComposerField;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.data.model.AbstractDataModelAttribute;
import org.eclipse.scout.rt.shared.data.model.AbstractDataModelEntity;

public class ComposerFieldForm extends AbstractForm implements IPageForm {

  public ComposerFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ComposerField");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  @Override
  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public ComposerField getComposerField() {
    return getFieldByClass(ComposerField.class);
  }

  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Order(10.0)
      public class ComposerField extends AbstractComposerField {

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("Composer");
        }

        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Order(20.0)
        public class LanguageAttribute extends AbstractDataModelAttribute {

          private static final long serialVersionUID = 1L;

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("Language");
          }

          @Override
          protected int getConfiguredType() {
            return TYPE_STRING;
          }
        }

        @Order(30.0)
        public class AddressAttribute extends AbstractDataModelAttribute {

          private static final long serialVersionUID = 1L;

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("Address");
          }

          @Override
          protected int getConfiguredType() {
            return TYPE_STRING;
          }
        }

        @Order(10.0)
        public class TimesheetEntry extends AbstractDataModelEntity {

          private static final long serialVersionUID = 1L;

          @Override
          protected String getConfiguredText() {
            return TEXTS.get("Timesheet");
          }

          @Order(20.0)
          public class PlannedEndAttribute extends AbstractDataModelAttribute {

            private static final long serialVersionUID = 1L;

            @Override
            protected String getConfiguredText() {
              return TEXTS.get("PlannedEnd");
            }

            @Override
            protected int getConfiguredType() {
              return TYPE_DATE;
            }
          }

          @Order(10.0)
          public class PlannedStartAttribute extends AbstractDataModelAttribute {

            private static final long serialVersionUID = 1L;

            @Override
            protected String getConfiguredText() {
              return TEXTS.get("PlannedStart");
            }

            @Override
            protected int getConfiguredType() {
              return TYPE_DATE;
            }
          }
        }
      }
    }

    @Order(20.0)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class PageFormHandler extends AbstractFormHandler {
  }
}

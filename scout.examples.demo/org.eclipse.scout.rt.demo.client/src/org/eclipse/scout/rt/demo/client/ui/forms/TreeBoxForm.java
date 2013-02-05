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
import org.eclipse.scout.rt.client.ui.form.fields.treebox.AbstractTreeBox;
import org.eclipse.scout.rt.demo.client.ui.forms.TreeBoxForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.TreeBoxForm.MainBox.GroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.TreeBoxForm.MainBox.GroupBox.TreeBoxField;
import org.eclipse.scout.rt.demo.shared.services.code.DateCodeType;
import org.eclipse.scout.rt.shared.TEXTS;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;

public class TreeBoxForm extends AbstractForm implements IPageForm {

  public TreeBoxForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("TreeBox");
  }

  @Override
  public void startPageForm() throws ProcessingException {
    startInternal(new PageFormHandler());
  }

  public CloseButton getCloseButton() {
    return getFieldByClass(CloseButton.class);
  }

  public GroupBox getGroupBox() {
    return getFieldByClass(GroupBox.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  public TreeBoxField getTreeBoxField() {
    return getFieldByClass(TreeBoxField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Order(10.0)
      public class TreeBoxField extends AbstractTreeBox<Long> {

        @Override
        protected Class<? extends ICodeType> getConfiguredCodeType() {
          return DateCodeType.class;
        }

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected int getConfiguredGridW() {
          return 2;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TreeBox");
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

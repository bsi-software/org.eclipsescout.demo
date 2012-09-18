package org.eclipse.scout.rt.demo.client.ui.forms;

import org.eclipse.scout.commons.annotations.Order;
import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.client.ui.basic.cell.Cell;
import org.eclipse.scout.rt.client.ui.basic.tree.AbstractTree;
import org.eclipse.scout.rt.client.ui.basic.tree.AbstractTreeNode;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.treefield.AbstractTreeField;
import org.eclipse.scout.rt.demo.client.ui.forms.TreeFieldForm.MainBox.CloseButton;
import org.eclipse.scout.rt.demo.client.ui.forms.TreeFieldForm.MainBox.GroupBox;
import org.eclipse.scout.rt.demo.client.ui.forms.TreeFieldForm.MainBox.GroupBox.TreeField;
import org.eclipse.scout.rt.shared.TEXTS;

public class TreeFieldForm extends AbstractForm implements IPageForm {

  public TreeFieldForm() throws ProcessingException {
    super();
  }

  @Override
  protected boolean getConfiguredAskIfNeedSave() {
    return false;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("TreeField");
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

  public TreeField getTreeField() {
    return getFieldByClass(TreeField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(10.0)
    public class GroupBox extends AbstractGroupBox {

      @Order(10.0)
      public class TreeField extends AbstractTreeField {

        @Override
        protected int getConfiguredGridH() {
          return 5;
        }

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("TreeField");
        }

        @Override
        protected void execInitField() throws ProcessingException {
          Tree exampleTree = new Tree();
          AbstractTreeNode node1 = new AbstractTreeNode() {
            @Override
            protected void execDecorateCell(Cell cell) {
              cell.setText("Node1");
            }
          };
          AbstractTreeNode node2 = new AbstractTreeNode() {
            @Override
            protected void execDecorateCell(Cell cell) {
              cell.setText("Node2");
            }
          };
          AbstractTreeNode node = new AbstractTreeNode() {
            @Override
            protected void execDecorateCell(Cell cell) {
              cell.setText("Node");
            }
          };
          exampleTree.addChildNode(exampleTree.getRootNode(), node1);
          exampleTree.addChildNode(exampleTree.getRootNode(), node2);
          exampleTree.addChildNode(node1, node);
          setTree(exampleTree, false);
        }

        @Order(10.0)
        public class Tree extends AbstractTree {
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

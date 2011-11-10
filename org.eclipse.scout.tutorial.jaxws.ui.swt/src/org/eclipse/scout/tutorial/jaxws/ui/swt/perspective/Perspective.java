/*******************************************************************************
 * Copyright (c) 2001 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Daniel Wiehl (Business Systems Integration AG) - initial API and implementation
 ******************************************************************************/
package org.eclipse.scout.tutorial.jaxws.ui.swt.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IViewLayout;

import org.eclipse.scout.tutorial.jaxws.ui.swt.Activator;

/** <h3>Activator</h3>
 *  ...
*/
public class Perspective implements IPerspectiveFactory {

  @Override
	public void createInitialLayout(IPageLayout layout) {

    layout.setEditorAreaVisible(false);

    layout.setFixed(false);

    layout.addStandaloneViewPlaceholder(Activator.OUTLINE_VIEW_ID,IPageLayout.LEFT,0.2f, IPageLayout.ID_EDITOR_AREA, true);

    String folderId="org.eclipse.scout.tutorial.jaxws.ui.swt.viewStack";
    IFolderLayout folderLayout = layout.createFolder(folderId,IPageLayout.RIGHT, 0.3f, Activator.TABLE_PAGE_VIEW_ID);
    folderLayout.addPlaceholder(Activator.TABLE_PAGE_VIEW_ID);
    folderLayout.addPlaceholder(Activator.CENTER_VIEW_ID);

    layout.addStandaloneViewPlaceholder(Activator.SEAECH_VIEW_ID,IPageLayout.BOTTOM,0.7f,  folderId, true);
    IViewLayout outlineSelectorLayout = layout.getViewLayout(Activator.OUTLINE_VIEW_ID);
    outlineSelectorLayout.setCloseable(false);
    outlineSelectorLayout.setMoveable(false);

    IViewLayout tablePageLayout = layout.getViewLayout(Activator.TABLE_PAGE_VIEW_ID);
    tablePageLayout.setCloseable(false);
    tablePageLayout.setMoveable(false);

    IViewLayout searchLayout = layout.getViewLayout(Activator.SEAECH_VIEW_ID);
    searchLayout.setCloseable(false);
    searchLayout.setMoveable(false);

//
//
//    String folderID = "org.eclipse.scout.rt.ui.swt.layout.viewStack";
//    layout.addStandaloneViewPlaceholder(Activator.CENTER_VIEW_ID,IPageLayout.LEFT,0.8f, IPageLayout.ID_EDITOR_AREA, true);

//    IFolderLayout folderLayout = layout.createFolder(folderID,
//        IPageLayout.RIGHT, 0.3f, Activator.CRM_OUTLINE_VIEW);
//    folderLayout.addView(Activator.CRM_TABLE_VIEW);
//    folderLayout.addPlaceholder(Activator.CRM_FORM_STACK_VIEW_ID+":*");
////    layout.addStandaloneView(Activator.CRM_TABLE_VIEW, true, IPageLayout.RIGHT,0.3f, Activator.CRM_OUTLINE_VIEW);
//    layout.addFastView("org.eclipse.ui.views.ProgressView", 0.3f);
//    layout.addPlaceholder(Activator.CRM_DETAIL_VIEW, IPageLayout.BOTTOM,0.6f, folderID);
//    layout.addStandaloneView(Activator.CRM_OUTLINE_SELECTOR_VIEW, false,IPageLayout.BOTTOM ,0.8f, Activator.CRM_OUTLINE_VIEW);
//
//
//    IViewLayout outlineSelectorLayout = layout.getViewLayout(Activator.CRM_OUTLINE_SELECTOR_VIEW);
//      outlineSelectorLayout.setCloseable(false);
//      outlineSelectorLayout.setMoveable(false);
//
//      IViewLayout outlineLayout = layout.getViewLayout(Activator.CRM_OUTLINE_VIEW);
//      outlineLayout.setCloseable(false);
//      outlineLayout.setMoveable(false);
//
//      IViewLayout detailLayout = layout.getViewLayout(Activator.CRM_DETAIL_VIEW);
//      detailLayout.setCloseable(false);
//      detailLayout.setMoveable(false);
//
//      IViewLayout tableViewLayout = layout.getViewLayout(Activator.CRM_TABLE_VIEW);
//      tableViewLayout.setCloseable(false);
  }

}

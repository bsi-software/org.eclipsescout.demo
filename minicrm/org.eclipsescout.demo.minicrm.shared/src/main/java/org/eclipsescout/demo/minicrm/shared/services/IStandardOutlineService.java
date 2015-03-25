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
package org.eclipsescout.demo.minicrm.shared.services;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.service.IService;
import org.eclipsescout.demo.minicrm.shared.ui.desktop.outlines.pages.CompanyTablePageData;
import org.eclipsescout.demo.minicrm.shared.ui.desktop.outlines.pages.PersonTablePageData;
import org.eclipsescout.demo.minicrm.shared.ui.desktop.outlines.pages.searchform.CompanySearchFormData;
import org.eclipsescout.demo.minicrm.shared.ui.desktop.outlines.pages.searchform.PersonSearchFormData;

@TunnelToServer
public interface IStandardOutlineService extends IService {

  public CompanyTablePageData getCompanyTableData(CompanySearchFormData formData) throws ProcessingException;

  public PersonTablePageData getPersonTableData(PersonSearchFormData formData) throws ProcessingException;
}

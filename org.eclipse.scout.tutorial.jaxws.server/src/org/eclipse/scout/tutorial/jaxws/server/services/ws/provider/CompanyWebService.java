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
package org.eclipse.scout.tutorial.jaxws.server.services.ws.provider;

import java.util.List;

import javax.jws.WebService;

import org.eclipse.scout.jaxws216.annotation.ScoutWebService;
import org.eclipse.scout.tutorial.jaxws.services.ws.companywebservice.Company;
import org.eclipse.scout.tutorial.jaxws.services.ws.companywebservice.CompanyWebServicePortType;

@ScoutWebService
@WebService(endpointInterface = "org.eclipse.scout.tutorial.jaxws.services.ws.companywebservice.CompanyWebServicePortType")
public class CompanyWebService implements CompanyWebServicePortType {

  @Override
  public List<Company> getCompanies() {
    return null;
  }

}

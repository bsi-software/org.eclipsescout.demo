/*******************************************************************************
 * Copyright (c) 2010 BSI Business Systems Integration AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the BSI CRM Software License v1.0
 * which accompanies this distribution as bsi-v10.html
 *
 * Contributors:
 *     BSI Business Systems Integration AG - initial API and implementation
 ******************************************************************************/
package org.eclipsescout.demo.bahbah.server.services.custom.security;

import org.eclipse.scout.commons.exception.ProcessingException;

/**
 *
 */
public interface IUserMapService {

  void publishUserMap() throws ProcessingException;

  /**
   * use this method only while in cluster-sync-mode
   * it will not notify any other clusters
   * 
   * @throws ProcessingException
   */
  void publishUserMapInternal() throws ProcessingException;

}

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
package org.eclipsescout.demo.minifigcreator.client.ui.forms;

import java.util.List;

import org.eclipse.scout.commons.exception.ProcessingException;
import org.eclipse.scout.rt.testing.shared.TestingUtility;
import org.eclipse.scout.testing.client.ScoutClientAssert;
import org.eclipse.scout.testing.client.runner.ScoutClientTestRunner;
import org.eclipsescout.demo.minifigcreator.client.Activator;
import org.eclipsescout.demo.minifigcreator.shared.services.process.DesktopFormData;
import org.eclipsescout.demo.minifigcreator.shared.services.process.FormState;
import org.eclipsescout.demo.minifigcreator.shared.services.process.IDesktopProcessService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.osgi.framework.ServiceRegistration;

/**
 * Tests for {@link org.eclipsescout.demo.minifigcreator.client.ui.forms.DesktopForm}
 *
 * @author jbr
 */
@RunWith(ScoutClientTestRunner.class)
public class DesktopFormTest {
  private IDesktopProcessService m_mockService = Mockito.mock(IDesktopProcessService.class);
  private List<ServiceRegistration> m_registeredServices;

  @Before
  public void setUp() {
    m_registeredServices = TestingUtility.registerServices(Activator.getDefault().getBundle(), 1000, m_mockService);
  }

  @After
  public void tearDown() {
    TestingUtility.unregisterServices(m_registeredServices);
  }

  /**
   * Test method: FormState tells that all fields should be enabled.
   */
  @Test
  public void testAllEnabled() throws Exception {
    DesktopForm form = createFormWithState(true, true, true);
    form.startView();

    ScoutClientAssert.assertEnabled(form.getHeadField());
    ScoutClientAssert.assertEnabled(form.getTorsoField());
    ScoutClientAssert.assertEnabled(form.getLegsField());
  }

  /**
   * Test method: FormState tells that all fields should be disabled.
   */
  @Test
  public void testAllDisabled() throws Exception {
    DesktopForm form = createFormWithState(false, false, false);
    form.startView();

    ScoutClientAssert.assertDisabled(form.getHeadField());
    ScoutClientAssert.assertDisabled(form.getTorsoField());
    ScoutClientAssert.assertDisabled(form.getLegsField());
  }

  /**
   * Test method: FormState tells that head field should be disabled.
   */
  @Test
  public void testHeadDisabled() throws Exception {
    DesktopForm form = createFormWithState(false, true, true);
    form.startView();

    ScoutClientAssert.assertDisabled(form.getHeadField());
    ScoutClientAssert.assertEnabled(form.getTorsoField());
    ScoutClientAssert.assertEnabled(form.getLegsField());
  }

  /**
   * Test method: FormState tells that torso field should be disabled.
   */
  @Test
  public void testTorsoDisabled() throws Exception {
    DesktopForm form = createFormWithState(true, false, true);
    form.startView();

    ScoutClientAssert.assertEnabled(form.getHeadField());
    ScoutClientAssert.assertDisabled(form.getTorsoField());
    ScoutClientAssert.assertEnabled(form.getLegsField());
  }

  /**
   * Test method: FormState tells that legs field should be disabled.
   */
  @Test
  public void testLegsDisabled() throws Exception {
    DesktopForm form = createFormWithState(true, true, false);
    form.startView();

    ScoutClientAssert.assertEnabled(form.getHeadField());
    ScoutClientAssert.assertEnabled(form.getTorsoField());
    ScoutClientAssert.assertDisabled(form.getLegsField());
  }

  /**
   * Ensure that all methods are called
   */
  @Test
  public void testAllMethodsAreCalled() throws Exception {
    DesktopForm form = Mockito.spy(createFormWithState(true, true, true));
    form.startView();

    InOrder orderCheck = Mockito.inOrder(form);

    Mockito.verify(form, Mockito.times(1)).updateImage();
    Mockito.verify(form, Mockito.times(1)).updateSummary();

    orderCheck.verify(form).updateImage();
    orderCheck.verify(form).updateSummary();
  }

  private DesktopForm createFormWithState(final boolean headEnabled, final boolean torsoEnabled, final boolean legsEnabled) throws ProcessingException {
    DesktopFormData loadFormData = new DesktopFormData();
    FormState state = new FormState();
    state.setHeadEnabled(headEnabled);
    state.setTorsoEnabled(torsoEnabled);
    state.setLegsEnabled(legsEnabled);

    loadFormData.setState(state);
    Mockito.when(m_mockService.load(Mockito.any(DesktopFormData.class))).thenReturn(loadFormData);

    DesktopForm form = new DesktopForm();
    return form;
  }
}

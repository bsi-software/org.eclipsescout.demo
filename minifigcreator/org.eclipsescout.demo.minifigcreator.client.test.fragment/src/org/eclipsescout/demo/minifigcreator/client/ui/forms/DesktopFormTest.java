/**
 *
 */
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
 * Tests for
 * {@link org.eclipsescout.demo.minifigcreator.client.ui.forms.DesktopForm}
 *
 * @author jbr
 */
// ...
@RunWith(ScoutClientTestRunner.class)
public class DesktopFormTest extends Mockito{

	private IDesktopProcessService m_mockService = mock(IDesktopProcessService.class);
	@SuppressWarnings("rawtypes")
	private List<ServiceRegistration> m_registeredServices;

	@Before
	public void setUp() {
		m_registeredServices = TestingUtility.registerServices(Activator.getDefault().getBundle(), 1000, m_mockService);
	}

	@After
	public void teardown() {
		TestingUtility.unregisterServices(m_registeredServices);
	}

	@Test
	public void testAllMethodsAreCalled() throws Exception {
		DesktopForm form = spy(createFormWithState(true, true, true));

		InOrder orderCheck = inOrder(form);

		verify(form, times(1)).updateImage();
		verify(form, times(1)).updateSummary();

		orderCheck.verify(form).updateImage();
		orderCheck.verify(form).updateSummary();
	}

	/**
	 * Test method: FormState tells that all fields should be enabled.
	 */
	@Test
	public void testAllEnabled() throws Exception {
		DesktopForm form = createFormWithState(true, true, true);

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

		ScoutClientAssert.assertEnabled(form.getHeadField());
		ScoutClientAssert.assertEnabled(form.getTorsoField());
		ScoutClientAssert.assertDisabled(form.getLegsField());
	}

	private DesktopForm createFormWithState(final boolean headEnabled, final boolean torsoEnabled, final boolean legsEnabled) throws ProcessingException {
		DesktopFormData loadFormData = new DesktopFormData();
		FormState state = new FormState();
		state.setHeadEnabled(headEnabled);
		state.setTorsoEnabled(torsoEnabled);
		state.setLegsEnabled(legsEnabled);

		loadFormData.setState(state);
		when(m_mockService.load(any(DesktopFormData.class))).thenReturn(loadFormData);

		DesktopForm form = new DesktopForm();
		form.startView();
		return form;
	}
}

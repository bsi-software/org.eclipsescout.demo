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

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 */
public class Example {

	@Test
	public void test() throws Exception {
		// Arrange
		IMyStringBuiler sb = new MyStringBuilder("a");

		// Act
		sb.append("b");

		// Assert
		Assert.assertEquals("ab", sb.toString());
	}

	@Test
	public void testMock() throws Exception {
		// Arrange
		CharSequence startString = Mockito.mock(CharSequence.class);
		IMyStringBuiler sb = new MyStringBuilder(startString);

		// Act
		sb.append("b");

		// Assert
		Mockito.verify(startString).subSequence(0, 0);
	}

	@Test
	public void testMockWhen() throws Exception {
		// Arrange
		CharSequence startString = Mockito.mock(CharSequence.class);
		// Use when
		Mockito.when(startString.subSequence(0, 0)).thenReturn("a");
		//
		when(startString.subSequence(0, 0)).thenReturn("a");

		IMyStringBuiler sb = new MyStringBuilder(startString);

		// Act
		sb.append("b");

		// Verify
		Mockito.verify(startString).subSequence(0, 0);
		// Assert
		Assert.assertEquals("ab", sb.toString());
		//
		assertEquals("ab", sb.toString());
		// Use hamcrest matchers
		Assert.assertThat(sb.toString(), Is.is("ab"));
		//
		assertThat(sb.toString(), is("ab"));
	}
}

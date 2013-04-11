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

/**
 *
 */
public class MyStringBuilder implements IMyStringBuiler {

	private CharSequence charSeq;

	/**
	 * @param string
	 */
	public MyStringBuilder(CharSequence string) {
		charSeq = string;
	}

	@Override
	public void append(String string) {
		charSeq = charSeq.subSequence(0, charSeq.length()) + string;
	}

	@Override
	public String toString() {
		return charSeq.toString();
	}
}

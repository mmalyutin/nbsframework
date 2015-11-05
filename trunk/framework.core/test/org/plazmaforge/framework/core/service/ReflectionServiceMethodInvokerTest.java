/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

/**
 * 
 */
package org.plazmaforge.framework.core.service;

import org.junit.Test;
import org.plazmaforge.framework.core.data.Parameters;
import org.plazmaforge.framework.core.service.ReflectionServiceMethodInvoker;


import junit.framework.TestCase;

/**
 * @author ohapon
 *
 */
public class ReflectionServiceMethodInvokerTest extends TestCase {

    @Test
    public void testCall() throws Exception {
	
	ReflectionServiceMethodInvoker caller = new ReflectionServiceMethodInvoker();
	final String labelText = "Label Text"; 
	final String labelName = "Label name";
	Label label = new Label();
	
	Parameters parameters = new Parameters();
	parameters.add(labelText);
	caller.invoke(label, "setText", parameters); // Own property
	assertEquals(labelText, label.getText());
	
	parameters.clear();
	parameters.add(labelName);
	caller.invoke(label, "setName", parameters); // Super class property
	assertEquals(labelName, label.getName());
	
	parameters.clear();
	parameters.add(labelText);
	String resultText = (String) caller.invoke(label, "getText", null); 
	assertEquals(resultText, labelText);
    }
    
    public static class Label {
	
	private String name;
	
	private String text;

	public Label() {
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getText() {
	    return text;
	}

	public void setText(String text) {
	    this.text = text;
	}
	
    }
}

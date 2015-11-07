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

package org.plazmaforge.framework.uwt.storage;


import java.io.InputStream;

import org.junit.Test;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.resources.ResourceHelper;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Layout;


import junit.framework.TestCase;

public class XMLReaderTest extends TestCase {

    protected void setUp() {
	
    }
    
    @Test
    public void testCompositeReader() throws Exception {
	XMLReader reader = new XMLReader();
	String fileName = "ui/Composite.ui.xml";
	InputStream is = ResourceHelper.getResourceStream(fileName);
	UIObject ui = reader.readObject(is);
	assertNotNull(ui);
	assertTrue(ui instanceof Composite);
	Composite composite  = (Composite) ui;
	Layout layout = composite.getLayout();
	assertNotNull(layout);
	assertTrue(layout instanceof GridLayout);
	GridLayout gridLayout = (GridLayout) layout;
	assertEquals(gridLayout.getColumnCount(), 2);
    }
    
 
}

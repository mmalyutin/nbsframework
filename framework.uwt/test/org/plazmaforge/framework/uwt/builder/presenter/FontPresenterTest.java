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

package org.plazmaforge.framework.uwt.builder.presenter;

import org.junit.Test;
import org.plazmaforge.framework.uwt.graphics.Font;

import junit.framework.TestCase;

public class FontPresenterTest extends TestCase {

    @Test
    public void testParseFont() throws Exception {
	
	FontPresenter presenter = new FontPresenter();
	
	// Test invalid name
	String fontName = presenter.parseName(null);
	assertNull(fontName);
	
	fontName = presenter.parseName("");
	assertNull(fontName);
	

	// Test valid name
	fontName = presenter.parseName("Arial");
	assertNotNull(fontName);
	assertEquals("Arial", fontName);

	
	
	
	// Test invalid size
	Integer fontSize = presenter.parseSize(null);
	assertNull(fontSize);
	
	fontSize = presenter.parseSize("");
	assertNull(fontSize);

	fontSize = presenter.parseSize("blahblah");
	assertNull(fontSize);

	fontSize = presenter.parseSize("10px"); // Ups...
	assertNull(fontSize);


	// Test valid size
	Integer testSize = 10;
	fontSize = presenter.parseSize("10");
	assertNotNull(fontSize);
	assertEquals(testSize, fontSize);

	
	
	
	// Test invalid style
	Integer fontStyle = presenter.parseStyle(null);
	assertNull(fontStyle);

	fontStyle = presenter.parseStyle("");
	assertNull(fontStyle);

	fontStyle = presenter.parseStyle("blahblah");
	assertNull(fontStyle);

	fontStyle = presenter.parseStyle("bo|it");
	assertNull(fontStyle);

	Integer testStyle = Font.BOLD;
	fontStyle = presenter.parseStyle("bold");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);


	testStyle = Font.ITALIC;
	fontStyle = presenter.parseStyle("italic");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	testStyle = Font.BOLD | Font.ITALIC;
	fontStyle = presenter.parseStyle("bold|italic");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);
	
	
    }
    
    @Test
    public void testToValue() throws Exception {
	
	FontPresenter presenter = new FontPresenter();
	
	// Test invalid
	Font font = (Font) presenter.toValue(null);
	assertNull(font);
	
	font = (Font) presenter.toValue("");
	assertNull(font);

	
	//
	String fontName = "Arial";
	int fontSize = 0;
	int fontStyle = 0;
	
	font = (Font) presenter.toValue("Arial");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());

	//
	fontName = "Arial";
	fontSize = 10;
	fontStyle = 0;
	
	font = (Font) presenter.toValue("Arial, 10");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());

	//
	fontName = "Arial";
	fontSize = 10;
	fontStyle = Font.BOLD;

	font = (Font) presenter.toValue("Arial, 10, bold");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());

	//
	fontName = "Arial";
	fontSize = 10;
	fontStyle = Font.ITALIC;

	font = (Font) presenter.toValue("Arial, 10, italic");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());
	
	//
	fontName = "Arial";
	fontSize = 10;
	fontStyle = Font.BOLD | Font.ITALIC;

	font = (Font) presenter.toValue("Arial, 10, bold|italic");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());

	//
	font = (Font) presenter.toValue("Arial, 10, bold | italic");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());


	//
	font = (Font) presenter.toValue("Arial,10,bold|italic");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());


    }
    
    @Test
    public void testToString() throws Exception {
	
	FontPresenter presenter = new FontPresenter();

	// Test invalid
	String fontString = presenter.toString(null);
	assertNull(fontString);

	// Test valid
	Font font = new Font("Arial", 10);
	fontString = presenter.toString(font);
	assertNotNull(fontString);
	assertEquals("Arial, 10", fontString);

	//
	font = new Font("Arial", 10, Font.BOLD);
	fontString = presenter.toString(font);
	assertNotNull(fontString);
	assertEquals("Arial, 10, bold", fontString);

	//
	font = new Font("Arial", 10, Font.ITALIC);
	fontString = presenter.toString(font);
	assertNotNull(fontString);
	assertEquals("Arial, 10, italic", fontString);

	//
	font = new Font("Arial", 10, Font.BOLD | Font.ITALIC);
	fontString = presenter.toString(font);
	assertNotNull(fontString);
	assertEquals("Arial, 10, bold|italic", fontString);

	
    }

}

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

package org.plazmaforge.framework.uwt.builder.formatter.type;

import org.junit.Test;
import org.plazmaforge.framework.uwt.builder.formatter.type.FontFormatter;
import org.plazmaforge.framework.uwt.graphics.Font;

import junit.framework.TestCase;

public class FontFormatterTest extends TestCase {

    @Test
    public void testParseAttributes() throws Exception {
	
	FontFormatter formatter = new FontFormatter();
	
	// Test invalid name
	String fontName = formatter.parseName(null);
	assertNull(fontName);
	
	fontName = formatter.parseName("");
	assertNull(fontName);
	

	// Test valid name
	fontName = formatter.parseName("Arial");
	assertNotNull(fontName);
	assertEquals("Arial", fontName);

	
	
	
	// Test invalid size
	Integer fontSize = formatter.parseSize(null);
	assertNull(fontSize);
	
	fontSize = formatter.parseSize("");
	assertNull(fontSize);

	fontSize = formatter.parseSize("blahblah");
	assertNull(fontSize);

	fontSize = formatter.parseSize("10px"); // Ups...
	assertNull(fontSize);


	// Test valid size
	Integer testSize = 10;
	fontSize = formatter.parseSize("10");
	assertNotNull(fontSize);
	assertEquals(testSize, fontSize);

	
	
	
	// Test invalid style
	Integer testStyle = null;
	Integer fontStyle = formatter.parseStyle(null);
	assertNull(fontStyle);

	
	fontStyle = formatter.parseStyle("");
	assertNull(fontStyle);

	fontStyle = formatter.parseStyle("blahblah");
	assertNull(fontStyle);

	fontStyle = formatter.parseStyle("bo|it");
	assertNull(fontStyle);

	
	testStyle = Font.BOLD;
	fontStyle = formatter.parseStyle("bold");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	testStyle = Font.ITALIC;
	fontStyle = formatter.parseStyle("italic");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	testStyle = Font.UNDERLINE;
	fontStyle = formatter.parseStyle("underline");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	testStyle = Font.STRIKEOUT;
	fontStyle = formatter.parseStyle("strikeout");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);
	
	testStyle = Font.BOLD | Font.ITALIC;
	fontStyle = formatter.parseStyle("bold|italic");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	testStyle = Font.BOLD | Font.ITALIC | Font.UNDERLINE;
	fontStyle = formatter.parseStyle("bold|italic|underline");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	testStyle = Font.BOLD | Font.ITALIC | Font.UNDERLINE | Font.STRIKEOUT;
	fontStyle = formatter.parseStyle("bold|italic|underline|strikeout");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);
	

    }
    
    @Test
    public void testParseFontStyle() {
	
	// Style matrix
	
	FontFormatter formatter = new FontFormatter();
	Integer testStyle = null;
	Integer fontStyle = formatter.parseStyle(null);
	
	// 0|0|0|1
	testStyle = Font.STRIKEOUT;
	fontStyle = formatter.parseStyle("strikeout");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 0|0|1|0
	testStyle = Font.UNDERLINE;
	fontStyle = formatter.parseStyle("underline");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 0|0|1|1
	testStyle = Font.UNDERLINE | Font.STRIKEOUT;
	fontStyle = formatter.parseStyle("underline|strikeout");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 0|1|0|0
	testStyle = Font.ITALIC;
	fontStyle = formatter.parseStyle("italic");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 0|1|0|1
	testStyle = Font.ITALIC | Font.STRIKEOUT;
	fontStyle = formatter.parseStyle("italic|strikeout");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 0|1|1|0
	testStyle = Font.ITALIC | Font.UNDERLINE;
	fontStyle = formatter.parseStyle("italic|underline");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 0|1|1|1
	testStyle = Font.ITALIC | Font.UNDERLINE | Font.STRIKEOUT;
	fontStyle = formatter.parseStyle("italic|underline|strikeout");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 1|0|0|0
	testStyle = Font.BOLD;
	fontStyle = formatter.parseStyle("bold");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 1|0|0|1
	testStyle = Font.BOLD | Font.STRIKEOUT;
	fontStyle = formatter.parseStyle("bold|strikeout");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 1|0|1|0
	testStyle = Font.BOLD | Font.UNDERLINE;
	fontStyle = formatter.parseStyle("bold|underline");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 1|0|1|1
	testStyle = Font.BOLD | Font.UNDERLINE | Font.STRIKEOUT;
	fontStyle = formatter.parseStyle("bold|underline|strikeout");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 1|1|0|0
	testStyle = Font.BOLD | Font.ITALIC;
	fontStyle = formatter.parseStyle("bold|italic");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 1|1|0|1
	testStyle = Font.BOLD | Font.ITALIC | Font.STRIKEOUT;
	fontStyle = formatter.parseStyle("bold|italic|strikeout");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 1|1|1|0
	testStyle = Font.BOLD | Font.ITALIC | Font.UNDERLINE;
	fontStyle = formatter.parseStyle("bold|italic|underline");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);

	// 1|1|1|1
	testStyle = Font.BOLD | Font.ITALIC | Font.UNDERLINE| Font.STRIKEOUT;
	fontStyle = formatter.parseStyle("bold|italic|underline|strikeout");
	assertNotNull(fontStyle);
	assertEquals(testStyle, fontStyle);
	
    }
    
    @Test
    public void testParseFont() throws Exception {
	
	FontFormatter formatter = new FontFormatter();
	
	// Test invalid
	Font font = (Font) formatter.parse(null);
	assertNull(font);
	
	font = (Font) formatter.parse("");
	assertNull(font);

	
	//
	String fontName = "Arial";
	int fontSize = 0;
	int fontStyle = 0;
	
	font = (Font) formatter.parse("Arial");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());

	//
	fontName = "Arial";
	fontSize = 10;
	fontStyle = 0;
	
	font = (Font) formatter.parse("Arial, 10");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());

	//
	fontName = "Arial";
	fontSize = 10;
	fontStyle = Font.BOLD;

	font = (Font) formatter.parse("Arial, 10, bold");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());

	//
	fontName = "Arial";
	fontSize = 10;
	fontStyle = Font.ITALIC;

	font = (Font) formatter.parse("Arial, 10, italic");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());
	
	//
	fontName = "Arial";
	fontSize = 10;
	fontStyle = Font.BOLD | Font.ITALIC;

	font = (Font) formatter.parse("Arial, 10, bold|italic");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());

	//
	font = (Font) formatter.parse("Arial, 10, bold | italic");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());


	//
	font = (Font) formatter.parse("Arial,10,bold|italic");
	assertNotNull(font);
	assertEquals(fontName, font.getName());
	assertEquals(fontSize, font.getSize());
	assertEquals(fontStyle, font.getStyle());


    }
    
    @Test
    public void testFormatFont() throws Exception {
	
	FontFormatter formatter = new FontFormatter();

	// Test invalid
	String fontString = formatter.format(null);
	assertNull(fontString);

	// Test valid
	Font font = new Font("Arial", 10);
	fontString = formatter.format(font);
	assertNotNull(fontString);
	assertEquals("Arial, 10", fontString);

	//
	font = new Font("Arial", 10, Font.BOLD);
	fontString = formatter.format(font);
	assertNotNull(fontString);
	assertEquals("Arial, 10, bold", fontString);

	//
	font = new Font("Arial", 10, Font.ITALIC);
	fontString = formatter.format(font);
	assertNotNull(fontString);
	assertEquals("Arial, 10, italic", fontString);

	//
	font = new Font("Arial", 10, Font.BOLD | Font.ITALIC);
	fontString = formatter.format(font);
	assertNotNull(fontString);
	assertEquals("Arial, 10, bold|italic", fontString);

	
    }

}

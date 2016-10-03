package org.plazmaforge.framework.uwt.builder.formatter.type;

import org.junit.Test;
import org.plazmaforge.framework.uwt.builder.formatter.type.ColorFormatter;
import org.plazmaforge.framework.uwt.graphics.Color;

import junit.framework.TestCase;

public class ColorFormatterTest  extends TestCase {
    
    private static final String[] COLORS = new String[] {
	"white", 
    	"lightgray",
	"gray",
	"darkgray",
	"black",
	"red",
	"pink",
	"orange",
	"yellow",
	"green",
	"magenta",
	"cyan",
	"blue"
    };	

    private static final String[] HEX_INVALID_VALUES = new String[] {
	"blahblah",
	"#",
	"##",
	"#112233445566778899",
	"#xxyyzz"
	};
    
    private static final String[] RGB_INVALID_VALUES = new String[] {
  	"blahblah",
  	"rgb",
  	"rgb()",
  	"rgb(",
  	"rgb)",
  	"rgbcolor",
  	"rgb(11, 22, 33, 44, 55, 66, 77, 88, 99)",
  	"rgb(xx, yy, zz)"
  	};
    

    @Test
    public void testParseHex() throws Exception {
	ColorFormatter formatter = new ColorFormatter();
	
	// Test invalid
	Color color = null;
	
	for (String value: HEX_INVALID_VALUES) {
	    color = formatter.parseHex(value);
	    assertNull(color);
	}
	
	// Test valid
	color = formatter.parseHex("#112233");
	assertNotNull(color);
	assertEquals("#112233", "#" + color.toHexString());

	color = formatter.parseHex("#aabbcc");
	assertNotNull(color);
	assertEquals("#aabbcc", "#" + color.toHexString());

    }

    
    @Test
    public void testParseRGB() throws Exception {
	ColorFormatter formatter = new ColorFormatter();
	
	// Test invalid
	Color color = null;
	
	for (String value: RGB_INVALID_VALUES) {
	    color = formatter.parseRGB(value);
	    assertNull(color);
	}
	
	// Test valid
	color = formatter.parseRGB("rgb(11, 22, 33)");
	assertNotNull(color);
	assertEquals(color.getRed(), 11);
	assertEquals(color.getGreen(), 22);
	assertEquals(color.getBlue(), 33);

    }
    
    @Test
    public void testParseNames() throws Exception {
	ColorFormatter formatter = new ColorFormatter();
	
	// Test invalid
	Color color = formatter.parseName(null);
	assertNull(color);
	
	color = formatter.parseName("blahblah");
	assertNull(color);
	
	// Test valid
	
	// Color in lower case
	for (String value: COLORS) {
	    color = formatter.parseName(value);
	    assertNotNull(color);
	    
	    Color findColor = findColor(value);

	    assertEquals(color.getRed(), findColor.getRed());
	    assertEquals(color.getGreen(), findColor.getGreen());
	    assertEquals(color.getBlue(), findColor.getBlue());

	}

	// Color in upper case
	for (String value: COLORS) {
	    color = formatter.parseName(value.toUpperCase());
	    assertNotNull(color);
	    
	    Color findColor = findColor(value);

	    assertEquals(color.getRed(), findColor.getRed());
	    assertEquals(color.getGreen(), findColor.getGreen());
	    assertEquals(color.getBlue(), findColor.getBlue());
	}


    }
    
    
    
    @Test
    public void testToValue() throws Exception {
	ColorFormatter formatter = new ColorFormatter();
	
	// Test invalid
	Color color = (Color) formatter.parse(null);
	assertNull(color);
	
	color = (Color) formatter.parse("");
	assertNull(color);

	for (String value: HEX_INVALID_VALUES) {
	    color = (Color) formatter.parse(value);
	    assertNull(color);
	}

	for (String value: RGB_INVALID_VALUES) {
	    color = (Color) formatter.parse(value);
	    assertNull(color);
	}
	
	
	color = formatter.parseName("blahblah");
	assertNull(color);
	
	// Test valid
	
	// Color in lower case
	for (String value: COLORS) {
	    color = (Color) formatter.parse(value);
	    assertNotNull(color);
	    
	    Color findColor = findColor(value);

	    assertEquals(color.getRed(), findColor.getRed());
	    assertEquals(color.getGreen(), findColor.getGreen());
	    assertEquals(color.getBlue(), findColor.getBlue());
	}

	// Color in upper case
	for (String value: COLORS) {
	    color = (Color) formatter.parse(value.toUpperCase());
	    assertNotNull(color);
	    
	    Color findColor = findColor(value);

	    assertEquals(color.getRed(), findColor.getRed());
	    assertEquals(color.getGreen(), findColor.getGreen());
	    assertEquals(color.getBlue(), findColor.getBlue());
	}

	// Hex
	color = (Color) formatter.parse("#112233");
	assertNotNull(color);
	assertEquals("#112233", "#" + color.toHexString());

	color = (Color) formatter.parse("#aabbcc");
	assertNotNull(color);
	assertEquals("#aabbcc", "#" + color.toHexString());

	// RGB
	color = (Color) formatter.parse("rgb(11, 22, 33)");
	assertNotNull(color);
	assertEquals(color.getRed(), 11);
	assertEquals(color.getGreen(), 22);
	assertEquals(color.getBlue(), 33);

    }
    
    @Test
    public void testToString() throws Exception {
	ColorFormatter formatter = new ColorFormatter();
	
	// Test invalid
	String colorString = formatter.format(null);
	assertNull(colorString);
	
	
	// Test valid
	int red = 11;
	int green = 22;
	int blue = 33;
	
	String redHex = toHexString(red);
	String greenHex = toHexString(green);
	String blueHex = toHexString(blue);

	String colorHex = "#" + redHex + greenHex + blueHex; 
	Color color = new Color(red, green, blue);
	colorString = formatter.format(color);
	assertNotNull(colorString);
	assertEquals(colorHex, colorString);

    }
    
    private Color findColor(String name) {
	//TODO: Find color in other storage
	return ColorFormatter.getColorByName(name);
    }
    
    private String toHexString(int value) {
	return value < 16 ? "0" + Integer.toHexString(value) : Integer.toHexString(value);
    }
}

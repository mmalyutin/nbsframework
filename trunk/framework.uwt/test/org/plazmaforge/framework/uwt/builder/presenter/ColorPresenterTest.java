package org.plazmaforge.framework.uwt.builder.presenter;

import org.junit.Test;
import org.plazmaforge.framework.uwt.graphics.Color;

import junit.framework.TestCase;

public class ColorPresenterTest  extends TestCase {
    
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
	ColorPresenter presenter = new ColorPresenter();
	
	// Test invalid
	Color color = null;
	
	for (String value: HEX_INVALID_VALUES) {
	    color = presenter.parseHex(value);
	    assertNull(color);
	}
	
	// Test valid
	color = presenter.parseHex("#112233");
	assertNotNull(color);
	assertEquals("#112233", "#" + color.toHexString());

	color = presenter.parseHex("#aabbcc");
	assertNotNull(color);
	assertEquals("#aabbcc", "#" + color.toHexString());

    }

    
    @Test
    public void testParseRGB() throws Exception {
	ColorPresenter presenter = new ColorPresenter();
	
	// Test invalid
	Color color = null;
	
	for (String value: RGB_INVALID_VALUES) {
	    color = presenter.parseRGB(value);
	    assertNull(color);
	}
	
	// Test valid
	color = presenter.parseRGB("rgb(11, 22, 33)");
	assertNotNull(color);
	assertEquals(color.getRed(), 11);
	assertEquals(color.getGreen(), 22);
	assertEquals(color.getBlue(), 33);

    }
    
    @Test
    public void testParseNames() throws Exception {
	ColorPresenter presenter = new ColorPresenter();
	
	// Test invalid
	Color color = presenter.parseName(null);
	assertNull(color);
	
	color = presenter.parseName("blahblah");
	assertNull(color);
	
	// Test valid
	
	// Color in lower case
	for (String value: COLORS) {
	    color = presenter.parseName(value);
	    assertNotNull(color);
	    
	    Color findColor = findColor(value);

	    assertEquals(color.getRed(), findColor.getRed());
	    assertEquals(color.getGreen(), findColor.getGreen());
	    assertEquals(color.getBlue(), findColor.getBlue());

	}

	// Color in upper case
	for (String value: COLORS) {
	    color = presenter.parseName(value.toUpperCase());
	    assertNotNull(color);
	    
	    Color findColor = findColor(value);

	    assertEquals(color.getRed(), findColor.getRed());
	    assertEquals(color.getGreen(), findColor.getGreen());
	    assertEquals(color.getBlue(), findColor.getBlue());
	}


    }
    
    
    
    @Test
    public void testToValue() throws Exception {
	ColorPresenter presenter = new ColorPresenter();
	
	// Test invalid
	Color color = (Color) presenter.toValue(null);
	assertNull(color);
	
	color = (Color) presenter.toValue("");
	assertNull(color);

	for (String value: HEX_INVALID_VALUES) {
	    color = (Color) presenter.toValue(value);
	    assertNull(color);
	}

	for (String value: RGB_INVALID_VALUES) {
	    color = (Color) presenter.toValue(value);
	    assertNull(color);
	}
	
	
	color = presenter.parseName("blahblah");
	assertNull(color);
	
	// Test valid
	
	// Color in lower case
	for (String value: COLORS) {
	    color = (Color) presenter.toValue(value);
	    assertNotNull(color);
	    
	    Color findColor = findColor(value);

	    assertEquals(color.getRed(), findColor.getRed());
	    assertEquals(color.getGreen(), findColor.getGreen());
	    assertEquals(color.getBlue(), findColor.getBlue());
	}

	// Color in upper case
	for (String value: COLORS) {
	    color = (Color) presenter.toValue(value.toUpperCase());
	    assertNotNull(color);
	    
	    Color findColor = findColor(value);

	    assertEquals(color.getRed(), findColor.getRed());
	    assertEquals(color.getGreen(), findColor.getGreen());
	    assertEquals(color.getBlue(), findColor.getBlue());
	}

	// Hex
	color = (Color) presenter.toValue("#112233");
	assertNotNull(color);
	assertEquals("#112233", "#" + color.toHexString());

	color = (Color) presenter.toValue("#aabbcc");
	assertNotNull(color);
	assertEquals("#aabbcc", "#" + color.toHexString());

	// RGB
	color = (Color) presenter.toValue("rgb(11, 22, 33)");
	assertNotNull(color);
	assertEquals(color.getRed(), 11);
	assertEquals(color.getGreen(), 22);
	assertEquals(color.getBlue(), 33);

    }
    
    @Test
    public void testToString() throws Exception {
	ColorPresenter presenter = new ColorPresenter();
	
	// Test invalid
	String colorString = presenter.toString(null);
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
	colorString = presenter.toString(color);
	assertNotNull(colorString);
	assertEquals(colorHex, colorString);

    }
    
    private Color findColor(String name) {
	//TODO: Find color in other storage
	return ColorPresenter.getColorByName(name);
    }
    
    private String toHexString(int value) {
	return value < 16 ? "0" + Integer.toHexString(value) : Integer.toHexString(value);
    }
}

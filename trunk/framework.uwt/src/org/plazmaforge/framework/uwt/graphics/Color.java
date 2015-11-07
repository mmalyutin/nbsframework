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

package org.plazmaforge.framework.uwt.graphics;


public class Color extends Resource {

    
    /**
     * The color white.  In the default sRGB space.
     */
    public final static Color WHITE = new Color(255, 255, 255);

    /**
     * The color light gray.  In the default sRGB space.
     */
    public final static Color LIGHT_GRAY = new Color(192, 192, 192);

    /**
     * The color gray.  In the default sRGB space.
     */
    public final static Color GRAY = new Color(128, 128, 128);

    /**
     * The color dark gray.  In the default sRGB space.
     */
    public final static Color DARK_GRAY = new Color(64, 64, 64);

    /**
     * The color black.  In the default sRGB space.
     */
    public final static Color BLACK = new Color(0, 0, 0);
    

    /**
     * The color red.  In the default sRGB space.
     */
    public final static Color RED = new Color(255, 0, 0);

    /**
     * The color pink.  In the default sRGB space.
     */
    public final static Color PINK = new Color(255, 175, 175);

    /**
     * The color orange.  In the default sRGB space.
     */
    public final static Color ORANGE = new Color(255, 200, 0);

    /**
     * The color yellow.  In the default sRGB space.
     */
    public final static Color YELLOW = new Color(255, 255, 0);


    /**
     * The color green.  In the default sRGB space.
     */
    public final static Color GREEN = new Color(0, 255, 0);

    /**
     * The color magenta.  In the default sRGB space.
     */
    public final static Color MAGENTA = new Color(255, 0, 255);

    /**
     * The color cyan.  In the default sRGB space.
     */
    public final static Color CYAN = new Color(0, 255, 255);

    /**
     * The color blue.  In the default sRGB space.
     */
    public final static Color BLUE = new Color(0, 0, 255);


    
    private int red;
    
    private int green;
    
    private int blue;

    private float alpha;
    
    public static final float DEFAULT_ALPHA = 1.0f; 

    public Color(int red, int green, int blue) {
	this(red, green, blue, DEFAULT_ALPHA);
    }
    
    public Color(int red, int green, int blue, float alpha) {
	this.red = red;
	this.green = green;
	this.blue = blue;
	this.alpha = (alpha < 0 || alpha > DEFAULT_ALPHA) ? DEFAULT_ALPHA : alpha;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
    
    
    public float getAlpha() {
        return alpha;
    }
    
    public static String getKey(Color color) {
	if (color == null) {
	    return null;
	}
	return color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + (color.getAlpha() == DEFAULT_ALPHA ? "" : (", " + color.getAlpha()));
    }
    
    public String getKey() {
	return getKey(this);
    }
    

    /**
     * Return hex presentation of color
     * @return
     */
    public String toHexString() {
	return toHexString(getRed()) + toHexString(getGreen()) + toHexString(getBlue());
    }
    
    private String toHexString(int value) {
	return value < 16 ? "0" + Integer.toHexString(value) : Integer.toHexString(value);
    }
    
    
    public static String toRGBString(Color color) {
	if (color == null) {
	    return null;
	}
	return "rgb(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ")";
    }

    public String toRGBString() {
	return toRGBString(this);
    }

    public static String toRGBAString(Color color) {
	if (color == null) {
	    return null;
	}
	return "rgba(" + color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + (color.getAlpha() == DEFAULT_ALPHA ? "" : (", " + color.getAlpha())) + ")";
    }

    public String toRGBAString() {
	return toRGBAString(this);
    }

    
}

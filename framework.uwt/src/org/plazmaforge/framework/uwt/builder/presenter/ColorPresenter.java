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

import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.data.presenter.AbstractValuePresenter;
import org.plazmaforge.framework.uwt.graphics.Color;

public class ColorPresenter extends AbstractValuePresenter  {

    // Hex
    // RGB
    // Names
    
    
    private static Map<String, Color> colors = new HashMap<String, Color>();
    
    static {
	colors.put("white", Color.WHITE);
	colors.put("lightgray", Color.LIGHT_GRAY);
	colors.put("gray", Color.GRAY);
	colors.put("darkgray", Color.DARK_GRAY);
	colors.put("black", Color.BLACK);
	colors.put("red", Color.RED);
	colors.put("pink", Color.PINK);
	colors.put("orange", Color.ORANGE);
	colors.put("yellow", Color.YELLOW);
	colors.put("green", Color.GREEN);
	colors.put("magenta", Color.MAGENTA);
	colors.put("cyan", Color.CYAN);
	colors.put("blue", Color.BLUE);	
    }
    
    @Override
    public String toString(Object value) {
	if (value == null) {
	    return null;
	}
	Color color = (Color) value;
	return "#" + color.toHexString();
    }

    @Override
    public Object toValue(String str) {
	if (str == null) {
	    return null;
	}
	if (str.startsWith("#")) {
	    return parseHex(str);
	}
		
	if (str.startsWith("rgb")) {
	    return parseRGB(str);
	}
	
	return parseName(str);
    }
    
    

    
    protected Color parseHex(String str) {
	if (str == null || !str.startsWith("#") || str.length() != 7) {
	    //CFE
	    return null;
	}
	String rs = str.substring(1, 3);
	String gs = str.substring(3, 5);
	String bs = str.substring(5, 7);
	return parseRGB(rs, gs, bs, 16);
    }
    
    protected Color parseRGB(String str) {
	if (str == null || !str.startsWith("rgb(") || !str.endsWith(")")) {
	    //CFE
	    return null;
	}
	String body = str.substring(4, str.length() - 1);
	String[] values = body.split(",");
	if (values == null || values.length != 3) {
	    return null;
	}
	String rs = normalize(values[0]);
	String gs = normalize(values[1]);
	String bs = normalize(values[2]);
	return parseRGB(rs, gs, bs, 10);
    }

    protected Color parseRGB(String rs, String gs, String bs, int radix) {
	try {
	    int r = Integer.parseInt(rs, radix);
	    int g = Integer.parseInt(gs, radix);
	    int b = Integer.parseInt(bs, radix);
	    return createColor(r, g, b);
	} catch (NumberFormatException e) {
	    //NFE
	}
	return null;
    }
    protected Color parseName(String str) {
	if (str == null) {
	    return null;
	}
   	return getColorByName(str);
    }
    
    protected Color createColor(int r, int g, int b) {
	if (!isValid(r) || !isValid(g) || !isValid(b)) {
	    return null;
	}
	return new Color(r, g, b);
    }
    
    protected boolean isValid(int value) {
	return value >=0 && value <= 255;
    }
    
    
    protected String normalize(String str) {
	return str == null ? null : str.trim();
    }
    
    public static Color getColorByName(String name) {
	if (name == null) {
	    return null;
	}
	return colors.get(name.toLowerCase());
    }

}

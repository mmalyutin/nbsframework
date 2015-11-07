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
import org.plazmaforge.framework.uwt.graphics.Font;

public class FontPresenter extends AbstractValuePresenter {

    private static final String ATTR_NAME = "name";
    
    private static final String ATTR_SIZE = "size";
    
    private static final String ATTR_STYLE = "style";
    
    
    private static final String BOLD = "bold";
    
    private static final String ITALIC = "italic";
    
    
    
    @Override
    public String toString(Object value) {
	if (value == null) {
	    return null;
	}

	Font font = (Font) value;
	StringBuffer buf = new StringBuffer();
	boolean first = true;

	// Font, size, style
	// Font, size	
	// Font, style
	// Font
	// size, style
	// size
	// style
	
	// Name
	String attr = toFontName(font);
	if (attr != null) {
	    first = false;
	    buf.append(attr);
	}
	
	// Size
	attr = toFontSize(font);
	if (attr != null) {
	    if (!first) {
		buf.append(", ");
	    }
	    first = false;
	    buf.append(attr);
	}
	
	// Style
	attr = toFontStyle(font);
	if (attr != null) {
	    if (!first) {
		buf.append(", ");
	    }
	    first = false;
	    buf.append(attr);
	}

	return buf.length() == 0 ? null : buf.toString();
    }
    
    @Override
    public Object toValue(String str) {
	if (str == null) {
	    return null;
	}
	String[] values = str.split(",");
	if (values == null || values.length == 0 || values.length > 3) {
	    return null;
	}
	
	Map<String, Object> map = new HashMap<String, Object>();
	
	if (values.length == 3) {
	    map.put(ATTR_NAME, parseName(normalizeString(values[0])));
	    map.put(ATTR_SIZE, parseSize(normalizeString(values[1])));
	    map.put(ATTR_STYLE, parseStyle(normalizeString(values[2])));
	} else if (values.length == 2) {
	    
	    FontAttribute fa1 = getFontAttribute(normalizeString(values[0]));
	    FontAttribute fa2 = getFontAttribute(normalizeString(values[1]));	    
	   
	    if (fa1 != null) {
		addFontAttribute(map, fa1);
	    }
	    if (fa2 != null) {
		addFontAttribute(map, fa2);
	    }
	    
	} else if (values.length == 1) {
	    FontAttribute fa = getFontAttribute(normalizeString(values[0]));
	    if (fa != null) {
		addFontAttribute(map, fa);
	    }
	}

	
	return createFont(map);
	
    }
    
    protected Font createFont(Map<String, Object> map) {
	String name = (String) map.get(ATTR_NAME);
	Integer size = (Integer) map.get(ATTR_SIZE);
	Integer style = (Integer) map.get(ATTR_STYLE);
	if (name == null && size == null && style == null) {
	    return null;
	}
	return new Font(name, size == null ? 0 : size, style == null ? 0 : style);
    }
    
    protected void addFontAttribute(Map<String, Object> map, FontAttribute attr) {
	if (attr == null) {
	    return;
	}
	map.put(attr.getName(), attr.getValue());
    }
    
    /**
     * Return name of font attribute by value
     * @param value
     * @return
     */
    protected FontAttribute getFontAttribute(String value) {
	if (value == null ){
	    return null;
	}
	value = value.trim();
	if (value.isEmpty()) {
	    return null;
	}

	// try parse size
	Integer size = parseSize(value);
	
	if (size != null) {
	    return new FontAttribute(ATTR_SIZE, size);
	}
	
	 // try parse style
	Integer style = parseStyle(value);
	
	if (style != null) {
	    return new FontAttribute(ATTR_STYLE, style);
	}
	
	// try parse name
	String name = parseName(value);
	if (name != null) {
	    return new FontAttribute(ATTR_NAME, name);
	}
	
	return null;
    }
    
    public static class FontAttribute {
	
	private String name;
	private Object value;
	
	public FontAttribute(String name, Object value) {
	    this.name = name;
	    this.value = value;
	}

	public String getName() {
	    return name;
	}

	public Object getValue() {
	    return value;
	}
	
	
	
    }
    
    protected String toFontName(Font font) {
	if (font == null) {
	    return null;
	}
	String name = font.getName();
	if (name == null) {
	    return null; 
	}
	return  name.trim();
    }
    
    protected String toFontSize(Font font) {
	if (font == null) {
	    return null;
	}
	int size = font.getSize();
	if (size <= 0) {
	    return null;
	}
	return "" + font.getSize();
    }

    protected String toFontStyle(Font font) {
	if (font == null) {
	    return null;
	}
	int style = font.getStyle();
	if (style <= 0) {
	    return null;
	}
	
	StringBuffer buf = new StringBuffer();
	boolean first = true;
	
	// Bold
	if (font.isBold()) {
	    buf.append(BOLD);
	    first = false;
	}
	
	// Italic
	if (font.isItalic()) {
	    if (!first) {
		buf.append("|");
	    }
	    buf.append(ITALIC);
	    first = false;
	}
	
	// (?) Underline
	// (?) Strikeout
	
	return buf.toString();
    }

    protected String parseName(String value) {
	if (value == null || value.isEmpty()) {
	    return null;
	}
	return value.trim();
    }


    protected Integer parseSize(String value) {
	if (value == null) {
	    return null;
	}
	try {
	    return Integer.valueOf(value);
	} catch (NumberFormatException e) {
	    
	}
	return null;
    }

    protected Integer parseStyle(String value) {
	if (value == null) {
	    return null;
	}
	String[] attributes = value.split("\\|");
	if (attributes == null || attributes.length == 0) {
	    return null;
	}
	int style = 0;
	for (String attribute: attributes) {
	    attribute = attribute.trim();
	    if (isBoldStyle(attribute)) {
		style |= Font.BOLD;
	    } else if (isItalicStyle(attribute)) {
		style |= Font.ITALIC;
	    } else {
		// Parse error: invalid style
		return null;
	    }
	}
	return style == 0 ? null : style;
    }

    protected boolean isBoldStyle(String value) {
	return BOLD.equals(value);
    }
    
    protected boolean isItalicStyle(String value) {
	return ITALIC.equals(value);
    }


}

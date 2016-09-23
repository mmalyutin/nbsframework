/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.report.storage.xml;

import org.jdom.Element;
import org.plazmaforge.framework.core.data.formatter.FormatterManager;
import org.plazmaforge.framework.uwt.builder.formatter.type.ColorFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.type.FontFormatter;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

public class XMLWorker {

    protected static final ColorFormatter COLOR_FORMATTER = new ColorFormatter();
    protected static final FontFormatter FONT_FORMATTER = new FontFormatter();
    
    private FormatterManager formatterManager;
    
    ////

    protected  FormatterManager getFormatterManager() {
	if (formatterManager == null) {
	    formatterManager = new FormatterManager();
	    formatterManager.registerDefaultFormatters();
	}
	return formatterManager;
    }
    
    protected String normalizeString(String str) {
	if (str == null) {
	    return null;
	}
	str = str.trim();
	return str.isEmpty() ? null : str;
    }

    //// GET-VALUE
    
    protected int intValue(Element element, String name) {
	return getIntegerValue(element, name, 0);
    }
    
    protected Integer getIntegerValue(Element element, String name) {
	return getIntegerValue(element, name, null);
    }
    
    protected Integer getIntegerValue(Element element, String name, Integer def) {
	String value = getStringValue(element, name);
	if (value == null) {
	    return def;
	}
	try {
	    return Integer.valueOf(value);
	} catch (NumberFormatException ex) {
	    return def;
	}
    }
    
    protected String getStringValue(Element element, String name) {
	if(element == null || name == null) {
	    return null;
	}
	String value = element.getAttributeValue(name);
	return normalizeString(value);
    }

    
    protected Color getColor(Element element, String name) {
	if(element == null || name == null) {
	    return null;
	}

	// color attribute (foreground, background)
	String value = getStringValue(element, name);
	if (value == null) {
	    return null;
	}
	Color color = (Color) COLOR_FORMATTER.parse(value);
	return color;
    }

    protected Font getFont(Element element, String name) {
	if(element == null || name == null) {
	    return null;
	}

	// font attribute: name, size, style
	String value = getStringValue(element, name);
	if (value == null) {
	    return null;
	}
	Font font = (Font) FONT_FORMATTER.parse(value);
	return font;
    }
    
    protected String getContentValue(Element element) {
	if (element == null) {
	    return null;
	}
	return element.getText();
    }
    
    
    
    //// SET_VALUE
    
    protected void setStringValue(Element element, String name, String value) {
 	if (value == null) {
 	    return;
 	}
    	element.setAttribute(name, value.toString());
     }

     protected void setIntegerValue(Element element, String name, Integer value) {
    	setStringValue(element, name, toString(value, "Integer"));
     }
     
     protected void setColor(Element element, String name, Color color) {
 	setStringValue(element, name, color == null ? null : COLOR_FORMATTER.format(color));
     }
     
     protected void setFont(Element element, String name, Font font) {
 	setStringValue(element, name, font == null ? null : FONT_FORMATTER.format(font));
     }
     
     protected void setContentValue(Element element, Object value) {
 	setContentValue(element, value, null);
     }
     
     protected void setContentValue(Element element, Object value, String dataType) {
 	if (value == null) {
 	    return;
 	}
 	String str = toString(value, dataType);
 	if (str == null) {
 	    return;
 	}
 	str = normalizeString(str);
 	if (str == null) {
 	    return;
 	}
 	element.setText(str);
     }
     
     protected String toString(Object value, String dataType) {
 	if (value == null) {
 	    return null;
 	}
 	// TODO: dataType
 	String str = value.toString();
 	return normalizeString(str);
     }
     
    
}

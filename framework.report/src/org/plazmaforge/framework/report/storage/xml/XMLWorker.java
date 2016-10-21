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
import org.plazmaforge.framework.core.data.formatter.RWFormatterManager;
import org.plazmaforge.framework.util.StringUtils;
import org.plazmaforge.framework.uwt.builder.formatter.type.ColorFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.type.FontFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.type.HorizontalAlignFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.type.VerticalAlignFormatter;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

public class XMLWorker {

    protected static final ColorFormatter COLOR_FORMATTER = new ColorFormatter();
    protected static final FontFormatter FONT_FORMATTER = new FontFormatter();
    protected static final HorizontalAlignFormatter HORIZONTAL_ALIGN_FORMATTER = new HorizontalAlignFormatter();
    protected static final VerticalAlignFormatter VERTICAL_ALIGN_FORMATTER = new VerticalAlignFormatter();
    
    private FormatterManager formatterManager;
    
    ////

    protected  FormatterManager getFormatterManager() {
	if (formatterManager == null) {
	    // Read/Write FormatterManager
	    formatterManager = new RWFormatterManager();
	    formatterManager.init();
	}
	return formatterManager;
    }
    
    protected String normalizeString(String str) {
	return StringUtils.normalizeString(str);
    }

    
    ////GET-SET-TYPED-VALUE

    /*
    protected Object getTypedValue(Element element, String name, String dataType) {
	String value = getStringValue(element, name);
	if (value == null) {
	    return null;
	}
	return getFormatterManager().toValue(value, dataType);
    }
    
    protected void setTypedValue(Element element, String name, String dataType, Object value) {
	if (value == null) {
 	    return;
 	}
	setStringValue(element, name, getFormatterManager().toString(value, dataType));
    }
    */

    
    protected Object getTValue(String dataType, String value) {
	if (value == null) {
	    return null;
	}
	return getFormatterManager().toValue(value, dataType);
    }
    
    protected String getTString(String dataType, Object value) {
	if (value == null) {
 	    return null;
 	}
	return getFormatterManager().toString(value, dataType);
    }
    
    
    ////GET-VALUE
    
    
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
	// color attribute (foreground, background)
	return (Color) COLOR_FORMATTER.parse(getStringValue(element, name));
    }

    protected Font getFont(Element element, String name) {
	// font attribute: name, size, style
	return (Font) FONT_FORMATTER.parse(getStringValue(element, name));
    }
    
    protected HorizontalAlign getHorizontalAlign(Element element, String name) {
 	return (HorizontalAlign) HORIZONTAL_ALIGN_FORMATTER.parse(getStringValue(element, name));
   }

    protected VerticalAlign getVerticalAlign(Element element, String name) {
 	return (VerticalAlign) VERTICAL_ALIGN_FORMATTER.parse(getStringValue(element, name));
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
    	setStringValue(element, name, getTString("Integer", value));
     }
     
     protected void setColor(Element element, String name, Color color) {
 	setStringValue(element, name, color == null ? null : COLOR_FORMATTER.format(color));
     }
     
     protected void setFont(Element element, String name, Font font) {
 	setStringValue(element, name, font == null ? null : FONT_FORMATTER.format(font));
     }

     protected void setHorizontalAlign(Element element, String name, HorizontalAlign horizontalAlign) {
 	setStringValue(element, name, horizontalAlign == null ? null : HORIZONTAL_ALIGN_FORMATTER.format(horizontalAlign));
     }

     protected void setVerticalAlign(Element element, String name, VerticalAlign verticalAlign) {
 	setStringValue(element, name, verticalAlign == null ? null : VERTICAL_ALIGN_FORMATTER.format(verticalAlign));
     }
     
     protected void setContentValue(Element element, Object value) {
 	setContentValue(element, value, null);
     }
     
     protected void setContentValue(Element element, Object value, String dataType) {
 	if (value == null) {
 	    return;
 	}
 	String str = getTString(dataType, value);
 	if (str == null) {
 	    return;
 	}
 	str = normalizeString(str);
 	if (str == null) {
 	    return;
 	}
 	element.setText(str);
     }
     
//     protected String toString(Object value, String dataType) {
// 	if (value == null) {
// 	    return null;
// 	}
// 	// TODO: dataType
// 	String str = value.toString();
// 	return normalizeString(str);
//     }
     
    
}

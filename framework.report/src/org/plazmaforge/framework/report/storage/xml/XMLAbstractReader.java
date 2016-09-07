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

/**
 * 
 */
package org.plazmaforge.framework.report.storage.xml;

import java.util.List;

import org.jdom.Element;
import org.plazmaforge.framework.core.data.formatter.FormatterManager;
import org.plazmaforge.framework.report.model.base.Insets;
import org.plazmaforge.framework.report.model.base.Margin;
import org.plazmaforge.framework.report.model.base.Size;
import org.plazmaforge.framework.uwt.builder.formatter.type.ColorFormatter;
import org.plazmaforge.framework.uwt.graphics.Color;

/**
 * @author ohapon
 *
 */
public class XMLAbstractReader implements XMLInfo  {

    
    protected static final ColorFormatter COLOR_FORMATTER = new ColorFormatter();
    
    private FormatterManager formatterManager;
    
    
    protected int intValue(Element element, String name) {
	return getIntegerValue(element, name, 0);
    }
    
    protected Integer getIntegerValue(Element element, String name) {
	return getIntegerValue(element, name, null);
    }
    
    protected Integer getIntegerValue(Element element, String name, Integer def) {
	String value = getValue(element, name);
	if (value == null) {
	    return def;
	}
	try {
	    return Integer.valueOf(value);
	} catch (NumberFormatException ex) {
	    return def;
	}
    }
    
    protected String getValue(Element element, String name) {
	if(element == null || name == null){
	    return null;
	}
	String value = element.getAttributeValue(name);
	return normalizeString(value);
    }

    protected String getContentValue(Element element) {
	if (element == null) {
	    return null;
	}
	return element.getText();
    }

    protected Element getChild(Element parent, String name) {
	return parent.getChild(name, parent.getNamespace());	
    }

    protected List getChildren(Element parent, String name) {
	return parent.getChildren(name, parent.getNamespace());	
    }

    protected boolean useNamespace() {
	return true;
    }

    ////
    
    /**
     * Get Size by 'width, 'height' attributes
     * @param element
     * @return
     */
    protected Size getSizeByAttributes(Element element) {
	if(element == null) {
	    return null;
	}
	
	Size size = null;
	
	// width
	Integer iValue = getIntegerValue(element, XML_ATTR_WIDTH);
	if (iValue != null) {
	    size = new Size();
	    size.setWidth(iValue);
	}
	
	// height
	iValue = getIntegerValue(element, XML_ATTR_HEIGHT);
	if (iValue != null) {
	    if (size == null) {
		size = new Size();
	    }
	    size.setHeight(iValue);
	}
	
	return size;

    }
    
    protected Color getColor(Element element, String name) {
	if(element == null || name == null) {
	    return null;
	}

	// width
	String value = getValue(element, name);
	if (value == null) {
	    return null;
	}
	Color color = (Color) COLOR_FORMATTER.parse(value);
	return color;
    }

    /**
     * Get margin by element attributes
     * @param element
     * @return
     */
    protected Margin getMarginByAttributes(Element element) {
	if(element == null) {
	    return null;
	}
	Margin margin = new Margin();
	Integer iValue = null;

	// margin
	iValue = getIntegerValue(element, XML_ATTR_MARGIN);
	if (iValue != null) {
	    margin.setValue(iValue);
	}
	
	 // left
	iValue = getIntegerValue(element, XML_ATTR_MARGIN_LEFT);
	if (iValue != null) {
	    margin.setLeft(iValue);
	}
	
	// top
	iValue = getIntegerValue(element, XML_ATTR_MARGIN_TOP);
	if (iValue != null) {
	    margin.setTop(iValue);
	}
	
	// right
	iValue = getIntegerValue(element, XML_ATTR_MARGIN_RIGHT);
	if (iValue != null) {
	    margin.setRight(iValue);
	}
	
	// bottom
	iValue = getIntegerValue(element, XML_ATTR_MARGIN_BOTTOM);
	if (iValue != null) {
	    margin.setBottom(iValue);
	}
	    
	return margin.isEmpty() ? null : margin;
    }
    
    protected Margin getMarginByNode(Element element) {
	if(element == null) {
	    return null;
	}
	Margin margin = new Margin();
	loadInsets(element, margin);
	return margin.isEmpty() ? null : margin;
    }
	
    
    protected void loadInsets(Element element, Insets margin) {
	if(element == null) {
	    return;
	}
	
	Integer iValue = null;

	 // left
	iValue = getIntegerValue(element, XML_ATTR_LEFT);
	if (iValue != null) {
	    margin.setLeft(iValue);
	}
	
	// top
	iValue = getIntegerValue(element, XML_ATTR_TOP);
	if (iValue != null) {
	    margin.setTop(iValue);
	}
	
	// right
	iValue = getIntegerValue(element, XML_ATTR_RIGHT);
	if (iValue != null) {
	    margin.setRight(iValue);
	}
	
	// bottom
	iValue = getIntegerValue(element, XML_ATTR_BOTTOM);
	if (iValue != null) {
	    margin.setBottom(iValue);
	}
	    
    }
    
    
    /**
     * Get margin by 'margin' node
     * @param element
     * @return
     */
    protected Margin getMargin(Element element) {
	if(element == null) {
	    return null;
	}
	// Get margin by attributes
	Margin margin = getMarginByAttributes(element);
	
	// Get margin by node
	Margin marginByNode = getMarginByNode(element.getChild(XML_MARGIN));
	if (marginByNode == null) {
	    return margin;
	}
	if (margin == null){
	    return marginByNode;
	}
	margin.merge(marginByNode);
	return margin.isEmpty() ? null : margin;
    }

    protected  FormatterManager getFormatterManager() {
	if (formatterManager == null) {
	    formatterManager = new FormatterManager();
	    formatterManager.registerDefaultFormatters();
	}
	return formatterManager;
    }
    
    ////
    
    protected String normalizeString(String str) {
	if (str == null) {
	    return null;
	}
	str = str.trim();
	return str.isEmpty() ? null : str;
    }

}

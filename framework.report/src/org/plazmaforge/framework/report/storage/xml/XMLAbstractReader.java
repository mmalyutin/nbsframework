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

/**
 * @author ohapon
 *
 */
public class XMLAbstractReader {

    
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
    
    protected String normalizeString(String str) {
	if (str == null) {
	    return null;
	}
	str = str.trim();
	return str.isEmpty() ? null : str;
    }

}

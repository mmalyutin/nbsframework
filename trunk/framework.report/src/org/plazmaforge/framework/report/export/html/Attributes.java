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

package org.plazmaforge.framework.report.export.html;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author ohapon
 *
 */
public class Attributes {

    private Map<String, String> attributes;

    public Attributes() {
	super();
	attributes = new LinkedHashMap<String, String>();
    }
    
    public Attributes(String name, String value) {
	this();
	addAttribute(name, value);
    }
    
    public Attributes(Map<String, String> attributes) {
 	this();
 	Set<Map.Entry<String, String>> entries = attributes.entrySet();
	String name = null;
	String value = null;
	for (Map.Entry<String, String> entry: entries) {
	    addAttribute(entry.getKey(), entry.getValue());
	}
     }    
    
    public void addAttribute(String name, String value) {
	attributes.put(name, value);
    }

    public void removeAttribute(String name) {
	attributes.remove(name);
    }
    
    public void clear() {
	attributes.clear();
    }
    
    public String toStyleString() {
	return toAttributesString(attributes, ";", ": ");
    }
    
    public String toAttributesString() {
	return toAttributesString(attributes, "", "=", "\"");
    }
    
    public static String toAttributesString(Map<String, String> attributes, String separator, String eq) {
	return toAttributesString(attributes, separator, eq, null);
    }
    
    public static String toAttributesString(Map<String, String> attributes, String separator, String eq, String bracket) {
	if (attributes == null) {
	    return null;
	}
	if (attributes.isEmpty()) {
	    return "";
	}
	if (separator == null) {
	    separator = "";
	}
	if (eq == null) {
	    eq = "=";
	}
	StringBuffer buf = new StringBuffer();
	Set<Map.Entry<String, String>> entries = attributes.entrySet();
	String name = null;
	String value = null;
	for (Map.Entry<String, String> entry: entries) {
	    if (buf.length() > 0) {
		buf.append(separator + " ");
	    }
	    name = entry.getKey();
	    value = bracket == null ? entry.getValue() : (bracket + entry.getValue() + bracket);
	    buf.append(name + eq + value);
	}
	return buf.toString();
    }
    
    public String toStyleAttribute() {
	return toStyleAttribute("style");
    }
    
    public String toStyleAttribute(String name) {
	if (name == null) {
	    return null;
	}
	return new Attributes(name, this.toStyleString()).toAttributesString();
    }
    
}

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

package org.plazmaforge.framework.uwt.storage;

import org.plazmaforge.framework.util.StringTokenizer;

public class UIStorageUtils {
    
    public static final String DEFAULT_DELIM = "_";
    
    public static String toCamelCase(String str, String delim) {
	if (str == null) {
	    return null;
	}
	if (delim == null) {
	    delim = DEFAULT_DELIM;
	}
	
	// Transform all string to lower case
	str = str.toLowerCase();
	
	StringBuffer buf = new StringBuffer();
	StringTokenizer t = new StringTokenizer(str, delim);
	boolean first = true;
	String token = null;
	while (t.hasMoreTokens()) {
	    token = t.nextToken();
	    if (!first) {
		if (token.length() > 0) {
		    token = token.substring(0, 1).toUpperCase() + (token.length() > 1 ? token.substring(1) : "");
		}
	    }
	    first = false;
	    buf.append(token);
	}
	return buf.toString();
    }

}

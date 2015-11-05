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

package org.plazmaforge.framework.core.validation;

import java.util.List;

public class ValidationUtils {

    private ValidationUtils() {
	
    }
    
    public static String getErrorMessage(List<ValidationResult> result) {
	return getErrorMessage(result, null);
    }
    
    public static String getErrorMessage(List<ValidationResult> result, String delim) {
	if (result == null || result.isEmpty()) {
	    return null;
	}
	StringBuffer buf = new StringBuffer();
	if (delim == null) {
	    delim = " ";
	}
	boolean first = true;
	for (ValidationResult r: result) {
	    if (r.getErrorMessage() == null) {
		continue;
	    }
	    if (!first) {
		buf.append(delim);
	    }
	    buf.append(r.getErrorMessage());
	    first = false;
	}
	return buf.toString();
    }
}

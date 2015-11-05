/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
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

/*
 * Created on 31.05.2004
 *
 */
package org.plazmaforge.framework.core.exception;

/**
 * @author ohapon
 */
public class AccessDeniedException extends Exception {

    private static final long serialVersionUID = -6651850213499519065L;
    
    public static String ACCESS_DENIED = "Access is denied";
    
    
    public static AccessDeniedException getException(Throwable e) {
	if (e == null) {
	    return null;
	}
	if (e instanceof AccessDeniedException) {
	    return ((AccessDeniedException) e);
	}
	if (e instanceof Throwable) {
	    
	    // 1. level
	    Throwable clause = ((Throwable) e).getCause();
	    if (clause == null) {
		return null;
	    }
	    if (clause instanceof AccessDeniedException) {
		return (AccessDeniedException) clause;
	    }
	    
	    
	    // 2. level
	    clause = clause.getCause();
	    if (clause == null) {
		return null;
	    }
	    if (clause instanceof AccessDeniedException) {
		return (AccessDeniedException) clause;
	    }

	}
	return null;
    }
    
    
    public AccessDeniedException() {
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}

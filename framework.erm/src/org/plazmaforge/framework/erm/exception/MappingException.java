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

/**
 * 
 */
package org.plazmaforge.framework.erm.exception;

import org.plazmaforge.framework.erm.ERMException;

/**
 * @author ohapon
 *
 */
public class MappingException extends ERMException {

    private static final long serialVersionUID = -9350260216040153L;

    public MappingException() {
    }

    public MappingException(String message, Throwable cause) {
	super(message, cause);
    }

    public MappingException(String message) {
	super(message);
    }

    public MappingException(Throwable cause) {
	super(cause);
    }

}

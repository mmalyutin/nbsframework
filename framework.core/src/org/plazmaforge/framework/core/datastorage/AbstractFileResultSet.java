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

package org.plazmaforge.framework.core.datastorage;

import java.io.IOException;
import java.util.List;

import org.plazmaforge.framework.core.exception.DSException;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractFileResultSet extends AbstractResultSet {

    public AbstractFileResultSet() {
	super();
    }

    public AbstractFileResultSet(List<String> fieldNames) {
	super(fieldNames);
    }
    
    protected void handleIOException(IOException ex) throws DSException {
	if (ex == null) {
	    return;
	}
	throw new DSException(ex);
    }

    
}

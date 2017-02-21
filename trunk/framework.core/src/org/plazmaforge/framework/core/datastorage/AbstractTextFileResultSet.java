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
import java.io.Reader;
import java.util.List;

import org.plazmaforge.framework.core.exception.DSException;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractTextFileResultSet extends AbstractFileResultSet {

    protected Reader reader;

    public AbstractTextFileResultSet() {
	super();
    }

    public AbstractTextFileResultSet(List<String> fieldNames) {
	super(fieldNames);
    }

    public AbstractTextFileResultSet(Reader reader) {
	super();
	this.reader = reader;
    }
    
    public AbstractTextFileResultSet(List<String> fieldNames, Reader reader) {
	super(fieldNames);
	this.reader = reader;
    }
    
    
    @Override
    public boolean isEmpty() throws DSException {
	// TODO
	return reader == null;
    }

    @Override
    public boolean isClosed() throws DSException {
	return reader == null;
    }

    @Override
    public void close() throws DSException {
	if (reader == null) {
	    return;
	}
	try {
	    reader.close();
	    reader = null;
	} catch (IOException ex) {
	    handleIOException(ex);
	}
    }
    
  
}

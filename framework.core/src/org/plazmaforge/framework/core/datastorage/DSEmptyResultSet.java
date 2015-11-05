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
package org.plazmaforge.framework.core.datastorage;

import java.io.Serializable;

import org.plazmaforge.framework.core.exception.DSException;

/**
 * @author ohapon
 *
 */
public class DSEmptyResultSet implements DSScrollableResultSet, Serializable {

    private static final long serialVersionUID = 9108216287910137742L;
    

    @Override
    public boolean first() throws DSException {
	return false;
    }

    @Override
    public boolean move(int index) throws DSException  {
	return false;
    }

    @Override
    public boolean last() throws DSException {
	return false;
    }

    @Override
    public boolean next() throws DSException {
	return false;
    }
    
    @Override
    public boolean prev() throws DSException {
	return false;
    }

    @Override
    public boolean top() throws DSException {
	return false;
    }

    @Override
    public boolean bottom() throws DSException {
	return false;
    }
    
    @Override
    public Object getValue(String name) throws DSException {
	return null;
    }
    
    @Override
    public boolean canScroll() throws DSException {
	return false;
    }    

    @Override
    public boolean isEmpty() throws DSException {
	return true;
    }

    @Override
    public boolean isClosed() throws DSException {
	return true;
    }

    @Override
    public void close() throws DSException {
    }


   


}

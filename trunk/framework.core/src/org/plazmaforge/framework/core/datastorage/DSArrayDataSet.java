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


package org.plazmaforge.framework.core.datastorage;

import java.io.Serializable;
import java.util.List;

import org.plazmaforge.framework.core.exception.DSException;




/** 
 * @author ohapon
 */

public class DSArrayDataSet extends AbstractStructuredDataSet implements DSDataSet, Serializable {

    private static final long serialVersionUID = -5856700519145144717L;
    

    
    /**
     * List of records
     */
    private List<Object[]> records;


    /**
     * Position of current record
     */
    private int position = -1;

    
    
    public boolean isEmpty() throws DSException {
	return records == null ? true : records.isEmpty();  
    }

    public boolean first() throws DSException {
	if (isEmpty()) {
	    return false;
	}
	position = 0;
	return true;
    }
    
    public boolean move(int index) throws DSException {
	if (isEmpty() || index < 0 || index > getRecordCount() - 1) {
	    return false;
	}
	this.position = index;
	return true;
    }
    
    public boolean last() throws DSException {
	if (isEmpty()) {
	    return false;
	}
	position = getRecordCount() - 1;
	return true;
    }

    public boolean next() throws DSException {
	if (isEmpty()) {
	    return false;
	}
	if (position + 1 == getRecordCount()) {
	    return false;
	}
	position++;
	return true;
    }
    
    public void setData(List<Object[]> records) {
	this.records = records;
    }
    
    public Object[] getRecord() throws DSException {
	return getRecord(position);
    }

    public Object[] getRecord(int index) throws DSException {
	if (records == null || records.size() == 0 || index < 0 || index > records.size() - 1) {
	    return null;
	}
	return records.get(index);
    }

    public Object getValue(int index) throws DSException {
	return getRecord()[index];
    }

    public Object getValue(String name) throws DSException {
	int index = getFieldIndex(name);
	if (index < 0) {
	    return null;
	}
	return getValue(index);
    }

    public Object getValue(DSField field) throws DSException {
	int index = getFieldIndex(field == null ? null : field.getName());
	if (index < 0) {
	    return null;
	}
	return getValue(index);
    }
    

    public int getRecordCount() {
	return records == null ? 0 : records.size();
    }
    
    public int getRetrieveRecordCount() {
	return getRecordCount();
    }

    @Override
    public boolean isClosed() throws DSException {
	return records == null;
    }

    @Override
    public void close() throws DSException {
	if (records == null) {
	    return;
	}
	records.clear();
	records = null;
    }



    
}

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.exception.DSException;

/**
 * @author ohapon
 *
 */
public class DSWrappedDataSet extends AbstractStructuredDataSet implements DSDataSet {

    private DSResultSet resultSet;
    
    private Map<Integer, String> fieldIndexNameMap;

    public DSWrappedDataSet(List<DSField> fields, DSResultSet resultSet) {
	super();
	
	assert(fields != null);
	assert(resultSet != null);
	
	setFields(fields);
	this.resultSet = resultSet;
	
	fieldIndexNameMap = new HashMap<Integer, String>();
	
	int count = fields.size();
	for (int i = 0; i < count; i++) {
	    DSField field = fields.get(i);
	    if (field == null || field.getName() == null) {
		continue;
	    }
	    fieldIndexNameMap.put(i, field.getName());
	}
    }

    @Override
    public boolean next() throws DSException {
	return resultSet.next();
    }

    @Override
    public Object getValue(int index) throws DSException {
	return getValue(fieldIndexNameMap.get(index));
    }

    @Override
    public Object getValue(String name) throws DSException {
	return resultSet.getValue(name);
    }

    @Override
    public Object getValue(DSField field) throws DSException {
	return field == null ? null : field.getName();
    }
    
    @Override
    public boolean isEmpty() throws DSException {
	return resultSet.isEmpty();
    }

    @Override
    public boolean isClosed() throws DSException {
	return resultSet.isClosed();
    }

    @Override
    public void close() throws DSException {
	resultSet.close();
    }

    @Override
    public Object[] getRecord() throws DSException {
	List<DSField> fields = getFields();
	int count = fields.size();
	Object[] record = new Object[count];
	for (int i = 0; i < count; i++) {
	    DSField field = fields.get(i);
	    record[i] = getValue(field == null ? null : field.getName());
	}
	return record;
    }
    
    
    
}

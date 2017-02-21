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

import java.util.List;

import org.plazmaforge.framework.core.exception.DSException;

/**
 * @author ohapon
 *
 */
public class AbstractWrappedDataSet extends AbstractStructuredDataSet {

    protected DSResultSet resultSet;
    
    //@Override
    public boolean next() throws DSException {
	return resultSet.next();
    }

    //@Override
    public Object getValue(int index) throws DSException {
	DSField field = getField(index);
	return getValue(field);
    }

    //@Override
    public Object getValue(String fieldName) throws DSException {
	DSField field = getField(fieldName);
	return getValue(field);
    }

    //@Override
    public Object getValue(DSField field) throws DSException {
	if (field == null) {
	    return null;
	}
	String path = getPath(field);
	Object value = resultSet.getValue(path);
	return convertValue(value, field);
    }
    
    //@Override
    public boolean isEmpty() throws DSException {
	return resultSet.isEmpty();
    }

    //@Override
    public boolean isClosed() throws DSException {
	return resultSet.isClosed();
    }

    //@Override
    public void close() throws DSException {
	resultSet.close();
    }

    //@Override
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

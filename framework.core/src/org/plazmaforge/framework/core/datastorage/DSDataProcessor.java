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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.criteria.Operation;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * @author ohapon
 *
 */
public class DSDataProcessor {

    
    
    public DSResultSet processResultSet(DSResultSet resultSet, DSDataSource dataSource) throws DSException {
	if (resultSet == null) {
	    return null;
	}
	
	// No processing: ResultSet is (empty OR closed OR cache)
	if (resultSet.isEmpty() || resultSet.isClosed() || isCache(resultSet)) {
	    return resultSet;
	}
	
	// No processing: DataSource has (no fields OR (no filters and orders))
	if (!needProcessing(dataSource)) {
	    return resultSet;
	}
	
	List<DSField> fields = dataSource.getFields();
	List<DSFilter> filters = dataSource.getFilters();
	
	Map<String, Integer> fieldIndexMap = createFieldIndexMap(fields);
	List<String> fieldNames = createFieldNames(fields);
	
	List<Object[]> records = new ArrayList<Object[]>();
	Object[] record = null;
	
	// Load all data to DSArrayResultSet
	while (resultSet.next()) {
	    
	    // Get record
	    record = getRecord(resultSet, fields);
	    
	    // Apply filters
	    if (isFilter(record, fieldIndexMap, filters)) {
		records.add(record);
	    }
	}
	
	DSArrayResultSet result = new DSArrayResultSet(fieldNames, records);
	//result.setData(records);
	
	// Close input origin ResultSet
	resultSet.close();
	
	//TODO: orders
	return result;
    }
    
    protected Map<String, Integer> createFieldIndexMap(List<DSField> fields) {
	Map<String, Integer> fieldIndexMap = new HashMap<String, Integer>();
	if (fields == null || fields.isEmpty()) {
	    return fieldIndexMap;
	}
	int size = fields.size();
	for (int i = 0; i < size; i++) {
	    fieldIndexMap.put(fields.get(i).getName(), i);
	}
	return fieldIndexMap;
    }

    protected List<String> createFieldNames(List<DSField> fields) {
	List<String> fieldNames = new ArrayList<String>();
	if (fields == null || fields.isEmpty()) {
	    return fieldNames;
	}
	int size = fields.size();
	for (int i = 0; i < size; i++) {
	    fieldNames.add(fields.get(i).getName());
	}
	return fieldNames;
    }
    
    protected Object[] getRecord(DSResultSet resultSet, List<DSField> fields) throws DSException {
	if (resultSet == null || fields == null) {
	    return null;
	}
	int size = fields.size();
	Object[] record = new Object[size];
	for (int i = 0; i < size; i++) {
	    record[i] = resultSet.getValue(fields.get(i).getName());
	}
	return record;
    }
    
    protected boolean isFilter(Object[] record, Map<String, Integer> fieldIndexMap, List<DSFilter> filters) {
	if (filters == null || filters.isEmpty()) {
	    return true;
	}
	for (DSFilter filter : filters) {
	    if (!isFilter(record, fieldIndexMap, filter)) {
		return false;
	    }
	}
	return true;
    }
    
    protected boolean isFilter(Object[] record, Map<String, Integer> fieldIndexMap, DSFilter filter) {
	if (filter == null) {
	    return true;
	}
	
	// TODO: Implemented only for DSFieldFilter
	if (filter instanceof DSFieldFilter) {
	    DSFieldFilter fieldFilter = ((DSFieldFilter) filter);
	    DSField field = fieldFilter.getField();
	    if (field == null) {
		return true;
	    }
	    String fieldName = field.getName();
	    if (fieldName == null) {
		return true;
	    }
	    Integer index = fieldIndexMap.get(fieldName);
	    if (index == null) {
		return true;
	    }
	    if (index < 0 || index > record.length - 1) {
		return true;
	    }
	    Object leftValue = record[index];
	    Object rightValue = fieldFilter.getValue();
	    String operation = fieldFilter.getOperation();
	    return isFilterByOperation(leftValue, operation, rightValue);
	}
	
	//TODO
	return true;
    }
    
    /*
    protected Boolean evaluateBooleanValue(Object leftValue, String operation, Object rightValue) {
	if (operation == null) {
	    operation = Operation.EQ;
	}
    }
    */
    
    protected boolean isFilterByOperation(Object leftValue, String operation, Object rightValue) {
	if (operation == null) {
	    operation = Operation.EQ;
	}

	if (Operation.EQ.equals(operation)) {
	    return isEQ(leftValue, rightValue);
	}
	if (Operation.NE.equals(operation)) {
	    return isNE(leftValue, rightValue);
	}
	if (Operation.LIKE.equals(operation)) {
	    return isLIKE(leftValue, rightValue);
	}
	if (Operation.NOTLIKE.equals(operation)) {
	    return isNOTLIKE(leftValue, rightValue);
	}
	if (Operation.LT.equals(operation)) {
	    return isLT(leftValue, rightValue);
	}
	if (Operation.LE.equals(operation)) {
	    return isLE(leftValue, rightValue);
	}
	if (Operation.GT.equals(operation)) {
	    return isGT(leftValue, rightValue);
	}
	if (Operation.GE.equals(operation)) {
	    return isGE(leftValue, rightValue);
	}

	// Default
	return isEQ(leftValue, rightValue);
    }    
    
    protected boolean isEQ(Object leftValue, Object rightValue) {
	if (leftValue == null || rightValue == null) {
	    return false;
	}
	return leftValue.equals(rightValue);
    }

    protected boolean isNE(Object leftValue, Object rightValue) {
	return !isEQ(leftValue, rightValue);
    }

    protected boolean isLIKE(Object leftValue, Object rightValue) {
	//TODO
	return true;
    }

    protected boolean isNOTLIKE(Object leftValue, Object rightValue) {
	return !isLIKE(leftValue, rightValue);
    }

    protected boolean isLT(Object leftValue, Object rightValue) {
	//TODO
	return true;
    }

    protected boolean isLE(Object leftValue, Object rightValue) {
	//TODO
	return true;
    }

    protected boolean isGT(Object leftValue, Object rightValue) {
	//TODO
	return true;
    }
    
    protected boolean isGE(Object leftValue, Object rightValue) {
	//TODO
	return true;
    }
    
    
    ////
    
    
    public boolean needProcessing(DSDataSource dataSource) {
	if (dataSource == null || !dataSource.hasFields()) {
	    return false;
	}
	// Only for filter or order
	boolean hasFilters = dataSource.hasFilters();
	boolean hasOrders = dataSource.hasOrders();
	
	return hasFilters || hasOrders;
    }
    
    public boolean isCache(DSResultSet resultSet) {
	if (resultSet == null) {
	    return false;
	}
	return resultSet instanceof DSArrayResultSet;
    }
    
    protected String normalize(String str) {
	return StringUtils.normalizeString(str);
    }
    
    protected boolean isEmpty(String str) {
	return normalize(str) == null;
    }
}

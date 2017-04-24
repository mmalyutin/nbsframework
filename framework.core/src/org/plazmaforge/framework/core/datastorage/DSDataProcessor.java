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
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.converter.Converter;
import org.plazmaforge.framework.core.data.converter.ConverterManager;
import org.plazmaforge.framework.core.data.converter.STConverterManager;
import org.plazmaforge.framework.core.datastorage.data.OperationProcessor;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.util.ClassUtils;
import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * @author ohapon
 *
 */
public class DSDataProcessor {

    private ConverterManager converterManager;
    
    private OperationProcessor operationProcessor; 
    
    public DSDataProcessor() {
	super();
	converterManager = new STConverterManager(true);
	converterManager.init();

	operationProcessor = new OperationProcessor();
	operationProcessor.registerDefaultEvaluators();
    }

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
	
	// Sort data records
	if (dataSource.hasOrders()) {
	    sortRecords(records, fieldIndexMap, dataSource.getOrders());
	}
	
	DSArrayResultSet result = new DSArrayResultSet(fieldNames, records);
	
	// Close input origin ResultSet
	resultSet.close();
	
	//TODO: orders
	return result;
    }
    
    protected void sortRecords(List<Object[]> records, Map<String, Integer> fieldIndexMap, final List<DSOrder> orders) {
	final Map<Object[], Object[]> map = new HashMap<Object[], Object[]>();
	int size = orders.size();
	DSOrder order = null;
	Object[] values = null; // sorting values
	Object value = null;
	
	// Iteration array and evaluate sorting values for each record
	// Create map: record -> sorting values 
	for (Object[] record : records) {
	    values = new Object[size];
	    for (int i = 0; i < size; i++) {
		order = orders.get(i);
		value = getOrderValue(record, fieldIndexMap, order);
		values[i] = value;
	    }
	    
	    // record -> sorting values
	    map.put(record, values);
	}
	
	
	Collections.sort(records, new Comparator<Object[]>() {
	    public int compare(Object[] record1, Object[] record2) {
		
		return compareRecord(record1, record2, map, orders);
	    }
	});
    }
    
    protected int compareRecord(Object[] record1, Object[] record2, Map<Object[], Object[]> map, List<DSOrder> orders) {
	if (record1 == null && record2 == null) {
	    return 0;
	}
	if (record1 == null) {
	    return -1;
	}
	if (record2 == null) {
	    return 1;
	}
	if (map == null) {
	    return 0;
	}
	Object[] value1 = map.get(record1);
	Object[] value2 = map.get(record2);

	if (value1 == null && value2 == null) {
	    return 0;
	}
	if (value1 == null) {
	    return -1;
	}
	if (value2 == null) {
	    return 1;
	}
	int size = value1.length;
	Object v1 = null;
	Object v2 = null;
	Integer result = 0;
	boolean asc = false;
	for (int i = 0; i < size; i++) {
	    v1 = value1[i];
	    v2 = value2[i];
	    result = OperationProcessor.compareValue(v1, v2);
	    asc = orders.get(i).isAsc();
	    if (result == null) {
		result = 0;
	    }
	    if (!asc) {
		result = result * -1;
	    }
	    if (result != 0) {
		return result;
	    }
	}
	return result;
    }
    
    protected Object getOrderValue(Object[] record, Map<String, Integer> fieldIndexMap, DSOrder order)  {
	if (order instanceof DSFieldOrder) {
	    DSField field = ((DSFieldOrder) order).getField();
	    if (field == null) {
		return null;
	    }
	    String fieldName = field.getName();
	    if (fieldName == null) {
		return null;
	    }
	    Integer index = fieldIndexMap.get(fieldName);
	    if (index == null) {
		return null;
	    }
	    if (index < 0 || index > record.length - 1) {
		return true;
	    }
	    
	    Object fieldValue = record[index]; // get original value
	    Object orderValue = convertValue(fieldValue, field); // convert value
	    
	    return orderValue;
	    
	}
	
	return null;
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
	DSField field = null;
	Object value = null;
	for (int i = 0; i < size; i++) {
	    field = fields.get(i);
	    value = resultSet.getValue(field.getName()); // get internal value
	    record[i] = convertValue(value, field); // convert value by dataType
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
	    Object filterValue = fieldFilter.getValue();
	    
	    Object leftValue = record[index];
	    Object rightValue = convertValue(filterValue, field);
	    
	    String operation = fieldFilter.getOperation();
	    return isFilterByOperation(leftValue, operation, rightValue);
	}
	
	//TODO
	return true;
    }
    
    // See AbstractDataSet
    protected Object convertValue(Object value, DSField field) {
  	if (value == null || field == null) {
  	    return value;
  	}
  	String type = field.getDataType();
  	if (type == null) {
  	    return value;
  	}
  	
  	// get input type (real)
  	Class<?> inputType = value.getClass();
  	
  	//dataType = ClassUtils.normalizeClass(dataType);
  	inputType = ClassUtils.normalizeClass(inputType);
  	
  	// no convert: type of value is correct
  	//if (dataType.isAssignableFrom(inputType)) {
  	//    return value;
  	//}
  	
  	String format = getFormat(field);
  	
  	Converter converter = getConverter(inputType.getSimpleName(), type, format);
  	Object result = converter == null ? null : converter.convert(value);
  	return result;
    }
      
    protected Converter<?, ?> getConverter(String sourceType, String targetType, String format) {
	if (sourceType == null || targetType == null) {
	    return null;
	}
	String name = ConverterManager.getConverterSimpleName(sourceType, targetType);
	if (name == null) {
	    return null;
	}
	return getConverterByName(name, format);
    }

    protected Converter<?, ?> getConverterByName(String name, String format) {
	return converterManager.getConverter(name, format);
    }

    protected String getFormat(DSField field) {
	if (field == null) {
	    return null;
	}
	String format = field.getFormat();
	if (format == null) {
	    format = getFormat(field.getDataType());
	}
	return format;
    }
    
    protected String getFormat(String type) {
	return TypeUtils.getDefaultFormat(type);
    }
    
    protected boolean isFilterByOperation(Object leftValue, String operation, Object rightValue) {
	Boolean result = operationProcessor.evaluate(leftValue, operation, rightValue);
	if (result == null) {
	    return false;
	}
	return result;
    }
    
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

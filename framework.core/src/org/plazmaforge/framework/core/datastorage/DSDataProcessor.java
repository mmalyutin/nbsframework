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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.converter.Converter;
import org.plazmaforge.framework.core.data.converter.ConverterManager;
import org.plazmaforge.framework.core.data.converter.STConverterManager;
import org.plazmaforge.framework.core.datastorage.data.ConditionProcessor;
import org.plazmaforge.framework.core.datastorage.data.RecordComparator;
import org.plazmaforge.framework.core.datastorage.data.Scope;
import org.plazmaforge.framework.core.exception.DSEvaluateException;
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

    // convert values
    private ConverterManager converterManager;
    
    // evaluate conditions
    private ConditionProcessor conditionProcessor; 
    
    public DSDataProcessor() {
	super();
	converterManager = new STConverterManager(true);
	converterManager.init();

	conditionProcessor = new ConditionProcessor();
	conditionProcessor.registerDefaultEvaluators();
    }

    public DSResultSet processResultSet(DSResultSet resultSet, DSDataSource dataSource) throws DSException {
	return processResultSet(resultSet, dataSource, null, null);
    }
    
    public DSResultSet processResultSet(DSResultSet resultSet, DSDataSource dataSource, Scope scope, DSExpressionEvaluator expressionEvaluator) throws DSException {
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
	
	Map<String, Integer> fieldIndexes = createFieldIndexes(fields);
	List<String> fieldNames = createFieldNames(fields);
	
	List<Object[]> records = new ArrayList<Object[]>();
	Object[] record = null;
	
	// Load all data to DSArrayResultSet
	while (resultSet.next()) {
	    
	    // Get record
	    record = getRecord(resultSet, fields);
	    
	    // Populate scope-1
	    populateScope(scope, record, fieldIndexes, fields, false);
	    
	    // Apply filters
	    if (isFilter(record, fieldIndexes, filters, expressionEvaluator)) {
		records.add(record);
	    }
	}
	
	// Sort data records
	if (dataSource.hasOrders()) {
	    sortRecords(scope, records, fieldIndexes, fields, dataSource.getOrders(), expressionEvaluator);
	}
	
	DSArrayResultSet result = new DSArrayResultSet(fieldNames, records);
	
	// Close input origin ResultSet
	resultSet.close();
	
	return result;
    }
    
    protected void sortRecords(Scope scope, List<Object[]> records, Map<String, Integer> fieldIndexes, List<DSField> fields, final List<DSOrder> orders, DSExpressionEvaluator expressionEvaluator) throws DSException {
	final Map<Object[], Object[]> map = new HashMap<Object[], Object[]>();
	int size = orders.size();
	DSOrder order = null;
	Object[] values = null; // sorting values
	Object value = null;
	
	// Iteration array and evaluate sorting values for each record
	// Create map: record values -> sorting values 
	for (Object[] record : records) {
	    
	    // Populate scope-2
	    populateScope(scope, record, fieldIndexes, fields, false);

	    
	    values = new Object[size];
	    for (int i = 0; i < size; i++) {
		order = orders.get(i);
		value = evaluateOrderValue(record, fieldIndexes, order, expressionEvaluator);
		values[i] = value;
	    }
	    
	    // record -> sorting values
	    map.put(record, values);
	}
	
	// asc flags
	boolean[] flags = new boolean[size];
	for (int i = 0; i < size; i++) {
	    flags[i] = orders.get(i).isAsc();
	}
	
	// Sort records
	Collections.sort(records, new RecordComparator(map, flags));
	
    }
    
    protected Object evaluateOrderValue(Object[] record, Map<String, Integer> fieldIndexes, DSOrder order, DSExpressionEvaluator expressionEvaluator)  {
	if (order instanceof DSFieldOrder) {
	    DSField field = ((DSFieldOrder) order).getField();
	    
	    Object fieldValue = getRecordValue(record, fieldIndexes, field); // get original value
	    Object orderValue = convertValue(fieldValue, field); // convert value
	    
	    return orderValue;
	    
	} else if (order instanceof DSExpressionOrder) {
	    DSExpression expression = ((DSExpressionOrder) order).getExpression();
	    return evaluateExpression(expression, expressionEvaluator);
	}
	
	return null;
    }
    
    
    protected Map<String, Integer> createFieldIndexes(List<DSField> fields) {
	Map<String, Integer> fieldIndexes = new HashMap<String, Integer>();
	if (fields == null || fields.isEmpty()) {
	    return fieldIndexes;
	}
	int size = fields.size();
	for (int i = 0; i < size; i++) {
	    String fieldName = getFieldName(fields.get(i));
	    if (fieldName == null) {
		continue;
	    }
	    fieldIndexes.put(fieldName, i);
	}
	return fieldIndexes;
    }

    protected List<String> createFieldNames(List<DSField> fields) {
	List<String> fieldNames = new ArrayList<String>();
	if (fields == null || fields.isEmpty()) {
	    return fieldNames;
	}
	int size = fields.size();
	for (int i = 0; i < size; i++) {
	    String fieldName = getFieldName(fields.get(i));
	    fieldNames.add(fieldName);
	}
	return fieldNames;
    }
    
    protected String getFieldName(DSField field) {
	return field == null ? null : field.getName();
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
    
    protected boolean isFilter(Object[] record, Map<String, Integer> fieldIndexes, List<DSFilter> filters, DSExpressionEvaluator expressionEvaluator) {
	if (filters == null || filters.isEmpty()) {
	    return true;
	}
	for (DSFilter filter : filters) {
	    if (!evaluateFilterValue(record, fieldIndexes, filter, expressionEvaluator)) {
		return false;
	    }
	}
	return true;
    }
    
    protected boolean evaluateFilterValue(Object[] record, Map<String, Integer> fieldIndexes, DSFilter filter, DSExpressionEvaluator expressionEvaluator) {
	if (filter == null) {
	    return true;
	}
	
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
	    Integer index = fieldIndexes.get(fieldName);
	    if (index == null) {
		return true;
	    }
	    if (index < 0 || index > record.length - 1) {
		return true;
	    }
	    
	    
	    Object leftValue = record[index];
	    
	    Object filterValue = fieldFilter.getValue();
	    Object rightValue = convertValue(filterValue, field);
	    
	    String operator = fieldFilter.getOperation();
	    
	    // Evaluate condition by operator
	    return evaluateCondition(leftValue, operator, rightValue);
	} else if (filter instanceof DSExpressionFilter) {
	    DSExpression expression = ((DSExpressionFilter) filter).getExpression();
	    
	    // Evaluate condition by expression
	    return evaluateCondition(expression, expressionEvaluator);
	}
	
	//TODO
	return true;
    }
    
    protected Object getRecordValue(Object[] record, Map<String, Integer> fieldIndexes, DSField field) {
	if (field == null) {
	    return null;
	}
	String fieldName = field.getName();
	if (fieldName == null) {
	    return null;
	}
	Integer index = fieldIndexes.get(fieldName);
	if (index == null) {
	    return null;
	}
	if (index < 0 || index > record.length - 1) {
	    return null;
	}
	return record[index];
    }
    
    protected void populateScope(Scope scope, Object[] record,  Map<String, Integer> fieldIndexes, List<DSField> fields, boolean reset) throws DSException {
	if (scope == null) {
	    // No scope
	    return;
	}
	if (fields == null) {
	    return;
	}
	for (DSField field : fields) {

	    String fieldName = field.getName();

	    if (fieldName == null) {
		continue;
	    }

	    // 1. Simple field name
	    transferFieldValue(scope, record, fieldIndexes, field, reset);

	    //
	}

    }

    protected void transferFieldValue(Scope scope, Object[] record, Map<String, Integer> fieldIndexes, DSField field, boolean reset) throws DSException {

	String fieldName = field.getName();

	// Get
	Object oldValue = scope.getFieldValue(fieldName);
	Object newValue = reset ? null : getRecordValue(record, fieldIndexes, field);

	// Set
	scope.setFieldOldValue(fieldName, oldValue);
	scope.setFieldValue(fieldName, newValue);

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
    
    
    protected boolean translateBoolean(Object value) {
	if (value == null) {
	    // NULL -> FALSE
	    return false;
	}
	if (value instanceof Boolean) {
	    return (Boolean) value;
	} else {
	    // NON BOOLEAN -> FALSE
	    return false;
	}
    }
    
    /**
     * Evaluate condition by operator
     * @param leftValue
     * @param operator
     * @param rightValue
     * @return
     */
    protected boolean evaluateCondition(Object leftValue, String operator, Object rightValue) {
	Boolean result = conditionProcessor.evaluate(leftValue, operator, rightValue);
	return translateBoolean(result);
    }

    /**
     * Evaluate condition by expression
     * @param expression
     * @param expressionEvaluator
     * @return
     */
    protected boolean evaluateCondition(DSExpression expression, DSExpressionEvaluator expressionEvaluator) {
	Object value = evaluateExpression(expression, expressionEvaluator);
	return translateBoolean(value);
    }
    
    /**
     * Evaluate expression
     * @param expression
     * @param expressionEvaluator
     * @return
     */
    protected Object evaluateExpression(DSExpression expression, DSExpressionEvaluator expressionEvaluator) {
	if (expression == null) {
	    return null;
	}
	if (expressionEvaluator == null) {
	    // TODO
	    return null;
	}
	try {
	    return expressionEvaluator.evaluate(expression);
	} catch (DSEvaluateException e) {
	    // TODO: safe value
	    return null;
	}
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

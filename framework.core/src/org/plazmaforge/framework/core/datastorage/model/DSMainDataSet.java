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
package org.plazmaforge.framework.core.datastorage.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.plazmaforge.framework.core.datastorage.AbstractStructuredDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.DSExpressionEvaluator;
import org.plazmaforge.framework.core.datastorage.DSExpressionParameter;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.core.datastorage.data.Scope;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * DSMainDataSet contains DSDataModel and navigates by all DSDataSets
 * 
 *  
 *         +-----------+
 *         |DSDataNode |           DSDataLevel 1
 *         +-----------+                | previous
 *           |       |                  |
 *           |       |                  |
 *           |       |                  |
 * +-----------+   +-----------+        | next
 * |DSDataNode |   | DSDataNode|   DSDataLevel 2
 * +-----------+   +-----------+
 * 
 * @author ohapon
 *
 */
public class DSMainDataSet extends AbstractStructuredDataSet implements DSDataSet {
    
    

    private DSDataModel model;
    
    // Current level
    private DSDataLevel level;
    
    private DSSession session;
    
    private Scope scope;
    
    private boolean init;

    // flag by next method
    private boolean[] levelFlags;
    
    ////
    
    private Set<String> uniqueFieldNames;
    
    private Map<String, FieldInfo> fieldNameInfoMap; // name, ds.name <-> field-info
    
    private FieldInfo[] fieldArray;
    
    private Object[] record;
    
    
    ////
    
    private DSExpressionEvaluator expressionEvaluator;
    
    // If true then we populate scope by next record
    private boolean populateScope;
    
    ////
    
    class FieldInfo {
	
	/**
	 * Global index
	 */
	int index;
	
	/**
	 * Normalize name
	 */
	String name;
	
	/**
	 * Normalize full name (ds.name)
	 */
	String fullName;
	
	/**
	 * Global path
	 */
	String path;
	
	/**
	 * Name of DataSource
	 */
	String dataSourceName;
	
	/**
	 * Field
	 */
	DSField field;
	
	/**
	 * DataNode
	 */
	DSDataNode node;
	
	/**
	 * ResultSet
	 */
	public DSResultSet getResultSet() {
	    return node == null ? null : node.getResultSet();
	}
	
    }
    
    public DSMainDataSet(DSDataModel model, DSSession session, Scope scope) {
	this(model, session, scope, null);
    }

    public DSMainDataSet(DSDataModel model, DSSession session, Scope scope, DSExpressionEvaluator expressionEvaluator) {
	super();
	this.model = model;
	this.session = session;
	this.scope = scope;
	this.expressionEvaluator = expressionEvaluator;
    }

    
    public boolean isPopulateScope() {
        return populateScope;
    }

    public void setPopulateScope(boolean populateScope) {
        this.populateScope = populateScope;
    }

    @Override
    public boolean next() throws DSException {
	boolean flag = doNext();
	if (flag) {
	    onNext();
	}
	return flag;
    }

    @Override
    public Object getValue(int index) throws DSException {
	if (record == null) {
	    return null;
	}
	return doGetRecordValue(index);
    }

    @Override
    public Object getValue(String name) throws DSException {
	if (record == null) {
	    return null;
	}
	FieldInfo fieldInfo = fieldNameInfoMap.get(name);
	if (fieldInfo == null) {
	    return null;
	}
	return doGetRecordValue(fieldInfo);
    }
    
    @Override
    public Object getValue(DSField field) throws DSException {
	// TODO: Use field <-> fieldPath map
	return getValue(field == null ? null : field.getName());
    }


    @Override
    public Object[] getRecord() throws DSException {
	if (record == null) {
	    return new Object[0];
	}
	return record;
    }
    
    /*
    protected void loadRecord() throws DSException {
	int count = fieldArray.length;
	if (record == null) {
	    record = new Object[count];
	}
	for (int i = 0; i < count; i++) {
	    record[i] = doGetValue(fieldArray[i]);
	}
    }
    */

    /**
     * Load value form data source
     * @param fieldInfo
     * @return
     * @throws DSException
     */
    protected Object doGetValue(FieldInfo fieldInfo) throws DSException {
	if (fieldInfo == null || fieldInfo.name == null || fieldInfo.getResultSet() == null) {
	    return null;
	}
	DSDataItem dataItem = fieldInfo.node.getDataItem();
	if (dataItem.isFetchedData()) {
	    return null;
	}
	
	DSResultSet resultSet = dataItem.getResultSet();
	if (resultSet == null) {
	    return null;
	}
	return fieldInfo.getResultSet().getValue(fieldInfo.name);
    }

    /**
     * Load value form record (cache)
     * @param fieldInfo
     * @return
     * @throws DSException
     */
    protected Object doGetRecordValue(FieldInfo fieldInfo) throws DSException {
	if (record == null || fieldInfo == null || fieldInfo.index < 0 || fieldInfo.index >= record.length) {
	    return null;
	}
	return  record[fieldInfo.index];
    }

    /**
     * Load value form record (cache)
     * @param index
     * @return
     * @throws DSException
     */
    protected Object doGetRecordValue(int index) throws DSException {
	if (index < 0 || index >= fieldArray.length) {
	    return null;
	}
	return doGetRecordValue(fieldArray[index]);
    }

    @Override
    public boolean isEmpty() throws DSException {
	// TODO
	return false;
    }

    @Override
    public boolean isClosed() throws DSException {
	// TODO
	return false;
    }

    @Override
    public void close() throws DSException {
	
	// Get all levels
	List<DSDataLevel> levels = getLevels();
	
	// Close all levels
	for (DSDataLevel level: levels) {
	    doClose(level);
	}
	
	// Close main session
	DataManager.close(session);
    }


    
    ////
    
    public DSSession getSession() {
        return session;
    }

    public void setSession(DSSession session) {
        this.session = session;
    }

    public void init() {
	if (init) {
	    return;
	}
	init = true;
	
	// Set current level
	level = model.getRootLevel();
	levelFlags = new boolean[model.getLevelCount()];
	
	initFields();
    }
    
    /**
     * Initialize all fields of MainDataSet
     */
    protected void initFields() {
	
	uniqueFieldNames = new HashSet<String>();
	fieldNameInfoMap = new HashMap<String, DSMainDataSet.FieldInfo>();
	
	DSDataLevel level = model.getRootLevel();
	List<DSField> allFields = new ArrayList<DSField>();
	List<FieldInfo> allFieldsInfo = new ArrayList<FieldInfo>();
	
	// Navigation by levels
	while (true) {
	    
	    // Navigation by nodes
	    List<DSDataNode> nodes = level.getNodes();
	    
	    for (DSDataNode node: nodes) {
		
		// Initialize fields by current node
		initFields(allFields, allFieldsInfo, node);
	    }
	    
	    // Go next level
	    if (level.getNextLevel() == null) {
		break;
	    }
	    level = level.getNextLevel();
	}
	
	
	setFields(allFields);
	
	fieldArray = allFieldsInfo.toArray(new FieldInfo[0]);
    }
    
    /**
     * Initialize fields of node (name, fullName, path)
     * @param allFields
     * @param allFieldsInfo
     * @param node
     */
    protected void initFields(List<DSField> allFields, List<FieldInfo> allFieldsInfo, DSDataNode node) {
	List<DSField> fields = node.getDataItem().getFields();
	if (fields == null || fields.isEmpty()) {
	    return;
	}
	DSDataSource dataSource = node.getDataItem().getDataSource();
	String dataSourceName = dataSource == null ? null : normalize(dataSource.getName());
	
	String fieldName = null;
	String fieldFullName = null;
	String fieldPathName = null;
	
	boolean first = true;
	FieldInfo fieldInfo = null;
	for (DSField field: fields) {
	
	    if (first) {
		
		// Set field index offset
		node.setFieldOffset(allFields.size());
		first = false;
	    }
	    
	    allFields.add(field);
	    int fieldIndex = allFields.size() - 1;
	    
	    fieldInfo = new FieldInfo();
	    allFieldsInfo.add(fieldInfo);
	    
	    fieldInfo.index = fieldIndex;
	    fieldInfo.field = field;
	    fieldInfo.node = node;
	    fieldInfo.dataSourceName = dataSourceName;
	    
	    
	    String nodeId = node.getId();
	    
	    
	    fieldName = normalize(field.getName());
	    if (fieldName == null) {
		continue;
	    }
	    
	    // 1. Simple name
	    // 2. Full Name (DataSource.Field)
	    // 3. Path (UUID/Field)
	    
	    fieldFullName = dataSourceName == null ? null : dataSourceName + "." + fieldName;
	    fieldPathName = nodeId + "/" + fieldName; 
	    
	    if (!uniqueFieldNames.contains(fieldName)) {
		uniqueFieldNames.add(fieldName);
		fieldNameInfoMap.put(fieldName, fieldInfo);	   // name <-> field-info
	    }
	    
	    if (fieldFullName != null) {
		fieldNameInfoMap.put(fieldFullName, fieldInfo);    // ds.name <-> field-info
	    }
	    
	    fieldInfo.name = fieldName;
	    fieldInfo.fullName = fieldFullName;
	    fieldInfo.path = fieldPathName;
	    
	    field.setProperty("$fullName", fieldFullName);
	}
    }
    
  
    /**
     * General next method
     * @return
     * @throws DSException
     */
    protected boolean doNext() throws DSException {
	if (!init) {
	    init();
	}
	
	// Reset level flags in this transaction
	Arrays.fill(levelFlags, false);
	
	while (true) {
	    boolean flag = doNext(level);
	    if (!flag) {
		int levelIndex = level.getIndex();
		
		// If flag of previous level is true then return true
		if (levelIndex > 0) {
		    if (levelFlags[levelIndex - 1]) {
			// Break successful transaction
			return true;
		    }
		}
		
		if (level.getPrevLevel() != null) {
		    level = level.getPrevLevel();
		} else {
		    // Break unsuccessful transaction
		    return false;
		}
	    } else {
		
		// Store flag of level
		levelFlags[level.getIndex()] = true;
		
		if (level.getNextLevel() != null) {
		    level = level.getNextLevel();
		} else {
		    // Break successful transaction
		    return true;
		}
	    }
	}
    }
    
    /**
     * Level next method 
     * @param level
     * @return
     * @throws DSException
     */
    protected boolean doNext(DSDataLevel level) throws DSException {
	List<DSDataNode> nodes = level.getNodes();
	if (nodes == null || nodes.isEmpty()){
	    return false;
	}
	boolean flag = false;
	for (DSDataNode node: nodes) {
	    if (doNext(node)) {
		flag = true;
	    }
	}
	return flag;
    }
    
    /**
     * Node next method
     * @param node
     * @return
     * @throws DSException
     */
    protected boolean doNext(DSDataNode node) throws DSException {
	if (node == null) {
	    return false;
	}
	DSDataItem dataItem = node.getDataItem();
	if (dataItem == null) {
	    return false;
	}
	
	if (!dataItem.isInitData()) {
	    
	    // Initialize DataItem
	    initDataItem(dataItem);
	    if (dataItem.isInvalidData()) {
		return false;
	    }
	    
	    // Initialize parent marker
	    if (node.getParent() != null) {
		// Store parent marker value to to special child value for next analyzing
		DSDataNode parent = node.getParent(); 
		String parentKey = parent.getId();
		String key = node.getId() + ".parent";
		Long value = (Long) scope.getScopeValue(Scope.DATA, parentKey, DSExpression.EVALUATION_DEFAULT);
		scope.setScopeValue(Scope.DATA, key, DSExpression.EVALUATION_DEFAULT, value);
	    }
	} else {
	    
	    if (dataItem.isInvalidData()) {
		return  false;
	    }
	    
	    if (node.getParent() != null) {
		// Load parent marker value
		DSDataNode parent = node.getParent();
		String parentKey = parent.getId();
		String key = node.getId() + ".parent";
		Long oldValue = (Long) scope.getScopeValue(Scope.DATA, key, DSExpression.EVALUATION_DEFAULT);
		Long newValue = (Long) scope.getScopeValue(Scope.DATA, parentKey, DSExpression.EVALUATION_DEFAULT);
		if (scope.isNewScopeValue(oldValue, newValue)) {
		    
		    // Set new parent marker 
		    scope.setScopeValue(Scope.DATA, key, DSExpression.EVALUATION_DEFAULT, newValue);
		    
		    // Parent is changed
		    reopenDataItem(dataItem);
		}
	    }
	}
	
	DSResultSet resultSet = dataItem.getResultSet();
	if (resultSet == null){
	    return false;
	}
	
	// All data is fetched
	if (dataItem.isFetchedData()) {
	    return false;
	}
	
	boolean flag = resultSet.next();
	if (flag) {
	    // Put special node marker to scope (Use only for children node)
	    // Marker indicates that node is changed
	    String key = node.getId();
	    Long value = (Long) scope.getScopeValue(Scope.DATA, key, DSExpression.EVALUATION_DEFAULT);
	    if (value == null) {
		value = 0L;
	    }
	    value++;
	    scope.setScopeValue(Scope.DATA, key, DSExpression.EVALUATION_DEFAULT, value);
	    
	    loadNodeRecord(node, resultSet, false);
	} else {
	    dataItem.setFetchedData(true); // All data is fetched
	    loadNodeRecord(node, resultSet, true);
	    // TODO: My be reset values: loadNodeRecord(node, resultSet, true);
	}
	return flag; 
    }
    
    /**
     * Load record of DSDataNode/DSResultSet
     * 
     * @param node
     * @param resultSet
     * @param reset
     * @throws DSException
     */
    protected void loadNodeRecord(DSDataNode node, DSResultSet resultSet, boolean reset) throws DSException {
	List<DSField> fields = node.getDataItem().getFields();
	if (fields == null || fields.isEmpty()) {
	    return;
	}
	if (record == null) {
	    record = new Object[fieldArray.length];
	}
	int count = fields.size();
	FieldInfo fieldInfo = null;
	int index = 0;
	Object value = null;
	for (int i = 0; i < count; i++) {
	    index = i + node.getFieldOffset();
	    fieldInfo = fieldArray[index];
	    value = reset ? null : doGetValue(fieldInfo);
	    record[index] = value;
	}
	
	if (populateScope) {
	    populateScope(fields, reset);
	}
	
    }
    
    protected void initDataItem(DSDataItem dataItem) throws DSException {
	if (dataItem == null) {
	    return;
	}
	dataItem.setInitData(true);
	if (dataItem.isEmbeddedData()) {
	    // Embedded data is always initialized
	    if (dataItem.getResultSet() == null) {
		dataItem.setInvalidData(true);
	    }
	    return;
	}
	
	DSSession session = null;
	if (dataItem.getDataConnector() != null) {
	    // Create own session
	    session = DataManager.openSession(dataItem.getDataConnector());
	} else {
	    // Use common session
	    session = getSession();
	}
	dataItem.setSession(session);
	
	reopenDataItem(dataItem);
    }
    
    protected void reopenDataItem(DSDataItem dataItem) throws DSException {
	if (dataItem == null) {
	    return;
	}
	
	// Reset fetched flag
	dataItem.setFetchedData(false);
	
	if (dataItem.isInvalidData()) {
	    return;
	}
	
	// Step 1. Get Session
	DSSession session = dataItem.getSession();
	if (session == null) {
	    dataItem.setInvalidData(true);
	    return;
	}
	
	// Step 2. Get DataSource
	DSDataSource dataSource = dataItem.getDataSource();
	if (dataSource == null) {
	    dataItem.setInvalidData(true);
	    return;
	}
	
	// Step 3. Get old ResultSet and close if found
	DSResultSet resultSet = dataItem.getResultSet();
	if (resultSet != null) {
	    try {
		resultSet.close();
	    } catch (DSException ex) {
		
	    }
	}
	
	// Step 5. Prepare parameters (if need). Evaluate parameters values
	List<Object> outputParameters = null;
	if (dataSource.hasParameters()) {
	    List<DSParameter> inputParameters = dataSource.getParameters();
	    outputParameters = new ArrayList<Object>();
	    Object outputParameter = null;
	    
	    for (DSParameter inputParameter: inputParameters) {
		
		// Set default parameter value
		outputParameter = null;
		
		// Evaluate value only for parameter with expression
		if (inputParameter instanceof DSExpressionParameter) {
		    if (expressionEvaluator == null) {
			outputParameters.add(outputParameter);
			continue;
		    }
		    DSExpression expression = ((DSExpressionParameter) inputParameter).getExpression();
		    if (!DSExpression.isEmpty(expression)) {
			// Evaluate parameter expression
			outputParameter = expressionEvaluator.evaluate(expression);
		    }
		} else {
		    // Transfer default value to parameter value
		    outputParameter = inputParameter.getDefaultValue();
		}
		
		outputParameters.add(outputParameter);
	    }
	}
	
	// Step 6. Open new ResultSet by DataSource
	resultSet = DataManager.openResultSet(session, dataSource, outputParameters == null ? null : outputParameters.toArray(new Object[0]));
	dataItem.setResultSet(resultSet);

	
	if (resultSet == null){
	    dataItem.setInvalidData(true);
	    return;
	}
    }

    protected void doClose(DSDataLevel level) throws DSException {
	// Get levels of node
	List<DSDataNode> nodes = level.getNodes();
	
	// Close all level nodes
	for (DSDataNode node: nodes) {
	    doClose(node);
	}
    }

    protected void doClose(DSDataNode node) throws DSException {
	DSDataItem dataItem = node.getDataItem();
	DSResultSet resultSet = dataItem.getResultSet();
	
	// Close result set
	if (resultSet != null) {
	    DataManager.close(resultSet);
	}
	
	// Close session
	DSSession session = dataItem.getSession();
	if (session != null) {
	    DataManager.close(session);
	}
    }
    
    protected List<DSDataLevel> getLevels() {
	List<DSDataLevel> levels = new ArrayList<DSDataLevel>();
	DSDataLevel level = model.getRootLevel();
	while (true) {
	    levels.add(level);
	    if (level.getNextLevel() == null) {
		break;
	    }
	    level = level.getNextLevel();
	}
	return levels;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //
    // EVENTS
    //
    ///////////////////////////////////////////////////////////////////////////////////////////////
    
    protected void onNext() throws DSException {
	// do nothing
    }
    
    /**
     * Populate scope
     * Transfer field values from DSResultSet to Scope
     * @param fields
     * @param reset
     * @throws DSException
     */
    protected void populateScope(List<DSField> fields, boolean reset) throws DSException {
	if (scope == null) {
	    // No scope
	    return;
	}
	if (fields == null) {
	    return;
	}
	for (DSField field: fields) {
	    
	    String fieldName = field.getName();
	    
	    if (fieldName == null){
		continue;
	    }
	    
	    // 1. Simple field name
	    transferFieldValue(fieldName, reset);
	    
	    // 2. Full field name (DataSource.Field)
	    String fieldFullName = field.getProperty("$fullName");
	    if (fieldFullName != null && !fieldFullName.equals(fieldName)) {
		transferFieldValue(fieldFullName, reset);
	    }

	}

    }
    
    protected void transferFieldValue(String fieldName, boolean reset) throws DSException {
	
	// Get
	Object oldValue = scope.getFieldValue(fieldName);
	Object newValue = reset ? null : getValue(fieldName);

	// Set
	scope.setFieldOldValue(fieldName, oldValue);
	scope.setFieldValue(fieldName, newValue);

    }
    
    
    protected String normalize(String str) {
	return StringUtils.normalizeString(str);
    }
    
    protected boolean isEmpty(String str) {
	return normalize(str) == null;
    }
    
    protected boolean isEmpty(Collection<?> collection) {
	return collection == null || collection.isEmpty();
    }
    
    protected boolean equals(String s1, String s2) {
	if (s1 == null || s2 == null){
	    return false;
	}
	return s1.equals(s2);
    }


}

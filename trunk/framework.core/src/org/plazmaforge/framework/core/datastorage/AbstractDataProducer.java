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


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.ParameterValue;
import org.plazmaforge.framework.core.datastorage.data.QueryAnalyzer;
import org.plazmaforge.framework.core.datastorage.data.QueryInfo;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.util.StringUtils;
import org.plazmaforge.framework.util.SystemUtils;

/**
 * @author ohapon
 *
 */
public abstract class AbstractDataProducer implements DataProducer {

    public static final boolean ALWAYS_ANALIZE_QUERY = false;
    
    @Override
    public DSResultSet openResultSet(String connectionString) throws DSException {
	// By default unsupported operation.
	// Only for single DSSession/DSDataConnector (one DSSession/DSDataConnector - one DSResultSet: CSV, XML, XLS..)
	throw new DSException("Unsupported operation");
    }

    @Override
    public DSResultSet openResultSet(DSSession session) throws DSException {
	// By default unsupported operation.
	// Only for single DSSession/DSDataConnector (one DSSession/DSDataConnector - one DSResultSet: CSV, XML, XLS..)
	throw new DSException("Unsupported operation");
    }
    
    @Override
    public DSResultSet openResultSet(DSSession session, DSDataSource dataSource) throws DSException {
	if (session == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session is null.");
	}
	if (dataSource == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "DataSource is null");
	}
	
	List<ParameterValue> parameters = createParameters(dataSource);
	return openResultSet(session, dataSource.getQueryText(), parameters == null ? null : parameters.toArray(new ParameterValue[0]));
    }

    @Override
    public DSResultSet openResultSet(DSSession session, DSDataSource dataSource, Object[] parameterValues)  throws DSException {

	if (session == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session is null.");
	}
	if (dataSource == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "DataSource is null");
	}
	
	List<ParameterValue> parameters = createParameters(dataSource, parameterValues);
	return openResultSet(session, dataSource.getQueryText(), parameters == null ? null : parameters.toArray(new ParameterValue[0]));
    }
    
    @Override
    public DSResultSet openResultSet(DSSession session, String query) throws DSException {
	return openResultSet(session, query, null);
    }
    
    
    ////
    
    public boolean supportsSingleDataSource() {
	// By default
	return false;
    }
    
    ////
    
    protected String getCheckConnectionString(String context, String connectionString) throws DSException {
	return DataManager.getCheckConnectionString(context, connectionString);
    }

    protected String[] parseLocalConnectionString(String context, String connectionString) throws DSException {
	return DataManager.parseLocalConnectionString(context, connectionString);
    }
    
    
    protected Map<String, Object> createConnectionParameterData(String parametersString) throws DSException {
	return DataManager.createConnectionParameterData(parametersString);
    }
    
    protected  void populateConnectionParameterData(String parametersString, Map<String, Object> data) throws DSException {
	DataManager.populateConnectionParameterData(parametersString, data);
    }
    
    protected void handleContextException(String context, Throwable cause) throws DSException {
	DataManager.handleContextException(context, cause);
    }
    
    protected void handleContextException(String context, String cause) throws DSException {
	DataManager.handleContextException(context, cause);
    }

    protected void handleException(String message, String cause) throws DSException {
	DataManager.handleException(message, cause);
    }
    
    protected String normalize(String str) {
 	return StringUtils.normalizeString(str);
    }

    protected <T> boolean isEmpty(T[] array) {
 	return SystemUtils.isEmpty(array);
    }
    
    protected boolean isEmpty(Collection<?> collection) {
 	return SystemUtils.isEmpty(collection);
    }

    ////
    
    protected List<ParameterValue> createParameters(DSDataSource dataSource) throws DSException {
	if (!dataSource.hasParameters()) {
	    return null;
	}
	List<ParameterValue> parameters = null;

	// Prepare parameter values
	// Value is default value of parameter
	List<DSParameter> dsParameters = dataSource.getParameters();
	int dsParameterCount = dsParameters == null ? 0 : dsParameters.size();

	parameters = new ArrayList<ParameterValue>();
	ParameterValue parameter = null;
	DSParameter dsParameter = null;

	for (int i = 0; i < dsParameterCount; i++) {
	    dsParameter = dsParameters.get(i);
	    parameter = new ParameterValue();
	    parameter.setName(dsParameter.getName());
	    parameter.setType(dsParameter.getDataType());
	    parameter.setValue(dsParameter.getDefaultValue());
	    parameters.add(parameter);
	}
	return parameters;

    }
    
    protected List<ParameterValue> createParameters(DSDataSource dataSource, Object[] parameterValues) throws DSException {
	if (!dataSource.hasParameters()) {
	    return null;
	}
	List<ParameterValue> parameters = null;

	// Prepare parameter values
	// Value is default value of parameter
	List<DSParameter> dsParameters = dataSource.getParameters();

	int inputParameterCount = parameterValues == null ? 0 : parameterValues.length;
	int dsParameterCount = dsParameters == null ? 0 : dsParameters.size();

	if (inputParameterCount != dsParameterCount) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Incorrect parameter count. Input/DataSource parameters: "
			    + inputParameterCount + "/" + dsParameterCount);
	}

	parameters = new ArrayList<ParameterValue>();
	ParameterValue parameter = null;
	DSParameter dsParameter = null;

	for (int i = 0; i < dsParameterCount; i++) {
	    dsParameter = dsParameters.get(i);

	    parameter = new ParameterValue();
	    parameter.setName(dsParameter.getName());
	    parameter.setType(dsParameter.getDataType());
	    parameter.setValue(parameterValues[i]);

	    parameters.add(parameter);
	}

	return parameters;

    }

    protected String compileQuery(String query, int inputParameterCount) throws DSException {
	String sql = normalize(query);
	if (sql == null) {
	    return null;
	}

	boolean needAnalizeQuery = inputParameterCount > 0 || ALWAYS_ANALIZE_QUERY;
	if (!needAnalizeQuery) {
	    return sql;
	}
	QueryAnalyzer queryAnalyzer = new QueryAnalyzer();
	QueryInfo queryInfo = queryAnalyzer.analyzeQuery(sql);

	int queryParameterCount = queryInfo.getParameterCount();
	int uniqueParameterCount = queryInfo.getUniqueParameterCount();

	// TODO: Only for named parameters (:param1, :param2)
	// But it doesn't work for '?' parameters!
	if (inputParameterCount != uniqueParameterCount) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, 
		    "Incorrect parameter count. Input/Query parameters: " + inputParameterCount + "/" + uniqueParameterCount);
	}

	sql = queryInfo.getCompileQuery();
	return sql;

    }
    
    protected Reader createReader(String fileName, String charset) throws IOException {
	return createReader(new FileInputStream(fileName), charset);
    }
    
    protected Reader createReader(InputStream inputStream, String charset) throws IOException {
	return charset == null ? new InputStreamReader(inputStream) : new InputStreamReader(inputStream, charset);
    }
    
    protected <T> T getProperty(Class<T> type, Map<String, Object> data, String name) {
	if (data == null) {
	    return null;
	}
	Object value = data.get(name);
	if (value == null) {
	    return null;
	}
	if (type == null) {
	    return (T) value;
	}
	
	
	//TODO: STUB: Use converter
	if (type == String.class) {
	    return (T) value.toString();
	}
	if (type == Boolean.class) {
	    if (value instanceof Boolean) {
		return (T) value;
	    }
	    return (T) Boolean.valueOf(value.toString());
	}
	
	return (T) value;
    }
}

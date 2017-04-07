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
package org.plazmaforge.framework.datastorage.support.json;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.plazmaforge.framework.core.data.ParameterValue;
import org.plazmaforge.framework.core.datastorage.AbstractDataProducer;
import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.core.datastorage.DataProducer;
import org.plazmaforge.framework.core.exception.DSException;


/**
 * @author ohapon
 *
 */
public class JSONDataProducer extends AbstractDataProducer implements DataProducer {
    
    
    @Override
    public DSSession openSession(DSDataConnector dataConnector) throws DSException {
	if (dataConnector == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "DataConnector is null");
	}
	if (!(dataConnector instanceof JSONDataConnector)) {
	    handleContextException(DataManager.CONTEXT_SESSION, "DataConnector must be JSONDataConnector");
	}	
	JSONDataConnector jsonDataConnector = (JSONDataConnector) dataConnector;
	
	String file = jsonDataConnector.getFile();
	String encoding = jsonDataConnector.getEncoding();	
	String dateFormat = jsonDataConnector.getDateFormat();
	String numberFormat = jsonDataConnector.getNumberFormat();
	
	
	Map<String, Object> data = new HashMap<String, Object>();
	data.put(JSONDataConnector.PROPERTY_FILE, file);
	data.put(JSONDataConnector.PROPERTY_ENCODING, encoding);
	data.put(JSONDataConnector.PROPERTY_DATE_FROMAT, dateFormat);
	data.put(JSONDataConnector.PROPERTY_NUMBER_FROMAT, numberFormat);
	
	return doOpenSession(data);
    }

    @Override
    public DSSession openSession(String connectionString) throws DSException {
	String fileName = getCheckConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	
	Map<String, Object> data = new HashMap<String, Object>();
	data.put(JSONDataConnector.PROPERTY_FILE, fileName);
	
	return doOpenSession(data);
    }

    @Override
    public DSSession openSession(String connectionString, Properties properties) throws DSException {
	String file = getCheckConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	
	if (file == null || file.isEmpty()) {
	    file = properties.getProperty(DataManager.PROPERTY_URL);
	}
	
	Map<String, Object> data = new HashMap<String, Object>();
	data.put(JSONDataConnector.PROPERTY_FILE, file);
	
	return doOpenSession(data);
    }

    @Override
    public DSSession openSession(String connectionString, String username, String password) throws DSException {
	String file = getCheckConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	
	Map<String, Object> data = new HashMap<String, Object>();
	data.put(JSONDataConnector.PROPERTY_FILE, file);
	
	return doOpenSession(data);
    }
    
    @Override
    public DSSession openSession(Properties properties) throws DSException {
	if (properties == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "Properties are null");
	}
	
	String fileName = properties.getProperty(DataManager.PROPERTY_URL);
	
	Map<String, Object> data = new HashMap<String, Object>();
	data.put(JSONDataConnector.PROPERTY_FILE, fileName);
	
	return doOpenSession(data);
    }
	
    @Override
    public DSSession openWrapSession(Object data) throws DSException {
	if (data == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "Data is null");
	}
	if (data instanceof Reader) {
	    Reader reader = (Reader) data;
	    return new JSONSession(reader);
	}
	handleContextException(DataManager.CONTEXT_SESSION, "Data is unknown");
	return null;
    }

    // General method
    protected DSSession doOpenSession(String url, String username, String password) throws DSException {
	String fileName = url;
	try {
	    Reader reader = createReader(fileName, null);
	    return new JSONSession(reader);
	} catch (IOException ex) {
	    throw new DSException(ex);
	}
    }

    // General method
    protected DSSession doOpenSession(Map<String, Object> data) throws DSException {
	
	String file = (String) data.get(JSONDataConnector.PROPERTY_FILE);
	String encoding = (String) data.get(JSONDataConnector.PROPERTY_ENCODING);	
	String dateFormat = (String) data.get(JSONDataConnector.PROPERTY_DATE_FROMAT);
	String numberFormat = (String) data.get(JSONDataConnector.PROPERTY_NUMBER_FROMAT);
	
	try {
	    Reader reader = createReader(file, encoding);
	    JSONSession session = new JSONSession(reader);
	    session.setDateFormat(dateFormat);
	    session.setNumberFormat(numberFormat);
	    return session;
	} catch (IOException ex) {
	    throw new DSException(ex);
	}
    }

    @Override
    public DSResultSet openResultSet(String connectionString) throws DSException {
	String[] values = parseLocalConnectionString(DataManager.CONTEXT_RESULT_SET, connectionString);
	String file = values[0];
	String parametersString = values[1];
	Map<String, Object>  parameterData = createConnectionParameterData(parametersString); 
	String encoding = (String) parameterData.get(JSONDataConnector.PROPERTY_ENCODING);
	try {
	    Reader reader = createReader(file, encoding);
	    JSONResultSet resultSet = new JSONResultSet(reader);
	    resultSet.setSelectExpression((String) parameterData.get(DataManager.PROPERTY_QUERY));
	    return resultSet;
	} catch (IOException ex) {
	    throw new DSException(ex);
	}
    }

    @Override
    public DSResultSet openResultSet(DSSession session) throws DSException {
	return doOpenResultSet(session, null, null);
    }
    
    @Override
    public DSResultSet openResultSet(DSSession session, String query, ParameterValue[] parameters) throws DSException {
	return doOpenResultSet(session, query, parameters);
    }

    // General method
    protected DSResultSet doOpenResultSet(DSSession session, String query, ParameterValue[] parameters) throws DSException {
	if (session == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session is null");
	}
	if (!(session instanceof JSONSession)) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session must be JSONSession");
	}

	JSONSession jsonSession = (JSONSession) session;
	Reader reader = jsonSession.getReader();
	if (reader == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Reader is null");
	}
	// query is select expression
	JSONResultSet resultSet = new JSONResultSet(reader);
	resultSet.setSelectExpression(query);
	return resultSet;

    }  
    
    @Override
    public DSDataSet openDataSet(DSSession session, DSDataSource dataSource) throws DSException {
	
	if (session == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session is null");
	}
	if (!(session instanceof JSONSession)) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session must be JSONSession");
	}

	JSONSession jsonSession = (JSONSession) session;
	Reader reader = jsonSession.getReader();
	if (reader == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Reader is null");
	}

	List<DSField> dsFields = dataSource.getFields();
	List<DSField> fields = new ArrayList<DSField>();
	DSField field = null;
	for (DSField dsField: dsFields) {
	    field = dsField.clone();
	    fields.add(field);
	}
	
	JSONDataSet dataSet = new JSONDataSet(fields, reader);
	dataSet.setSelectExpression(dataSource.getQueryText());
	
	dataSet.setDateFormat(jsonSession.getDateFormat());
	dataSet.setNumberFormat(jsonSession.getNumberFormat());
	return dataSet;

    }
    
    public DSDataConnector createDataConnector() {
	return new JSONDataConnector();
    }

    public boolean supportsSingleDataSource() {
	return true;
    }

}

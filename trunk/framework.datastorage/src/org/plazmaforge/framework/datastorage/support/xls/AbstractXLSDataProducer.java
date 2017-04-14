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

package org.plazmaforge.framework.datastorage.support.xls;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
 * 
 * @author ohapon
 *
 */
public abstract class AbstractXLSDataProducer extends AbstractDataProducer implements DataProducer {
    
    @Override
    public DSSession openSession(DSDataConnector dataConnector) throws DSException {
	checkDataConnector(dataConnector);
	AbstractXLSDataConnector xlsDataConnector = (AbstractXLSDataConnector) dataConnector;
	
	String file = xlsDataConnector.getFile();
	Boolean firstRowHeader = xlsDataConnector.isFirstRowHeader();
	String dateFormat = xlsDataConnector.getDateFormat();
	String numberFormat = xlsDataConnector.getNumberFormat();
	
	Map<String, Object> data = new HashMap<String, Object>();
	data.put(XLSDataConnector.PROPERTY_FILE, file);
	data.put(XLSDataConnector.PROPERTY_FIRST_ROW_HEADER, firstRowHeader);
	data.put(XLSDataConnector.PROPERTY_DATE_FROMAT, dateFormat);
	data.put(XLSDataConnector.PROPERTY_NUMBER_FROMAT, numberFormat);
	
	return doOpenSession(data);
    }   
    
    @Override
    public DSSession openSession(String connectionString) throws DSException {
	// Parse connection string
	String[] values = parseLocalConnectionString(DataManager.CONTEXT_RESULT_SET, connectionString);
	String file = values[0];
	String parametersString = values[1];
	Map<String, Object> data = createConnectionParameterData(parametersString);
	file = normalize(file);
	if (file != null) {
	    data.put(XLSDataConnector.PROPERTY_FILE, file);
	}
	return doOpenSession(data);
    }

    @Override
    public DSSession openSession(String connectionString, Properties properties) throws DSException {
	String file = getCheckConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	file = normalize(file);
	if (file == null && properties != null) {
	    file = properties.getProperty(XLSDataConnector.PROPERTY_FILE);
	}
	Map<String, Object> data = new HashMap<String, Object>();
	data.put(XLSDataConnector.PROPERTY_FILE, file);
	return doOpenSession(data);
    }

    @Override
    public DSSession openSession(String connectionString, String username, String password) throws DSException {
	String file = getCheckConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	file = normalize(file);
	Map<String, Object> data = new HashMap<String, Object>();
	data.put(XLSDataConnector.PROPERTY_FILE, file);
	return doOpenSession(data);
    }
    
    @Override
    public DSSession openSession(Properties properties) throws DSException {
	if (properties == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "Properties are null");
	}
	String file = properties.getProperty(XLSDataConnector.PROPERTY_FILE);
	file = normalize(file);
	Map<String, Object> data = new HashMap<String, Object>();
	data.put(XLSDataConnector.PROPERTY_FILE, file);
	return doOpenSession(data);
    }
	
    @Override
    public DSSession openWrapSession(Object data) throws DSException {
	if (data == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "Data is null");
	}
	if (data instanceof InputStream) {
	    InputStream inputStream = (InputStream) data;
	    return createXLSSession(inputStream);
	}
	handleContextException(DataManager.CONTEXT_SESSION, "Data is unknown");
	return null;
    }

    // DSSession: General method
    protected DSSession doOpenSession(Map<String, Object> data) throws DSException {
	
	String file = (String) data.get(XLSDataConnector.PROPERTY_FILE);
	Boolean firstRowHeader = getProperty(Boolean.class, data, XLSDataConnector.PROPERTY_FIRST_ROW_HEADER);
	String dateFormat = (String) data.get(XLSDataConnector.PROPERTY_DATE_FROMAT);
	String numberFormat = (String) data.get(XLSDataConnector.PROPERTY_NUMBER_FROMAT);
	
	try {
	    FileInputStream inputStream = new FileInputStream(file);
	    AbstractXLSSession session = createXLSSession(inputStream);
	    if (firstRowHeader != null) {
		session.setFirstRowHeader(firstRowHeader);
	    }
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
	Map<String, Object> data = createConnectionParameterData(parametersString);
	file = normalize(file);
	if (file != null) {
	    data.put(XLSDataConnector.PROPERTY_FILE, file);
	}
	DSSession session = doOpenSession(data);
	return openResultSet(session);
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
	checkSession(session);
	AbstractXLSSession xlsSession = (AbstractXLSSession) session;
	InputStream inputStream = xlsSession.getInputStream();
	if (inputStream == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "InputStream is null");
	}
	// query is select expression
	AbstractXLSResultSet resultSet = createXLSResultSet(inputStream);
	resultSet.setFirstRowHeader(xlsSession.isFirstRowHeader());
	//TODO: Query is not support
	//resultSet.setSelectExpression(query);
	return resultSet;

    }  
    
    @Override
    public DSDataSet openDataSet(DSSession session, DSDataSource dataSource) throws DSException {
	checkSession(session);
	AbstractXLSSession xlsSession = (AbstractXLSSession) session;
	InputStream inputStream = xlsSession.getInputStream();
	if (inputStream == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "InputStream is null");
	}

	List<DSField> dsFields = dataSource.getFields();
	List<DSField> fields = new ArrayList<DSField>();
	DSField field = null;
	for (DSField dsField: dsFields) {
	    field = dsField.clone();
	    fields.add(field);
	}
	
	AbstractXLSDataSet dataSet = createXLSDataSet(fields, inputStream);
	//dataSet.setSelectExpression(dataSource.getQueryText());

	dataSet.setFirstRowHeader(xlsSession.isFirstRowHeader());
	dataSet.setDateFormat(xlsSession.getDateFormat());
	dataSet.setNumberFormat(xlsSession.getNumberFormat());
	
	return dataSet;

    }

    ////

    protected void checkDataConnector(DSDataConnector dataConnector) throws DSException {
	if (dataConnector == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "DataConnector is null");
	}
    }
    
    protected void checkSession(DSSession session) throws DSException {
	if (session == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session is null");
	}
    }
    
    protected abstract AbstractXLSSession createXLSSession(InputStream inputStream) throws DSException;

    protected abstract AbstractXLSResultSet createXLSResultSet(InputStream inputStream) throws DSException;
    
    protected abstract AbstractXLSDataSet createXLSDataSet(List<DSField> fields, InputStream inputStream) throws DSException;
    
}

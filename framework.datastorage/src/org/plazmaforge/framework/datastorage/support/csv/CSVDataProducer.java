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
package org.plazmaforge.framework.datastorage.support.csv;

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
public class CSVDataProducer extends AbstractDataProducer implements DataProducer {
    
    
    @Override
    public DSSession openSession(DSDataConnector dataConnector) throws DSException {
	if (dataConnector == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "DataConnector is null");
	}
	if (!(dataConnector instanceof CSVDataConnector)) {
	    handleContextException(DataManager.CONTEXT_SESSION, "DataConnector must be CSVDataConnector");
	}	
	CSVDataConnector csvDataConnector = (CSVDataConnector) dataConnector;
	
	String file = csvDataConnector.getFile();
	String encoding = csvDataConnector.getEncoding();
	String columnDelimiter = csvDataConnector.getColumnDelimiter();
	String rowDelimiter = csvDataConnector.getRowDelimiter();
	Boolean firstRowHeader = csvDataConnector.isFirstRowHeader();
	String dateFormat = csvDataConnector.getDateFormat();
	String numberFormat = csvDataConnector.getNumberFormat();
	
	
	Map<String, Object> data = new HashMap<String, Object>();
	data.put(CSVDataConnector.PROPERTY_FILE, file);
	data.put(CSVDataConnector.PROPERTY_ENCODING, encoding);
	data.put(CSVDataConnector.PROPERTY_COLUMN_DELIMITER, columnDelimiter);
	data.put(CSVDataConnector.PROPERTY_ROW_DELIMITER, rowDelimiter);
	data.put(CSVDataConnector.PROPERTY_FIRST_ROW_HEADER, firstRowHeader);
	data.put(CSVDataConnector.PROPERTY_DATE_FROMAT, dateFormat);
	data.put(CSVDataConnector.PROPERTY_NUMBER_FROMAT, numberFormat);
	
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
	    data.put(CSVDataConnector.PROPERTY_FILE, file);
	}
	return doOpenSession(data);
    }

    @Override
    public DSSession openSession(String connectionString, Properties properties) throws DSException {
	String file = getCheckConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	file = normalize(file);
	if (file == null && properties != null) {
	    file = properties.getProperty(CSVDataConnector.PROPERTY_FILE);
	}
	Map<String, Object> data = new HashMap<String, Object>();
	data.put(CSVDataConnector.PROPERTY_FILE, file);
	return doOpenSession(data);
    }

    @Override
    public DSSession openSession(String connectionString, String username, String password) throws DSException {
	String file = getCheckConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	file = normalize(file);
	Map<String, Object> parameterData = new HashMap<String, Object>();
	parameterData.put(CSVDataConnector.PROPERTY_FILE, file);
	return doOpenSession(parameterData);
    }
    
    @Override
    public DSSession openSession(Properties properties) throws DSException {
	if (properties == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "Properties are null");
	}
	String file = properties.getProperty(CSVDataConnector.PROPERTY_FILE);
	file = normalize(file);
	Map<String, Object> data = new HashMap<String, Object>();
	data.put(CSVDataConnector.PROPERTY_FILE, file);
	return doOpenSession(data);
    }
	
    @Override
    public DSSession openWrapSession(Object data) throws DSException {
	if (data == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "Data is null");
	}
	if (data instanceof Reader) {
	    Reader reader = (Reader) data;
	    return new CSVSession(reader);
	}
	handleContextException(DataManager.CONTEXT_SESSION, "Data is unknown");
	return null;
    }

    // DSSession: General method
    protected DSSession doOpenSession(Map<String, Object> data) throws DSException {
	
	String file = (String) data.get(CSVDataConnector.PROPERTY_FILE);
	String encoding = (String) data.get(CSVDataConnector.PROPERTY_ENCODING);
	String columnDelimiter = (String) data.get(CSVDataConnector.PROPERTY_COLUMN_DELIMITER);
	String rowDelimiter = (String) data.get(CSVDataConnector.PROPERTY_ROW_DELIMITER);
	Boolean firstRowHeader = getProperty(Boolean.class, data, CSVDataConnector.PROPERTY_FIRST_ROW_HEADER);
	String dateFormat = (String) data.get(CSVDataConnector.PROPERTY_DATE_FROMAT);
	String numberFormat = (String) data.get(CSVDataConnector.PROPERTY_NUMBER_FROMAT);
	
	if (file == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "File name is null");
	}

	file = normalize(file);
	if (file == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "File name is empty");
	}

	encoding = normalize(encoding);
	columnDelimiter = normalize(columnDelimiter);
	rowDelimiter = normalize(rowDelimiter);

	try {
	    Reader reader = createReader(file, encoding);
	    CSVSession session = new CSVSession(reader);
	    if (columnDelimiter != null) {
		session.setColumnDelimiter(columnDelimiter);
	    }
	    if (rowDelimiter != null) {
		session.setRowDelimiter(rowDelimiter);
	    }
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
	    data.put(CSVDataConnector.PROPERTY_FILE, file);
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

    // DSResultSet: General method
    protected DSResultSet doOpenResultSet(DSSession session, String query, ParameterValue[] parameters) throws DSException {
	if (session == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session is null");
	}
	if (!(session instanceof CSVSession)) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session must be CSVSession");
	}

	CSVSession csvSession = (CSVSession) session;
	Reader reader = csvSession.getReader();
	if (reader == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Reader is null");
	}
	// query is not supported
	CSVResultSet resultSet = new CSVResultSet(reader);
	
	resultSet.setColumnDelimiter(csvSession.getColumnDelimiter());
	resultSet.setRowDelimiter(csvSession.getRowDelimiter());
	resultSet.setFirstRowHeader(csvSession.isFirstRowHeader());
	
	return resultSet;

    }  
    
    @Override
    public DSDataSet openDataSet(DSSession session, DSDataSource dataSource) throws DSException {
	
	if (session == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session is null");
	}
	if (!(session instanceof CSVSession)) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session must be CSVSession");
	}

	CSVSession csvSession = (CSVSession) session;
	Reader reader = csvSession.getReader();
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
	CSVDataSet dataSet = new CSVDataSet(fields, reader);

	dataSet.setColumnDelimiter(csvSession.getColumnDelimiter());
	dataSet.setRowDelimiter(csvSession.getRowDelimiter());
	dataSet.setFirstRowHeader(csvSession.isFirstRowHeader());
	dataSet.setDateFormat(csvSession.getDateFormat());
	dataSet.setNumberFormat(csvSession.getNumberFormat());
	
	return dataSet;

    }

    public DSDataConnector createDataConnector() {
	return new CSVDataConnector();
    }

    public boolean supportsSingleDataSource() {
	return true;
    }
    

}

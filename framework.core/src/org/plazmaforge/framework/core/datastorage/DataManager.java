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

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.plazmaforge.framework.core.data.ParameterValue;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.datastorage.support.sql.SQLDataConnector;
import org.plazmaforge.framework.datastorage.support.sql.SQLDataProducerFactory;
import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * General DataManager.
 * 
 * Universal connection string:
 * 
 * [type]::driver=[driver]; url=[url]; username=[username]; password=[password]
 * 
 * @author ohapon
 *
 */
public class DataManager {

    
    public static final String SQL = "SQL";
    
    public static final String JDBC_CONNECTION = "JDBC_CONNECTION";
    
    public static final String JDBC_DATA_SOURCE = "JDBC_DATA_SOURCE";
    
    
    public static final String CONTEXT_SESSION = "CONTEXT_SESSION";
    
    public static final String CONTEXT_DATA_SET = "CONTEXT_DATA_SET";
    
    public static final String CONTEXT_RESULT_SET = "CONTEXT_RESULT_SET";
    
    
    
    public static final String PROPERTY_URL = "url";
    
    public static final String PROPERTY_USERNAME = "username";
    
    public static final String PROPERTY_USER = "user";
    
    public static final String PROPERTY_PASSWORD = "password";
    
    public static final String PROPERTY_QUERY = "query";
    
    
    private Map<String, DataProducerFactory> dataProducers;
    
    private Map<String, String> contextErrors;
    
    private static DataManager instance; 
    
    private DSDataProcessor dataProcessor;
    
    private DataManager() {
	dataProcessor = new DSDataProcessor();
    }
    
    private static DataManager getInstance() {
	if (instance == null) {
	    instance = new DataManager();
	    instance.init();
	}
	return instance;
    }    
    
   
    /**
     * Open session by DataConnector
     * 
     * @param dataConnector
     * @return session
     * @throws DSException
     */
    public static DSSession openSession(DSDataConnector dataConnector) throws DSException {
	if (dataConnector == null) {
	    handleContextException(CONTEXT_SESSION, "DataConnector is null.");
	}
	DataProducer dataProducer = getCheckDataProducer(CONTEXT_SESSION, dataConnector.getType());
	return dataProducer.openSession(dataConnector);
    }
    
    //sql::driver=; url=; username=; password=
    public static DSSession openSession(String connectionString) throws DSException {
	String[] values = parseConnectionString(CONTEXT_SESSION, connectionString);
	String type = values[0];
	String connection = values[1];
	DataProducer dataProducer = getCheckDataProducer(CONTEXT_SESSION, type);
	
	return dataProducer.openSession(connection);
    }
    
    public static DSSession openSession(String connectionString, Properties properties) throws DSException {
	String[] values = parseConnectionString(CONTEXT_SESSION, connectionString);
	String type = values[0];
	String connection = values[1];
	DataProducer dataProducer = getCheckDataProducer(CONTEXT_SESSION, type);
	
	return dataProducer.openSession(connection, properties);
    }

    public static DSSession openSession(String connectionString, String username, String password) throws DSException {
	String[] values = parseConnectionString(CONTEXT_SESSION, connectionString);
	String type = values[0];
	String connection = values[1];
	DataProducer dataProducer = getCheckDataProducer(CONTEXT_SESSION, type);
	
	return dataProducer.openSession(connection, username, password);
    }

    public static DSSession openSession(Connection connection) throws DSException {
	if (connection == null) {
	    handleContextException(CONTEXT_SESSION, "Connection is null.");
	}
	String type = SQL;
	DataProducer dataProducer = getCheckDataProducer(CONTEXT_SESSION, type);
	
	return dataProducer.openWrapSession(connection);
    }

    public static DSSession openSession(DataSource dataSource) throws DSException {
	if (dataSource == null) {
	    handleContextException(CONTEXT_SESSION, "DataSource is null.");
	}
	String type = SQL;
	DataProducer dataProducer = getCheckDataProducer(CONTEXT_SESSION, type);
	
	return dataProducer.openWrapSession(dataSource);
    }

    /**
     * Open DSResultSet by connection string.
     * Only for single DSSession/DSDataConnector (one DSSession/DSDataConnector - one DSResultSet: CSV, XML, XLS..)
     * @param connectionString
     * @return
     * @throws DSException
     */
    public static DSResultSet openResultSet(String connectionString) throws DSException {
	String[] values = parseConnectionString(CONTEXT_RESULT_SET, connectionString);
	String type = values[0];
	String connection = values[1];
	DataProducer dataProducer = getCheckDataProducer(CONTEXT_RESULT_SET, type);
	
	return dataProducer.openResultSet(connection);
    }
    
    public static DSResultSet openResultSet(DSSession session, DSDataSource dataSource, Object[] parameterValues) throws DSException {
	if (session == null) {
	    handleContextException(CONTEXT_RESULT_SET, "Session is null.");
	}
	if (dataSource == null) {
	    handleContextException(CONTEXT_RESULT_SET, "DataSource is null.");
	}
	String type = session.getType();
	DataProducer dataProducer = getCheckDataProducer(CONTEXT_RESULT_SET, type); // DataSource type
	
	DSResultSet resultSet = dataProducer.openResultSet(session, dataSource, parameterValues);
	
	// Processing ResultSet (filters, orders)
	resultSet = getInstance().dataProcessor.processResultSet(resultSet, dataSource);
	
	return resultSet; 
    }

    public static DSResultSet openResultSet(DSSession session, DSDataSource dataSource) throws DSException {
	if (session == null) {
	    handleContextException(CONTEXT_RESULT_SET, "Session is null.");
	}
	if (dataSource == null) {
	    handleContextException(CONTEXT_RESULT_SET, "DataSource is null.");
	}
	String type = session.getType();
	DataProducer dataProducer = getCheckDataProducer(CONTEXT_RESULT_SET, type); // DataSource type

	DSResultSet resultSet = dataProducer.openResultSet(session, dataSource);
	
	// Processing ResultSet (filters, orders)
	resultSet = getInstance().dataProcessor.processResultSet(resultSet, dataSource);
	
	return resultSet;
    }

    /**
     * Open DSResultSet by DSSession.
     * Only for single DSSession/DSDataConnector (one DSSession/DSDataConnector - one DSResultSet: CSV, XML, XLS..)
     * @param session
     * @return
     * @throws DSException
     */
    public static DSResultSet openResultSet(DSSession session) throws DSException {
	if (session == null) {
	    handleContextException(CONTEXT_RESULT_SET, "Session is null.");
	}
	String type = session.getType();
	DataProducer dataProducer = getCheckDataProducer(CONTEXT_RESULT_SET, type); // Session type
	
	return dataProducer.openResultSet(session);
    }
    
    public static DSResultSet openResultSet(DSSession session, String query) throws DSException {
	if (session == null) {
	    handleContextException(CONTEXT_RESULT_SET, "Session is null.");
	}
	String type = session.getType();
	DataProducer dataProducer = getCheckDataProducer(CONTEXT_RESULT_SET, type); // Session type
	
	return dataProducer.openResultSet(session, query);
    }

    public static DSResultSet openResultSet(DSSession session, String query, ParameterValue[] parameters) throws DSException {
	if (session == null) {
	    handleContextException(CONTEXT_RESULT_SET, "Session is null.");
	}
	String type = session.getType();
	DataProducer dataProducer = getCheckDataProducer(CONTEXT_RESULT_SET, type); // Session type
	
	return dataProducer.openResultSet(session, query, parameters);
    }

    
    
    public static DSDataSet openDataSet(DSSession session, DSDataSource dataSource) throws DSException {
	if (session == null) {
	    handleContextException(CONTEXT_DATA_SET, "Session is null.");
	}
	if (dataSource == null) {
	    handleContextException(CONTEXT_DATA_SET, "DataSource is null.");
	}
	String type = session.getType();
	DataProducer dataProducer = getCheckDataProducer(CONTEXT_DATA_SET, type); // DataSource type
	
	return dataProducer.openDataSet(session, dataSource);
    }
    
    
    public static void close(DSSession session) {
	if (session == null) {
	    return;
	}
	try {
	    session.close();
	} catch (DSException ex) {
	  //error
	}
    }

    public static void close(DSResultSet resultSet) {
	if (resultSet == null) {
	    return;
	}
	try {
	    resultSet.close();
	} catch (DSException ex) {
	    //error
	}
	
    }

    public static DataProducer getDataProducer(String type) {
	DataProducerFactory factory = getDataProducerFactory(type);
	return factory == null ? null : factory.getDataProducer();
    }
    
    public static DataProducerFactory getDataProducerFactory(String type) {
	return getInstance().dataProducers.get(normalizeKey(type));
    }
    
    public static void registerDataProducerFactory(String type, DataProducerFactory factory) {
	getInstance().dataProducers.put(normalizeKey(type), factory);
    }

    public static void unregisterDataProducerFactory(String type) {
	getInstance().dataProducers.remove(normalizeKey(type));
    }

    public static boolean supportsDataProducer(String type) {
	return getDataProducerFactory(type) != null;
    }
    
    private void init() {
	
	// Initialize context errors
	contextErrors = new HashMap<String, String>();
	contextErrors.put(CONTEXT_SESSION, "Can't open Session.");
	contextErrors.put(CONTEXT_RESULT_SET, "Can't open ResultSet.");
	contextErrors.put(CONTEXT_DATA_SET, "Can't open DataSet.");
	
	// Initialize data producers
	dataProducers = new HashMap<String, DataProducerFactory>();
	
	// Add producers by default 
	dataProducers.put(SQLDataConnector.TYPE, new SQLDataProducerFactory());
    }
    
    /**
     * Check and parse connection string
     * @param context
     * @param connectionString
     * @return String array
     *  [0] - type
     *  [1] - connection
     *   
     * @throws DSException
     */
    public static String[] parseConnectionString(String context, String connectionString) throws DSException {
	connectionString = getCheckConnectionString(context, connectionString);
	
	String type = null;
	String connection = null;
	
	int index = connectionString.indexOf("::");
	if (index >= 0) {
	    type = connectionString.substring(0, index);
	    index = index + 2; // ::
	    connection = index == connectionString.length() ? null : connectionString.substring(index);
	} else if (StringUtils.isJavaIdentifier(connectionString)) {
	    type = connectionString;
	} else {
	    connection = connectionString;
	}
	
	type = normalize(type);
	connection = normalize(connection);
	
	if (connection == null) {
	    handleContextException(context, "Connection is null");
	}
	
	String[] values = new String[] {type, connection};
	return values;
    }
    
    
    /**
     * Parse local connection string:
     * 
     * - first element: real connection string (url, file...) 
     * - second element: list of parameters 
     * 
     * @param context
     * @param connectionString
     * @return
     * @throws DSException
     */
    public static String[] parseLocalConnectionString(String context, String connectionString) throws DSException {
	
	connectionString = getCheckConnectionString(context, connectionString);
	
	String connection = getCheckConnectionString(context, connectionString);
	String parameters = null;
	int index = 0;
	
	index = connection.indexOf("->");
	if (index >= 0) {

	    connection = connectionString.substring(0, index);
	    index = index + 2; // ->
	    parameters = index == connectionString.length() ? null : connectionString.substring(index);
	    parameters = normalize(parameters);

	    if (parameters != null) {
		int startIndex = parameters.startsWith("(") ? 1 : 0;
		int endIndex = parameters.endsWith(")") ? parameters.length() - 1 : parameters.length();
		if (startIndex == endIndex) {
		    parameters = null;
		} else {
		    parameters = parameters.substring(startIndex, endIndex);
		}
	    }
	}
	
	String[] values = new String[] {connection, parameters};
	return values;
    }
    
    public static Map<String, Object> createConnectionParameterData(String parametersString) throws DSException {
	Map<String, Object> data = new HashMap<String, Object>();
	if (parametersString == null) {
	    return data;
	}
	populateConnectionParameterData(parametersString, data);
	return data;
    }
    
    public static void populateConnectionParameterData(String parametersString, Map<String, Object> data) throws DSException {
	populateParameterData(parametersString, data, ";"); // driver=[driver]; url=[url]; username=[username]; password=[password]
    }

    public static void populateParameterData(String parametersString, Map<String, Object> data, String separator) throws DSException {
	if (parametersString == null) {
	    return;
	}
	if (data == null) {
	    throw new DSException("Parameters data is null");
	}
	if (separator == null) {
	    separator = ",";
	}
	String[] parameterLines = parametersString.split(separator);
	for (String parameterLine : parameterLines ) {
	    parameterLine = normalize(parameterLine);
	    if (parameterLine == null) {
		continue;
	    }
	    String[] values = parameterLine.split("=");
	    if (values.length > 1) {
		data.put(values[0], values[1]);
	    }
	}
	
    }
    
    public static String getCheckConnectionString(String context, String connectionString) throws DSException {
	if (connectionString == null) {
	    handleContextException(CONTEXT_SESSION, "Connection string is null.");
	}
	connectionString = connectionString.trim();
	if (connectionString.isEmpty()) {
	    handleContextException(CONTEXT_SESSION, "Connection string is empty.");
	}
	return connectionString;
    }
    
    
    public static DataProducer getCheckDataProducer(String context, String type) throws DSException {
	
	// By default DataConnector type is SQL
	type = normalize(type);
	if (type == null) {
	    type = SQL;
	}
	
	DataProducer dataProducer = getDataProducer(type);
	if (dataProducer == null) {
	    handleContextException(context, "DataProducer not found by type '" + type + "'.");
	}
	return dataProducer;
    }
    
    public static String getContextMessage(String context) {
	return getInstance().contextErrors.get(context); 
    }

    public static void handleContextException(String context, Throwable cause) throws DSException {
	String message1 = getContextMessage(context);
	String message2 = cause == null ? null : cause.getMessage();
	String message = null;
	if (message1 != null || message2 != null) {
	    if (message1 == null) {
		message = message2;
	    } else if (message2 == null) {
		message = message1;
	    } else {
		message = message1 + " " + message2;
	    }
	    
	}
	throw new DSException(message/*getContextMessage(context)*/, cause);
    }

    public static void handleContextException(String context, String cause) throws DSException {
	handleException(getContextMessage(context), cause);
    }

    public static void handleException(String message, String cause) throws DSException {
	throw new DSException(message + " " + cause);
    }

    private static String normalize(String str) {
	return StringUtils.normalizeString(str);
    }
    
    private static String normalizeKey(String key) {
	key = normalize(key);
	return key == null ? null : key.toUpperCase();
    }
}


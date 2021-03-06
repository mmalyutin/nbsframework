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
package org.plazmaforge.framework.datastorage.support.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.plazmaforge.framework.core.data.ParameterValue;
import org.plazmaforge.framework.core.datastorage.AbstractDataProducer;
import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSField;
import org.plazmaforge.framework.core.datastorage.DSParameter;
import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.core.datastorage.DataProducer;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.core.sql.SQLBaseValueWriter;
import org.plazmaforge.framework.core.sql.SQLEnvironment;
import org.plazmaforge.framework.core.sql.SQLValueWriter;

/**
 * @author ohapon
 *
 */
public class SQLDataProducer extends AbstractDataProducer implements DataProducer {


    @Override
    public DSSession openSession(DSDataConnector dataConnector) throws DSException {
	if (dataConnector == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "DataConnector is null.");
	}
	if (!(dataConnector instanceof SQLDataConnector)) {
	    handleContextException(DataManager.CONTEXT_SESSION, "DataConnector must be SQLDataConnector");
	}	
	SQLDataConnector sqlDataConnector = (SQLDataConnector) dataConnector;
	
	String driver = sqlDataConnector.getDriver();
	String url = sqlDataConnector.getUrl();
	String username = sqlDataConnector.getUsername();
	String password = sqlDataConnector.getPassword();
	String query = sqlDataConnector.getQuery(); // experimental
	
	Map<String, Object> data = new HashMap<String, Object>();
	data.put(SQLDataConnector.PROPERTY_DRIVER, driver);
	data.put(SQLDataConnector.PROPERTY_URL, url);
	data.put(SQLDataConnector.PROPERTY_USERNAME, username);
	data.put(SQLDataConnector.PROPERTY_PASSWORD, password);
	data.put(SQLDataConnector.PROPERTY_QUERY, query); // experimental
	
	
	return doOpenSession(data);
    }

    @Override
    public DSSession openSession(String connectionString) throws DSException {
	// Parse connection string
	String[] values = parseLocalConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	String url = values[0];
	String parametersString = values[1];
	Map<String, Object> data = createConnectionParameterData(parametersString);
	url = normalize(url);
	if (url != null) {
	    data.put(SQLDataConnector.PROPERTY_URL, url);
	}
	return doOpenSession(data);
    }

    @Override
    public DSSession openSession(String connectionString, Properties properties) throws DSException {
	String url = getCheckConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	try {
	    return openWrapSession(DriverManager.getConnection(url, properties));
	} catch (SQLException ex) {
	    handleContextException(DataManager.CONTEXT_SESSION, ex);
	}
	return null;
    }

    @Override
    public DSSession openSession(String connectionString, String username, String password) throws DSException {
	String url = getCheckConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	try {
	    return openWrapSession(DriverManager.getConnection(url, username, password));
	} catch (SQLException ex) {
	    handleContextException(DataManager.CONTEXT_SESSION, ex);
	}
	return null;
    }
    
    @Override
    public DSSession openSession(Properties properties) throws DSException {
	if (properties == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "Properties are null.");
	}
	
	String url = properties.getProperty(DataManager.PROPERTY_URL);
	String username = properties.getProperty(DataManager.PROPERTY_USERNAME);
	String password = properties.getProperty(DataManager.PROPERTY_PASSWORD);
	
	if (username == null) {
	    username = properties.getProperty(DataManager.PROPERTY_USER);
	}

	return openSession(url, username, password);
    }
	
    @Override
    public DSSession openWrapSession(Object data) throws DSException {
	if (data == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "Data is null.");
	}
	
	try {
	    if (data instanceof Connection) {
		Connection jdbcConnection = (Connection) data;
		return new SQLSession(jdbcConnection);
	    }
	    
	    if (data instanceof DataSource) {
		DataSource jdbcDataSource = (DataSource) data;
		return new SQLSession(jdbcDataSource.getConnection());
	    }

	} catch (SQLException ex) {
	    throw new DSException(ex);
	}
	handleContextException(DataManager.CONTEXT_SESSION, "Data is unknown.");
	return null;
    }

    // DSSession: General method
    protected DSSession doOpenSession(Map<String, Object> data) throws DSException {
	
	String driver = (String) data.get(SQLDataConnector.PROPERTY_DRIVER);
	String url = (String) data.get(SQLDataConnector.PROPERTY_URL);
	String username = (String) data.get(SQLDataConnector.PROPERTY_USERNAME);
	String password = (String) data.get(SQLDataConnector.PROPERTY_PASSWORD);
	String query = (String) data.get(SQLDataConnector.PROPERTY_QUERY); // experimental
	
	if (url == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "URL is null");
	}

	url = normalizeUnquote(url);
	if (url == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "URL is empty");
	}

	driver = normalizeUnquote(driver);
	username = normalizeUnquote(username);
	query = normalizeUnquote(query, false); // why? trim=false
	
	try {
	    if (driver != null) {
		Class.forName(driver);
	    }
	    Connection connection = null;
	    if (username != null || password != null) {
		connection = DriverManager.getConnection(url, username, password);
	    } else {
		connection = DriverManager.getConnection(url);
	    }
	    SQLSession session = (SQLSession) openWrapSession(connection);
	    session.setQuery(query); // experimental
	    return session;
	    
	} catch (ClassNotFoundException ex) {
	    handleContextException(DataManager.CONTEXT_SESSION, ex);
	} catch (SQLException ex) {
	    handleContextException(DataManager.CONTEXT_SESSION, ex);
	}
	
	return null;
    }

    
    @Override
    public DSResultSet openResultSet(String connectionString) throws DSException {
	String[] values = parseLocalConnectionString(DataManager.CONTEXT_RESULT_SET, connectionString);
	String url = values[0];
	String parametersString = values[1];
	Map<String, Object> data = createConnectionParameterData(parametersString); 
	url = normalize(url);
	if (url != null) {
	    data.put(SQLDataConnector.PROPERTY_URL, url);
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
    
    protected DSResultSet doOpenResultSet(DSSession session, String query, ParameterValue[] parameters) throws DSException {
	if (session == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session is null.");
	}
	if (!(session instanceof SQLSession)) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session must be SQLSession");
	}

	SQLResultSet data = null;
	SQLSession sqlSession = (SQLSession) session;
	Connection cn = sqlSession.getConnection();
	if (cn == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "SQL Connection is null");
	}

	int parameterCount = parameters == null ? 0 : parameters.length;
	
	if (query == null) {
	    query = sqlSession.getQuery(); // experimental
	}	
	String sql = compileQuery(query, parameterCount);
	if (sql == null) {
	    return new SQLResultSet(null);
	}
	
	try {
	    ResultSet rs = prepareResultSet(cn, sql, parameters);
	    data = new SQLResultSet(rs);
	    return data;
	} catch (SQLException ex) {
	    throw new DSException(ex);
	}

    }

    @Override
    public DSDataSet openDataSet(DSSession session, DSDataSource dataSource) throws DSException {
	
	if (session == null) {
	    handleContextException(DataManager.CONTEXT_DATA_SET, "Session is null.");
	}
	if (!(session instanceof SQLSession)) {
	    handleContextException(DataManager.CONTEXT_DATA_SET, "Session must be SQLSession");
	}

	SQLDataSet data = null;
	SQLSession sqlSession = (SQLSession) session;
	Connection cn = sqlSession.getConnection();
	if (cn == null) {
	    handleContextException(DataManager.CONTEXT_DATA_SET, "SQL Connection is null");
	}

	String query = dataSource.getQueryText();
	if (query == null) {
	    query = sqlSession.getQuery(); // experimental
	}		
	if (query == null) {
	    handleContextException(DataManager.CONTEXT_DATA_SET, "SQL query is null");
	}
	List<DSParameter> dsParameters = dataSource.getParameters();
	
	List<DSField> dsFields = dataSource.getFields();
	List<DSField> fields = new ArrayList<DSField>();
	DSField field = null;
	for (DSField dsField: dsFields) {
	    field = dsField.clone();
	    fields.add(field);
	}
	
	int parameterCount = dsParameters == null ? 0 : dsParameters.size();
	String sql = compileQuery(query, parameterCount);
	if (sql == null) {
	    handleContextException(DataManager.CONTEXT_DATA_SET, "SQL query is empty");
	    //return new SQLDataSet(fields, null);
	}
	
	// Default value of parameter
	List<ParameterValue> parameters = null;
	if (parameterCount > 0) {
	    parameters = new ArrayList<ParameterValue>();
	    ParameterValue parameter = null;
	    for (DSParameter dsParameter: dsParameters) {
		parameter = new ParameterValue(dsParameter.getDataType(), dsParameter.getDefaultValue());
		parameters.add(parameter);
	    }
	}
	
	try {
	    ResultSet rs = prepareResultSet(cn, sql, parameters == null ? null : parameters.toArray(new ParameterValue[0]));
	    data = new SQLDataSet(fields, rs);
	    return data;
	} catch (SQLException ex) {
	    throw new DSException(ex);
	}
    }


    ////
    
    public DSDataConnector createDataConnector() {
	return new SQLDataConnector();
    }
    
    ////
    
    protected ResultSet prepareResultSet(Connection cn, String sql, ParameterValue[] parameters) throws SQLException {
	PreparedStatement stm = cn.prepareStatement(sql);
	int inputParameterCount = parameters == null ? 0 : parameters.length;
	if (inputParameterCount > 0) {
	    SQLValueWriter valueWriter = new SQLBaseValueWriter();
	    ParameterValue parameter = null;
	    for (int i = 0; i < inputParameterCount; i++) {
		parameter = parameters[i];
		setParameter(valueWriter, stm, parameter.getValue(), i + 1, parameter.getType());
	    }
	}
	ResultSet rs = stm.executeQuery();
	return rs;
    }
    
    protected void setParameter(SQLValueWriter valueWriter, PreparedStatement stm, Object value, int index, String type) throws SQLException {
	int sqlType = SQLEnvironment.getSQLType(type);
	valueWriter.setValue(stm, value, index, sqlType);
    }
    
    protected String getQuery(DSSession session) {
	return ((SQLSession) session).getQuery();
    }
    
}

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
package org.plazmaforge.framework.datastorage.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.plazmaforge.framework.core.data.ParameterValue;
import org.plazmaforge.framework.core.datastorage.AbstractDataProducer;
import org.plazmaforge.framework.core.datastorage.DSDataConnector;
import org.plazmaforge.framework.core.datastorage.DSDataSet;
import org.plazmaforge.framework.core.datastorage.DSDataSource;
import org.plazmaforge.framework.core.datastorage.DSResultSet;
import org.plazmaforge.framework.core.datastorage.DSSession;
import org.plazmaforge.framework.core.datastorage.DataManager;
import org.plazmaforge.framework.core.datastorage.DataProducer;
import org.plazmaforge.framework.core.datastorage.data.QueryAnalyzer;
import org.plazmaforge.framework.core.datastorage.data.QueryInfo;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.core.sql.SQLBaseValueWriter;
import org.plazmaforge.framework.core.sql.SQLEnvironment;
import org.plazmaforge.framework.core.sql.SQLValueWriter;

/**
 * @author ohapon
 *
 */
public class SQLDataProducer extends AbstractDataProducer implements DataProducer {


    public static final boolean ALWAYS_ANALIZE_QUERY = false;
    
    
    @Override
    public DSSession openSession(DSDataConnector dataConnector) throws DSException {
	if (dataConnector == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "DataConnector is null.");
	}
	String url = dataConnector.getUrl();
	String username = dataConnector.getUsername();
	String password = dataConnector.getPassword();
	return openSession(url, username, password);
    }

    @Override
    public DSSession openSession(String connectionString) throws DSException {
	String url = getCheckConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	try {
	    return openWrapSession(DriverManager.getConnection(url));
	} catch (SQLException ex) {
	    handleContextException(DataManager.CONTEXT_SESSION, ex);
	}
	return null;
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


    @Override
    public DSResultSet openResultSet(DSSession session, String query, ParameterValue[] parameters) throws DSException {
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
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Connection is null");
	}

	String sql = normalize(query);
	if (sql == null) {
	    return new SQLResultSet(null);
	}
	
	boolean hasParameters = !isEmpty(parameters);
	int inputParameterCount = hasParameters ? parameters.length : 0;
	
	boolean needAnalizeQuery = hasParameters || ALWAYS_ANALIZE_QUERY; 
	if (needAnalizeQuery) {
	    QueryAnalyzer queryAnalyzer = new QueryAnalyzer();
	    QueryInfo queryInfo = queryAnalyzer.analyzeQuery(sql);
	    
	    
	    int queryParameterCount = queryInfo.getParameterCount();
	    int uniqueParameterCount = queryInfo.getUniqueParameterCount();
	    
	    // TODO: Only for named parameters (:param1, :param2)
	    // But it doesn't work for '?' parameters! 
	    if (inputParameterCount !=  uniqueParameterCount) {
		handleContextException(DataManager.CONTEXT_RESULT_SET, "Incorrect parameter count. Input/Query parameters: " + inputParameterCount + "/" + uniqueParameterCount);
	    }
	    
	    sql = queryInfo.getCompileQuery();
	}

	
	try {
	    PreparedStatement stm = cn.prepareStatement(sql);
	    if (hasParameters) {
		SQLValueWriter valueWriter = new SQLBaseValueWriter();
		ParameterValue parameter = null; 
		for (int i = 0; i < inputParameterCount; i++) {
		    parameter = parameters[i];
		    setParameter(valueWriter, stm, parameter.getValue(), i + 1, parameter.getType());
		}
	    }
	    ResultSet rs = stm.executeQuery();
	    data = new SQLResultSet(rs);
	    return data;
	} catch (SQLException ex) {
	    throw new DSException(ex);
	}

    }

    @Override
    public DSDataSet openDataSet(DSSession session, DSDataSource dataSource) throws DSException {
	// TODO
	return null;
    }


    ////
    
    protected void setParameter(SQLValueWriter valueWriter, PreparedStatement stm, Object value, int index, String type) throws SQLException {
	int sqlType = SQLEnvironment.getSQLType(type);
	valueWriter.setValue(stm, value, index, sqlType);
    }
}

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

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
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
	    handleContextException(DataManager.CONTEXT_SESSION, "DataConnector is null.");
	}
	if (!(dataConnector instanceof CSVDataConnector)) {
	    handleContextException(DataManager.CONTEXT_SESSION, "DataConnector must be CSVDataConnector");
	}	
	CSVDataConnector sqlDataConnector = (CSVDataConnector) dataConnector;
	String url = sqlDataConnector.getFileName();
	String username = sqlDataConnector.getUsername();
	String password = sqlDataConnector.getPassword();
	return openSession(url, username, password);
    }

    @Override
    public DSSession openSession(String connectionString) throws DSException {
	String url = getCheckConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	return doOpenSession(url, null, null);
    }

    @Override
    public DSSession openSession(String connectionString, Properties properties) throws DSException {
	String url = getCheckConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	
	if (url == null || url.isEmpty()) {
	    url = properties.getProperty(DataManager.PROPERTY_URL);
	}
	String username = properties.getProperty(DataManager.PROPERTY_USERNAME);
	String password = properties.getProperty(DataManager.PROPERTY_PASSWORD);
	
	if (username == null) {
	    username = properties.getProperty(DataManager.PROPERTY_USER);
	}
	return doOpenSession(url, username, password);
    }

    @Override
    public DSSession openSession(String connectionString, String username, String password) throws DSException {
	String url = getCheckConnectionString(DataManager.CONTEXT_SESSION, connectionString);
	return doOpenSession(url, username, password);
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

	return doOpenSession(url, username, password);
    }
	
    @Override
    public DSSession openWrapSession(Object data) throws DSException {
	if (data == null) {
	    handleContextException(DataManager.CONTEXT_SESSION, "Data is null.");
	}
	if (data instanceof Reader) {
	    Reader reader = (Reader) data;
	    return new CSVSession(reader);
	}
	handleContextException(DataManager.CONTEXT_SESSION, "Data is unknown.");
	return null;
    }

    // General method
    protected DSSession doOpenSession(String url, String username, String password) throws DSException {
	String fileName = url;
	try {
	    Reader reader = new FileReader(fileName);
	    return new CSVSession(reader);
	} catch (IOException ex) {
	    throw new DSException(ex);
	}
    }

    @Override
    public DSResultSet openResultSet(String connectionString) throws DSException {
	String url = getCheckConnectionString(DataManager.CONTEXT_RESULT_SET, connectionString);
	String fileName = url;
	try {
	    Reader reader = new FileReader(fileName);
	    return new CSVResultSet(reader);
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
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session is null.");
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
	return new CSVResultSet(reader);

    }  
    
    @Override
    public DSDataSet openDataSet(DSSession session, DSDataSource dataSource) throws DSException {
	
	if (session == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session is null.");
	}
	if (!(session instanceof CSVSession)) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session must be SQLSession");
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
	// query is not supported
	//return new CSVDataSet(fields, reader);
	
	//TODO: Not implemented
	return null;
	
    }


}

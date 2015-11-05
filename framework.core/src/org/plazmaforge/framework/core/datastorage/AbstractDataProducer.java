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


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.plazmaforge.framework.core.data.ParameterValue;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.util.StringUtils;
import org.plazmaforge.framework.util.SystemUtils;

/**
 * @author ohapon
 *
 */
public abstract class AbstractDataProducer implements DataProducer {

    
    @Override
    public DSResultSet openResultSet(DSSession session, DSDataSource dataSource)  throws DSException {
	if (session == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "Session is null.");
	}
	if (dataSource == null) {
	    handleContextException(DataManager.CONTEXT_RESULT_SET, "DataSource is null");
	}
	
	List<ParameterValue> parameters = null;
	if (dataSource.hasParameters()) {
	    
	    // Prepare parameter values
	    // Value is default value of parameter
	    List<DSParameter> dsParameters = dataSource.getParameters();
	    int dsParameterCount = dsParameters == null ? 0: dsParameters.size();
	    
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
	    
	}
	
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
	
	List<ParameterValue> parameters = null;
	if (dataSource.hasParameters()) {
	    
	    // Prepare parameter values
	    // Value is default value of parameter
	    List<DSParameter> dsParameters = dataSource.getParameters();
	    
	    int inputParameterCount = parameterValues == null ? 0: parameterValues.length;
	    int dsParameterCount = dsParameters == null ? 0: dsParameters.size();
	    
	    if (inputParameterCount !=  dsParameterCount) {
		handleContextException(DataManager.CONTEXT_RESULT_SET, "Incorrect parameter count. Input/DataSource parameters: " + inputParameterCount + "/" + dsParameterCount);
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
	    
	}
	
	return openResultSet(session, dataSource.getQueryText(), parameters == null ? null : parameters.toArray(new ParameterValue[0]));
    }
    
    @Override
    public DSResultSet openResultSet(DSSession session, String query) throws DSException {
	return openResultSet(session, query, null);
    }
    
    ////
    
    protected String getCheckConnectionString(String context, String connectionString) throws DSException {
	return DataManager.getCheckConnectionString(context, connectionString);
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


}

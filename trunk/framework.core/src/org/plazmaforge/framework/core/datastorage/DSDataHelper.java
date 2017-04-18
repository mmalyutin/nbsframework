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

package org.plazmaforge.framework.core.datastorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.plazmaforge.framework.core.datastorage.data.QueryAnalyzer;
import org.plazmaforge.framework.core.datastorage.data.QueryInfo;
import org.plazmaforge.framework.core.datastorage.data.QueryParameter;
import org.plazmaforge.framework.core.exception.DSEvaluateException;
import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * @author ohapon
 *
 */
public class DSDataHelper {

    
    /**
     * Find parameter by name
     * @param parameters
     * @param name
     * @return
     */
    public DSParameter findParameter(List<DSParameter> parameters, String name) {
	if (parameters == null || parameters.isEmpty()) {
	    return null;
	}
	for (DSParameter parameter : parameters) {
	    if (name.equals(parameter.getName())) {
		return parameter;
	    }
	}
	return null;
    }
   
    /**
     * Add new query parameters to DataSource by query analyzing
     * @param dataSource
     */
    public void generateQueryParameters(DSDataSource dataSource) {
	generateQueryParameters(dataSource, null, null, false);
    }

    
    /**
     * Add new query parameters to DataSource by query analyzing
     * 
     * For example:
     *  query = 'SELECT NAME, PRICE FROM PRODUCT WHERE PRICE >= :PRICE_START AND PRICE <= :PRICE_END'
     *  new parameters = PRICE_START, PRICE_END
     * 
     * @param dataSource
     * @param parameterPrefix
     * @param parameterSuffix
     * @param override
     */
    public void generateQueryParameters(DSDataSource dataSource, String parameterPrefix, String parameterSuffix, boolean override) {
	if (dataSource == null) {
	    return;
	}
	String query = dataSource.getQueryText();
	query = normalize(query);
	if (query == null) {
	    return;
	}

	QueryAnalyzer queryAnalyzer = new QueryAnalyzer();
	QueryInfo queryInfo = queryAnalyzer.analyzeQuery(query);
	
	Set<QueryParameter> parameters = queryInfo.getUniqueParameters();
	int parameterCount = parameters == null ? 0: parameters.size();
	if (parameterCount == 0) {
	    return;
	}
	
	parameterPrefix = normalize(parameterPrefix);
	parameterSuffix = normalize(parameterSuffix);
	
	boolean isExpression = parameterPrefix != null || parameterSuffix != null;
	
	parameterPrefix = parameterPrefix == null ? "" : parameterPrefix;
	parameterSuffix = parameterSuffix == null ? "" : parameterSuffix;
	
	for (QueryParameter parameter : parameters) {
	    String parameterName = parameter.getName();
	    DSParameter findParameter = dataSource.getParameter(parameterName);
	    boolean needParameter = false;
	    
	    if (findParameter == null) {
		needParameter = true;
	    } else {
		if (override && isExpression) {
		    dataSource.removeParameter(findParameter);
		    needParameter = true;
		}
	    }
	    
	    if (needParameter) {
		DSParameter newParameter = null;
		if (isExpression) {
		    DSExpressionParameter expressionParameter = new DSExpressionParameter();
		    expressionParameter.setExpressionText(parameterPrefix + parameterName + parameterSuffix);
		    newParameter = expressionParameter;
		} else {
		    newParameter = new DSParameter();
		}
		newParameter.setName(parameterName);
		dataSource.addParameter(newParameter);
	    }
	}
	
    }
    
    public void transferParametersToDataSource(DSDataSource dataSource, List<DSParameter> generalParameters, Map<String, Object> parameters) {
	if (dataSource == null || parameters == null || parameters.isEmpty()) {
	    return;
	}

	// Generate parameters by QUERY TEXT (:parameter1, :parameter2)
	generateQueryParameters(dataSource);

	// No parameters
	if (!dataSource.hasParameters()) {
	    return;
	}

	List<DSParameter> dsParameters = dataSource.getParameters();
	for (DSParameter dsParameter : dsParameters) {
	    if (dsParameter instanceof DSExpressionParameter) {
		continue;
	    }
	    String name = dsParameter.getName();
	    if (name == null) {
		continue;
	    }
	    Object value = dsParameter.getDefaultValue();

	    // Fixed dataType
	    if (dsParameter.getDataType() == null) {
		DSParameter reportParameter = findParameter(generalParameters, name);
		if (reportParameter != null) {
		    dsParameter.setDataType(reportParameter.getDataType());
		}
	    }
	    // If default value is setting then break
	    if (value != null) {
		continue;
	    }
	    // Get value from parameters map
	    value = parameters.get(name);
	    if (value == null) {
		continue;
	    }
	    dsParameter.setDefaultValue(value); // TODO: Must compare 'data-type' and type of value
	}
    }
    
    /**
     * Evaluate DataSource parameters
     * 
     * @param dataSource
     * @param expressionEvaluator
     * @return
     * @throws DSEvaluateException
     */
    public List<Object> evaluateParameters(DSDataSource dataSource, DSExpressionEvaluator expressionEvaluator) throws DSEvaluateException {
	if (!dataSource.hasParameters()) {
	    return null;
	}
	
	List<Object> outputParameters = null;
	List<DSParameter> inputParameters = dataSource.getParameters();
	outputParameters = new ArrayList<Object>();
	Object outputParameter = null;

	for (DSParameter inputParameter : inputParameters) {

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

	return outputParameters;
    }
    
    protected String normalize(String str) {
	return StringUtils.normalizeString(str);
    }
    
    protected boolean isEmpty(String str) {
	return normalize(str) == null;
    }
}

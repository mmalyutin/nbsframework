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
package org.plazmaforge.framework.core.datastorage.data;

import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.datastorage.DSExpression;

/**
 * @author ohapon
 *
 */
public class Scope {

    
    public static final String PARAMETER = "PARAMETER";
    
    public static final String FIELD = "FIELD";
    
    public static final String VARIABLE = "VARIABLE";
    
    public static final String GROUP = "GROUP";
    
    public static final String DATA = "DATA"; // custom data context
    

    private Map<String, Map<String, EvaluationValue>> contextData;
    
    private Map<String, AggregationValue> variableAggregationValues;
    
    
    public Scope() {
	
	contextData = new HashMap<String, Map<String, EvaluationValue>>();
	contextData.put(PARAMETER, new HashMap<String, EvaluationValue>());
	contextData.put(FIELD, new HashMap<String, EvaluationValue>());
	contextData.put(VARIABLE, new HashMap<String, EvaluationValue>());
	contextData.put(GROUP, new HashMap<String, EvaluationValue>());
	
	variableAggregationValues = new HashMap<String, AggregationValue>();
	
    }

    // Parameters
    
    public void setParameterValue(String parameter, Object value) {
	setScopeValue(PARAMETER, parameter, DSExpression.EVALUATION_DEFAULT, value);
    }
    
    public Object getParameterValue(String parameter) {
	return getScopeValue(PARAMETER, parameter, DSExpression.EVALUATION_DEFAULT);
    }
    
    // Fields
    
    public void setFieldOldValue(String field, Object value) {
	setScopeValue(FIELD, field, DSExpression.EVALUATION_OLD, value);
    }
    
    public Object getFieldOldValue(String field) {
	return getScopeValue(FIELD, field, DSExpression.EVALUATION_OLD);
    }
    
    public void setFieldValue(String field, Object value) {
	setScopeValue(FIELD, field, DSExpression.EVALUATION_DEFAULT, value);
    }
    
    public Object getFieldValue(String field) {
	return getScopeValue(FIELD, field, DSExpression.EVALUATION_DEFAULT);
    }
    
    // Variables
    
    public void setVariableInitValue(String variable, Object value) {
	setScopeValue(VARIABLE, variable, DSExpression.EVALUATION_INIT, value);
    }
    
    public Object getVariableInitValue(String variable) {
	return getScopeValue(VARIABLE, variable, DSExpression.EVALUATION_INIT);
    }
    
    public void setVariableOldValue(String variable, Object value) {
	setScopeValue(VARIABLE, variable, DSExpression.EVALUATION_OLD, value);
    }
    
    public Object getVariableOldValue(String variable) {
	return getScopeValue(VARIABLE, variable, DSExpression.EVALUATION_OLD);
    }

    public void setVariableValue(String variable, Object value) {
	setScopeValue(VARIABLE, variable, DSExpression.EVALUATION_DEFAULT, value);
    }
    
    public Object getVariableValue(String variable) {
	return getScopeValue(VARIABLE, variable, DSExpression.EVALUATION_DEFAULT);
    }

    // Aggregation variables
    public void setVariableAggregationValue(String variable, AggregationValue value) {
	variableAggregationValues.put(variable, value);
    }

    public AggregationValue getVariableAggregationValue(String variable) {
	return variable == null ? null : variableAggregationValues.get(variable);
    }

    // Groups
    public void setGroupOldValue(String group, Object value) {
	setScopeValue(GROUP, group, DSExpression.EVALUATION_OLD, value);
    }
    
    public Object getGroupOldValue(String group) {
	return getScopeValue(GROUP, group, DSExpression.EVALUATION_OLD);
    }
    
    public void setGroupValue(String group, Object value) {
	setScopeValue(GROUP, group, DSExpression.EVALUATION_DEFAULT, value);
    }
    
    public Object getGroupValue(String group) {
	return getScopeValue(GROUP, group, DSExpression.EVALUATION_DEFAULT);
    }

    public Object getScopeValue(String context, String name, int evaluation) {
	if (context == null || name == null) {
	    return null;
	}
	Map<String, EvaluationValue> contextValues = getContextValues(context);
	if (contextValues == null) {
	    return null;
	}
	EvaluationValue contextValue = contextValues.get(name);
	if (contextValue == null) {
	    return null;
	}
	
	if (evaluation == DSExpression.EVALUATION_INIT) {
	    return contextValue.getInitValue();
	} else if (evaluation == DSExpression.EVALUATION_OLD) {
	    return contextValue.getOldValue();
	} else {
	    return contextValue.getValue();
	}
    }
    
    public void setScopeValue(String context, String name, int evaluation, Object value) {
	if (context == null || name == null) {
	    return;
	}
	Map<String, EvaluationValue> contextValues = getContextValues(context);
	if (contextValues == null) {
	    contextValues = new HashMap<String, EvaluationValue>();
	    contextData.put(context, contextValues);
	}
	EvaluationValue contextValue = contextValues.get(name);
	if (contextValue == null) {
	    contextValue = new EvaluationValue();
	    contextValues.put(name, contextValue);
	}
	if (evaluation == DSExpression.EVALUATION_INIT) {
	    contextValue.setInitValue(value);
	} if (evaluation == DSExpression.EVALUATION_OLD) {
	    contextValue.setOldValue(value);
	} else {
	    contextValue.setValue(value);
	}
    }
    
    protected Map<String, EvaluationValue> getContextValues(String context) {
	return contextData.get(context);
    }
    
    public boolean isNewScopeValue(Object oldValue, Object newValue) {
	return !equalsScopeValues(oldValue, newValue);
    }
    
    protected boolean equalsScopeValues(Object oldValue, Object newValue) {
	if (oldValue == null && newValue == null) {
	    return true;
	}
	if (oldValue == null || newValue == null) {
	    return false;
	}
	return oldValue.equals(newValue);
	
    }
    

}

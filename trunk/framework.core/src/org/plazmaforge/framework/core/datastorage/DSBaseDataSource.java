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
import java.util.List;


/**
 * @author ohapon
 *
 */
public class DSBaseDataSource extends AbstractDataSource implements DSDataSource, HasExpressionBuilder  {

    private static final long serialVersionUID = 4764564520974413985L;
    

    private List<DSParameter> parameters;
    
    private List<DSField> fields;
    
    private List<DSVariable> variables;
    
    private List<DSOrder> orders;
    
    private List<DSFilter> filters;
    
    private List<DSGroup> groups;

    
    public DSBaseDataSource() {
	super();
    }


    @Override
    public List<DSParameter> getParameters() {
	if (parameters == null) {
	    parameters = new ArrayList<DSParameter>();
	}
        return parameters;
    }
    
    @Override
    public DSParameter getParameter(String name) {
	if (name == null || parameters == null || parameters.isEmpty()) {
	    return null;
	}
	for (DSParameter parameter: parameters) {
	    if (name.equals(parameter.getName())) {
		return parameter;
	    }
	}
        return null;
    }

    @Override
    public void setParameters(List<DSParameter> parameters) {
        this.parameters = parameters;
    }
    
    @Override
    public void addParameter(DSParameter parameter) {
        getParameters().add(parameter);
    }

    @Override
    public void removeParameter(DSParameter parameter) {
        getParameters().remove(parameter);
    }    
    @Override
    public boolean hasParameters() {
	return parameters != null && !parameters.isEmpty();
    }

    @Override
    public int getParameterCount() {
	return parameters == null ? 0 : parameters.size();
    }
    
    @Override
    public List<DSField> getFields() {
	if (fields == null) {
	    fields = new ArrayList<DSField>();
	}
        return fields;
    }

    @Override
    public DSField getField(String name) {
	if (name == null || fields == null || fields.isEmpty()) {
	    return null;
	}
	for (DSField field : fields) {
	    if (name.equals(field.getName())) {
		return field;
	    }
	}
	return null;
    }
    
    @Override
    public void setFields(List<DSField> fields) {
        this.fields = fields;
    }

    @Override
    public void addField(DSField field) {
        getFields().add(field);
    }

    @Override
    public void removeField(DSField field) {
        getFields().remove(field);
    }

    @Override
    public boolean hasFields() {
	return fields != null && !fields.isEmpty();
    }

    @Override
    public int getFieldCount() {
	return fields == null ? 0 : fields.size();
    }
    
    @Override
    public List<DSVariable> getVariables() {
	if (variables == null) {
	    variables = new ArrayList<DSVariable>();
	}
        return variables;
    }

    @Override
    public DSVariable getVariable(String name) {
	if (name == null || variables == null || variables.isEmpty()) {
	    return null;
	}
	for (DSVariable variable : variables) {
	    if (name.equals(variable.getName())) {
		return variable;
	    }
	}
	return null;
    }
    
    @Override
    public void setVariables(List<DSVariable> variables) {
        this.variables = variables;
    }
    
    @Override
    public void addVariable(DSVariable variable) {
        getVariables().add(variable);
    }

    @Override
    public void removeVariable(DSVariable variable) {
        getVariables().remove(variable);
    }
    
    @Override
    public boolean hasVariables() {
	return variables != null && !variables.isEmpty();
    }

    @Override
    public int getVariableCount() {
	return variables == null ? 0 : variables.size();
    }

    @Override   
    public List<DSOrder> getOrders() {
	if (orders == null) {
	    orders = new ArrayList<DSOrder>();
	}
        return orders;
    }

    @Override    
    public void setOrders(List<DSOrder> orders) {
        this.orders = orders;
    }
    @Override
    public void addOrder(DSOrder order) {
        getOrders().add(order);
    }

    @Override
    public void removeOrder(DSOrder order) {
        getOrders().remove(order);
    }

    @Override    
    public boolean hasOrders() {
	return orders != null && !orders.isEmpty();
    }

    @Override    
    public int getOrderCount() {
	return orders == null ? 0 : orders.size();
    }

    @Override    
    public List<DSFilter> getFilters() {
	if (filters == null) {
	    filters = new ArrayList<DSFilter>();
	}
        return filters;
    }

    @Override    
    public void setFilters(List<DSFilter> filters) {
        this.filters = filters;
    }

    @Override
    public void addFilter(DSFilter filter) {
        getFilters().add(filter);
    }

    @Override
    public void removeFilter(DSFilter filter) {
        getFilters().remove(filter);
    }
    
    @Override    
    public boolean hasFilters() {
	return filters != null && !filters.isEmpty();
    }

    @Override
    public int getFilterCount() {
	return filters == null ? 0 : filters.size();
    }
    
    @Override    
    public List<DSGroup> getGroups() {
	if (groups == null) {
	    groups = new ArrayList<DSGroup>();
	}
        return groups;
    }

    @Override    
    public void setGroups(List<DSGroup> groups) {
        this.groups = groups;
    }
    
    @Override
    public void addGroup(DSGroup group) {
        getGroups().add(group);
    }

    @Override
    public void removeGroup(DSGroup group) {
        getGroups().remove(group);
    }    
    
    @Override    
    public boolean hasGroups() {
	return groups != null && !groups.isEmpty();
    }

    @Override
    public int getGroupCount() {
	return groups == null ? 0 : groups.size();
    }

    @Override
    public List<DSExpression> buildExpressions() {
	List<DSExpression> expressions = new ArrayList<DSExpression>();
	populateExpressions(expressions);
	return expressions;
    }

    @Override
    public void populateExpressions(List<DSExpression> expressions) {
	
	if (hasParameters()) {
	    for (DSParameter parameter: parameters) {
		populateExpressions(expressions, parameter);
	    }
	}
	
	if (hasFields()) {
	    for (DSField field: fields) {
		populateExpressions(expressions, field);
	    }
	}
	
	if (hasVariables()) {
	    for (DSVariable variable: variables) {
		populateExpressions(expressions, variable);
	    }
	}
	
	if (hasFilters()) {
	    for (DSFilter filter: filters) {
		populateExpressions(expressions, filter);
	    }
	}

	if (hasOrders()) {
	    for (DSOrder order: orders) {
		populateExpressions(expressions, order);
	    }
	}
	
	if (hasGroups()) {
	    for (DSGroup group: groups) {
		populateExpressions(expressions, group);
	    }
	}
	
    }

    
    protected void populateExpressions(List<DSExpression> expressions, Object element) {
	if (element == null) {
	    return;
	}
	if (element instanceof HasExpressionBuilder) {
	    ((HasExpressionBuilder) element).populateExpressions(expressions);
	    return;
	}
	if (element instanceof HasExpression) {
	    HasExpression hasExpression = (HasExpression) element;
	    DSExpression exprsession = hasExpression.getExpression();
	    if (exprsession == null || exprsession.isEmpty()) {
		return;
	    }
	    expressions.add(exprsession);
	}
    }


    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((fields == null) ? 0 : fields.hashCode());
	result = prime * result + ((filters == null) ? 0 : filters.hashCode());
	result = prime * result + ((groups == null) ? 0 : groups.hashCode());
	result = prime * result + ((orders == null) ? 0 : orders.hashCode());
	result = prime * result
		+ ((parameters == null) ? 0 : parameters.hashCode());
	result = prime * result
		+ ((variables == null) ? 0 : variables.hashCode());
	return result;
    }


    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	DSBaseDataSource other = (DSBaseDataSource) obj;
	if (fields == null) {
	    if (other.fields != null)
		return false;
	} else if (!fields.equals(other.fields))
	    return false;
	if (filters == null) {
	    if (other.filters != null)
		return false;
	} else if (!filters.equals(other.filters))
	    return false;
	if (groups == null) {
	    if (other.groups != null)
		return false;
	} else if (!groups.equals(other.groups))
	    return false;
	if (orders == null) {
	    if (other.orders != null)
		return false;
	} else if (!orders.equals(other.orders))
	    return false;
	if (parameters == null) {
	    if (other.parameters != null)
		return false;
	} else if (!parameters.equals(other.parameters))
	    return false;
	if (variables == null) {
	    if (other.variables != null)
		return false;
	} else if (!variables.equals(other.variables))
	    return false;
	return true;
    }
    
    
}

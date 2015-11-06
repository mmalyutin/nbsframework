package org.plazmaforge.framework.erm.query;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.criteria.Connector;

public class ContainerFilterDef extends FilterDef {

    List<FilterDef> filters = new ArrayList<FilterDef>();
    
    private Connector innerConnector;
    
    
    public ContainerFilterDef() {
    }
    
    public ContainerFilterDef(Connector innerConnector) {
	this.innerConnector = innerConnector;
    }

    public Connector getInnerConnector() {
        return innerConnector;
    }

    public List<FilterDef> getFilters() {
        return filters;
    }

    public void addFilter(FilterDef filter) {
        filters.add(filter);
    }
    
    public boolean hasFilters() {
	return filters != null && !filters.isEmpty();
    }
    
    @Override
    public boolean isParameter() {
	if (filters == null || filters.isEmpty()) {
	    return false;
	}
	for (FilterDef filter: filters) {
	    if (filter.isParameter()) {
		return true;
	    }
	}
	return false;
    }
    
    @Override
    public int getParameterCount() {
	if (filters == null || filters.isEmpty()) {
	    return 0;
	}
	int count = 0;
	for (FilterDef filter: filters) {
	    if (!filter.isParameter()) {
		continue;
	    }
	    count = count + filter.getParameterCount();
	}
	return count;
    }
    

    @Override
    public TypeValue getTypeValue() {
	
	int parameterCount = getParameterCount();
	if (parameterCount != 1) {
	    throw new RuntimeException("Filter has more values");
	}
	TypeValue[] typeValues = getTypeValues();
	if (typeValues == null || typeValues.length != 1) {
	    throw new RuntimeException("Filter has not one value");
	}
	return typeValues[0];
    }

    @Override
    public TypeValue[] getTypeValues() {
	if (filters == null || filters.isEmpty()) {
	    return new TypeValue[0];
	}
	List<TypeValue> typeValues = new ArrayList<TypeValue>();
	for (FilterDef filter: filters) {
	    if (!filter.isParameter()) {
		continue;
	    }
	    int parameterCount = filter.getParameterCount();
	    if (parameterCount == 1) {
		typeValues.add(filter.getTypeValue());
	    } else {
		TypeValue[] cTypeValues = filter.getTypeValues();
		for (TypeValue cTypeValue : cTypeValues) {
		    typeValues.add(cTypeValue);
		}
	    }
	}
	return typeValues.toArray(new TypeValue[0]);
    }
    
    public String toSqlString() {
	if (!hasFilters()) {
	    return "(1=1)";
	}
	List<FilterDef> filters = getFilters();
	int count = filters.size();
	StringBuffer buf = new StringBuffer();
	buf.append("(");
	for (int i = 0; i < count; i++) {
	    FilterDef c = filters.get(i);
	    if (i > 0) {
		Connector innerConnector = getInnerConnector();
		if (innerConnector == null) {
		    innerConnector = Connector.AND;
		}
		buf.append(" " + innerConnector.name() + " ");
	    }
	    buf.append(c.toSqlString());
	}
	buf.append(")");
	return buf.toString();
    }

}

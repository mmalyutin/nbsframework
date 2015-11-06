package org.plazmaforge.framework.erm.query;

import org.plazmaforge.framework.core.criteria.Operation;
import org.plazmaforge.framework.core.sql.type.StringType;
import org.plazmaforge.framework.core.sql.type.Type;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.util.QueryUtils;

public class ValueFilterDef extends FilterDef {

    
    /**
     * Attribute of column
     */
    private Attribute attribute;
    
    /**
     * Name of column
     */
    private String columnName;
    
    /**
     * Table of column
     */
    private TableDef table;
    

    /**
     * Entity of Attribute
     */
    private Entity entity;

    
    // =, <>, >=, <=, IS_NULL, IS_NOT_NULL
    private String operation;
    
    private Type type;

    private Object value;
    
    private Object[] values;
    
    // Only for string value
    private boolean ignoreCase;
    
    private boolean parameter;
    
 
    public ValueFilterDef() {
	parameter = true;
    }
    
    ////
    public String getAttributeName() {
        return attribute == null ? null : attribute.getName();
    }

    public Type getAttributeType() {
        return attribute == null ? null : attribute.getType();
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    
    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public String getEntityIdentifier() {
        return entity == null ? null : entity.getIdentifier();
    }

    public TableDef getTable() {
        return table;
    }

    public void setTable(TableDef table) {
        this.table = table;
    }

    public String getTableName() {
        return table == null ? null :table.getTableName();
    }

    public String getTableAlias() {
	return table == null ? null :table.getTableAlias();
    }

    public String toPropertiesString() {
	return "attribute=" + getAttributeName()+ ", column=" + getColumnName()+ ", entity=" + getEntityIdentifier() + ", table=" + getTableName();
    }    
    ////

    public Type getType() {
        return type == null ? getAttributeType() : type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

 
    public boolean isParameter() {
        return parameter;
    }

    public void setParameter(boolean parameter) {
        this.parameter = parameter;
    }

    public int getParameterCount() {
	return isParameter() ? 1 : 0;
    }

    public String toString() {
	return "Filter: operation=" + operation + ", " + toPropertiesString(); 
    }

    public TypeValue getTypeValue() {
	TypeValue typeValue = new TypeValue();
	typeValue.setType(getType());
	typeValue.setValue(getValue());
	return typeValue;
    }
    
    public TypeValue[] getTypeValues() {
	return new TypeValue[] {getTypeValue()}; 
    }
    
    public String toSqlString() {
	String columnName = getColumnName();
	String tableAlias = getTableAlias();
	columnName = QueryUtils.getQueryColumnString(columnName, tableAlias);
	return toSqlString(columnName);
    }
    
    public String toSqlString(String columnName) {
	boolean isParameter = isParameter();
	String operation = getOperation();
	if (operation == null) {
	    operation = "=";
	}
	if (isParameter) {
	    if (isNotParameterOperation(operation)) {
		//TODO: ERROR
	    }
	}
	Type type = getType();
	if (Operation.ISNULL.equals(operation)) {
	    // is Null
	    return columnName + " IS NULL";
	} else if (Operation.ISNOTNULL.equals(operation)) {
	    // is Not Null
	    return columnName + " IS NOT NULL";
	} else if (Operation.IN.equals(operation)) {
	    Object[] values = getValues();
	    int count = values == null ? 0 : values.length;
	    if (count == 0) {
		throw new RuntimeException("Filter hasn't values, operator='" + Operation.IN + "'");
	    }
	    StringBuffer buf = new StringBuffer();
	    buf.append(columnName + " IN (");
	    if (isParameter) {
		for (int i = 0; i < count; i++) {
		    if (i > 0) {
			buf.append(", ");
		    }
		    buf.append(toSqlExpression(type, "?", ignoreCase)); // SQL
		}
	    } else {
		for (int i = 0; i < count; i++) {
		    if (i > 0) {
			buf.append(", ");
		    }
		    buf.append(toSqlValue(type, values[i], ignoreCase)); // SQL
		}
	    }
	    buf.append(")");
	    return buf.toString();
	} else {
	    // Other
	    if (isParameter) {
		return toSqlExpression(type, columnName, ignoreCase) + " " + operation + " " + toSqlExpression(type, "?", ignoreCase); // SQL
	    } else {
		Object value = getValue();
		if (value == null) {
		    if (Operation.EQ.equals(operation) || Operation.LIKE.equals(operation)) {
			// is Null
			return columnName + " IS NULL";
		    } else if (Operation.NE.equals(operation)) {
			// Is Not Null
			return columnName + " IS NOT NULL";
		    }
		    throw new RuntimeException("Can't convert NULL value, operator = '" + operation + "'");
		}
		return toSqlExpression(type, columnName, ignoreCase) + " " + operation + " " + toSqlValue(type, value, ignoreCase); // SQL
	    }
	}
    }
    
    private boolean isNotParameterOperation(String operation) {
	return (Operation.ISNULL.equals(operation) || Operation.ISNOTNULL.equals(operation));
    }

    private String toSqlExpression(Type type, String expression, boolean ignoreCase) {
	if (type == null || type instanceof StringType) {
	    return ignoreCase ? ("UPPER(" + expression + ")") : expression;
   	}
	return expression;
    }
    
    private String toSqlValue(Type type, Object value, boolean ignoreCase) {
   	// TODO
   	if (type == null || type instanceof StringType) {
   	    return ignoreCase ? ("UPPER('" + value + "')") : ("'" + value + "'");
   	}
   	if  (value == null)  {
   	    return "NULL";
   	}
   	return value.toString();
   }
    

}

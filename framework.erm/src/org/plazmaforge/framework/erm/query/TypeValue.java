package org.plazmaforge.framework.erm.query;

import org.plazmaforge.framework.core.sql.type.Type;

public class TypeValue {
    
    private Type type;
    
    private Object value;

    public TypeValue() {

    }

    public TypeValue(Type type, Object value) {
	super();
	this.type = type;
	this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    } 

    
    
}

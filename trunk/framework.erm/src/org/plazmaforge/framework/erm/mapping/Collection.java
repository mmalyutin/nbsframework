package org.plazmaforge.framework.erm.mapping;

import java.lang.reflect.Method;

import org.plazmaforge.framework.erm.Accessor;
import org.plazmaforge.framework.erm.ErrorHandler;
import org.plazmaforge.framework.util.ClassUtils;

public class Collection extends Association implements ICollection {

    private static final long serialVersionUID = -37591001757388558L;

    /**
     * Order by
     * SQL expression by default
     */
    private String orderBy;
    
    /**
     * Where clause
     * SQL expression by default
     */
    private String where;
    
    
    private String keyColumnName; // Only for map

    private String keyAttributeName; // Only for map


    private String valueColumnName; // Only for attribute-type='lookup'

    private String valueAttributeName; // Only for attribute-type='lookup'
    
    // Collection (Outer)
    public boolean isCollectionType() {
	return true;
    }
    
    
    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
    
    ////
    
    public String getKeyColumnName() {
        return keyColumnName;
    }


    public void setKeyColumnName(String keyColumnName) {
        this.keyColumnName = keyColumnName;
    }


    public String getKeyAttributeName() {
        return keyAttributeName;
    }


    public void setKeyAttributeName(String keyAttributeName) {
        this.keyAttributeName = keyAttributeName;
    }


    public String getValueColumnName() {
        return valueColumnName;
    }


    public void setValueColumnName(String valueColumnName) {
        this.valueColumnName = valueColumnName;
    }


    public String getValueAttributeName() {
        return valueAttributeName;
    }
    
    public void setValueAttributeName(String valueAttributeName) {
        this.valueAttributeName = valueAttributeName;
    }

    public String toString() {
	return "Collection Attribute: " + toPropertiesString(); 
    }

    protected void checkAccessor() {
    }
    
    protected void checkImplementerClass() {
	Class implementerClass = getImplementerClass();
	if (implementerClass == null) {
	    return;
	}
	if (!ClassUtils.isCollection(implementerClass)) {
	    ErrorHandler.handleEntityError(getEntity(), "Collection implementer class must extend 'java.util.Collection' or 'java.util.Map'");
	}
	Accessor accessor = getAccessor();
	if (accessor == null) {
	    return;
	}
	Method setter = accessor.getSetter();
	if (setter == null) {
	    return;
	}
	Class parameterType = setter.getParameterTypes()[0];
	if (parameterType == null) {
	    return;
	}
	
	if (!ClassUtils.isExtends(implementerClass, parameterType)) {
	    ErrorHandler.handleEntityError(getEntity(), "Collection implementer class must extend '" + parameterType.getName() + "'");
	}
    }
    
   
    
}

package org.plazmaforge.framework.erm;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Collection;
import org.plazmaforge.framework.erm.mapping.Entity;
import org.plazmaforge.framework.erm.mapping.EntityHelper;
import org.plazmaforge.framework.util.ClassUtils;

/**
 * Entity Accessor
 * 
 * @author ohapon
 *
 */
public class EntityAccessor {


    
  

    public Object getValue(Object obj, Attribute attribute) {
	if (obj == null) {
	    throw new RuntimeException("Object must be not null");
	}
	if (attribute == null) {
	    throw new RuntimeException("Attribute must be not null");
	}
	Accessor accessor = attribute.getAccessor();
	if (accessor == null) {
	    throw new RuntimeException("Accessor is null of attribute '" + attribute + "'");
	}
	Method getter = accessor.getGetter();
	if (getter == null) {
	    throw new RuntimeException("Get method is null of attribute '" + attribute + "'");
	}
	try {
	    return invoke(getter, obj);
	} catch (Exception ex) {
	    handleMethodError("get", ex, attribute);
	}
	return null;
    }

    public void setValue(Object obj, Attribute attribute, Object value) {
	if (obj == null) {
	    throw new RuntimeException("Object must be not null");
	}
	if (attribute == null) {
	    throw new RuntimeException("Attribute must be not null");
	}
	Accessor accessor = attribute.getAccessor();
	if (accessor == null) {
	    throw new RuntimeException("Accessor is null of attribute '" + attribute + "'");
	}
	Method setter = accessor.getSetter();
	if (setter == null) {
	    throw new RuntimeException("Set method is null of attribute '" + attribute + "'");
	}
	
	try {
	    if (value == null) {
		// SET DEFAULT VALUE FOR PRIMITIVE
		Class valueClass = accessor.getAttributeClass();
		if (valueClass != null && valueClass.isPrimitive() && isAutoConvertPrimitive()) {
		    value = ClassUtils.getDefaultPrimitiveValue(valueClass);
		}
	    }
	    invoke(setter, obj, value);
	} catch (Exception ex) {
	    handleMethodError("set", ex, attribute);
	}
    }

    /**
     * If true then we auto convert all null value to default value for primitive types 
     * @return
     */
    protected boolean isAutoConvertPrimitive() {
	return true;
    }
    
    

    ////
    
    public Object getPathValue(Entity entity, Object obj, String path) {
  	if (obj == null) {
  	    throw new RuntimeException("Object must be not null");
  	}
  	Attribute[] attributePath = EntityHelper.getAttributePath(entity, path);
  	if (attributePath == null || attributePath.length == 0) {
  	    throw new RuntimeException("Path not found: entity=" + entity.getIdentifier() + ", path=" + path);
  	}
  	Object o = obj;
  	for (Attribute attribute : attributePath) {
  	    o = getValue(o, attribute);
  	    if (o == null) {
  		return null;
  	    }
  	}
  	return o;
    }

    public void setPathValue(Entity entity, Object obj, String path, Object value) {
  	if (obj == null) {
  	    throw new RuntimeException("Object must be not null");
  	}
  	Attribute[] attributePath = EntityHelper.getAttributePath(entity, path);
  	if (attributePath == null || attributePath.length == 0) {
  	    throw new RuntimeException("Path not found: entity=" + entity.getIdentifier() + ", path=" + path);
  	}
  	Object o = obj;
  	Attribute valueAttribute = attributePath[attributePath.length - 1];
  	for (Attribute attribute : attributePath) {
  	    if (attribute == valueAttribute) {
  		setValue(o, attribute, value);
  	    } else {
  		o = getValue(o, attribute);
  		if (o == null) {
  		    throw new RuntimeException("Can't set value. Node attribute is null: entity=" + entity.getIdentifier() + ", path=" + path);
  	  	}
  	    }
  	}
    }

    ////    
   
    private void handleMethodError(String type, Exception ex, Attribute attribute) {
	Entity entity = attribute.getEntity();
	String className = entity == null ? null : entity.getClassName();
	String propertyName = attribute.getName();
	throw new RuntimeException("Error " + type + " value. " + ex + ": class='" + className + "', property='" + propertyName + "'", ex);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    public Object invoke(Method method, Object obj, Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	return method.invoke(obj, args);
    }
    
    
    public java.util.Collection<?> getCollectionValue(Object obj, Collection attribute) {
	Object value = getValue(obj, attribute);
	if (value == null) {
	    return null;
	}
	if (value instanceof java.util.Collection) {
	    return (java.util.Collection<?>) value;
	}
	if (value instanceof java.util.Map) {
	    return ((java.util.Map) value).entrySet(); // TODO
	}
	if (value.getClass().isArray()) {
	    return Arrays.asList(value);
	}
	throw new RuntimeException("Can't convert value '" + value.getClass() + "' to collection");

    }
    
    public void setCollectionValue(Object obj, Collection attribute, Object value) {
	if (obj == null) {
	    throw new RuntimeException("Object must be not null");
	}
	if (attribute == null) {
	    throw new RuntimeException("Attribute must be not null");
	}
	Accessor accessor = attribute.getAccessor();
	if (accessor == null) {
	    throw new RuntimeException("Accessor is null of attribute '" + attribute + "'");
	}
	Method setter = accessor.getSetter();
	if (setter == null) {
	    throw new RuntimeException("Set method is null of attribute '" + attribute + "'");
	}
	java.util.Collection inputValue = (java.util.Collection) value;  
	Class attributeClass = setter.getParameterTypes()[0]; // TODO: May be use accessor.getAttributeClass()
	Class implementerClass = attribute.getImplementerClass();
	
	value = createCollectionValue(attributeClass, implementerClass, inputValue);
	    
	try {
	    invoke(setter, obj, value);
	} catch (Exception ex) {
	    handleMethodError("set", ex, attribute);
	}
	
    }
    
    protected Object createCollectionValue(Class attributeClass, Class implementerClass, java.util.Collection inputValue) {
	if (implementerClass == null) {
	    return createDefaultCollectionValue(attributeClass, inputValue);
	}
	return createImplementerCollectionValue(implementerClass, inputValue);
    }

    protected Object createDefaultCollectionValue(Class attributeClass, java.util.Collection inputValue) {
	Object value = null;
	if (isExtends(attributeClass, java.util.List.class)) {
	    value = createDefaultList((java.util.Collection) inputValue);
	} else if (isExtends(attributeClass, java.util.Set.class)) {
	    value = createDefaultSet((java.util.Collection) inputValue);
	} else if (isExtends(attributeClass, java.util.Map.class)) {
	    throw new UnsupportedOperationException("Collection with 'java.util.Map' is not implemented");	    
	} else {
	    throw new RuntimeException("Can't convert collection to object value '" + attributeClass + "'");
	}
	return value;
    }
    
    protected Object createImplementerCollectionValue(Class implementerClass, java.util.Collection inputValue) {
	Object value = null;
	try {
	    value = implementerClass.newInstance();
	} catch (Exception ex) {
	    throw new RuntimeException("Can't create implementer object: '" + ex.toString()+ "'");
	}
	if (inputValue == null) {
	    return value;
	}
	if (value instanceof java.util.List) {
	    ((java.util.List) value).addAll(inputValue);
	} else if (value instanceof java.util.Set) {
	    ((java.util.Set) value).addAll(inputValue);
	} else if (value instanceof java.util.Map) {
	    throw new UnsupportedOperationException("Collection with 'java.util.Map' is not implemented");
	} else {
	    throw new RuntimeException("Implementer object must be 'java.util.Collection' or 'java.util.Map'");
	}
	return value;
    }

    
    protected java.util.List<?> createDefaultList(java.util.Collection<?> collection) {
	return collection == null ? new ArrayList() : new ArrayList(collection) ;
    }

    protected java.util.Set<?> createDefaultSet(java.util.Collection<?> collection) {
	return collection == null ? new HashSet() : new HashSet(collection) ;
    }

    protected boolean isExtends(Class<?> klass, Class<?> subclass) {
	return ClassUtils.isExtends(klass, subclass);
    }
}

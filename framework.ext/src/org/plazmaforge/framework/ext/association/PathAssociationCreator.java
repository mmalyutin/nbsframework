/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
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
package org.plazmaforge.framework.ext.association;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.plazmaforge.framework.config.ConfigUtils;
import org.plazmaforge.framework.config.object.IEntityConfig;
import org.plazmaforge.framework.ext.service.Service;
import org.plazmaforge.framework.util.StringUtils;



/**
 * The <code>Path Association Creator</code>.
 * Create elements by specific path.
 * 
 * @author ohapon
 *
 */
public class PathAssociationCreator extends AbstractAssociationCreator {

    /**
     * Standard path definition
     */
    private static final char PATH_SEPARATOR = '.';
    
    
    private static final String COMMON_PATH = "common";

    private static final String BEAN_PATH = COMMON_PATH + PATH_SEPARATOR + "bean";

    private static final String SERVICE_PATH = COMMON_PATH + PATH_SEPARATOR + "service";



    /**
     * Standard suffix definition
     */
    private static final String SERVICE_SUFFIX   = "Service";


    /**
     * The absolute application start path. 
     * Example: "com.mycompany.myproject"
     */
    private String startPath;
    
    
    /**
     * The relative bean path
     * Example: "common.bean"
     */
    private String beanPath;

    
    private Map<Class, TypeConfig> typeMap = new LinkedHashMap<Class, TypeConfig>(); // Insertion order is very important

    public PathAssociationCreator() {
        setBeanPath(BEAN_PATH);
        addType(Service.class, SERVICE_PATH, SERVICE_SUFFIX);
    }

    public void addType(Class type, String path, String suffix) {
        typeMap.put(type, new TypeConfig(path, suffix));
    }
    
    public Class getTypeByElementClass(Class klass) {
        Iterator<Class> itr = typeMap.keySet().iterator();
        while (itr.hasNext()) {
            Class type = itr.next();
            if (isAssignByClass(type, klass)) {
        	return type;
            }
            //if (type.isAssignableFrom(klass)) {
            //    return type; 
            //}
        }
        return null;
    }

    public Class getTypeByElementClassName(String className) {
	if (className == null) {
	    return null;
	}
        Iterator<Class> itr = typeMap.keySet().iterator();
        while (itr.hasNext()) {
            Class type = itr.next();
            if (isAssignByClassName(type, className)) {
        	return type;
            }
        }
        return null;
    }
    
    ////
    
    public boolean isAssignByClass(Class type, Class klass) {
	if (type == null || klass == null) {
	    return false;
	}
	return type.isAssignableFrom(klass);
    }
    
    public boolean isAssignByClassName(Class type, String className) {
	if (type == null || className == null) {
	    return false;
	}
	TypeConfig typeConfig = typeMap.get(type);
	if (typeConfig == null || typeConfig.suffix == null) {
	    return false;
	}
	if (className.endsWith(typeConfig.suffix)) {
	    return true;
	}
	return false;
    }
    
    ////

    public String getTypeSuffix(Class type, String defSuffix) {
	TypeConfig typeConfig = typeMap.get(type);
	if (typeConfig == null || typeConfig.suffix == null) {
	    return defSuffix;
	}
        return typeConfig.suffix;
    }
    
    public String getTypeSuffix(Class type) {
	return getTypeSuffix(type, null);
    }
    
    public String createElementClassName(String beanClassName, Class type) {
        TypeConfig typeConfig = (TypeConfig) typeMap.get(type);
        if (typeConfig == null) {
            return null;
        }
        return createElementClassName(beanClassName, typeConfig.path, typeConfig.suffix);
    }

    public String createElementName(String beanName, Class type) {
        TypeConfig typeConfig = (TypeConfig) typeMap.get(type);
        if (typeConfig == null) {
            return null;
        }
        return createElementName(beanName, typeConfig.suffix);
    }

    public String createElementName(String beanName, String typeSuffix) {
        return beanName + typeSuffix;
    }

    protected String createElementClassName(String beanClassName, String typePath, String typeSuffix) {
        int pos;
        pos = beanClassName.lastIndexOf(PATH_SEPARATOR);
        String beanName = beanClassName;
        String path = "";
        if (pos > 0) {
            beanName = beanClassName.substring(pos + 1);
            path = beanClassName.substring(0, pos);
        }
        
        if (!isEmpty(beanPath)) {
            pos = beanClassName.indexOf(beanPath);
            if (pos < 0) {
                throw new IllegalArgumentException("Class name is not corrected");
            }
            path = beanClassName.substring(0, pos);
        }

        
        String preffix = path + typePath + PATH_SEPARATOR;
        if (".".equals(preffix)) {
            preffix = "";
        }
        return  preffix + beanName + typeSuffix;
    }

    public String createElementClassName(String packageName, String elementName, Class type) {
        TypeConfig typeConfig = (TypeConfig) typeMap.get(type);
        if (typeConfig == null) {
            return null;
        }
        String res = getStartPath();
        
        if (!isEmpty(packageName) ) {
            if (!isEmpty(res)) {
                res += PATH_SEPARATOR;  
            }
            res += packageName;
        }
        
        if (!isEmpty(typeConfig.path) ) {
            if (!isEmpty(res)) {
                res += PATH_SEPARATOR;  
            }
            res += typeConfig.path;
        }
        if (!isEmpty(res)) {
            res += PATH_SEPARATOR;  
        }
        res += elementName;
        return res;
    }
    
    public String createBeanClassName(String elementClassName, Class type) {
        TypeConfig typeConfig = (TypeConfig) typeMap.get(type);
        if (typeConfig == null) {
            return null;
        }        
        return createBeanClassName(elementClassName, typeConfig.path, typeConfig.suffix);
    }
    
    protected String createBeanClassName(String elementClassName, String typePrefix, String typeSuffix) {
        int pos;
        pos = elementClassName.lastIndexOf(PATH_SEPARATOR);
        String elementName = elementClassName;
        String elementPackage = "";
        if (pos > 0) {
            elementName = elementClassName.substring(pos + 1);
            elementPackage = elementClassName.substring(0, pos);
        }
        pos = elementName.indexOf(typeSuffix);
        if (pos > 0) {
            elementName = elementName.substring(0, pos);
        }
        pos = elementPackage.indexOf(typePrefix);
        if (pos > 0) {
            elementPackage = elementPackage.substring(0, pos);
        }
        if (isEmpty(elementPackage)) {
            return beanPath + PATH_SEPARATOR + elementName;
        } else {
            String preffix = elementPackage + beanPath + PATH_SEPARATOR;
            if (".".equals(preffix)) {
                preffix = "";
            }
            return  preffix  + elementName;
        }
     }
    

    public String createBeanName(String elementName, Class type) {
        TypeConfig typeConfig = (TypeConfig) typeMap.get(type);
        if (typeConfig == null) {
            return null;
        }        
        return createBeanName(elementName, typeConfig.suffix);
    }

    protected String createBeanName(String elementName, String typeSuffix) {
        return cutSuffix(elementName, typeSuffix);
    }    

    protected String cutSuffix(String str, String suffix) {
	if (str == null) {
	    return null;
	}
        int pos = str.indexOf(suffix);
        if (pos > 0) {
            return str.substring(0, pos);
        }
        return str;
     }    


    public String createClassNameByEntity(IEntityConfig entity) {
        if (entity == null) {
            return null;
        }
        String entityPackage = entity.getEntityPackage();
        String entityClass = entity.getEntityClass();
        if (isEmpty(entityClass)) {
            entityClass = ConfigUtils.generateCodeById(entity.getConfigId());
        } else {
            entityClass = entityClass.trim(); 
        }
        if (isEmpty(entityPackage)) {
            return getStartPreffix() + beanPath + PATH_SEPARATOR + entityClass;
        }
	entityPackage = entityPackage.toLowerCase().trim();
        return getStartPreffix() + entityPackage + PATH_SEPARATOR + beanPath + PATH_SEPARATOR + entityClass;
    }
    
    protected String getStartPreffix() {
        if (isEmpty(startPath)) {
            return "";
        }
        return startPath + PATH_SEPARATOR;
    }

    public String getStartPath() {
        return startPath;
    }

    public void setStartPath(String startPath) {
        this.startPath = startPath;
    }
    

    public String getBeanPath() {
        return beanPath;
    }

    public void setBeanPath(String beanPath) {
        this.beanPath = beanPath;
    }
    
    protected boolean isEmpty(String value) {
        return StringUtils.isEmpty(value);
    }
    
    /**
     * The config of type
     *
     */
    private class TypeConfig {
        
        String path;
        
        String suffix;

        public TypeConfig() {
            super();
        }

        public TypeConfig(String path, String suffix) {
            this.path = path;
            this.suffix = suffix;
        }
    }


    
}

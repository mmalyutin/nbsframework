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

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.plazmaforge.framework.config.ConfigUtils;
import org.plazmaforge.framework.config.object.IEntityConfig;
import org.plazmaforge.framework.util.ClassUtils;
import org.plazmaforge.framework.util.StringUtils;



public abstract class AbstractAssociationCreator implements AssociationCreator {

    //protected static final Log logger = LogFactory.getLog(getClass().getName());

    public Association createAssociation(String id, String name) {
        Association association = new Association();
        association.setId(id);
        association.setName(name);
        return association;
    }

    ////
    
    public Association createAssociationByBeanClassName(String id, String beanClassName) {
        Association association = new Association();
        association.setId(id == null ? ConfigUtils.createIdByClassName(beanClassName) : id);
        association.setBeanClassName(beanClassName);
        association.addElementByClassName(beanClassName);
        return association;
    }
    
    public Association createAssociationByBeanClassName(String beanClassName) {
	return createAssociationByBeanClassName(null, beanClassName);
    }
    
    public Association createAssociationByElementClassName(String elementClassName) {
        String beanClassName = createBeanClassNameByElement(elementClassName);
        Association association = new Association();
        association.setId(ConfigUtils.createIdByClassName(beanClassName));
        association.setBeanClassName(beanClassName);
        association.addElementByClassName(elementClassName);
        return association;
    }    

    ////
    //
    // DISABLE DON'T REMOVE IT
    //
    ////
    
    /*
    public Association createAssociationByBeanClass(String id, Class beanClass) {
        Association association = new Association();
        association.setId(id == null ? ConfigUtils.createIdByClass(beanClass) : id);
        association.setBeanClassName(beanClass.getName());
        association.addElementByClass(beanClass);
        return association;
    }
    
    public Association createAssociationByBeanClass(Class beanClass) {
	return createAssociationByBeanClass(null, beanClass);
    }
    
    public Association createAssociationByElementClass(Class elementClass) {
        String beanClassName = createBeanClassNameByElement(elementClass);
        Association association = new Association();
        association.setId(ConfigUtils.createIdByClassName(beanClassName));
        association.setBeanClassName(beanClassName);
        association.addElementByClass(elementClass);
        return association;
    } 
    */   

    ////
    
    public Class createElement(Association association, Class type) {
        String className = createElementClassName(association.getBeanClassName(), type);
        if (className == null) {
            return null;
        }
        return createElement(className);

    }

    public Class createElement(String packageName, String elementName, Class type) {
        String className = createElementClassName(packageName, elementName, type);
        if (className == null) {
            return null;
        }
        return createElement(className);        
    }
    

    ////
    
    public String createElementClassName(Association association, Class type) {
        return createElementClassName(association.getBeanClassName(), type);
    }

    /**
     * Create bean class name by element class
     * @param elementClass
     * @return
     */
    public String createBeanClassNameByElement(Class elementClass) {
	Class type = getTypeByElementClass(elementClass);
        if (type == null) {
            return null;
        }
        return createBeanClassName(elementClass.getName(), type);
    }
    

    /**
     * Create bean class name by element class
     * @param elementClass
     * @return
     */
    public String createBeanClassNameByElement(String elementClassName) {
	Class type = getTypeByElementClassName(elementClassName);
        if (type == null) {
            return null;
        }
        return createBeanClassName(elementClassName, type);
    }

    

    protected Class createElement(String className) {
        try {
            return ClassUtils.getClass(className);
        } catch (Exception ex) {
            //TODO
            //logger.error("Create association element error", ex);
            System.err.println(ex);
        }
        return null;
    }

    public String createClassNameByEntity(IEntityConfig entity) {
        if (entity == null) {
            return null;
        }
        String entityPackage = entity.getEntityPackage();
        String entityClass = ConfigUtils.generateCodeById(entity.getId().trim());
        if (StringUtils.isEmpty(entityPackage)) {
            return entityClass;
        }
        return entityPackage + "." + entityClass; 
    }
    
    
    
}

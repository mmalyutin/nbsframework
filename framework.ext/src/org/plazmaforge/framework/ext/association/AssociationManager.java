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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.config.ConfigUtils;
import org.plazmaforge.framework.config.object.IEntityConfig;
import org.plazmaforge.framework.core.exception.ApplicationException;
import org.plazmaforge.framework.ext.config.EntityManager;


public class AssociationManager {

    private static AssociationManager instance;

    private Map<String, Association> associationMap = new HashMap<String, Association>();
    
    private List<Association> associationList = new ArrayList<Association>();
    
    private AssociationCreator associationCreator;


    private AssociationManager() {
        super();
    }


    public static AssociationManager getInstance() {
        if (instance == null) {
            instance = new AssociationManager();
        }
        return instance;
    }

    /**
     * Add association
     * @param association
     */
    public static void addAssociation(Association association) {
        getInstance().associationMap.put(association.getId(), association);
        getInstance().associationList.add(association);
    }
    
    /**
     * Remove association
     * @param association
     */
    public static void removeAssociation(Association association) {
        getInstance().associationMap.remove(association.getId());
    }

    /**
     * Remove association by id
     * @param id
     */
    public static void removeAssociation(String id) {
	Association association = getInstance().associationMap.remove(id);
        getInstance().associationMap.remove(id);
    }


    
    
    /**
     * Get association by Id
     * @param id
     * @return
     */
    public static Association getAssociation(String id) {
	String normalId = Association.getKeyString(id, true);
	Association association = association(normalId);
	if (association == null) {
	    IEntityConfig entity = EntityManager.getEntityById(normalId);
	    if (entity == null) {
		return null;
	    }
	    try {
		return createAssociationByEntity(entity);
	    } catch (ApplicationException ex) {
		return null;
	    }
	}
	return association;
    }

    private static Association doGetAssociation(String id) {
	return (Association) getInstance().associationMap.get(id);
    }

    private static Association association(String id, boolean isNormalize) {
	String normalId = isNormalize ? Association.getKeyString(id, true) : id;
	return doGetAssociation(normalId);
    }
    
  

    private static Association association(String id) {
	return association(id, false);
    }
	

    /**
     * Get association by element class
     * @param elementClass
     * @return
     */
    public static Association getAssociationByElementClass(Class elementClass) {
	
        // Find association by elements
        List<Association> associations = getAssociations();
        for (Association a: associations) {
            if (a.hasElement(elementClass)) {
        	return a;
            }
        }
        
        // Get bean class name
        String beanClassName = getInstance().associationCreator.createBeanClassNameByElement(elementClass);
        if (beanClassName != null) {
            for (Association a: associations) {
                if (beanClassName.equals(a.getBeanClassName())) {
                    a.addElementByClass(elementClass);
                    return a;
                }
            }
        }
        
        return getAssociationCreator().createAssociationByElementClassName(elementClass.getName());
    }


    /**
     * Get association by bean class
     * @param beanClass
     * @return
     */
    public static Association getAssociationByBeanClass(Class beanClass) {
        return getAssociation(ConfigUtils.createIdByClass(beanClass));
    }

    public static Association getAssociationByEntity(IEntityConfig entity) throws ApplicationException {
	if (entity == null) {
	    return null;
	}
	Association association = association(entity.getConfigId(), true);
	if (association == null) {
	    return createAssociationByEntity(entity);
	}
	return association;
    }

    public static Association createAssociationByEntity(IEntityConfig entity) throws ApplicationException {
	String className = getAssociationCreator().createClassNameByEntity(entity);
	String id = entity.getConfigId();
	addAssociationByBeanClass(id, className);
	return association(id, true);
    }


    /**
     * Get element by association id and type
     * 
     * @param id
     * @param type
     * @return
     */
    public static Class getElement(String id, Class type) {
        Association association = getAssociation(id);
        if (association == null) {
            return null;
        }
        return association.getElement(type);
    }
    
    public static Class getElement(IEntityConfig entity, Class type) {
	String id = entity == null ? null : entity.getConfigId();
        return getElement(id, type);
    }
    

    /**
     * Get element by bean class and type
     * @param beanClass
     * @param type
     * @return
     */
    public static Class getElementByBean(Class beanClass, Class type) {
        Association association = getAssociationByBeanClass(beanClass);
        if (association == null && getAssociationCreator() != null) {
            association = getAssociationCreator().createAssociationByBeanClassName(beanClass.getName());
        }
        if (association == null) {
            return null;
        }
        return association.getElement(type);
    }
    
    

    public static AssociationCreator getAssociationCreator() {
        return getInstance().associationCreator;
    }

    public static void setAssociationCreator(AssociationCreator creator) {
        getInstance().associationCreator = creator;
    }


    public static Association createAssociation(String id, String name) {
        if (getAssociationCreator() == null) {
            return null;
        }
        return getAssociationCreator().createAssociation(id, name);
    }

    //// BY CLASS ////
    
    public static Association createAssociationByBeanClass(String id, Class beanClass) {
        if (getAssociationCreator() == null) {
            return null;
        }
        return getAssociationCreator().createAssociationByBeanClassName(id, beanClass.getName());
    }

    public static Association createAssociationByBeanClass(Class beanClass) {
	return createAssociationByBeanClass(null, beanClass);
    }
    
    //// BY NAME ////

    public static Association createAssociationByBeanClassName(String id, String beanClassName) {
        if (getAssociationCreator() == null) {
            return null;
        }
        return getAssociationCreator().createAssociationByBeanClassName(id, beanClassName);
    }

    public static Association createAssociationByBeanClassName(String beanClassName) {
	return createAssociationByBeanClassName(null, beanClassName);
    }

    ////
    
    public static void addAssociation(String id, String name) {
        addAssociation(createAssociation(id, name));
    }

    public static void addAssociationByBeanClass(Class beanClass) {
        addAssociation(createAssociationByBeanClass(beanClass));
    }

    public static void addAssociationByBeanClass(String id, String className) throws ApplicationException {
        addAssociation(createAssociationByBeanClassName(id, className));
    }

    public static void addAssociationByBeanClass(String className) throws ApplicationException {
	addAssociationByBeanClass(null, className);
    }
    
    public static Class createElement(Association association, Class type) {
        if (getAssociationCreator() == null) {
            return null;
        }
        return getAssociationCreator().createElement(association, type);
    }
    
    public static String createElementClassName(Association association, Class type) {
        if (getAssociationCreator() == null) {
            return null;
        }
        return getAssociationCreator().createElementClassName(association, type);
    }
    

    public static List<Association> getAssociations() {
	return getInstance().associationList;
    }
 
    
    public static boolean isAssignByClass(Class type, Class klass) {
	return getAssociationCreator().isAssignByClass(type, klass);
    }
    
    public static boolean isAssignByClassName(Class type, String className) {
	return getAssociationCreator().isAssignByClassName(type, className);
    }
    
}

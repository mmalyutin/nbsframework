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

import org.plazmaforge.framework.config.object.IEntityConfig;

/**
 * The association creator
 * 
 * @author ohapon
 *
 */
public interface AssociationCreator {

    /**
     * Create new association by id and name
     * @param id
     * @param name
     * @return association
     */
    Association createAssociation(String id, String name);

    
    Association createAssociationByBeanClassName(String id, String beanClassName);
    
    Association createAssociationByBeanClassName(String beanClassName);

    Association createAssociationByElementClassName(String beanClassName);
    
    
    /**
     * Create new element (member) of association by association and element type
     * @param association
     * @param type
     * @return element
     */
    Class createElement(Association association, Class type);

    /**
     * Create new element class name (member) of association by association and element type
     * @param association
     * @param type
     * @return
     */
    String createElementClassName(Association association, Class type);

    
    /**
     * Create class name of element of association by <code>EntityConfig</code> of element
     * @param entity
     * @return class name
     */
    String createClassNameByEntity(IEntityConfig entity);
    
    /**
     * Create element by packageName, elementName and type
     */
    Class createElement(String packageName, String elementName, Class type);
    
    /**
     * Create bean class name by element class 
     * @param elementClass
     * @return
     */
    String createBeanClassNameByElement(Class elementClass);
    
    
    /**
     * Return Type (Superclass) of element class
     * @param klass
     * @return
     */
    Class getTypeByElementClass(Class klass);
    
    /**
     * Return Type (Superclass) of element class name
     * @param className
     * @return
     */
    Class getTypeByElementClassName(String className);
    
    
    String getTypeSuffix(Class type, String defSuffix);
    
    String getTypeSuffix(Class type);
    
    
    
    String createElementClassName(String beanClassName, Class type);
    
    String createElementName(String beanName, Class type);
    
    String createElementClassName(String packageName, String elementName, Class type);
    
    String createBeanClassName(String elementClassName, Class type);
    
    String createBeanName(String elementName, Class type);
    
    
    boolean isAssignByClass(Class type, Class klass);
    
    boolean isAssignByClassName(Class type, String className);

}

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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.plazmaforge.framework.ext.data.ClassType;
import org.plazmaforge.framework.util.StringUtils;


public class Association {
	
    /** 
     * Association ID. Upper case 
     */
    private String id;

    /**
     *  Association Name. 
     *  Default name is class name of bean
     */
    private String name;

    /**
     *  Association bean
     */
    private ClassType bean;

    /**
     *  Association elements
     */
    private Set<AssociationElement> elements = new HashSet<AssociationElement>();
    

    public Association() {
	bean = new ClassType();
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = getKeyString(id, true);
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getBeanClassName() {
	return bean.getName();
    }

    public void setBeanClassName(String beanClassName) {
	bean.setName(beanClassName);
    }

    public Class getBeanClass() {
	return bean.getType();
    }

    public void addElementByClass(Class elementClass) {
	elements.add(new AssociationElement(elementClass));
    }

    public void addElementByClassName(String elementClassName) {
	elements.add(new AssociationElement(elementClassName));
    }

    public void removeElementByClass(Class elementClass) {
	if (elementClass == null) {
	    return;
	}
	removeElementByClassName(elementClass.getName());
    }
    
    public void removeElementByClassName(String elementClassName) {
	if (elementClassName == null) {
	    return;
	}
	Iterator<AssociationElement> itr = elements.iterator();
	while (itr.hasNext()) {
	    AssociationElement e = itr.next();
	    if (elementClassName.equals(e.getElementClassName())) {
		itr.remove();
		break;
	    }
	}
    }
    

    /**
     * Return all elements
     * @return
     */
    public Set<AssociationElement> getElements() {
	return elements;
    }

    /**
     * Return element by super type
     * @param type
     * @return
     */
    public Class getElementByType(Class type) {
	return getElementByType(type, true);
    }
    
    /**
     * Return element by super type
     * @param type
     * @param isForceCreation
     * @return
     */
    public Class getElementByType(Class type, boolean isForceCreation) {
	if (type == null) {
	    return null;
	}
	Iterator<AssociationElement> itr = elements.iterator();
	while (itr.hasNext()) {
	    AssociationElement e = itr.next();
	    Class elementClass = e.getElementClass();
	    if (elementClass == null) {
		continue;
	    }
	    if (AssociationManager.isAssignByClass(type, elementClass)) {
		return elementClass;
	    }
	}

	if (!isForceCreation) {
	    return null;
	}
	
	Class element = AssociationManager.createElement(this, type);
	if (element != null) {
	    addElementByClass(element);
	}
	return element;
    }
	

    public String getElementClassNameByType(Class type) {
	return getElementClassNameByType(type, true);
    }
    
    public String getElementClassNameByType(Class type, boolean isForceCreation) {
	if (type == null) {
	    return null;
	}
	String elementClassName = null;
	Iterator<AssociationElement> itr = elements.iterator();
	while (itr.hasNext()) {
	    AssociationElement e = itr.next();
	    elementClassName = e.getElementClassName();
	    if (elementClassName == null) {
		continue;
	    }
	    if (AssociationManager.isAssignByClassName(type, elementClassName)) {
		return elementClassName;
	    }
	}

	if (!isForceCreation) {
	    return null;
	}
	
	elementClassName = AssociationManager.createElementClassName(this, type);
	if (elementClassName != null) {
	    addElementByClassName(elementClassName);
	}
	return elementClassName;
    }
    
    public Class getElement(Class klass) {
	if (klass == null) {
	    return null;
	}
	Iterator<AssociationElement> itr = elements.iterator();
	while (itr.hasNext()) {
	    AssociationElement e = itr.next();
	    Class elementClass = e.getElementClass();
	    if (klass.equals(elementClass)) {
		return elementClass;
	    }
	}
	return null;
    }

    public boolean hasElement(Class klass) {
	return hasClassName(klass == null ? null : klass.getName()); 
    }

    public boolean hasClassName(String className) {
	return findAssociationElementByClassName(className) != null;
    }
    
    private AssociationElement findAssociationElementByClassName(String className) {
	if (className == null) {
	    return null;
	}
	Iterator<AssociationElement> itr = elements.iterator();
	while (itr.hasNext()) {
	    AssociationElement e = itr.next();
	    String elementClassName = e.getElementClassName();
	    if (className.equals(elementClassName)) {
		return e;
	    }
	}
	return null;
    }
    
    public static String getKeyString(String str, boolean isCheck) {
	if (isCheck && StringUtils.isEmpty(str)) {
	    throw new IllegalArgumentException("String is empty");
	}
	if (str == null) {
	    return null;
	}
	// return str.replaceAll(" ", "").replaceAll("_", "").toUpperCase();
	return str.replaceAll(" ", "_").toUpperCase();
    }

}

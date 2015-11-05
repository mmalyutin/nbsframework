/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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

/**
 * 
 */
package org.plazmaforge.framework.core.data.path;

import java.io.Serializable;

/**
 * @author ohapon
 *
 */
public class TypeInfo implements Serializable {

    private static final long serialVersionUID = 4748830183541306185L;
    
    public static final String UNKNOWN = "[UNKNOWN]";
    
    
    
    private String type;
    
    /**
     * Biz package name
     */
    private String packageName;
    
    /**
     * Simple name
     */
    private String simpleName;
    
    /**
     * Part of simple name without type suffix
     */
    private String associationName;
    
    /**
     * Full class name
     */
    private String className;

    
    public TypeInfo() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }
    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

   

    public String toString() {
	return "TypeInfo [type=" + type + ", packageName=" + packageName + ", simpleName=" + simpleName +  ", associationName=" + associationName + ", className=" + className + "]";
    }
    
}

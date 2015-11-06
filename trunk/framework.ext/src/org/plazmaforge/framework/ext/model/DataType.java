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

package org.plazmaforge.framework.ext.model;


/**
 * Data Type definition
 * 
 */
public class DataType extends EntityObject<String> {

    private static final long serialVersionUID = 4226543802208168308L;
    

    public static String STRING_TYPE = "STRING";

    public static String INTEGER_TYPE = "INTEGER";

    public static String FLOAT_TYPE = "FLOAT";

    public static String DATE_TYPE = "DATE";

    public static String DATETIME_TYPE = "DATETIME";

    public static String BOOLEAN_TYPE = "BOOLEAN";

    public static String REFERENCE_TYPE = "REFERENCE"; // enumeration, lookup, dictionary and etc.

    private String code;

    private String name;

    private boolean enable;


    public String getKey() {
	String id = getId();
	return id == null ? null : id.trim();
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public boolean isEnable() {
	return enable;
    }

    public void setEnable(boolean enable) {
	this.enable = enable;
    }

    public String toString() {
	return getName();
    }
    
    protected boolean equalsId(Object id1, Object id2) {
	String st1 = (String) id1;
	String st2 = (String) id2;
	return st1.trim().equals(st2.trim());
    }
    
    /**
     * Return true if data type is reference (enumeration, lookup, dictionary and etc.)
     * @return
     */
    public boolean isReference() {
	return DataType.REFERENCE_TYPE.equals(getKey());
    }
}

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

package org.plazmaforge.framework.database.structure;

/** 
 * @author ohapon
 */

public class DataType implements IDataType {

    private String name;
    
    private int jdbcType;
    
    private String sqlType;
 
    private Class javaClass;
    

    private boolean supportSize;
	
    private boolean supportDecimal;
    
    
	
    public DataType() {
	super();
    }
    
    public DataType(String name) {
	super();
	this.name = name;
    }

    public DataType(String name, boolean supportSize, boolean supportDecimal) {
	super();
	this.name = name;
	this.supportSize = supportSize;
	this.supportDecimal = supportDecimal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(int jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public Class getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(Class javaClass) {
        this.javaClass = javaClass;
    }

    public boolean isSupportDecimal() {
        return supportDecimal;
    }

    public void setSupportDecimal(boolean supportDecimal) {
        this.supportDecimal = supportDecimal;
    }

    public boolean isSupportSize() {
        return supportSize;
    }

    public void setSupportSize(boolean supportSize) {
        this.supportSize = supportSize;
    }
    

    
    
}

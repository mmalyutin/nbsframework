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

public class DBColumn extends DBObject implements IDBColumn {

    private IDataType dataType = new DataType();
    
    private int size;
    
    private int decimalDigits;
    
    private boolean required;
    
    private String defaultValue;
    
    private boolean primaryKey;

    private boolean foreignKey;

    
    private boolean unique;
    
    private boolean autoIncrement;
    
    private String remarks;
    
    private String message;


    
    private Boolean supportSize; // TODO: STUB
    
    public IDataType getDataType() {
	if (dataType == null) {
	    dataType = new DataType();
	}
        return dataType;
    }

    public void setDataType(IDataType dataType) {
        this.dataType = dataType;
        supportSize = null;
    }

    protected DataType doGetDataType() {
	return (DataType) getDataType();
    }
    
    public String getType() {
	return getDataType().getName();
    }
    
    public void setType(String type) {
	doGetDataType().setName(type);
	supportSize = null;
    }
    
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }
    
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
    
    public void setNotNull(boolean notNull) {
        setRequired(notNull);
    }
    
    public boolean isNotNull() {
        return isRequired();
    }

    public void setNullable(boolean nullable) {
        setRequired(!nullable);
    }
    
    public boolean isNullable() {
        return !isRequired();
    }

    
    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
    
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    public boolean isForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(boolean foreignKey) {
        this.foreignKey = foreignKey;
    }

    public boolean isUnique() {
        return unique;
    }

    public void setUnique(boolean unique) {
        this.unique = unique;
    }
    
    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    public String getDisplayColumnSize() {
	if (decimalDigits == 0) {
	    return "" + size;
	}
	return "" +  + size + "," + decimalDigits;
    }

    protected boolean isEmptySize() {
	if (size == 0) {
	    return true;
	}
	if (dataType == null) {
	    return false;
	}
	
	return !isSupportSize();
    }
    
    protected boolean isSupportSize() {
	if (dataType == null) {
	    return false;
	}
	if (supportSize == null) {
	    supportSize = DBEnvironment.isSupportTypeSize(getType());
	}
	return supportSize;
    }
    
    public String getDisplayType() {
	return isEmptySize() ? getType() : ( getType() + "(" + getDisplayColumnSize() + ")" );
    }

    
}

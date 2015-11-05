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

/* 
 * Created on 10.09.2007
 *
 */

package org.plazmaforge.framework.core.datastorage;

import java.io.Serializable;
import java.text.Format;

import org.plazmaforge.framework.core.data.ComplexLocalizedIdentifier;
import org.plazmaforge.framework.core.data.HasDataType;


/** 
 * @author ohapon
 */

public class DSField extends ComplexLocalizedIdentifier implements HasDataType, Serializable, Cloneable {

    
    private static final long serialVersionUID = -3120489731428267368L;
    
    public static final String PROPERTY_FORMAT = "format";
    public static final String PROPERTY_ALIGN = "align";

    
    public static final int LEFT_ALIGN = 1;
    public static final int CENTER_ALIGN = 2;
    public static final int RIGHT_ALIGN = 3;


    /**
     *  Field data type
     */
    private String dataType;

    
    /**
     *  Native type (For example JDBC type code)
     */
    private int nativeTypeCode;
    
    /**
     * Native type (For example SQL column type)
     */
    private String nativeType;
    
    
    
    /**
     * Size of field
     */
    private int size;
    
    private int scale;
    
    private Format format;

    ////
    
    private boolean isFixedType;
    
    ////////////////////////////////////////////////////////////////////
    
    
    private int align;
    
    private int displaySize;
    
    private boolean primaryKey;
    
    private boolean foreignKey;
    

    public int getAlign() {
        return align;
    }

    public void setAlign(int align) {
        this.align = align;
    }

    public int getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(int displaySize) {
        this.displaySize = displaySize;
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
    
    public DSField clone() {
	DSField field = new DSField();
	field.setId(getId());
	field.setName(getName());
	field.setCaption(getCaption());
	field.setDescription(getDescription());
	field.setDataType(dataType);
	field.setNativeTypeCode(nativeTypeCode);
	field.setNativeType(nativeType);
	field.setScale(scale);
	field.setSize(size);
	field.setFormat(format);
	field.setAlign(align);
	field.setDisplaySize(displaySize);
	field.setPrimaryKey(primaryKey);
	field.setForeignKey(foreignKey);
	
	//TODO Clone properties
	return field;
    }

    ////////////////////////////////////////////////////////////////////////////////////
    
    public boolean isFixedType() {
	if (isFixedType) {
	    return true;
	}
	return getSize() > 0 && getScale() > 0;
	
//	if (getSize() > 0 && getScale() > 0) {
//	    return true;
//	}
//	return JDBCEnvironment.isFixedType(getJdbcType());
	
    }

    ////////////////////////////////////////////////////////////////////////////////////
    
    
    public int getNativeTypeCode() {
        return nativeTypeCode;
    }


    public void setNativeTypeCode(int nativeTypeCode) {
        this.nativeTypeCode = nativeTypeCode;
    }

    public String getNativeType() {
        return nativeType;
    }

    public void setNativeType(String nativeType) {
        this.nativeType = nativeType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    
    
}

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
     * Field path in native data source
     */
    private String path;

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
    
    private String format;

    ////
    
    private boolean isFixedType;
    
    ////////////////////////////////////////////////////////////////////
    
    
    private int align;
    
    private int displaySize;
    
    private boolean primaryKey;
    
    private boolean foreignKey;
    

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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
	field.setPath(path);
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + align;
	result = prime * result
		+ ((dataType == null) ? 0 : dataType.hashCode());
	result = prime * result + displaySize;
	result = prime * result + (foreignKey ? 1231 : 1237);
	result = prime * result + ((format == null) ? 0 : format.hashCode());
	result = prime * result + (isFixedType ? 1231 : 1237);
	result = prime * result
		+ ((nativeType == null) ? 0 : nativeType.hashCode());
	result = prime * result + nativeTypeCode;
	result = prime * result + ((path == null) ? 0 : path.hashCode());
	result = prime * result + (primaryKey ? 1231 : 1237);
	result = prime * result + scale;
	result = prime * result + size;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	DSField other = (DSField) obj;
	if (align != other.align)
	    return false;
	if (dataType == null) {
	    if (other.dataType != null)
		return false;
	} else if (!dataType.equals(other.dataType))
	    return false;
	if (displaySize != other.displaySize)
	    return false;
	if (foreignKey != other.foreignKey)
	    return false;
	if (format == null) {
	    if (other.format != null)
		return false;
	} else if (!format.equals(other.format))
	    return false;
	if (isFixedType != other.isFixedType)
	    return false;
	if (nativeType == null) {
	    if (other.nativeType != null)
		return false;
	} else if (!nativeType.equals(other.nativeType))
	    return false;
	if (nativeTypeCode != other.nativeTypeCode)
	    return false;
	if (path == null) {
	    if (other.path != null)
		return false;
	} else if (!path.equals(other.path))
	    return false;
	if (primaryKey != other.primaryKey)
	    return false;
	if (scale != other.scale)
	    return false;
	if (size != other.size)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "DSField[name=" + getName()
		+ ", path=" + path 
		+ ", dataType=" + dataType
		+ ", nativeTypeCode=" + nativeTypeCode 
		+ ", nativeType=" + nativeType 
		+ ", size=" + size 
		+ ", scale=" + scale
		+ ", format=" + format 
		+ ", isFixedType=" + isFixedType()
		+ ", align=" + align 
		+ ", displaySize=" + displaySize
		+ ", primaryKey=" + primaryKey 
		+ ", foreignKey=" + foreignKey
		+ "]";
    }


    
    
}

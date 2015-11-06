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

import java.util.Date;

import org.plazmaforge.framework.config.object.IEntityConfig;


/** 
 * @author ohapon
 */

public class AttributeValue extends BaseEntity implements IValueHolder {

    private static final long serialVersionUID = 2734495494795009074L;

    private IValueHolder valueHolder = new ValueHolder();

    private Attribute attribute;
    
    private IEntityConfig entity;
    
    private Integer ownerId;

    
    
    public IEntityConfig getEntity() {
        return entity;
    }

    public void setEntity(IEntityConfig entity) {
        this.entity = entity;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
        getValueHolder().setDataType(attribute == null ? null : attribute.getDataType());
        getValueHolder().setValueEntity(attribute == null ? null : attribute.getValueEntity());
    }

    
    public String getCode() {
	if (attribute == null) {
	    return null;
	}
	return attribute.getCode();
    }
    
    public void setCode(String code) {
	getAttribute().setCode(code);
    }
    
    public String getName() {
	if (attribute == null) {
	    return null;
	}
	return attribute.getName();
    }

    public void setName(String name) {
	getAttribute().setName(name);
    }

    
    public IValueHolder getValueHolder() {
        return valueHolder;
    }

    public String getStringValue() {
	return getValueHolder().getStringValue();
    }

    public void setStringValue(String stringValue) {
	getValueHolder().setStringValue(stringValue);
    }

    public Integer getIntegerValue() {
	return getValueHolder().getIntegerValue();
    }

    public void setIntegerValue(Integer integerValue) {
	getValueHolder().setIntegerValue(integerValue);
    }

    public Float getFloatValue() {
	return getValueHolder().getFloatValue();
    }

    public void setFloatValue(Float floatValue) {
	getValueHolder().setFloatValue(floatValue);
    }

    public Date getDateValue() {
	return getValueHolder().getDateValue();
    }

    public void setDateValue(Date dateValue) {
	getValueHolder().setDateValue(dateValue);
    }

    public Date getDateTimeValue() {
	return getValueHolder().getDateTimeValue();
    }

    public void setDateTimeValue(Date dateTimeValue) {
	getValueHolder().setDateTimeValue(dateTimeValue);
    }

    public Boolean getBooleanValue() {
	return getValueHolder().getBooleanValue();
    }

    public void setBooleanValue(Boolean booleanValue) {
	getValueHolder().setBooleanValue(booleanValue);
    }

    
    public IEntityConfig getValueEntity() {
	return getValueHolder().getValueEntity();
    }

    public void setValueEntity(IEntityConfig valueEntity) {
	setValueEntity(valueEntity, false);
    }
    
    public void setValueEntity(IEntityConfig valuEntity, boolean isUpdateType) {
	getValueHolder().setValueEntity(valuEntity);
	if (isUpdateType && getAttribute() != null) {
	    getAttribute().setValueEntity(valuEntity);
	}
    }
    

    public DataType getDataType() {
	return getValueHolder().getDataType();
    }

    public void setDataType(DataType dataType) {
	setDataType(dataType, false);
    }
    
    public void setDataType(DataType dataType, boolean isUpdateType) {
	getValueHolder().setDataType(dataType);
	if (isUpdateType && getAttribute() != null) {
	    getAttribute().setDataType(dataType);
	}
    }

    public void clearValues() {
	getValueHolder().clearValues();
    }

    public Object getValue() {
	return getValueHolder().getValue();
    }

    public void setValue(Object value) {
	getValueHolder().setValue(value);
    }

    public void setExpression(String exp) {
	getValueHolder().setExpression(exp);
    }

    public String getExpression() {
	return getValueHolder().getExpression();
    }

    public boolean isReference() {
	return getValueHolder().isReference();
    }

    public Object getDisplayValue() {
	return getValueHolder().getDisplayValue();
    }
    
    public String getStringDisplayValue() {
	return getValueHolder().getStringDisplayValue();
    }
    
  
}

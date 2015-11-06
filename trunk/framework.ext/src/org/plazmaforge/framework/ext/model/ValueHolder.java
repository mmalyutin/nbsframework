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

//import java.text.SimpleDateFormat;
import java.util.Date;

import org.plazmaforge.framework.config.object.IEntityConfig;
//import org.plazmaforge.framework.core.SystemEnvironment;


/** 
 * @author ohapon
 */

//TODO: Must use Own DataParser: JODA

public class ValueHolder implements IValueHolder {

    private static final long serialVersionUID = 761023404791209875L;
    

    private DataType dataType;

    private String stringValue;

    private Integer integerValue;

    private Float floatValue;

    private Date dateValue;

    // private Date dateTimeValue;

    // private Boolean booleanValue;

    
    
    private IEntityConfig valueEntity;
    
    
    
    public String getStringValue() {
	return stringValue;
    }

    public void setStringValue(String stringValue) {
	this.stringValue = stringValue;
    }

    public Integer getIntegerValue() {
	return integerValue;
    }

    public void setIntegerValue(Integer integerValue) {
	this.integerValue = integerValue;
    }

    public Float getFloatValue() {
	return floatValue;
    }

    public void setFloatValue(Float floatValue) {
	this.floatValue = floatValue;
    }

    public Date getDateValue() {
	return dateValue;
    }

    public void setDateValue(Date dateValue) {
	this.dateValue = dateValue;
    }

    public Date getDateTimeValue() {
	// return dateTimeValue;
	return getDateValue();
    }

    public void setDateTimeValue(Date dateTimeValue) {
	// this.dateTimeValue = dateTimeValue;
	setDateValue(dateTimeValue);
    }

    public Boolean getBooleanValue() {
	// return booleanValue;
	Integer integerValue = getIntegerValue();
	if (integerValue == null) {
	    return new Boolean(false);
	}
	return integerValue.intValue() == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    public void setBooleanValue(Boolean booleanValue) {
	// this.booleanValue = booleanValue;
	if (booleanValue == null) {
	    setIntegerValue(new Integer(0));
	    return;
	}
	if (booleanValue.booleanValue()) {
	    setIntegerValue(new Integer(1));
	} else {
	    setIntegerValue(new Integer(0));
	}
    }

    public IEntityConfig getValueEntity() {
	return valueEntity;
    }

    public void setValueEntity(IEntityConfig dictionaryEntity) {
	this.valueEntity = dictionaryEntity;
    }

    public DataType getDataType() {
	if (dataType == null) {
	    dataType = new DataType();
	    dataType.setId(DataType.STRING_TYPE);
	}
	return dataType;
    }

    public void setDataType(DataType dataType) {
	this.dataType = dataType;
    }

    public void clearValues() {
	stringValue = null;
	integerValue = null;
	floatValue = null;
	dateValue = null;
	// dateTimeValue = null;
	// booleanValue = null;
    }

    public Object getValue() {
	if (getDataType() == null) {
	    return null;
	}
	String type = getDataType().getKey();
	if (DataType.STRING_TYPE.equals(type)) {
	    return this.getStringValue();
	} else if (DataType.INTEGER_TYPE.equals(type)) {
	    return this.getIntegerValue();
	} else if (DataType.FLOAT_TYPE.equals(type)) {
	    return this.getFloatValue();
	} else if (DataType.DATE_TYPE.equals(type)) {
	    return this.getDateValue();
	} else if (DataType.DATETIME_TYPE.equals(type)) {
	    return this.getDateTimeValue();
	} else if (DataType.BOOLEAN_TYPE.equals(type)) {
	    return this.getBooleanValue();
	} else if (DataType.REFERENCE_TYPE.equals(type)) {
	    return this.getIntegerValue();
	} else {
	    return null;
	}
    }

    public void setValue(Object value) {
	try {
	    this.clearValues();
	    if (value == null || getDataType() == null) {
		return;
	    }
	    String type = getDataType().getKey();
	    if (DataType.STRING_TYPE.equals(type)) {
		setStringValue((String) value);
	    } else if (DataType.INTEGER_TYPE.equals(type)) {
		setIntegerValue((Integer) value);
	    } else if (DataType.FLOAT_TYPE.equals(type)) {
		setFloatValue((Float) value);
	    } else if (DataType.DATE_TYPE.equals(type)) {
		setDateValue((Date) value);
	    } else if (DataType.DATETIME_TYPE.equals(type)) {
		setDateTimeValue((Date) value);
	    } else if (DataType.BOOLEAN_TYPE.equals(type)) {
		setBooleanValue((Boolean) value);
	    } else if (DataType.REFERENCE_TYPE.equals(type)) {
		if (!(value instanceof IBaseEntity)) {
		    return;
		}
		IBaseEntity b = (IBaseEntity) value;
		setIntegerValue(b.getId());
		if (value instanceof IStringPresentation) {
		    IStringPresentation presentation = (IStringPresentation) value;
		    String name = presentation.getStringPresentation();
		    setStringValue(name);
		}
	    } else {
		// Default
		setStringValue((String) value);
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public void setExpression(String exp) {
	try {
	    this.clearValues();
	    if (getDataType() == null) {
		return;
	    }
	    String type = getDataType().getKey();
	    if (DataType.STRING_TYPE.equals(type)) {
		setStringValue(exp);
	    } else if (DataType.INTEGER_TYPE.equals(type)) {
		setIntegerValue(new Integer(exp));
	    } else if (DataType.FLOAT_TYPE.equals(type)) {
		setFloatValue(new Float(exp));
	    } else if (DataType.DATE_TYPE.equals(type)) {
		//TODO
		//setDateValue((new SimpleDateFormat()).parse(exp));
	    } else if (DataType.DATETIME_TYPE.equals(type)) {
		//TODO
		//setDateValue((new SimpleDateFormat()).parse(exp));
	    } else if (DataType.BOOLEAN_TYPE.equals(type)) {
		setBooleanValue(new Boolean(exp));
	    } else if (DataType.REFERENCE_TYPE.equals(type)) {
		setIntegerValue(new Integer(exp));
	    } else {
		// Default
		setStringValue(exp);
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public String getExpression() {
	Object val = getValue();
	if (val == null) {
	    return null;
	}
	return val.toString();
    }

    public boolean isReference() {
	DataType dataType = getDataType();
	return dataType == null ? false : dataType.isReference();
    }

    public Object getDisplayValue() {
	Object value = getValue();
	if (value instanceof Date) {
	    //TODO
	    return value == null ? null : value.toString();
	    //return SystemEnvironment.getDateFormat().format((Date) value);
	}
	if (isReference()) {
	    return this.getStringValue();
	}
	if (value instanceof Boolean) {
	    //TODO
	    return value == null ? null : ((Boolean) value ? "Yes" : "No");
	    //return value == null ? null : SystemEnvironment.getYesNoString(((Boolean) value).booleanValue());
	}
	return value;
    }
    
    public String getStringDisplayValue() {
	Object value = getDisplayValue();
	if (value == null) {
	    return null;
	}
	return value.toString();
    }
    
}

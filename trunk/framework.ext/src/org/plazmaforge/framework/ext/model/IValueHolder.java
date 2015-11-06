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

import java.io.Serializable;
import java.util.Date;

import org.plazmaforge.framework.config.object.IEntityConfig;


/** 
 * @author ohapon
 */

public interface IValueHolder extends Serializable {

    
    String getStringValue();

    void setStringValue(String stringValue);

    Integer getIntegerValue();

    void setIntegerValue(Integer integerValue);

    Float getFloatValue();

    void setFloatValue(Float floatValue);

    Date getDateValue();
    
    void setDateValue(Date dateValue);

    Date getDateTimeValue();

    void setDateTimeValue(Date dateTimeValue);

    Boolean getBooleanValue();

    void setBooleanValue(Boolean booleanValue);

    IEntityConfig getValueEntity();

    void setValueEntity(IEntityConfig valueEntity);

    DataType getDataType();

    void setDataType(DataType dataType);

    void clearValues();

    Object getValue();
    
    void setValue(Object value);
    
    void setExpression(String exp);
    
    String getExpression();

    boolean isReference();

    Object getDisplayValue();
    
    String getStringDisplayValue();

}

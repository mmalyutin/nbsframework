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

public interface IDBColumn extends IDBObject {

    
    IDataType getDataType();

    //void setDataType(IDataType dataType);

    String getType();
    
    //void setType(String type);
    
    int getSize();

    //void setSize(int size);
    
    int getDecimalDigits();

    //void setDecimalDigits(int decimalDigits);
    
    
    
    boolean isRequired();

    //void setRequired(boolean required);
    
    // notNull == required
    //void setNotNull(boolean notNull);
    
    boolean isNotNull();
    

    // nullable == !required
    //void setNullable(boolean nullable);
    
    boolean isNullable();
    
    
    
    
    String getDefaultValue();

    //void setDefaultValue(String defaultValue);
    
    
    boolean isPrimaryKey();

    //void setPrimaryKey(boolean primaryKey);
    
    
    boolean isForeignKey();

    //void setForeignKey(boolean foreignKey);
    
    
    boolean isUnique();

    //void setUnique(boolean unique);
    
    boolean isAutoIncrement();

    //void setAutoIncrement(boolean autoIncrement);

    String getRemarks();

    //void setRemarks(String remarks);
    
    String getMessage();

    //void setMessage(String message);

    String getDisplayColumnSize();
    
    String getDisplayType();
}

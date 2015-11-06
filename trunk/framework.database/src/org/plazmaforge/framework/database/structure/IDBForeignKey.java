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

import java.util.List;

/** 
 * @author ohapon
 */

public interface IDBForeignKey extends IDBSchemaObject {

    
    String getPkTableName();

    //void setPkTableName(String pkTableName);
    
    String getPkName();

    //void setPkName(String pkName);
    
    
    
    String getFkTableName();

    //void setFkTableName(String fkTableName);
    
    String getFkName();

    //void setFkName(String fkName);

    
    
    List<String> getPkColumnNames();

    //void setPkColumnNames(List<String> pkColumnNames);

    //void addPkColumn(String pkColumnName);
    
    
    
    List<String> getFkColumnNames();
    
    //void setFkColumnNames(List<String> fkColumnNames);

    //void addFkColumn(String pkColumnName);
    
    

    short getUpdateRule();

    //void setUpdateRule(short updateRule);
    
    short getDeleteRule();

    //void setDeleteRule(short deleteRule);

    short getDeferrability();

    //void setDeferrability(short deferrability);
    
    
    
    String getRemarks();

    //void setRemarks(String remarks);
    
    
    
    //boolean isPkColumn(String columnName);
  
    //boolean isFkColumn(String columnName);


}

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

public interface IDBTable extends IDBSchemaObject {

    public static final String TABLE = "TABLE";
    
    public static final String VIEW = "VIEW";
    
    
    String getTableType();

    
    //void setTableType(String tableType);
    
    // Vendor special table type
    String getSpecialTableType();

    //void setSpecialTableType(String specialTableType);
    
    //void addColumn(IDBColumn column);

    //void removeColumn(IDBColumn column);
    
    //void removeAllColumns();
    

    List<IDBColumn> getColumns();

    //void setColumns(List<IDBColumn> columns);
    
    int getColumnCount();
    
    ////
    
    IDBPrimaryKey getPrimaryKey();

    //void setPrimaryKey(IDBPrimaryKey primaryKey);

    
    ///
    List<IDBForeignKey> getImportKeys();

    //void setImportKeys(List<IDBForeignKey> importKeys);

    //void addImportKey(IDBForeignKey importKey);
    
    int getImportKeyCount();
    
    List<IDBForeignKey> getExportKeys();

    //void setExportKeys(List<IDBForeignKey> exportKeys);

    //void addExportKey(IDBForeignKey exportKey);

    int getExportKeyCount();
    
    
    String getRemarks();

    //void setRemarks(String remarks);
}

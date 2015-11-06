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

import java.util.ArrayList;
import java.util.List;

/** 
 * @author ohapon
 */

public class DBTable extends DBSchemaObject implements IDBTable {

    private String tableType;
    
    // Vendor special table type
    private String specialTableType;
    
    private List<IDBColumn> columns = new ArrayList<IDBColumn>();
    
    private IDBPrimaryKey primaryKey;
    
    private List<IDBForeignKey> importKeys = new ArrayList<IDBForeignKey>();
    
    private List<IDBForeignKey> exportKeys = new ArrayList<IDBForeignKey>();
    
    private String remarks;
    
    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }
    
    
    public String getSpecialTableType() {
        return specialTableType;
    }

    public void setSpecialTableType(String specialTableType) {
        this.specialTableType = specialTableType;
    }


    public void addColumn(IDBColumn column) {
	getColumns().add(column);
    }

    public void removeColumn(IDBColumn column) {
	getColumns().remove(column);
    }

    public void removeAllColumns() {
	if (columns == null) {
	    return;
	}
	columns.clear();
    }
    
    
    public List<IDBColumn> getColumns() {
	if (columns == null) {
	    columns = new ArrayList<IDBColumn>();
	}
        return columns;
    }

    public void setColumns(List<IDBColumn> columns) {
        this.columns = columns;
    }
    
    public int getColumnCount() {
	return columns == null ? 0 : columns.size();
    }
    
    
    public IDBPrimaryKey getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(IDBPrimaryKey primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<IDBForeignKey> getImportKeys() {
	if (importKeys == null) {
	    importKeys = new ArrayList<IDBForeignKey>();
	}
        return importKeys;
    }

    public void setImportKeys(List<IDBForeignKey> importKeys) {
        this.importKeys = importKeys;
    }    
    
    public void setImportKeys(IDBForeignKey[] importKeys) {
	if (importKeys == null) {
	    this.importKeys = null;
	    return;
	}
	this.importKeys = new ArrayList<IDBForeignKey>();
	for (int i = 0; i < importKeys.length; i++) {
	    this.importKeys.add(importKeys[i]);
	}
    }
    

    public void addImportKey(IDBForeignKey importKey) {
	getImportKeys().add(importKey);
    }
    
    public int getImportKeyCount() {
	return importKeys == null ? 0 : importKeys.size(); 
    }
    
    public List<IDBForeignKey> getExportKeys() {
	if (exportKeys == null) {
	    exportKeys = new ArrayList<IDBForeignKey>();
	}
        return exportKeys;
    }

    public void setExportKeys(List<IDBForeignKey> exportKeys) {
        this.exportKeys = exportKeys;
    }
    
    public void setExportKeys(IDBForeignKey[] exportKeys) {
	if (exportKeys == null) {
	    this.exportKeys = null;
	    return;
	}
	this.exportKeys = new ArrayList<IDBForeignKey>();
	for (int i = 0; i < exportKeys.length; i++) {
	    this.exportKeys.add(exportKeys[i]);
	}
    }

    public void addExportKey(IDBForeignKey exportKey) {
	getExportKeys().add(exportKey);
    }

    public int getExportKeyCount() {
	return exportKeys == null ? 0 : exportKeys.size(); 
    }

 

    ///
    
    public void markPrimaryKeyColumns() {
	if (getColumnCount() == 0) {
	    return;
	}
	List<String> keyColumns = new ArrayList<String>();
	populatePrimaryKeyColumns(keyColumns);
	for (IDBColumn c : getColumns()) {
	    DBColumn column = (DBColumn) c; 
	    boolean isPK = keyColumns.contains(column.getName());
	    column.setPrimaryKey(isPK);
	    if (!isPK) {
		continue;
	    }
	    column.setNotNull(true); // FIXED: 2009-01-02
	    column.setUnique(true); // FIXED: 2009-01-02
	}
    }
    
    public void markForeignKeyColumns() {
	if (getColumnCount() == 0) {
	    return;
	}
	List<String> keyColumns = new ArrayList<String>();
	populateForeignKeyColumns(keyColumns);
	for (IDBColumn c : getColumns()) {
	    DBColumn column = (DBColumn) c;
	    column.setForeignKey(keyColumns.contains(column.getName()));
	}
    }
    
    protected void populatePrimaryKeyColumns(List<String> keyColumns) {
	if (primaryKey == null || primaryKey.getPkColumnNames() == null || primaryKey.getPkColumnNames().size() == 0) {
	    return;
	}
	int count = primaryKey.getPkColumnNames().size();
	for (int i = 0; i < count; i++) {
	    keyColumns.add(primaryKey.getPkColumnNames().get(i));
	}
    }
    
    protected void populateForeignKeyColumns(List<String> keyColumns) {
	if (importKeys == null || importKeys.size() == 0) {
	    return;
	}
	for (IDBForeignKey key : importKeys) {
	    if (key.getFkColumnNames() == null || key.getFkColumnNames().size() == 0) {
		continue;
	    }
	    for (String name : key.getFkColumnNames()) {
		keyColumns.add(name);
	    }
	}
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    
}

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

public class DBForeignKey extends DBSchemaObject implements IDBForeignKey {

    private String pkTableName;
    private String pkName;
    
    private String fkTableName;
    private String fkName;
    
    private List<String> pkColumnNames;
    private List<String> fkColumnNames;
    
    private short updateRule;
    private short deleteRule;
    private short deferrability;
    
    private String remarks;
    

    public String getPkTableName() {
        return pkTableName;
    }

    public void setPkTableName(String pkTableName) {
        this.pkTableName = pkTableName;
    }
    
    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }
    
    public String getFkTableName() {
        return fkTableName;
    }

    public void setFkTableName(String fkTableName) {
        this.fkTableName = fkTableName;
    }
    
    public String getFkName() {
        return fkName;
    }

    public void setFkName(String fkName) {
        this.fkName = fkName;
    }
    
    public List<String> getPkColumnNames() {
	if (pkColumnNames == null) {
	    pkColumnNames = new ArrayList<String>();
	}
        return pkColumnNames;
    }

    public void setPkColumnNames(List<String> pkColumnNames) {
        this.pkColumnNames = pkColumnNames;
    }
    
    public void addPkColumn(String pkColumnName) {
	getPkColumnNames().add(pkColumnName);
    }    
    

    public List<String> getFkColumnNames() {
	if (fkColumnNames == null) {
	    fkColumnNames = new ArrayList<String>();
	}
        return fkColumnNames;
    }

    public void setFkColumnNames(List<String> fkColumnNames) {
        this.fkColumnNames = fkColumnNames;
    }

    public void addFkColumn(String fkColumnName) {
	getFkColumnNames().add(fkColumnName);
    }    

    public short getUpdateRule() {
        return updateRule;
    }

    public void setUpdateRule(short updateRule) {
        this.updateRule = updateRule;
    }
    
    public short getDeleteRule() {
        return deleteRule;
    }

    public void setDeleteRule(short deleteRule) {
        this.deleteRule = deleteRule;
    }

    public short getDeferrability() {
        return deferrability;
    }

    public void setDeferrability(short deferrability) {
        this.deferrability = deferrability;
    }
    

    public boolean isPkColumn(String columnName) {
	return isColumnInList(columnName, pkColumnNames);
    }

  
    public boolean isFkColumn(String columnName) {
	return isColumnInList(columnName, fkColumnNames);
    }


    protected boolean isColumnInList(String columnName, List<String> columns) {
	if (columnName == null || columns == null) {
	    return false;
	}
	for (String fkColumn : columns) {
	    if (columnName.equalsIgnoreCase(fkColumn)) {
		return true;
	    }
	}
	return false;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    

}

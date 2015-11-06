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

public class DBPrimaryKey extends DBSchemaObject implements IDBPrimaryKey {
    
    private String tableName;
    
    private String pkName;
    
    private List<String> pkColumnNames;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
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

    public boolean isPkColumn(String columnName) {
	if (columnName == null || pkColumnNames == null) {
	    return false;
	}
	for (String pkColumn : pkColumnNames) {
	    if (columnName.equalsIgnoreCase(pkColumn)) {
		return true;
	    }
	}
	return false;
    }
}

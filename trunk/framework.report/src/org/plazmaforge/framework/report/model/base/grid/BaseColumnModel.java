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

/**
 * 
 */
package org.plazmaforge.framework.report.model.base.grid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ohapon
 *
 */
public class BaseColumnModel implements ColumnModel {

    private List<Column> columns;

    @Override
    public List<Column> getColumns() {
	if (columns == null) {
	    columns = new ArrayList<Column>();
	}
        return columns;
    }

    @Override
    public Column getColumn(int index) {
	return getColumns().get(index);
    }
    
    @Override
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    @Override
    public void addColumn(Column column) {
	getColumns().add(column);
	
    }

    @Override
    public void removeColumn(Column column) {
	getColumns().remove(column);
	
    }

    @Override
    public boolean hasColumns() {
	return columns != null && !columns.isEmpty();
    }

    @Override
    public int getColumnCount() {
	return columns == null ? 0 : columns.size();
    }
    
    
}

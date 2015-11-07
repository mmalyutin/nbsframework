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
public class BaseRowModel implements RowModel {

    private List<Row> rows;

    @Override
    public List<Row> getRows() {
	if (rows == null) {
	    rows = new ArrayList<Row>();
	}
        return rows;
    }
    
    @Override
    public Row getRow(int index) {
	return getRows().get(index);
    }

    @Override
    public void setRows(List<Row> rows) {
        this.rows = rows;
    }

    @Override
    public void addRow(Row row) {
	getRows().add(row);
	
    }

    @Override
    public void removeRow(Row row) {
	getRows().remove(row);
	
    }

    @Override
    public boolean hasRows() {
	return rows != null && !rows.isEmpty();
    }

    @Override
    public int getRowCount() {
	return rows == null ? 0 : rows.size();
    }
}

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

import java.util.List;

import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.datastorage.HasExpressionBuilder;
import org.plazmaforge.framework.report.model.base.Element;

/**
 * @author ohapon
 *
 */
public class Grid extends Element implements HasExpressionBuilder {

    private static final long serialVersionUID = -6413187521200757054L;
    
    private CellBorderType cellBorderType;
    

    private ColumnModel columnModel;
    
    private RowModel rowModel;

    public Grid() {
	columnModel = new BaseColumnModel();
	rowModel = new BaseRowModel();
    }

    public Grid(ColumnModel columnModel, RowModel rowModel) {
	this.columnModel = columnModel;
	this.rowModel = rowModel;
    }
    
    protected ColumnModel getColumnModel() {
	if (columnModel == null) {
	    columnModel = new BaseColumnModel();
	}
	return columnModel;
    }
    
    protected RowModel getRowModel() {
	if (rowModel == null) {
	    rowModel = new BaseRowModel();
	}
	return rowModel;
    }

    public List<Column> getColumns() {
	return getColumnModel().getColumns();
    }

    public void setColumns(List<Column> columns) {
	getColumnModel().setColumns(columns);
    }

    public void addColumn(Column column) {
	getColumnModel().addColumn(column);
    }
    
    public void removeColumn(Column column) {
	getColumnModel().removeColumn(column);
    }
    
    public int getColumnCount() {
	return getColumnModel().getColumnCount();
    }
    
    public boolean hasColumns() {
	return getColumnModel().hasColumns();
    }

    public List<Row> getRows() {
	return getRowModel().getRows();
    }
    
    public void setRows(List<Row> rows) {
	getRowModel().setRows(rows);
    }

    public void addRow(Row row) {
	getRowModel().addRow(row);
    }

    public void removeRow(Row row) {
	getRowModel().removeRow(row);
    }
    
    public int getRowCount() {
	return getRowModel().getRowCount();
    }
    
    public boolean hasRows() {
	return getRowModel().hasRows();
    }
    
    public String toString() {
	StringBuffer buf = new StringBuffer();
	buf.append("Grid[");
	
	if (getCellBorderType() != null) {
	    buf.append("cellBorderType=" + getCellBorderType() + ", ");
	}
	
	List<Column> columns = getColumns();
	buf.append("columns=[");
	boolean first = true;	
	for (Column column: columns) {
	    if (!first) {
		buf.append(", ");
	    }
	    first = false;
	    buf.append(column.toString());
	}
	buf.append("]");
	
	List<Row> rows = getRows();
	buf.append(", ");	
	buf.append("rows=[");
	first = true;	
	for (Row row: rows) {
	    if (!first) {
		buf.append(", ");
	    }
	    first = false;
	    buf.append(row.toString());
	}
	buf.append("]");

	
	buf.append("]");
	
	return buf.toString();
    }

    public String toStringModel() {
	StringBuffer buf = new StringBuffer();
	buf.append("Grid[");

	if (getCellBorderType() != null) {
	    buf.append("cellBorderType=" + getCellBorderType() + ", ");
	}
	
	List<Column> columns = getColumns();
	buf.append("\n  columns=[");
	for (Column column: columns) {
	    buf.append("\n    " + column.toString());
	}
	buf.append("\n  ]");
	
	List<Row> rows = getRows();
	buf.append("\n  rows=[");
	for (Row row: rows) {
	    buf.append("\n    " + row.toString());
	}
	buf.append("\n  ]");

	buf.append("\n]");
	
	return buf.toString();
    }
    
    @Override
    public void populateExpressions(List<DSExpression> expressions) {
	super.populateExpressions(expressions);
	
	if (hasColumns()) {
	    for (Column column : getColumns()) {
		populateExpressions(expressions, column);
	    }
	}
	
	if (hasRows()) {
	    for (Row row : getRows()) {
		populateExpressions(expressions, row);
	    }
	}
	
    }

    
    public CellBorderType getCellBorderType() {
        return cellBorderType;
    }

    public void setCellBorderType(CellBorderType cellBorderType) {
        this.cellBorderType = cellBorderType;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((columnModel == null) ? 0 : columnModel.hashCode());
	result = prime * result
		+ ((rowModel == null) ? 0 : rowModel.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Grid other = (Grid) obj;
	if (columnModel == null) {
	    if (other.columnModel != null)
		return false;
	} else if (!columnModel.equals(other.columnModel))
	    return false;
	if (rowModel == null) {
	    if (other.rowModel != null)
		return false;
	} else if (!rowModel.equals(other.rowModel))
	    return false;
	return true;
    }
    
    
}

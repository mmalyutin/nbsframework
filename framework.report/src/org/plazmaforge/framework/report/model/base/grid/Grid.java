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
import org.plazmaforge.framework.report.model.base.Pen;

/**
 * @author ohapon
 *
 */
public class Grid extends Element implements HasExpressionBuilder {

    private static final long serialVersionUID = -6413187521200757054L;
    
    private CellBorderType cellBorderType;
    
    private Pen cellBorder;
    
    private Pen columnBorder;
    
    private Pen rowBorder;

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
	
	if (cellBorderType != null) {
	    buf.append("cellBorderType=" + cellBorderType + ", ");
	}
	if (cellBorder != null) {
	    buf.append("cellBorder=" + cellBorder + ", ");
	}
	if (columnBorder != null) {
	    buf.append("columnBorder=" + columnBorder + ", ");
	}
	if (rowBorder != null) {
	    buf.append("rowBorder=" + rowBorder + ", ");
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

	if (cellBorderType != null) {
	    buf.append("cellBorderType=" + cellBorderType + ", ");
	}
	if (cellBorder != null) {
	    buf.append("cellBorder=" + cellBorder + ", ");
	}
	if (columnBorder != null) {
	    buf.append("columnBorder=" + columnBorder + ", ");
	}
	if (rowBorder != null) {
	    buf.append("rowBorder=" + rowBorder + ", ");
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

    ////
    
    public CellBorderType getCellBorderType() {
        return cellBorderType;
    }

    public void setCellBorderType(CellBorderType cellBorderType) {
        this.cellBorderType = cellBorderType;
    }

    public Pen getCellBorder() {
	if (cellBorder == null) {
	    cellBorder = new Pen();
	}
        return cellBorder;
    }

    public void setCellBorder(Pen cellBorder) {
        this.cellBorder = cellBorder;
    }

    public boolean hasCellBorder() {
	return cellBorder != null;
    }

    public boolean isEmptyCellBorder() {
	return cellBorder == null || cellBorder.isEmpty();
    }

    public Pen getColumnBorder() {
	if (columnBorder == null) {
	    columnBorder = new Pen();
	}
        return columnBorder;
    }

    public void setColumnBorder(Pen columnBorder) {
        this.columnBorder = columnBorder;
    }

    public boolean hasColumnBorder() {
	return columnBorder != null;
    }

    public boolean isEmptyColumnBorder() {
	return columnBorder == null || columnBorder.isEmpty();
    }
    
    public Pen getRowBorder() {
	if (rowBorder == null) {
	    rowBorder = new Pen();
	}
        return rowBorder;
    }

    public void setRowBorder(Pen rowBorder) {
        this.rowBorder = rowBorder;
    }

    public boolean hasRowBorder() {
	return rowBorder != null;
    }

    public boolean isEmptyRowBorder() {
	return rowBorder == null || rowBorder.isEmpty();
    }
    
    ////
    
    public void setColumnModel(ColumnModel columnModel) {
        this.columnModel = columnModel;
    }

    public void setRowModel(RowModel rowModel) {
        this.rowModel = rowModel;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result
		+ ((cellBorder == null) ? 0 : cellBorder.hashCode());
	result = prime * result
		+ ((cellBorderType == null) ? 0 : cellBorderType.hashCode());
	result = prime * result
		+ ((columnBorder == null) ? 0 : columnBorder.hashCode());
	result = prime * result
		+ ((columnModel == null) ? 0 : columnModel.hashCode());
	result = prime * result
		+ ((rowBorder == null) ? 0 : rowBorder.hashCode());
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
	if (cellBorder == null) {
	    if (other.cellBorder != null)
		return false;
	} else if (!cellBorder.equals(other.cellBorder))
	    return false;
	if (cellBorderType != other.cellBorderType)
	    return false;
	if (columnBorder == null) {
	    if (other.columnBorder != null)
		return false;
	} else if (!columnBorder.equals(other.columnBorder))
	    return false;
	if (columnModel == null) {
	    if (other.columnModel != null)
		return false;
	} else if (!columnModel.equals(other.columnModel))
	    return false;
	if (rowBorder == null) {
	    if (other.rowBorder != null)
		return false;
	} else if (!rowBorder.equals(other.rowBorder))
	    return false;
	if (rowModel == null) {
	    if (other.rowModel != null)
		return false;
	} else if (!rowModel.equals(other.rowModel))
	    return false;
	return true;
    }
    
    
}

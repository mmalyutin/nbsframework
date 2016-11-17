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
package org.plazmaforge.framework.report.model.design;

import java.util.List;

import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.report.model.base.Container;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.grid.BaseRowModel;
import org.plazmaforge.framework.report.model.base.grid.CellBorderType;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.base.grid.RowModel;

/**
 * @author ohapon
 *
 */
public class Band extends Container implements RowModel {

    private static final long serialVersionUID = -5843743940302794344L;
    

    /**
     * Band type: ReportHeader, PageHeader, GroupHeader, Detail, GroupFooter, PageFooter, ReportFooter
     */
    private String type;
    
    
    private CellBorderType cellBorderType;
    
    private Pen cellBorder;
    
    private Pen columnBorder;
    
    private Pen rowBorder;
    

    /**
     * Own RowModel. Only for TableReport
     */
    private RowModel rowModel;
    
    public Band() {
	rowModel = new BaseRowModel();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    
    @Override
    public List<Row> getRows() {
	return rowModel.getRows();
    }

    @Override
    public Row getRow(int index) {
	return rowModel.getRow(index);
    }
    
    @Override
    public void setRows(List<Row> rows) {
	rowModel.setRows(rows);
    }

    @Override
    public void addRow(Row row) {
	rowModel.addRow(row);
    }

    @Override
    public void removeRow(Row row) {
	rowModel.removeRow(row);
    }

    @Override
    public boolean hasRows() {
	return rowModel.hasRows();
    }

    @Override
    public int getRowCount() {
	return rowModel.getRowCount();
    }
    
    @Override
    public void populateExpressions(List<DSExpression> expressions) {
	super.populateExpressions(expressions);
	
	// TODO: add own expressions
	
	if (hasRows()) {
	    for (Row row : getRows()) {
		populateExpressions(expressions, row);
	    }
	}
    }
    
}

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
import org.plazmaforge.framework.report.model.base.grid.CellBorderRule;
import org.plazmaforge.framework.report.model.base.grid.HasCellBorderRule;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.base.grid.RowModel;

/**
 * @author ohapon
 *
 */
public class Band extends Container implements HasCellBorderRule, RowModel {

    private static final long serialVersionUID = -5843743940302794344L;
    

    /**
     * Band type: ReportHeader, PageHeader, GroupHeader, Detail, GroupFooter, PageFooter, ReportFooter
     */
    private String type;
    
    /**
     * Rule for generation cell border
     */
    private CellBorderRule cellBorderRule;
    
    /**
     * Cell border line. Only for cell border rule
     */
    private Pen cellLine;

    /**
     * Column border line. Only for cell border rule
     */
    private Pen columnLine;
    
    /**
     * Row border line. Only for cell border rule
     */
    private Pen rowLine;
    

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
    
    public CellBorderRule getCellBorderRule() {
        return cellBorderRule;
    }

    public void setCellBorderRule(CellBorderRule cellBorderRule) {
        this.cellBorderRule = cellBorderRule;
    }

    public Pen getCellLine() {
        return cellLine;
    }

    public void setCellLine(Pen cellLine) {
        this.cellLine = cellLine;
    }

    public boolean isEmptyCellLine() {
	return cellLine == null || cellLine.isEmpty();
    }

    public Pen getColumnLine() {
        return columnLine;
    }

    public void setColumnLine(Pen columnLine) {
        this.columnLine = columnLine;
    }

    public boolean isEmptyColumnLine() {
	return columnLine == null || columnLine.isEmpty();
    }
    
    public Pen getRowLine() {
        return rowLine;
    }

    public void setRowLine(Pen rowLine) {
        this.rowLine = rowLine;
    }

    public boolean isEmptyRowLine() {
	return rowLine == null || rowLine.isEmpty();
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

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
import org.plazmaforge.framework.report.model.base.grid.BaseRowModel;
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

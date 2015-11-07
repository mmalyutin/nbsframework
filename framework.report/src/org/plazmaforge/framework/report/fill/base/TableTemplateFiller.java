/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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
package org.plazmaforge.framework.report.fill.base;

import java.util.List;

import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.report.fill.process.ReportContext;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.model.design.Template;
import org.plazmaforge.framework.report.model.document.Page;

/**
 * @author ohapon
 *
 */
public class TableTemplateFiller extends BaseTemplateFiller {

    
    protected void prepareNewPage(ReportContext context) {
	Template template = context.getTemplate();
	Page page = context.getPage();

	// TableReport
	Grid grid = new Grid(template, null);
	page.addChild(grid);
	context.setGrid(grid);
    }
    
    protected void preparePageFooter(ReportContext context, Band band) {

	int offsetY = context.getEndY() - context.getPageFooterHeight();

	if (offsetY > context.getOffsetY()) {
	    Grid grid = context.getGrid();

	    // Add blank row with rest height
	    Row row = createRow(context);
	    row.setHeight(offsetY - context.getOffsetY());
	    int columnCount = grid.getColumnCount();
	    if (columnCount > 0) {
		// Add blank cell
		Cell cell = new Cell();
		row.addCell(cell);
		cell.setColspan(columnCount);
	    }
	    grid.addRow(row);
	}

	// Set current Y
	context.setOffsetY(offsetY);

    }    
    
    protected void fillContainer(ReportContext context, int evaluation, Band fillContainer, boolean paging) {

	List<Row> rows = fillContainer.getRows();
	if (rows != null) {

	    int height = 0;
	    for (Row row : rows) {
		height += row.getHeight();
	    }

	    if (paging /* && !force */) {
		int offsetY = context.getOffsetY() + height; // calculate new offesetY
		// System.out.println("New offsetY =" + offsetY);
		int endY = context.getEndY();
		int pageFooterHeight = context.getPageFooterHeight();

		if (pageFooterHeight > 0) {
		    if (offsetY >= endY - pageFooterHeight) {
			boolean isPrintPageFooter = evaluatePrintExpression(context, evaluation, context.getTemplateStructure().getPageFooter());
			if (isPrintPageFooter) {
			    startNewPage(context, true); // without evaluate print expression (force=true)
			} else {
			    if (offsetY >= endY) {
				startNewPage(context);
			    }
			}
		    }
		} else {
		    if (offsetY >= endY) {
			startNewPage(context);
		    }
		}
	    }

	    // Shift Y position
	    context.setOffsetY(context.getOffsetY() + height);

	    Grid grid = context.getGrid();
	    for (Row row : rows) {
		grid.addRow(row);
	    }
	}

    }    
    
    protected Band createFillContainer(ReportContext context, int evaluation, Band band) {
	if (band == null) {
	    return null;
	}
	Band fillContainer = null;
	if (!band.hasRows()) {
	    return null;
	}
	fillContainer = new Band();

	// Grid grid = context.getGrid();
	List<Row> rows = band.getRows();
	Row oRow = null;
	Cell oCell = null;
	for (Row row : rows) {

	    oRow = createRow(context);
	    oRow.setHeight(row.getHeight());

	    fillContainer.addRow(oRow);
	    // grid.addRow(oRow);

	    List<Cell> cells = row.getCells();
	    for (Cell cell : cells) {

		oCell = new Cell();
		oCell.setColspan(cell.getColspan());
		oCell.setRowspan(cell.getRowspan());
		oRow.addCell(oCell);

		DSExpression expression = cell.getExpression();
		if (expression != null) {
		    // Set text value by cell expression
		    Object value = evaluateExpression(context, evaluation, expression);
		    if (value != null) {
			String text = formatCellValue(value, cell);
			if (text != null) {
			    oCell.setValue(text);
			}
		    }
		} else {
		    // Set text value by cell value
		    Object value = cell.getValue();
		    if (value != null) {
			String text = formatCellValue(value, cell);
			if (text != null) {
			    oCell.setValue(text);
			}
		    }
		}

	    }
	}
	return fillContainer;
    }
    
}

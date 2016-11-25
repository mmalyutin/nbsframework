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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.datastorage.DSExpression;
import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.report.fill.process.ReportContext;
import org.plazmaforge.framework.report.model.base.Border;
import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.report.model.base.grid.BaseRowModel;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.CellBorderRule;
import org.plazmaforge.framework.report.model.base.grid.GridLayout;
import org.plazmaforge.framework.report.model.base.grid.GridUtils;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.base.grid.RowModel;
import org.plazmaforge.framework.report.model.design.Band;
import org.plazmaforge.framework.report.model.design.Template;
import org.plazmaforge.framework.report.model.design.TemplateStructure;
import org.plazmaforge.framework.report.model.document.Page;

/**
 * @author ohapon
 *
 */
public class TableTemplateFiller extends BaseTemplateFiller {

    @Override
    protected void prepareNewPage(ReportContext context) {
	Template template = context.getTemplate();
	//Band band = context.getBand();
	Page page = context.getPage();

	// New Page: Create grid by table template
	Grid grid = new Grid(template, null);
	
	
	//////////////////////////////////////////////////////////////////////
	//
	// Initialize grid
	//
	//////////////////////////////////////////////////////////////////////
	
	// Template border rule
	CellBorderRule cellBorderRule = template.getCellBorderRule();
	Pen cellBorder = GridUtils.normalizeNonePen(template.hasCellBorder() ? template.getCellBorder() : null);
	Pen columnBorder = GridUtils.normalizeNonePen(template.hasColumnBorder() ? template.getColumnBorder() : null);
	Pen rowBorder = GridUtils.normalizeNonePen(template.hasRowBorder() ? template.getRowBorder() : null);

	// Band border rule
	//CellBorderRule cellBorderRuleB = band.getCellBorderRule();
	//Pen cellBorderB = ExportHelper.normalizeNonePen(band.hasCellBorder() ? band.getCellBorder() : null);
	//Pen columnBorderB = ExportHelper.normalizeNonePen(band.hasColumnBorder() ? band.getColumnBorder() : null);
	//Pen rowBorderB = ExportHelper.normalizeNonePen(band.hasRowBorder() ? band.getRowBorder() : null);

	// cell-border-rule
	grid.setCellBorderRule(cellBorderRule);
	
	// cell-border
	if (cellBorder != null) {
	    grid.setCellLine(cellBorder.clone());
	}
	
	// column-border
	if (columnBorder != null) {
	    grid.setColumnLine(columnBorder.clone());
	}

	// row-border
	if (rowBorder != null) {
	    grid.setRowLine(rowBorder.clone());
	}
	
	page.addChild(grid);
	context.setGrid(grid);
    }
    
    @Override
    protected void preparePageFooter(ReportContext context, Band band) {

	Band pageFooter = context.getTemplateStructure().getPageFooter();
	int pageFooterHeight = context.getPageFooterHeight();
	//int pageFooterHeight = calculateNewBandHeight(context, pageFooter);	
	int offsetY = context.getEndY() - pageFooterHeight;
	
	Grid grid = context.getGrid();

	if (offsetY > context.getOffsetY()) {
	    //Grid grid = context.getGrid();

	    // Add blank row with rest height
	    Row row = createBlankRow(context);
	    row.setHeight(offsetY - context.getOffsetY());
	    
	    // Add blank cell
	    Cell cell = createBlankCell(context);
	    if (cell != null) {
		row.addCell(cell);
	    }
	    grid.addRow(row);
	    
	    
//	    int columnCount = grid.getColumnCount();
//	    if (columnCount > 0) {
//		// Add blank cell
//		Cell cell = new Cell();
//		row.addCell(cell);
//		cell.setColspan(columnCount);
//	    }
	    
	    
	}

	//Set current Y
	//TODO: OFFSET-Y
	//context.setOffsetY(offsetY);

    }    
    
    @Override
    protected void fillContainer(ReportContext context, int evaluation, Band fillContainer, boolean paging) {

	List<Row> rows = fillContainer.getRows();
	if (rows != null) {

	    //TODO: OFFSET-Y
	    //int height = calculateNewBandHeight(context, fillContainer);
	    
	    //TODO: POINT-1: OLD GRID
	    //Grid grid = context.getGrid();
	    
	    if (paging /* && !force */) {
		
		//TODO: OFFSET-Y
		//int offsetY = context.getOffsetY() + height; // calculate new offesetY
		
		int offsetY = context.getOffsetY() + calculateTotalHeight(context, fillContainer); // calculate new offesetY
		
		// System.out.println("New offsetY =" + offsetY);
		int endY = context.getEndY();
		
		Band pageFooter = context.getTemplateStructure().getPageFooter();
		int pageFooterHeight = context.getPageFooterHeight();
		//int pageFooterHeight = calculateNewBandHeight(context, pageFooter);

		if (pageFooterHeight > 0) {
		    
		    int offsetY2 = offsetY + pageFooterHeight;
		    //int offsetY2 = context.getOffsetY() + calculateTotalHeight(context, fillContainer, pageFooter); // calculate new offesetY2 (with pageFooter)
		    
		    //if (offsetY >= endY - pageFooterHeight) {
		    if (offsetY2 >= endY) {
			boolean isPrintPageFooter = evaluatePrintExpression(context, evaluation, pageFooter);
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

	    //Shift Y position
	    //TODO: OFFSET-Y
	    //context.setOffsetY(context.getOffsetY() + height);

	    //TODO: POINT-2: NEW GRID when start new page
	    Grid grid = context.getGrid();
	    for (Row row : rows) {
		grid.addRow(row);
	    }
	}

    }    
    
    @Override
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
		
		if (cell.getBackground() != null) {
		    oCell.setBackground(cell.getBackground());
		}
		if (cell.getForeground() != null) {
		    oCell.setForeground(cell.getForeground());
		}
		if (cell.getFont() != null) {
		    oCell.setFont(cell.getFont());
		}
		if (cell.getHorizontalAlign() != null) {
		    oCell.setHorizontalAlign(cell.getHorizontalAlign());
		}
		if (cell.getVerticalAlign() != null) {
		    oCell.setVerticalAlign(cell.getVerticalAlign());
		}
		
		if (cell.hasBorder()) {
		    oCell.setBorder(Border.cloneBorder(cell.getBorder()));
		}
		
		oRow.addCell(oCell);

		
		Object value = evaluateCellValue(context, evaluation, cell);
		String dataType = cell.getDataType();
		if (dataType == null) {
		    // Auto set data type by class
		    dataType = TypeUtils.getType(value == null ? null : value.getClass());
		}
		oCell.setDataType(dataType);
		oCell.setValue(value);
		oCell.setFormat(cell.getFormat());
		
	    }
	}
	return fillContainer;
    }
    
    protected Object evaluateCellValue(ReportContext context, int evaluation, Cell cell) {
	if (cell == null) {
	    return null;
	}
	DSExpression expression = cell.getExpression();
	if (expression != null) {
	    return evaluateExpression(context, evaluation, expression);
	}
	return cell.getValue();
    }
 
    protected Row createRow(ReportContext context) {
	return createRow(context, false);
    }

    protected Row createBlankRow(ReportContext context) {
	return createRow(context, true);
    }
    
    protected Row createRow(ReportContext context, boolean blank) {
	Row row = new Row();
	if (blank) {
	    row.setRowLine(Pen.NONE);
	    row.setCellLine(Pen.NONE);
	}
	
	Band band = context.getBand();
	if (band == null) {
	    return row;
	}
	
	// Transfer band attributes to row
	if (band.getBackground() != null) {
	    row.setBackground(band.getBackground());
	}
	if (band.getForeground() != null) {
	    row.setForeground(band.getForeground());
	}
	if (band.getFont() != null) {
	    row.setFont(band.getFont());
	}
	
	// Transfer band borders to row
	if (band.hasCellLine()) {
	    row.setCellLine(band.getCellLine().clone());
	}
	if (band.hasColumnLine()) {
	    row.setColumnLine(band.getColumnLine().clone());
	}
	if (band.hasRowLine()) {
	    row.setRowLine(band.getRowLine().clone());
	}
	
	
	return row;
    }

    protected Cell createCell(ReportContext context) {
	return createCell(context, false);
    }

    protected Cell createBlankCell(ReportContext context) {
	return createCell(context, true);
    }
    
    protected Cell createCell(ReportContext context, boolean blank) {
	
	if (blank) {
	    
	    // blank cell
	    Grid grid = context.getGrid();
	    if (grid == null) {
		return null;
	    }
	    int columnCount = grid.getColumnCount();
	    if (columnCount == 0) {
		return null;
	    }
	    Cell cell = new Cell();
	    cell.setColspan(columnCount);
	    cell.setBorder(Border.NONE);
	    
	    return cell;
	}

	Cell cell = new Cell();
	return cell;
    }
    
    @Override
    protected int calculateTemplateHeight(Template template, TemplateStructure structure) {
	int height = super.calculateTemplateHeight(template, structure);
	// TODO: GRID BORDER: top, bottom
	return height;
    }
    
    @Override
    protected int calculateBandHeight(Template template, Band band, boolean force) {
  	if (band == null) {
  	    return 0;
  	}
  	int height = band.getHeight();
  	if (!force) {
  	    return height;
  	}
  	
  	
  	CellBorderRule cellBorderRule = template.getCellBorderRule();
	
	Pen defCellBorder = template.hasCellBorder() ? template.getCellBorder() : null;
	Pen defColumnBorder = defCellBorder;
	Pen defRowBorder = defCellBorder;
	
	// Override column border
	if (!template.isEmptyColumnBorder()) {
	    defColumnBorder  = template.getColumnBorder();
	}
	
	// Override row border
	if (!template.isEmptyRowBorder()) {
	    defRowBorder  = template.getRowBorder();
	}
	
	GridLayout layout = GridUtils.getGridLayout(template, band, cellBorderRule, defColumnBorder, defRowBorder);
	return layout.getAreaHeight();
	
  	
  	// TODO: Only for Table report
  	//height = calculateBandHeightByRows(band);
  	//return height;
  	
  	//TODO: Get children band
  	//return band.getHeight();
    }
    
    protected int calculateBandHeightByRows(Band band) {
  	if (!band.hasRows()) {
  	    return 0;
  	}
  	int height = 0;
  	List<Row> rows = band.getRows();
  	for (Row row : rows) {
  	    height += row.getHeight();
  	}
  	return height;
    }
    
    protected int calculateNewBandHeight(ReportContext context, Band band) {
	if (band == null) {
	    return 0;
	}
	Grid grid = context.getGrid();
	GridLayout layout = GridUtils.getGridLayout(grid);
	int heightOld = layout.getAreaHeight();
	
	List<Row> rows = new ArrayList<Row>();
	if (grid.hasRows()) {
	    rows.addAll(grid.getRows());
	}
	if (band.hasRows()) {
	    rows.addAll(band.getRows());
	}
	RowModel rowModel = new BaseRowModel(rows);
	layout = GridUtils.getGridLayout(grid, rowModel);
	
	int heightNew = layout.getAreaHeight();
	
	return heightNew - heightOld;
    }
    
    
    /**
     * Calculate total height:
     * - grid
     * - band
     * 
     * @param context
     * @param band
     * @return
     */
    protected int calculateTotalHeight(ReportContext context, Band band) {
	return calculateTotalHeight(context, band, null);
    }
    
    
    /**
     * Calculate total height:
     * - grid
     * - band1
     * - band2
     *  
     * @param context
     * @param band1
     * @param band2
     * @return
     */
    protected int calculateTotalHeight(ReportContext context, Band band1, Band band2) {
	Grid grid = context.getGrid();
	GridLayout layout = null;
	int height = 0;
	
	if (band1 == null && band2 == null) {
	    layout = GridUtils.getGridLayout(grid);
	    height = layout.getAreaHeight();
	    // TODO: GRID BORDER: top, bottom?
	    return height;
	}
	
	// grid
	List<Row> rows = new ArrayList<Row>();
	if (grid.hasRows()) {
	    rows.addAll(grid.getRows());
	}
	
	// band1
	if (band1 != null && band1.hasRows()) {
	    rows.addAll(band1.getRows());
	}
	
	// band2
	if (band2 != null && band2.hasRows()) {
	    rows.addAll(band2.getRows());
	}
	
	RowModel rowModel = new BaseRowModel(rows);
	layout = GridUtils.getGridLayout(grid, rowModel);
	height = layout.getAreaHeight();
	// TODO: GRID BORDER: top, bottom?
	return height;
    }
    
}

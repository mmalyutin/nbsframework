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
package org.plazmaforge.framework.report.export.uwt;

import java.util.List;

import org.plazmaforge.framework.report.exception.RTException;
import org.plazmaforge.framework.report.export.AbstractReportExporter;
import org.plazmaforge.framework.report.model.base.Element;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.report.model.base.grid.Column;
import org.plazmaforge.framework.report.model.base.grid.Grid;
import org.plazmaforge.framework.report.model.base.grid.Row;
import org.plazmaforge.framework.report.model.document.Document;
import org.plazmaforge.framework.report.model.document.Page;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.GC;

/**
 * @author ohapon
 *
 */
public class UWTCanvasExporter extends AbstractReportExporter {


    
    private int offsetX;
    
    private int offsetY;

    @Override
    public void exportDocument(Document document) throws RTException {
	if (document == null) {
	    return;
	}
	GC gc = getGC();
	if (gc == null) {
	    throw new RTException("Can't export document to format 'UWTCanvas'. GC is null.");
	}
	init();
	paintDocument(gc, document);
    }

    @Override
    public void exportPage(Page page) throws RTException {
	if (page == null) {
	    return;
	}
	GC gc = getGC();
	if (gc == null) {
	    throw new RTException("Can't export page to format 'UWTCanvas'. GC is null.");
	}
	init();
	paintPage(gc, page);
    }

    protected GC getGC() {
	return (GC) getData("gc");
    }
    
    protected void init() {
	Integer offset = getIntegerValue("offsetX");
	if (offset != null) {
	    offsetX = offset;
	}
	offset = getIntegerValue("offsetY");
	if (offset != null) {
	    offsetY = offset;
	}
    }
    
    protected Integer getIntegerValue(String name) {
	Object value = getData(name);
	return (value instanceof Integer) ? (Integer) value : null;  
    }
    
    protected void paintDocument(GC gc, Document document) {
	if (document == null || !document.hasPages()) {
	    return;
	}
	paintPage(gc, document.getPages().get(0));
    }

    protected void paintPage(GC gc, Page page) {
	if (page == null) {
	    return;
	}

	if (!page.hasChildren()) {
	    return;
	}

	// shift offsets by margin
	offsetX = offsetX + page.getMargin().getLeft();
	offsetY = offsetY + page.getMargin().getTop();
	
	List<Element> children = page.getChildren();

	for (Element element : children) {
	    if (element instanceof Grid) {
		paintGrid(gc, (Grid) element);
	    }
	}
    }

    protected void paintGrid(GC gc, Grid grid) {
	if (grid.getRowCount() == 0) {
	    return;
	}

	int columnCount = grid.getColumnCount();
	List<Column> columns = grid.getColumns();
	int width = 0;
	for (Column column : columns) {
	    width += column.getWidth();
	}

	List<Row> rows = grid.getRows();
	int height = 0;
	for (Row row : rows) {
	    height += row.getHeight();
	}

	// Draw grid border
	gc.drawRectangle(offsetX, offsetY, width, height);

	Color background = gc.getBackground();
	for (Row row : rows) {
	    
	    if (row.getBackground() != null) {
		gc.setBackground(row.getBackground());
		gc.fillRectangle(offsetX, offsetY, width, row.getHeight());
	    }
	    
	    gc.setBackground(background);
	    
	    
	    List<Cell> cells = row.getCells();

	    int columnIndex = 0;
	    int cellX = offsetX;
	    int cellY = offsetY;
	    for (Cell cell : cells) {

		if (cell.getValue() != null) {
		    if (cell.getValue() instanceof String) {
			String text = (String) cell.getValue();
			gc.drawText(text, cellX, cellY);
		    }
		}
		int prevColumnIndex = columnIndex;
		columnIndex += cell.getColspan();
		if (columnIndex < columnCount) {
		    cellX += columns.get(prevColumnIndex).getWidth(); // ???
		}

	    }
	    

	    offsetY += row.getHeight();
	    
	    //TODO
	    // Draw row bottom border
	    gc.drawLine(offsetX, offsetY - 1, offsetX + width, offsetY - 1);

	}
    }

    
}

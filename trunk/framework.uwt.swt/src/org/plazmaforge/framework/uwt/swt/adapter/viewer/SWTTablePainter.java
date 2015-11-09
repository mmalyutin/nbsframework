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

package org.plazmaforge.framework.uwt.swt.adapter.viewer;

import org.eclipse.swt.SWT;
import org.plazmaforge.framework.uwt.swt.adapter.SWTHelper;
import org.plazmaforge.framework.uwt.swt.adapter.SWTTableColumnAdapter;
import org.plazmaforge.framework.uwt.util.UWTUtils;
import org.plazmaforge.framework.uwt.widget.CellContext;
import org.plazmaforge.framework.uwt.widget.CellRenderer;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

public class SWTTablePainter {
    
    public CellContext createCellData(org.eclipse.swt.widgets.Table xTable,
	    org.eclipse.swt.widgets.TableColumn xTableColumn,
	    org.eclipse.swt.widgets.TableItem xTableItem,
	    org.eclipse.swt.widgets.Event event,
	    TableColumn tableColumn) {

	CellContext cellContext = new CellContext();
	cellContext.setColumn(event.index);
	cellContext.setRow(xTable.indexOf(xTableItem));
	//cellContext.selected = (event.detail & SWT.SELECTED) != 0; // DISABLE: Use in MeasureEvent
	//cellContext.focus = hasFocus; //TODO: May be (event.detail & SWT.FOCUS) != 0; // DISABLE: Use in MeasureEvent 
	cellContext.setData(xTableItem.getData()); // Element/data of item/row
	cellContext.setValue(UWTUtils.getValue(cellContext.getData(), tableColumn.getTable(), tableColumn)); // TODO: Double calculate value of cell !!! See SWTLabelProvider
	//cellContext.image = xTableItem.getText(event.index); //getTextValue(value, tableColumn); //TODO
	cellContext.setText(xTableItem.getText(event.index)); //TODO: Must analyze. See SWTLabelProvider

	return cellContext;
    }
    
    protected CellContext getCellData(org.eclipse.swt.widgets.TableItem xTableItem, int column) {
	return (CellContext) xTableItem.getData(SWTTableColumnAdapter.SYS_PROPETY_TABLE_COLUMN + column);
    }

    protected void setCellData(org.eclipse.swt.widgets.TableItem xTableItem, int column, CellContext cellData) {
	xTableItem.setData(SWTTableColumnAdapter.SYS_PROPETY_TABLE_COLUMN + column, cellData);
    }

    
    /**
     * Return true if need erase cell
     * @param cellData
     * @return
     */
    protected boolean isNeedErase(CellContext cellData) {
	if (cellData == null) {
	    return false;
	}
	return cellData.getBackground() != null;
    }
    
    /**
     * Return true if need paint cell
     * @param cellData
     * @return
     */
    protected boolean isNeedPaint(CellContext cellData) {
	if (cellData == null) {
	    return false;
	}
	return cellData.getForeground() != null || cellData.getFont() != null; 
    }

    
    /**
     * Measure item
     * Use to prepare
     * @param xTable
     * @param event
     */
    public void onMeasureItem(org.eclipse.swt.widgets.Table xTable, org.eclipse.swt.widgets.Event event) {
	// Do nothing
	// We can't Celldata in 
	if (xTable == null || event == null) {
	    return;
	}
	org.eclipse.swt.widgets.TableColumn xTableColumn = xTable.getColumn(event.index);
	org.eclipse.swt.widgets.TableItem xTableItem = (org.eclipse.swt.widgets.TableItem) event.item;
	
	TableColumn tableColumn = (TableColumn) xTableColumn.getData(SWTTableColumnAdapter.SYS_PROPETY_TABLE_COLUMN);
	CellRenderer cellRenderer = tableColumn.getCellRenderer();
	
	if (cellRenderer == null) {
	    return;
	}
	
	CellContext cellData = createCellData(xTable, xTableColumn, xTableItem, event, tableColumn);
	
	// WARNING !!! 
	// (event.detail & SWT.SELECTED) IS NOT SETTING in MeasureEvent
	// We can't render cell in MeasureEvent
	// Use EraseEvent to rendering

	// Set CellData to SWT TableItem by column index
	setCellData(xTableItem, event.index, cellData);
    }

    /**
     * Erase Item
     * Use to set background
     * @param xTable
     * @param event
     */
    public void onEraseItem(org.eclipse.swt.widgets.Table xTable, org.eclipse.swt.widgets.Event event) {
	if (xTable == null || event == null) {
	    return;
	}
	org.eclipse.swt.widgets.TableColumn xTableColumn = xTable.getColumn(event.index);
	org.eclipse.swt.widgets.TableItem xTableItem = (org.eclipse.swt.widgets.TableItem) event.item;
	
	TableColumn tableColumn = (TableColumn) xTableColumn.getData(SWTTableColumnAdapter.SYS_PROPETY_TABLE_COLUMN);
	CellRenderer cellRenderer = tableColumn.getCellRenderer();
	
	if (cellRenderer == null) {
	    return;
	}
	
	CellContext cellData = getCellData(xTableItem, event.index);
	cellData.setSelected((event.detail & SWT.SELECTED) != 0);
	cellRenderer.render(cellData);
	
	//if (!isNeedErase(cellData)) {
	//    return;
	//}

	org.eclipse.swt.graphics.Rectangle bounds = event.getBounds();
	org.eclipse.swt.graphics.Color oldBackground = event.gc.getBackground();
	
	if (cellData.getBackground() != null) {
	    event.gc.setBackground(SWTHelper.getColor(cellData.getBackground()));
	}
	
	event.gc.fillRectangle(bounds);

	/* restore the old GC colors */
	event.gc.setBackground(oldBackground);

	if (cellData.getBackground() != null) {
	    event.detail &= ~SWT.SELECTED;    // ERASE SELCTED MODE
	    event.detail &= ~SWT.BACKGROUND;  // ERASE BACKGROUND MODE
	}
	
	
	event.detail &= ~SWT.FOREGROUND;  // ERASE FOREGROUND MODE
    }

    
    public void onPaintItem(org.eclipse.swt.widgets.Table xTable, org.eclipse.swt.widgets.Event event) {
	if (xTable == null || event == null) {
	    return;
	}
	org.eclipse.swt.widgets.TableColumn xTableColumn = xTable.getColumn(event.index);
	org.eclipse.swt.widgets.TableItem xTableItem = (org.eclipse.swt.widgets.TableItem) event.item;
	TableColumn tableColumn = (TableColumn) xTableColumn.getData(SWTTableColumnAdapter.SYS_PROPETY_TABLE_COLUMN);
	
	CellRenderer cellRenderer = tableColumn.getCellRenderer();
	
	if (cellRenderer == null) {
	    //TODO: Must analyze column with attributes: align = center, image != null
	    // If need own renderer then paint cell
	    return;
	}
	
	CellContext cellData = getCellData(xTableItem, event.index);
	//if (!isNeedPaint(cellData)) {
	//    return;
	//}
	
	//http://www.eclipse.org/articles/Article-CustomDrawingTableAndTreeItems/customDraw.htm
	//http://stackoverflow.com/questions/13627676/jface-tableviewer-make-cell-background-color-blink
	
	if (cellData.getForeground() != null) {
	    event.gc.setForeground(SWTHelper.getColor(cellData.getForeground()));
	}
	
	if (cellData.getFont() != null) {
	    event.gc.setFont(SWTHelper.getFont(cellData.getFont()));
	}

	if (cellData.getImage() != null) {
	    //TODO: Draw image
	}

	if (cellData.getText() != null) {
	    
	    
	    org.eclipse.swt.graphics.Point textSize = event.gc.textExtent(cellData.getText());
	    int xTextOffset = 5; // WHY?
	    int yTextOffset = Math.max(0, (event.height - textSize.y) / 2) + 1; // WHY + 1?
	    
	    int textAreaWidth = event.width - (xTextOffset * 2);
	    if (textAreaWidth < 0) {
		textAreaWidth = 0;
	    }
	    int textAreaHeight = event.height - (yTextOffset * 2);
	    if (textAreaHeight < 0) {
		textAreaHeight = 0;
	    }
	    
	    //TODO: Must convert text if width of text > cell width
	    //TODO: Must use ALIGN to draw text
	    event.gc.drawString(cellData.getText(), event.x + xTextOffset, event.y + yTextOffset, true);
	}
	
	
    }


}

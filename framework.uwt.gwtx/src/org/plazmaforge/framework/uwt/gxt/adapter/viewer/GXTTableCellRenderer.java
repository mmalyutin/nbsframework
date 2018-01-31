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

package org.plazmaforge.framework.uwt.gxt.adapter.viewer;


import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTHelper;
import org.plazmaforge.framework.uwt.gxt.data.Model;
import org.plazmaforge.framework.uwt.gxt.widget.XColumnConfig;
import org.plazmaforge.framework.uwt.gxt.widget.cell.XCell;
import org.plazmaforge.framework.uwt.gxt.widget.cell.XCellRenderer;
import org.plazmaforge.framework.uwt.gxt.widget.cell.XContext;
import org.plazmaforge.framework.uwt.util.StorageUtils;
import org.plazmaforge.framework.uwt.widget.CellContext;
import org.plazmaforge.framework.uwt.widget.CellRenderer;
import org.plazmaforge.framework.uwt.widget.LabelProvider;
import org.plazmaforge.framework.uwt.widget.Viewer;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

/**
 * 
 * @author ohapon
 *
 */
public class GXTTableCellRenderer implements XCellRenderer {

    
    /**
     * UWT Viewer
     */
    private Viewer viewer;

    /**
     * UWT TableColumn
     */
    private TableColumn tableColumn;

    /**
     * UWT CellRenderer
     */
    private CellRenderer cellRenderer;

    /**
     * UWT LabelProvider
     */
    private LabelProvider labelProvider;
    
    
    
    public GXTTableCellRenderer(Viewer viewer, TableColumn tableColumn) {
	super();
	this.viewer  = viewer;
	this.tableColumn = tableColumn;
    }

    
    public CellRenderer getCellRenderer() {
        return cellRenderer;
    }


    public void setCellRenderer(CellRenderer cellRenderer) {
        this.cellRenderer = cellRenderer;
    }


    public LabelProvider getLabelProvider() {
        return labelProvider;
    }

    public void setLabelProvider(LabelProvider labelProvider) {
        this.labelProvider = labelProvider;
    }

    /**
     * Render cell value
     */
    public void render(Context context, Object value, SafeHtmlBuilder sb) {
	int rowIndex = context.getIndex();
	int colIndex = context.getColumn();

	Object model = null;
	Object element = null;
	XContext xContext = null;
	
	if (context instanceof XContext) {
	    xContext = (XContext) context;
	}
	
	if (xContext != null) {
	    model = xContext.getModel();
	    element = getElement((Model) model);
	}
	
	// Cell image 
	Image image = null;

	// Cell text 
	String text = null;
	
	// Cell background
	Color background = null;
	    
	// Cell foreground
	Color foreground = null;
	  
	// Cell font
	Font font = null;    	

	String imageAttr = null;
	String textStyle = null;
	String textAttr = null;
	
	String cellStyle = null;
	String cellAttr = null;
	
	String content = null;
	String imageContent = null;
	String textContent = null;
	
	
	
	if (labelProvider != null) {
	    image = labelProvider.getImage(element);
	    text = labelProvider.getText(element);
	} else {
	    text = getTextValue(value, tableColumn);
	}

	if (cellRenderer != null) {
	    
	    // Reset cell attributes
	    //config.cellAttr = null;
	    if (xContext != null) {
		xContext.setCellStyle(null);
	    }
	    
	    CellContext cellContext = new CellContext();
	    cellContext.setRow(rowIndex);
	    cellContext.setColumn(colIndex);
	    cellContext.setData(element);
	    cellContext.setValue(value);
	    cellContext.setText(text);
	    cellContext.setImage(image);
	    
	    //TODO: Selection doesn't support in rendering mode
	    //cellContext.selected = isSelectedRow(grid, model);
	    
	    cellRenderer.render(cellContext);
	    
	    text = cellContext.getText();
	    image = cellContext.getImage();
	    background = cellContext.getBackground();
	    foreground = cellContext.getForeground();
	    font = cellContext.getFont();
	}
	

	if (image != null) {
	    String path = image.getPath();
	    if (path != null) {
		String storage = StorageUtils.getImageStorage(viewer, path);
		String fullPath = StorageUtils.getPath(storage, path);
		imageAttr = "border='0' style='width: 16px; height: 16px; background: url(" + fullPath + ") no-repeat 0px 0px transparent; '";
		imageAttr = imageAttr + " src='/application/clear.cache.gif'";
		// imageAttr = imageAttr + " class='x-tree3-node-icon'";
	    }
	}
	if (text == null) {
	    text = "&#160;";
	}

	textStyle = "white-space: nowrap; line-height: 11px; text-decoration: none; padding: 0 0 0 3px; position: relative;";
	if (imageAttr != null) {
	    textStyle = textStyle + " top: -4px;"; // Fix top if use Image. Why?
	}
	// textAttr = " style='white-space: nowrap; line-height: 11px; text-decoration: none; padding: 0 0 0 13px; position: relative;'";
	
	if (foreground != null) {
	    String foregroundString = getColorString(foreground);
	    textStyle = GXTHelper.applyStyle(textStyle, "color", foregroundString);
	}

	textAttr = " style='" + textStyle + "'";
	// textAttr = textAttr + " class='x-tree3-node-text'"; // top: -4px !!!	// if use Image
	textContent = "<span " + textAttr + " >" + text + "</span>";

	if (imageAttr != null) {
	    imageContent = "<img " + imageAttr + "/>";
	    cellStyle = "padding-top: 0px; padding-bottom: 0px;";
	    // config.cellAttr = "style='padding-top: 0px; padding-bottom: 0px;'"; // Fix padding of cell: big height of row
	}

	if (background != null) {
	    String backgroundString = getColorString(background);
	    cellStyle = GXTHelper.applyStyle(cellStyle, "background", backgroundString);
	}

	//if (cellStyle != null) {
	    //config.cellAttr = "style='" + cellStyle + "'";
	   
	//}
	
	if (xContext != null) {
	    xContext.setCellStyle(cellStyle);
	}
	 
	if (imageContent == null) {
	    imageContent = "";
	}

	content = imageContent + textContent;	
	
	sb.append(SafeHtmlUtils.fromTrustedString(content));
    }


//    /**
//     * Return true if row is selected
//     * @param grid
//     * @param model
//     * @return
//     */
//    protected boolean isSelectedRow(Grid<M> grid, M model) {
//	if (grid == null || model == null) {
//	    return false;
//	}
//	GridSelectionModel<M> selectionModel = grid.getSelectionModel();
//	return selectionModel == null ? false : selectionModel.isSelected(model);
//    }
    
    protected Object getElement(Model model) {
	return GXTHelper.getBean(model);
    }
    
    protected String getColorString(Color color) {
	return GXTHelper.getColorString(color);
    }

    protected String getFontString(Font font) {
	return GXTHelper.getFontString(font);
    }

    protected String getFontStyle(Font font) {
	return GXTHelper.getFontStyle(font);
    }
    
    protected XCell getCell(TableColumn column) {
	if (column == null) {
	    return null;
	}
	XColumnConfig xColumn = (XColumnConfig) column.getDelegate();
	if (xColumn == null) {
	    return null;
	}
	
	return xColumn.getXCell();
    }
    
    protected String getTextValue(Object value) {
	return value == null ? null : value.toString();
    }
    
    protected String getTextValue(Object value, TableColumn column) {
	if (value == null) {
	    return null;
	}
	
	XCell xCell = getCell(column);
	if (xCell == null) {
	    return getTextValue(value);
	}
	
	return xCell.formatValue(value);
    }


   

}

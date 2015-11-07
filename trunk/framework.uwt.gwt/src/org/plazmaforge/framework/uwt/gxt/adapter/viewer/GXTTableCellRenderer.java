package org.plazmaforge.framework.uwt.gxt.adapter.viewer;

import java.util.Date;

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.gxt.adapter.GXTHelper;
import org.plazmaforge.framework.uwt.gxt.widget.XColumnConfig;
import org.plazmaforge.framework.uwt.util.StorageUtils;
import org.plazmaforge.framework.uwt.widget.CellContext;
import org.plazmaforge.framework.uwt.widget.CellRenderer;
import org.plazmaforge.framework.uwt.widget.LabelProvider;
import org.plazmaforge.framework.uwt.widget.Viewer;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.ColumnData;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.GridSelectionModel;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

public class GXTTableCellRenderer<M extends ModelData> implements GridCellRenderer<M> {

    
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



    @Override
    public Object render(M model, String property, ColumnData config, int rowIndex, int colIndex, ListStore<M> store, Grid<M> grid) {
	
	// Get row element
	Object element = getElement(model);
	
	// Get cell value of element
	Object value = getValue(model, property);
	
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
	    config.cellAttr = null;
	    
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

	if (cellStyle != null) {
	    config.cellAttr = "style='" + cellStyle + "'";
	}
	if (imageContent == null) {
	    imageContent = "";
	}

	content = imageContent + textContent;
	return content;
    }

    /**
     * Return true if row is selected
     * @param grid
     * @param model
     * @return
     */
    protected boolean isSelectedRow(Grid<M> grid, M model) {
	if (grid == null || model == null) {
	    return false;
	}
	GridSelectionModel<M> selectionModel = grid.getSelectionModel();
	return selectionModel == null ? false : selectionModel.isSelected(model);
    }
    
    protected Object getElement(M model) {
	return GXTHelper.getBean(model);
    }
    
    /**
     * Return value of model by property
     * @param model
     * @param property
     * @return
     */
    protected Object getValue(M model, String property) {
	return GXTHelper.getValue(model, property);
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
    
    protected String getTextValue(Object value) {
	return value.toString();
    }
    
    protected String getTextValue(Object value, TableColumn column) {
	if (value == null) {
	    return null;
	}
	String format = column.getFormat();
	if (format == null) {
	    return getTextValue(value);
	}
	
	XColumnConfig xColumn = (XColumnConfig) column.getDelegate();
	if (xColumn == null) {
	    return null;
	}
	//

	if (value instanceof Number) {
	    
	    // GWT NumberFormat
	    NumberFormat numberFormat = xColumn.getNumberFormat();
	    return numberFormat == null? getTextValue(value) : numberFormat.format((Number) value);
	}
	
	if (value instanceof Date) {
	    
	    // GWT DateTimeFormat
	    DateTimeFormat dateTimeFormat = xColumn.getDateTimeFormat();
	    return dateTimeFormat == null? getTextValue(value) : dateTimeFormat.format((Date) value);
	}
	
	return getTextValue(value);
    }

}

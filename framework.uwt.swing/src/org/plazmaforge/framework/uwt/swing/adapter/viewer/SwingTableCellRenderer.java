package org.plazmaforge.framework.uwt.swing.adapter.viewer;


import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.swing.adapter.SwingHelper;
import org.plazmaforge.framework.uwt.util.UWTUtils;
import org.plazmaforge.framework.uwt.widget.CellContext;
import org.plazmaforge.framework.uwt.widget.CellRenderer;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.LabelProvider;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

public class SwingTableCellRenderer extends DefaultTableCellRenderer {

    /**
     * UWT TableColumn
     */
    private TableColumn tableColumn;
    
    
    public SwingTableCellRenderer(TableColumn tableColumn) {
	super();
	this.tableColumn = tableColumn;
    }


    public java.awt.Component getTableCellRendererComponent(JTable table, Object value,  boolean isSelected, boolean hasFocus, int row, int column) {
	
	CellRenderer cellRenderer = tableColumn.getCellRenderer();
	LabelProvider labelProvider = tableColumn.getLabelProvider();
	
	Object element = null; 
		
	java.awt.Component xComponent = null;
	javax.swing.Icon xImage = null;
	String text = null;
	
	// UWT render control
	Control control = null;
	
	java.awt.Color xBackground = null;
	java.awt.Color xForeground = null;
	java.awt.Font xFont = null;
	
	if (cellRenderer != null) {
	    element = ((SwingTableModel) table.getModel()).getElement(row);
	    
	    CellContext cellContext = new CellContext();
	    cellContext.setColumn(column);
	    cellContext.setRow(row);
	    cellContext.setSelected(isSelected);
	    cellContext.setFocus(hasFocus);
	    cellContext.setData(element);
	    cellContext.setValue(value);
	    cellContext.setText(getTextValue(value, tableColumn));
	    
	    cellRenderer.render(cellContext);
	    
	    if (cellContext.getBackground() != null) {
		xBackground = SwingHelper.getColor(cellContext.getBackground());
	    }
	    if (cellContext.getForeground() != null) {
		xForeground = SwingHelper.getColor(cellContext.getForeground());
	    }
	    if (cellContext.getFont() != null) {
		xFont = SwingHelper.getFont(cellContext.getFont());
	    }
	    
	    if (cellContext.getImage() != null) {
		// Create Swing Image
		xImage = SwingHelper.createImageIcon(tableColumn, cellContext.getImage());
	    }
	    
	    text = cellContext.getText();
	
	    
	} else if (labelProvider != null) {
	    
	    element = ((SwingTableModel) table.getModel()).getElement(row);
	    
	    // Get UWT Image by LabelProvider
	    Image image = labelProvider.getImage(element);
	    
	    // Create Swing Image
	    xImage = SwingHelper.createImageIcon(tableColumn, image);
	    
	    // Get text value by LabelProvider
	    text = labelProvider.getText(element);
	} else {
	    // Get text value by Format
	    text = getTextValue(value, tableColumn);
	}

	// Set Swing Image
	if (xImage != null) {
	    setIcon(xImage);
	}
	
	xComponent = super.getTableCellRendererComponent(table, text, isSelected, hasFocus, row, column);
	
	if (cellRenderer != null) {
	    
	    // Set Background
	    if (xBackground == null) {
		xBackground = isSelected ? table.getSelectionBackground() : table.getBackground();
	    }
	    xComponent.setBackground(xBackground);
	    
	    // Set Foreground
	    if (xForeground == null) {
		xBackground = isSelected ? table.getSelectionForeground() : table.getForeground();
	    }
	    xComponent.setForeground(xForeground);
	    
	    // Set Font
	    if (xFont == null) {
		xFont = table.getFont();
	    }
	    xComponent.setFont(xFont);
	}

	return xComponent;
    }
    
    protected String getTextValue(Object value, TableColumn column) {
  	return UWTUtils.getTextValue(value, column);
    }
    
   
}

package org.plazmaforge.framework.uwt.swing.adapter;

import javax.swing.JCheckBox;

import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.swing.adapter.viewer.SwingCellEditor;
import org.plazmaforge.framework.uwt.swing.util.SwingUtils;
import org.plazmaforge.framework.uwt.swing.widget.XDateField;
import org.plazmaforge.framework.uwt.swing.widget.XDateTimeField;
import org.plazmaforge.framework.uwt.swing.widget.XNumberField;
import org.plazmaforge.framework.uwt.swing.widget.XTextField;
import org.plazmaforge.framework.uwt.swing.widget.XTimeField;
import org.plazmaforge.framework.uwt.util.StorageUtils;
import org.plazmaforge.framework.uwt.util.UWTHelper;
import org.plazmaforge.framework.uwt.widget.CellEditor;
import org.plazmaforge.framework.uwt.widget.DefaultCellEditor;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

public class SwingHelper {

    private SwingHelper() {
    }

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // RESOURCES
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Convert alpha form 0..1 to 0..255
     * @param alpha
     * @return
     */
    public static int get255ColorAlpha(float alpha) {
	return (int) (alpha * 255);
    }

    public static java.awt.Color getColor(Color color) {
	return new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue(), get255ColorAlpha(color.getAlpha()));
    }

    public static java.awt.Font getFont(Font font) {
	int style = java.awt.Font.PLAIN;
	if (font.isBold()) {
	    style |= java.awt.Font.BOLD;
	}
	if (font.isItalic()) {
	    style |= java.awt.Font.ITALIC;
	}
	// WARNING !!! 
	// AWT Attributes order: name, style, size
	// UWT Attributes order: name, size, style
	java.awt.Font xFont = new java.awt.Font(font.getName(), style, font.getSize());
	return xFont;
    }

    public static javax.swing.ImageIcon createImageIcon(UIObject element, Image image) {
	if (image == null) {
	    return null;
	}
	String path = image.getPath();
	return createImageIcon(element, path);
    }

    public static javax.swing.ImageIcon createImageIcon(UIObject element, String path) {
	if (path == null) {
	    return null;
	}
	String storage = StorageUtils.getImageStorage(element, path);
	return createImageIcon(storage, path);
    }
    
    public static javax.swing.ImageIcon createImageIcon(String storage, String path) {
	String fullPath = StorageUtils.getPath(storage, path);
	return createImageIcon(fullPath);
    }

    public static javax.swing.ImageIcon createImageIcon(String path) {
	// TODO: By default we get Image from classpath
	// Must analyze prefixes 'file:', 'classpath:'
	// - classpath:		getClassImageIcon
	// - file: and other	getFileImageIcon
	return path == null ? null : SwingUtils.getClassImageIcon(path);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // FACTORY
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static javax.swing.table.TableCellEditor createCellEditor(javax.swing.JTable xTable,  TableColumn tableColumn, CellEditor cellEditor) {
	if (cellEditor == null) {
	    return null;
	}

	// Create native CellEditor by data type. Only for DefaultCellEditor
	if (cellEditor instanceof DefaultCellEditor) {
	    
	    String dataType = cellEditor.getDataType();
	    // If data type is unknown then use data type of column
	    if (dataType == null) {
		dataType = tableColumn.getDataType();
	    }
	    
	    String format = tableColumn.getFormat(); 
		    
	    // TODO: Only for String, Text, Boolean data type
	    if (dataType == null || TypeUtils.isLikeStringType(dataType)) {
		return new SwingCellEditor(new XTextField());
		
	    } else if (TypeUtils.isBooleanType(dataType)) {
		return new SwingCellEditor(new JCheckBox());
		
	    } else if (TypeUtils.isLikeNumberType(dataType)) {
		XNumberField xNumberField = new XNumberField();
		String numberFormat = format;
		if (numberFormat == null) {
		    numberFormat = UWTHelper.getNumberFormat(tableColumn);
		}
		if (numberFormat != null) {
		    xNumberField.setPattern(numberFormat);
		}
		return new SwingCellEditor(xNumberField);
		
	    } else if (TypeUtils.isDateType(dataType)) {
		XDateField xDateField = new XDateField();
		String dateFormat = format;
		if (dateFormat == null) {
		    dateFormat = UWTHelper.getDateFormat(tableColumn);
		}
		if (dateFormat != null) {
		    xDateField.setPattern(dateFormat);
		}
		return new SwingCellEditor(xDateField);
		
	    } else if (TypeUtils.isTimeType(dataType)) {
		XTimeField xTimeField = new XTimeField();
		String timeFormat = format;
		if (timeFormat == null) {
		    timeFormat = UWTHelper.getTimeFormat(tableColumn);
		}
		if (timeFormat != null) {
		    xTimeField.setPattern(timeFormat);
		}		
		return new SwingCellEditor(xTimeField);
		
	    } else if (TypeUtils.isDateTimeType(dataType) || TypeUtils.isTimestampType(dataType)) {
		XDateTimeField xDateTimeField = new XDateTimeField();
		String dateTimeFormat = format;
		if (dateTimeFormat == null) {
		    dateTimeFormat = UWTHelper.getDateTimeFormat(tableColumn);
		}
		if (dateTimeFormat != null) {
		    xDateTimeField.setPattern(dateTimeFormat);
		}
		return new SwingCellEditor(xDateTimeField);
	    }



	    return null;
	}
	
	//TODO: Must implemented other native CellEditor by class of UWT CellEditor 
	return null;
    }
    

}

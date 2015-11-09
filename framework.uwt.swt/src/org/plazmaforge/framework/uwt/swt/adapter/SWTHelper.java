package org.plazmaforge.framework.uwt.swt.adapter;



import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTDateCellEditor;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTDateTimeCellEditor;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTNumberCellEditor;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTTextCellEditor;
import org.plazmaforge.framework.uwt.swt.adapter.viewer.SWTTimeCellEditor;
import org.plazmaforge.framework.uwt.swt.util.SWTUtils;
import org.plazmaforge.framework.uwt.util.StorageUtils;
import org.plazmaforge.framework.uwt.util.UWTHelper;
import org.plazmaforge.framework.uwt.widget.CellEditor;
import org.plazmaforge.framework.uwt.widget.DefaultCellEditor;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

public class SWTHelper {

    private SWTHelper() {
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
    
    public static org.eclipse.swt.graphics.Color getColor(Color color) {
  	return new org.eclipse.swt.graphics.Color(null, color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * Return SWT Font by UWT Font
     * @param font
     * @return
     */
    public static org.eclipse.swt.graphics.Font getFont(Font font) {
	
	// Convert font style from UWT to SWT
	int style = SWT.NORMAL;
	if ((font.getStyle() & Font.BOLD) != 0) {
	    style |= SWT.BOLD;
	}
	if ((font.getStyle() & Font.ITALIC) != 0) {
	    style |= SWT.ITALIC;
	}
	org.eclipse.swt.widgets.Display display = Display.getCurrent();
	int height = getFontHeight(display, font.getSize());
	org.eclipse.swt.graphics.FontData fontData = new org.eclipse.swt.graphics.FontData(font.getName(), height, style);
	org.eclipse.swt.graphics.Font xFont = new org.eclipse.swt.graphics.Font(Display.getCurrent(), fontData);
  	return xFont;
    }

    /**
     * Return height of font by Device DPI
     * @param device
     * @param size
     * @return
     */
    public static int getFontHeight(org.eclipse.swt.graphics.Device device, int size) {
	 return getFontHeight(device.getDPI().y, size);
    }

    /**
     * Return height of font by Device DPI
     * @param dpi
     * @param size
     * @return
     */
    public static int getFontHeight(int dpi, int size) {
	 int height = (int) Math.round(size * 72.0 / dpi);
	 return height;
    }

    public static org.eclipse.swt.graphics.Image createImage(UIObject element, Image image) {
	if (image == null) {
	    return null;
	}
	String path = image.getPath();
	return createImage(element, path);
    }

    public static org.eclipse.swt.graphics.Image createImage(UIObject element, String path) {
	if (path == null) {
	    return null;
	}
	String storage = StorageUtils.getImageStorage(element, path);
	return createImage(storage, path);
    }

    public static org.eclipse.swt.graphics.Image createImage(String storage, String path) {
	String fullPath = StorageUtils.getPath(storage, path);
	return createImage(fullPath);
    }

    public static org.eclipse.swt.graphics.Image createImage(String path) {
	// TODO: By default we get Image from classpath
	// Must analyze prefixes 'file:', 'classpath:'
	// - classpath:		getClassImage
	// - file: and other	getFileImage
	return path == null ? null : SWTUtils.getClassImage(path);
    }    
    

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // FACTORY
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static org.eclipse.jface.viewers.CellEditor createCellEditor(org.eclipse.swt.widgets.Table xTable, TableColumn tableColumn, CellEditor cellEditor) {
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
	    
	    // TODO: Only for String, Text, boolean data type
	    if (dataType == null || TypeUtils.isLikeStringType(dataType)) {
		return new SWTTextCellEditor(xTable);
		
	    } else if (TypeUtils.isBooleanType(dataType)) {
		return new org.eclipse.jface.viewers.CheckboxCellEditor(xTable);
		
	    } else if (TypeUtils.isLikeNumberType(dataType)) {
		SWTNumberCellEditor xCellEditor = new SWTNumberCellEditor(xTable);
		String numberFormat = format;
		if (numberFormat == null) {
		    numberFormat = UWTHelper.getNumberFormat(tableColumn);
		}
		if (numberFormat != null) {
		    xCellEditor.setPattern(numberFormat);
		}
		return xCellEditor;
		
	    } else if (TypeUtils.isDateType(dataType)) {
		SWTDateCellEditor xCellEditor = new SWTDateCellEditor(xTable);
		String dateFormat = format;
		if (dateFormat == null) {
		    dateFormat = UWTHelper.getDateFormat(tableColumn);
		}
		if (dateFormat != null) {
		    xCellEditor.setPattern(dateFormat);
		}
		return xCellEditor;
		
	    } else if (TypeUtils.isTimeType(dataType)) {
		SWTTimeCellEditor xCellEditor = new SWTTimeCellEditor(xTable);
		String timeFormat = format;
		if (timeFormat == null) {
		    timeFormat = UWTHelper.getTimeFormat(tableColumn);
		}
		if (timeFormat != null) {
		    xCellEditor.setPattern(timeFormat);
		}
		return xCellEditor;
		
		
	    } else if (TypeUtils.isDateTimeType(dataType) || TypeUtils.isTimestampType(dataType)) {
		SWTDateTimeCellEditor xCellEditor = new SWTDateTimeCellEditor(xTable);
		String dateTimeFormat = format;
		if (dateTimeFormat == null) {
		    dateTimeFormat = UWTHelper.getDateTimeFormat(tableColumn);
		}
		if (dateTimeFormat != null) {
		    xCellEditor.setPattern(dateTimeFormat);
		}
		return xCellEditor;
		
	    }

	}
	//TODO: Must implemented other native CellEditor by class of UWT CellEditor 
	return null;
    }

}

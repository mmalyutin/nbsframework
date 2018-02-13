package org.plazmaforge.framework.uwt.swt.adapter;



import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.uwt.UIElement;
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
    
    private static Map<String, org.eclipse.swt.graphics.Color> colorMap = new HashMap<String, org.eclipse.swt.graphics.Color>();
    
    private static Map<String, org.eclipse.swt.graphics.Font> fontMap = new HashMap<String, org.eclipse.swt.graphics.Font>(); 

    /**
     * Convert alpha form 0..1 to 0..255
     * @param alpha
     * @return
     */
    public static int get255ColorAlpha(float alpha) {
	return (int) (alpha * 255);
    }
    
    public static org.eclipse.swt.graphics.Color getColor(Color color) {
	if (color == null) {
	    return null;
	}
	String key = color.getKey();
	org.eclipse.swt.graphics.Color xColor = colorMap.get(key);
	if  (xColor != null) {
	    return xColor;
	}
	xColor = new org.eclipse.swt.graphics.Color(null, color.getRed(), color.getGreen(), color.getBlue());
	
	colorMap.put(key, xColor);
	
	return xColor;
    }

    /**
     * Return SWT Font by UWT Font
     * @param font
     * @return
     */
    public static org.eclipse.swt.graphics.Font getFont(Font font) {
	if (font == null) {
	    return null;
	}
	String key = font.getKey();
	org.eclipse.swt.graphics.Font xFont = fontMap.get(key);
	if (xFont != null) {
	    return xFont;
	}
	
	// Convert font style from UWT to SWT
	int style = SWT.NORMAL;
	if (font.isBold()) {
	    style |= SWT.BOLD;
	}
	if (font.isItalic()) {
	    style |= SWT.ITALIC;
	}
	
	org.eclipse.swt.widgets.Display display = Display.getCurrent();
	String name = font.getName();
	int height = getFontHeight(display, font.getSize());
	
	if (font.isIncomplete()) {
	    
	    org.eclipse.swt.graphics.Font defaultFont = getDefaultFont(display);
	    org.eclipse.swt.graphics.FontData defaultFontData[] = defaultFont.getFontData();
	    
	    if (font.isEmptyName()) {
		name = defaultFontData[0].getName();
	    }
	    if (font.isEmptySize()) {
		height = defaultFontData[0].getHeight();
	    }
	}
	
	org.eclipse.swt.graphics.FontData fontData = new org.eclipse.swt.graphics.FontData(name, height, style);
	xFont = new org.eclipse.swt.graphics.Font(Display.getCurrent(), fontData);
	
	fontMap.put(key, xFont);
	
  	return xFont;
    }

    public static org.eclipse.swt.graphics.Font getDefaultFont(org.eclipse.swt.widgets.Display display) {
	if (display == null) {
	    display = Display.getCurrent();
	}
	return display.getSystemFont();
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

    public static org.eclipse.swt.graphics.Image createImage(UIElement element, Image image) {
	if (image == null) {
	    return null;
	}
	String path = image.getPath();
	return createImage(element, path);
    }

    public static org.eclipse.swt.graphics.Image createImage(UIElement element, String path) {
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

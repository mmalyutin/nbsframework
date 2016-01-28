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

package org.plazmaforge.framework.datastorage.support.xls;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.plazmaforge.framework.core.data.converter.Converter;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.core.type.TypeUtils;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractPOIResultSet extends AbstractXLSResultSet {

    
    private Workbook workbook;

    private boolean initSheet;
    
    public AbstractPOIResultSet(InputStream inputStream) throws DSException {
	super(inputStream);
	this.workbook = loadWorkbook(inputStream);
    }

    public AbstractPOIResultSet(List<String> fieldNames, InputStream inputStream) throws DSException {
	super(fieldNames, inputStream);
	this.workbook = loadWorkbook(inputStream);
    }

    protected abstract Workbook loadWorkbook(InputStream inputStream) throws DSException;

    protected void initSheet() throws DSException {
	if (initSheet) {
	    return;
	}
	initSheet = true;
	sheetIndex = getSheetIndex(getSheetExpression());
    }

    @Override
    public void beforeFirst() throws DSException {
	super.beforeFirst();
	this.processing = false;
	this.initSheet = false;
    }
    
    @Override
    public boolean next() throws DSException {
	if (workbook == null) {
	    return false;
	}
	
	processing = true;

	// initialize sheetIndex before first record
	initSheet();
	
	//if (sheetIndex < 0) {
	//    sheetIndex = getSheetIndex(getSheetExpression());
	//}

	recordIndex++;

	if (getSheetExpression() == null) {
	    // Moving by sheets
	    // TODO: Maybe optional mode
	    if (recordIndex > workbook.getSheetAt(sheetIndex).getLastRowNum()) {
		if (sheetIndex + 1 < workbook.getNumberOfSheets() && workbook.getSheetAt(sheetIndex + 1).getLastRowNum() > 0) {
		    sheetIndex++;
		    recordIndex = -1;
		    return next();
		}
	    }
	}

	if ((getSheetExpression() != null || sheetIndex == 0) && isFirstRowHeader() && recordIndex == 0) {
	    // readHeader(); // TODO: OHA
	    recordIndex++;
	}
	if (recordIndex <= workbook.getSheetAt(sheetIndex).getLastRowNum()) {
	    return true;
	} else {
	    // TODO: Auto close: May be optional
	    // if (closeWorkbook) {
	    // FIXME: close workbook
	    // workbook.close();
	    // }
	}

	return false;
    }

    protected int getSheetIndex(String sheetExpression) throws DSException {
	if (sheetExpression == null) {
	    return 0;
	}
	int sheetIndex = -1;
	
	// Step 1: Try parse string as sheet index
	Integer parseIndex = parseIndex(sheetExpression);
	if (parseIndex != null) {
	    sheetIndex = parseIndex;
	    int startIndex = 0;
	    int endIndex = workbook.getNumberOfSheets() - 1;
	    if (sheetIndex < startIndex || sheetIndex > endIndex) {
		throw new DSException("Sheet index " + sheetIndex + " is out of range: [0.." + endIndex + "]");
	    }
	}
	
	try {
	    
	    sheetIndex = Integer.parseInt(sheetExpression);
	} catch (NumberFormatException e) {
	    // Ignore
	}
	
	// Step 2: Try use string as sheet name
	if (sheetIndex < 0) {
	    sheetIndex = getSheetIndexByName(sheetExpression);
	    if (sheetIndex < 0) {
		throw new DSException("Sheet '" + sheetExpression + "' not found in workbook.");
	    }
	}
	return sheetIndex;
    }
    
    /**
     * Return sheet index by name
     * @param sheetName
     * @return
     */
    protected int getSheetIndexByName(String sheetName) {
	if (sheetName == null) {
	    return -1;
	}
	Sheet sheet = workbook.getSheet(sheetName);
	if (sheet == null) {
	    return -1;
	}
	return workbook.getSheetIndex(sheet);
    }
    
    protected Integer parseIndex(String str) {
	return parseIndex(str, null);
    }
    
    protected Integer parseIndex(String str, Integer def) {
	if (str == null) {
	    return def;
	}
	str = str.trim();
	if (str.isEmpty()) {
	    return def;
	}
	Integer result = parseInteger(str);
	if (result == null) {
	    if (str.startsWith("[") && str.endsWith("]")) {
		if (str.length() > 2) {
		    str = str.substring(1, str.length() - 1);
		    result = parseInteger(str); // '[123]': index
		}
	    } else if (str.startsWith("(") && str.endsWith(")")) {
		if (str.length() > 2) {
		    str = str.substring(1, str.length() - 1);
		    result = parseInteger(str); // '(123)': position
		    if (result != null) {
			// Decrement because position start with 1 but index start with 0;
			result--;
		    }
		}
	    }
	} else {
	    // Decrement because number start with 1 but index start with 0;
	    result--;
	}
	if (result == null) {
	    result = def;
	}
	return result;
    }
    
    protected Integer parseInteger(String str) {
	if (str == null) {
	    return null;
	}
	try {
	    return Integer.parseInt(str);
	} catch (NumberFormatException e) {
	    // Ignore
	}
	return null;
    }
    
    //@Override
    public Object getValue(String name) throws DSException {
	return getNativeValue(name);
    }

    //@Override
    public Object getValue(int index) throws DSException {
	return getNativeValue(index);
    }
 
    
    //Native
    public Object getNativeValue(String name) throws DSException {
	int index = getFieldIndex(name);
	return getNativeValue(index);
    }

    //Native
    public Object getNativeValue(int index) throws DSException {
	return getNativeValue(index, null, null);
    }
    
    //Native
    public Object getNativeValue(int index, String type, String format) throws DSException {
	Sheet sheet = workbook.getSheetAt(sheetIndex);
	if (sheet == null) {
	    return null;
	}
	Row row = sheet.getRow(recordIndex);
	if (row == null) {
	    return null;
	}
	Cell cell = sheet.getRow(recordIndex).getCell(index);
	if (cell == null) {
	    return null;
	}
	Object value = getCellValue(cell, type, format);
	return value;
    }
    
    public Object getCellValue(Cell cell, String type, String format) throws DSException {
	if (cell == null) {
	    return null;
	}
	int cellType = cell.getCellType();
	if (cellType == Cell.CELL_TYPE_FORMULA) {
	    FormulaEvaluator evaluator = workbook.getCreationHelper() .createFormulaEvaluator();
	    cellType = evaluator.evaluateFormulaCell(cell);
	    return getCellValue(cell, cellType, type, format);
	}
	return getCellValue(cell, cellType, type, format);
    }
    
    public Object getCellValue(Cell cell, int cellType, String type, String format) throws DSException {
	Object value = null;
	String sourceType = null;
	String targetType = type;
	
	switch (cellType) {
	case Cell.CELL_TYPE_BOOLEAN: {
	    value = cell.getBooleanCellValue();
	    sourceType = "Boolean";
	    break;
	}
	case Cell.CELL_TYPE_NUMERIC: {
	    if (type != null && (TypeUtils.isLikeDateType(type) || TypeUtils.isLikeTimeType(type))) {
		sourceType = "Date";
		value = cell.getDateCellValue();
	    } else {
		sourceType = "Double";
		value = cell.getNumericCellValue();
	    }
	    break;
	}
	case Cell.CELL_TYPE_STRING: {
	    value = cell.getStringCellValue();
	    sourceType = "String";
	    break;
	}
	case Cell.CELL_TYPE_BLANK:
	case Cell.CELL_TYPE_ERROR:
	case Cell.CELL_TYPE_FORMULA:
	default:
	    break;
	}
	if (targetType == null) {
	    // No targetType - no converter
	    return value;
	}
	
	Converter converter = getConverter(sourceType, targetType, format);
	value = converter == null ? null : converter.convert(value);

	return value;
    }

}

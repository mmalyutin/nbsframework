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
package org.plazmaforge.framework.report.export;

import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.data.formatter.FormatterManager;
import org.plazmaforge.framework.core.data.formatter.STFormatterManager;
import org.plazmaforge.framework.report.model.base.grid.Cell;
import org.plazmaforge.framework.util.StringUtils;


/**
 * @author ohapon
 *
 */
public abstract class AbstractReportExporter implements ReportExporter {

    private FormatterManager formatterManager;
    
    private Map<String, Object> dataMap;

    public AbstractReportExporter() {
	dataMap = new HashMap<String, Object>();
    }

    @Override
    public void setData(String name, Object data) {
	dataMap.put(name, data);
	
    }

    @Override
    public Object getData(String name) {
	return dataMap.get(name);
    }

    
    protected String getOutputType() {
	return (String) dataMap.get(PROPERTY_OUTPUT_TYPE);
    }
    
    protected boolean isValidOutputType(String outputType) {
	if (outputType == null) {
	    return false;
	}
	return outputType.equals("fileName") || outputType.equals("file") || outputType.equals("stream") || outputType.equals("writer");  
    }
    
    protected boolean isFileNameOutputType(String outputType) {
	return (outputType == null ? false : outputType.equals("fileName"));
    }
    
    protected boolean isFileOutputType(String outputType) {
	return (outputType == null ? false : outputType.equals("file"));
    }

    protected String formatCellValue(Cell cell) {
	return formatValue(cell.getValue(), cell.getDataType(), cell.getFormat());
    }

    protected String formatValue(Object value, String dataType, String format) {
	return getFormatterManager().formatValue(value, dataType, format);
    }
    
    protected String normalizeString(String str) {
	return StringUtils.normalizeString(str);
    }

    protected FormatterManager getFormatterManager() {
	if (formatterManager == null) {
	    formatterManager = new STFormatterManager(true);
	    formatterManager.init();
	    
	}
        return formatterManager;
    }
    
    
}


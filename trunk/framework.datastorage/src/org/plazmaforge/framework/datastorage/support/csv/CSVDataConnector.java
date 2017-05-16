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

/**
 * 
 */
package org.plazmaforge.framework.datastorage.support.csv;

import org.plazmaforge.framework.core.Platform;
import org.plazmaforge.framework.core.datastorage.AbstractFileDataConnector;


/**
 * @author ohapon
 *
 */
public class CSVDataConnector extends AbstractFileDataConnector {

    public static final String TYPE = "CSV";
    
    public static final String PROPERTY_COLUMN_DELIMITER = "columnDelimiter";
    public static final String PROPERTY_ROW_DELIMITER = "rowDelimiter";
    public static final String PROPERTY_FIRST_ROW_HEADER = "firstRowHeader";
    
    public static final String DEFAULT_LINE_DELIMITER = Platform.DEFAULT_LINE_DELIMITER;
    public static final String DEFAULT_COLUMN_DELIMITER = Platform.DEFAULT_COLUMN_DELIMITER;
    public static final String DEFAULT_ROW_DELIMITER = Platform.DEFAULT_ROW_DELIMITER;
    public static final boolean DEFAULT_FIRST_ROW_HEADER = false;
    
    
    
    
    private String columnDelimiter = DEFAULT_COLUMN_DELIMITER;

    private String rowDelimiter = DEFAULT_ROW_DELIMITER;
   
    private boolean firstRowHeader = DEFAULT_FIRST_ROW_HEADER;


    public CSVDataConnector() {
	super();
    }
    
    @Override
    public String getType() {
	return TYPE;
    }

    public String getColumnDelimiter() {
        return columnDelimiter;
    }

    public void setColumnDelimiter(String columnDelimiter) {
        this.columnDelimiter = columnDelimiter;
    }

    public String getRowDelimiter() {
        return rowDelimiter;
    }

    public void setRowDelimiter(String rowDelimiter) {
        this.rowDelimiter = rowDelimiter;
    }

    public boolean isFirstRowHeader() {
        return firstRowHeader;
    }

    public void setFirstRowHeader(boolean firstRowHeader) {
        this.firstRowHeader = firstRowHeader;
    }

    public static String normalizeLineDelimiter(String lineDelimiter) {
	if (lineDelimiter == null) {
	    return lineDelimiter;
	}
	if (eqDelimiter(lineDelimiter, "CRLF")) {
	    return Platform.CRLF_DELIMITER;
	}
	if (eqDelimiter(lineDelimiter, "CR")) {
	    return Platform.CR_DELIMITER;
	}
	if (eqDelimiter(lineDelimiter, "LF")) {
	    return Platform.LF_DELIMITER;
	}
	if (eqDelimiter(lineDelimiter, "WIN") || eqDelimiter(lineDelimiter, "WINDOWS")) {
	    return Platform.WIN_LINE_DELIMITER;
	}
	if (eqDelimiter(lineDelimiter, "NIX") || eqDelimiter(lineDelimiter, "UNIX") || eqDelimiter(lineDelimiter, "LINUX")) {
	    return Platform.NIX_LINE_DELIMITER;
	}
	if (eqDelimiter(lineDelimiter, "MAC")) {
	    return Platform.MAC_LINE_DELIMITER;
	}
	return lineDelimiter;
    }
    
    private static boolean eqDelimiter(String value, String constant) {
	return ieq(value, "#" + constant);
    }
    
    private static boolean ieq(String value1, String value2) {
	if (value1 == null || value2 == null) {
	    return false;
	}
	return value1.equalsIgnoreCase(value2);
    }
    
    

}

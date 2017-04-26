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

import org.plazmaforge.framework.core.datastorage.AbstractFileDataConnector;
import org.plazmaforge.framework.util.SystemInfo;


/**
 * @author ohapon
 *
 */
public class CSVDataConnector extends AbstractFileDataConnector {

    public static final String TYPE = "CSV";
    
    public static final String PROPERTY_COLUMN_DELIMITER = "columnDelimiter";
    public static final String PROPERTY_ROW_DELIMITER = "rowDelimiter";
    public static final String PROPERTY_FIRST_ROW_HEADER = "firstRowHeader";
    

    // CARRIAGE RETURN	- '\r'
    // LINE FEED	- '\n'
    
    // CRLF		- '\r\n'
    // LF		- '\n'
    
    public static final String CRLF_DELIMITER = "\r\n";
    public static final String LF_DELIMITER = "\n";

    public static final String WIN_LINE_DELIMITER = CRLF_DELIMITER;	// Windows systems	'\r\n'
    public static final String NIX_LINE_DELIMITER = LF_DELIMITER;	// *nix systems		'\n'
    
    public static final String DEFAULT_LINE_DELIMITER = SystemInfo.isWindows ? WIN_LINE_DELIMITER : NIX_LINE_DELIMITER;
    public static final String DEFAULT_COLUMN_DELIMITER = ",";
    public static final String DEFAULT_ROW_DELIMITER = DEFAULT_LINE_DELIMITER; //"\\n";
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
        this.rowDelimiter = evaluateLineDelimiter(rowDelimiter);
    }

    public boolean isFirstRowHeader() {
        return firstRowHeader;
    }

    public void setFirstRowHeader(boolean firstRowHeader) {
        this.firstRowHeader = firstRowHeader;
    }

    
    public static String evaluateLineDelimiter(String lineDelimiter) {
	if (lineDelimiter == null) {
	    return lineDelimiter;
	}
	if (lineDelimiter.equalsIgnoreCase("CRLF")) {
	    return CRLF_DELIMITER;
	}
	if (lineDelimiter.equalsIgnoreCase("LF")) {
	    return LF_DELIMITER;
	}
	if (lineDelimiter.equalsIgnoreCase("WIN")) {
	    return WIN_LINE_DELIMITER;
	}
	if (lineDelimiter.equalsIgnoreCase("NIX")) {
	    return NIX_LINE_DELIMITER;
	}
	return lineDelimiter;
    }
    
    

}

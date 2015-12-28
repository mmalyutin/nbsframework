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

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.datastorage.AbstractFileResultSet;
import org.plazmaforge.framework.core.datastorage.DSIndexableResultSet;
import org.plazmaforge.framework.core.datastorage.DSStructuredResultSet;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.util.StringUtils;

/**
 * @author ohapon
 *
 */
public class CSVResultSet extends AbstractFileResultSet implements DSIndexableResultSet, DSStructuredResultSet {

    
    private List<String> values;

    private char buffer[] = new char[1024];
    private int position;
    private int bufSize;
    
    // Only for loading default columns
    private int startColumnIndex = 1;

    private String columnDelimiter;
    private String rowDelimiter;
    private boolean firstRowHeader;

    public CSVResultSet(List<String> fieldNames, Reader reader) {
	super(fieldNames);
	this.reader = reader;
	// loadCSVFields(fieldNames);
    }

    public CSVResultSet(Reader reader) {
	this(null, reader);
    }

    @Override
    public boolean next() throws DSException {
	try {
	    if (!processing) {
		if (isFirstRowHeader()) {

		    boolean hasRow = parseRow();
		    if (!hasRow) {
			processing = true;
			return false;
		    }

		    // ////////////////////////////////
		    if (hasFields()) {
			processing = true;
			return parseRow();
		    }
		    // ///////////////////////////////

		    // Ignore blank row at top
		    // TODO
		    //if (!isBlankRow()) {
			//loadHeaderColumns();

			processing = true;
		    //}

		} else {
		    processing = true;
		}

	    }

	    return parseRow();
	} catch (IOException e) {
	    throw new DSException(e);
	}
    }    

    @Override
    public Object getValue(String name) throws DSException {
	return getStringValue(name);
    }

    @Override
    public Object getValue(int index) throws DSException {
	return getStringValue(index);
    }

    //Native
    public String getStringValue(String name) throws DSException {
	int index = getFieldIndex(name);
	return getStringValue(index);
    }
    
    //Native
    public String getStringValue(int index) throws DSException {
	if (values == null || values.isEmpty() || index < 0 || index >= values.size()) {
	    return null;
	}
	return values.get(index);
    }
    
  

    ////
    
    private boolean parseRow() throws IOException {
   	int pos = 0;
   	int startFieldPos = 0;
   	boolean insideQuotes = false;
   	boolean hadQuotes = false;
   	boolean misplacedQuote = false;
   	char c;
   	values = new ArrayList<String>();

   	String row = getRow();
   	if (row == null)// || row.length() == 0)
   	    return false;
   	
   	char fieldDelimiterChar = getColumnDelimiterChar();
   	
   	while (pos < row.length()) {
   	    c = row.charAt(pos);

   	    if (c == '"') {
   		// already inside a text containing quotes
   		if (!insideQuotes) {
   		    if (!hadQuotes) {
   			insideQuotes = true;
   			hadQuotes = true;
   		    } else
   			// the field contains a bad string, like
   			// "fo"o", instead of "fo""o"
   			misplacedQuote = true;
   		}
   		// found a quote when already inside quotes, expecting two
   		// consecutive quotes, otherwise it means
   		// it's a closing quote
   		else {
   		    if (pos + 1 < row.length() && row.charAt(pos + 1) == '"')
   			pos++;
   		    else
   			insideQuotes = false;
   		}
   	    }
   	    // field delimiter found, copy the field contents to the field array
   	    if (c == fieldDelimiterChar && !insideQuotes) {
   		String field = row.substring(startFieldPos, pos);
   		// if an illegal quote was found, the entire field is considered
   		// illegal
   		if (misplacedQuote) {
   		    misplacedQuote = false;
   		    hadQuotes = false;
   		    field = "";
   		}
   		// if the field was between quotes, remove them and turn any
   		// escaped quotes inside the text into normal quotes
   		else if (hadQuotes) {
   		    field = field.trim();
   		    if (field.startsWith("\"") && field.endsWith("\"")) {
   			field = field.substring(1, field.length() - 1);
   			field = StringUtils.replaceAll(field, "\"\"", "\"");
   		    } else
   			field = "";
   		    hadQuotes = false;
   		}

   		values.add(field);
   		startFieldPos = pos + 1;
   	    }

   	    pos++;
   	    // if the record delimiter was found inside a quoted field, it is
   	    // not an actual record delimiter,
   	    // so another line should be read
   	    if ((pos == row.length()) && insideQuotes) {
   		row = row + getRowDelimiter() + getRow();
   	    }
   	}

   	// end of row was reached, so the final characters form the last field
   	// in the record
   	String field = row.substring(startFieldPos, pos);
   	if (field == null)
   	    return true;

   	if (misplacedQuote)
   	    field = "";
   	else if (hadQuotes) {
   	    field = field.trim();
   	    if (field.startsWith("\"") && field.endsWith("\"")) {
   		field = field.substring(1, field.length() - 1);
   		field = StringUtils.replaceAll(field, "\"\"", "\"");
   	    } else
   		field = "";
   	}
   	values.add(field);

   	return true;
       }

    /**
     * Reads a row from the stream. A row is a sequence of characters separated
     * by the record delimiter.
     */
    private String getRow() throws IOException {
	StringBuffer row = new StringBuffer();
	char c;
	String rowDelimiter = getRowDelimiter();
	while (true) {
	    try {
		c = getChar();

		// searches for the first character of the record delimiter
		if (c == rowDelimiter.charAt(0)) {
		    int i;
		    char[] temp = new char[rowDelimiter.length()];
		    temp[0] = c;
		    boolean isDelimiter = true;
		    // checks if the following characters in the stream form the
		    // record delimiter
		    for (i = 1; i < rowDelimiter.length() && isDelimiter; i++) {
			temp[i] = getChar();
			if (temp[i] != rowDelimiter.charAt(i))
			    isDelimiter = false;
		    }

		    if (isDelimiter) {
			return row.toString();
		    }

		    row.append(temp, 0, i);
		}

		row.append(c);
	    } catch (DSException e) {
		if (row.length() == 0) {
		    return null;
		} else {
		    return row.toString();
		}
	    }

	} // end while
    }

    /**
     * Reads a character from the stream.
     * 
     * @throws IOException if any I/O error occurs
     * @throws DSException  if end of stream has been reached
     */
    private char getChar() throws IOException, DSException {
	// end of buffer, fill a new buffer
	if (position + 1 > bufSize) {
	    bufSize = reader.read(buffer);
	    position = 0;
	    if (bufSize == -1)
		throw new DSException("No more chars");
	}

	return buffer[position++];
    }
       
     
    ////
       
    public String getColumnDelimiter() {
	if (columnDelimiter == null) {
	    return CSVDataConnector.DEFAULT_COLUMN_DELIMITER;
	}
	return columnDelimiter;
    }

    public void setColumnDelimiter(String columnDelimiter) {
	if (processing) {
	    handlePropertyModifyException();
	}
	this.columnDelimiter = columnDelimiter;
    }

    public char getColumnDelimiterChar() {
	String columnDelimiter = getColumnDelimiter();
	if (StringUtils.isEmpty(columnDelimiter)) {
	    return 0;
	}
	return columnDelimiter.charAt(0);
    }

    public String getRowDelimiter() {
	if (rowDelimiter == null) {
	    return CSVDataConnector.DEFAULT_ROW_DELIMITER;
	}
	return rowDelimiter;
    }

    public void setRowDelimiter(String rowDelimiter) {
	if (processing) {
	    handlePropertyModifyException();
	}
	this.rowDelimiter = rowDelimiter;
    }

    public boolean isFirstRowHeader() {
	return firstRowHeader;
    }

    public void setFirstRowHeader(boolean firstRowHeader) {
	if (processing) {
	    handlePropertyModifyException();
	}
	this.firstRowHeader = firstRowHeader;
    }

    
       
}

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

import org.plazmaforge.framework.core.datastorage.AbstractResultSet;
import org.plazmaforge.framework.core.datastorage.DSIndexableResultSet;
import org.plazmaforge.framework.core.datastorage.DSStructuredResultSet;
import org.plazmaforge.framework.core.exception.DSException;
import org.plazmaforge.framework.util.StringUtils;

/**
 * @author ohapon
 *
 */
public class CSVResultSet extends AbstractResultSet implements DSIndexableResultSet, DSStructuredResultSet {

    
    private Reader reader;
    
    private List<String> values;

    private char buffer[] = new char[1024];
    private int position;
    private int bufSize;
    
    // Only for loading default columns
    private int startColumnIndex = 1;

    
    private boolean processing;
    
    private String fieldDelimiter;
    private String recordDelimiter;
    private boolean includeHeader;

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
		if (isIncludeHeader()) {

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
	int index = getFieldIndex(name);
	return getValue(index);
    }

    @Override
    public Object getValue(int index) throws DSException {
	if (values == null || values.isEmpty() || index < 0 || index >= values.size()) {
	    return null;
	}
	return values.get(index);
    }
    
    @Override
    public boolean isEmpty() throws DSException {
	// TODO
	return reader == null;
    }

    @Override
    public boolean isClosed() throws DSException {
	return reader == null;
    }

    @Override
    public void close() throws DSException {
	if (reader == null) {
	    return;
	}
	try {
	    reader.close();
	    reader = null;
	} catch (IOException ex) {
	    handleIOException(ex);
	}
    }
    
    protected void handleIOException(IOException ex) throws DSException {
	if (ex == null) {
	    return;
	}
	throw new DSException(ex);
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
   	    if (c == getFieldDelimiterChar() && !insideQuotes) {
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
   		row = row + getRecordDelimiter() + getRow();
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

	while (true) {
	    try {
		c = getChar();

		// searches for the first character of the record delimiter
		if (c == getRecordDelimiter().charAt(0)) {
		    int i;
		    char[] temp = new char[getRecordDelimiter().length()];
		    temp[0] = c;
		    boolean isDelimiter = true;
		    // checks if the following characters in the stream form the
		    // record delimiter
		    for (i = 1; i < getRecordDelimiter().length()
			    && isDelimiter; i++) {
			temp[i] = getChar();
			if (temp[i] != getRecordDelimiter().charAt(i))
			    isDelimiter = false;
		    }

		    if (isDelimiter)
			return row.toString();

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
       
    public String getFieldDelimiter() {
	if (fieldDelimiter == null) {
	    return CSVDataConnector.DEFAULT_FIELD_DELIMITER;
	}
	return fieldDelimiter;
    }

    public void setFieldDelimiter(String filedDelimiter) {
	if (processing) {
	    handlePropertyModifyMessage();
	}
	this.fieldDelimiter = filedDelimiter;
    }

    public char getFieldDelimiterChar() {
	String filedDelimiter = getFieldDelimiter();
	if (StringUtils.isEmpty(filedDelimiter)) {
	    return 0;
	}
	return filedDelimiter.charAt(0);
    }

    public String getRecordDelimiter() {
	if (recordDelimiter == null) {
	    return CSVDataConnector.DEFAULT_RECORD_DELIMITER;
	}
	return recordDelimiter;
    }

    public void setRecordDelimiter(String recordDelimiter) {
	if (processing) {
	    handlePropertyModifyMessage();
	}
	this.recordDelimiter = recordDelimiter;
    }

    public boolean isIncludeHeader() {
	return includeHeader;
    }

    public void setIncludeHeader(boolean includeHeader) {
	if (processing) {
	    handlePropertyModifyMessage();
	}
	this.includeHeader = includeHeader;
    }

    protected void handlePropertyModifyMessage() {
	String message = "Cannot modify '" + getClass().getSimpleName()	+ "' properties after data processing has started";
	throw new RuntimeException(message);
    }
       
}

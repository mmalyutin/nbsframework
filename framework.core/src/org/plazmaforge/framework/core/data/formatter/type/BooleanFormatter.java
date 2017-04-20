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

package org.plazmaforge.framework.core.data.formatter.type;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.Platform;
import org.plazmaforge.framework.core.data.formatter.Formatter;
import org.plazmaforge.framework.util.StringUtils;

public class BooleanFormatter implements Formatter<Boolean> {

    
    public static final String DEFAULT_FORMAT = Platform.DEFAULT_BOOLEAN_FORMAT;
    
    public static final String[] FORMATS = Platform.DEFAULT_BOOLEAN_FORMATS;
    
    public static final String[] DEFAULT_VALUES;
    
    public static final String[][] ALL_DEFAULT_VALUES;
	
     
    
    /**
     * Boolean format
     */
    private String format;
    
    /**
     * Array of 'true' and 'false' presentation
     */
    private String[] values;
    
    /**
     * Ignore case flag for parsing string
     */
    private boolean ignoreCaseString;
    
    private boolean nullBooleanAsFalse;
    
    private boolean unknownBooleanAsFalse;
    
    static {
	List<String[]> list = new ArrayList<String[]>();
	String[] v = null;
	for (String f : FORMATS) {
	    v = parseFormat(f);
	    list.add(v);
	}
	ALL_DEFAULT_VALUES = (String[][]) list.toArray(new String[0][0]);
	DEFAULT_VALUES = ALL_DEFAULT_VALUES[0];
    }
    
    public BooleanFormatter() {
	this(null, true, false, false);
    }

    public BooleanFormatter(String format) {
	this(format, true, false, false);
    }
	
    public BooleanFormatter(String format, boolean ignoreCaseString) {
	this(format, ignoreCaseString, false, false);
    }
    
    public BooleanFormatter(String format, boolean ignoreCaseString, boolean nullBooleanAsFalse, boolean unknownBooleanAsFalse) {
	super();
	this.format = format;
	this.values = format == null ? null : parseFormat(format, true);
	this.ignoreCaseString = ignoreCaseString;
	this.nullBooleanAsFalse = nullBooleanAsFalse;
	this.unknownBooleanAsFalse = unknownBooleanAsFalse;
    }

    public String getFormat() {
        return format;
    }

    public boolean isIgnoreCaseString() {
        return ignoreCaseString;
    }

    public boolean isNullBooleanAsFalse() {
        return nullBooleanAsFalse;
    }

    public boolean isUnknownBooleanAsFalse() {
        return unknownBooleanAsFalse;
    }

    @Override
    public String format(Boolean value) {
	return formatBoolean(value);
    }

    @Override
    public Boolean parse(String str) {
	return parseBoolean(str);
    }

    protected String formatBoolean(Boolean value) {
	if (value == null) {
	    return null;
	    
	}
	String[] values = this.values == null ? DEFAULT_VALUES : this.values;
	return value == null ? null : (value ? values[0] : values[1]);
    }

    protected Boolean parseBoolean(String str) {
	if (str == null) {
	    return nullBooleanValue();		// NULL
	}
	if (values != null) {
	    if (equalsBooleanStrings(str, values[0])) {
		return Boolean.TRUE;		 // TRUE
	    }
	    if (equalsBooleanStrings(str, values[1])) {
		return Boolean.FALSE;		 // FALSE
	    }
	} else {
	    for (String[] values : ALL_DEFAULT_VALUES) {
		if (equalsBooleanStrings(str, values[0])) {
		    return Boolean.TRUE;	// TRUE
		}
		if (equalsBooleanStrings(str, values[1])) {
		    return Boolean.FALSE;	// FALSE
		}
	    }
	}
	return unknownBooleanValue();		// UNKNOWN
    }
    
    protected Boolean nullBooleanValue() {
	if (isNullBooleanAsFalse()) {
	    return Boolean.FALSE;
	}
	return null;
    }

    protected Boolean unknownBooleanValue() {
	if (isUnknownBooleanAsFalse()) {
	    return Boolean.FALSE;
	}
	return nullBooleanValue();
    }
    
    protected boolean equalsBooleanStrings(String str1, String str2) {
	return equalsBooleanStrings(str1, str2, isIgnoreCaseString());
    }
    
    public static boolean equalsBooleanStrings(String str1, String str2, boolean ignoreCaseString) {
	if (str1 == null || str2 == null) {
	    return false;
	}
	return ignoreCaseString ? str1.equalsIgnoreCase(str2) : str1.equals(str2);
    }
    
    
    /**
     * Parse format boolean format
     * and return array with 'true' and 'false' presentation
     * If 'def' is true return DEFAULT_VALUES
     * 
     * @param format
     * @param def
     * @return
     */
    public static String[] parseFormat(String format, boolean def) {
	String[] values = parseFormat(format);
	if (values == null && def) {
	    values = DEFAULT_VALUES;
	}
	return values;
    }
    
    /**
     * Parse format boolean format 
     * and return array with 'true' and 'false' presentation
     * 
     * @param format
     * @return
     */
    public static String[] parseFormat(String format) {
	if (format == null) {
	    return null;
	}
	format = StringUtils.normalizeString(format);
	if (format == null) {
	    return null;
	}
	String values[] = format.split("\\|");
	if (values == null || values.length != 2) {
	    return null;
	}
	
	values[0] = StringUtils.normalizeString(values[0]);
	values[1] = StringUtils.normalizeString(values[1]);
	if (values[0] == null || values[1] == null) {
	    return null;
	}
	return values;
    }
    
}

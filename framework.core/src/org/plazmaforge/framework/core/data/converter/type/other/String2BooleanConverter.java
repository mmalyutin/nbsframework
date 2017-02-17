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

package org.plazmaforge.framework.core.data.converter.type.other;

import org.plazmaforge.framework.core.data.converter.Converter;
import org.plazmaforge.framework.core.data.formatter.type.BooleanFormatter;

public class String2BooleanConverter implements Converter<String, Boolean> {


    private String format;
    
    private String[] values;
    
    /**
     * Ignore case flag for parsing string
     */
    private boolean ignoreCaseString;
    
    private boolean nullBooleanAsFalse;
    
    private boolean unknownBooleanAsFalse;

    
    public String2BooleanConverter() {
	this(null, true, false, false);
    }

    public String2BooleanConverter(String format) {
	this(format, true, false, false);
    }

    public String2BooleanConverter(String format, boolean ignoreCaseString) {
	this(format, ignoreCaseString, false, false);
    }

    public String2BooleanConverter(String format, boolean ignoreCaseString, boolean nullBooleanAsFalse, boolean unknownBooleanAsFalse) {
	super();
	this.format = format;
	this.values = format == null ? null : BooleanFormatter.parseFormat(format, true);
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
    public Boolean convert(String source) {
	if (source == null) {
	    return nullBooleanValue();		// NULL
	}
	if (values != null) {
	    if (equalsBooleanStrings(source, values[0])) {
		return Boolean.TRUE;		// TRUE
	    }
	    if (equalsBooleanStrings(source, values[1])) {
		return Boolean.FALSE;		// FALSE
	    }
	} else {
	    for (String[] value: BooleanFormatter.ALL_DEFAULT_VALUES) {
		if (equalsBooleanStrings(source, value[0])) {
		    return Boolean.TRUE;	// TRUE
		}
		if (equalsBooleanStrings(source, value[1])) {
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
	return BooleanFormatter.equalsBooleanStrings(str1, str2, isIgnoreCaseString());
    }


}

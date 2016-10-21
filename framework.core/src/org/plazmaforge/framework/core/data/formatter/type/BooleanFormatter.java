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

import org.plazmaforge.framework.core.data.formatter.Formatter;
import org.plazmaforge.framework.util.StringUtils;

public class BooleanFormatter implements Formatter<Boolean> {

    public static String[] FORMATS = new String[] {"true|false", "yes|no" ,"t|f", "y|n", "TRUE|FALSE", "YES|NO" ,"T|F", "Y|N"}; 
	    
    private String format;
    
    private String[] values;
    
    public BooleanFormatter() {
	super();
    }

    public BooleanFormatter(String format) {
	super();
	this.format = format;
	this.values = createValues(format);
    }

    public String getFormat() {
        return format;
    }

    @Override
    public String format(Boolean value) {
	return formatBoolean(value);
    }

    @Override
    public Boolean parse(String str) {
	return null;
    }

    protected String formatBoolean(Boolean value) {
	return value == null ? null : (value ? values[0] : values[1]);
    }

    protected Boolean parseBoolean(String str) {
	return str == null ? null : str.equals(values[0]);
    }
    
    /**
     * Create true|false values by format
     * 
     * @param format
     * @return
     */
    protected String[] createValues(String format) {
	String[] values = parseFormat(format);
	return values == null ? new String[] {"true", "false"} : values;
    }
    
    /**
     * Parse format
     * 
     * @param format
     * @return
     */
    protected String[] parseFormat(String format) {
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

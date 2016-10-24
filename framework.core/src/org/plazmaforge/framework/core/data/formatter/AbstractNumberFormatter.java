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

package org.plazmaforge.framework.core.data.formatter;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.plazmaforge.framework.util.NumberUtils;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractNumberFormatter<T extends Number> implements Formatter<T> {

    private String format;

    private NumberFormat formatter;
    
    
    public AbstractNumberFormatter() {
	super();
    }

    public AbstractNumberFormatter(String format) {
	super();
	this.format = format;
	this.formatter = createFormatter(format);
    }

    protected NumberFormat createFormatter(String format) {
	return format == null ? null : new DecimalFormat(format);
    }

    protected NumberFormat getFormatter() {
	return formatter;
    }      

    protected abstract Class<T> getType();
    
    @Override
    public String format(T value) {
	return NumberUtils.formatNumber(value, formatter);
    }

    @Override
    public T parse(String str) {
	return NumberUtils.parseNumber(str, getType(), formatter);
    }

}

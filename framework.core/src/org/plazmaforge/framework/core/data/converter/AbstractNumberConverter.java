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

package org.plazmaforge.framework.core.data.converter;

import java.text.NumberFormat;
import org.plazmaforge.framework.util.NumberUtils;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractNumberConverter<S, T> implements Converter<S, T> {

   
    
    public AbstractNumberConverter() {
	super();
    }


    //Parse
    protected <T extends Number> T parseNumber(String source, Class<T> type, NumberFormat formatter) {
	return parseNumber(source, type, formatter, false);
    }
    
    protected <T extends Number> T parseNumber(String source, Class<T> type, NumberFormat formatter, boolean checkOverflow) {
	return NumberUtils.parseNumber(source, type, formatter, checkOverflow);
    }
    
    //Convert
    protected <T extends Number> T convertNumber(Number source, Class<T> type) {
	return NumberUtils.convertNumber(source, type, false);
    }

    protected <T extends Number> T convertNumber(Number source,  Class<T> type, boolean checkOverflow) {
	return NumberUtils.convertNumber(source, type, checkOverflow);
    }

    //Format
    protected String formatNumber(Number value, NumberFormat formatter) {
	return NumberUtils.formatNumber(value, formatter);
    }

}

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

package org.plazmaforge.framework.core.data.converter.type.number;


import org.plazmaforge.framework.core.data.converter.AbstractString2NumberConverter;

/**
 * 
 * @author ohapon
 *
 */
public class String2ShortConverter extends AbstractString2NumberConverter<Short> {

    
    public String2ShortConverter() {
	super();
    }

    public String2ShortConverter(String format) {
	super(format);
    }

    @Override
    public Short convert(String source) {
	return parseNumber(source, Short.class, getFormatter());
    }

}

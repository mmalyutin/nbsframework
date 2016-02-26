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

package org.plazmaforge.framework.core.data.converter.type.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.plazmaforge.framework.core.data.converter.AbstractString2DateConverter;
import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * @author ohapon
 *
 */
public class String2TimeConverter extends AbstractString2DateConverter<Date> {

    
    public String2TimeConverter() {
	super();
    }

    public String2TimeConverter(String format) {
	super(format);
    }

    @Override
    public Date convert(String source) {
	return parseDate(source, getFormatter());
    }
    
    protected DateFormat createFormatter(String format) {
  	return new SimpleDateFormat(StringUtils.isEmpty(format, true) ? DEFAULT_TIME_FORMAT : format);
      }
    

}

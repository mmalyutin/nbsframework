/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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

import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.type.Types;
import org.plazmaforge.framework.core.data.formatter.type.ByteFormatter;
import org.plazmaforge.framework.core.data.formatter.type.DateFormatter;
import org.plazmaforge.framework.core.data.formatter.type.DateTimeFormatter;
import org.plazmaforge.framework.core.data.formatter.type.DoubleFormatter;
import org.plazmaforge.framework.core.data.formatter.type.FloatFormatter;
import org.plazmaforge.framework.core.data.formatter.type.IntegerFormatter;
import org.plazmaforge.framework.core.data.formatter.type.ShortFormatter;
import org.plazmaforge.framework.core.data.formatter.type.StringFormatter;
import org.plazmaforge.framework.core.data.formatter.type.TimeFormatter;

/**
 * Formatter Factory
 * 
 * @author ohapon
 *
 */
public class FormatterFactory {


    private Map<String, Formatter> formatters = new HashMap<String, Formatter>();
    
    public FormatterFactory() {
	super();
    }

    public void registerFormatter(String type, Formatter formatter) {
	formatters.put(type, formatter);
    }

    public void unregisterFormatter(String type) {
	formatters.remove(type);
    }
    
    public void registerDefaultFormatters() {
	registerFormatter(Types.StringType, new StringFormatter());
	registerFormatter(Types.TextType, new StringFormatter());
	registerFormatter(Types.BooleanType, new ByteFormatter());
	registerFormatter(Types.ShortType, new ShortFormatter());
	registerFormatter(Types.IntegerType, new IntegerFormatter());
	registerFormatter(Types.FloatType, new FloatFormatter());
	registerFormatter(Types.DoubleType, new DoubleFormatter());
	registerFormatter(Types.DateType, new DateFormatter());
	registerFormatter(Types.TimeType, new TimeFormatter());
	registerFormatter(Types.DateTimeType, new DateTimeFormatter());
    }
    
    
    public Formatter getFormatter(String type) {
	return getFormatter(type, null);
    }

    public Formatter getFormatter(String type, String format) {
	
	/*
	if (type == null) {
	    return null;
	}
	
	if (TypeUtils.isLikeStringType(type)) {
	    return new StringPresenter();
	} else if (Types.IntegerType.equals(type)) {
	    return new IntegerPresenter();
	} else if (Types.FloatType.equals(type)) {
	    return new FloatPresenter();
	} else if (Types.DoubleType.equals(type)) {
	    return new DoublePresenter();
	}
	return null;
	*/
	
	return doGetFormatter(type);
    }
    
    protected Formatter doGetFormatter(String type) {
	return formatters.get(type);
    }
    
    

}

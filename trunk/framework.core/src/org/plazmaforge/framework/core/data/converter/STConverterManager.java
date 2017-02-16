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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.plazmaforge.framework.core.data.converter.type.date.Date2DateTimeConverter;
import org.plazmaforge.framework.core.data.converter.type.date.Date2DateTimeConverterFactory;
import org.plazmaforge.framework.core.data.converter.type.date.Date2TimeConverter;
import org.plazmaforge.framework.core.data.converter.type.date.Date2TimeConverterFactory;
import org.plazmaforge.framework.core.data.converter.type.date.String2DateTimeConverter;
import org.plazmaforge.framework.core.data.converter.type.date.String2DateTimeConverterFactory;
import org.plazmaforge.framework.core.data.converter.type.date.String2TimeConverter;
import org.plazmaforge.framework.core.data.converter.type.date.String2TimeConverterFactory;
import org.plazmaforge.framework.core.data.converter.type.other.Boolean2StringConverter;
import org.plazmaforge.framework.core.data.converter.type.other.Boolean2StringConverterFactory;
import org.plazmaforge.framework.core.data.converter.type.other.String2BooleanConverter;
import org.plazmaforge.framework.core.data.converter.type.other.String2BooleanConverterFactory;

/**
 * 
 * @author ohapon
 *
 */
public class STConverterManager extends GenericConverterManager {

    public STConverterManager() {
	super();
    }

    public STConverterManager(boolean cacheMode) {
	super(cacheMode);
    }

    @Override
    public void init() {
	super.init();
	registerConveretrFactories();
    }
      
    
    protected void registerConveretrFactories() {
	
	//////////////////////////////////////////////////////////////////////////////////
	// Package: number
	//////////////////////////////////////////////////////////////////////////////////
	
	// String -> <Number>
	registerGenericConveretrFactory(".number", String.class, Byte.class);
	registerGenericConveretrFactory(".number", String.class, Short.class);
	registerGenericConveretrFactory(".number", String.class, Integer.class);
	registerGenericConveretrFactory(".number", String.class, Long.class);
	registerGenericConveretrFactory(".number", String.class, Float.class);
	registerGenericConveretrFactory(".number", String.class, Double.class);
	registerGenericConveretrFactory(".number", String.class, BigInteger.class);
	registerGenericConveretrFactory(".number", String.class, BigDecimal.class);

	
	//////////////////////////////////////////////////////////////////////////////////
	// <Number> -> <Number>, String
	//////////////////////////////////////////////////////////////////////////////////
	
	// Byte
	registerGenericConveretrFactory(".number", Byte.class, Short.class);
	registerGenericConveretrFactory(".number", Byte.class, Integer.class);
	registerGenericConveretrFactory(".number", Byte.class, Long.class);
	registerGenericConveretrFactory(".number", Byte.class, Float.class);
	registerGenericConveretrFactory(".number", Byte.class, Double.class);
	registerGenericConveretrFactory(".number", Byte.class, BigInteger.class);
	registerGenericConveretrFactory(".number", Byte.class, BigDecimal.class);
	registerGenericConveretrFactory(".number", Byte.class, String.class);

	// Short
	registerGenericConveretrFactory(".number", Short.class, Byte.class);
	registerGenericConveretrFactory(".number", Short.class, Integer.class);
	registerGenericConveretrFactory(".number", Short.class, Long.class);
	registerGenericConveretrFactory(".number", Short.class, Float.class);
	registerGenericConveretrFactory(".number", Short.class, Double.class);
	registerGenericConveretrFactory(".number", Short.class, BigInteger.class);
	registerGenericConveretrFactory(".number", Short.class, BigDecimal.class);
	registerGenericConveretrFactory(".number", Short.class, String.class);

	// Integer
	registerGenericConveretrFactory(".number", Integer.class, Byte.class);
	registerGenericConveretrFactory(".number", Integer.class, Short.class);
	registerGenericConveretrFactory(".number", Integer.class, Long.class);
	registerGenericConveretrFactory(".number", Integer.class, Float.class);
	registerGenericConveretrFactory(".number", Integer.class, Double.class);
	registerGenericConveretrFactory(".number", Integer.class, BigInteger.class);
	registerGenericConveretrFactory(".number", Integer.class, BigDecimal.class);
	registerGenericConveretrFactory(".number", Integer.class, String.class);

	// Float
	registerGenericConveretrFactory(".number", Float.class, Byte.class);
	registerGenericConveretrFactory(".number", Float.class, Short.class);
	registerGenericConveretrFactory(".number", Float.class, Integer.class);
	registerGenericConveretrFactory(".number", Float.class, Long.class);
	registerGenericConveretrFactory(".number", Float.class, Double.class);
	registerGenericConveretrFactory(".number", Float.class, BigInteger.class);
	registerGenericConveretrFactory(".number", Float.class, BigDecimal.class);
	registerGenericConveretrFactory(".number", Float.class, String.class);

	// Double
	registerGenericConveretrFactory(".number", Double.class, Byte.class);
	registerGenericConveretrFactory(".number", Double.class, Short.class);
	registerGenericConveretrFactory(".number", Double.class, Integer.class);
	registerGenericConveretrFactory(".number", Double.class, Long.class);
	registerGenericConveretrFactory(".number", Double.class, Float.class);
	registerGenericConveretrFactory(".number", Double.class, BigInteger.class);
	registerGenericConveretrFactory(".number", Double.class, BigDecimal.class);
	registerGenericConveretrFactory(".number", Double.class, String.class);

	// BigInteger
	registerGenericConveretrFactory(".number", BigInteger.class, Byte.class);
	registerGenericConveretrFactory(".number", BigInteger.class, Short.class);
	registerGenericConveretrFactory(".number", BigInteger.class, Integer.class);
	registerGenericConveretrFactory(".number", BigInteger.class, Long.class);
	registerGenericConveretrFactory(".number", BigInteger.class, Float.class);
	registerGenericConveretrFactory(".number", BigInteger.class, Double.class);
	registerGenericConveretrFactory(".number", BigInteger.class, BigDecimal.class);

	// BigDecimal
	registerGenericConveretrFactory(".number", BigDecimal.class, Byte.class);
	registerGenericConveretrFactory(".number", BigDecimal.class, Short.class);
	registerGenericConveretrFactory(".number", BigDecimal.class, Integer.class);
	registerGenericConveretrFactory(".number", BigDecimal.class, Long.class);
	registerGenericConveretrFactory(".number", BigDecimal.class, Float.class);
	registerGenericConveretrFactory(".number", BigDecimal.class, Double.class);
	registerGenericConveretrFactory(".number", BigDecimal.class, BigInteger.class);
	
	
	//////////////////////////////////////////////////////////////////////////////////
	// Package: date
	//////////////////////////////////////////////////////////////////////////////////
	
	// String -> <Date representation> - real type class
	registerGenericConveretrFactory(".date", String.class, Date.class);

	// String -> <Date representation> - virtual type class
	registerConveretrFactory(String2TimeConverter.class.getSimpleName(), new String2TimeConverterFactory());
	registerConveretrFactory(String2DateTimeConverter.class.getSimpleName(), new String2DateTimeConverterFactory());
	
	registerConveretrFactory(Date2DateTimeConverter.class.getSimpleName(), new Date2DateTimeConverterFactory());
	registerConveretrFactory(Date2TimeConverter.class.getSimpleName(), new Date2TimeConverterFactory());
	
	// String -> Boolean, Boolean -> String
	registerConveretrFactory(String2BooleanConverter.class.getSimpleName(), new String2BooleanConverterFactory());
	registerConveretrFactory(Boolean2StringConverter.class.getSimpleName(), new Boolean2StringConverterFactory());
	
	// Self
	registerSelfConveretrFactory(String.class);
	
	registerSelfConveretrFactory(Byte.class);
	registerSelfConveretrFactory(Short.class);
	registerSelfConveretrFactory(Integer.class);
	registerSelfConveretrFactory(Long.class);
	registerSelfConveretrFactory(Float.class);
	registerSelfConveretrFactory(Double.class);
	registerSelfConveretrFactory(BigInteger.class);
	registerSelfConveretrFactory(BigDecimal.class);
	
	registerSelfConveretrFactory(Date.class);
	
    }
    
}

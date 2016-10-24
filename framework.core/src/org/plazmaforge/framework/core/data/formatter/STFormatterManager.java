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

import java.util.Date;

import org.plazmaforge.framework.core.data.formatter.type.BooleanFormatter;
import org.plazmaforge.framework.core.data.formatter.type.ByteFormatter;
import org.plazmaforge.framework.core.data.formatter.type.DateFormatter;
import org.plazmaforge.framework.core.data.formatter.type.DateTimeFormatter;
import org.plazmaforge.framework.core.data.formatter.type.DoubleFormatter;
import org.plazmaforge.framework.core.data.formatter.type.FloatFormatter;
import org.plazmaforge.framework.core.data.formatter.type.IntegerFormatter;
import org.plazmaforge.framework.core.data.formatter.type.ShortFormatter;
import org.plazmaforge.framework.core.data.formatter.type.StringFormatter;
//import org.plazmaforge.framework.core.data.formatter.type.StringFormatter;
import org.plazmaforge.framework.core.data.formatter.type.TimeFormatter;
import org.plazmaforge.framework.core.type.Types;

/**
 * Standard FormatterManager
 * 
 * @author ohapon
 *
 */
public class STFormatterManager extends FormatterManager {

    @Override
    public void init() {
	registerFormatterFactories();
    }
    
    protected void registerFormatterFactories() {
	registerFormatterFactory(Types.StringType, new StringFormatterFactory());
	registerFormatterFactory(Types.TextType, new StringFormatterFactory());
	registerFormatterFactory(Types.BooleanType, new BooleanFormatterFactory());
	registerFormatterFactory(Types.ByteType, new ByteFormatterFactory());
	registerFormatterFactory(Types.ShortType, new ShortFormatterFactory());
	registerFormatterFactory(Types.IntegerType, new IntegerFormatterFactory());
	registerFormatterFactory(Types.FloatType, new FloatFormatterFactory());
	registerFormatterFactory(Types.DoubleType, new DoubleFormatterFactory());
	registerFormatterFactory(Types.DateType, new DateFormatterFactory());
	registerFormatterFactory(Types.TimeType, new TimeFormatterFactory());
	registerFormatterFactory(Types.DateTimeType, new DateTimeFormatterFactory());
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private static class StringFormatterFactory extends AbstractFormatterFactory<String> {

	@Override
	public Formatter<String> getFormatter() {
	    return new StringFormatter();
	}

    }
    
    private static class BooleanFormatterFactory extends AbstractFormatterFactory<Boolean> {

  	@Override
  	public Formatter<Boolean> getFormatter() {
  	    return new BooleanFormatter();
  	}
  	
    }    

    private static class ByteFormatterFactory extends AbstractFormatterFactory<Byte> {

  	@Override
  	public Formatter<Byte> getFormatter() {
  	    return new ByteFormatter();
  	}
  	
    }    

    private static class ShortFormatterFactory extends AbstractFormatterFactory<Short> {

  	@Override
  	public Formatter<Short> getFormatter() {
  	    return new ShortFormatter();
  	}
  	
    }    

    private static class IntegerFormatterFactory extends AbstractFormatterFactory<Integer> {

  	@Override
  	public Formatter<Integer> getFormatter() {
  	    return new IntegerFormatter();
  	}
  	
    }    

    private static class FloatFormatterFactory extends AbstractFormatterFactory<Float> {

  	@Override
  	public Formatter<Float> getFormatter() {
  	    return new FloatFormatter();
  	}
  	
    }    

    private static class DoubleFormatterFactory extends AbstractFormatterFactory<Double> {

  	@Override
  	public Formatter<Double> getFormatter() {
  	    return new DoubleFormatter();
  	}
  	
    }    

    private static class DateFormatterFactory extends AbstractFormatterFactory<Date> {

  	@Override
  	public Formatter<Date> getFormatter() {
  	    return new DateFormatter();
  	}
  	
    }    

    private static class TimeFormatterFactory extends AbstractFormatterFactory<Date> {

  	@Override
  	public Formatter<Date> getFormatter() {
  	    return new TimeFormatter();
  	}
  	
    }    

    private static class DateTimeFormatterFactory extends AbstractFormatterFactory<Date> {

  	@Override
  	public Formatter<Date> getFormatter() {
  	    return new DateTimeFormatter();
  	}
  	
    }    
}

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

import org.plazmaforge.framework.core.data.formatter.type.RWBooleanFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWByteFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWDateFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWDateTimeFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWDoubleFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWFloatFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWIntegerFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWShortFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWStringFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWTimeFormatter;
import org.plazmaforge.framework.core.type.Types;

/**
 * 
 * Read/Write FormatterManager
 * 
 * 
 * @author ohapon
 *
 */
public class RWFormatterManager extends FormatterManager {

    @Override
    public void init() {
	registerFormatterFactories();
    }
    
    protected void registerFormatterFactories() {
	registerFormatterFactory(Types.StringType, new RWStringFormatterFactory());
	registerFormatterFactory(Types.TextType, new RWStringFormatterFactory());
	registerFormatterFactory(Types.BooleanType, new RWBooleanFormatterFactory());
	registerFormatterFactory(Types.ByteType, new RWByteFormatterFactory());
	registerFormatterFactory(Types.ShortType, new RWShortFormatterFactory());
	registerFormatterFactory(Types.IntegerType, new RWIntegerFormatterFactory());
	registerFormatterFactory(Types.FloatType, new RWFloatFormatterFactory());
	registerFormatterFactory(Types.DoubleType, new RWDoubleFormatterFactory());
	registerFormatterFactory(Types.DateType, new RWDateFormatterFactory());
	registerFormatterFactory(Types.TimeType, new RWTimeFormatterFactory());
	registerFormatterFactory(Types.DateTimeType, new RWDateTimeFormatterFactory());
    }
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private static class RWStringFormatterFactory extends AbstractFormatterFactory<String> {

	@Override
	public Formatter<String> getFormatter() {
	    return new RWStringFormatter();
	}

    }
    
    private static class RWBooleanFormatterFactory extends AbstractFormatterFactory<Boolean> {

  	@Override
  	public Formatter<Boolean> getFormatter() {
  	    return new RWBooleanFormatter();
  	}
  	
    }    

    private static class RWByteFormatterFactory extends AbstractFormatterFactory<Byte> {

  	@Override
  	public Formatter<Byte> getFormatter() {
  	    return new RWByteFormatter();
  	}
  	
    }    

    private static class RWShortFormatterFactory extends AbstractFormatterFactory<Short> {

  	@Override
  	public Formatter<Short> getFormatter() {
  	    return new RWShortFormatter();
  	}
  	
    }    

    private static class RWIntegerFormatterFactory extends AbstractFormatterFactory<Integer> {

  	@Override
  	public Formatter<Integer> getFormatter() {
  	    return new RWIntegerFormatter();
  	}
  	
    }    

    private static class RWFloatFormatterFactory extends AbstractFormatterFactory<Float> {

  	@Override
  	public Formatter<Float> getFormatter() {
  	    return new RWFloatFormatter();
  	}
  	
    }    

    private static class RWDoubleFormatterFactory extends AbstractFormatterFactory<Double> {

  	@Override
  	public Formatter<Double> getFormatter() {
  	    return new RWDoubleFormatter();
  	}
  	
    }    

    private static class RWDateFormatterFactory extends AbstractFormatterFactory<Date> {

  	@Override
  	public Formatter<Date> getFormatter() {
  	    return new RWDateFormatter();
  	}
  	
    }    

    private static class RWTimeFormatterFactory extends AbstractFormatterFactory<Date> {

  	@Override
  	public Formatter<Date> getFormatter() {
  	    return new RWTimeFormatter();
  	}
  	
    }    

    private static class RWDateTimeFormatterFactory extends AbstractFormatterFactory<Date> {

  	@Override
  	public Formatter<Date> getFormatter() {
  	    return new RWDateTimeFormatter();
  	}
  	
    }    
    
}

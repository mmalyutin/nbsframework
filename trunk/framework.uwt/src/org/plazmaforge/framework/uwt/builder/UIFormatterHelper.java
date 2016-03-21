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

package org.plazmaforge.framework.uwt.builder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.data.formatter.Formatter;
import org.plazmaforge.framework.core.data.formatter.type.BooleanFormatter;
import org.plazmaforge.framework.core.data.formatter.type.ByteFormatter;
import org.plazmaforge.framework.core.data.formatter.type.DateFormatter;
import org.plazmaforge.framework.core.data.formatter.type.DateTimeFormatter;
import org.plazmaforge.framework.core.data.formatter.type.DoubleFormatter;
import org.plazmaforge.framework.core.data.formatter.type.FloatFormatter;
import org.plazmaforge.framework.core.data.formatter.type.IntegerFormatter;
import org.plazmaforge.framework.core.data.formatter.type.ShortFormatter;
import org.plazmaforge.framework.core.data.formatter.type.TimeFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.ColorFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.DirectionFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.FontFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.HorizontalAlignFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.LayoutRegionFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.OrientationFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.VerticalAlignFormatter;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Style.Direction;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.LayoutRegion;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

public class UIFormatterHelper {

    //////////////////////////////////////////////////////////////////////
    // FORMATTERS
    //////////////////////////////////////////////////////////////////////

    public static final String STRING_TYPE = "Boolean";
    public static final String BOOLEAN_TYPE = "Boolean";
    public static final String BYTE_TYPE = "Byte";
    public static final String SHORT_TYPE = "Short";
    public static final String INTEGER_TYPE = "Integer";
    public static final String FLOAT_TYPE = "Float";
    public static final String DOUBLE_TYPE = "Double";
    
    public static final String DATE_TYPE = "Date";
    public static final String TIME_TYPE = "Time";
    public static final String DATE_TIME_TYPE = "DateTime";
    
    public static final String COLOR_TYPE = "Color";
    public static final String FONT_TYPE = "Font";
    
    public static final String HORIZONTAL_ALIGN_TYPE = "HorizontalAlign";
    public static final String VERTICAL_ALIGN_TYPE = "VerticalAlign";
    public static final String DIRECTION_TYPE = "Direction";
    public static final String ORIENTATION_TYPE = "Orientation";
    public static final String LAYOUT_REGION_TYPE = "LayoutRegion";
    
    
    
    
    private static Map<String, Formatter> formatters = new HashMap<String, Formatter>();
    
    static {
	formatters.put(BOOLEAN_TYPE, new BooleanFormatter());
	formatters.put(BYTE_TYPE, new ByteFormatter());
	formatters.put(SHORT_TYPE, new ShortFormatter());
	formatters.put(INTEGER_TYPE, new IntegerFormatter());
	formatters.put(FLOAT_TYPE, new FloatFormatter());
	formatters.put(DOUBLE_TYPE, new DoubleFormatter());
	
	formatters.put(DATE_TYPE, new DateFormatter());
	formatters.put(TIME_TYPE, new TimeFormatter());
	formatters.put(DATE_TIME_TYPE, new DateTimeFormatter());
	
	formatters.put(COLOR_TYPE, new ColorFormatter());
	formatters.put(FONT_TYPE, new FontFormatter());
	
	formatters.put(HORIZONTAL_ALIGN_TYPE, new HorizontalAlignFormatter());
	formatters.put(VERTICAL_ALIGN_TYPE, new VerticalAlignFormatter());
	formatters.put(DIRECTION_TYPE, new DirectionFormatter());
	formatters.put(ORIENTATION_TYPE, new OrientationFormatter());
	formatters.put(LAYOUT_REGION_TYPE, new LayoutRegionFormatter());
    }
    
    /**
     * Get Formatter by type
     * @param type
     * @return
     */
    public static Formatter getFormatter(String type) {
	return formatters.get(type);
    }
    

    ////
    
    public static Boolean toBoolean(String value) {
	return value == null ? null : (Boolean) getFormatter(BOOLEAN_TYPE).toValue(value);
    }

    public static String toString(Boolean value) {
	return value == null ? null : getFormatter(BOOLEAN_TYPE).toString(value);
    }

    ////
    
    public static Byte toByte(String value) {
	return value == null ? null : (Byte) getFormatter(BYTE_TYPE).toValue(value);
    }

    public static String toString(Byte value) {
	return value == null ? null : getFormatter(BYTE_TYPE).toString(value);
    }

    ////
    
    public static Short toShort(String value) {
	return value == null ? null : (Short) getFormatter(SHORT_TYPE).toValue(value);
    }

    public static String toString(Short value) {
	return value == null ? null : getFormatter(SHORT_TYPE).toString(value);
    }
    
    ////
    
    public static Integer toInteger(String value) {
	return value == null ? null : (Integer) getFormatter(INTEGER_TYPE).toValue(value);
    }

    public static String toString(Integer value) {
	return value == null ? null : getFormatter(INTEGER_TYPE).toString(value);
    }
    
    ////
    
    public static Float toFloat(String value) {
	return value == null ? null : (Float) getFormatter(FLOAT_TYPE).toValue(value);
    }

    public static String toString(Float value) {
	return value == null ? null : getFormatter(FLOAT_TYPE).toString(value);
    }

    ////
    
    public static Double toDouble(String value) {
	return value == null ? null : (Double) getFormatter(DOUBLE_TYPE).toValue(value);
    }

    public static String toString(Double value) {
	return value == null ? null : getFormatter(DOUBLE_TYPE).toString(value);
    }

    ////
    
    public static Date toDate(String value) {
	return value == null ? null : (Date) getFormatter(DATE_TYPE).toValue(value);
    }

    public static String toStringDate(Date value) {
	return value == null ? null : getFormatter(DATE_TYPE).toString(value);
    }

    public static Date toTime(String value) {
	return value == null ? null : (Date) getFormatter(TIME_TYPE).toValue(value);
    }

    public static String toStringTime(Date value) {
	return value == null ? null : getFormatter(TIME_TYPE).toString(value);
    }

    public static Date toDateTime(String value) {
	return value == null ? null : (Date) getFormatter(DATE_TIME_TYPE).toValue(value);
    }

    public static String toStringDateTime(Date value) {
	return value == null ? null : getFormatter(DATE_TIME_TYPE).toString(value);
    }

    ////
    
    public static Color toColor(String value) {
	return value == null ? null : (Color) getFormatter(COLOR_TYPE).toValue(value);
    }

    public static String toString(Color value) {
	return value == null ? null : getFormatter(COLOR_TYPE).toString(value);
    }

    ////
    
    public static Font toFont(String value) {
	return value == null ? null : (Font) getFormatter(FONT_TYPE).toValue(value);
    }

    public static String toString(Font value) {
	return value == null ? null : getFormatter(FONT_TYPE).toString(value);
    }

    ////
    
    public static HorizontalAlign toHorizontalAlign(String value) {
	return value == null ? null : (HorizontalAlign) getFormatter(HORIZONTAL_ALIGN_TYPE).toValue(value);
    }
    
    public static VerticalAlign toVerticalAlign(String value) {
	return value == null ? null : (VerticalAlign) getFormatter(VERTICAL_ALIGN_TYPE).toValue(value);
    }

    public static Direction toDirection(String value) {
	return value == null ? null : (Direction) getFormatter(DIRECTION_TYPE).toValue(value);
    }
    
    public static Orientation toOrientation(String value) {
	return value == null ? null : (Orientation) getFormatter(ORIENTATION_TYPE).toValue(value);
    }

    public static LayoutRegion toLayoutRegion(String value) {
	return value == null ? null : (LayoutRegion) getFormatter(LAYOUT_REGION_TYPE).toValue(value);
    }



}

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
import org.plazmaforge.framework.core.data.formatter.type.RWBooleanFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWByteFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWDateFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWDateTimeFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWDoubleFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWFloatFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWIntegerFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWShortFormatter;
import org.plazmaforge.framework.core.data.formatter.type.RWTimeFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.type.ColorFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.type.DirectionFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.type.FontFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.type.HorizontalAlignFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.type.LayoutRegionFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.type.OrientationFormatter;
import org.plazmaforge.framework.uwt.builder.formatter.type.VerticalAlignFormatter;
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
	
	// Read/Write formatter
	formatters.put(BOOLEAN_TYPE, new RWBooleanFormatter());
	formatters.put(BYTE_TYPE, new RWByteFormatter());
	formatters.put(SHORT_TYPE, new RWShortFormatter());
	formatters.put(INTEGER_TYPE, new RWIntegerFormatter());
	formatters.put(FLOAT_TYPE, new RWFloatFormatter());
	formatters.put(DOUBLE_TYPE, new RWDoubleFormatter());
	
	formatters.put(DATE_TYPE, new RWDateFormatter());
	formatters.put(TIME_TYPE, new RWTimeFormatter());
	formatters.put(DATE_TIME_TYPE, new RWDateTimeFormatter());
	
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
    
    public static Boolean parseBoolean(String value) {
	return value == null ? null : (Boolean) getFormatter(BOOLEAN_TYPE).parse(value);
    }

    public static String formatBoolean(Boolean value) {
	return value == null ? null : getFormatter(BOOLEAN_TYPE).format(value);
    }

    ////
    
    public static Byte parseByte(String value) {
	return value == null ? null : (Byte) getFormatter(BYTE_TYPE).parse(value);
    }

    public static String formatByte(Byte value) {
	return value == null ? null : getFormatter(BYTE_TYPE).format(value);
    }

    ////
    
    public static Short parseShort(String value) {
	return value == null ? null : (Short) getFormatter(SHORT_TYPE).parse(value);
    }

    public static String formatShort(Short value) {
	return value == null ? null : getFormatter(SHORT_TYPE).format(value);
    }
    
    ////
    
    public static Integer parseInteger(String value) {
	return value == null ? null : (Integer) getFormatter(INTEGER_TYPE).parse(value);
    }

    public static String formatInteger(Integer value) {
	return value == null ? null : getFormatter(INTEGER_TYPE).format(value);
    }
    
    ////
    
    public static Float parseFloat(String value) {
	return value == null ? null : (Float) getFormatter(FLOAT_TYPE).parse(value);
    }

    public static String formatFloat(Float value) {
	return value == null ? null : getFormatter(FLOAT_TYPE).format(value);
    }

    ////
    
    public static Double parseDouble(String value) {
	return value == null ? null : (Double) getFormatter(DOUBLE_TYPE).parse(value);
    }

    public static String formatString(Double value) {
	return value == null ? null : getFormatter(DOUBLE_TYPE).format(value);
    }

    ////
    
    public static Date parseDate(String value) {
	return value == null ? null : (Date) getFormatter(DATE_TYPE).parse(value);
    }

    public static String formatDate(Date value) {
	return value == null ? null : getFormatter(DATE_TYPE).format(value);
    }

    public static Date parseTime(String value) {
	return value == null ? null : (Date) getFormatter(TIME_TYPE).parse(value);
    }

    public static String formatTime(Date value) {
	return value == null ? null : getFormatter(TIME_TYPE).format(value);
    }

    public static Date parseDateTime(String value) {
	return value == null ? null : (Date) getFormatter(DATE_TIME_TYPE).parse(value);
    }

    public static String formatDateTime(Date value) {
	return value == null ? null : getFormatter(DATE_TIME_TYPE).format(value);
    }

    ////
    
    public static Color parseColor(String value) {
	return value == null ? null : (Color) getFormatter(COLOR_TYPE).parse(value);
    }

    public static String formatColor(Color value) {
	return value == null ? null : getFormatter(COLOR_TYPE).format(value);
    }

    ////
    
    public static Font parseFont(String value) {
	return value == null ? null : (Font) getFormatter(FONT_TYPE).parse(value);
    }

    public static String formatFont(Font value) {
	return value == null ? null : getFormatter(FONT_TYPE).format(value);
    }

    ////
    
    public static HorizontalAlign parseHorizontalAlign(String value) {
	return value == null ? null : (HorizontalAlign) getFormatter(HORIZONTAL_ALIGN_TYPE).parse(value);
    }
    
    public static VerticalAlign parseVerticalAlign(String value) {
	return value == null ? null : (VerticalAlign) getFormatter(VERTICAL_ALIGN_TYPE).parse(value);
    }

    public static Direction parseDirection(String value) {
	return value == null ? null : (Direction) getFormatter(DIRECTION_TYPE).parse(value);
    }
    
    public static Orientation parseOrientation(String value) {
	return value == null ? null : (Orientation) getFormatter(ORIENTATION_TYPE).parse(value);
    }

    public static LayoutRegion parseLayoutRegion(String value) {
	return value == null ? null : (LayoutRegion) getFormatter(LAYOUT_REGION_TYPE).parse(value);
    }



}

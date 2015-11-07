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

import org.plazmaforge.framework.core.data.ValuePresenter;
import org.plazmaforge.framework.core.data.presenter.BooleanPresenter;
import org.plazmaforge.framework.core.data.presenter.BytePresenter;
import org.plazmaforge.framework.core.data.presenter.DatePresenter;
import org.plazmaforge.framework.core.data.presenter.DateTimePresenter;
import org.plazmaforge.framework.core.data.presenter.DoublePresenter;
import org.plazmaforge.framework.core.data.presenter.FloatPresenter;
import org.plazmaforge.framework.core.data.presenter.IntegerPresenter;
import org.plazmaforge.framework.core.data.presenter.ShortPresenter;
import org.plazmaforge.framework.core.data.presenter.TimePresenter;
import org.plazmaforge.framework.uwt.builder.presenter.ColorPresenter;
import org.plazmaforge.framework.uwt.builder.presenter.DirectionPresenter;
import org.plazmaforge.framework.uwt.builder.presenter.FontPresenter;
import org.plazmaforge.framework.uwt.builder.presenter.HorizontalAlignPresenter;
import org.plazmaforge.framework.uwt.builder.presenter.LayoutRegionPresenter;
import org.plazmaforge.framework.uwt.builder.presenter.OrientationPresenter;
import org.plazmaforge.framework.uwt.builder.presenter.VerticalAlignPresenter;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.widget.Style.Direction;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.LayoutRegion;
import org.plazmaforge.framework.uwt.widget.Style.Orientation;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

public class UIPresenter {

    //////////////////////////////////////////////////////////////////////
    // VALUE PRESENTERS
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
    
    
    
    
    private static Map<String, ValuePresenter> presenters = new HashMap<String, ValuePresenter>();
    
    static {
	presenters.put(BOOLEAN_TYPE, new BooleanPresenter());
	presenters.put(BYTE_TYPE, new BytePresenter());
	presenters.put(SHORT_TYPE, new ShortPresenter());
	presenters.put(INTEGER_TYPE, new IntegerPresenter());
	presenters.put(FLOAT_TYPE, new FloatPresenter());
	presenters.put(DOUBLE_TYPE, new DoublePresenter());
	
	presenters.put(DATE_TYPE, new DatePresenter());
	presenters.put(TIME_TYPE, new TimePresenter());
	presenters.put(DATE_TIME_TYPE, new DateTimePresenter());
	
	presenters.put(COLOR_TYPE, new ColorPresenter());
	presenters.put(FONT_TYPE, new FontPresenter());
	
	presenters.put(HORIZONTAL_ALIGN_TYPE, new HorizontalAlignPresenter());
	presenters.put(VERTICAL_ALIGN_TYPE, new VerticalAlignPresenter());
	presenters.put(DIRECTION_TYPE, new DirectionPresenter());
	presenters.put(ORIENTATION_TYPE, new OrientationPresenter());
	presenters.put(LAYOUT_REGION_TYPE, new LayoutRegionPresenter());
    }
    
    /**
     * Get UIBuilder by type
     * @param type
     * @return
     */
    public static ValuePresenter getPresenter(String type) {
	return presenters.get(type);
    }
    

    ////
    
    public static Boolean toBoolean(String value) {
	return value == null ? null : (Boolean) getPresenter(BOOLEAN_TYPE).toValue(value);
    }

    public static String toString(Boolean value) {
	return value == null ? null : getPresenter(BOOLEAN_TYPE).toString(value);
    }

    ////
    
    public static Byte toByte(String value) {
	return value == null ? null : (Byte) getPresenter(BYTE_TYPE).toValue(value);
    }

    public static String toString(Byte value) {
	return value == null ? null : getPresenter(BYTE_TYPE).toString(value);
    }

    ////
    
    public static Short toShort(String value) {
	return value == null ? null : (Short) getPresenter(SHORT_TYPE).toValue(value);
    }

    public static String toString(Short value) {
	return value == null ? null : getPresenter(SHORT_TYPE).toString(value);
    }
    
    ////
    
    public static Integer toInteger(String value) {
	return value == null ? null : (Integer) getPresenter(INTEGER_TYPE).toValue(value);
    }

    public static String toString(Integer value) {
	return value == null ? null : getPresenter(INTEGER_TYPE).toString(value);
    }
    
    ////
    
    public static Float toFloat(String value) {
	return value == null ? null : (Float) getPresenter(FLOAT_TYPE).toValue(value);
    }

    public static String toString(Float value) {
	return value == null ? null : getPresenter(FLOAT_TYPE).toString(value);
    }

    ////
    
    public static Double toDouble(String value) {
	return value == null ? null : (Double) getPresenter(DOUBLE_TYPE).toValue(value);
    }

    public static String toString(Double value) {
	return value == null ? null : getPresenter(DOUBLE_TYPE).toString(value);
    }

    ////
    
    public static Date toDate(String value) {
	return value == null ? null : (Date) getPresenter(DATE_TYPE).toValue(value);
    }

    public static String toStringDate(Date value) {
	return value == null ? null : getPresenter(DATE_TYPE).toString(value);
    }

    public static Date toTime(String value) {
	return value == null ? null : (Date) getPresenter(TIME_TYPE).toValue(value);
    }

    public static String toStringTime(Date value) {
	return value == null ? null : getPresenter(TIME_TYPE).toString(value);
    }

    public static Date toDateTime(String value) {
	return value == null ? null : (Date) getPresenter(DATE_TIME_TYPE).toValue(value);
    }

    public static String toStringDateTime(Date value) {
	return value == null ? null : getPresenter(DATE_TIME_TYPE).toString(value);
    }

    ////
    
    public static Color toColor(String value) {
	return value == null ? null : (Color) getPresenter(COLOR_TYPE).toValue(value);
    }

    public static String toString(Color value) {
	return value == null ? null : getPresenter(COLOR_TYPE).toString(value);
    }

    ////
    
    public static Font toFont(String value) {
	return value == null ? null : (Font) getPresenter(FONT_TYPE).toValue(value);
    }

    public static String toString(Font value) {
	return value == null ? null : getPresenter(FONT_TYPE).toString(value);
    }

    ////
    
    public static HorizontalAlign toHorizontalAlign(String value) {
	return value == null ? null : (HorizontalAlign) getPresenter(HORIZONTAL_ALIGN_TYPE).toValue(value);
    }
    
    public static VerticalAlign toVerticalAlign(String value) {
	return value == null ? null : (VerticalAlign) getPresenter(VERTICAL_ALIGN_TYPE).toValue(value);
    }

    public static Direction toDirection(String value) {
	return value == null ? null : (Direction) getPresenter(DIRECTION_TYPE).toValue(value);
    }
    
    public static Orientation toOrientation(String value) {
	return value == null ? null : (Orientation) getPresenter(ORIENTATION_TYPE).toValue(value);
    }

    public static LayoutRegion toLayoutRegion(String value) {
	return value == null ? null : (LayoutRegion) getPresenter(LAYOUT_REGION_TYPE).toValue(value);
    }



}

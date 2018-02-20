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

package org.plazmaforge.framework.uwt.jfx.adapter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.widget.DateField;
import org.plazmaforge.framework.uwt.widget.HasFormat;

import javafx.util.StringConverter;


/**
 * 
 * @author ohapon
 *
 */
public class JFXDateFieldAdapter extends JFXControlAdapter {
    
    public Object createDelegate(UIElement parent, UIElement element) {
	// https://docs.oracle.com/javase/8/javafx/user-interface-tutorial/date-picker.htm
	
	String format = ((HasFormat) element).getFormat();
	javafx.scene.control.DatePicker xDateField = new javafx.scene.control.DatePicker();
	
	xDateField.setConverter(new DateStringConverter(format));
	addChild(getContent(parent.getDelegate()), xDateField, element); // Add to parent
	return xDateField;
    }
    
    protected javafx.scene.control.DatePicker asDateField(Object delegate) {
	return (javafx.scene.control.DatePicker) delegate;
    }
    
    @Override
    public void setProperty(UIElement element, String name, Object value) {
	javafx.scene.control.DatePicker xDateField = asDateField(element.getDelegate());
	if (xDateField == null) {
	    return;
	}
	if (DateField.PROPERTY_VALUE.equals(name)) {
	    xDateField.setValue(toLocalDate(asDate(value)));
	    return;
	} else if (DateField.PROPERTY_FORMAT.equals(name)) {
	    String format = asString(value);
	    DateStringConverter converter = ( DateStringConverter) xDateField.getConverter();
	    converter.setFormat(format);
	    return;
	} 
	
	super.setProperty(element, name, value);
    }
    
    
    @Override
    public Object getProperty(UIElement element, String name) {
	javafx.scene.control.DatePicker xDateField = asDateField(element.getDelegate());
	if (xDateField == null) {
	    return null;
	}
	if (DateField.PROPERTY_VALUE.equals(name)) {
	    return toDate(xDateField.getValue());
	} 
	return super.getProperty(element, name);
    }
    
    
    // [CORE]
    public static class DateStringConverter extends StringConverter<LocalDate> {
	
        private DateTimeFormatter dateFormatter;
        
        public DateStringConverter() {
            setFormat(null);
	}

	public DateStringConverter(String pattern) {
            setFormat(pattern);
        }

        public void setFormat(String format) {
            dateFormatter = DateTimeFormatter.ofPattern(format == null ? "yyyy-MM-dd" : format);
	}

	@Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
            }
        }
        
        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }
    }         
    
    // [CORE]
    // https://stackoverflow.com/questions/22929237/convert-java-time-localdate-into-java-util-date-type
    public static Date toDate(LocalDate localDate) {
	if (localDate == null) {
	    return null;
	}
	return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(LocalDateTime localDateTime) {
	if (localDateTime == null) {
	    return null;
	}
	return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate toLocalDate(Date date) {
	if (date == null) {
	    return null;
	}
	return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
	if (date == null) {
	    return null;
	}
	return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}

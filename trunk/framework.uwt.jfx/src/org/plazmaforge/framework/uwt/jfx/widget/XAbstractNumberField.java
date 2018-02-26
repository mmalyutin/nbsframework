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
package org.plazmaforge.framework.uwt.jfx.widget;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.NumberStringConverter;
 
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

/**
 * 
 * @author ohapon
 *
 */
public abstract class XAbstractNumberField<T extends Number> extends TextField {
 
    //https://stackoverflow.com/questions/40472668/numeric-textfield-for-integers-in-javafx-8-with-textformatter-and-or-unaryoperat
    //https://stackoverflow.com/questions/15111420/how-to-check-if-a-string-contains-only-digits-in-java
    public XAbstractNumberField() {
        setAlignment(Pos.CENTER_RIGHT);
        setFormat(null);
    }
 
    public void setFormat(String format) {
	UnaryOperator<TextFormatter.Change> formatterFilter = createFormatterFilter();
	XNumberFormatter formatter = createFormatter(format, formatterFilter);
        setTextFormatter(formatter);
    }
    
    public void setValue(T value) {
	String str = null;
	if (value == null) {
	    str = "";
	} else {
	    str = getNumberConverter().toString(value); 
	}
	setText(str);
    }
    
    public T getValue() {
	String str = getText();
	if (str != null) {
	    str = str.trim();
	    if (str.isEmpty()) {
		str = null;
	    }
	}
	if (str == null) {
	    return null;
	}
	Number value = getNumberConverter().fromString(str);
	return castValue(value);
    }
    
    protected abstract T castValue(Number value);
    
    protected String getDefaultFormat() {
	return "#0.00";
    }
    
    protected String normalyzeFormat(String format) {
	return format == null ? getDefaultFormat() : format;
    }
    
    protected XNumberFormatter createFormatter(String format, UnaryOperator<TextFormatter.Change> formatterFilter) {
	return new XNumberFormatter(normalyzeFormat(format), formatterFilter);
    }
    
    protected UnaryOperator<TextFormatter.Change> createFormatterFilter() {
	return null;
    }
 
    protected XNumberFormatter getNumberFormatter() {
	return (XNumberFormatter) getTextFormatter();
    }
    
    protected NumberStringConverter getNumberConverter() {
	return getNumberFormatter().getNumberStringConverter();
    }  
    
    
    public static class XNumberFormatter extends TextFormatter {
 
        public XNumberFormatter(String pattern, UnaryOperator filter) {
            super(new NumberStringConverter(pattern), null, filter);
        }
 
        public XNumberFormatter(String pattern) {
            super(new NumberStringConverter(pattern), null, null);
        }
 
        public XNumberFormatter(UnaryOperator filter) {
            super(filter);
        }
 
        public XNumberFormatter(NumberStringConverter valueConverter, Object defaultValue, UnaryOperator filter) {
            super(valueConverter, defaultValue, filter);
        }
 
        public XNumberFormatter(NumberStringConverter valueConverter, Object defaultValue) {
            super(valueConverter, defaultValue);
        }
 
        public XNumberFormatter(NumberStringConverter valueConverter) {
            super(valueConverter);
        }
 
        public NumberStringConverter getNumberStringConverter( ){
            return (NumberStringConverter) getValueConverter();
        }
    }
 
 
    public static class IntegerFilter implements UnaryOperator<TextFormatter.Change> {
 
        @Override
        public TextFormatter.Change apply(TextFormatter.Change t) {
 
            if (t.isReplaced())
                if(t.getText().matches("[^0-9]"))
                    t.setText(t.getControlText().substring(t.getRangeStart(), t.getRangeEnd()));
 
 
            if (t.isAdded()) {
                if (t.getControlText().contains(".")) {
                    if (t.getText().matches("[^0-9]")) {
                        t.setText("");
                    }
                } else if (t.getText().matches("[^0-9.]")) {
                    t.setText("");
                }
            }
 
            return t;
        }
    };
 
    public static class NumberFilter implements UnaryOperator<TextFormatter.Change>  {
 
        @Override
        public TextFormatter.Change apply(TextFormatter.Change t) {
            String newText = t.getControlNewText();
            //[\.]
            //if (newText.matches("-?([1-9][0-9]*)?")) {
            //if (newText.matches("-?([1-9]([\\.])?[0-9]*)?")) {
            if (isNumber(newText)) {
                return t;
            }
            return null;
 
        }
    };
 
    private static final Pattern NUMBER_PATTERN = Pattern.compile(
            "[\\x00-\\x20]*[+-]?(NaN|Infinity|((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)" +
                    "([eE][+-]?(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|" +
                    "(((0[xX](\\p{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))" +
                    "[pP][+-]?(\\p{Digit}+)))[fFdD]?))[\\x00-\\x20]*");
 
    public static boolean isNumber(String s){
        return NUMBER_PATTERN.matcher(s).matches();
    }
 
 
}


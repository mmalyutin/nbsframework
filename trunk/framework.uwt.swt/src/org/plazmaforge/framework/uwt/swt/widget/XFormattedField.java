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

package org.plazmaforge.framework.uwt.swt.widget;

import org.eclipse.swt.widgets.Composite;


public class XFormattedField extends XTextField {

    public XFormattedField(Composite parent, int style) {
	super(parent, style);
	initialize();
    }

    private void initialize() {
	TextFormatter formatter = createFormatter();
	if (formatter != null) {
	    setFormatter(formatter);
	}
    }
    
    /**
     * Returns the current value of the widget.<p>
     * 
     * The returned value is provided by the formatter and is of the type managed
     * bu the formatter. For exemple a <code>DateFormatter</code> will return a
     * <code>Date</code> value.<br>
     * If no formatter is associated, the <code>String</code> contained in the
     * <code>Text</code> widget is returned.
     * 
     * @return Current value
     */
    public Object getValue() {
	return getFormatterValue();
    }

    /**
     * Sets a new value.<p>
     * 
     * If no formatter is currently associated to he widget, a new one is created
     * by the factory based on the value's class.<br>
     * If the value is incompatible with the formatter, an <code>IllegalArgumentException</code>
     * is returned.
     * 
     * @param value new value
     */
    public void setValue(Object value) {
	setFormatterValue(value);
    }
    
    protected Object getFormatterValue() {
	return AbstractTextFormatter.getValue(text);
    }

    protected void setFormatterValue(Object value) {
	AbstractTextFormatter.setValue(text, value);
    }

    /**
     * Returns <code>true</code> if the current value is valid, else <code>false</code>.
     * 
     * @return <code>true</code> if valid.
     */
    public boolean isValid() {
      return getFormatter() != null ? getFormatter().isValid() : true;
    }

    
    
    public void setValueClass(Class valueClass) {
	if (getFormatter() != null && getFormatter().getValueClass() == null) {
	    getFormatter().setValueClass(valueClass);
	    //TODO
	    //getModel().setValueClass(valueClass);
	}
	
    }
    
    public Class getValueClass() {
	return getFormatter() == null ? null : getFormatter().getValueClass();
    }
    
    /////
    
    public void setFormatter(TextFormatter formatter) {
	AbstractTextFormatter.initFormatter(text, formatter);
    }

    public TextFormatter getFormatter() {
	return AbstractTextFormatter.getFormatter(text);
    }

    // /////////////////////////////////////////////////////////////////////////////////

    public void setPattern(String pattern) {
	TextFormatter formatter = createFormatter(pattern);
	setFormatter(formatter);
    }

    protected TextFormatter createFormatter() {
	return createFormatter(getDefaultPattern());
    }

    protected String getDefaultPattern() {
	return null;
    }

    protected TextFormatter createFormatter(String pattern) {
	return null;
    }
    
//    protected TextFormatter createFormatter() {
//	return createFormatter("-#,###,##0.00");
//    }
//    
//    protected TextFormatter createFormatter(String pattern) {
//	NumberFormatter formatter = new NumberFormatter(pattern);
//	formatter.setDecimalSeparatorAlwaysShown(false);
//	formatter.setFixedLengths(true, true);
//	return formatter;
//    }
    
    
}

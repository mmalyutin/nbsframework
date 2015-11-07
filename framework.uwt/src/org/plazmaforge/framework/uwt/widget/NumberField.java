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

package org.plazmaforge.framework.uwt.widget;


public class NumberField extends AbstractField<Number> {

    private String format;
  
    @Override
    protected void configure() {
	super.configure();
	configureFormat();
    }
  
    protected void configureFormat() {
	if (getFormat() != null) {
	    return;
	}    
	String format = getConfigFormat();
	if (format != null) {
	    setFormat(format);
	}
    }
    
    protected String getConfigFormat() {
	return getConfigProperty(CONFIG_FORMAT_NUMBER);
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
        fireChangeProperty(PROPERTY_FORMAT, format);
    }
    

    public int intValue() {
	Number value = getValue();
	return value == null ? 0: value.intValue();
    }

    public long longValue() {
	Number value = getValue();
	return value == null ? 0: value.longValue();
    }

    public float floatValue() {
	Number value = getValue();
	return value == null ? 0f: value.floatValue();
    }

    public double doubleValue() {
	Number value = getValue();
	return value == null ? 0d: value.doubleValue();
    }

    public byte byteValue() {
   	return (byte) intValue();
   }

    public short shortValue() {
	return (short) intValue();
    }

}

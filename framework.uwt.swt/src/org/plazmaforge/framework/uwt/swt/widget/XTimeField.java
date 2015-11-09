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

import java.util.Date;

import org.eclipse.swt.widgets.Composite;

public class XTimeField extends XFormattedField {

    public XTimeField(Composite parent, int style) {
	super(parent, style);
    }

    protected String getDefaultPattern() {
	return "HH:mm:ss"/*PlatformEnvironment.getTimePattern()*/;
    }
    
    
    protected TextFormatter createFormatter(String pattern) {
 	 return new DateTimeFormatter(pattern);
    }
    
    
    public Date getDate() {
	return (Date) getFormatterValue();
    }

    public void setDate(Date value) {
	setFormatterValue(value);
    }

}

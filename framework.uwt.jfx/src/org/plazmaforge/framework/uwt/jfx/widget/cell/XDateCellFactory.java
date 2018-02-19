/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the LicensS, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple PlacS, Suite 330, Boston, MA  02111-1307, USA.
 * 
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */
package org.plazmaforge.framework.uwt.jfx.widget.cell;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author ohapon
 *
 * @param <S>
 */
public class XDateCellFactory<S> extends XAbstractCellFactory<S, Date> {

    private DateFormat formatter;
    
    public XDateCellFactory() {
	super();
	//TODO
	initFormatter("yyyy-MM-dd");
    }
    
    public XDateCellFactory(DateFormat formatter) {
	super();
	this.formatter = formatter;
    }

    public XDateCellFactory(String format) {
	super();
	initFormatter(format);
    }
    
    protected void initFormatter(String format) {
	formatter = createFormatter(format);
    }
    
    protected DateFormat createFormatter(String format) {
	return new SimpleDateFormat(format);
    }

    @Override
    protected String formatValue(Date value) {
	return value == null ? null : formatter.format(value);
    }

}

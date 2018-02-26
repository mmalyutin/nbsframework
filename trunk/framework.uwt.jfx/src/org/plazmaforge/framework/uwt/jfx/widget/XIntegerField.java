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

import javafx.scene.control.TextFormatter;
 
import java.util.function.UnaryOperator;


/**
 * 
 * @author ohapon
 *
 */
public class XIntegerField extends XAbstractNumberField<Integer> {
 
    public XIntegerField() {
        super();
    }
    
    protected Integer castValue(Number value) {
	//TODO
	return (Integer) value;
    }
    
    @Override
    protected String getDefaultFormat() {
	return "#0";
    }
         
    @Override
    protected UnaryOperator<TextFormatter.Change> createFormatterFilter() {
	return new IntegerFilter();
    }
 
 
 
}


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

package org.plazmaforge.framework.core.criteria;

import java.io.Serializable;

/**
 * 
 * @author ohapon
 *
 */
public abstract class ValuesFilter extends ValueFilter {

    private static final long serialVersionUID = -4249459725350239866L;
    
    private Serializable[] values;

    public ValuesFilter() {
	super();
    }

    protected ValuesFilter(String propertyName, Serializable[] values, String operation) {
	super(propertyName, null, operation);
	this.values = values;
    }

    protected ValuesFilter(String propertyName, Serializable[] values) {
	super(propertyName, null);
	this.values = values;
    }
    
    public Serializable[] getValues() {
        return values;
    }
    
    public Serializable getValue() {
        return values == null || values.length == 0 ? null : values[0];
    }
    
    

}

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
public class Order implements Serializable {

    
    private static final long serialVersionUID = 1159447188029796950L;
    
    

    private boolean asc;
    
    private String propertyName;

    public Order() {
	super();
	this.asc = true;
    }

    public Order(String propertyName, boolean asc) {
	super();
	this.propertyName = propertyName;
	this.asc = asc;
    }

    public Order(String propertyName) {
	super();
	this.propertyName = propertyName;
	this.asc = true;
    }

    public boolean isAsc() {
        return asc;
    }

    public String getPropertyName() {
        return propertyName;
    }
    
    public String toString() {
	return propertyName + " " + (isAsc() ? "asc" : "desc");
    }
    
}

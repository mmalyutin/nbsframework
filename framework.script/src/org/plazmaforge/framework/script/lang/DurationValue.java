/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.script.lang;

import java.io.Serializable;

/**
 * Presents duration time in milliseconds 
 * 
 * @author ohapon
 *
 */
public class DurationValue implements Serializable {

    private static final long serialVersionUID = 3110803116355040902L;
    
    private long instant;

    public DurationValue() {
	super();
    }

    public DurationValue(long instant) {
	super();
	this.instant = instant;
    }

    public long getInstant() {
        return instant;
    }

    public void setInstant(long instant) {
        this.instant = instant;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (instant ^ (instant >>> 32));
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	DurationValue other = (DurationValue) obj;
	if (instant != other.instant)
	    return false;
	return true;
    }
    
    
    

    
}

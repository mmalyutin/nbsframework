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

/**
 * Presents time period (star, end)
 *  - start time in milliseconds
 *  - end time in milliseconds
 *  
 * @author ohapon
 *
 */
public class PeriodValue {

    /**
     * Start time in milliseconds
     */
    private long start;
    
    /**
     * End time in milliseconds
     */
    private long end;

    
    public PeriodValue() {
	super();
    }

    public PeriodValue(long start, long end) {
	super();
	this.start = start;
	this.end = end;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
    
    public long getInstant() {
	return end - start; 
    }
    
}

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

import org.plazmaforge.framework.script.util.CalendarConstants;


public class LPeriod extends LInterval implements CalendarConstants {

    
    /**
     * @param value
     */
    public LPeriod(long start, long end) {
	super(Type.PERIOD, new PeriodValue(start, end));
    }


    @Override
    public String _toString() {
	return "" + getStart() + ":" + getEnd();
    }    
    
    protected PeriodValue getPeriodValue() {
	return (PeriodValue) getValue();
    }

    public long getInstant() {
	return getPeriodValue().getInstant();
    }

    protected long getStart() {
	return getPeriodValue().getStart();
    }

    protected long getEnd() {
	return getPeriodValue().getEnd();
    }
    
}

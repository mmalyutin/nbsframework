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

import java.util.List;

import org.plazmaforge.framework.script.util.CalendarConstants;


public class LDuration extends LInterval implements CalendarConstants {

    
    /**
     * @param value
     */
    public LDuration(long value) {
	super(Type.DURATION, new DurationValue(value));
    }

    @Override
    public String _toString() {
	return "" + getInstant();
    }
    
    @Override
    public LValue _invoke(String method, List<LValue> parameters) {
	
	// TIME IN MILLISECONDS
	if ("setInstant".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	    setInstant(parameter.asLong());
	    return LValue.VOID;
	}
	
	return super._invoke(method, parameters);
    }
    
    protected DurationValue getDurationValue() {
	 return (DurationValue) getValue();
    }

    public long getInstant() {
        return getDurationValue().getInstant();
    }

    public void setInstant(long instant) {
	getDurationValue().setInstant(instant);
    }    
}

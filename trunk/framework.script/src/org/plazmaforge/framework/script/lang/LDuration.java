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

import org.plazmaforge.framework.script.util.CalendarInfo;


public class LDuration extends LValue implements CalendarInfo {

    
    /**
     * @param value
     */
    public LDuration(Long value) {
	super(Type.DURATION, value);
    }

    
    
    @Override
    public LValue _invoke(String method, List<LValue> parameters) {
	
	// TIME
	if ("getInstant".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asLong());
	    
	}/* else if ("setInstant".equals(method)) {
	    checkMethod(method, parameters, 1);
	    LValue parameter = parameters.get(0);
	    if (!parameter.isNumber()) {
		raiseIllegalMethodParameterTypeException("Number");
	    }
	}*/
	
	else if ("toMilliseconds".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asLong());
	} else if ("toSeconds".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asLong() / 1000);
	} else if ("toMinutes".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asLong() / 1000 / 60);
	} else if ("toHours".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asLong() / 1000 / 60 / 60);
	} else if ("toDays".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asLong() / 1000 / 60 / 60 / 24);
	}

	
	return super._invoke(method, parameters);
    }
}

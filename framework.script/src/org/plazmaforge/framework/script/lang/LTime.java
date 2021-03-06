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

/**
 * 
 */
package org.plazmaforge.framework.script.lang;

import java.util.Date;
import java.util.List;

import org.plazmaforge.framework.script.util.CommonUtils;

/**
 * @author ohapon
 *
 */
public class LTime extends LInstant {

    // Emulate start date 1970-Jan-1

    public LTime(Date value) {
	super(Type.TIME, value);
    }

    public LTime(long time) {
	super(Type.TIME, CommonUtils.getTime(time));
    }
   
    protected LTime newInstance(long time) {
	return new LTime(time);
    }
    
    @Override
    public String _toString() {
	return toTimeString();
    }
    
    
    @Override
    public LValue _invoke(String method, List<LValue> parameters) {
	if (isUnsupportedMethod(method)) {
	    raiseIllegalMethodException(method);
	    return null;
	}
	return super._invoke(method, parameters);
    }
    
    protected boolean isUnsupportedMethod(String method) {
	if (method == null) {
	    return false;
	}
	return "getYear".equals(method)
		|| "setYear".equals(method)
		|| "getMonth".equals(method)
		|| "setMonth".equals(method)
		|| "getDay".equals(method)
		|| "setDay".equals(method);
    }
    
    
}

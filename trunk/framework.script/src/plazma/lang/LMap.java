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
package plazma.lang;

import java.util.List;
import java.util.Map;

import plazma.ast.LNode;

/**
 * @author ohapon
 *
 */
public class LMap extends LValue {

    /**
     * @param value
     */
    public LMap(Map<?, ?> value) {
	super(Type.MAP, value);
    }

    ////
    
    public LValue _get(LValue index) {
	return getMapValue(index);
    }

    public void _set(LValue index, LValue value) {
	setMapValue(index, value);
    }

    @Override
    public LValue _invoke(String method, List<LNode> parameters) {
	if ("size".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asList().size());
	}
	return super._invoke(method, parameters);
    }
    
    ////
    
    protected LValue getMapValue(LValue index) {
    	return asMap().get(index);
    }

    protected void setMapValue(LValue index, LValue value) {
    	asMap().put(index, value);
    }    
}

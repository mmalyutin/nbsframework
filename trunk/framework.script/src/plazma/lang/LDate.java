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

import java.util.Calendar;
import java.util.Date;

/**
 * @author ohapon
 *
 */
public class LDate extends LValue {

    /**
     * @param value
     */
    public LDate(Date value) {
	super(Type.DATE, value);
    }

    @Override
    public String _toString() {
	Calendar calendar = Calendar.getInstance();
	calendar.setTime((Date) getValue());
	int year = calendar.get(Calendar.YEAR);
	int month = calendar.get(Calendar.MONTH) + 1;
	int day = calendar.get(Calendar.DAY_OF_MONTH);
	return "" + year + "-" + (month < 9 ? ("0" + month) : month) + "-" + ((day < 9 ? ("0" + day) : day));
    }
    
    // <
    @Override
    public LValue _lt(LValue that) {
	if (!that.isDate()) {
	    return super._lte(that);
	}
	return new LBoolean(asDate().getTime() < that.asDate().getTime()); 
    }
    
    // <=
    @Override
    public LValue _lte(LValue that) {
	if (!that.isDate()) {
	    return super._lte(that);
	}
	return new LBoolean(asDate().getTime() <= that.asDate().getTime()); 
    }

    // >
    @Override
    public LValue _gt(LValue that) {
	if (!that.isDate()) {
	    return super._lte(that);
	}
	return new LBoolean(asDate().getTime() > that.asDate().getTime()); 
    }
    
    // >=
    @Override
    public LValue _gte(LValue that) {
	if (!that.isDate()) {
	    return super._lte(that);
	}
	return new LBoolean(asDate().getTime() >= that.asDate().getTime()); 
    }
    
}

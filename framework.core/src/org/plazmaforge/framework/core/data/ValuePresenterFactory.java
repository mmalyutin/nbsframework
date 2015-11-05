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

package org.plazmaforge.framework.core.data;

import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.type.Types;
import org.plazmaforge.framework.core.data.presenter.BytePresenter;
import org.plazmaforge.framework.core.data.presenter.DatePresenter;
import org.plazmaforge.framework.core.data.presenter.DateTimePresenter;
import org.plazmaforge.framework.core.data.presenter.DoublePresenter;
import org.plazmaforge.framework.core.data.presenter.FloatPresenter;
import org.plazmaforge.framework.core.data.presenter.IntegerPresenter;
import org.plazmaforge.framework.core.data.presenter.ShortPresenter;
import org.plazmaforge.framework.core.data.presenter.StringPresenter;
import org.plazmaforge.framework.core.data.presenter.TimePresenter;

/**
 * Value Presenter Factory
 * 
 * @author ohapon
 *
 */
public class ValuePresenterFactory {


    private Map<String, ValuePresenter> presenters = new HashMap<String, ValuePresenter>();
    
    public ValuePresenterFactory() {
	super();
    }

    public void registerValuePresenter(String type, ValuePresenter valuePresenter) {
	presenters.put(type, valuePresenter);
    }

    public void unregisterValuePresenter(String type) {
	presenters.remove(type);
    }
    
    public void registerDefaultValuePresenters() {
	registerValuePresenter(Types.StringType, new StringPresenter());
	registerValuePresenter(Types.TextType, new StringPresenter());
	registerValuePresenter(Types.BooleanType, new BytePresenter());
	registerValuePresenter(Types.ShortType, new ShortPresenter());
	registerValuePresenter(Types.IntegerType, new IntegerPresenter());
	registerValuePresenter(Types.FloatType, new FloatPresenter());
	registerValuePresenter(Types.DoubleType, new DoublePresenter());
	registerValuePresenter(Types.DateType, new DatePresenter());
	registerValuePresenter(Types.TimeType, new TimePresenter());
	registerValuePresenter(Types.DateTimeType, new DateTimePresenter());
    }
    
    
    public ValuePresenter getValuePresenter(String type) {
	return getValuePresenter(type, null);
    }

    public ValuePresenter getValuePresenter(String type, String format) {
	
	/*
	if (type == null) {
	    return null;
	}
	
	if (TypeUtils.isLikeStringType(type)) {
	    return new StringPresenter();
	} else if (Types.IntegerType.equals(type)) {
	    return new IntegerPresenter();
	} else if (Types.FloatType.equals(type)) {
	    return new FloatPresenter();
	} else if (Types.DoubleType.equals(type)) {
	    return new DoublePresenter();
	}
	return null;
	*/
	
	return doGetValuePresenter(type);
    }
    
    protected ValuePresenter doGetValuePresenter(String type) {
	return presenters.get(type);
    }
    
    

}

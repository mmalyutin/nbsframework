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

package org.plazmaforge.framework.core.datastorage;

import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.data.BaseLocalizedIdentifier;
import org.plazmaforge.framework.core.data.ValuePresenter;
import org.plazmaforge.framework.core.data.presenter.DoublePresenter;
import org.plazmaforge.framework.core.data.presenter.FloatPresenter;
import org.plazmaforge.framework.core.data.presenter.IntegerPresenter;
import org.plazmaforge.framework.core.data.presenter.StringPresenter;
import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractDataSet extends BaseLocalizedIdentifier {


    // We use presenters like converters to convert value form string to typed value
    private Map<String, ValuePresenter> converters;
    

    private String dataSourceName;

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
    
    /**
     * Initialize data type converters
     */
    protected void initConverts() {
	converters = new HashMap<String, ValuePresenter>();
	converters.put("String", new StringPresenter());
	converters.put("Integer", new IntegerPresenter());
	converters.put("Float", new FloatPresenter());
	converters.put("Double", new DoublePresenter());
    }
    
    protected Object convert(String value, String type) {
	if (converters == null) {
	    initConverts();
	}
	value = StringUtils.normalizeString(value);
	ValuePresenter converter = type == null ? null : converters.get(type);
	Object result = converter == null ? null : converter.toValue(value);
	return result;
    }
    

}

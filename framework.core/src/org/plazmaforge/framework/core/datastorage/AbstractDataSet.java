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
    
    private String dateFormat;

    private String numberFormat;
    

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
    
    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getNumberFormat() {
        return numberFormat;
    }

    public void setNumberFormat(String numberFormat) {
        this.numberFormat = numberFormat;
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
    
    /**
     * Convert string value by type and format
     * @param value
     * @param field
     * @return
     */
    protected Object convertString(String value, DSField field) {
	if (field == null) {
	    return null;
	}
	value = StringUtils.normalizeString(value);
	String type = field.getDataType();
	String format = getFormat(field);
	ValuePresenter converter = getConverter(type, format);
	Object result = converter == null ? null : converter.toValue(value);
	return result;
    }
    
    /**
     * Return converter by type and format
     * @param type
     * @param format
     * @return
     */
    protected ValuePresenter getConverter(String type, String format) {
	if (type == null) {
	    return null;
	}
	String key = null;
	if (format == null) {
	    key = type;
	} else {
	    key = type + "::" + format;
	}
	if (converters == null) {
	    initConverts();
	}
	return converters.get(key);
    }

    /**
     * Return format by field
     * @param field
     * @return
     */
    protected String getFormat(DSField field) {
	if (field == null) {
	    return null;
	}
	String format = null; //field.getFormat() // TODO
	if (format == null) {
	    format = getFormat(field.getDataType());
	}
	return format;
    }
    
    /**
     * Return format by type
     * @param type
     * @return
     */
    protected String getFormat(String type) {
	//TODO
	return null;
    }


    /**
     * Return path of field
     * @param field
     * @return
     */
    protected String getPath(DSField field) {
	if (field == null) {
	    return null;
	}
	String path = field.getPath();
	return path == null ? field.getName() : path; 
    }
  
}

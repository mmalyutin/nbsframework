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

import org.plazmaforge.framework.core.data.BaseLocalizedIdentifier;
import org.plazmaforge.framework.core.data.converter.Converter;
import org.plazmaforge.framework.core.data.converter.ConverterManager;
import org.plazmaforge.framework.util.StringUtils;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractDataSet extends BaseLocalizedIdentifier {


    private ConverterManager converterManager;
    
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
	Converter converter = getConverter("String", type, format);
	Object result = converter == null ? null : converter.convert(value);
	return result;
    }
    
    protected Converter<?, ?> getConverter(String sourceType, String targetType, String format) {
	if (sourceType == null || targetType == null) {
	    return null;
	}
	String name = ConverterManager.getConverterSimpleName(sourceType, targetType);
	if (name == null) {
	    return null;
	}
	return getConverterByName(name, format);
    }
    
    protected Converter<?, ?> getConverterByName(String name, String format) {
	return getConverterManager().getConverter(name, format);
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
  
    protected void initConverterManager() {
	//TODO: Move to converterManager.registerBaseConverterFactories()
	converterManager.registerGenericConveretrFactory(String.class, Integer.class);
	converterManager.registerGenericConveretrFactory(String.class, Long.class);
	converterManager.registerGenericConveretrFactory(String.class, Float.class);
	converterManager.registerGenericConveretrFactory(String.class, Double.class);
	
	converterManager.registerSelfConveretrFactory(String.class);
	converterManager.registerSelfConveretrFactory(Integer.class);
	converterManager.registerSelfConveretrFactory(Float.class);
	converterManager.registerSelfConveretrFactory(Double.class);
    }

    public ConverterManager getConverterManager() {
	if (converterManager == null) {
	    converterManager = new ConverterManager(true);
	    initConverterManager();
	}
        return converterManager;
    }
    
    
}

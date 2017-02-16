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

package org.plazmaforge.framework.core.data.converter.type.other;


import org.plazmaforge.framework.core.data.converter.Converter;
import org.plazmaforge.framework.core.data.converter.ConverterFactory;

public class Boolean2StringConverterFactory implements ConverterFactory<Boolean, String> {

    public Boolean2StringConverterFactory() {
	super();
    }

    @Override
    public Converter<Boolean, String> getConverter() {
	return new Boolean2StringConverter();
    }

    @Override
    public Converter<Boolean, String> getConverter(String format) {
	return new Boolean2StringConverter();
    }

    @Override
    public Converter<Boolean, String> getConverter(String sourceFormat, String targetFormat) {
	return new Boolean2StringConverter();
    }    

}

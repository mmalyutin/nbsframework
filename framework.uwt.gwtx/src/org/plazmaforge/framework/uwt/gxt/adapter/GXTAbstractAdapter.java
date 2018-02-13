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

package org.plazmaforge.framework.uwt.gxt.adapter;

import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.uwt.AbstractUIAdapter;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.Image;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;

/**
 * 
 * @author ohapon
 *
 */
public abstract class GXTAbstractAdapter extends AbstractUIAdapter {
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // RESOURCES
    //
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    

    /**
     * Return hex presentation of color
     * @param color
     * @return
     */
    protected String getColorString(Color color) {
	return  GXTHelper.getColorString(color);
    }

    protected String getRGBColorString(Color color) {
	return  GXTHelper.getRGBColorString(color);
    }

    protected String getRGBAColorString(Color color) {
	return  GXTHelper.getRGBAColorString(color);
    }

    protected String getFontString(Font font) {
	return  GXTHelper.getFontString(font);
    }

    protected String getFontStyle(Font font) {
	return  GXTHelper.getFontStyle(font);
    }

    protected ImageResource createImage(UIElement element, Image image) {
	return GXTHelper.createImage(element, image);
    }

    protected ImageResource createImage(UIElement element, String path) {
	return GXTHelper.createImage(element, path);
    }
    
    protected ImageResource createImage(String storage, String path) {
	return GXTHelper.createImage(storage, path);
    }

    protected ImageResource createImage(String path) {
	return GXTHelper.createImage(path);
    }

    protected NumberFormat getNumberFormat(String pattern) {
	return GXTHelper.getNumberFormat(pattern);
    }
    
    protected NumberPropertyEditor<?> createNumberPropertyEditor(String dataType, String format) {
	Class<?> dataClass = TypeUtils.getClass(dataType);
	return createNumberPropertyEditor(dataClass, format);
    }    

    protected NumberPropertyEditor<?> createNumberPropertyEditor(Class<?> type, String format) {
	return GXTHelper.createNumberPropertyEditor(type, format);
    }

    protected DateTimePropertyEditor createDateTimePropertyEditor(String format) {
	return GXTHelper.createDateTimePropertyEditor(format);
    }
}

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

import org.plazmaforge.framework.uwt.AbstractUIAdapter;
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.Image;

import com.google.gwt.user.client.ui.AbstractImagePrototype;

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

    protected AbstractImagePrototype createImage(UIObject element, Image image) {
	return GXTHelper.createImage(element, image);
    }

    protected AbstractImagePrototype createImage(UIObject element, String path) {
	return GXTHelper.createImage(element, path);
    }
    
    protected AbstractImagePrototype createImage(String storage, String path) {
	return GXTHelper.createImage(storage, path);
    }

    protected AbstractImagePrototype createImage(String path) {
	return GXTHelper.createImage(path);
    }


}

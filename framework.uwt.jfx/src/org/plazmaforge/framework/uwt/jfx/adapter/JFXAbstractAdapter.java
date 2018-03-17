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

package org.plazmaforge.framework.uwt.jfx.adapter;

import org.plazmaforge.framework.core.type.TypeUtils;
import org.plazmaforge.framework.uwt.AbstractUIAdapter;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Frame;

import javafx.scene.control.SpinnerValueFactory;

/**
 * 
 * @author ohapon
 *
 */
public abstract class JFXAbstractAdapter extends AbstractUIAdapter {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // RESOURCES
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Get JFX Color by UWT Color
     * @param color
     * @return
     */
    protected javafx.scene.paint.Color getColor(Color color) {
    	return JFXHelper.getColor(color);
    }

    protected javafx.scene.text.Font getFont(Font font) {
	return JFXHelper.getFont(font);
    }

    protected String toWebString(javafx.scene.paint.Color color) {
    	return JFXHelper.toWebString(color);
    }
    
    protected javafx.scene.image.Image createImage(UIElement element, Image image) {
	return JFXHelper.createImage(element, image);
    }

    protected javafx.scene.image.Image createImage(UIElement element, String path) {
	return JFXHelper.createImage(element, path);	
    }
    
    protected javafx.scene.image.Image createImage(String storage, String path) {
	return JFXHelper.createImage(storage, path);	
    }
    
    protected javafx.scene.image.Image createImage(String path) {
	return JFXHelper.createImage(path);
    }

    ////
    
    protected javafx.scene.image.ImageView createImageView(UIElement element, Image image) {
	return JFXHelper.createImageView(element, image);
    }

    protected javafx.scene.image.ImageView createImageView(UIElement element, String path) {
	return JFXHelper.createImageView(element, path);
    }

    protected javafx.scene.image.ImageView createImageView(String storage, String path) {
	return JFXHelper.createImageView(storage, path);
    }

    protected javafx.scene.image.ImageView createImageView(String path) {
	return JFXHelper.createImageView(path);
    }
      
    ////

    protected SpinnerValueFactory<?> createSpinnerValueFactory(String dataType, Number value, Number minValue, Number maxValue, Number incrementValue) {
	Class<?> dataClass = TypeUtils.getClass(dataType);
	return createSpinnerValueFactory(dataClass, value, minValue, maxValue, incrementValue);
    }
    
    protected SpinnerValueFactory<?> createSpinnerValueFactory(Class<?> type, Number value, Number minValue, Number maxValue, Number incrementValue) {
	return JFXHelper.createSpinnerValueFactory(type, value, minValue, maxValue, incrementValue);
    }
    
    ////
    
    protected javafx.stage.Stage getPrimaryStage(UIElement element) {
	Frame frame = getApplicationFrame(element);
	return frame == null ? null : (javafx.stage.Stage) frame.getDelegate();
    }
}

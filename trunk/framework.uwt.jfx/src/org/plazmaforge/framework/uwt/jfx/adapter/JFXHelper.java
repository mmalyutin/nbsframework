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


import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.jfx.util.JFXUtils;
import org.plazmaforge.framework.uwt.util.StorageUtils;


/**
 * 
 * @author ohapon
 *
 */
public class JFXHelper {

    private JFXHelper() {
    }

    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // RESOURCES
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static javafx.scene.image.Image createImage(UIElement element, Image image) {
	if (image == null) {
	    return null;
	}
	String path = image.getPath();
	return createImage(element, path);
    }

    public static javafx.scene.image.Image createImage(UIElement element, String path) {
	if (path == null) {
	    return null;
	}
	String storage = StorageUtils.getImageStorage(element, path);
	return createImage(storage, path);
    }
    
    public static javafx.scene.image.Image createImage(String storage, String path) {
	String fullPath = StorageUtils.getPath(storage, path);
	return createImage(fullPath);
    }

    public static javafx.scene.image.Image createImage(String path) {
	// TODO: By default we get Image from classpath
	// Must analyze prefixes 'file:', 'classpath:'
	// - classpath:		getClassImageIcon
	// - file: and other	getFileImageIcon
	return path == null ? null : JFXUtils.getClassImage(path);
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static javafx.scene.image.ImageView createImageView(UIElement element, Image image) {
	javafx.scene.image.Image xImage = createImage(element, image);
	if (xImage == null) {
	    return null;
	}
	return new javafx.scene.image.ImageView(xImage);
    }

    public static javafx.scene.image.ImageView createImageView(UIElement element, String path) {
	javafx.scene.image.Image xImage = createImage(element, path);
	if (xImage == null) {
	    return null;
	}
	return new javafx.scene.image.ImageView(xImage);
    }

    public static javafx.scene.image.ImageView createImageView(String storage, String path) {
	javafx.scene.image.Image xImage = createImage(storage, path);
	if (xImage == null) {
	    return null;
	}
	return new javafx.scene.image.ImageView(xImage);
    }

    public static javafx.scene.image.ImageView createImageView(String path) {
	javafx.scene.image.Image xImage = createImage(path);
	if (xImage == null) {
	    return null;
	}
	return new javafx.scene.image.ImageView(xImage);
    }
 
    

}

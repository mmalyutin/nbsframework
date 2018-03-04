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
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
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

    //1, RGB(225, 98, 51), HSL(11, 178, 130)
    //2, RGB(233, 159, 39), HSL(25, 196, 128)
    //3, RGB(99, 184, 99), HSL(80, 90, 133)
    //4, RGB(88, 178, 206), HSL(129, 131, 138)
    //5, RGB(75, 95, 195), HSL(153, 120, 127)
    //6, RGB(148, 71, 191), HSL(186, 116, 123)
    //7, RGB(194, 73, 105), HSL(229, 120, 126)
    //8, RGB(130, 130, 130), HSL(160, 0, 122)
    
    /**
     * Returns JFX Color by UWT Font
     * @param color
     * @return
     */
    public static javafx.scene.paint.Color getColor(Color color) {
 	if (color == null) {
 	    return null;
 	}
 	return javafx.scene.paint.Color.rgb(color.getRed(), color.getGreen(), color.getBlue());
     }

     /**
      * Returns JFX Font by UWT Font
      * @param font
      * @return
      */
     //public static javafx.scene.paint.Font getFont(Font font) {
	 
     //}

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

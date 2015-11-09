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

package org.plazmaforge.framework.uwt.swing.util;

import java.awt.Image;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;



public class SwingUtils {

    
    public static ImageIcon getFileImageIcon(String path) {
	if (path == null) {
	    return getMissingImageIcon();
	}
	return new ImageIcon(path);
    }

    public static ImageIcon getClassImageIcon(String path) {
	if (path != null && path.length() > 0 && path.charAt(0) != '/') {
	    path = "/" + path;
	}
	return getImageIcon(SwingUtils.class, path);
    }
    
    public static ImageIcon getImageIcon(Class<?> clazz, String path) {
	if (path == null) {
	    return getMissingImageIcon();
	}
	URL url = clazz.getResource(path);
	if (url != null) {
	    return new ImageIcon(url);
	}
	return getMissingImageIcon();
    }

    public static ImageIcon getMissingImageIcon() {
	return null;
    }
    
    public static Image getImage(Icon icon) {
	if (icon == null){
	    return null;
	}
	if (icon instanceof ImageIcon) {
	    return ((ImageIcon) icon).getImage();
	}
	
	//TODO: Must convert Icon to Image
	return null;
    }

}

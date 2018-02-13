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

package org.plazmaforge.framework.uwt.swing.adapter;

import org.plazmaforge.framework.uwt.AbstractUIAdapter;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.Image;

/**
 * 
 * @author ohapon
 *
 */
public abstract class SwingAbstractAdapter extends AbstractUIAdapter {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // RESOURCES
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected java.awt.Color getColor(Color color) {
	return SwingHelper.getColor(color);
    }
    
    protected java.awt.Font getFont(Font font) {
	return SwingHelper.getFont(font);
    }

    
    protected javax.swing.ImageIcon createImageIcon(UIElement element, Image image) {
	return SwingHelper.createImageIcon(element, image);
    }

    protected javax.swing.ImageIcon createImageIcon(UIElement element, String path) {
	return SwingHelper.createImageIcon(element, path);
    }
    
    protected javax.swing.ImageIcon createImageIcon(String storage, String path) {
	return SwingHelper.createImageIcon(storage, path);
    }

    protected javax.swing.ImageIcon createImageIcon(String path) {
	return SwingHelper.createImageIcon(path);
    }

}

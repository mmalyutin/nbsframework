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

package org.plazmaforge.framework.uwt.widget;

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

public class DefaultCellRenderer implements CellRenderer {

    /**
     * Background color of cell
     */
    private Color background;
    
    /**
     * Foreground color of cell
     */
    private Color foreground;
  
    /**
     * Font of cell
     */
    private Font font;
    
    
    @Override
    public void render(CellContext cellContext) {
	
	// Transfer attributes
	cellContext.setBackground(background);
	cellContext.setForeground(foreground);
	cellContext.setFont(font);
	
	return;
    }


    public Color getBackground() {
        return background;
    }


    public void setBackground(Color background) {
        this.background = background;
    }


    public Color getForeground() {
        return foreground;
    }


    public void setForeground(Color foreground) {
        this.foreground = foreground;
    }


    public Font getFont() {
        return font;
    }


    public void setFont(Font font) {
        this.font = font;
    }

}

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

package org.plazmaforge.framework.report.export;

import org.plazmaforge.framework.report.model.base.Pen;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;

/**
 * 
 * @author ohapon
 *
 */
public abstract class AbstractBaseExporter extends AbstractReportExporter {

    public static int DEFAULT_BORDER_WIDTH = 1;
    
    public static Color DEFAULT_BORDER_COLOR = Color.BLACK;
    
    
    protected Color parentBackground;
    protected Color parentForeground;
    protected Font parentFont;
	
    protected Color background;
    protected Color foreground;
    protected Font font;

    
    protected Color getColor(Color color, Color parentColor) {
	//return color == null ? parentColor : color;
	return getColor(color, parentColor, null);
    }

    protected Color getColor(Color color1, Color color2, Color color3) {
	// find first not null color
	if (color1 != null) {
	    return color1;
	}
	if (color2 != null) {
	    return color2;
	}
	return color3;
    }

    protected Font getFont(Font font, Font parentFont) {
	/*
	if (font == null && parentFont == null) {
	    return null;
	}
	
	if (font == null) {
	    font = parentFont;
	}
	if (font.isComplete()) {
	    return font;
	}
	
	// merge incomplete font
	String name = null;
	int size = 0;
	int style = 0;
	
	// font name
	if (font.isEmptyName()) {
	    if (parentFont != null && !parentFont.isEmptyName()) {
		name = parentFont.getName();
	    } else {
		name = "";
	    }
	} else {
	    name = font.getName();
	}

	// font size
	if (font.isEmptySize()) {
	    if (parentFont != null && !parentFont.isEmptySize()) {
		size = parentFont.getSize();
	    } else {
		size = 12; // TODO
	    }
	} else {
	    size = font.getSize();
	}
	
	// font style
	style = font.getStyle();
	
	return new Font(name , size, style);
	*/
	
	return getFont(font, parentFont, null);
    }
    
    protected Font getFont(Font font1, Font font2, Font font3) {
	// find first not null font
	Font font = font1;
	if (font == null) {
	    font = font2;
	}
	if (font == null) {
	    font = font3;
	}
	if (font == null) {
	    return null;
	}
	if (font.isComplete()) {
	    return font;
	}
	
	// merge incomplete font
	String name = null;
	int size = 0;
	int style = 0;
	

	// font name
	name = getStrongFontName(font1);
	if (name == null) {
	    name = getStrongFontName(font2);
	}
	if (name == null) {
	    name = getStrongFontName(font3);
	}
	if (name == null) {
	    name = "";
	}
	
	// font size
	size = getStrongFontSize(font1);
	if (size == 0) {
	    size = getStrongFontSize(font2);
	}
	if (size == 0) {
	    size = getStrongFontSize(font3);
	}
	if (size == 0) {
	    size = 12;
	}
	
	// font style
	style = font.getStyle();
	
	return new Font(name , size, style);
    }
    
    protected String getStrongFontName(Font font) {
	return (font == null || font.isEmptyName()) ? null : font.getName(); 
    }

    protected int getStrongFontSize(Font font) {
	return font == null ? 0 : font.getSize(); 
    }

    protected void normalizeCurrentStyle() {
	background = getColor(background, parentBackground);
	foreground = getColor(foreground, parentForeground);
	font = getFont(font, parentFont);
    }

    protected int getLineWidth(Pen pen) {
	if (pen == null || pen.isEmpty()) {
	    return 0;
	}
	int w = pen.getLineWidth();
	if (w <= 0) {
	    w = DEFAULT_BORDER_WIDTH;
	}
	return w;
    }

    protected Color getLineColor(Pen pen) {
	if (pen == null) {
	    return null;
	}
	return getColor(pen.getLineColor(), DEFAULT_BORDER_COLOR);
    }
    
}

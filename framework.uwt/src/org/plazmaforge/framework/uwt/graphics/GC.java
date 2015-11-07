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

package org.plazmaforge.framework.uwt.graphics;

import org.plazmaforge.framework.uwt.UIObject;

/**
 * 
 * Graphics context
 * 
 * AWT: java.awt.Graphics/Graphics2D
 * SWT: org.eclipse.swt.graphics.GC
 * GWT: com.google.gwt.canvas.dom.client.Context2d
 * 
 * @author ohapon
 *
 */
public final class GC extends UIObject {


    public static final  Color DEFAULT_BACKGROUND = Color.WHITE;
    
    public static final  Color DEFAULT_FOREGROUND = Color.BLACK;
    
    public static final  Font DEFAULT_FONT = new Font("Verdana", 10); // TODO

    
    
    public static final String METHOD_DRAW_POINT = "drawPoint";
    
    public static final String METHOD_DRAW_LINE = "drawLine";
    
    public static final String METHOD_DRAW_RECTANGLE = "drawRectangle";
    
    public static final String METHOD_DRAW_ELLIPSE = "drawEllipse";
    
    public static final String METHOD_DRAW_ARC = "drawArc";
    
    public static final String METHOD_DRAW_TEXT = "drawText";
    

    public static final String METHOD_FILL_RECTANGLE = "fillRectangle";
    
    public static final String METHOD_FILL_ELLIPSE = "fillEllipse";
    
    public static final String METHOD_FILL_ARC = "fillArc";

    
    
    public static final String METHOD_SET_BACKGROUND = "setBackground";
    
    public static final String METHOD_SET_FOREGROUND = "setForeground";
    
    public static final String METHOD_SET_FONT = "setFont";
    
    
    public static final String METHOD_GET_STRING_WIDTH = "getStringWidth";
    
    public static final String METHOD_GET_STRING_SIZE = "getStringSize";
    
    
    

    private Color background = DEFAULT_BACKGROUND;
    
    private Color foreground = DEFAULT_FOREGROUND;
    
    
    private Font font = DEFAULT_FONT;  
    

    public void setBackground(Color background) {
	this.background = background;
	getAdapter().invoke(this, METHOD_SET_BACKGROUND, new Object[] {background});
    }

    public void setForeground(Color foreground) {
	this.foreground = foreground;
	getAdapter().invoke(this, METHOD_SET_FOREGROUND, new Object[] {foreground});
    }

    public Color getBackground() {
	if (background == null) {
	    return DEFAULT_BACKGROUND;
	}
        return background;
    }

    public Color getForeground() {
	if (foreground == null) {
	    return DEFAULT_FOREGROUND;
	}
        return foreground;
    }
    
    public Font getFont() {
	if (font == null) {
	    return DEFAULT_FONT;
	}
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
        getAdapter().invoke(this, METHOD_SET_FONT, new Object[] {font});
    }

    public int getStringWidth(String string) {
	if (string == null) {
	    return 0;
	}
	return (Integer) getAdapter().invoke(this, METHOD_GET_STRING_WIDTH, new Object[] {string});
    }
    
    public Size getStringSize(String string) {
	if (string == null) {
	    return new Size(0, 0);
	}
	return (Size) getAdapter().invoke(this, METHOD_GET_STRING_SIZE, new Object[] {string});
    } 
    
    ////
    
    public void drawPoint(int x, int y) {
	getAdapter().invoke(this, METHOD_DRAW_POINT, new Object[] {x, y});
    }

    public void drawLine(int x1, int y1, int x2, int y2) {
	getAdapter().invoke(this, METHOD_DRAW_LINE, new Object[] {x1, y1, x2, y2});
    }

    public void drawRectangle(int x1, int y1, int width, int height) {
	getAdapter().invoke(this, METHOD_DRAW_RECTANGLE, new Object[] {x1, y1, width, height});
    }

    public void drawEllipse(int x, int y, int width, int height) {
	getAdapter().invoke(this, METHOD_DRAW_ELLIPSE, new Object[] {x, y, width, height});
    }
    
    public void drawCircle(int x, int y, int width) {
	drawEllipse(x, y, width, width);
    }

    public void drawCircleR(int ox, int oy, int radius) {
	drawEllipse(ox - radius, oy - radius, radius * 2, radius * 2);
    }

    public void drawArc(int x, int y, int width, int height, int startAngle, int endAngle) {
	getAdapter().invoke(this, METHOD_DRAW_ARC, new Object[] {x, y, width, height, startAngle, endAngle});
    }

    ////
    
    public void drawText(String text,int x, int y) {
	getAdapter().invoke(this, METHOD_DRAW_TEXT, new Object[] {text, x, y});
    }

    public void drawText(String text,int x, int y, int angle) {
	getAdapter().invoke(this, METHOD_DRAW_TEXT, new Object[] {text, x, y, angle});
    }

    ////

    public void fillRectangle(int x1, int y1, int width, int height) {
	getAdapter().invoke(this, METHOD_FILL_RECTANGLE, new Object[] {x1, y1, width, height});
    }

    public void fillEllipse(int x1, int y1, int width, int height) {
	getAdapter().invoke(this, METHOD_FILL_ELLIPSE, new Object[] {x1, y1, width, height});
    }
    
    public void fillCircle(int x, int y, int width) {
	fillEllipse(x, y, width, width);
    }
    
    public void fillCircleR(int ox, int oy, int radius) {
	fillEllipse(ox - radius, oy - radius, radius * 2, radius * 2);
    }
    

    public void fillArc(int x, int y, int width, int height, int startAngle, int endAngle) {
	getAdapter().invoke(this, METHOD_FILL_ARC, new Object[] {x, y, width, height, startAngle, endAngle});
    }



}

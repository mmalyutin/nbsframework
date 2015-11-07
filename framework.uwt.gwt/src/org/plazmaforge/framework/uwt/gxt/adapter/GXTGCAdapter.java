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

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.graphics.Size;

import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.canvas.dom.client.FillStrokeStyle;

public class GXTGCAdapter extends GXTAbstractAdapter {

    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	return null;
    }

    @Override
    public void disposeDelegate(UIObject parent, UIObject element) {
    }

    @Override
    public Object invoke(UIObject element, String methodName, Object[] args) {
	if (element == null) {
	    return null;
	}
	
	GC gc = (GC) element;
	com.google.gwt.canvas.dom.client.Context2d xGC = (com.google.gwt.canvas.dom.client.Context2d) element.getDelegate();
	if (xGC == null) {
	    return null;
	}
	
	// Background/Foreground
	if (eq(methodName, GC.METHOD_SET_BACKGROUND)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length == 1) {
		 Color color = (Color) args[0];
		 // WARNING! We have problems with convert to rgba() in dev mode. 
		 CssColor fillStyle = CssColor.make(getRGBColorString(color));
		 xGC.setFillStyle(fillStyle);
	    }
	    return null;

	} else if (eq(methodName, GC.METHOD_SET_FOREGROUND)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length == 1) {
		 Color color = (Color) args[0];
		 CssColor strokeStyle = CssColor.make(getRGBColorString(color));
		 xGC.setStrokeStyle(strokeStyle);
	    }
	    return null;
	    
	} else if (eq(methodName, GC.METHOD_SET_FONT)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length == 1) {
		 Font font = (Font) args[0];
		 xGC.setFont(getFontString(font));
	    }
	    return null;	    

	} else if (eq(methodName, GC.METHOD_GET_STRING_WIDTH)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length == 1) {
		 String string = (String) args[0];
		 return (int) xGC.measureText(string).getWidth();
	    }
	    return null;
	} else if (eq(methodName, GC.METHOD_GET_STRING_SIZE)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length == 1) {
		String string = (String) args[0];
		int width = (int) xGC.measureText(string).getWidth();

		// TODO
		Integer fontHeight = (Integer) gc.getData("gc.fontSize");
		if (fontHeight == null) {
		    fontHeight = getFontSize(xGC.getFont());
		    gc.setData("gc.fontSize", fontHeight);
		}
		int height = fontHeight;
		return new Size(width, height);
	    }
	    return null;
	        	    
	//Draw    
	} else if (eq(methodName, GC.METHOD_DRAW_LINE)) {
	    
	    if (args == null) {
		return null;
	    }
	    if (args.length == 4 ) {
		int x1 = intValue(args[0]);
		int y1 = intValue(args[1]);
		int x2 = intValue(args[2]);
		int y2 = intValue(args[3]);
		
		xGC.beginPath();
		xGC.moveTo(x1, y1);
		xGC.lineTo(x2, y2);
		xGC.closePath();
		xGC.stroke(); 
	    }
	    return null;
	} else if (eq(methodName, GC.METHOD_DRAW_RECTANGLE)) { 
	    if (args == null) {
		return null;
	    }
	    if (args.length == 4) {
		int x = intValue(args[0]);
		int y = intValue(args[1]);
		int width = intValue(args[2]);
		int height = intValue(args[3]);
		xGC.beginPath();
		xGC.rect(x, y, width, height);
		xGC.closePath();
		xGC.stroke(); 
	    }
	    return null;
	} else if (eq(methodName, GC.METHOD_DRAW_ELLIPSE)) { 
	    if (args == null) {
		return null;
	    }
	    if (args.length == 4) {
		int x = intValue(args[0]);
		int y = intValue(args[1]);
		int width = intValue(args[2]);
		int height = intValue(args[3]);
		xGC.beginPath();
		drawEllipse(xGC, x, y, width, height);
		xGC.closePath();
		xGC.stroke(); 
	    }
	    return null;
	} else if (eq(methodName, GC.METHOD_DRAW_ARC)) { 
	    if (args == null) {
		return null;
	    }
	    if (args.length == 6) {
		int x = intValue(args[0]);
		int y = intValue(args[1]);
		int width = intValue(args[2]);
		int height = intValue(args[3]);
		
		int startAngle = intValue(args[4]);
		int endAngle = intValue(args[5]);
		
		xGC.beginPath();
		drawArc(xGC, x, y, width, height, startAngle, endAngle);
		xGC.stroke(); 
	    }
	    return null;
	} else if (eq(methodName, GC.METHOD_DRAW_TEXT)) { 
	    if (args == null) {
		return null;
	    }
	    if (args.length == 3) {
		String text = getString(args[0]);
		int x = intValue(args[1]);
		int y = intValue(args[2]);
		xGC.beginPath();
		
		FillStrokeStyle fillStyle = xGC.getFillStyle();
		FillStrokeStyle strokeStyle = xGC.getStrokeStyle();
		
		// Set fill style because we use 'fillText' to draw text
		//int fontHeight = 10; 		
		Integer fontHeight = (Integer) gc.getData("gc.fontSize");
		if (fontHeight == null) {
		    fontHeight = getFontSize(xGC.getFont());
		    gc.setData("gc.fontSize", fontHeight);
		}
		xGC.setFillStyle(strokeStyle);
		xGC.fillText(text, x, y + fontHeight);
		xGC.closePath();
		xGC.fill();
		
		// Restore fill style
		xGC.setFillStyle(fillStyle);
		
	    } else if (args.length == 4) {
		String text = getString(args[0]);
		int x = intValue(args[1]);
		int y = intValue(args[2]);
		int angle = intValue(args[3]);
		
		xGC.beginPath();
		
		FillStrokeStyle fillStyle = xGC.getFillStyle();
		FillStrokeStyle strokeStyle = xGC.getStrokeStyle();
		
		// Set fill style because we use 'fillText' to draw text
		//int fontHeight = 10; 		
		Integer fontHeight = (Integer) gc.getData("gc.fontSize");
		if (fontHeight == null) {
		    fontHeight = getFontSize(xGC.getFont());
		    gc.setData("gc.fontSize", fontHeight);
		}
		xGC.setFillStyle(strokeStyle);
		
		//
		xGC.translate(x + fontHeight, y);
		xGC.rotate(toRadian(angle) * (-1));
		//
		
		//xGC.fillText(text, x, y + fontHeight);
		xGC.fillText(text, 0, 0);
		
		xGC.closePath();
		xGC.fill();
		
		// Restore fill style
		xGC.setFillStyle(fillStyle);
		
	    }

	    return null;
	    
	//Fill    
	} else if (eq(methodName, GC.METHOD_FILL_RECTANGLE)) { 
	    if (args == null) {
		return null;
	    }
	    if (args.length == 4) {
		
		int x = intValue(args[0]);
		int y = intValue(args[1]);
		int width = intValue(args[2]);
		int height = intValue(args[3]);
		
		xGC.beginPath();
		xGC.rect(x, y, width, height);
		xGC.closePath();
		xGC.fill(); 
	    }
	    return null;
	} else if (eq(methodName, GC.METHOD_FILL_ELLIPSE)) { 
	    if (args == null) {
		return null;
	    }
	    if (args.length == 4) {
		int x = intValue(args[0]);
		int y = intValue(args[1]);
		int width = intValue(args[2]);
		int height = intValue(args[3]);
		
		xGC.beginPath();
		drawEllipse(xGC, x, y, width, height);
		xGC.closePath();
		xGC.fill();
		
	    }
	    return null;
	} else if (eq(methodName, GC.METHOD_FILL_ARC)) { 
	    if (args == null) {
		return null;
	    }
	    if (args.length == 6) {
		int x = intValue(args[0]);
		int y = intValue(args[1]);
		int width = intValue(args[2]);
		int height = intValue(args[3]);
		
		int startAngle = intValue(args[4]);
		int endAngle = intValue(args[5]);
		
		xGC.beginPath();
		drawArc(xGC, x, y, width, height, startAngle, endAngle, true);
		xGC.fill(); 
	    }
	    return null;
	}

	return null;
    }
    
    protected void drawEllipse(com.google.gwt.canvas.dom.client.Context2d xGC, int x, int y, int width, int height) {
	float kappa = 0.5522848f;
	int ox = (int) ((width / 2.0) * kappa);		// control point offset horizontal
	int oy = (int) ((height / 2.0) * kappa);	// control point offset vertical
	int xe = x + width;				// x-end
	int ye = y + height;				// y-end
	int xm = x + (int) (width / 2.0);		// x-middle
	int ym = y + (int) (height / 2.0);		// y-middle

	xGC.moveTo(x, ym);
	xGC.bezierCurveTo(x, ym - oy, xm - ox, y, xm, y);
	xGC.bezierCurveTo(xm + ox, y, xe, ym - oy, xe, ym);
	xGC.bezierCurveTo(xe, ym + oy, xm + ox, ye, xm, ye);
	xGC.bezierCurveTo(xm - ox, ye, x, ym + oy, x, ym);
	
	//http://www.w3schools.com/tags/canvas_beziercurveto.asp
	//http://www.rgraph.net/blog/2013/january/html5-canvas-ellipse.html
	//http://www.w3schools.com/tags/canvas_beziercurveto.asp
	
    }
    
    protected void drawArc(com.google.gwt.canvas.dom.client.Context2d xGC, int x, int y, int width, int height, int startAngle, int endAngle) {
	drawArc(xGC, x, y, width, height, startAngle, endAngle, false);
    }
    
    protected void drawArc(com.google.gwt.canvas.dom.client.Context2d xGC, int x, int y, int width, int height, int startAngle, int endAngle, boolean fill) {
	double rx = width / 2;
	double ry = height / 2;
	double ox = x + rx;
	double oy = y + ry;
	double startRadian = toRadian(startAngle) * (-1);
	double endRadian = toRadian(endAngle) * (-1);
	if (fill) {
	    // Add point (center of arc) to fill path 
	    xGC.moveTo(ox, oy);
	}
	xGC.arc(ox, oy, rx, startRadian, endRadian, true);
    }
    
    protected double toRadian(int degrees) {
	return degrees * Math.PI / 180;
    }
    
    protected int getFontSize(String fontString) {
	//TODO
	if (fontString == null) {
	    return 0;
	}
	String[] array = fontString.split(" ");
	String sizeString = null;
	for (String s: array) {
	    if (s.length() > 0 && Character.isDigit(s.charAt(0))) {
		sizeString = s;
		break;
	    }
	}
	int size = 0;
	if (sizeString.endsWith("px")) {
	    if (sizeString.length() > 2) {
		try {
		    size = Integer.valueOf(sizeString.substring(0, sizeString.length() - 2));
		} catch (Exception e) {
		    
		}
	    }
	}
	return size;
	
    }

}

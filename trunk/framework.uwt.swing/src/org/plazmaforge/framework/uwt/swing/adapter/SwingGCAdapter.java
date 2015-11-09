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

import java.awt.geom.AffineTransform;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.graphics.Size;
import org.plazmaforge.framework.uwt.swing.util.SwingGCUtils;

public class SwingGCAdapter extends SwingAbstractAdapter {

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
	
	java.awt.Graphics xGC = (java.awt.Graphics) element.getDelegate();
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
		 xGC.setColor(getColor(color)); //TODO
	    }
	    return null;

	} else if (eq(methodName, GC.METHOD_SET_FOREGROUND)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length == 1) {
		 Color color = (Color) args[0];
		 xGC.setColor(getColor(color)); // TODO
	    }
	    return null;
	} else if (eq(methodName, GC.METHOD_SET_FONT)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length == 1) {
		 Font font = (Font) args[0];
		 xGC.setFont(getFont(font));
	    }
	    return null;
	} else if (eq(methodName, GC.METHOD_GET_STRING_WIDTH)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length == 1) {
		 String string = (String) args[0];
		 return (int) SwingGCUtils.getStringWidth(string, (java.awt.Graphics2D) xGC);
	    }
	    return null;
	} else if (eq(methodName, GC.METHOD_GET_STRING_SIZE)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length == 1) {
		 String string = (String) args[0];
		 java.awt.geom.Rectangle2D bounds =  SwingGCUtils.getTextBounds(string, (java.awt.Graphics2D) xGC);
		 return new Size((int) bounds.getWidth(), (int) bounds.getHeight());
	    }
	    return null;
	    
	// Draw    
	} else if (eq(methodName, GC.METHOD_DRAW_LINE)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length == 4 ) {
		int x1 = intValue(args[0]);
		int y1 = intValue(args[1]);
		int x2 = intValue(args[2]);
		int y2 = intValue(args[3]);
		setForeground(xGC, gc);
		xGC.drawLine(x1, y1, x2, y2);
	    }
	} else if (eq(methodName, GC.METHOD_DRAW_RECTANGLE)) { 
	    if (args == null) {
		return null;
	    }
	    if (args.length == 4) {
		int x = intValue(args[0]);
		int y = intValue(args[1]);
		int width = intValue(args[2]);
		int height = intValue(args[3]);
		setForeground(xGC, gc);
		xGC.drawRect(x, y, width, height);
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
		setForeground(xGC, gc);
		xGC.drawOval(x, y, width, height);
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
		
		setForeground(xGC, gc);
		xGC.drawArc(x, y, width, height, startAngle, endAngle - startAngle); // angle delta		
	    }
	    return null;

	} else if (eq(methodName, GC.METHOD_DRAW_TEXT)) { 
	    if (args == null) {
		return null;
	    }
	    if (args.length == 3) {
		int fontHeight = xGC.getFont().getSize();
		String text = getString(args[0]);
		int x = intValue(args[1]);
		int y = intValue(args[2]) + fontHeight;
		setForeground(xGC, gc);
		xGC.drawString(text, x, y);
	    } else if (args.length == 4) {
		int fontHeight = xGC.getFont().getSize();
		String text = getString(args[0]);
		int x = intValue(args[1]) + fontHeight;     // TODO: WHY(?) fontHeight
		int y = intValue(args[2]) /*+ fontHeight*/; // TODO: WHY(?) fontHeight
		int angle = intValue(args[3]);
		setForeground(xGC, gc);
		drawText((java.awt.Graphics2D) xGC, text, x, y, angle);
	    }
	    return null;
	    
	    
	// Fill    
	} else if (eq(methodName, GC.METHOD_FILL_RECTANGLE)) { 
	    if (args == null) {
		return null;
	    }
	    if (args.length == 4) {
		int x = intValue(args[0]);
		int y = intValue(args[1]);
		int width = intValue(args[2]);
		int height = intValue(args[3]);
		setBackground(xGC, gc);
		xGC.fillRect(x, y, width, height);
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
		setBackground(xGC, gc);
		xGC.fillOval(x, y, width, height);
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
		
		setBackground(xGC, gc);
		xGC.fillArc(x, y, width, height, startAngle, endAngle - startAngle); // angle delta
	    }
	    return null;
	}
	
	
	return null;
    }

    protected void setBackground(java.awt.Graphics xGC, GC gc) {
	if (xGC == null || gc == null||  gc.getBackground() == null)  {
	    return;
	}
	xGC.setColor(getColor(gc.getBackground()));
    }
    
    protected void setForeground(java.awt.Graphics xGC, GC gc) {
	if (xGC == null || gc == null||  gc.getForeground() == null)  {
	    return;
	}
	xGC.setColor(getColor(gc.getForeground()));
    }
    

    protected void drawText(java.awt.Graphics2D gc, String text, int x, int y, int angle) {
	if (text == null) {
	    return;
	}
	
	AffineTransform transform = new AffineTransform();
	transform.translate(x, y);

	// Convert to radian
	double radian = (angle * Math.PI / 180) * (-1);
	transform.rotate(radian);
	gc.setTransform(transform);
	
	gc.drawString(text, 0, 0);
	
    }
}

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

package org.plazmaforge.framework.uwt.swt.adapter;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.graphics.Size;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

public class SWTGCAdapter extends SWTAbstractAdapter {

    @Override
    public Object createDelegate(UIElement parent, UIElement element) {
	return null;
    }

    @Override
    public void disposeDelegate(UIElement parent, UIElement element) {
    }

    
    @Override
    public Object invoke(UIElement element, String methodName, Object[] args) {
	if (element == null) {
	    return null;
	}
	
	GC gc = (GC) element;
	
	org.eclipse.swt.graphics.GC xGC = (org.eclipse.swt.graphics.GC) element.getDelegate();
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
		 xGC.setBackground(getColor(color));
		 int alpha = SWTHelper.get255ColorAlpha(color.getAlpha());
		 xGC.setAlpha(alpha);
	    }
	    return null;

	} else if (eq(methodName, GC.METHOD_SET_FOREGROUND)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length == 1) {
		 Color color = (Color) args[0];
		 xGC.setForeground(getColor(color));
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
		 return xGC.stringExtent(string).x;
	    }
	    return null;
	} else if (eq(methodName, GC.METHOD_GET_STRING_SIZE)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length == 1) {
		 String string = (String) args[0];
		 org.eclipse.swt.graphics.Point point =  xGC.stringExtent(string);
		 return new Size(point.x, point.y);
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
		setForegroundAlpha(xGC, gc);
		xGC.drawLine(x1, y1, x2, y2);
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
		setForegroundAlpha(xGC, gc);
		xGC.drawRectangle(x, y, width, height);
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
		setForegroundAlpha(xGC, gc);
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
		
		setForegroundAlpha(xGC, gc);
		xGC.drawArc(x, y, width, height, startAngle, endAngle - startAngle); // angle delta
		
	    }
	    return null;

	} else if (eq(methodName, GC.METHOD_DRAW_TEXT)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length == 3) {
		//System.out.println("Text Font: size=" + xGC.getFont().getFontData()[0].getHeight() + ", name=" + xGC.getFont().getFontData()[0].getName());
		String text = asString(args[0]);
		int x = intValue(args[1]);
		int y = intValue(args[2]);
		setForegroundAlpha(xGC, gc);
		//xGC.drawText(text, x, y, true); // true - without filling background
		drawText(gc, xGC, text, x, y);
	    } else if (args.length == 4) {
		String text = asString(args[0]);
		int x = intValue(args[1]);
		int y = intValue(args[2]);
		int angle = intValue(args[3]);
		setForegroundAlpha(xGC, gc);
		drawText(xGC, text, x, y, angle); // true - without filling background
	    }
	    return null;

	} else if (eq(methodName, GC.METHOD_DRAW_TEXT_BOX)) {
	    if (args == null) {
		return null;
	    }
	    if (args.length >= 5) {
		String text = asString(args[0]);
		int x = intValue(args[1]);
		int y = intValue(args[2]);
		int width = intValue(args[3]);
		int height = intValue(args[4]);
		HorizontalAlign horizontalAlign = args.length > 5 ? (HorizontalAlign) args[5] : null;
		VerticalAlign verticalAlign = args.length > 6 ? (VerticalAlign) args[6] : null;
		setForegroundAlpha(xGC, gc);
		drawTextBox(gc, xGC, text, x, y, width, height, horizontalAlign, verticalAlign); 
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
		setBackgroundAlpha(xGC, gc);
		xGC.fillRectangle(x, y, width, height);
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
		setBackgroundAlpha(xGC, gc);
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
		
		setBackgroundAlpha(xGC, gc);
		xGC.fillArc(x, y, width, height, startAngle, endAngle - startAngle); // angle delta
	    }
	    return null;
	}

	
	return null;
    }

    protected void setBackgroundAlpha(org.eclipse.swt.graphics.GC xGC, GC gc) {
	if (xGC == null || gc == null || gc.getBackground() == null) {
	    return;
	}
	int alpha = SWTHelper.get255ColorAlpha(gc.getBackground().getAlpha());
	xGC.setAlpha(alpha);
    }
       
    protected void setForegroundAlpha(org.eclipse.swt.graphics.GC xGC, GC gc) {
	if (xGC == null || gc == null || gc.getForeground() == null) {
	    return;
	}
	int alpha = SWTHelper.get255ColorAlpha(gc.getForeground().getAlpha());
	xGC.setAlpha(alpha);
    }       
 
    protected Path createPath() {
	return new Path(Display.getDefault());
    }
    
    protected void drawText(org.eclipse.swt.graphics.GC gc, String text, int x, int y, int angle) {
	if (text == null) {
	    return;
	}
	org.eclipse.swt.graphics.Transform transform = new org.eclipse.swt.graphics.Transform(Display.getDefault());
	
	transform.translate(x, y);
	transform.rotate((-1) * angle); // Reverse angle rotation
	
	gc.setTransform(transform);
	
	gc.drawText(text, 0, 0, true);
	
	transform.dispose();
	
	gc.setTransform(null);
	
	
    }
    
    protected void drawText(GC gc, org.eclipse.swt.graphics.GC xGC, String text, int x, int y) {
	if (text == null){
	    return;
	}
	boolean underline = false;
	boolean strikeout = false;
	Font font = gc.getFont();
	underline = font != null && font.isUnderline();
	strikeout = font != null && font.isStrikeout();

	if (!underline && !strikeout) {
	    xGC.drawText(text, x, y, true);
	    return;

	}
	
	TextLayout textLayout = getTextLayout(xGC);
	//int textWidth = xGC.stringExtent(text).x;
	//textLayout.setWidth(textWidth + 10);
	textLayout.setText(text);
	
	TextStyle textStyle = getTextStyle(xGC);
	textStyle.underline = underline;
	textStyle.strikeout = strikeout;

	textLayout.setStyle(textStyle, 0, text.length() - 1);
	
	
	textLayout.draw(xGC, x, y);
	
	textLayout.dispose();
    }

    protected TextLayout getTextLayout(org.eclipse.swt.graphics.GC xGC) {
	TextLayout layout = new TextLayout(xGC.getDevice());
	return layout;
    }
    
    protected TextStyle getTextStyle(org.eclipse.swt.graphics.GC xGC) {
	TextStyle textStyle = new TextStyle(xGC.getFont(), null, null);
	return textStyle;
    }
    
    protected void drawTextBox(GC gc, org.eclipse.swt.graphics.GC xGC, String text, int x, int y, int width, int height, HorizontalAlign horizontalAlign, VerticalAlign verticalAlign) {
	if (text == null) {
	    return;
	}
	boolean underline = false;
	boolean strikeout = false;
	Font font = gc.getFont();
	underline = font != null && font.isUnderline();
	strikeout = font != null && font.isStrikeout();
	
	// horizontal alignment: left, center, right
	if (horizontalAlign == null || horizontalAlign == HorizontalAlign.FILL) {
	    horizontalAlign = HorizontalAlign.LEFT;
	}
	int aligment = SWT.LEFT;
	if (horizontalAlign == HorizontalAlign.CENTER) {
	    aligment = SWT.CENTER;
	} else if (horizontalAlign == HorizontalAlign.RIGHT) {
	    aligment = SWT.RIGHT;
	}

	// vertical alignment: top, middle, bottom
	if (verticalAlign == null || verticalAlign == VerticalAlign.FILL) {
	    verticalAlign = VerticalAlign.TOP;
	}
	
	
	// create text layout
	TextLayout textLayout = getTextLayout(xGC);
	textLayout.setText(text);
	textLayout.setWidth(width);
	
	// horizontal alignment
	textLayout.setAlignment(aligment);
	
	int textWidth = width;
	int textHeight = 0;
	//int textHeight = textLayout.getBounds().height;
	
	// calculate text height
	int lineCount = textLayout.getLineCount();
	for (int i = 0; i < lineCount; i++) {
	    textHeight += textLayout.getLineBounds(i).height;
	}
	
	int offsetY = 0;
	if (textHeight < height) {
	    if (verticalAlign == VerticalAlign.MIDDLE) {
		offsetY = (height - textHeight) / 2;
	    } else if  (verticalAlign == VerticalAlign.BOTTOM) {
		offsetY = height - textHeight;
	    }
	}
	
	
	// create text style
	TextStyle textStyle = getTextStyle(xGC);
	textStyle.underline = underline;
	textStyle.strikeout = strikeout;

	textLayout.setStyle(textStyle, 0, text.length() - 1);

	// draw text (clip)
	xGC.setClipping(x, y, width, height);
	textLayout.draw(xGC, x, y + offsetY);
	xGC.setClipping((Rectangle) null);
	
	textLayout.dispose();
	
    }
}

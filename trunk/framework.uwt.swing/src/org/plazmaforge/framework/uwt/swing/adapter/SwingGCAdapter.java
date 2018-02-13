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

import java.awt.Shape;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.graphics.Size;
import org.plazmaforge.framework.uwt.swing.util.SwingGCUtils;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

public class SwingGCAdapter extends SwingAbstractAdapter {

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
		String text = asString(args[0]);
		int x = intValue(args[1]);
		int y = intValue(args[2]) + fontHeight;
		setForeground(xGC, gc);
		xGC.drawString(text, x, y);
	    } else if (args.length == 4) {
		int fontHeight = xGC.getFont().getSize();
		String text = asString(args[0]);
		int x = intValue(args[1]) + fontHeight;     // TODO: WHY(?) fontHeight
		int y = intValue(args[2]) /*+ fontHeight*/; // TODO: WHY(?) fontHeight
		int angle = intValue(args[3]);
		setForeground(xGC, gc);
		drawText((java.awt.Graphics2D) xGC, text, x, y, angle);
	    }
	    return null;

	} else if (eq(methodName, GC.METHOD_DRAW_TEXT_BOX)) { 
	    if (args == null) {
		return null;
	    }
	    if (args.length >= 5) {
		int fontHeight = xGC.getFont().getSize();
		String text = asString(args[0]);
		int x = intValue(args[1]);
		int y = intValue(args[2]);// + fontHeight; //TODO
		int width = intValue(args[3]);
		int height = intValue(args[4]);
		HorizontalAlign horizontalAlign = args.length > 5 ? (HorizontalAlign) args[5] : null;
		VerticalAlign verticalAlign = args.length > 6 ? (VerticalAlign) args[6] : null;
		setForeground(xGC, gc);
		drawTextBox((java.awt.Graphics2D) xGC, text, x, y, width, height, horizontalAlign, verticalAlign);
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
    
    protected void drawTextBox(java.awt.Graphics2D gc, String text, int x, int y, int width, int height, HorizontalAlign horizontalAlign, VerticalAlign verticalAlign) {
	if (text == null) {
	    return;
	}
	
	int start = 0;
	int end	= text.length();
	java.awt.Font font = gc.getFont();
	java.awt.Color color = gc.getColor();
	
	Map<TextAttribute, ?> m = font.getAttributes();
	Object underlineObject = m.get(TextAttribute.UNDERLINE);
	Object strikeoutObject = m.get(TextAttribute.STRIKETHROUGH);
	
	boolean underline = underlineObject != null && (((Integer) underlineObject > -1));
	boolean strikeout = strikeoutObject != null && strikeoutObject.equals(true);
	 
	AttributedString astr = new AttributedString(text);
	astr.addAttribute(TextAttribute.FONT, font, start, end);
	astr.addAttribute(TextAttribute.FOREGROUND, color, start, end);
	if (underline) {
	    astr.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, start, end);	    
	}
	if (strikeout) {
	    astr.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON, start, end);	    
	}
	
	// horizontal alignment: left, center, right
	if (horizontalAlign == null || horizontalAlign == HorizontalAlign.FILL) {
	    horizontalAlign = HorizontalAlign.LEFT;
	}
	
	// vertical alignment: top, middle, bottom
	if (verticalAlign == null || verticalAlign == VerticalAlign.FILL) {
	    verticalAlign = VerticalAlign.TOP;
	}
	
	AttributedCharacterIterator paragraph = astr.getIterator();
	LineBreakMeasurer lineMeasurer = new LineBreakMeasurer(paragraph, gc.getFontRenderContext());
	
	int lineX = x;
	int lineY = y;
	
	int textWidth = 0;
	int textHeight = 0;
	int offsetX = 0;
	int offsetY = 0;
	
	gc.setClip(x, y, width, height);
	
	List<TextLayout> textlayouts = new ArrayList<TextLayout>();
	List<Rectangle2D> rectangles = new ArrayList<Rectangle2D>();
	
	while (lineMeasurer.getPosition() < paragraph.getEndIndex()) {
	    TextLayout textlayout = lineMeasurer.nextLayout(width);
	    if (textlayout == null) {
		break;
	    }
	    textlayouts.add(textlayout);
	    
	    Rectangle2D rectangle = textlayout.getBounds(); 
	    rectangles.add(rectangle);
	    
	    textHeight += textlayout.getAscent() + textlayout.getLeading() + textlayout.getDescent();
	}

	if (textHeight < height) {
	    if (verticalAlign == VerticalAlign.MIDDLE) {
		offsetY = (height - textHeight) / 2;
	    } else if  (verticalAlign == VerticalAlign.BOTTOM) {
		offsetY = height - textHeight;
	    }
	}
	
	lineY += offsetY;
	
	for (int i = 0; i < textlayouts.size(); i++) {
	    lineX = x;
	    offsetX = 0;
	    TextLayout textlayout = textlayouts.get(i);
	    Rectangle2D rectangle = rectangles.get(i);
	    
	    if (horizontalAlign == HorizontalAlign.CENTER) {
		textWidth = (int) rectangle.getWidth();
		if (textWidth < width) {
		    offsetX = (int) (width - textWidth) / 2;
		}
	    } else if (horizontalAlign == HorizontalAlign.RIGHT) {
		textWidth = (int) rectangle.getWidth();
		if (textWidth < width) {
		    offsetX = width - textWidth;
		}
	    }
	    
	    lineX += offsetX;
		
	    lineY += textlayout.getAscent(); // 1
	    textlayout.draw(gc, lineX, lineY);
	    lineY += textlayout.getDescent() + textlayout.getLeading(); // 2
	}
	
	/*
	while (lineMeasurer.getPosition() < paragraph.getEndIndex()) {
	    lineX = x;
	    offsetX = 0;
	    TextLayout textlayout = lineMeasurer.nextLayout(width);
	    if (textlayout == null) {
		break;
	    }
	    if (horizontalAlign == HorizontalAlign.CENTER) {
		textWidth = (int) textlayout.getBounds().getWidth();
		if (textWidth < width) {
		    offsetX = (int) (width - textWidth) / 2;
		}
	    } else if (horizontalAlign == HorizontalAlign.RIGHT) {
		textWidth = (int) textlayout.getBounds().getWidth();
		if (textWidth < width) {
		    offsetX = width - textWidth;
		}
	    }
	    
	    lineX += offsetX;
		
	    lineY += textlayout.getAscent();
	    textlayout.draw(gc, lineX, lineY);
	    lineY += textlayout.getDescent() + textlayout.getLeading();
	}
	*/
	
	
	gc.setClip((Shape) null);
	
    }
    
}

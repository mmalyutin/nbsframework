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

package org.plazmaforge.framework.uwt.gxt.widget;

import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.dom.client.Style.Position;

/**
 * 
 * @author ohapon
 *
 */
public class XCanvas extends SimpleContainer {

    static final int height = 400;
    static final int width = 400;

    final Canvas canvas; 
	    
    public XCanvas() {
	super();

	canvas = Canvas.createIfSupported();
	if (canvas == null) {
	    return;
	}

	// init the canvases
	canvas.setWidth(width + "px");
	canvas.setHeight(height + "px");
	canvas.setCoordinateSpaceWidth(width);
	canvas.setCoordinateSpaceHeight(height);
	// paint(new PaintEvent(canvas));
	    
	add(canvas);
	
    }
    
    @Override
    protected void doLayout() {
	if (widget != null && resize) {
	    widget.getElement().getStyle().setPosition(Position.ABSOLUTE);
	    resize = false;
	}
    }
      
    @Override
    protected void onResize(int width, int height) {
      super.onResize(width, height);
      if (canvas == null) {
	  return;
      }
      
      paint(new PaintEvent(canvas));
    }
     
    protected void paint(PaintEvent e) {
	// do nothing by default
    }
    
    public static class PaintEvent {
	
	private Canvas canvas;
	
	public PaintEvent(Canvas canvas) {
	    this.canvas = canvas;
	}
	
	public Context2d getContext2d() {
	    return canvas == null ? null : canvas.getContext2d();
	}
	
	public Canvas getCanvas() {
	    return canvas;
	}
	
	
    }

}

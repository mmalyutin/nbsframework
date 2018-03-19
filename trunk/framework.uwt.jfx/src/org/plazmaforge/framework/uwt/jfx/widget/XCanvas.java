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

package org.plazmaforge.framework.uwt.jfx.widget;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

/**
 * 
 * @author ohapon
 *
 */
public class XCanvas extends Canvas  {

    public static final int DEFAULT_WIDTH = 400;
    
    public static final int DEFAULT_HEIGHT = 400;

    public XCanvas() {
	
	setWidth(DEFAULT_WIDTH);
	setHeight(DEFAULT_HEIGHT);
	
	repaint();
	
        // Repaint canvas when size changes.
        widthProperty().addListener(evt -> repaint());
        heightProperty().addListener(evt -> repaint());
    }

    private void repaint() {
        //double width = getWidth();
        //double height = getHeight();

        GraphicsContext gc = getGraphicsContext2D();
        paint(gc);
        
        //gc.clearRect(0, 0, width, height);

        //gc.setStroke(Color.RED);
        //gc.strokeLine(0, 0, width, height);
        //gc.strokeLine(0, height, width, 0);
    }
    
    protected void paint(GraphicsContext gc) {
	
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }
    
    
    
//    public XCanvas() {
//	super();
//
//	canvas = Canvas.createIfSupported();
//	if (canvas == null) {
//	    return;
//	}
//
//	// init the canvases
//	canvas.setWidth(width + "px");
//	canvas.setHeight(height + "px");
//	canvas.setCoordinateSpaceWidth(width);
//	canvas.setCoordinateSpaceHeight(height);
//	// paint(new PaintEvent(canvas));
//	    
//	add(canvas);
//	
//    }
//    
//    @Override
//    protected void doLayout() {
//	if (widget != null && resize) {
//	    widget.getElement().getStyle().setPosition(Position.ABSOLUTE);
//	    resize = false;
//	}
//    }
//      
//    @Override
//    protected void onResize(int width, int height) {
//      super.onResize(width, height);
//      if (canvas == null) {
//	  return;
//      }
//      
//      paint(new PaintEvent(canvas));
//    }
//     
//    protected void paint(PaintEvent e) {
//	// do nothing by default
//    }
//    
//    public static class PaintEvent {
//	
//	private Canvas canvas;
//	
//	public PaintEvent(Canvas canvas) {
//	    this.canvas = canvas;
//	}
//	
//	public Context2d getContext2d() {
//	    return canvas == null ? null : canvas.getContext2d();
//	}
//	
//	public Canvas getCanvas() {
//	    return canvas;
//	}
//	
//	
//    }

}

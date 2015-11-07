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

import org.plazmaforge.framework.uwt.gxt.layout.XGridLayout;

import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.DomEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;

public class XCanvas extends LayoutContainer {

    static final int height = 400;
    static final int width = 400;

    
    public XCanvas() {
	super();
	setLayout(new XGridLayout());
	
	
	final Canvas canvas = Canvas.createIfSupported();
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
	addListener(Events.Render, new Listener<ComponentEvent>() {
	        public void handleEvent(ComponentEvent be) {
	            paint(new PaintEvent(canvas));
	        }
	});
    }
    

    protected void paint(PaintEvent e) {
	// do nothing by default
    }

    
    public static class PaintEvent extends DomEvent {

	public PaintEvent(Canvas canvas) {
	    super(canvas);
	}
	
	public Context2d getContext2d() {
	    return getCanvas() == null ? null : getCanvas().getContext2d();
	}
	
	public Canvas getCanvas() {
	    return (Canvas) getSource();
	}
	
	
    }

}

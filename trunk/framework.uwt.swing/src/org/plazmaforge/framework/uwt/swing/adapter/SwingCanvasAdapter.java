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

import java.awt.RenderingHints;

import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.widget.Canvas;

public class SwingCanvasAdapter extends SwingControlAdapter {

    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	java.awt.Container xParent = (java.awt.Container) getContent(parent.getDelegate());
	final Canvas canvas = (Canvas) element;
	java.awt.Canvas xCanvas = new java.awt.Canvas() {
	    @Override
	    public void paint(java.awt.Graphics graphics) {
		
		GC gc = new GC();
		initGraphics(graphics, gc);
		gc.setDelegate(graphics);
		canvas.repaint(gc);
	    }
	};
	addToParent(xParent, xCanvas, element);
	return xCanvas;
    }
    
    protected void initGraphics(java.awt.Graphics graphics, GC gc) {
	// background and foreground will initialize in draw or fill methods of SwingGCAdapter
	graphics.setFont(getFont(gc.getFont()));
	
	if (!(graphics instanceof java.awt.Graphics2D)) {
	    return;
	}
	java.awt.Graphics2D graphics2D = (java.awt.Graphics2D) graphics;
	initGraphics2D(graphics2D, gc);
    }
    
    protected void initGraphics2D(java.awt.Graphics2D graphics2D, GC gc) {
	graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON); // By default anti aliasing is ON 
    }

}

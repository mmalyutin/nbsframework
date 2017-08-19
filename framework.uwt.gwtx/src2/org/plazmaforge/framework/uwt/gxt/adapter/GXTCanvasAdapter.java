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
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.gxt.widget.XCanvas;
import org.plazmaforge.framework.uwt.gxt.widget.XCanvas.PaintEvent;
import org.plazmaforge.framework.uwt.widget.Canvas;

import com.google.gwt.canvas.dom.client.CssColor;


public class GXTCanvasAdapter extends GXTControlAdapter {

    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	final Canvas canvas = (Canvas) element;
	XCanvas xCanvas = new XCanvas() {
	    
	    protected void paint(PaintEvent e) {
		if (e == null) {
		    return;
		}
		com.google.gwt.canvas.dom.client.Context2d context2d = e.getContext2d();
		if (context2d == null) {
		    return;
		}
		
		GC gc = new GC();
		initContext2d(context2d, gc);
		gc.setDelegate(context2d);
		
		//context2d.beginPath();
		canvas.repaint(gc);
		//context2d.closePath();
		//context2d.stroke(); // important (why?)
	    }	    
	    
	};
	addToParent(getContent(parent.getDelegate()), xCanvas, element);
	return xCanvas;
    }
    
    protected void initContext2d(com.google.gwt.canvas.dom.client.Context2d context2d, GC gc) {
	if (context2d == null) {
	    return;
	}
	//FillStrokeStyle strokeStyle = CssColor.make("#333300");
	//CssColor fillStyle = CssColor.make("rgba(255,255,255,0.6)");
	//CssColor fillStyle = CssColor.make("rgba(255,255,255)");

	CssColor fillStyle = CssColor.make(getRGBColorString(gc.getBackground()));
	CssColor strokeStyle = CssColor.make(getRGBColorString(gc.getForeground()));
	String font = getFontString(gc.getFont());
	//if (font != null && font.endsWith(";") && font.length() > 1) {
	//    font = font.substring(0, font.length() - 1);
	//}
	context2d.setFillStyle(fillStyle);
	context2d.setStrokeStyle(strokeStyle);
	context2d.setFont(font);

    }

}

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
import org.plazmaforge.framework.uwt.UIObject;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.widget.Canvas;

public class SWTCanvasAdapter extends SWTControlAdapter {

    @Override
    public Object createDelegate(UIObject parent, UIObject element) {
	org.eclipse.swt.widgets.Composite xParent = (org.eclipse.swt.widgets.Composite) getContent(parent.getDelegate());
	org.eclipse.swt.widgets.Canvas xCanvas = new org.eclipse.swt.widgets.Canvas(xParent, SWT.NO_REDRAW_RESIZE); // SWT.NO_REDRAW_RESIZE
	xCanvas.setBackgroundMode(SWT.INHERIT_DEFAULT);
	final Canvas canvas = (Canvas) element;
	xCanvas.addPaintListener(new org.eclipse.swt.events.PaintListener() {
	    public void paintControl(org.eclipse.swt.events.PaintEvent e) {
		GC gc = new GC();
		initGC(e.gc, gc);
		gc.setDelegate(e.gc);
		canvas.repaint(gc);
	    }
	});
	addToParent(xParent, xCanvas, element);
	return xCanvas;
    }
    
    protected void initGC(org.eclipse.swt.graphics.GC xGC, GC gc) {
 	xGC.setBackground(getColor(gc.getBackground()));
 	xGC.setForeground(getColor(gc.getForeground()));
 	xGC.setFont(getFont(gc.getFont()));
 	xGC.setAntialias(SWT.ON); // By default anti aliasing is ON
 	int alpha = SWTHelper.get255ColorAlpha(gc.getBackground().getAlpha());
 	xGC.setAlpha(alpha);
   }
   
}

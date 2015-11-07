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

package org.plazmaforge.framework.uwt.widget.chart.plot;

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.graphics.Rectangle;

public class DonutPlot extends PiePlot {

    protected int backgroundRadius;
    
    public DonutPlot() {
	visibleLabels = true;
    }

    @Override
    public void prepareDraw(GC gc, Rectangle area) {
	super.prepareDraw(gc, area);
	backgroundRadius = (int) (pieRadius * 0.6);
	int delta = pieRadius - backgroundRadius;
	pointRadius = pieRadius - (delta / 2);
    }
    @Override
    public void drawData(GC gc, Rectangle area) {
	
	if (isEmptyDataValues()) {
	    return;
	}
	
	super.drawData(gc, area);
	
	int ox = pieCorner.getX() + pieRadius;
	int oy = pieCorner.getY() + pieRadius;
	drawPie2(gc, area, Color.WHITE, ox, oy, backgroundRadius);
    }
    
    
    // [GC]
    protected void drawPie2(GC gc, Rectangle area, Color color, int ox, int oy, int radius) {
	gc.setBackground(color);
	gc.fillCircleR(ox, oy, radius);
    }
}

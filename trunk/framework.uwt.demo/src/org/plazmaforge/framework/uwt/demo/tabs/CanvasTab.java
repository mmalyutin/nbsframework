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

package org.plazmaforge.framework.uwt.demo.tabs;

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.Font;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Canvas;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;

public class CanvasTab extends AbstractTab {

    public CanvasTab() {
    }

    @Override
    protected void createUI() {
	setLayout(new GridLayout());
	
	Canvas panel = createCanvas();
	add(panel);
    }
    
    
    private Canvas createCanvas() {
	Canvas canvas = new Canvas() {
	    protected void paint(GC gc) {
		paintCanvas(gc);
	    }
	    
	};
	canvas.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.FILL, true, true));
	return canvas;
    }
    
    protected void paintCanvas(GC gc) {
	gc.setBackground(new Color(255, 255, 0, 0.7f));
	//gc.setForeground(Color.BLACK);
	gc.fillCircle(10, 30, 50);
	
	gc.fillRectangle(20, 100, 50, 50);
	gc.setBackground(Color.BLUE);
	gc.fillRectangle(80, 110, 50, 50);
	
	gc.drawRectangle(0, 0, 200, 200);
	gc.drawRectangle(10, 10, 180, 180);
	gc.drawLine(10, 10, 190, 190);
	
	gc.drawLine(10, 20, 190, 20);
	gc.drawEllipse(10, 30, 180, 140);
	
	//gc.setBackground(Color.YELLOW);
	
	gc.setFont(new Font("Verdana", 20));
	gc.drawText("Hello", 10 + 5, 30 + 5);
	
	//gc.fillRectangle(200, 100, 50, 50);
	
	gc.setForeground(Color.BLACK);
	
	gc.setBackground(Color.GREEN);
	gc.fillRectangle(140, 120, 50, 50);
	//gc.drawCircle(10, 30, 50);
	
	gc.drawArc(100, 40, 50, 50, 45, 90);
	
	gc.fillArc(100, 80, 50, 50, 45, 90);
    }

}

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

import java.util.List;

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.graphics.Point;
import org.plazmaforge.framework.uwt.graphics.Rectangle;

public class LinePlot extends AxisPlot {

    public static Color DEFAULT_POINT_COLOR = Color.BLACK;
    
    public static Color DEFAULT_LINE_COLOR = Color.RED;
    
    private Color pointColor;
    
    private Color lineColor;
    
    
    public LinePlot() {
	pointColor = DEFAULT_POINT_COLOR;
	lineColor = DEFAULT_LINE_COLOR;
    }


    public Color getPointColor() {
        return pointColor;
    }

    public void setPointColor(Color pointColor) {
        this.pointColor = pointColor == null ? DEFAULT_POINT_COLOR : pointColor;
    }

    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor == null ? DEFAULT_LINE_COLOR : lineColor;
    }

   
    @Override
    public void draw(GC gc, Rectangle area) {
	super.draw(gc, area);
	//TODO
    }

    @Override
    protected void prepareDraw(GC gc, Rectangle area) {
	super.prepareDraw(gc, area);
	//TODO
    }
    
    protected void drawData(GC gc, Rectangle area) {

	if (isEmptyValueRange()) {
	    return;
	}

	List<List<PointValue>> seriesPoints = getSeriesPoints();
	if (seriesPoints == null || seriesPoints.isEmpty()) {
	    return;
	}
	
	int seriesCount = seriesPoints.size();
	for (int s = 0; s < seriesCount; s++) {
	    Point[] points = getDrawPoints(area, seriesPoints.get(s));
	    transform(area, points);
	    Color seriesLineColor = getSeries(s).getColor();
	    if (seriesLineColor == null) {
		seriesLineColor = lineColor;
	    }
	    drawPointLines(gc, area, points, seriesLineColor);
	    drawPoints(gc, area, points, pointColor);
	}
	
	drawAxis(gc, area);
	drawLegend(gc, area);
    }
    
    protected void drawPoints(GC gc, Rectangle area, Point[] points, Color pointColor) {
	if (points == null || points.length == 0) {
	    return;
	}
	if (pointColor != null) {
	    gc.setBackground(pointColor);
	}
	for (Point point: points) {
	    if (point == null) {
		continue;
	    }
	    drawPoint(gc, area, point.getX(), point.getY());
	}
    }
    
    protected void drawPointLines(GC gc, Rectangle area, Point[] points, Color lineColor) {
	if (points == null || points.length <= 1) {
	    return;
	}
	int prevX = points[0].getX();
	int prevY = points[0].getY();
	Point currPoint = null;
	if (lineColor != null) {
	    gc.setForeground(lineColor);
	}
	for (int i = 1; i < points.length; i++) {
	    currPoint = points[i];
	    drawLine(gc, area, prevX, prevY, currPoint.getX(), currPoint.getY());
	    prevX = currPoint.getX();
	    prevY = currPoint.getY();
	}
    }
    
    
    /**
     * Draw point (dot)
     * @param gc
     * @param area
     * @param x
     * @param y
     */
    //[GC]
    protected void drawPoint(GC gc, Rectangle area, int x, int y) {
  	// TODO: Check area range
  	//gc.drawPoint(x, y);
  	gc.fillCircleR(x, y, 2);
    }
    
    
    /**
     * Draw line
     * @param gc
     * @param area
     * @param x1 (start point)
     * @param y1 (start point)
     * @param x2 (end point)
     * @param y2 (end point)
     */
    //[GC]
    protected void drawLine(GC gc, Rectangle area, int x1, int y1, int x2, int y2) {
	gc.drawLine(x1, y1, x2, y2);
    }

  
}

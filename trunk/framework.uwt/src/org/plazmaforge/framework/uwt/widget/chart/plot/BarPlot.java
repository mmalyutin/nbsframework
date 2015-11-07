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

/**
 * 
 */
package org.plazmaforge.framework.uwt.widget.chart.plot;

import java.util.List;

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.graphics.Point;
import org.plazmaforge.framework.uwt.graphics.Rectangle;

/**
 * @author ohapon
 *
 */
public class BarPlot extends AxisPlot {
    
    public static Color DEFAULT_BAR_COLOR = Color.RED;
    
    public static int DEFAULT_BAR_WIDTH = 10;
    
    public static int MIN_BAR_WIDTH = 1; 
    
    public static int MAX_BAR_WIDTH = 50;
    
    private Color barColor;
    
    private int barWidth;
    
    private boolean expandedDataArea;
    
    private int marginData;
    
    private int barSpacing;
    
    // Specify minimum segment bar spacing  
    private int segmentSpacing;
    
    private int segmentWidth;
    
    public BarPlot() {
	barColor = DEFAULT_BAR_COLOR;
	barWidth = DEFAULT_BAR_WIDTH;
	marginData = 0;
	expandedDataArea = true;
	
	barSpacing = 0;
	segmentSpacing = 10;
    }
    
    @Override
    protected Number[] getAxisNumerValues(Object[] values) {
  	if (values == null) {
	    return null;
	}
	Number[] result = new Number[values.length];
	for (int i = 0; i < values.length; i++) {
	    result[i] = i;
	}
	return result;
    }
          
    
    @Override
    public void prepareDraw(GC gc, Rectangle area) {
	
	int valueCount = axisValues == null ? 0 : axisValues.length;
	if (valueCount == 0) {
	    return;
	}

	int width = area.getWidth() - marginData * 2;
	int seriesCount = !expandedDataArea ? 1 : getSeriesCount();
	if (seriesCount == 0) {
	    seriesCount = 1;
	}

	// Width of bars segment
	// 
	// segmentWidth = (barWidth * seriesCount) + (barSpacing * (seriesCount - 1))
	// allSegmentWidth = segmentWidth * valueCount;
	// allSegmentSpacing = segmentSpacing * (valueCount - 1)
	// width = allSegmentWidth + allSegmentSpacing  
	
	// width = ((barWidth * seriesCount) + (barSpacing * (seriesCount - 1))) * valueCount + allSegmentSpacing
	// ((barWidth * seriesCount) + (barSpacing * (seriesCount - 1))) * valueCount = width - allSegmentSpacing
	
	// (barWidth * seriesCount) + (barSpacing * (seriesCount - 1)) = (width - allSegmentSpacing) / valueCount
	// (barWidth * seriesCount) = ((width - allSegmentSpacing) / valueCount) - (barSpacing * (seriesCount - 1))
	
	int allSegmentSpacing = segmentSpacing * (valueCount - 1);
	
	barWidth = ((width - allSegmentSpacing) / valueCount) - (barSpacing * (seriesCount - 1)) / seriesCount;
	barWidth = barWidth / 2; // TODO WHY?
	
	// Fixed bar width
	if (barWidth < MIN_BAR_WIDTH) {
	    barWidth = MIN_BAR_WIDTH;
	} else if (barWidth > MAX_BAR_WIDTH) {
	    barWidth = MAX_BAR_WIDTH;
	}
	
	segmentWidth = getSegmentWidth(barWidth, seriesCount);
	
	super.prepareDraw(gc, area);

    }
    
    protected Rectangle createDataArea(Rectangle area) {
	return expandedDataArea ? createExpandedDataArea(area) : createCollapsedDataArea(area); 
    }

    protected Rectangle createCollapsedDataArea(Rectangle area) {
	int marginLeft = marginData;
	int marginRight = barWidth + marginLeft;
	return new Rectangle(area.getX() + marginLeft, area.getY(), area.getWidth() - marginLeft - marginRight, area.getHeight());
    }

    protected Rectangle createExpandedDataArea(Rectangle area) {
	int marginLeft = marginData;
	int marginRight = segmentWidth + marginLeft;
	return new Rectangle(area.getX() + marginLeft, area.getY(), area.getWidth() - marginLeft - marginRight, area.getHeight());
    }

    protected int getSegmentWidth(int barWidth, int seriesCount) {
	return (barWidth * seriesCount) + (barSpacing * (seriesCount - 1));
    }
    
    @Override
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
		seriesLineColor = barColor;
	    }
	    int seriesShift = expandedDataArea ?  getSegmentWidth(barWidth, s) : 0; 
	    drawBars(gc, area, points, seriesLineColor, seriesShift);
	}
	
	drawAxis(gc, area);
	drawLegend(gc, area);
    }

    protected void drawBars(GC gc, Rectangle area, Point[] points, Color barColor, int seriesShift) {
	if (points == null || points.length == 0) {
	    return;
	}
	if (barColor != null) {
	    gc.setBackground(barColor);
	}
	for (Point point : points) {
	    drawBar(gc, area, point, seriesShift);
	}
    }

    /**
     * Draw bar
     * @param gc
     * @param area
     * @param point
     */
    //[GC]
    protected void drawBar(GC gc, Rectangle area, Point point, int seriesShift) {
	if (point == null) {
	    return;
	}
	// TODO: Check area range
	gc.fillRectangle(point.getX() + seriesShift, point.getY(), barWidth, dataArea.getHeight() - (point.getY() - dataArea.getY()));
    }
}

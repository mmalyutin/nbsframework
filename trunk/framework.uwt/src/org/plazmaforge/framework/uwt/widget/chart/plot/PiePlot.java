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
import org.plazmaforge.framework.uwt.graphics.Point;
import org.plazmaforge.framework.uwt.graphics.Rectangle;
import org.plazmaforge.framework.uwt.graphics.Size;
import org.plazmaforge.framework.uwt.widget.chart.Chart;
import org.plazmaforge.framework.uwt.widget.chart.dataset.Dataset;
import org.plazmaforge.framework.uwt.widget.chart.dataset.Series;
import org.plazmaforge.framework.uwt.widget.chart.util.ChartGraphicsUtils;

public class PiePlot extends Plot {
    
    
    protected Number[] dataValues;
    protected double total;
    protected float[] percentValues;
    protected float[] angleValues;
    protected Color[] colorValues;
    
    protected int initAngle;
    protected int pieRadius;
    protected int pointRadius;
    protected Point pieCorner;
    
    protected boolean visibleLabels;

    public PiePlot() {
	total = 0.0;
	initAngle = 0;
	visibleLabels = true;
    }

    @Override
    public void prepareDraw(GC gc, Rectangle area) {
	dataArea = createDataArea(area);
	pieRadius = Math.min(dataArea.getWidth(), dataArea.getHeight()) / 2;
	pieCorner = new Point(dataArea.getX() + (dataArea.getWidth() / 2 - pieRadius), dataArea.getY() + (dataArea.getHeight() / 2 - pieRadius));
	
	pointRadius = (int) (pieRadius * 0.6);
    }
    
    @Override
    public void drawData(GC gc, Rectangle area) {
	
	if (isEmptyDataValues()) {
	    return;
	}
	
	int currentAngle = initAngle;
	int endAngle = 0;
	int segmentAngle = 0;
	
	Number dataValue = null;
	
	int x = pieCorner.getX();
	int y = pieCorner.getY();
	
	int ox = x + pieRadius;
	int oy = y + pieRadius;

	int d = pieRadius * 2;
	
	
	int count = dataValues.length;
	for (int i = 0; i < count; i++) {
	    dataValue = dataValues[i];
	    if (isIgnoreDataValue(dataValue)) {
		continue;
	    }
	    segmentAngle = (int) angleValues[i];
	    endAngle = currentAngle + segmentAngle;
	    if (i == count - 1) {
		endAngle = 360; // fix last angle
	    }
	    drawPie(gc, area, colorValues[i], x, y, d, currentAngle, endAngle);
	    if (visibleLabels) {
		//String label = "" + percentValues[i] + "%"; // TODO
		String label = "" + Math.round(percentValues[i]) + "%"; // TODO
		drawPieLabel(gc, area, ox, oy, currentAngle, endAngle, label);
	    }
	    currentAngle = endAngle;
	}
	
	drawLegend(gc, area);
	
    }
    
    protected Rectangle createDataArea(Rectangle area) {
	int dataMargin = 5;
  	return new Rectangle(area.getX() + dataMargin, area.getY() + dataMargin, area.getWidth() - dataMargin * 2, area.getHeight() - dataMargin * 2);
    }

    @Override
    protected void initDataValues(Dataset dataset) {
	
	int seriesCount = dataset.getSeriesCount();
	if (seriesCount == 0) {
	    return;
	}
	
	// Get data values from first series.
	dataValues = dataset.getDataValues(0);
	if (dataValues == null) {
	    return;
	}
	

	int count = dataValues.length;
	percentValues = new float[count];
	angleValues = new float[count];
	colorValues = new Color[count];
	Number dataValue = null;
	
	// Calculate total value
	for (int i = 0; i < count; i++) {
	    dataValue = dataValues[i];
	    if (isIgnoreDataValue(dataValue)) {
		continue;
	    }
	    total += dataValue.doubleValue();
	}
	if (total == 0.0) {
	    return;
	}
	
	// Calculate values
	for (int i = 0; i < count; i++) {
	    dataValue = dataValues[i];
	    if (isIgnoreDataValue(dataValue)) {
		continue;
	    }
	    percentValues[i] = (float) (dataValue.doubleValue() / total * 100); // Total is 100%
	    angleValues[i] = (float) (dataValue.doubleValue() / total * 360); // Total is 360
	    colorValues[i] = i < Chart.DEFAULT_SERIES_COLORS.length ? Chart.DEFAULT_SERIES_COLORS[i]: ChartGraphicsUtils.generateColor();
	}

    }
    
   
    @Override
    protected Legend createLegend(Dataset dataset) {
	
	Legend legend = new Legend();
	Series[] seriesCollection = dataset.getSeriesCollection();
	if (seriesCollection == null) {
	    return legend;
	}
	
	Number dataValue = null;
	int count = dataValues.length;
	
	AxisValue axisValue = null;
	for (int i = 0; i < count; i++) {
	    dataValue = dataValues[i];
	    if (isIgnoreDataValue(dataValue)) {
		continue;
	    }
	    
	    axisValue = axisValues[i];

	    Object axisObjectValue = axisValue.getValue();
	    if (axisObjectValue == null) {
		// No object value - return;
		continue;
	    }
	    Number axisNumberValue = axisValue.getNumberValue();
	    if (axisNumberValue == null) {
		// No number representation value - return
		continue;
	    }
	    String text = axisValue.getTextValue();
	    
	    LegendLabel label = new LegendLabel(text, colorValues[i]);
	    legend.addLabel(label);
	}
	
	return legend;
    }
    
    
    protected boolean isIgnoreDataValue(Number value) {
	return value == null || value.doubleValue() < 0.0; // Null or negative value is ignored // TODO: why? may be shift negative values ? 
    }
    
    protected boolean isEmptyDataValues() {
	return dataValues == null || dataValues.length == 0;
    }

    
    /**
     * 
     * @param gc
     * @param area
     * @param color
     * @param x (left corner)
     * @param y (left corner)
     * @param width
     * @param startAngle (degrees)
     * @param endAngle (degrees)
     */
    // [GC]
    protected void drawPie(GC gc, Rectangle area, Color color, int x, int y, int width, int startAngle, int endAngle) {
	gc.setBackground(color);
	gc.fillArc(x, y, width, width, startAngle, endAngle);
    }
    
    protected void drawPieLabel(GC gc, Rectangle area, int ox, int oy, int startAngle, int endAngle, String text) {
	if  (text == null || text.isEmpty()) {
	    return;
	}
	Size textSize = gc.getStringSize(text);
	if (textSize == null) {
	    return;
	}
		
	int deltaAngle = endAngle - startAngle;
	if (deltaAngle <= 0) {
	    return;
	}
	
	
	int pointAngle = startAngle + deltaAngle / 2;

	Point point = getCirclePoint(ox, oy, pointRadius, pointAngle);
	
	Point point1 = getCirclePoint(ox, oy, pieRadius, startAngle);
	Point point2 = getCirclePoint(ox, oy, pieRadius, endAngle);
	
	//gc.setBackground(Color.BLACK);
	//drawPoint(gc, area, point.getX(), point.getY());
	//drawPoint(gc, area, point1.getX(), point1.getY());
	//drawPoint(gc, area, point2.getX(), point2.getY());

	int labelY = point.getY();
	int labelX = point.getX();
	
	int labelWidth = textSize.getWidth();
	int labelHeight = textSize.getHeight();
	
	
	labelY -= labelHeight / 2;
	labelX = point.getX() - labelWidth / 2;

	//if (pointAngle > 60 && pointAngle < 120) {
	//    labelY -= labelHeight;
	//} else if (pointAngle > 240 && pointAngle < 300) {
	//    labelY += labelHeight;
	//}
	
	//if (pointAngle > 90 && pointAngle < 270) {
	//    labelX -= labelWidth;
	//}
	
	// Label rectangle points
	Point[] points = new Point[4];
	points[0] = new Point(labelX, labelY);
	points[1] = new Point(labelX + labelWidth, labelY);
	points[2] = new Point(labelX + labelWidth, labelY + labelHeight);
	points[3] = new Point(labelX, labelY + labelHeight);
	
	int marker1 = 0;
	int marker2 = 0;
	
	boolean flag = true;
	for (int i = 0; i < points.length; i++) {
	    marker1 = getLineMarker(ox, oy, point1.getX(), point1.getY(), points[i].getX(), points[i].getY());
	    marker2 = getLineMarker(ox, oy, point2.getX(), point2.getY(), points[i].getX(), points[i].getY());
	    flag = (marker1 > 0 && marker2 > 0) || (marker1 < 0 && marker2 < 0);
	    if (flag) {
		return;
	    }
	}
	
	//if (flag) {
	//    return;
	//}
	
	gc.drawText(text, labelX, labelY);
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
    
    
    protected Point getCirclePoint(int ox, int oy, int radius, float angleDegrees) {
	
	// Convert angle to radians
	float angleRadian = (float) Math.toRadians(angleDegrees);

	// Calculate X and Y by COS and SIN
	float pointX = (float) (Math.cos(angleRadian) * radius);
	float pointY = (float) -(Math.sin(angleRadian) * radius);

	// Translate point by center of circle coordinates 
	pointX += ox;
	pointY += oy;

	return new Point((int) pointX, (int) pointY);
    }
    
    protected int getLineMarker(int x1, int y1, int x2, int y2, int x, int y) {
	return (x1 - x) * (y2 - y1) - (x2 - x1) * (y1 - y);
    }
    
    
}

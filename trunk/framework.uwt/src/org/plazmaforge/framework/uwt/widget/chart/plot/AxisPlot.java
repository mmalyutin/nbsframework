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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.graphics.Padding;
import org.plazmaforge.framework.uwt.graphics.Point;
import org.plazmaforge.framework.uwt.graphics.Rectangle;
import org.plazmaforge.framework.uwt.graphics.Size;
import org.plazmaforge.framework.uwt.widget.chart.util.ChartGraphicsUtils;

/**
 * @author ohapon
 *
 */
public abstract class AxisPlot extends Plot {

    /**
     * X Axis labels
     */
    protected List<AxisLabel> xLabels;
    
    /**
     * Y Axis labels
     */
    protected List<AxisLabel> yLabels;
    

    protected boolean xLabelsVisible;
    
    protected boolean yLabelsVisible;

    
    private String axisTitle;
    
    private String dataTitle;

    
    protected int axisMargin;
    
    
    protected int maxXLabelWidth;
    
    protected int maxXLabelHeight;
    
    protected int xTitleHeight;
    
    
    protected int maxYLabelWidth;
    
    protected int maxYLabelHeight;
    
    protected int yTitleHeight;
    
    
    protected double minX;
    
    protected double minY;
    
	
    protected double kX;
    
    protected double kY;
    
    
    
 
    
    public AxisPlot() {
	
	// Axis space
	axisMargin = 2;
	
	xLabelsVisible = true;
	yLabelsVisible = true;
	    
    }


    public String getAxisTitle() {
        return axisTitle;
    }

    public void setAxisTitle(String axisTitle) {
        this.axisTitle = axisTitle;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    
    // X/Y Specify: begin

    protected double calculateKX(Rectangle dataArea) {
	return axisValueRange / dataArea.getWidth();
    }
    
    protected double calculateKY(Rectangle dataArea) {
	return dataValueRange / dataArea.getHeight();
    }
    
    //
    
    protected double getMinX() {
	return minAxisValue;
    }

    protected double getMinY() {
	return minDataValue;
    }
    
    //
    
    protected String getXTitle() {
	return axisTitle;
    }

    protected String getYTitle() {
	return dataTitle;
    }

    
    // X/Y Specify: end
    
    
    
    
    @Override
    protected void initData() {
	super.initData();
	
	minX = getMinX();
	minY = getMinY();

	initAxis();
    }
    
    protected void initAxis() {
	initXAxis();
	initYAxis();
    }
    
    protected void initXAxis() {
	if (!xLabelsVisible || axisValues == null) {
	    return;
	}
	xLabels = new ArrayList<AxisLabel>();
	
	// TODO: Use X AxisValue
	
	AxisValue axisValue = null;
	for (int i = 0; i < axisValues.length; i++) {
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
	    double value = axisNumberValue.doubleValue();
	    AxisLabel label = new AxisLabel();
	    label.setText(text);
	    label.setValue(value);
	    
	    xLabels.add(label);
	}
    }
    
    protected void initYAxis() {
	if (!yLabelsVisible || minDataValue == null || maxDataValue == null) {
	    return;
	}
	yLabels = new ArrayList<AxisLabel>();
	
	// TODO: Use Y AxisValue
	
	double[] values = new double[] {minDataValue, maxDataValue};
	for (int i = 0; i < values.length; i++) {
	    double value = values[i];
	    String text = "" + value; // TODO: Must format
	    AxisLabel label = new AxisLabel();
	    label.setText(text);
	    label.setValue(value);
	    
	    yLabels.add(label);
	}
    }
    
    @Override
    protected void prepareDraw(GC gc, Rectangle area) {
	dataArea = createDataArea(area);
	kX = calculateKX(dataArea);
	kY = calculateKY(dataArea);
    }
    
    
    
    @Override
    protected void calculateDraw(GC gc) {
	super.calculateDraw(gc);
	
	// Calculate draw axis
	
	// X Axis
	calculateDrawXAxis(gc);
	
	// Y Axis
	calculateDrawYAxis(gc);
    }
 

    // X Axis
    protected void calculateDrawXAxis(GC gc) {
	calculateDrawXLabels(gc);
	calculateDrawXTitle(gc);
    }
    
    protected void calculateDrawXLabels(GC gc) {
	maxXLabelWidth = 0;
	maxXLabelHeight = 0;
	if (xLabels == null) {
	    return;
	}
	int count = xLabels.size();
	for (int i = 0; i < count; i++) {
	    AxisLabel label = xLabels.get(i);
	    calculateTextSize(gc, label, MAX_AXIS_LABEL_WIDTH);
	    Size size = label.getDisplayTextSize();
	    
	    // Calculate min, max
	    if (size != null) {
		maxXLabelWidth = Math.max(maxXLabelWidth, size.getWidth());
		maxXLabelHeight = Math.max(maxXLabelHeight, size.getHeight());
	    }
	}
    }
    
    protected void calculateDrawXTitle(GC gc) {
	xTitleHeight = 0;
	if (getXTitle() == null) {
	    return;
	}
	Size size = gc.getStringSize(getXTitle());
	if (size != null) {
	    xTitleHeight = size.getHeight();
	}
    }
    
    
    // Y Axis
    protected void calculateDrawYAxis(GC gc) {
	calculateDrawYLabels(gc);
	calculateDrawYTitle(gc);
    }
    
    protected void calculateDrawYLabels(GC gc) {
	maxYLabelWidth = 0;
	maxYLabelHeight = 0;
	if (yLabels == null) {
	    return;
	}
	int count = yLabels.size();
	for (int i = 0; i < count; i++) {
	    AxisLabel label = yLabels.get(i);
	    calculateTextSize(gc, label, MAX_AXIS_LABEL_WIDTH);
	    Size size = label.getDisplayTextSize();
	    
	    // Calculate min, max
	    if (size != null) {
		maxYLabelWidth = Math.max(maxYLabelWidth, size.getWidth());
		maxYLabelHeight = Math.max(maxYLabelHeight, size.getHeight());
	    }
	}
    }

    protected void calculateDrawYTitle(GC gc) {
	yTitleHeight = 0;
	if (getYTitle() == null) {
	    return;
	}
	Size size = gc.getStringSize(getYTitle());
	if (size != null) {
	    yTitleHeight = size.getHeight();
	}
    }


    protected void transform(Rectangle area, Point[] points) {
	if (points == null || points.length == 0) {
	    return;
	}
	for (Point point : points) {
	    transform(area, point);
	}
    }

    protected void transform(Rectangle area, Point point) {
	if (point == null) {
	    return;
	}
	minX = minAxisValue;
	minY = minDataValue;

	int x = toTransformValue(point.getX(), minX, kX);
	int y = toTransformValue(point.getY(), minY, kY);

	y = dataArea.getHeight() - y;
	point.setX(x + dataArea.getX());
	point.setY(y + dataArea.getY());
    }

 
    @Override
    public void needPadding(Rectangle chartArea, Padding padding) {
 	
 	
 	int leftSpace = maxYLabelWidth == 0 ? 0 : (maxYLabelWidth + axisMargin); // Y Axis (Label Width)
 	int topSpace = 0;
 	int rightSpace = calculateNeedLegendWidth();
 	int bottomSpace = maxXLabelHeight == 0 ? 0 : (maxXLabelHeight + axisMargin); // X Axis (Label Height)
 	
 	// Create draw padding by space
 	Padding drawPadding = new Padding();
 	drawPadding.setLeft(leftSpace);
 	drawPadding.setTop(topSpace);
 	drawPadding.setRight(rightSpace);
 	drawPadding.setBottom(bottomSpace);
 	
 	// Increment draw padding 
 	if (padding != null) {
 	    drawPadding.inc(padding);
 	}
 	
 	Rectangle plotArea = createSpaceArea(chartArea, drawPadding);
 	Rectangle dataArea = createDataArea(plotArea);
 	
 	double kX = calculateKX(dataArea);
	double kY = calculateKY(dataArea);

 	// X AXIS LABELS
 	List<Rectangle> xRectangles = calculateXLabels(chartArea, dataArea, kX);
 	int x1  = -1;
 	int x2  = -1;
 	if (xRectangles != null) {
 	    for (Rectangle rectangle: xRectangles ) {
 		x1 = Math.min(x1, rectangle.getX());
 		x2 = Math.max(x2, rectangle.getX() + rectangle.getWidth());
 	    }
 	}
 	
 	int xLeftSpace = 0;
 	if (x1 > -1 && x1 < plotArea.getX()) {
 	   xLeftSpace = plotArea.getX() - x1;
 	}
 	leftSpace = Math.max(leftSpace, xLeftSpace);
 	
 	int xRightSpace = 0;
 	if (x2 > -1 && x2 > plotArea.getX() + plotArea.getWidth()) {
 	   xRightSpace = x2 - (plotArea.getX()  + plotArea.getWidth());
 	}
 	rightSpace = Math.max(rightSpace, xRightSpace);

 	
 	// Y AXIS LABELS
 	List<Rectangle> yRectangles = calculateYLabels(chartArea, dataArea, kY);
 	int y1  = -1;
 	int y2  = -1;
 	if (yRectangles != null) {
 	    for (Rectangle rectangle: yRectangles ) {
 		y1 = Math.min(y1, rectangle.getY());
 		y2 = Math.max(y2, rectangle.getY() + rectangle.getHeight());
 	    }
 	}
 	
 	int yTopSpace = 0;
 	if (y1 > -1 && y1 < plotArea.getY()) {
 	   yTopSpace = plotArea.getY() - y1;
 	}
 	topSpace = Math.max(topSpace, yTopSpace);
 	
 	
 	int yBottomSpace = 0;
 	if (y2 > -1 && y2 > plotArea.getY() + plotArea.getHeight()) {
 	   yBottomSpace = y2 - (plotArea.getY()  + plotArea.getHeight());
 	}
 	bottomSpace = Math.max(bottomSpace, yBottomSpace);
 	
 	// X Title
 	if (xTitleHeight > 0) {
 	   bottomSpace += (axisMargin + xTitleHeight);
 	}

 	// Y Title
 	if (yTitleHeight > 0) {
 	   leftSpace += (axisMargin + yTitleHeight);
 	}

 	needPadding = new Padding();
 	needPadding.setLeft(leftSpace);
 	needPadding.setTop(topSpace);
 	needPadding.setRight(rightSpace);
 	needPadding.setBottom(bottomSpace);
     }

    
    
    protected void drawAxis(GC gc, Rectangle area) {
	drawXAxis(gc, area);
	drawYAxis(gc, area);
    }

    // X AXIS
    protected void drawXAxis(GC gc, Rectangle area) {
	drawXLabels(gc, area);
	drawXTitle(gc, area);
    }
    
    protected void drawXLabels(GC gc, Rectangle area) {
	if (xLabels == null || xLabels.isEmpty()) {
	    return;
	}
	int offsetY = area.getY() + area.getHeight() + axisMargin; // PLOT AREA
	int count = xLabels.size();
	for (int i = 0; i < count; i++) {

	    AxisLabel label = xLabels.get(i);
	    int x = toTransformValue(label.getValue(), minX, kX);
	    x = x + dataArea.getX(); // DATA AREA
	    label.setAxisValue(x); // TODO: Prepare graphics

	    drawXLabel(gc, area, label, offsetY);
	}
    }
    
    protected void drawXTitle(GC gc, Rectangle area) {
	if (getXTitle() == null) {
	    return;
	}
	
	int offsetX = area.getX();
	int offsetY = area.getY() + area.getHeight() + axisMargin + maxXLabelHeight + axisMargin; // PLOT AREA
	Rectangle titleArea = new Rectangle(offsetX, offsetY, area.getWidth(), xTitleHeight);
	
	//gc.drawText(getXTitle(), titleArea.getX(), titleArea.getY(), 90);
	
	ChartGraphicsUtils.drawString(gc, getXTitle(), titleArea, getChart().getTitleColor(), true); // Draw center string
    }
    
    
    protected List<Rectangle> calculateXLabels(Rectangle area, Rectangle dataArea, double kX) {
	if (xLabels == null || xLabels.isEmpty()) {
	    return null;
	}
	int offsetY = area.getY() + area.getHeight() + axisMargin; // PLOT AREA
	int count = xLabels.size();
	List<Rectangle> rectangles = new ArrayList<Rectangle>();
	
	for (int i = 0; i < count; i++) {

	    AxisLabel label = xLabels.get(i);
	    int x = toTransformValue(label.getValue(), minX, kX);
	    x = x + dataArea.getX(); // DATA AREA
	    
	    Size size = label.getTextSize();
	    if (size == null) {
		continue;
	    }

	    // TODO: Only for horizontal x-labels (need vertical/angle labels)
	    rectangles.add(new Rectangle(x, offsetY, size.getWidth(), size.getHeight()));

	}
	
	return rectangles;
    }


    // Y AXIS
    
    protected void drawYAxis(GC gc, Rectangle area) {
	drawYLabels(gc, area);
	drawYTitle(gc, area);
    }
    
    protected void drawYLabels(GC gc, Rectangle area) {
	if (yLabels == null || yLabels.isEmpty()) {
	    return;
	}
	int offsetX = area.getX() - maxYLabelWidth - axisMargin;
	
	double minY = minDataValue;
	int count = yLabels.size();
	for (int i = 0; i < count; i++) {

	    AxisLabel label = yLabels.get(i);
	    int y = toTransformValue(label.getValue(), minY, kY);
	    y = dataArea.getHeight() - y;	// DATA AREA
	    y = y + dataArea.getY(); 		// DATA AREA
	    label.setAxisValue(y); // TODO: Prepare graphics

	    drawYLabel(gc, area, label, offsetX);

	}
    }
    
    protected void drawYTitle(GC gc, Rectangle area) {
	if (getYTitle() == null) {
	    return;
	}
	
	int titleAreaWidth = area.getHeight();
	
	int offsetX = area.getX() - maxYLabelWidth - axisMargin - yTitleHeight - axisMargin;
	int offsetY = area.getY() + titleAreaWidth; // PLOT AREA
	
	Rectangle titleArea = new Rectangle(offsetX, offsetY, titleAreaWidth, xTitleHeight);
	
	String title = getXTitle(); 
	Size size = gc.getStringSize(title);
	int offset = 0;
	if (size.getWidth() > titleAreaWidth) {
	    title = ChartGraphicsUtils.getCollapseString(title, size.getWidth(), titleAreaWidth);
	    size = gc.getStringSize(title);
	}
	
	offset = (titleAreaWidth - size.getWidth()) / 2;
	gc.drawText(title, titleArea.getX(), titleArea.getY() - offset, 90);
    }
    

    protected List<Rectangle> calculateYLabels(Rectangle area, Rectangle dataArea, double kY) {
	if (yLabels == null || yLabels.isEmpty()) {
	    return null;
	}
	int offsetX = area.getX() - maxYLabelWidth - axisMargin;
	
	double minY = minDataValue;
	int count = yLabels.size();
	
	List<Rectangle> rectangles = new ArrayList<Rectangle>();
	for (int i = 0; i < count; i++) {

	    AxisLabel label = yLabels.get(i);
	    int y = toTransformValue(label.getValue(), minY, kY);
	    y = dataArea.getHeight() - y;	// DATA AREA
	    y = y + dataArea.getY(); 		// DATA AREA


	    Size size = label.getTextSize();
	    if (size == null) {
		continue;
	    }

	    // TODO: Only for horizontal y-labels (need vertical/angle labels)
	    rectangles.add(new Rectangle(offsetX, y, size.getWidth(), size.getHeight()));

	}
	
	return rectangles;
    }
    
    

    protected void drawXLabel(GC gc, Rectangle area, AxisLabel label, int offsetY) {
	ChartGraphicsUtils.drawString(gc, label.getDisplayText(), label.getAxisValue(), offsetY, Color.BLACK);
    }

    protected void drawYLabel(GC gc, Rectangle area, AxisLabel label, int offsetX) {
	ChartGraphicsUtils.drawString(gc, label.getDisplayText(), offsetX, label.getAxisValue(), Color.BLACK);
    }
          
    protected Point[] getDrawPoints(Rectangle area, List<PointValue> pointValues) {
 	int count = pointValues == null ? 0: pointValues.size();
 	Point[] result = new Point[count];
 	PointValue pointValue = null;
 	for (int i = 0; i < count; i++) {
 	    pointValue = pointValues.get(i);
 	    result[i] = toPoint(pointValue);
 	}
 	return result;
    }
    
    protected int toTransformValue(double value, double min, double k) {
	return toAxisValue((value - min) / k);
    }

    protected int toAxisValue(double value) {
	return toInt(value);
    }

    protected int toInt(double value) {
	return (int) value;
    }

    protected Point toPoint(PointValue pointValue) {
	return pointValue == null ? null : new Point(toAxisValue(pointValue.getAxisValue()), toAxisValue(pointValue.getDataValue()));
    }

}

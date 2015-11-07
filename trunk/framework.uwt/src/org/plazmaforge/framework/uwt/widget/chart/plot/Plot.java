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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.graphics.Padding;
import org.plazmaforge.framework.uwt.graphics.Rectangle;
import org.plazmaforge.framework.uwt.graphics.Size;
import org.plazmaforge.framework.uwt.widget.chart.Chart;
import org.plazmaforge.framework.uwt.widget.chart.dataset.Dataset;
import org.plazmaforge.framework.uwt.widget.chart.dataset.Series;
import org.plazmaforge.framework.uwt.widget.chart.util.ChartDataUtils;
import org.plazmaforge.framework.uwt.widget.chart.util.ChartGraphicsUtils;

/**
 * Chart plot
 * 
 * @author ohapon
 *
 */
public abstract class Plot {

    public Color DEFAULT_OUTLINE_COLOR = Color.BLACK;
    
    public int MAX_LABEL_WIDTH = 50;
    
    public int MAX_AXIS_LABEL_WIDTH = 50;
    
    public int MAX_LEGEND_LABEL_WIDTH = 100;
    
    
    private boolean outlineVisible;
    
    private Color outlineColor;
    
    private boolean legendVisible;
    
    private Chart chart;
    
    
    protected Rectangle dataArea;
    
    
    protected Double minDataValue;
    
    protected Double maxDataValue;

    protected Double minAxisValue;
    
    protected Double maxAxisValue;
    
    
    protected double axisValueRange;
    
    protected double dataValueRange;
    
    
    protected AxisValue[] axisValues;
    
    protected List<List<PointValue>> seriesPoints;
    
    
    protected Legend legend;

    protected int maxLegendLabelWidth;
    
    protected int maxLegendLabelHeight;
    
    protected int legendMargin;
    
    protected int legendPadding;
    
    protected int legendLabelSpacing; // Vertical legend label spacing
    
    protected int legendMarkerSize;   // Marker width/height
    
    protected int legendMarkerMargin; // Horizontal space marker->label
    
    
    /**
     * Initialize flag
     */
    private boolean init;
    
    /**
     * Calculate flag
     */
    private boolean calculate;
    
    protected Padding needPadding;
    
    
    public Plot() {
	super();
	
	outlineVisible = true;
	outlineColor = DEFAULT_OUTLINE_COLOR;
	legendVisible = true;
	
	minDataValue = null;
	maxDataValue = null;
	minAxisValue = null;
	maxAxisValue = null;
	
	axisValueRange = 0.0;
	dataValueRange = 0.0;

	// Legend space
	legendMargin = 5;
	legendPadding = 2;
	legendLabelSpacing = 1;
	legendMarkerSize = 5;
	legendMarkerMargin = 2;

    }


    public boolean isInit() {
        return init;
    }
    
    public Chart getChart() {
        return chart;
    }


    public void setChart(Chart chart) {
        this.chart = chart;
    }

    public Dataset getDataset() {
	return chart == null ? null : chart.getDataset();
    }

    public int getSeriesCount() {
 	return chart == null ? 0 : chart.getDataset().getSeriesCount();
    }

    public Series getSeries(int series) {
 	return chart == null ? null : chart.getDataset().getSeries(series);
    }
    
    public boolean isOutlineVisible() {
        return outlineVisible;
    }

    public void setOutlineVisible(boolean outlineVisible) {
        this.outlineVisible = outlineVisible;
    }

    public Color getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor == null ? DEFAULT_OUTLINE_COLOR : outlineColor;
    }

    public boolean isLegendVisible() {
        return legendVisible;
    }


    public void setLegendVisible(boolean legendVisible) {
        this.legendVisible = legendVisible;
    }

    
    
    public void init() {

 	if (init) {
 	    return;
 	}
 	init = true;
 	
 	initData();
 	initLegend();
     }
     
     protected void initData() {
 	
 	// Reset all data
 	resetData();
 	
 	Dataset dataset = getDataset();
 	if (dataset == null) {
 	    return;
 	}
 	
 	// Initialize axis values (axis values before data values!)  
 	initAxisValues(dataset);
 	
 	// Initialize data values
 	initDataValues(dataset);

 	// Initialize value range
 	initValueRange();
 		
     }
     
     protected void resetData() {
 	// Reset data
 	
 	if (seriesPoints != null) {
 	    seriesPoints.clear();
 	    seriesPoints = null;
 	}
 	
 	minDataValue = null;
 	maxDataValue = null;
 	minAxisValue = null;
 	maxAxisValue = null;
     }
     
     protected void initDataValues(Dataset dataset) {

 	seriesPoints = new ArrayList<List<PointValue>>();
 	int seriesCount = dataset.getSeriesCount();

 	for (int i = 0; i < seriesCount; i++) {
 	    Number[] seriesData = dataset.getDataValues(i);

 	    Double min = ChartDataUtils.min(seriesData);
 	    if (minDataValue == null) {
 		minDataValue = min;
 	    } else if (min != null) {
 		minDataValue = Math.min(minDataValue, min);
 	    }

 	    Double max = ChartDataUtils.max(seriesData);
 	    if (maxDataValue == null) {
 		maxDataValue = max;
 	    } else if (max != null) {
 		maxDataValue = Math.max(maxDataValue, max);
 	    }

 	    List<PointValue> pointValues = createPointValues(seriesData, axisValues);
 	    seriesPoints.add(pointValues);
 	}

     }
  
     protected void initAxisValues(Dataset dataset) {
 	
 	Object[] axisObjectValues = dataset.getAxisValues();
 	Number[] axisNumberValues = getAxisNumerValues(axisObjectValues);
 	
 	axisValues = new AxisValue[axisObjectValues.length];
 	AxisValue axisValue = null;
 	for (int i = 0; i < axisObjectValues.length; i++) {
 	   axisValue = new AxisValue(); 
 	   Object value = axisObjectValues[i];
 	   axisValue.setValue(value);
 	   axisValue.setNumberValue(axisNumberValues[i]);
 	   axisValue.setTextValue(value == null ? null : (value.toString())); // TODO: FORMAT
 	   
 	   axisValues[i] = axisValue;
 	}

 	minAxisValue = ChartDataUtils.min(axisNumberValues);
 	maxAxisValue = ChartDataUtils.max(axisNumberValues);
 	
     }
     
     protected void initValueRange() {
 	if (isEmptyValueLimit()) {
 	    return;
 	}
 	
 	axisValueRange = maxAxisValue - minAxisValue;
 	dataValueRange = maxDataValue - minDataValue;
     }
     
     protected Number[] getAxisNumerValues(Object[] values) {
 	return ChartDataUtils.toNumber(values);
     }


     protected boolean isEmptyValueLimit() {
 	return (minAxisValue == null || maxAxisValue == null
 		|| minDataValue == null || maxDataValue == null);
     }
  
     protected boolean isEmptyValueRange() {
 	return (axisValueRange == 0.0 || dataValueRange == 0.0); //TODO: Use epsilon (value < epsilon)
     }
     
     protected void initLegend() {
 	if (!legendVisible) {
 	    return;
 	}
 	
 	Dataset dataset = getDataset();
 	if (dataset == null) {
 	    return;
 	}
 	
 	legend = createLegend(dataset);
     }

     protected Legend createLegend(Dataset dataset) {
 	
 	Legend legend = new Legend();
 	Series[] seriesCollection = dataset.getSeriesCollection();
 	if (seriesCollection == null) {
 	    return legend;
 	}
 	
 	// Populate legend by series collection
 	for (Series series: seriesCollection) {
 	    LegendLabel label = new LegendLabel(series.getLabel(), series.getColor());
 	    legend.addLabel(label);
 	}
 	
 	return legend;
     }

     protected List<List<PointValue>> getSeriesPoints() {
 	return seriesPoints;
     }

     protected List<PointValue> createPointValues(Number[] data, AxisValue[] axis) {
 	if (data == null || axis == null) {
 	    return null;
 	}
 	int count = Math.min(data.length, axis.length);
 	Number dataValue = null;
 	Number axisValue = null;
 	List<PointValue> pointValues = new ArrayList<PointValue>();
 	for (int i = 0; i < count; i++) {
 	    dataValue = data[i];
 	    axisValue = axis[i].getNumberValue();
 	    
 	    if (dataValue == null || axisValue == null) {
 		continue;
 	    }
 	    
 	    double axisDoubleValue = axisValue.doubleValue();
 	    double dataDoubleValue = dataValue.doubleValue();
 	    pointValues.add(new PointValue(axisDoubleValue, dataDoubleValue));
 	}
 	return pointValues;
     }    

     public void calculate(GC gc) {
	if (calculate) {
	    return;
	}
	calculate = true;
	needPadding = null;
	calculateDraw(gc);
    }
     
    public void needPadding(Rectangle chartArea, Padding padding) {
	needPadding = new Padding();
	needPadding.setRight(calculateNeedLegendWidth());
    }

    public Padding getNeedPadding() {
	return needPadding;
    }

    
    public void prepare(GC gc, Rectangle area) {
	prepareDraw(gc, area);
    }
     
    protected abstract void prepareDraw(GC gc, Rectangle area);
    
    protected void calculateDraw(GC gc) {
	calculateLegend(gc);
    };

    protected void calculateLegend(GC gc) {
	if (legend == null) {
	    return;
	}
	int count = legend.getLabelCount();
	for (int i = 0; i < count; i++) {
	    LegendLabel label = legend.getLabel(i);
	    calculateTextSize(gc, label, MAX_LEGEND_LABEL_WIDTH);
	    
	    Size size = label.getDisplayTextSize();
	    
	    // Calculate min, max
	    if (size != null) {
		maxLegendLabelWidth = Math.max(maxLegendLabelWidth, size.getWidth());
		maxLegendLabelHeight = Math.max(maxLegendLabelHeight, size.getHeight());
	    }

	}
    };
    
    
    protected void calculateTextSize(GC gc, Label label, int maxLabelWidth) {
	if (label == null) {
	    return;
	}
	String text = label.getText();
	if (text == null || text.isEmpty()) {
	    return;
	}
	
	// Fixed maxLabelWidth 
	if (maxLabelWidth <= 0 ) {
	    maxLabelWidth = MAX_LABEL_WIDTH;
	}

	// Calculate text size
	Size textSize = gc.getStringSize(text);
	label.setTextSize(textSize);
	
	if (textSize.getWidth() > maxLabelWidth) {
	    
	    // Trim text
	    text = ChartGraphicsUtils.getCollapseString(text, textSize.getWidth(), maxLabelWidth);
	    
	    // Get new text size
	    textSize = gc.getStringSize(text);
	}
	
	label.setDisplayText(text);
	label.setDisplayTextSize(new Size(textSize.getWidth(), textSize.getHeight()));
	
    }

    
    public void draw(GC gc, Rectangle area) {
	
	drawOutline(gc, area);
	
	drawData(gc, area);
    }

    protected abstract void drawData(GC gc, Rectangle area);
    
    public void drawOutline(GC gc, Rectangle area) {
	if (!outlineVisible) {
	    return;
	}
	gc.setForeground(outlineColor);
	gc.drawRectangle(area.getX(), area.getY(), area.getWidth(), area.getHeight());
    }
    
    
	
	
    protected int calculateNeedLegendWidth() {
	if (!legendVisible || legend == null || !legend.hasLabels()) {
	    return 0;
	}
	return (legendMargin * 2) + (legendPadding * 2) + legendMarkerSize + legendMarkerMargin + maxLegendLabelWidth;
    }
    
    protected void drawLegend(GC gc, Rectangle area) {
	
	if (!legendVisible || legend == null || !legend.hasLabels()) {
	    return;
	}
	
	int cornerX = area.getX() + area.getWidth() + legendMargin;
	int cornerY = area.getY();
	
	int maxY = area.getY() + area.getHeight();
	
	int count = legend.getLabelCount();
	int posX = cornerX + legendPadding;
	int posY = cornerY + legendPadding;
	
	boolean drawLabel = false;
	int maxLabelWidth = 0;
	for (int i = 0; i < count; i++) {
	    LegendLabel label = legend.getLabel(i); 
	    String text = label.getDisplayText();
	    if (text == null || text.isEmpty()) {
		continue;
	    }
	    Size textSize = label.getDisplayTextSize();
	    if (textSize == null) {
		continue;
	    }
	    int curY = posY + textSize.getHeight() + legendLabelSpacing;
	    if (curY > maxY) {
		break; // TODO
	    }
	    
	    drawLabel = true;
	    
	    // Marker position
	    int markerX = posX;
	    int markerY = posY;
	    
	    // Center marker by vertical
	    if (legendMarkerSize < textSize.getHeight()) {
		markerY += (textSize.getHeight() / 2 - legendMarkerSize / 2);
	    } else if (legendMarkerSize > textSize.getHeight()) {
		//markerY -= (legendMarkerSize / 2 - textSize.getHeight() / 2);
	    }
	    
	    // Draw marker
	    gc.setBackground(label.getColor());
	    gc.fillRectangle(markerX, markerY, legendMarkerSize, legendMarkerSize);
	    
	    // Draw label
	    gc.drawText(text, posX + legendMarkerSize + legendMarkerMargin, posY);
	    
	    
	    posY = curY;
	    maxLabelWidth = Math.max(maxLabelWidth, textSize.getWidth());
	}
	
	if (drawLabel) {
	    gc.drawRectangle(cornerX, cornerY,  legendMarkerSize + legendMarkerMargin + maxLabelWidth + (legendPadding * 2), posY - cornerY);
	}
	
    }
    
    
 

    /**
     * Create data area by plot area
     * @param area
     * @return
     */
    protected Rectangle createDataArea(Rectangle area) {
	return new Rectangle(area.getX(), area.getY(), area.getWidth(), area.getHeight());
    }
    
    protected Rectangle createSpaceArea(Rectangle area, Padding padding) {
	int widthDelta = padding == null ? 0 : (padding.getLeft() + padding.getRight());
	int heightDelta = padding == null ? 0 : (padding.getTop() + padding.getBottom());
	return new Rectangle(area.getX() + padding.getLeft(), area.getY() + padding.getTop(), area.getWidth() - widthDelta, area.getHeight() - heightDelta);
    }

}

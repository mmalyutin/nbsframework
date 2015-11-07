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

package org.plazmaforge.framework.uwt.widget.chart;

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.graphics.GC;
import org.plazmaforge.framework.uwt.graphics.Padding;
import org.plazmaforge.framework.uwt.graphics.Rectangle;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Canvas;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.widget.chart.dataset.Dataset;
import org.plazmaforge.framework.uwt.widget.chart.dataset.Series;
import org.plazmaforge.framework.uwt.widget.chart.plot.Plot;
import org.plazmaforge.framework.uwt.widget.chart.util.ChartGraphicsUtils;

public class Chart extends Composite {

    public static Color DEFAULT_TITLE_COLOR = Color.BLACK;
    
    public static Color DEFAULT_BORDER_COLOR = Color.BLACK;
    
    public static Color[] DEFAULT_SERIES_COLORS = new Color[] {Color.RED, Color.BLUE, Color.GREEN};
    
    private Padding DEFAULT_PADDING = new Padding(5); //new Padding(20, 60, 20, 40);

    
    private String title;
    
    private boolean titleVisible;
    
    private Color titleColor;
    
    private boolean borderVisible;
    
    private Color borderColor;
    
    private Padding padding;
    
    ////
    
    private Plot plot;
    
    private Dataset dataset;
    
    public Chart(Plot plot, Dataset dataset) {
	this(null, plot, dataset);
    }

    public Chart(String title, Plot plot, Dataset dataset) {
	if (plot == null) {
	    throw new IllegalArgumentException("Chart Plot must be not null");
	}
	if (dataset == null) {
	    throw new IllegalArgumentException("Chart Dataset must be not null");
	}	
	this.title = title;
	this.plot = plot;
	this.dataset = dataset;
	this.titleVisible = true;
	this.titleColor = DEFAULT_TITLE_COLOR;
	this.borderVisible = true;
	this.borderColor = DEFAULT_BORDER_COLOR;
	this.padding = DEFAULT_PADDING;
	
	this.plot.setChart(this);
	
	normalize();
    }      
      
    protected final void normalize() {
	 Series[] seriesCollection = dataset.getSeriesCollection();
	 if (seriesCollection != null) {
	     Series series = null;
	     for (int i = 0; i < seriesCollection.length; i++) {
		 series = seriesCollection[i];
		 if (series.getLabel() == null) {
		     series.setLabel("Series " + (i + 1));
		 }
		 if (series.getColor() == null) {
		     if (i < DEFAULT_SERIES_COLORS.length) {
			 series.setColor(DEFAULT_SERIES_COLORS[i]);
		     } else {
			 series.setColor(generateSeriesColor(seriesCollection));
		     }

		 }
	     }
	 }
    }
    
    protected final Color generateSeriesColor(Series[] seriesCollection) {
	//TODO: Find color in seriesCollection
	return ChartGraphicsUtils.generateColor();
    }
    
    
  
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isTitleVisible() {
        return titleVisible;
    }

    public void setTitleVisible(boolean titleVisible) {
        this.titleVisible = titleVisible;
    }

    public Color getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(Color titleColor) {
        this.titleColor = titleColor == null ? DEFAULT_TITLE_COLOR : titleColor;
    }

    public boolean isBorderVisible() {
        return borderVisible;
    }

    public void setBorderVisible(boolean borderVisible) {
        this.borderVisible = borderVisible;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor == null ? DEFAULT_BORDER_COLOR : borderColor;
    }
    
    public Padding getPadding() {
        return padding;
    }

    public void setPadding(Padding padding) {
        this.padding = padding == null ? DEFAULT_PADDING : padding;
    }

    public Plot getPlot() {
        return plot;
    }

    public Dataset getDataset() {
        return dataset;
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
	
	// Chart Area
	Rectangle chartArea = new Rectangle(0, 0, 250, 200); // TODO: Calculate chart area
	
	// Check space area
	Rectangle spaceArea = createPlotArea(chartArea, padding);
	if (spaceArea.getWidth() <= 0 || spaceArea.getHeight() <= 0) { // TODO Use MinWidth and MinHeight
	    return;
	}
	
		
	plot.init();
	
	plot.calculate(gc);

	boolean hasTitle = hasTitle();
	int titleHeight = hasTitle ? gc.getStringSize(title).getHeight() : 0;  

	Padding drawPadding = padding.clone();
	drawPadding.setTop(drawPadding.getTop() + titleHeight + 5);

	plot.needPadding(chartArea, drawPadding);

	
	Padding needPadding = plot.getNeedPadding();
	if (needPadding != null) {
	    drawPadding.inc(needPadding);
	}
	
	// Plot Area
	Rectangle plotArea = createPlotArea(chartArea, drawPadding);
	plot.prepare(gc, plotArea);

	drawBorder(gc, chartArea);
	
	if (hasTitle) {
	    
	    // Title Area
	    Rectangle titleArea = createTitleArea(chartArea, padding, titleHeight);
	    drawTitle(gc, titleArea);
	    
	}

	plot.draw(gc, plotArea);
    }

    protected Rectangle createPlotArea(Rectangle chartArea, Padding padding) {
	int widthDelta = padding.getLeft() + padding.getRight();
	int heightDelta = padding.getTop() + padding.getBottom();
	return new Rectangle(chartArea.getX() + padding.getLeft(), chartArea.getY() + padding.getTop(), chartArea.getWidth() - widthDelta, chartArea.getHeight() - heightDelta);
    }
    
    protected Rectangle createTitleArea(Rectangle chartArea, Padding padding, int height) {
	//TODO: Use title position: left, top, right, bottom
	
	//TODO: Only for top title position
	return new Rectangle(chartArea.getX() + padding.getLeft(), chartArea.getY() + padding.getTop(), chartArea.getWidth() - padding.getLeft() - padding.getRight(), height);
    }
    
    protected void drawBorder(GC gc, Rectangle area) {
	if (!borderVisible) {
	    return;
	}
	gc.setForeground(borderColor);
	gc.drawRectangle(area.getX(), area.getY(), area.getWidth(), area.getHeight());
    }
    
    protected void drawTitle(GC gc, Rectangle area) {
	if (!titleVisible || title == null) {
	    return;
	}
	ChartGraphicsUtils.drawString(gc, title, area, titleColor, true); // Draw center string
    }  

    protected boolean hasTitle() {
	return titleVisible && title != null;
    }
  
}

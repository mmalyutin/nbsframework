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


import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.graphics.Color;
import org.plazmaforge.framework.uwt.layout.GridData;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Style.VerticalAlign;
import org.plazmaforge.framework.uwt.widget.chart.Chart;
import org.plazmaforge.framework.uwt.widget.chart.dataset.Dataset;
import org.plazmaforge.framework.uwt.widget.chart.dataset.Series;
import org.plazmaforge.framework.uwt.widget.chart.plot.BarPlot;
import org.plazmaforge.framework.uwt.widget.chart.plot.DonutPlot;
import org.plazmaforge.framework.uwt.widget.chart.plot.LinePlot;
import org.plazmaforge.framework.uwt.widget.chart.plot.PiePlot;

public class ChartTab extends AbstractTab  {

    @Override
    protected void createUI() {
	setLayout(new GridLayout(2));
	
	Chart lineChart = createLineChart();
	add(lineChart);
	
	Chart barChart = createBarChart();
	add(barChart);

	Chart pieChart = createPieChart();
	add(pieChart);

	Chart donutChart = createDonutChart();
	add(donutChart);

    }
    
    
    private Chart createLineChart() {
	LinePlot plot = new LinePlot();
	
	plot.setAxisTitle("Axis values");
	plot.setDataTitle("Data values");
	
	Dataset dataset = createDataset();
	Chart chart = new Chart("Line Chart", plot, dataset);
	chart.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.FILL, true, true));
	return chart;
    }
    
    private Chart createBarChart() {
	BarPlot plot = new BarPlot();
	
	Dataset dataset = createDataset();
	Chart chart = new Chart("Bar Chart", plot, dataset);
	chart.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.FILL, true, true));
	return chart;
    }

    private Chart createPieChart() {
	PiePlot plot = new PiePlot();
	
	Dataset dataset = createDataset();
	Chart chart = new Chart("Pie Chart", plot, dataset);
	chart.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.FILL, true, true));
	return chart;
    }
    
    private Chart createDonutChart() {
	DonutPlot plot = new DonutPlot();
	
	Dataset dataset = createDataset();
	Chart chart = new Chart("Donut Chart", plot, dataset);
	chart.setLayoutData(new GridData(1, 1, HorizontalAlign.FILL, VerticalAlign.FILL, true, true));
	return chart;
    }

    private Dataset createDataset() {
	List<Series> seriesCollection = new ArrayList<Series>();
	Series series = new Series(new Number[] {10, 30, 60, 100, 140});
	series.setColor(Color.GREEN);
	seriesCollection.add(series);
	
	series = new Series(new Number[] {0, 13, 35, 63, 120});
	series.setColor(Color.BLUE);
	seriesCollection.add(series);
	
	return Dataset.createDatasetBySeriesCollection(seriesCollection.toArray(new Series[0]), new Object[] {10, 30, 60, 90, 130});
    }
}

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

package org.plazmaforge.framework.uwt.widget.chart.dataset;

import java.util.List;

/**
 * Chart dataset
 * 
 * @author ohapon
 *
 */
public class Dataset {

    
    /**
     * Array of series
     */
    private Series[] seriesCollection;
      
    /**
     * Array of axis values
     */
    private Object[] axisValues;
    
 
    

    public Dataset(Number[][] dataValues, Object[] axisValues) {
	initialize(dataValues, axisValues);
    }
    

    public Dataset(Series[] seriesCollection, Object[] axisValues) {
	initialize(seriesCollection, axisValues);
    }
    
    public Number[] getDataValues(int series) {
	return seriesCollection == null ? null : seriesCollection[series].getValues();
    }
    
    public Number getDataValue(int series, int index) {
   	return seriesCollection == null ? null : seriesCollection[series].getValues()[index];
    }
    
    public Object getAxisValue(int index) {
   	return axisValues == null ? null : axisValues[index];
    }
    
    public Object[] getAxisValues() {
   	return axisValues;
    }
    
    public int getSeriesCount() {
	return seriesCollection == null ? 0 : seriesCollection.length;
    }
    
    public int getItemCount() {
 	return axisValues == null ? 0 : axisValues.length;
    }
    
    public Series[] getSeriesCollection() {
        return seriesCollection;
    }

    public Series getSeries(int series) {
        return seriesCollection == null ? null : seriesCollection[series];
    }

    protected final void initialize(Number[][] dataValues, Object[] axisValues) {
	
	// seriesCount is length of dataValues array
	int seriesCount = dataValues == null ? 0 : dataValues.length;
	
	// By default itemCount is length of axisValues array
	int itemCount = axisValues == null ? 0 : axisValues.length;
	
	// Calculate Max of item count
	for (int s = 0; s < seriesCount; s++) {
	    Number[] seriesData = dataValues[s];
	    if (seriesData == null) {
		continue;
	    }
	    itemCount = Math.max(itemCount, seriesData.length);
	}
	
	
	// Create series collection
	this.seriesCollection = new Series[seriesCount];
	 
	// Normalize length of series data array
	if (dataValues != null) {
	    for (int s = 0; s < seriesCount; s++) {
		Number[] seriesValues = dataValues[s];
		if (seriesValues == null) {
		    
		    // Empty array
		    seriesValues = new Number[itemCount];
		} else if (seriesValues.length < itemCount) {
		    
		    // Extends array
		    Number[] newSeriesValues = new Number[itemCount];
		    System.arraycopy(seriesValues, 0, newSeriesValues, 0, seriesValues.length);
		    seriesValues = newSeriesValues;
		}
		this.seriesCollection[s] = new Series(seriesValues);
	    }
	}
	
	// Normalize length of axis
	if (axisValues == null) {
	    
	    // Empty array
	    axisValues = new Object[itemCount];
	} else if (axisValues.length < itemCount) {
	    
	    // Extends array
	    Object[] newAxisValues = new Object[itemCount];
	    System.arraycopy(axisValues, 0, newAxisValues, 0, axisValues.length);
	    axisValues = newAxisValues;
	}
	
	this.axisValues = axisValues;

    }
    
    protected final void initialize(Series[] seriesCollection, Object[] axisValues) {
	
	// seriesCount is length of dataValues array
	int seriesCount = seriesCollection == null ? 0 : seriesCollection.length;
	
	// By default itemCount is length of axisValues array
	int itemCount = axisValues == null ? 0 : axisValues.length;
	
	// Calculate Max of item count
	for (int s = 0; s < seriesCount; s++) {
	    Number[] seriesData = seriesCollection[s].getValues();
	    if (seriesData == null) {
		continue;
	    }
	    itemCount = Math.max(itemCount, seriesData.length);
	}
	
	
	// Create series collection
	this.seriesCollection = new Series[seriesCount];
	 
	// Normalize length of series data array
	if (seriesCollection != null) {
	    for (int s = 0; s < seriesCount; s++) {
		Series series = seriesCollection[s];
		Number[] seriesValues = series == null ? null : series.getValues();
		if (seriesValues == null) {
		    
		    // Empty array
		    seriesValues = new Number[itemCount];
		} else if (seriesValues.length < itemCount) {
		    
		    // Extends array
		    Number[] newSeriesValues = new Number[itemCount];
		    System.arraycopy(seriesValues, 0, newSeriesValues, 0, seriesValues.length);
		    seriesValues = newSeriesValues;
		}
		Series newSeries = new Series(seriesValues);
		if (series != null) {
		    newSeries.setLabel(series.getLabel());
		    newSeries.setColor(series.getColor());
		}
		this.seriesCollection[s] = newSeries;
	    }
	}
	
	// Normalize length of axis
	if (axisValues == null) {
	    
	    // Empty array
	    axisValues = new Object[itemCount];
	} else if (axisValues.length < itemCount) {
	    
	    // Extends array
	    Object[] newAxisValues = new Object[itemCount];
	    System.arraycopy(axisValues, 0, newAxisValues, 0, axisValues.length);
	    axisValues = newAxisValues;
	}
	
	this.axisValues = axisValues;

    }

    public static Dataset createDatasetBySeries(Number[][] seriesValues, Object[] axisValues) {
   	return new Dataset(seriesValues, axisValues);
    }
   
    public static Dataset createDatasetBySeries(List<Number[]> seriesValues, List<Object> axisValues) {
	return new Dataset(seriesValues == null ? null : seriesValues.toArray(new Number[0][0]), axisValues == null ? null: axisValues.toArray(new Object[0]));
    }
    
    public static Dataset createDatasetBySeriesCollection(List<Series> seriesCollection, List<Object> axisValues) {
  	return new Dataset(seriesCollection == null ? null : seriesCollection.toArray(new Number[0][0]), axisValues == null ? null: axisValues.toArray(new Object[0]));
    }
    
    public static Dataset createDatasetBySeriesCollection(Series[] seriesCollection, Object[] axisValues) {
  	return new Dataset(seriesCollection, axisValues);
    }
    
    
    public static Dataset createDatasetByItem(Number[][] itemValues, Object[] axisValues) {
	
  	int itemCount = itemValues == null ? 0: itemValues.length;
  	int seriesCount = 0;
	
	// Calculate Max of series count
	for (int i = 0; i < itemCount; i++) {
	    Number[] itemData = itemValues[i];
	    if (itemData == null) {
		continue;
	    }
	    seriesCount = Math.max(seriesCount, itemData.length);
	}
	  	
	// Transformation item array to data array
	Number[][] dataValues = new Number[seriesCount][itemCount];

	for (int s = 0; s < seriesCount; s++) {
	    Number[] seriesData = new Number[itemCount];
	    for (int i = 0; i < itemCount; i++) {
		Number[] itemData = itemValues[i];
		if (itemData == null) {
		    continue;
		}
		seriesData[i] = itemData[s];

	    }
	    dataValues[s] = seriesData;
	}

	return new Dataset(dataValues, axisValues);

    }
    
    public static Dataset createDatasetByItem(List<Number[]> itemValues, List<Object> axisValues) {
	
	int itemCount = itemValues == null ? 0: itemValues.size();
	int seriesCount = 0;
	
	// Calculate Max of series count
	for (int i = 0; i < itemCount; i++) {
	    Number[] itemData = itemValues.get(i);
	    if (itemData == null) {
		continue;
	    }
	    seriesCount = Math.max(seriesCount, itemData.length);
	}
	
	// Transformation item list to data array
	Number[][] data = new Number[seriesCount][itemCount];
	
	for (int s = 0; s < seriesCount; s++) {
	    Number[] seriesData = new Number[itemCount];
	    for (int i = 0; i < itemCount; i++) {
		 Number[] itemData = itemValues.get(i);
		 if (itemData == null) {
		     continue;
		 }
		 seriesData[i] = itemData[s];
		
	    }
	    data[s] = seriesData;
	}
	
   	return new Dataset(data, axisValues == null ? null: axisValues.toArray(new Object[0]));
    }
    
    
    
}

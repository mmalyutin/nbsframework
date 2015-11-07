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

package org.plazmaforge.framework.uwt.widget;

public class SpinnerField extends NumberField {

    
    public static final String PROPERTY_DECIMALS = "decimals";
    
    
    
    public static final double DEFAULT_MIN_VALUE = 0d;
    
    public static final double DEFAULT_MAX_VALUE = 100d;
    
    public static final double DEFAULT_INCREMENT_VALUE = 1d;
    

    private double minValue = DEFAULT_MIN_VALUE;
    
    private double maxValue = DEFAULT_MAX_VALUE;
    
    private double incrementValue = DEFAULT_INCREMENT_VALUE;
    
    
    private int decimals;
    
    public SpinnerField() {
	super();
    }

    
    public SpinnerField(double value, double minValue, double maxValue, double incrementValue, int decimals) {
	super();
	setValue(value);
	initialize(minValue, maxValue, incrementValue, decimals);
    }

    public SpinnerField(double minValue, double maxValue, double incrementValue, int decimals) {
	super();
	initialize(minValue, maxValue, incrementValue, decimals);
    }

    public SpinnerField(double minValue, double maxValue, double incrementValue) {
	super();
	initialize(minValue, maxValue, incrementValue, 0);
    }

    public SpinnerField(double minValue, double maxValue) {
	super();
	initialize(minValue, maxValue, DEFAULT_INCREMENT_VALUE, 0);
    }
    

    private void initialize(double minValue, double maxValue, double incrementValue, int decimals) {
	setMinValue(minValue);
	setMaxValue(maxValue);
	setIncrementValue(incrementValue);
	setDecimals(decimals);
    }


    @Override
    protected void checkValue(Number value) {
	super.checkValue(value);
    }
    
    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
        fireChangeProperty(PROPERTY_MIN_VALUE, minValue);
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
        fireChangeProperty(PROPERTY_MAX_VALUE, maxValue);
    }

    public double getIncrementValue() {
        return incrementValue;
    }

    public void setIncrementValue(double incrementValue) {
	if (incrementValue < 0) {
	    throw new IllegalArgumentException("MinValue must be only positive");
	}
        this.incrementValue = incrementValue;
        fireChangeProperty(PROPERTY_INCREMENT_VALUE, incrementValue);
    }
    
    public int getDecimals() {
        return decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
	if (decimals < 0) {
	    throw new IllegalArgumentException("Decimals must be only positive");
	}
        fireChangeProperty(PROPERTY_DECIMALS, decimals);
    }



}

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

package org.plazmaforge.framework.uwt.swing.widget;

import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JTextField;
import javax.swing.text.Document;

/**
 * Number Field
 * 
 * @author ohapon
 *
 */
public class XNumberField extends JTextField implements IXField {

    public static int DEFAULT_COLUMNS = 12;
    
    public static int DEFAULT_DECIMALS = 2;
    
    public static Number DEFAULT_VALUE = 0.0;

    
    private int decimals;

    private boolean integerType;

    private Number value;

    private NumberFormat numberFormat;
    
    
    /**
     * Preferred width
     */
    private int preferredWidth;
    
    /**
     * Preferred height
     */
    private int preferredHeight;
    

    public XNumberField() {
        setColumns(DEFAULT_COLUMNS);
        initialize();
    }

    public XNumberField(int col) {
        super(col);
        initialize();
    }


    private void initialize() {
	decimals = DEFAULT_DECIMALS;
	value = DEFAULT_VALUE;
        numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setGroupingUsed(true);
        this.setText(numberFormat.format(0));
        setHorizontalAlignment(JTextField.RIGHT);
        setPreferredWidth(DEFAULT_FIELD_WIDTH);
    }
	
    protected Document createDefaultModel() {
        return new NumberDocument();
    }

    public int getDecimals() {
        return this.decimals;
    }

    public void setDecimals(int decimals) {
        this.decimals = decimals;
        if (!isIntegerType()) {
            numberFormat.setMaximumFractionDigits(decimals);
            numberFormat.setMinimumFractionDigits(decimals);
        }
        this.setText(numberFormat.format(getSafeValue()));
    }

    public boolean isIntegerType() {
        return this.integerType;
    }

    public void setIntegerType(boolean integerType) {
        if (integerType) {
            numberFormat.setMaximumFractionDigits(0);
            numberFormat.setMinimumFractionDigits(0);
        } else {
            numberFormat.setMaximumFractionDigits(getDecimals());
            numberFormat.setMinimumFractionDigits(getDecimals());
        }
        this.integerType = integerType;
        this.setText(numberFormat.format(getSafeValue()));
    }

    public void setPattern(String pattern) {
	numberFormat = new DecimalFormat(pattern);
	this.setText(numberFormat.format(getSafeValue()));
    }

    protected void parseValue() {
        setText(getText());
        try {
            value =  numberFormat.parse(getText());
        } catch (ParseException ex){
            ex.printStackTrace();
        }
        setText(numberFormat.format(getSafeValue(value)));
    }

    public void setValue(Number value) {
        this.value = value;
        this.setText(numberFormat.format(getSafeValue(value)));
    }

    public Number getValue() {
        parseValue();
        return this.value;
    }

    protected Number getSafeValue(Number value) {
	return value == null ? DEFAULT_VALUE : value;
    }

    protected Number getSafeValue() {
  	return getValue();
    }
    
    public boolean isGrouping() {
        return this.numberFormat.isGroupingUsed();
    }

    public void setGrouping(boolean grouping) {
        this.numberFormat.setGroupingUsed(grouping);
    }

    ////
    
    public int getPreferredWidth() {
        return preferredWidth;
    }

    public void setPreferredWidth(int preferredWidth) {
	if (preferredWidth < 0) {
	    throw new IllegalArgumentException("PreferredWidth must be >= 0");
	}
        this.preferredWidth = preferredWidth;
    }

    public int getPreferredHeight() {
        return preferredHeight;
    }

    public void setPreferredHeight(int preferredHeight) {
	if (preferredHeight < 0) {
	    throw new IllegalArgumentException("PreferredHeight must be >= 0");
	}
        this.preferredHeight = preferredHeight;
    }

    @Override
    public Dimension getPreferredSize() {
	Dimension size = super.getPreferredSize();
	super.getMinimumSize();
	if (preferredWidth > 0) {
	    size.width = preferredWidth;
	}
	if (preferredHeight > 0) {
	    size.height = preferredHeight;
	}
	return size;
    }
    
    @Override
    public Dimension getMinimumSize() {
	return getPreferredSize();
    }
    
    
    @Override
    public Object getFieldValue() {
	return getValue();
    }

    @Override
    public void setFieldValue(Object value) {
	setValue((Number) value);
    }
    
}

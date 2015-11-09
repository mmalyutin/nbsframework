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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import org.plazmaforge.framework.uwt.util.DateMaskCreator;
import org.plazmaforge.framework.uwt.util.FormatUtils;


// TODO: MUST ENABLE CODE 
public class XDateField extends JFormattedTextField implements IXField {

    /**
     * Format of date
     */
    private DateFormat dateFormat;
    
    
    /**
     * Preferred width
     */
    private int preferredWidth;
    
    /**
     * Preferred height
     */
    private int preferredHeight;

    
    public XDateField() {
	init(null);
	//init("dd.MM.yyyy");
    }

    private void init(String datePattern) {
	initDatePattern(datePattern);
	// setFocusLostBehavior(JFormattedTextField.COMMIT);
	setPreferredWidth(DEFAULT_FIELD_WIDTH);
    }

    private void initDatePattern(String datePattern) {

	if (datePattern == null) {
	    datePattern = getDefaultDatePattern();
	}
	
	// WARNING !!!
	// Normalize date pattern (M/d/yy - > MM/dd/yyyy)
	// Edit date pattern must be with fixed position
	
	
	datePattern = FormatUtils.normalizeDate(datePattern); 
	try {
	    String maskPattern = DateMaskCreator.getDateMaskPattern(datePattern);
	    dateFormat = new SimpleDateFormat(datePattern);
	    MaskFormatter editFormatter = new MaskFormatter(maskPattern);
	    setFormatterFactory(new DefaultFormatterFactory(editFormatter));
	    setColumns(maskPattern.length());
	} catch (ParseException ex) {
	    ex.printStackTrace();
	}
    }

    public void setPattern(String pattern) {
	initDatePattern(pattern);
    }
    
    private DateFormat getDateFormat() {
	return dateFormat;
    }

    protected String getDefaultDatePattern() {
	
	//TODO [MUST-DISABLE]
	SimpleDateFormat dateFormat = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT);
	String datePattern = dateFormat.toPattern();
	//String datePattern = FormatUtils.normalizeDate(datePattern);
	return datePattern;
	
	//TODO [MUST-ENABLE]
	//return SystemEnvironment.getNormalizeDatePattern();
    }

    private final String EMPTY_VAL = null;


    //oha-new
    public void setValue(Object value) {
	setDate((Date) value);
    }

//TODO    
//oha-new
//    public Object getValue() {
//        return getDate();
//    }

    public void setDate(Date date) {
	if (date == null) {
	    super.setValue(EMPTY_VAL);
	} else {
	    super.setValue(getDateFormat().format(date));
	}
    }

    public Date getDate() {
	Object val = null;
	Date date = null;
	try {
	    commitEdit();
	    val = super.getValue();
	    if (val == null)
		return null;
	    date = getDateFormat().parse((String) val);
	} catch (ParseException e) {
	    //
	}
	return date;
    }
    
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

    public Dimension getPreferredSize() {
	Dimension size = super.getPreferredSize();
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
	return getDate();
    }

    @Override
    public void setFieldValue(Object value) {
	setDate((Date) value);
    }
    
}

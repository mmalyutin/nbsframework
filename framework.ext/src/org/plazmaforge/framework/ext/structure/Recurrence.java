/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.ext.structure;

import java.io.Serializable;
import java.util.Date;

/**
 * Describe Recurrence
 * 
 * @author ohapon
 *
 */
public class Recurrence implements Serializable {

    private static final long serialVersionUID = 6350069208188169574L;
    

    public String DAILY = "D:";
    
    public String WEEKLY = "W:";
    
    public String MONTHLY = "M:";
    
    public String YEARLY = "Y:";
    
    ////
    
    public String RANGE_START = "RS";
    
    public String RANGE_END = "RE";
    
    public String RECURRENCE_COUNT = "RC";
    ////
    
    // Day of month (Integer value: 1..31)
    public String DAY = "D";
    
    // Day of month (Integer value: 1..31)
    public String DAY_OF_MONTH = DAY;
    
    // SUNDAY, MONDAY, ...DAY, WEEKDAY, HOLIDAY (String value because we use DAY, WEEKDAY, HOLIDAY)
    public String DAY_OF_WEEK = "DW";
    
    // Month of year (Integer value) start with 1
    public String MONTH = "M";
    
    // Week of month (String value: 1, 2, 3, 4, LAST, FIRST)
    public String WEEK_OF_MONTH = "WM";
    
    public String EVERY_DAY = "ED";
    public String EVERY_WEEK = "EW";
    public String EVERY_MONTH = "EM";
    public String EVERY_YEAR = "EY";
    
    
    
    private String expression;

    
    private String type;
    
    private Date rangeStart;
    
    private Date rangeEnd;
    
    private int recurrenceCount;
    
    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
        parse();
    }
    
    protected void parse() {
	if (expression == null) {
	    clear();
	    return;
	}
	
	//String str = expression.trim();
	
	// TODO:  Must parse following expressions
	// May be use CronExpression
	
	// "D: ED=1; DW=SUNDAY, MONDAY"
	// "D: ED=WEEKDAY; DW=SUNDAY, MONDAY"
	
	// "W: EW=1; DW=SUNDAY, MONDAY"
	
	// "M: EM=1; DAY=25"
	// "M: EM=1; WM=1; DW=SUNDAY"

	// "Y: EY=1; M=2; D=25"
	// "Y: EY=1; WM=3; DW=1; M=2"


    }
    
    protected void clear() {
	type = null;
	rangeStart = null;
	rangeEnd = null;
	recurrenceCount = 0;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(Date rangeStart) {
        this.rangeStart = rangeStart;
    }

    public Date getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(Date rangeEnd) {
        this.rangeEnd = rangeEnd;
    }

    public int getRecurrenceCount() {
        return recurrenceCount;
    }

    public void setRecurrenceCount(int recurrenceCount) {
        this.recurrenceCount = recurrenceCount;
    }
    
    
}

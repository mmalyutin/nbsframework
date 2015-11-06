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
 * 
 * @author ohapon
 *
 */
public class DatePeriod implements Serializable {

    private static final long serialVersionUID = -8537661649244172759L;
    
    
    // TYPE
    public static final String DAY = "DAY";
    public static final String WEEK = "WEEK";
    public static final String MONTH = "MONTH";
    public static final String QUARTER = "QUARTER";
    public static final String YEAR = "YEAR";
    public static final String CUSTOM = "CUSTOM";
    public static final String UNKNOWN = "UNKNOWN";
    
    
    
    // DAY
    public static final String TODAY = "TODAY";
    public static final String YESTERDAY = "YESTERDAY";
    public static final String TOMORROW = "TOMORROW";
    
    // WEEK
    public static final String CURRENT_WEEK = "CURRENT_WEEK";
    public static final String LAST_WEEK = "LAST_WEEK";
    public static final String NEXT_WEEK = "NEXT_WEEK";

    // MONTH
    public static final String CURRENT_MONTH = "CURRENT_MONTH";    
    public static final String LAST_MONTH = "LAST_MONTH";
    public static final String NEXT_MONTH = "NEXT_MONTH";

    // YEAR
    public static final String CURRENT_YEAR = "CURRENT_YEAR";    
    public static final String LAST_YEAR = "LAST_YEAR";
    public static final String NEXT_YEAR = "NEXT_YEAR";
  
    
    private Date startDate;
    
    private Date endDate;
    
    private String code;
    
    private String name;

    
    public DatePeriod() {
	super();
    }

    
    public DatePeriod(String code, String name, Date startDate, Date endDate) {
	super();
	this.code = code;
	this.name = name;
	this.startDate = startDate;
	this.endDate = endDate;
    }


    public DatePeriod(String code, String name) {
	super();
	this.code = code;
	this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public void setPeriodDate(Date startDate, Date endDate) {
	setStartDate(startDate);
	setEndDate(endDate);
    }
    
 
    
    
}

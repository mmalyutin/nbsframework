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

package org.plazmaforge.framework.core.logging;

import java.io.PrintStream;
import java.util.Date;

public class SimpleHandler extends Handler {

    @Override
    public void log(LogRecord record) {
	if (record == null) {
	    return;
	}
	String date = formatDate(new Date(record.getTimestamp()));
	String level = record.getLevel() == null ? null : record.getLevel().getName(); 
	//String methodInfo = formatMethodInfo(findStackTrace(record.getLoggerName()));
	String message = record.getMessage() == null ? "" : record.getMessage();
	Throwable error = record.getError();
	
	PrintStream out = System.out; //record.getLevel() == Level.ERROR ? System.err : System.out;   
	
	out.println(
		"" + date
		+ (level == null ? "" : (" " + level))
		//+ (methodInfo == null ? "" : (" " + methodInfo))
		+ (" - " + message ));
	
	if (error != null) {
	    error.printStackTrace(out);
	}
	
    }
    
    protected String formatDate(Date date) {
   	if (date == null) {
   	    return null;
   	}
   	int year = date.getYear() + 1900;
   	int month = date.getMonth() + 1;
   	int day = date.getDate();
   	
   	int hours = date.getHours();
   	int minutes = date.getMinutes();
   	int seconds = date.getSeconds();	

   	return "" + year + "-" + toString2(month) + "-" + toString2(day)
   		+ " " + toString2(hours) + ":" + toString2(minutes)+ ":" + toString2(seconds);		
       }
       
       protected String toString2(int value) {
   	return "" + (value < 10 ? ("0" + value) : value);
       }    
}

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

/**
 * 
 */
package org.plazmaforge.framework.core.logging;

import java.util.Date;


/**
 * @author ohapon
 *
 */
public class ConsoleHandler extends Handler {

    @Override
    public void log(LogRecord record) {
	if (record == null) {
	    return;
	}
	String date = formatDate(new Date(record.getTimestamp()));
	String level = record.getLevel() == null ? null : record.getLevel().getName(); 
	String methodInfo = formatMethodInfo(findStackTrace(record.getLoggerName()));
	String message = record.getMessage() == null ? "" : record.getMessage();
	Throwable error = record.getError();
	
	System.out.println(
		"" + date
		+ (level == null ? "" : (" " + level))
		+ (methodInfo == null ? "" : (" " + methodInfo))
		+ (" - " + message));
	
	if (error == null) {
	    return;
	}
	
	System.out.println(formatError(error));
    }
    
    protected StackTraceElement[] getStackTrace() {
	return Thread.currentThread().getStackTrace();
    }
    
    protected StackTraceElement findStackTrace(String name) {
	StackTraceElement[] stackTrace = getStackTrace();
	return findStackTrace(stackTrace, name);
    }
    
    protected StackTraceElement findStackTrace(StackTraceElement[] stackTrace, String name) {
	if (stackTrace == null || name == null) {
	    return null;
	}
	for (StackTraceElement e: stackTrace) {
	    if (name.equals(e.getClassName())) {
		return e;
	    }
	}
	return null;
    }

    protected String formatMethodInfo(StackTraceElement stackTrace) {
	if (stackTrace == null) {
	    return null;
	}
	String className = stackTrace.getClassName();
	int index = className.lastIndexOf(".");
	if (index > 0){
	    className = className.substring(index + 1);
	}
	return className + ":" + stackTrace.getLineNumber();
    }
    
    protected String formatError(Throwable error) {
	if (error == null) {
	    return null;
	}
	StringBuilder builder = new StringBuilder();
	
	// Add error class name
	builder.append(error.getClass().getName());
	
	// Add error message
	String message = error.getMessage();
	if (message != null) {
	    builder.append(": " + message);
	}
	
	// Add error stack trace
	StackTraceElement[] stackTraces = error.getStackTrace();
	if (stackTraces != null) {
	    for (StackTraceElement e: stackTraces) {
		String details = e.getFileName();
		if (details == null) {
		    if (e.getLineNumber() == -1 ) {
			details = "Unknown Source";
		    } else if (e.getLineNumber() == -2) {
			details = "Native Method";
		    } else {
			details = "";
		    }

		} else {
		    details = e.getFileName() + ":" + e.getLineNumber();
		}
		
		builder.append("\n\t at " + e.getClassName() + "." + e.getMethodName() + "(" + details + ")");
	    }
	}
	return builder.toString();
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

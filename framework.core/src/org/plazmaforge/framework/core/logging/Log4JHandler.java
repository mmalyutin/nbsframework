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

import org.apache.log4j.Logger;

public class Log4JHandler extends Handler {

    @Override
    public void log(LogRecord record) {
	if (record == null) {
	    return;
	}
	String loggerName = getLoggerName(record);
	Logger log4jLogger = Logger.getLogger(loggerName);
	
	int levelValue = record.getLevel() == null ? Level.INFO_INT : record.getLevel().getValue();
	String message = record.getMessage();
	Throwable error = record.getError();
	
	if (levelValue == Level.FATAL_INT) {
	    log4jLogger.fatal(message, error);
	    
	} else if (levelValue == Level.ERROR_INT) {
	    log4jLogger.error(message, error);
	    
	} else if (levelValue == Level.WARN_INT) {
	    log4jLogger.warn(message, error);
	    
	} else if (levelValue == Level.INFO_INT) {
	    log4jLogger.info(message, error);
	    
	} else if (levelValue == Level.DEBUG_INT) {
	    log4jLogger.debug(message, error);
	    
	} else if (levelValue == Level.TRACE_INT) {
	    log4jLogger.debug(message, error); // TODO: trace -> debug
	    
	} else {
	    log4jLogger.info(message, error); // TODO: info by default
	} 
	
	
	
    }

}

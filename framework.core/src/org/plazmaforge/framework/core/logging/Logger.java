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

import java.util.ArrayList;
import java.util.List;

/**
 * @author ohapon
 *
 */
public class Logger {

    
    public static final Level DEFAULT_SOFT_LEVEL = Level.INFO;
    
	    
    private final String name;

    private final Logger parent;
    
    private List<Handler> handlers = new ArrayList<Handler>();
    
    private Level softLevel;
    
    protected Logger(String name) {
	this(name, null);
    }
    
    protected Logger(String name, Logger parent) {
	this.name = name;
	this.parent = parent;
	
    }
    
    public String getName() {
        return name;
    }
    
    public Logger getParent() {
        return parent;
    }

 


    public static Logger getLogger(Class<?> klass) {
	if (klass == null) {
	    return null;
	}
	return getLogger(klass.getName());
    }

    public static Logger getLogger(String name) {
	if (name == null) {
	    return null;
	}
	return LogManager.getLogger(name);
    }

    public void addHandler(Handler handler) {
	handlers.add(handler);
    }

    public void removeHandler(Handler handler) {
	handlers.remove(handler);
    }

    public void clearHandlers() {
	handlers.clear();
    }

    public List<Handler> getHandlers() {
        return handlers;
    }
    
    public boolean hasHandlers() {
        return handlers != null && !handlers.isEmpty();
    }
    
    public boolean hasAutoLevel() {
	return softLevel != null;
    }

    public Level getSoftLevel() {
	if (softLevel == null) {
	    return DEFAULT_SOFT_LEVEL;
	}
	return softLevel;
    }
    
    
    public void setSoftLevel(Level softLevel) {
        this.softLevel = softLevel;
    }

    protected LogRecord createLogRecord(Level level, String message) {
	LogRecord record = new LogRecord(level, message);
	record.setLoggerName(this.getName());
	return record;
    }
    
    /**
     * General log method
     * 
     * @param record
     */
    public void log(LogRecord record) {
	if (record == null) {
	    return;
	}
	
	//TODO: Check level
	
	// Process own handlers
	processLog(getHandlers(), record);
	
	// Process parent handler
	if (getParent() == null) {
	    return;
	}
	getParent().log(record);
	
    }
    
    protected void processLog(List<Handler> handlers, LogRecord record) {
	if (handlers == null || record == null) {
	    return;
	}
	for (Handler handler: handlers) {
	    handler.log(record);
	}
    }
    
    public void log(Level level, String message, Throwable error) {
	//TODO: Check level
	LogRecord record = createLogRecord(level, message);
	record.setError(error);
	log(record);
    }
    
    public void log(Level level, String message) {
	log(level, message, null);
    }

    public void log(Level level, Throwable error) {
	log(level, null, error);
    }

    
    
    // soft-level
    
    public void log(String message) {
	// TODO: Off level
	Level level = getSoftLevel();
	if (Level.OFF_INT == level.getValue()) {
	    return;
	}
	log(level, message);
    }
    


    // fatal
    
    public void fatal(String message, Throwable error) {
	log(Level.FATAL, message, error);
    }
    
    public void fatal(String message) {
	log(Level.FATAL, message);
    }
    
    public void fatal(Throwable error) {
	log(Level.FATAL, error);
    }
    
    
    // error
    
    public void error(String message, Throwable error) {
	log(Level.ERROR, message, error);
    }
    
    public void error(String message) {
	log(Level.ERROR, message);
    }

    public void error(Throwable error) {
	log(Level.ERROR, error);
    }

    // warn
    
    public void warn(String message, Throwable error) {
	log(Level.WARN, message, error);
    }
    
    public void warn(String message) {
	log(Level.WARN, message);
    }

    public void warn(Throwable error) {
	log(Level.WARN, error);
    }

    // info
    
    public void info(String message, Throwable error) {
	log(Level.INFO, message, error);
    }
    
    public void info(String message) {
	log(Level.INFO, message);
    }

    public void info(Throwable error) {
	log(Level.INFO, error);
    }

    // debug
    
    public void debug(String message, Throwable error) {
	log(Level.DEBUG, message, error);
    }
    
    public void debug(String message) {
	log(Level.DEBUG, message);
    }

    public void debug(Throwable error) {
	log(Level.DEBUG, error);
    }

    // trace
    
    public void trace(String message, Throwable error) {
	log(Level.TRACE, message, error);
    }
    
    public void trace(String message) {
	log(Level.TRACE, message);
    }

    public void trace(Throwable error) {
	log(Level.TRACE, error);
    }

}

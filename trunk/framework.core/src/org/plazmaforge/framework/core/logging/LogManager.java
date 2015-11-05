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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogManager {

    private static Logger rootLogger = new RootLogger("ROOT");

    private static Map<String, Logger> loggers = new HashMap<String, Logger>();
    
    private static List<Handler> defaultHandlers = new ArrayList<Handler>();

    private LogManager() {
    }

    static {
	defaultHandlers.add(new SimpleHandler());
	
	//rootLogger.addHandler(new ConsoleHandler());
	//rootLogger.addHandler(new Log4JHandler());
    }

    public synchronized static Logger getLogger(String name) {
	if (name == null) {
	    return null;
	}
	Logger logger = loggers.get(name);
	if (logger == null) {
	    logger = new Logger(name, rootLogger);
	    loggers.put(name, logger);
	}
	return logger;
    }
    
    public static Logger getRootLogger() {
	return rootLogger;
    }

    public static class RootLogger extends Logger {

	protected RootLogger(String name) {
	    super(name);
	}
	
	public List<Handler> getHandlers() {
	    if (!hasHandlers()) {
		return defaultHandlers;
	    }
	    return super.getHandlers();
	}
	
    }
}

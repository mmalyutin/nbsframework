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

package org.plazmaforge.framework.core.data;

import org.plazmaforge.framework.core.logging.Logger;


public abstract class AbstractInitializer implements Initializer {

    private Logger logger;

    protected void log(String message) {
	getLogger().log(message);
    }

    protected void log(String message, Throwable e) {
	getLogger().error(message, e);
    }

    protected void log(Throwable e) {
	getLogger().error(e);
    }

    protected void logInfo(String message) {
	getLogger().info(message);
    }

    protected void logWarn(String message) {
	getLogger().warn(message);
    }
    
    protected void logError(String message) {
	getLogger().error(message);
    }

    protected void logError(String message, Throwable e) {
	getLogger().error(message, e);
    }

    protected void logError(Throwable e) {
	getLogger().error(e);
    }
    
    protected void handleError(Throwable e) {
	logError(e);
    }

    protected Logger getLogger() {
	if (logger == null) {
	    logger = Logger.getLogger(getClass().getName());
	}
	return logger;
    }
}

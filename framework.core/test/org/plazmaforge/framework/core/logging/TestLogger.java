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

import junit.framework.TestCase;

import org.junit.Test;
import org.plazmaforge.framework.core.logging.Logger;

/**
 * @author ohapon
 *
 */
public class TestLogger extends TestCase {

    public void setUp() {
	LogManager.getRootLogger().clearHandlers();
	//LogManager.getRootLogger().addHandler(new Log4JHandler());
	
    }
    
    @Test
    public void testLogger() throws Exception {
	Logger logger = Logger.getLogger(this.getClass());
	logger.info("Logger: " + logger.getName());
	
	
	logger.fatal("Test Fatal");
	logger.error("Test Error");
	logger.error("Test Error", new Throwable("he"));
	logger.warn("Test Warn");
	logger.info("Test Info");
	logger.debug("Test Debug");
	logger.trace("Test Trace");
    }

}

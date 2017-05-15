/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.core;

import org.plazmaforge.framework.util.SystemInfo;

/**
 * 
 * @author ohapon
 *
 */
public final class Platform {

    // CARRIAGE RETURN	- '\r'
    // LINE FEED	- '\n'
    
    // CRLF		- '\r\n'
    // CR		- '\r'    
    // LF		- '\n'
    
    public static final String CRLF_DELIMITER = "\r\n";
    public static final String CR_DELIMITER = "\r";
    public static final String LF_DELIMITER = "\n";

    public static final String WIN_LINE_DELIMITER = CRLF_DELIMITER;	// Windows systems	'\r\n'
    public static final String NIX_LINE_DELIMITER = LF_DELIMITER;	// *nix systems		'\n'
    public static final String MAC_LINE_DELIMITER = LF_DELIMITER;	// Mac systems		'\n'
    
    public static final String DEFAULT_LINE_DELIMITER = SystemInfo.isWindows ? WIN_LINE_DELIMITER : NIX_LINE_DELIMITER;
    public static final String DEFAULT_COLUMN_DELIMITER = ",";
    public static final String DEFAULT_ROW_DELIMITER = DEFAULT_LINE_DELIMITER; //"\\n";
    
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_BOOLEAN_FORMAT = "true|false";
    
    public static final String[] DEFAULT_BOOLEAN_FORMATS = new String[] {"true|false", "yes|no" , "y|n", "t|f", "1|0"};
    

    private Platform() {
    }

}

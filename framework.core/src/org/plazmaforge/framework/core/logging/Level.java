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

import java.io.Serializable;

/**
 * @author ohapon
 *
 */
public class Level implements Serializable {

    private static final long serialVersionUID = 4206184391637138191L;
    

    public final static int OFF_INT = Integer.MAX_VALUE;

    public final static int FATAL_INT = 50000;

    public final static int ERROR_INT = 40000;
    
    public final static int WARN_INT  = 30000;

    public final static int INFO_INT  = 20000;
    
    public final static int DEBUG_INT = 10000;

    public static final int TRACE_INT = 5000;
    
    public final static int ALL_INT = Integer.MIN_VALUE;
   
   
    public static Level OFF = new Level("OFF", OFF_INT);

    public static Level FATAL = new Level("FATAL", FATAL_INT);

    public static Level ERROR = new Level("ERROR", ERROR_INT);

    public static Level WARN  = new Level("WARN", WARN_INT);

    public static Level INFO  = new Level("INFO", INFO_INT);

    public static Level DEBUG = new Level("DEBUG", DEBUG_INT);
    
    public static Level TRACE = new Level("TRACE", TRACE_INT);
    
    public static Level ALL = new Level("ALL", ALL_INT);
    
    
    
    private String name;
    
    private int value;

    public Level() {
    }

    public Level(String name, int value) {
	this.name = name;
	this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    
    
}

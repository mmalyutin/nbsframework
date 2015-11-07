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

package plazma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScriptUtils {
    
    public static boolean isGlobalVariable(String identifier) {
	if (identifier != null && identifier.startsWith("$")) {
	    return true; 
	}
	return false;
    }

    public static Scope getSafeScope(Scope scope) {
	return scope == null ? new Scope() : scope;
    }
    
    public static <T> List<T> getSafeList(List<T> list) {
	return list == null ? new ArrayList<T>() : list; 
    }
    
    public static Map<String, Function> getSafeFunctions(Map<String, Function> functions) {
	return functions == null ? new HashMap<String, Function>() : functions; 
    }

}

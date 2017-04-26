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

package org.plazmaforge.framework.core.datastorage.data;

import java.util.Comparator;
import java.util.Map;

public class RecordComparator implements Comparator<Object[]> {

    
    // record values -> order values
    private  Map<Object[], Object[]> map;
    
    // asc flags
    private  boolean[] flags;
    
    private ValueComparator valueComparator = new ValueComparator(); 
    
    public RecordComparator(Map<Object[], Object[]> map, boolean[] flags) {
	super();
	this.map = map;
	this.flags = flags;
    }

    @Override
    public int compare(Object[] o1, Object[] o2) {
	return compareRecord(o1, o2);
    }
    
    protected int compareRecord(Object[] record1, Object[] record2) {
   	if (record1 == null && record2 == null) {
   	    return 0;
   	}
   	if (record1 == null) {
   	    return -1;
   	}
   	if (record2 == null) {
   	    return 1;
   	}
   	if (map == null) {
   	    return 0;
   	}
   	Object[] value1 = map.get(record1);
   	Object[] value2 = map.get(record2);

   	if (value1 == null && value2 == null) {
   	    return 0;
   	}
   	if (value1 == null) {
   	    return -1;
   	}
   	if (value2 == null) {
   	    return 1;
   	}
   	int size = value1.length;
   	Object v1 = null;
   	Object v2 = null;
   	int result = 0;
   	boolean asc = false;
   	for (int i = 0; i < size; i++) {
   	    v1 = value1[i];
   	    v2 = value2[i];
   	    result = valueComparator.compare(v1, v2);
   	    asc = i > flags.length - 1 ? true: flags[i];
   	    if (!asc) {
   		result = result * -1;
   	    }
   	    if (result != 0) {
   		return result;
   	    }
   	}
   	return result;
   }
    
    
}

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

package org.plazmaforge.framework.uwt.data.store;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Data Sorter
 * 
 * @author ohapon
 *
 */
//[CORE]
public class DataSorter<T> {

    /**
     * Comparator (optional)
     */
    private Comparator<T> comparator;

    /**
     * Default comparator
     * Use to compare string representation of object
     */
    private Comparator<T> defaultComparator;
    
    
    
    public Comparator<T> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }
    
    protected Comparator<T> getDefaultComparator() {
	if (defaultComparator == null) {
	    defaultComparator = new Comparator<T>() {
		public int compare(T e1, T e2) {
		    return e1.toString().compareTo(e2.toString());
		}
	    };
	}
	return defaultComparator;
    }
    
    public int category(T element) {
	return 0;
    }
    
    public int compare(T e1, T e2) {
	int cat1 = category(e1);
	int cat2 = category(e2);

	if (cat1 != cat2) {
	    return cat1 - cat2;
	}
	
	// TODO: Bad implementation
	
	if (e1 == null & e2 == null) {
	    return 0;
	}
	if (e1 == null) {
	    return -1;
	}
	if (e2 == null) {
	    return 1;
	}
	
	if (comparator != null) {
	    return comparator.compare(e1, e2);
	}
	
	return getDefaultComparator().compare(e1, e2);
    }
    

    public void sort(List<T> elements) {
	if (elements == null) {
	    return;
	}
	Collections.sort(elements, new Comparator<T>() {
	    public int compare(T a, T b) {
		return DataSorter.this.compare(a, b);
	    }
	});

    }
    
    
}

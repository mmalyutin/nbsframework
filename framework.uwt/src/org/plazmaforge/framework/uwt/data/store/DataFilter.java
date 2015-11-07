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

import java.util.ArrayList;
import java.util.List;

/**
 * Data Filter
 * 
 * @author ohapon
 *
 */
//[CORE]
public abstract class DataFilter<T> {

    /**
     * Filters the given elements for the given viewer.
     * The input array is not modified.
     * <p>
     * The default implementation of this method calls 
     * <code>select</code> on each element in the array, 
     * and returns only those elements for which <code>select</code>
     * returns <code>true</code>.
     * </p>
     * 
     * @param parent
     * @param elements
     * @return the filtered elements
     */
    public List<T> filter(T parent, List<T> elements) {
        int size = elements.size();
        ArrayList<T> out = new ArrayList<T>();
        for (int i = 0; i < size; ++i) {
            T element = elements.get(i);
            if (select(parent, element)) {
                out.add(element);
            }
        }
        return out;
    }

    /**
     * Returns whether the given element makes it through this filter.
     * @param parent
     * @param element
     * @return
     */
    public abstract boolean select(T parent, T element);
}

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

package org.plazmaforge.framework.core.proxy;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;


/**
 * 
 * @author ohapon
 *
 * @param <E>
 */
public class SetProxy<E> implements Set<E> {

    final Collection<E> set;

    public SetProxy(Collection<E> set) {
	this.set = set;
    }

    public boolean add(E o) {
	return set.add(o);
    }

    public boolean addAll(Collection<? extends E> c) {
	return set.addAll(c);
    }

    public void clear() {
	set.clear();
    }

    public boolean contains(Object o) {
	return set.contains(o);
    }

    public boolean containsAll(Collection<?> c) {
	return set.containsAll(c);
    }

    public boolean isEmpty() {
	return set.isEmpty();
    }

    public Iterator<E> iterator() {
	return new IteratorProxy<E>(set.iterator());
    }

    public boolean remove(Object o) {
	return set.remove(o);
    }

    public boolean removeAll(Collection<?> c) {
	return set.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
	return set.retainAll(c);
    }

    public int size() {
	return set.size();
    }

    public Object[] toArray() {
	return set.toArray();
    }

    public <T> T[] toArray(T[] array) {
	return set.toArray(array);
    }

}
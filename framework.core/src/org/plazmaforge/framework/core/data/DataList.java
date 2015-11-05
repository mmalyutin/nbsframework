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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.plazmaforge.framework.core.proxy.IteratorProxy;
import org.plazmaforge.framework.core.proxy.ListIteratorProxy;

/**
 * DataList is wrapper of the <code>java.util.List</code>
 * 
 * @author ohapon
 *
 * @param <E>
 */
public class DataList<E> implements Serializable, List<E> {

    private static final long serialVersionUID = -6111759660488041556L;
    
    

    private int total;
    
    private List<E> list;
    
    public DataList(List<E> list, int total) {
	if (list == null) {
	    throw new IllegalArgumentException("List must be not null");
	}
	this.list = list;
	this.total = total;
    }
    
    public DataList(List<E> list) {
	this(list, 0);
    }

    public List<E> getList() {
        return list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int total() {
        return getTotal();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static <T> DataList<T> createDataList(T element) {
	List<T> list = new ArrayList<T>();
	list.add(element);
	return new DataList<T>(list);
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void add(int index, E value) {
	list.add(index, value);
    }

    /**
     * @see java.util.Collection#add(Object)
     */
    public boolean add(E o) {
	return list.add(o);
    }

    /**
     * @see java.util.Collection#addAll(Collection)
     */
    public boolean addAll(Collection<? extends E> c) {
	return list.addAll(c);
    }

    /**
     * @see java.util.List#addAll(int, Collection)
     */
    public boolean addAll(int i, Collection<? extends E> c) {
	return list.addAll(i, c);
    }

    /**
     * @see java.util.Collection#clear()
     */
    public void clear() {
	list.clear();
    }

    /**
     * @see java.util.Collection#contains(Object)
     */
    public boolean contains(Object o) {
	return list.contains(o);
    }

    /**
     * @see java.util.Collection#containsAll(Collection)
     */
    public boolean containsAll(Collection<?> c) {
	return list.containsAll(c);
    }

    /**
     * @see java.util.List#get(int)
     */
    public E get(int i) {
	return list.get(i);
    }

    /**
     * @see java.util.List#indexOf(Object)
     */
    public int indexOf(Object o) {
	return list.indexOf(o);
    }

    /**
     * @see java.util.Collection#isEmpty()
     */
    public boolean isEmpty() {
	return list.isEmpty();
    }

    /**
     * @see java.util.Collection#iterator()
     */
    public Iterator<E> iterator() {
	return new IteratorProxy<E>(list.iterator());
    }

    /**
     * @see java.util.List#lastIndexOf(Object)
     */
    public int lastIndexOf(Object o) {
	return list.lastIndexOf(o);
    }

    /**
     * @see java.util.List#listIterator()
     */
    public ListIterator<E> listIterator() {
	return new ListIteratorProxy<E>(list.listIterator());
    }

    /**
     * @see java.util.List#listIterator(int)
     */
    public ListIterator<E> listIterator(int i) {
	return new ListIteratorProxy<E>(list.listIterator(i));
    }

    /**
     * @see java.util.List#remove(int)
     */
    public E remove(int i) {
	return list.remove(i);
    }

    /**
     * @see java.util.Collection#remove(Object)
     */
    public boolean remove(Object o) {
	return list.remove(o);
    }

    /**
     * @see java.util.Collection#removeAll(Collection)
     */
    public boolean removeAll(Collection<?> c) {
	return list.removeAll(c);
    }

    /**
     * @see java.util.Collection#retainAll(Collection)
     */
    public boolean retainAll(Collection<?> c) {
	return list.retainAll(c);
    }

    /**
     * @see java.util.List#set(int, Object)
     */
    public E set(int i, E o) {
	return list.set(i, o);
    }

    /**
     * @see java.util.Collection#size()
     */
    public int size() {
	return list.size();
    }

    /**
     * @see java.util.List#subList(int, int)
     */
    public List<E> subList(int i, int j) {
	return list.subList(i, j);
    }

    /**
     * @see java.util.Collection#toArray()
     */
    public Object[] toArray() {
	return list.toArray();
    }

    /**
     * @see java.util.Collection#toArray(Object[])
     */
    public <T> T[] toArray(T[] array) {
	return list.toArray(array);
    }


}

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

package org.plazmaforge.framework.core.criteria;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author ohapon
 *
 */
public class Criteria implements Serializable {

    
    
    private static final long serialVersionUID = 7227111144676724642L;
    
    

    public final static String CRITERIA = "CRITERIA";
    
    public final static int ORDER = 1;
    
    public final static int FILTER = 2;
    
   
    private List<Filter> filters;
    
    private List<Order> orders;

    /**
     * Row limit
     */
    private int limit;
    
    /**
     * Row offset. Start with 0.
     */
    private int offset;
	
    
    public Criteria() {
	super();
    }

    public List<Filter> getFilters() {
	if (filters == null) {
	    filters = new ArrayList<Filter>();
	}
        return filters;
    }

    
    /**
     * Add filter with operation (=,<>,like) to criteria (property = "name", value = "Tiger", operation = "<>")
     * @param property
     * @param value
     * @param operation
     */
    public void addFilter(String property, Serializable value, String operation) {
	getFilters().add(new ValueFilter(property, value, operation));
    }

    public void addFilter(String property, Serializable value, boolean ignoreCase) {
	getFilters().add(new ValueFilter(property, value, ignoreCase));
    }

    public void addFilter(Filter filter) {
	getFilters().add(filter);
    }


    /**
     * Add filter to criteria (property = "name", value = "Tiger")
     * @param property
     * @param value
     */
    public void addFilter(String property, Serializable value) {
	getFilters().add(new ValueFilter(property, value));
    }
    
    public void addIsNullFilter(String property) {
	getFilters().add(new NullFilter(property));
    }

    public void addIsNotNullFilter(String property) {
	getFilters().add(new NotNullFilter(property));
    }

    public void addInFilter(String property, Serializable[] values) {
	getFilters().add(new InFilter(property, values));
    }

   
    ////
    
//    public void addAndFilter(Filter filter) {
//	getFilters().add(new AndFilter(new Filter[] {filter}));
//    }

    public void addAndFilter(Filter filter1, Filter filter2) {
	getFilters().add(new AndFilter(new Filter[] {filter1, filter2}));
    }
    
    public void addAndFilter(Filter[] filters) {
	getFilters().add(new AndFilter(filters));
    }
    
    ////
    
//    public void addOrFilter(Filter filter) {
//	getFilters().add(new OrFilter(new Filter[] {filter}));
//    }

    public void addOrFilter(Filter filter1, Filter filter2) {
	getFilters().add(new OrFilter(new Filter[] {filter1, filter2}));
    }
    
    public void addOrFilter(Filter[] filters) {
	getFilters().add(new OrFilter(filters));
    }

    ////
    
    public List<Order> getOrders() {
	if (orders == null) {
	    orders = new ArrayList<Order>();
	}
        return orders;
    }

    public void addOrder(Order order) {
	getOrders().add(order);
    }
    
    public void addOrder(String property, boolean asc) {
	getOrders().add(new Order(property, asc));
    }
    
    public void addOrder(String property) {
	getOrders().add(new Order(property));
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
	if (limit < 0) {
	    throw new IllegalArgumentException("Limit must be >= 0");
	}
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
	if (limit < 0) {
	    throw new IllegalArgumentException("Offset must be >= 0");
	}
        this.offset = offset;
    }


    public boolean isPaging() {
	return limit > 0;
    }
    
    public static Criteria createFilterCriteria(String filter, Serializable value) {
	Criteria criteria = new Criteria();
	if (filter == null) {
	    return criteria;
	}
	criteria.addFilter(filter, value);
	return criteria;
    }
    
    public static Criteria createFilterCriteria(String[] filters, Serializable[] values) {
	Criteria criteria = new Criteria();
	if (filters == null && values == null) {
	    return criteria;
	}
	if (filters == null || values == null || filters.length != values.length) {
	    throw new IllegalArgumentException("Invalid filters and values");
	}
	for (int i = 0; i < filters.length; i++) {
	    criteria.addFilter(filters[i], values[i]);
	}
	return criteria;
    }

    /////
    
    /**
     * All reset criteria (Filters and Orders)
     */
    public void reset() {
	resetFilters();
	resetOrders();
    }
    
    /**
     * Reset Filters
     */
    public void resetFilters() {
	if (filters == null) {
	    return;
	}
	filters.clear();
	filters = null;
    }
    
    /**
     * Reset Orders
     */
    public void resetOrders() {
	if (orders == null) {
	    return;
	}
	orders.clear();
	orders = null;
    }
    
    /**
     * Return true if the Criteria has filters
     * @return
     */
    public boolean hasFilters() {
	return filters != null && !filters.isEmpty();
    }
    
    
    /**
     * Return true if the Criteria has orders
     * @return
     */
    public boolean hasOrders() {
	return orders != null && !orders.isEmpty();
    }



}

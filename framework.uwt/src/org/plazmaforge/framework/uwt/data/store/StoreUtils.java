package org.plazmaforge.framework.uwt.data.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.core.criteria.Filter;
import org.plazmaforge.framework.core.criteria.Order;
import org.plazmaforge.framework.core.criteria.ValueFilter;
import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.uwt.data.store.PropertySorter.PropertyInfo;

public class StoreUtils {

    
    
    /**
     * Create filters by Criteria
     * @param criteria
     * @return
     */
    public static <T> List<DataFilter<T>> createDataFilters(Criteria criteria) {
	return createDataFilters(criteria, null);
    }
    
    /**
     * Create filters by Criteria and configure filters by PropertyProvider
     * @param criteria
     * @param propertyProvider
     * @return
     */
    public static <T> List<DataFilter<T>> createDataFilters(Criteria criteria, PropertyProvider<T> propertyProvider) {
	if (criteria == null) {
	    return null;
	}
	List<Filter> conditions = criteria.getFilters();
	List<DataFilter<T>> list = new ArrayList<DataFilter<T>>();
	for (Filter filter : conditions) {
	    if (!(filter instanceof ValueFilter)) {
		continue;
	    }

	    ValueFilter valueFilter = (ValueFilter) filter;
	    PropertyFilter<T> propertyFilter = new PropertyFilter<T>(valueFilter.getPropertyName(), valueFilter.getValue());
	    propertyFilter.setOperation(valueFilter.getOperation());
	    propertyFilter.setIgnoreCase(valueFilter.isIgnoreCase());

	    // CONFIGURE FILTER
	    configure(propertyFilter, propertyProvider);

	    list.add(propertyFilter);
	}
	return list;
    }  
    
    public static <T>  void configure(PropertyFilter<T> propertyFilter, PropertyProvider<T> propertyProvider) {
	if (propertyFilter == null || propertyProvider == null || propertyFilter.getPropertyProvider() != null ) {
	    return;
	}
	propertyFilter.setPropertyProvider(propertyProvider);
    }    

    
    
    
    
    /**
     * Create sorter by Criteria
     * @param criteria
     * @return
     */
    public static <T> DataSorter<T> createDataSorter(Criteria criteria) {
	return createDataSorter(criteria, null);
    }
    
    /**
     * Create sorter by Criteria and configure sorter by PropertyProvider
     * @param criteria
     * @param propertyProvider
     * @return
     */
    public static <T> DataSorter<T> createDataSorter(Criteria criteria, PropertyProvider<T> propertyProvider) {
	if (criteria == null) {
	    return null;
	}
	List<Order> orders = criteria.getOrders();
	PropertySorter<T> sorter = new PropertySorter<T>();
	for (Order order : orders) {
	    sorter.addProperty(order.getPropertyName(), order.isAsc());
	}
	
	// CONFIGURE SORTER
	configure(sorter, propertyProvider);
	return sorter;
    }

    /**
     * Configure PropertySorter
     * Set PropertyProvider
     * @param propertySorter
     */
    public static <T> void configure(PropertySorter<T> propertySorter, PropertyProvider<T> propertyProvider) {
	if (propertySorter == null || propertyProvider == null || propertySorter.getPropertyProvider() != null ) {
	    return;
	}
	propertySorter.setPropertyProvider(propertyProvider);
    }
    
    
    
    
    
    public static <T> void populateCriteriaDataFilters(Criteria criteria, List<DataFilter<T>> filters) {
   	// Add new filters if need
   	if (filters == null || filters.isEmpty()) {
   	    return;
   	}
   	for (DataFilter<T> field : filters) {
   	    if (!(field instanceof PropertyFilter)) {
   		continue;
   	    }
   	    PropertyFilter<T> propertyFilter = (PropertyFilter<T>) field;
   	    criteria.addFilter(new ValueFilter(propertyFilter.getProperty(), (Serializable) propertyFilter.getValue(), propertyFilter.getOperation(), propertyFilter.isIgnoreCase()));
   	}
    }

    
    
    public static <T> void populateCriteriaDataSorter(Criteria criteria, DataSorter<T> orderFields) {
	// Add new orders (if need)
	if (orderFields == null || !(orderFields instanceof PropertySorter)) {
	    return;
	}
	PropertySorter<T> propertySorter = (PropertySorter<T>) orderFields;
	if (!propertySorter.hasProperties()) {
	    return;
	}
	List<PropertyInfo> properties = propertySorter.getProperties();
	for (PropertyInfo property : properties) {
	    criteria.addOrder(new Order(property.getName(), property.isAsc()));
	}	
    }

    
    
    ////
    
    public static <T> void  transferCriteriaToDataStore(Criteria criteria, DataStore<T> dataStore, PropertyProvider<T> propertyProvider) {
	if (criteria == null || dataStore == null) {
	    return;
	}
	
	// Reset Filters and Sorter
	dataStore.resetDataFilters();
	dataStore.resetDataSorter();
	
	
	List<DataFilter<T>> dataFilters = createDataFilters(criteria, propertyProvider);
	DataSorter<T> dataSorter = createDataSorter(criteria, propertyProvider);
	
	// Set filter
	if (dataFilters != null) {
	    dataStore.setDataFilters(dataFilters);
	}
	
	// Set sorter
	if (dataSorter != null) {
	    dataStore.setDataSorter(dataSorter);
	}
	
	dataStore.setOffset(criteria.getOffset());
	dataStore.setLimit(criteria.getLimit());


	
    }
}

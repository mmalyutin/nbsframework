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

package org.plazmaforge.framework.uwt.demo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.ClassPropertyProviderFactory;
import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.PropertyProviderFactory;
import org.plazmaforge.framework.core.data.provider.ArrayProvider;
import org.plazmaforge.framework.core.service.ServiceCaller;
import org.plazmaforge.framework.core.service.ServiceCallerException;
import org.plazmaforge.framework.uwt.data.store.BaseDataStore;
import org.plazmaforge.framework.uwt.data.store.DataStore;
import org.plazmaforge.framework.uwt.data.store.StoreUtils;
import org.plazmaforge.framework.uwt.demo.model.BaseModel;
import org.plazmaforge.framework.uwt.demo.model.Product;


/**
 * General Demo Service Caller
 *  
 * @author ohapon
 *
 */
public class DemoServiceCaller implements ServiceCaller {

    
    private Map<String, DataStore> dataStoreMap = new HashMap<String, DataStore>();
    
    private Map<String, PropertyProvider> propertyProviderMap = new HashMap<String, PropertyProvider>();

    private PropertyProviderFactory propertyProviderFactory = new ClassPropertyProviderFactory();
    
    
    public DemoServiceCaller() {
	super();
	
	dataStoreMap.put("ProductDataStore", BaseDataStore.createDefaultDataStore(createProductList()));
	propertyProviderMap.put("ProductPropertyProvider", propertyProviderFactory.getPropertyProvider(Product.class));
	
    }
    
    //////////////////////////////////////////////////////////////////////////////////////
    
    private DataStore getDataStore(String name) {
	return (DataStore) dataStoreMap.get(name);
    }

    private PropertyProvider getPropertyProvider(String name) {
	return (PropertyProvider) propertyProviderMap.get(name);
    }
    
    //////////////////////////////////////////////////////////////////////////////////////

    
    
    //////////////////////////////////////////////////////////////////////////////////////
    //
    // PRODUCT
    //
    //////////////////////////////////////////////////////////////////////////////////////

    private Product createProduct(int index) {
   	int i = index + 1;
   	int code = 1000 + i;
   	Product product = new Product("" + code, "S" + code, "Product " + i , 100.00f + i);
   	product.setStartDate(new Date()); // today
   	return product;
    }
    
    private List<Product> createProductList() {
	List<Product> dataList = new ArrayList<Product>();
	for (int i = 0; i < 100; i++) {
	    dataList.add(createProduct(i));
	}
	return dataList;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////
    
    private List findAll(DataStore dataStore) {
	List dataList = dataStore.getAllList();
	return dataList;
    }

    private List findAll(DataStore dataStore, PropertyProvider propertyProvider, Criteria criteria) {
	StoreUtils.transferCriteriaToDataStore(criteria, dataStore, propertyProvider);
	dataStore.load();
	List<Product> dataList = dataStore.getDataList();
	return dataList;
    }
    
    private Object findById(DataStore dataStore, PropertyProvider propertyProvider, String id) {
	List dataList = dataStore.getAllList();
	for (Object product : dataList) {
	    String dataId = (String) propertyProvider.getValue(product, "id");
	    if (id.equals(dataId)) {
		return product;
	    }
	}
	return null;
    }

    //////////////////////////////////////////////////////////////////////////////////////
    
    private int indexById(List list, Serializable id) {
	for (int i = 0; i < list.size(); i++) {
	    BaseModel model = (BaseModel) list.get(i);
	    if (id.equals(model.getId())) {
		return i;
	    }
	}
	return -1;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////
    
    public Object doCall(String serviceName, String methodName, Object[] parameters) {
	// By Index
	DataStore dataStore = getDataStore("ProductDataStore");
	PropertyProvider propertyProvider = getPropertyProvider("ProductPropertyProvider");
	ArrayProvider dataProvider = (ArrayProvider) dataStore.getDataProvider();
	
	Object result = null;
	
	if ("findAll".equals(methodName)) {
	    if (parameters == null || parameters.length == 0) {
		result = findAll(dataStore);
	    } else {
		result = findAll(dataStore, propertyProvider, (Criteria) parameters[0]);
	    }
	} else if ("findById".equals(methodName)) {
	    result = findById(dataStore, propertyProvider, (String) parameters[0]);
	} else if ("update".equals(methodName)) {
	    BaseModel data = (BaseModel) parameters[0];
	    String id = data.getId();
	    int index = indexById(dataProvider.getList(), id);
	    if (index >=0) {
		dataProvider.set(index, data);
	    }
	} else if ("create".equals(methodName)) {
	    Product data = (Product) parameters[0];
	    int id = dataProvider.getList().size() + 1;
	    data.setId("" + id);
	    dataProvider.add(data);
	} else if ("removeById".equals(methodName)) {
	    String id = (String) parameters[0];
	    if (id == null) {
		return null;
	    }
	    List list = dataProvider.getList();
	    for (int i = 0; i < list.size(); i++) {
		Product model = (Product) list.get(i);
		if (id.equals(model.getId())) {
		    list.remove(i);
		    return null;
		}
	    }
	}

	return result;
    }
    
    public Object doCall(String serviceName, String methodName, Map<String, Object> parameters) {
	throw new ServiceCallerException(ServiceCallerException.ERROR_PARAMETER_BY_NAME);
    }
    
    @Override
    public <T> void call(String serviceName, String methodName, Object[] parameters, final Callback<T> callback) {
	// By index
	Object result = doCall(serviceName, methodName, parameters);
	if (callback != null) {
	    callback.onSuccess((T) result);
	}
	
    }	
    
    @Override
    public <T> void call(String serviceName, String methodName, Map<String, Object> parameters, final Callback<T> callback) {
	// By name
	Object result = doCall(serviceName, methodName, parameters);
	if (callback != null) {
	    callback.onSuccess((T) result);
	}
    }	

}

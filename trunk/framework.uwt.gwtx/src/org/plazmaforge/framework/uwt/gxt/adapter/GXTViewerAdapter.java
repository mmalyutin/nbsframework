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

package org.plazmaforge.framework.uwt.gxt.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.plazmaforge.framework.core.data.ValueProvider;
import org.plazmaforge.framework.uwt.gxt.data.BaseModel;
import org.plazmaforge.framework.uwt.gxt.data.BeanModel;
import org.plazmaforge.framework.uwt.gxt.data.ModelData;
import org.plazmaforge.framework.uwt.widget.IViewer;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.TreeStore;




/**
 * Base abstract adapter of viewers (Table, Tree...) 
 * 
 * @author ohapon
 *
 */
public abstract class GXTViewerAdapter extends GXTCompositeAdapter {

    
    /**
     * Create <code>ModelData</code> by bean object
     *  
     * We use <code>ModelData</code> in GXT Table, Tree...
     * 
     * @param bean
     * @return
     */
    protected ModelData createModel(Object bean) {
	if (bean == null) {
	    return new BaseModel();
	}
	ModelData model = GXTHelper.createBeanModel(bean);
	if (model == null) {
	    
	    // TODO
	    Map<String, Object> properties = new HashMap<String, Object>();
	    String beanString = bean == null ? "" : bean.toString();
	    properties.put("text", beanString);
	    properties.put("toString", beanString);
	    
	    model = new BaseModel(properties);
	}
	return model;
    }


    /**
     * Find Model in store by bean
     * @param store
     * @param bean
     * @return
     */
    protected ModelData findModelByBean(ListStore<ModelData> store, Object bean) {
	if (bean == null) {
	    return null;
	}
	List<ModelData> list = store.getAll();
	String toString = null;
	for (ModelData m: list) {
	    if (m instanceof BeanModel) {
		BeanModel bm = (BeanModel) m;
		if (bean.equals(bm.getBean())) {
		    return m;
		}
	    } else if (m instanceof BaseModel) {
		
		
		//TODO
		BaseModel bm = (BaseModel) m;
		if (toString == null) {
		    toString = bean.toString();
		}
		if (toString.equals(bm.get("toString"))) {
		    return m;
		}
		
	    }
	}
	
	return null;
    }
    
    /**
     * Find Model in store by bean or create if not found
     * 
     * @param viewer
     * @param store
     * @param bean
     * @return
     */
    protected ModelData findModelByBean(IViewer viewer, ListStore<ModelData> store, Object bean) {
	if (bean == null) {
	    return null;
	}
	ModelData model = findModelByBean(store, bean);
	if (model != null) {
	    return model;
	}
	model = createModel(bean);
	return model;
    }
    
    /**
     * Find Model in store by index
     * @param store
     * @param index
     * @return
     */
    protected ModelData findModelByIndex(ListStore<ModelData> store, int index) {
   	return store.get(index);
    }
    
    /**
     * Get bean from model
     * @param model
     * @return
     */
    protected Object getBean(ModelData model) {
	return GXTHelper.getBean(model);
    }
    
    public void populateListStore2(IViewer<?> viewer, List<?> dataList, ListStore<ModelData> store) {
	if (dataList == null) {
	    return;
	}
	List<ModelData> models = new ArrayList<ModelData>();
	for (Object data : dataList) {

	    // Create wrap of data
	    ModelData model = createModel(data);
	    models.add(model);
	}
	store.addAll(models);
    }
    
    public void populateTreeStore2(IViewer viewer, List<?> dataList, TreeStore<ModelData> store) {
	if (dataList == null) {
	    return;
	}
   	
   	List<ModelData> models = new ArrayList<ModelData>();
   	for (Object data : dataList) {

	    // Create wrap of data
	    ModelData model = createModel(data);
	    models.add(model);
	}
   	store.add(models); // TODO: DISABLE:MIGRATION Maybe need flag addChildren=true	
    }

    protected Map<String, ValueProvider> createValueProviders(List<TableColumn> columns) {
	if  (columns == null || columns.isEmpty()) {
	    return null;
	}
	Map<String, ValueProvider> valueProviders = new HashMap<String, ValueProvider>();
	for (TableColumn column: columns) {
	    String property = column.getProperty();
	    ValueProvider valueProvider = column.getValueProvider(); 
	    if (property == null || valueProvider == null) {
		continue;
	    }
	    valueProviders.put(property, valueProvider);
	}
	return valueProviders.isEmpty() ? null : valueProviders;
    }
    
  
    
}

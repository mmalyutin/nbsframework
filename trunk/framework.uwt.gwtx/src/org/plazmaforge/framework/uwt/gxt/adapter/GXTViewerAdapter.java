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
import org.plazmaforge.framework.uwt.gxt.data.Model;
import org.plazmaforge.framework.uwt.widget.IViewer;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Widget;
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
    protected Model createModel(Object bean) {
	if (bean == null) {
	    return new BaseModel();
	}
	Model model = GXTHelper.createBeanModel(bean);
	if (model == null) {
	    
	    //TODO
	    String beanString = bean == null ? "" : bean.toString();
	    if (bean.getClass().isArray()) {
		BeanModel beanModel = new BeanModel();
		beanModel.setBean(bean);
		
		beanModel.set("text", beanString);
		beanModel.set("toString", beanString);

		    
		return beanModel;
	    }
	    
	    // TODO
	    Map<String, Object> properties = new HashMap<String, Object>();
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
    protected Model findModelByBean(ListStore<Model> store, Object bean) {
	if (bean == null) {
	    return null;
	}
	List<Model> list = store.getAll();
	String toString = null;
	for (Model m: list) {
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
    protected Model findModelByBean(IViewer viewer, ListStore<Model> store, Object bean) {
	if (bean == null) {
	    return null;
	}
	Model model = findModelByBean(store, bean);
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
    protected Model findModelByIndex(ListStore<Model> store, int index) {
   	return store.get(index);
    }
    
    /**
     * Get bean from model
     * @param model
     * @return
     */
    protected Object getBean(Model model) {
	return GXTHelper.getBean(model);
    }
    
    public void populateListStore2(IViewer<?> viewer, List<?> dataList, ListStore<Model> store) {
	if (dataList == null) {
	    return;
	}
	List<Model> models = new ArrayList<Model>();
	for (Object data : dataList) {

	    // Create wrap of data
	    Model model = createModel(data);
	    models.add(model);
	}
	store.addAll(models);
    }
    
    public void populateTreeStore2(IViewer viewer, List<?> dataList, TreeStore<Model> store) {
	if (dataList == null) {
	    return;
	}
   	
   	List<Model> models = new ArrayList<Model>();
   	for (Object data : dataList) {

	    // Create wrap of data
	    Model model = createModel(data);
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
    
  
    protected com.google.gwt.event.logical.shared.SelectionHandler<Model> createModelSelectionListener(Widget widget, final Listener listener) {
  	com.google.gwt.event.logical.shared.SelectionHandler<Model> xListener = new com.google.gwt.event.logical.shared.SelectionHandler<Model>() {

  	    @Override
  	    public void onSelection(com.google.gwt.event.logical.shared.SelectionEvent<Model> e) {
  		listener.handleEvent(createEvent(e));

  	    }
  	};
  	widget.assignListener(listener, xListener);
  	return xListener;
     }  
}

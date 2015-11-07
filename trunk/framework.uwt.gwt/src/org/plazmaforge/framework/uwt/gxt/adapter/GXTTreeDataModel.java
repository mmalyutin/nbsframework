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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.provider.TreeProvider;


import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.ChangeEvent;
import com.extjs.gxt.ui.client.data.ChangeEventSource;
import com.extjs.gxt.ui.client.data.ChangeEventSupport;
import com.extjs.gxt.ui.client.data.ChangeListener;
import com.extjs.gxt.ui.client.data.Model;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PropertyChangeEvent;
import com.extjs.gxt.ui.client.data.TreeModel;
import com.extjs.gxt.ui.client.store.TreeStore;
import com.extjs.gxt.ui.client.util.Util;

public class GXTTreeDataModel implements TreeModel {

    
    /**
     * The model's parent.
     */
    protected TreeModel parent;

    /**
     * The model's children.
     */
    protected List<ModelData> children;

    protected transient ChangeEventSupport changeEventSupport;
    
    
    ////
    
    private boolean load;
    
    private ModelData model;
    
    private TreeStore<ModelData> store;
    
    private TreeProvider provider;
    
    public GXTTreeDataModel(ModelData model, TreeStore<ModelData> store, TreeProvider provider) {
	this.model = model;
	this.store = store;
	this.provider = provider;
	changeEventSupport = new ChangeEventSupport();
	children = new ArrayList<ModelData>();
    }

    public ModelData getModel() {
        return model;
    }

    //////////////////////////////////////////////////////////////////////////////////////

    private boolean isLoad() {
        return load;
    }

    private void setLoad(boolean load) {
        this.load = load;
    }

    protected void checkLoad(GXTTreeDataModel treeModel) {
	if (treeModel.isLoad()) {
	    return;
	}
	ModelData model = treeModel.getModel();
	treeModel.setLoad(true);
	
	// Only for BeanModel
	if (!(model instanceof BeanModel)) {
	    return;
	}
	BeanModel beanModel = (BeanModel) model;
	Object element = beanModel.getBean();
	
	if (!provider.hasChildren(element)) {
	    return;
	}
	
	List<Object> children = provider.getChildren(element);
	for (Object e: children) {
	    ModelData m = createModel(e);
	    GXTTreeDataModel tm = new GXTTreeDataModel(m, store, provider);
	    store.insert(treeModel, tm, treeModel._getChildCount(), false);
	}
    }
    
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
    
    
    private int _getChildCount() {
	return children.size();
    }

    
    //////////////////////////////////////////////////////////////////////////////////////
    
    

    @Override
    public <X> X get(String property) {
	return model.get(property);
    }

    @Override
    public <X> X set(String property, X value) {
	return model.set(property, value);
    }

    @Override
    public Map<String, Object> getProperties() {
	return model.getProperties();
    }

    @Override
    public Collection<String> getPropertyNames() {
	return model.getPropertyNames();
    }

    @Override
    public <X> X remove(String property) {
	return model.remove(property);
    }

    ////
    
    ///////////////////////////////////////////////////////////////////////////////
    //
    // Tree Model methods
    //
    ///////////////////////////////////////////////////////////////////////////////
    
    public void addChangeListener(ChangeListener... listener) {
	changeEventSupport.addChangeListener(listener);
    }

    /**
     * Adds the listeners to receive change events.
     * 
     * @param listeners
     *            the listeners to add
     */
    public void addChangeListener(List<ChangeListener> listeners) {
	for (ChangeListener listener : listeners) {
	    changeEventSupport.addChangeListener(listener);
	}
    }

    /**
     * Returns true if change events are disabled.
     * 
     * @return true if silent
     */
    public boolean isSilent() {
	return changeEventSupport.isSilent();
    }

    public void _notify(ChangeEvent evt) {
	changeEventSupport.notify(evt);
    }
    
    @Override
    public void notify(ChangeEvent evt) {
	
      //super.notify(evt);
      _notify(evt);
      
      if (!isSilent() && parent != null && parent instanceof ChangeEventSource) {
        evt.setSource(parent);
        ((ChangeEventSource) parent).notify(evt);
      }
    }

    /**
     * Removes a previously added change listener.
     * 
     * @param listener
     *            the listener to be removed
     */
    public void removeChangeListener(ChangeListener... listener) {
	changeEventSupport.removeChangeListener(listener);
    }

    public void removeChangeListeners() {
	changeEventSupport.removeChangeListeners();
    }

    public void setSilent(boolean silent) {
	changeEventSupport.setSilent(silent);
    }

    protected void fireEvent(int type) {
	notify(new ChangeEvent(type, this));
    }

    protected void fireEvent(int type, Model item) {
	notify(new ChangeEvent(type, this, item));
    }

    protected void notifyPropertyChanged(String name, Object value, Object oldValue) {
	if (!Util.equalWithNull(value, oldValue)) {
	    notify(new PropertyChangeEvent(Update, this, name, oldValue, value));
	}
    }

    @Override
    public void add(ModelData child) {
	children.add(child);
	((TreeModel) child).setParent(this);
	
	int index = getChildCount();
	
	ChangeEvent evt = new ChangeEvent(Add, this);
	    evt.setParent(this);
	    evt.setItem(child);
	    evt.setIndex(index);
	    notify(evt);
    }

    @Override
    public ModelData getChild(int index) {
	if ((index < 0) || (index >= children.size())) return null;
	return children.get(index);

    }

    @Override
    public int getChildCount() {
	checkLoad(this);
	return children.size();
    }

    @Override
    public List<ModelData> getChildren() {
	checkLoad(this);
	return children;
    }

    @Override
    public TreeModel getParent() {
	return parent;
    }

    @Override
    public int indexOf(ModelData child) {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public void insert(ModelData child, int index) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public boolean isLeaf() {
	checkLoad(this);
	return children.size() == 0;
    }

    @Override
    public void remove(ModelData child) {
	children.remove(child);
	((TreeModel) child).setParent(null);
    }

    @Override
    public void removeAll() {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void setParent(TreeModel parent) {
	this.parent = parent;
	
    }


}

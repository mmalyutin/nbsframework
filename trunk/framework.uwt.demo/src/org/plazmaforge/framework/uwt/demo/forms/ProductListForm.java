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

package org.plazmaforge.framework.uwt.demo.forms;

import java.io.Serializable;
import java.util.List;

import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.provider.CriteriaProviderAsync;
import org.plazmaforge.framework.core.data.provider.DataProviderAsync;
import org.plazmaforge.framework.core.data.provider.ListProvider;
import org.plazmaforge.framework.core.data.provider.ModelProviderAsync;
import org.plazmaforge.framework.uwt.data.store.BaseDataStore;
import org.plazmaforge.framework.uwt.data.store.DataStore;
import org.plazmaforge.framework.uwt.data.store.StoreUtils;
import org.plazmaforge.framework.uwt.demo.model.Product;
import org.plazmaforge.framework.uwt.form.ListForm;
import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.DefaultCellEditor;
import org.plazmaforge.framework.uwt.widget.Style.HorizontalAlign;
import org.plazmaforge.framework.uwt.widget.Viewer;
import org.plazmaforge.framework.uwt.widget.Viewer.SelectionMode;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;

public class ProductListForm extends ListForm<Product> {
    
    private Table<Product> table;
    
    
    @Override
    protected void configure() {
	super.configure();
	
	setLazyLoad(true);
	setClientData(false); // Server mode
	
	setPropertyProvider(getPropertyProvider(Product.class));
	
	// VARIANT 1: DataProvider
	//setDataProvider(createProductDataProvider());
	
	// VARIANT 2: DataProviderAsync (Callback)
	//setDataProviderAsync(createProductDataProviderAsync());
	
	// VARIANT 3: DataProviderAsync (Callback) with ServiceCaller
	//setDataProviderAsync(createProductServiceDataProviderAsync());
	//setServiceCaller(createProductServiceCaller());

	// VARIANT 4: DataProviderAsync (Callback) with General ServiceCaller
	setDataProviderAsync(createProductServiceDataProviderAsync());
	//setServiceCaller(createGeneralServiceCaller());
	
	setResourceName("org.plazmaforge.framework.uwt.demo.messages");
	addRelationForm("EditForm", "DemoEditForm");
    }
    
    

    protected void buildContent(Composite content) {
	setTitle(getString("DemoListForm.title"));
	content.setLayout(new FitLayout());
	
	table = new Table<Product>();
	table.setSelectionMode(SelectionMode.CELL);
	TableColumn column = new TableColumn();
	column.setText(getString("DemoListForm.skuColumn.text"));
	column.setProperty("code");
	column.setWidth(200);
	column.setDataType("String");
	table.addColumn(column);
	
	column = new TableColumn();
	column.setText(getString("DemoListForm.nameColumn.text"));
	column.setProperty("name");
	column.setWidth(300);
	column.setDataType("String");
	column.setCellEditor(new DefaultCellEditor());
	table.addColumn(column);

	column = new TableColumn();
	column.setText(getString("DemoListForm.priceColumn.text"));
	column.setProperty("price");
	column.setWidth(100);
	column.setDataType("Float");
	column.setFormat("#.00");
	column.setSortable(false); // Reset sortable mode
	column.setAlign(HorizontalAlign.RIGHT);
	column.setCellEditor(new DefaultCellEditor());
	table.addColumn(column);
	
	
	column = new TableColumn();
	column.setText("Start Date" /*getString("DemoListForm.priceColumn.text")*/);
	column.setProperty("startDate");
	column.setWidth(100);
	column.setDataType("Date");
	//column.setFormat("yyyy-MM-dd");
	column.setSortable(false); // Reset sortable mode
	column.setCellEditor(new DefaultCellEditor());
	table.addColumn(column);
	
	content.add(table);
    }

    @Override
    public Viewer<Product> getViewer() {
	return table;
    }

    private DataProviderAsync<Product> createProductServiceDataProviderAsync() {
   	DataProviderAsync<Product> dataProviderAsync = new ProductServiceDataProviderAsync();
   	return dataProviderAsync;
    }

    class ProductDataProvider implements ListProvider<Product> {

	private List<Product> dataList;
	
	public ProductDataProvider(List<Product> dataList) {
	    this.dataList = dataList;
	}

	@Override
	public List<Product> getList() {
	    return dataList;
	}

    }
    
    class ProductDataProviderAsync implements CriteriaProviderAsync<Product> {

	private DataStore<Product> dataStore;
	
	public ProductDataProviderAsync(List<Product> dataList) {
	    dataStore =  BaseDataStore.createDefaultDataStore(dataList);
	}

	@Override
	public void getList(Callback<List<Product>> callback) {
	    List<Product> dataList = dataStore.getAllList();
	    callback.onSuccess(dataList);
	}
	
	@Override
	public void getList(Criteria criteria, Callback<List<Product>> callback) {
	    StoreUtils.transferCriteriaToDataStore(criteria, dataStore, getPropertyProvider());
	    dataStore.load();
	    List<Product> dataList = dataStore.getDataList();
	    callback.onSuccess(dataList);
	}
	
	
    }
    
    class ProductServiceDataProviderAsync implements ModelProviderAsync<Product> {

   	public ProductServiceDataProviderAsync() {
   	}

   	@Override
   	public void getList(Callback<List<Product>> callback) {
   	    getServiceCaller().call("ProductService", "findAll", (Object[]) null, callback);
   	}
   	
   	@Override
   	public void getList(Criteria criteria, Callback<List<Product>> callback) {
   	    getServiceCaller().call("ProductService", "findAll", new Object[] {criteria}, callback);
   	}

	@Override
	public void create(Product data, Callback<Serializable> callback) {
   	    getServiceCaller().call("ProductService", "create", new Object[] {data}, callback);
	}

	@Override
	public void remove(Product data, Callback<?> callback) {
	    // TODO
	}

	@Override
	public void removeById(Serializable id, Callback<?> callback) {
   	    getServiceCaller().call("ProductService", "removeById", new Object[] {id}, callback);
	}

	@Override
	public void update(Product data, Callback<?> callback) {
   	    getServiceCaller().call("ProductService", "update", new Object[] {data}, callback);
	}

	@Override
	public void getById(Serializable id, Callback<Product> callback) {
   	    getServiceCaller().call("ProductService", "findById", new Object[] {id}, callback);
	}

	@Override
	public void get(Criteria criteria, Callback<Product> callback) {
	    // TODO
	}

	@Override
	public void instance(Callback<Product> callback) {
	    callback.onSuccess(new Product());
	    
	}
   	
   	
       }


}

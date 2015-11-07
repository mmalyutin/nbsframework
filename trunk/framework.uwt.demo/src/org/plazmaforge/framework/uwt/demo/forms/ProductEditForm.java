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
import org.plazmaforge.framework.core.data.provider.ModelProviderAsync;
import org.plazmaforge.framework.uwt.demo.model.Product;
import org.plazmaforge.framework.uwt.form.EditForm;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.CurrencyField;
import org.plazmaforge.framework.uwt.widget.Label;
import org.plazmaforge.framework.uwt.widget.TextField;

public class ProductEditForm extends EditForm<Product> {

    private Label skuLabel;
    private Label nameLabel;
    private Label priceLabel;
    
    private TextField skuField;
    private TextField nameField;
    private CurrencyField priceField;
	    
    @Override
    protected void configure() {
	super.configure();
	setPack(false); // Reset auto pack form
	setResourceName("org.plazmaforge.framework.uwt.demo.messages");
	setDataProviderAsync(new ProductServiceDataProviderAsync());
    }
    
    
    @Override
    protected void buildContent(Composite content) {
	setTitle("Edit Product");
	GridLayout layout = new GridLayout(2);
	content.setLayout(layout);
	
	skuLabel = new Label(getString("DemoEditForm.skuLabel.text"));
	content.add(skuLabel);
	skuField = new TextField();
	content.add(skuField);
	
	nameLabel = new Label(getString("DemoEditForm.nameLabel.text"));
	content.add(nameLabel);
	nameField = new TextField();
	content.add(nameField);
	
	priceLabel = new Label(getString("DemoEditForm.priceLabel.text"));
	content.add(priceLabel);
	priceField = new CurrencyField();
	content.add(priceField);

    }
    
    @Override
    public void updateForm() {
	Product product = getModel();
	skuField.setValue(product.getCode());
	nameField.setValue(product.getName());
	priceField.setValue(product.getPrice());
    }

    @Override
    public void updateData() {
	Product product = getModel();
	product.setCode(skuField.getValue());
	product.setName(nameField.getValue());
	product.setPrice(priceField.floatValue());
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

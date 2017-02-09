/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.core.data.converter;

import org.plazmaforge.framework.core.data.ClassPropertyProviderFactory2;
import org.plazmaforge.framework.core.data.PropertyProvider;

import junit.framework.TestCase;

public class ClassPropertyProvider2Test extends TestCase {
    

    public void testConverter() {
	
	ClassPropertyProviderFactory2 propertyProviderFactory = new ClassPropertyProviderFactory2();
	PropertyProvider propertyProvider = propertyProviderFactory.getPropertyProvider(Product.class);
	
	String name = null;
	Object nameObj = null;
		
	Float price = null;
	Object priceObj = null; 
		
	////
	
	Product product = new Product();
	product.setName(name);
	
	name = "Name1";
	name = (String) propertyProvider.getValue(product, "name");
	System.out.println("name=" + name);
	
	name = "Name2";
	propertyProvider.setValue(product, "name", name);
	System.out.println("name=" + name);

	nameObj = 123;
	propertyProvider.setValue(product, "name", nameObj);
	nameObj = propertyProvider.getValue(product, "name");
	System.out.println("name=" + nameObj);
	
	price = 567f;
	propertyProvider.setValue(product, "price", price);
	priceObj = propertyProvider.getValue(product, "price");
	System.out.println("price=" + priceObj);
	
	
	price = 789f;
	propertyProvider.setValue(product, "primitivePrice", price);
	price = (Float) propertyProvider.getValue(product, "primitivePrice");
	System.out.println("primitivePrice=" + price);
    }
    
    
    
    
    public static class Product {
	
	private String name;
	
	private Float price;
	
	private float primitivePrice;

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public Float getPrice() {
	    return price;
	}

	public void setPrice(Float price) {
	    this.price = price;
	}

	public float getPrimitivePrice() {
	    return primitivePrice;
	}

	public void setPrimitivePrice(float primitivePrice) {
	    this.primitivePrice = primitivePrice;
	}
	
	
	
	
	
    }
}

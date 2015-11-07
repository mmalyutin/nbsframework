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

/**
 * 
 */
package org.plazmaforge.framework.report.model.base;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.datastorage.DSExpression;

/**
 * @author ohapon
 *
 */
public class Container extends Element {

    private static final long serialVersionUID = 2159963005052721945L;
    
    private List<Element> children;

    public List<Element> getChildren() {
	if (children == null){
	    children = new ArrayList<Element>();
	}
        return children;
    }

    public void setChildren(List<Element> children) {
        this.children = children;
    }
    
    public void addChild(Element child) {
	getChildren().add(child);
    }

    public void removeChild(Element child) {
	getChildren().remove(child);
    }
    
    public boolean hasChildren() {
	return children != null && !children.isEmpty();
    }

    public int getChildrenCount() {
	return children == null ? 0: children.size();
    }

    @Override
    public void populateExpressions(List<DSExpression> expressions) {
	super.populateExpressions(expressions);
	if (!hasChildren()) {
	    return;
	}
	for (Element child: children) {
	    populateExpressions(expressions, child);
	}
    }
    
   
    
}

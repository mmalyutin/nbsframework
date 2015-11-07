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

package org.plazmaforge.framework.uwt.view;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;

/**
 * Container view
 * 
 * @author ohapon
 *
 */
public class ContainerView<T> extends ContentView<T> implements IContainerView<T> {

    
    private List<IView> views;
    
    private IView  activeView;

    
    public IView getActiveView() {
	if (activeView != null) {
	    return activeView;
	}
	return getView();
    }

    @Override
    public IView getView() {
	if (views == null || views.isEmpty()) {
	    return null;
	}
	return views.get(0);
    }

    @Override
    public IView[] getViews() {
	if (views == null) {
	    return new IView[0];
	}
	return doGetViews().toArray(new IView[0]);
    }

    protected List<IView> doGetViews() {
	if (views == null) {
	    views = new ArrayList<IView>();
	}
	return views;
    }
    
    /**
     * Add view
     * @param view
     */
    public void addView(IView view) {
	 doGetViews().add(view);
    }
    
    /**
     * Remove view
     * @param view
     */
    public void removeView(IView view) {
	doGetViews().remove(view);
    }
    
    protected void addViews(Composite parent, List<IView> views) {
	if (parent == null || views == null) {
	    return;
	}
	for (IView view: views) {
	    addView(parent, view);
	}
    }
    
    protected void addView(Composite parent, IView view) {
	
    }
    
    protected void removeView(Composite parent, IView view) {
	
    }

    protected void addControlToContent(Composite content, Control control) {
	if (control == null) {
	    return;
	}
	super.addControlToContent(content, control);
	
	List<IView> views = doGetViews();
	if (views == null | views.isEmpty()) {
	    return;
	}
	addViews((Composite) control, views);
    }
}

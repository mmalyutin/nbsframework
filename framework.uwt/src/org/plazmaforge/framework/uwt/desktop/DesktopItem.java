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

package org.plazmaforge.framework.uwt.desktop;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.Notifier;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.IDecorator;
import org.plazmaforge.framework.uwt.widget.Layout;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.Window;

/**
 * DesktopItem container
 * 
 * @author ohapon
 *
 */
public class DesktopItem extends Composite implements IDecorator, Notifier {

    /**
     * Title of item
     */
    private String title;
    
    /**
     * Icon of item
     */
    private Image icon;
    
    /**
     * Content of the desktop item
     */
    private Control control;
    
    /**
     * Control that uses the desktop item
     */
    private Notifier notifier;

    /**
     * Protected constructor
     */
    protected DesktopItem() {
	super();
    }

    public void setTitle(String title) {
	this.title = title;
	fireChangeProperty(PROPERTY_TITLE, title);
    }
    
    public String getTitle() {
	return title;
    }

    public Image getIcon() {
        return icon;
    }

    public void setIcon(Image icon) {
        this.icon = icon;
        fireChangeProperty(PROPERTY_ICON, icon);
    }
    
    public void setIcon(String path) {
	doGetIcon().setPath(path);
	fireChangeProperty(PROPERTY_ICON_PATH, path);
    }

    protected Image doGetIcon() {
	if (icon == null) {
	    icon = new Image();
	}
	return icon;
    }
    
    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
	if (this.control != null) {
	    throw new IllegalArgumentException("Control must be single");
	}
        this.control = control;
        doAdd(control);
        List<Widget> children = new ArrayList<Widget>();
        children.add(control);
        fireChangeProperty(PROPERTY_CHILDREN, children);
    }
    
    public void setParent(Widget parent) {
	if (!(parent instanceof Desktop)) {
	    throw new IllegalArgumentException("Parent must be Desktop");
	}
	super.setParent(parent);
    }

    ////
    
    public void setLayout(Layout layout) {
	//ignore
    }
    
    public void add(Widget element) {
	//ignore
    }

    public void add(Widget element, Object layoutData) {
	//ignore
    }

    public void remove(Widget element) {
	//ignore
    }

    public void removeAll() {
	//ignore
    }

    ////
    
    protected void doAdd(Widget element) {
	super.add(element);
	
    }

    protected void doAdd(Widget element, Object layoutData) {
	super.add(element, layoutData);
    }

    protected void doRemove(Widget element) {
	super.remove(element);
    }

    protected void doRemoveAll() {
	super.removeAll();
    }

    @Override
    public void open() {
	invokeDelegate(this, Window.METHOD_OPEN, null);
    }

    @Override
    public void close() {
	invokeDelegate(this, Window.METHOD_CLOSE, null);
    }

    @Override
    public void pack() {
	invokeDelegate(this, Window.METHOD_PACK, null);
    }

    public Notifier getNotifier() {
        return notifier;
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    /**
     * Send request 'canNotify' to client
     */
    public void canNotify(String message, Callback<Boolean> callback) {
	if (getNotifier() == null) {
	    if (callback != null) {
		callback.onSuccess(true);
	    }
	    return;
	}
	getNotifier().canNotify(message, callback);
    }

    /**
     * Notify message to client
     */
    public void notify(String message) {
	if (getNotifier() == null) {
	    return;
	}
	getNotifier().notify(message);
    }

    
}

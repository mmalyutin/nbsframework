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

package org.plazmaforge.framework.uwt.form;

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.uwt.Application;
import org.plazmaforge.framework.uwt.UWTException;
import org.plazmaforge.framework.uwt.desktop.Desktop;
import org.plazmaforge.framework.uwt.desktop.DesktopItem;
import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Frame;
import org.plazmaforge.framework.uwt.widget.IDecorator;

/**
 * Implementation of <code>FormProvider</code> for <code>Desktop</code>
 * 
 * @author ohapon
 *
 */
public class DesktopFormProvider implements FormProvider {

    /**
     * Application
     */
    private Application application;
    
    
    public DesktopFormProvider(Application application) {
	this.application = application;
    }

    
    
    
    @Override
    public void open(IForm<?> form) {
	Callback callback = null; //TODO
	if (form instanceof IListForm) {
	    openListForm(form);
	} else if (form instanceof IEditForm) {
	    openEditForm(form);
	}
	fireCallback(callback);
    }

    @Override
    public void close(IForm<?> form) {
	// TODO
	IDecorator decorator = form.getDecorator();
	if (decorator == null) {
	    return;
	}
	reassign(decorator, form);
	decorator.close();
    }

    
    protected void openListForm(IForm<?> form) {
	
	if (form.isModal()) {
	    // If the form has modal mode then open the form in own frame
	    openModalForm(form);
	    return;
	}
	// By default open the form in desktop
	openDesktopForm(form);
    }

    protected void openEditForm(IForm<?> form) {
	// My default open modal form
	openModalForm(form);
    }

    protected void openDesktopForm(IForm<?> form) {
	Desktop desktop = getDesktop();
	if (desktop == null) {
	    throw new UWTException("Application Desktop is null");
	}
	IDecorator decorator = form.getDecorator();
	DesktopItem desktopItem = null;
	boolean isNew = false;
	if (decorator == null) {
	    // New form
	    isNew = true;
	    desktopItem = desktop.addItem(); // Add new desktop item
	    desktopItem.setControl(getFormContainer(form));
	    decorator = desktopItem; 
	    assign(decorator, form); 
	} else {
	    if (decorator instanceof DesktopItem) {
		desktopItem = (DesktopItem) decorator;
	    } else {
		//TODO: May be open form with new DesktopItem
		return;
	    }
	}
	if (isNew || isRefreshDecorator()) {
	    decorate(decorator, form);
	}
	
	if  (desktopItem != null) {
	    desktop.setActiveItem(desktopItem);
	}
    }
    
    protected void openModalForm(IForm<?> form) {
	// Get current decorator
	IDecorator decorator = form.getDecorator();
	if (decorator != null) {
	    // Reassign
	    reassign(decorator, form);

	    // TODO: May be close decorator
	    //decorator.close();
	}
	decorator = null;
	
	Frame frame = new Frame();
	frame.setModal(true);
	frame.setWidth(IForm.DEFAULT_FORM_WIDTH);
	frame.setHeight(IForm.DEFAULT_FORM_HEIGHT);
	if (form.getWidth() > 0) {
	    frame.setWidth(form.getWidth() < IForm.MIN_FORM_WIDTH ? IForm.MIN_FORM_WIDTH : form.getWidth());
	}
	if (form.getHeight() > 0) {
	    frame.setHeight(form.getHeight() < IForm.MIN_FORM_HEIGHT ? IForm.MIN_FORM_HEIGHT : form.getHeight());
	}
	frame.setLayout(new FitLayout());
	frame.add(getFormContainer(form));
	frame.setPack(form.isPack()); // Pack mode
	frame.setCenter(form.isCenter()); // Center mode
	
	decorator = frame;
	
	// Assign
	assign(decorator, form);
	
	// Decorate
	decorate(decorator, form);
	
	decorator.open();
    }

    /**
     * Fire callback
     * @param callback
     */
    protected void fireCallback(Callback callback) {
	if (callback == null) {
	    return;
	}
	callback.onSuccess("ok");
    }
    
    ////
    
    /**
     * Return true if need refresh decorator after activate form
     * @return
     */
    protected boolean isRefreshDecorator() {
	return false;
    }
    
    /**
     * Decorate <code>IDecorator</code>
     * @param decorator
     * @param form
     */
    protected void decorate(IDecorator decorator, IForm form) {
	decorator.setTitle(form.getTitle());
    }

    /**
     * Assign decorator and form
     * @param decorator
     * @param form
     */
    protected void assign(IDecorator decorator, IForm form) {
	decorator.setNotifier(form);
	form.setDecorator(decorator);
    }

    /**
     * Reassign (reset) decorator and form 
     * @param decorator
     * @param form
     */
    protected void reassign(IDecorator decorator, IForm form) {
	decorator.setNotifier(null);
	form.setDecorator(null);
    }

    /**
     * Return general container of form
     * @param form
     * @return
     */
    protected Composite getFormContainer(IForm form) {
	return ((Composite) form);
    }
    
    /**
     * Return <code>Desktop</code> of <code>Application</code>
     * @return
     */
    protected Desktop getDesktop() {
	return (Desktop) application.getData(PROPERTY_DESKTOP);
    }

}

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

package org.plazmaforge.framework.uwt.demo.tabs;

import org.plazmaforge.framework.core.data.CallbackAdapter;
import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.Application;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.demo.DemoFormCreator;
import org.plazmaforge.framework.uwt.desktop.Desktop;
import org.plazmaforge.framework.uwt.desktop.DesktopItem;
import org.plazmaforge.framework.uwt.form.DesktopFormProvider;
import org.plazmaforge.framework.uwt.form.FormManager;
import org.plazmaforge.framework.uwt.form.FormProvider;
import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.storage.TemplateProviderAsync;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Control;
import org.plazmaforge.framework.uwt.widget.Label;

public class DesktopTab extends AbstractTab {

    public DesktopTab() {
    }

    @Override
    protected void createUI() {
	createContent(this);
    }
    
    
    private void createContent(Composite parent) {
	setLayout(new FitLayout());
	
	// Create Desktop
	final Desktop desktop = new Desktop();
	add(desktop);

	// Get current application and assign desktop
	Application application = Application.getCurrent();
	application.setData(FormProvider.PROPERTY_DESKTOP, desktop);
	
	
	final TemplateProviderAsync templateProviderAsync = (TemplateProviderAsync) application.getConfigAttribute(Application.CONFIG_TEMPLATE_PROVIDER_ASYNC);
	final UIBuilder uiBuilder = new UIBuilder();
	
	// Register Form Creator
	FormManager.setFormCreator(new DemoFormCreator());
	
	// Register Form Provider
	FormManager.setFormProvider(new DesktopFormProvider(application));

	
	//DesktopItem desktopItem = desktop.addItem();
	//desktopItem.setTitle("Frame 1");
	
	 
	DesktopItem desktopItem = null;
	templateProviderAsync.getData("/org/plazmaforge/framework/uwt/demo/ui/GridLayoutComposite.ui.xml", new CallbackAdapter<IData>() {
	    
	    @Override
	    public void onFailure(Throwable error) {
		System.err.println("Load UI error: " + error);
	    }

	    
	    @Override
	    public void onSuccess(IData result) {
		Control control1 = null;
		IData data1 = result;
		if (data1 != null) {
		    control1 = (Control) uiBuilder.buildObject(data1);
		}
		if (control1 == null ){
		    control1 = new Label("Content 1");
		}
		
		DesktopItem desktopItem1 = desktop.addItem();
		desktopItem1.setTitle("Frame 1");
		
		desktopItem1.setControl(control1);

	    }
	});
	
	
		
	
	desktopItem = desktop.addItem();
	desktopItem.setTitle("Frame 2");
	desktopItem.setControl(new Label("Content 2"));

	desktopItem = desktop.addItem();
	desktopItem.setTitle("Frame 3");
	desktopItem.setControl(new Label("Content 3"));

	
	// Create and open Demo forms
	FormManager.open("DemoListForm");
	
	//DemoListForm listForm = new DemoListForm();
	//listForm.open();

	//DemoEditForm editForm = new DemoEditForm();
	//editForm.open();

    }
    
    

}

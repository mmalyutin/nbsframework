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
package org.plazmaforge.framework.uwt.demo;

import java.util.HashMap;
import java.util.Map;

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.CallbackAdapter;
import org.plazmaforge.framework.core.data.DataWriter;
import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.core.logging.Logger;
import org.plazmaforge.framework.uwt.Application;
import org.plazmaforge.framework.uwt.ApplicationView;
import org.plazmaforge.framework.uwt.UIElement;
import org.plazmaforge.framework.uwt.builder.UIBuilder;
import org.plazmaforge.framework.uwt.demo.tabs.AdvancedTab;
import org.plazmaforge.framework.uwt.demo.tabs.ButtonTab;
import org.plazmaforge.framework.uwt.demo.tabs.CanvasTab;
import org.plazmaforge.framework.uwt.demo.tabs.ChartTab;
import org.plazmaforge.framework.uwt.demo.tabs.DesktopTab;
import org.plazmaforge.framework.uwt.demo.tabs.FieldTab;
import org.plazmaforge.framework.uwt.demo.tabs.LabelTab;
import org.plazmaforge.framework.uwt.demo.tabs.LayoutTab;
import org.plazmaforge.framework.uwt.demo.tabs.ListTab;
import org.plazmaforge.framework.uwt.demo.tabs.MessageTab;
import org.plazmaforge.framework.uwt.demo.tabs.PanelTab;
import org.plazmaforge.framework.uwt.demo.tabs.TableTab;
import org.plazmaforge.framework.uwt.demo.tabs.TreeTab;
import org.plazmaforge.framework.uwt.demo.tabs.WindowTab;
import org.plazmaforge.framework.uwt.dialog.Dialog;
import org.plazmaforge.framework.uwt.event.SelectionAdapter;
import org.plazmaforge.framework.uwt.event.SelectionEvent;
import org.plazmaforge.framework.uwt.form.DesktopFormProvider;
import org.plazmaforge.framework.uwt.form.FormManager;
import org.plazmaforge.framework.uwt.form.IForm;
import org.plazmaforge.framework.uwt.layout.FitLayout;
import org.plazmaforge.framework.uwt.layout.GridLayout;
import org.plazmaforge.framework.uwt.storage.TemplateProviderAsync;
import org.plazmaforge.framework.uwt.widget.Container;
import org.plazmaforge.framework.uwt.widget.Frame;
import org.plazmaforge.framework.uwt.widget.Label;
import org.plazmaforge.framework.uwt.widget.MessageBox;
import org.plazmaforge.framework.uwt.widget.Window;
import org.plazmaforge.framework.uwt.widget.menu.Menu;
import org.plazmaforge.framework.uwt.widget.menu.MenuBar;
import org.plazmaforge.framework.uwt.widget.menu.MenuItem;
import org.plazmaforge.framework.uwt.widget.menu.MenuSeparator;
import org.plazmaforge.framework.uwt.widget.panel.Panel;
import org.plazmaforge.framework.uwt.widget.panel.TabItem;
import org.plazmaforge.framework.uwt.widget.panel.TabPanel;
import org.plazmaforge.framework.uwt.widget.tool.ToolBar;
import org.plazmaforge.framework.uwt.widget.tool.ToolItem;
import org.plazmaforge.framework.uwt.widget.tool.ToolSeparator;

/**
 * Demo Application Builder
 * 
 * @author ohapon
 *
 */
public class DemoApplicationBuilder {

    /**
     * Store for building from
     */
    private Map<String, IForm<?>> formStore = new HashMap<String, IForm<?>>(); 

    private static final Logger logger = Logger.getLogger(DemoApplicationBuilder.class.getName());
    
    private TabPanel tabPanel;
    
    
    
    public void populateFrame(Frame frame) {
	// Create application view
	ApplicationView appView = new ApplicationView() {
	    
	    // TODO: For GXT only
	    // Remove this method (createContent()) after implementation setLayout in GXTComposteAdapter
	    @Override
	    protected Container createContent() {
		Container content = new Panel();
		FitLayout layout = new FitLayout();
		layout.resetMargin();
		content.setLayout(layout);
	        return content;
	    }
	};
	
	appView.create();
	frame.add(appView);
	populate(appView);
    }

    public void populate(ApplicationView appView) {
	populateMenuBar(appView);
	populateToolBar(appView);
	populateContent(appView.getContent());
	appView.setStatusText("DEMO status");
    }

    public void populateContent(Container parent) {
	

	Application application = Application.getCurrent();
	
	// Register Form Creator
	FormManager.setFormCreator(new DemoFormCreator());
	
	// Register Form Provider
	FormManager.setFormProvider(new DesktopFormProvider(application));
	
	
	
	//parent.setBackground(Color.DARK_GRAY);
	//populateTreeContent(parent);
	populateTabContent(parent);
    }
    
    public void populateMenuBar(ApplicationView appView) {
	
	MenuBar menuBar = new MenuBar();

	///////////////////////////////////////////////////////////////////////
	// File Menu
	///////////////////////////////////////////////////////////////////////
	Menu fileMenu = createFileMenu();
	menuBar.addItem(fileMenu);
	
	///////////////////////////////////////////////////////////////////////
	// Help Menu
	///////////////////////////////////////////////////////////////////////
	Menu helpMenu = createHelpMenu();
	
	menuBar.addItem(helpMenu);
	
	appView.setMenuBar(menuBar);
    }
    
    private Menu createFileMenu() {
	
	Menu fileMenu = new Menu("File");

	// FileMenu: New Form
	final MenuItem newEditFormItem = new MenuItem("New EditForm");
	newEditFormItem.setIcon("widget/leaf.gif");
	newEditFormItem.addSelectionListener(new SelectionAdapter() {
	    public void select(SelectionEvent event) {
		doOpenEditForm(newEditFormItem.getApplication());
	    }
	});
	fileMenu.addItem(newEditFormItem);

	// FileMenu: Open Dialog
	final MenuItem openDialogItem = new MenuItem("Open Dialog");
	openDialogItem.setIcon("widget/folder.gif");
	openDialogItem.addSelectionListener(new SelectionAdapter() {
	    public void select(SelectionEvent event) {
		doOpenSimpleDialog(openDialogItem.getApplication());
	    }
	});
	fileMenu.addItem(openDialogItem);
	
	// FileMenu: Separator
	fileMenu.addItem(new MenuSeparator());

	// FileMenu: Load UI
	Menu loadUIMenu = new Menu("Load UI");

	
	final MenuItem loadWindowItem = new MenuItem("Window.ui.xml");
	loadWindowItem.addSelectionListener(new SelectionAdapter() {
	    public void select(SelectionEvent event) {
		doOpenWindow(loadWindowItem.getApplication());
	    }
	});
	
	final MenuItem loadFrameItem = new MenuItem("Frame.ui.xml");
	loadFrameItem.addSelectionListener(new SelectionAdapter() {
	    public void select(SelectionEvent event) {
		doOpenFrame(loadFrameItem.getApplication());
	    }
	});
	
	final MenuItem loadEditFormItem = new MenuItem("EditForm.ui.xml");
	loadEditFormItem.addSelectionListener(new SelectionAdapter() {
	    public void select(SelectionEvent event) {
		doOpenEditForm(loadEditFormItem.getApplication());
	    }
	});
	
	
	loadUIMenu.addItem(loadWindowItem);
	loadUIMenu.addItem(loadFrameItem);
	loadUIMenu.addItem(loadEditFormItem);

	fileMenu.addItem(loadUIMenu);
	
	return fileMenu;

    }
    
    private Menu createHelpMenu() {
	Menu helpMenu = new Menu("Help");
	
	MenuItem openAboutDialogItem = new MenuItem("About");
	openAboutDialogItem.addSelectionListener(new SelectionAdapter() {
	    public void select(SelectionEvent event) {
		doAboutDialog();
	    }
	});
	
	helpMenu.addItem(openAboutDialogItem);
	return helpMenu;
    }
    
    public void populateToolBar(ApplicationView appView) {

	///////////////////////////////////////////////////////////////////////
	// ToolBar 1
	///////////////////////////////////////////////////////////////////////
	ToolBar toolBar1 = new ToolBar();
	

	// ToolBar1: New EditForm
	final ToolItem newEditFormItem = new ToolItem();
	newEditFormItem.setToolTip("ToolItem1");
	newEditFormItem.setIcon("widget/leaf.gif");
	newEditFormItem.setToolTip("New EditForm");
	toolBar1.addItem(newEditFormItem);
	newEditFormItem.addSelectionListener(new SelectionAdapter() {
	    public void select(SelectionEvent event) {
		doOpenEditForm(newEditFormItem.getApplication());
		
	    }
	});
	
	// ToolBar1: Open Dialog
	final ToolItem openDialogItem = new ToolItem();
	openDialogItem.setIcon("widget/folder.gif");
	openDialogItem.setToolTip("Open Dialog");
	toolBar1.addItem(openDialogItem);
	openDialogItem.addSelectionListener(new SelectionAdapter() {
	    public void select(SelectionEvent event) {
		doOpenSimpleDialog(openDialogItem.getApplication());
		
	    }
	});
	
	toolBar1.addItem(new ToolSeparator());

	// ToolBar1: Open TableForm
	final ToolItem openTableFormItem = new ToolItem();
	openTableFormItem.setIcon("widget/folder.gif");
	openTableFormItem.setToolTip("Open TableForm");
	toolBar1.addItem(openTableFormItem);
	openTableFormItem.addSelectionListener(new SelectionAdapter() {
	    public void select(SelectionEvent event) {
		doOpenTableForm(openDialogItem.getApplication());
	    }
	});
	
	// ToolBar1: Open TreeForm
	final ToolItem openTreeFormItem = new ToolItem();
	openTreeFormItem.setIcon("widget/folder.gif");
	openTreeFormItem.setToolTip("Open TreeForm");
	toolBar1.addItem(openTreeFormItem);
	openTreeFormItem.addSelectionListener(new SelectionAdapter() {
	    public void select(SelectionEvent event) {
		doOpenTreeForm(openTreeFormItem.getApplication());
	    }
	});


	appView.addToolBar(toolBar1);
	
	
	///////////////////////////////////////////////////////////////////////
	// ToolBar 2
	///////////////////////////////////////////////////////////////////////
	ToolBar toolBar2 = new ToolBar();
	
	// ToolBar2: Open About Dialog
	ToolItem openAboutDialogItem = new ToolItem();
	openAboutDialogItem.setIcon("widget/refresh.gif");
	toolBar2.addItem(openAboutDialogItem);
	openAboutDialogItem.addSelectionListener(new SelectionAdapter() {
	    public void select(SelectionEvent event) {
		doAboutDialog();
	    }
	});

	
	appView.addToolBar(toolBar2);
    }
    
    
    public void populateTabContent(Container parent) {
	
	tabPanel = new TabPanel();
	
	parent.setLayout(new FitLayout());
	parent.add(tabPanel);
	
	addTabItem(tabPanel, "Fields", new FieldTab());
	addTabItem(tabPanel, "Advanced", new AdvancedTab());
	addTabItem(tabPanel, "Labels", new LabelTab());
	addTabItem(tabPanel, "Buttons", new ButtonTab());
	addTabItem(tabPanel, "List", new ListTab());
	addTabItem(tabPanel, "Table", new TableTab());
	addTabItem(tabPanel, "Tree", new TreeTab());
	addTabItem(tabPanel, "Messages", new MessageTab());
	addTabItem(tabPanel, "Layouts", new LayoutTab());
	addTabItem(tabPanel, "Panels", new PanelTab());
	addTabItem(tabPanel, "Windows", new WindowTab());
	addTabItem(tabPanel, "Desktop", new DesktopTab());
	addTabItem(tabPanel, "Canvas", new CanvasTab());
	addTabItem(tabPanel, "Charts", new ChartTab());
	
   }
   
    private void addTabItem(TabPanel tabPanel, String title, Panel panel) {
	TabItem tabItem = new TabItem(title);
	tabItem.setId(title); // ID: Title
	tabItem.setIcon("widget/leaf.gif");
	tabItem.setLayout(new FitLayout());
	tabItem.add(panel);
	tabPanel.add(tabItem);
	
    }

    private void doAboutDialog() {
	MessageBox.information("Information", "Demo UWT Application");
    }
    
    private void doOpenTableForm(Application application) {
	tabPanel.setActiveItemById("Desktop");
	doOpenForm(application, "/org/plazmaforge/framework/uwt/demo/ui/TableForm.ui.xml", true);
    }

    private void doOpenTreeForm(Application application) {
	tabPanel.setActiveItemById("Desktop");
	doOpenForm(application, "/org/plazmaforge/framework/uwt/demo/ui/TreeForm.ui.xml", true);
    }

    private void doOpenEditForm(Application application) {
	doOpenForm(application, "/org/plazmaforge/framework/uwt/demo/ui/EditForm.ui.xml");
    }
   
    private void doOpenForm(Application application, String path) {
	doOpenForm(application, path, false);
    }
    
    private void doOpenSimpleDialog(Application application) {
	doOpenDialog(application, "/org/plazmaforge/framework/uwt/demo/ui/Dialog.ui.xml");
    }
    
    private void doOpenWindow(Application application) {
	doOpenWindow(application, "/org/plazmaforge/framework/uwt/demo/ui/Window.ui.xml");
    }

    private void doOpenFrame(Application application) {
	doOpenFrame(application, "/org/plazmaforge/framework/uwt/demo/ui/Frame.ui.xml");
    }
	
    
    private void doOpenForm(final Application application, final String path, final boolean store) {
	IForm<?> form = null;
	if  (store) {
	    form = formStore.get(path);
	    if (form != null && !form.isClosed()) {
		doOpenForm(form);
		return;
	    }
	}
	CallbackAdapter<UIElement> callback = new CallbackAdapter<UIElement>() {
	    @Override
	    public void onSuccess(UIElement result) {
		IForm<?> form = (IForm<?>) result;
		if (store) {
		    formStore.put(path, form);
		}
		doOpenForm(form);
	    }
	};
	doBuildObject(application, path, callback);
    }
    
    private void doOpenForm(IForm<?> form) {
	if (form == null) {
	    return;
	}
	try {
	    form.open();
	} catch (Throwable e) {
	    MessageBox.error(e);
	}
    }

    private void doOpenDialog(Application application, String path) {
	CallbackAdapter<UIElement> callback = new CallbackAdapter<UIElement>() {
	    @Override
	    public void onSuccess(UIElement result) {
		Dialog dialog = (Dialog) result;
		dialog.open();
	    }
	};
	doBuildObject(application, path, callback);
    }
    
    private void doOpenWindow(Application application, String path) {
	CallbackAdapter<UIElement> callback = new CallbackAdapter<UIElement>() {
	    @Override
	    public void onSuccess(UIElement result) {
		Window window = (Window) result;
		window.open();
	    }
	};
	doBuildObject(application, path, callback);
    }

    private void doOpenFrame(Application application, String path) {
	CallbackAdapter<UIElement> callback = new CallbackAdapter<UIElement>() {
	    @Override
	    public void onSuccess(UIElement result) {
		Frame frame = (Frame) result;
		frame.open();
	    }
	};
	doBuildObject(application, path, callback);
    }


    private void doBuildObject(Application application, final String path, final Callback<UIElement> callback) {
	final TemplateProviderAsync templateProviderAsync = (TemplateProviderAsync) application.getConfigAttribute(Application.CONFIG_TEMPLATE_PROVIDER_ASYNC);
	final UIBuilder uiBuilder = new UIBuilder();
	
	templateProviderAsync.getData(path, new CallbackAdapter<IData>() {
	    
	    @Override
	    public void onFailure(Throwable error) {
		MessageBox.error("Load UIObject error: " + error);
		if (callback != null) {
		    callback.onFailure(error);
		}
	    }

	    
	    @Override
	    public void onSuccess(IData result) {
		dump(result);
		UIElement object = null;
		if (result != null) {
		    object = (UIElement) uiBuilder.buildObject(result);
		}
		if (object == null ){
		    MessageBox.error("Load UIOBject error: Object is empty");
		    return;
		}
		
		if (callback != null) {
		    callback.onSuccess(object);
		}
	    }
	});

    }
    
    private void dump(IData data) {
	logger.info("DUMP-DATA");

	DataWriter writer = new DataWriter();
	writer.addExcludeProperty("$parent");
	StringBuffer buf = new StringBuffer();
	try {
	    writer.write(data, buf);
	} catch (Exception e) {
	    //e.printStackTrace(e);
	}
	String toString = "\n" + buf.toString();
	logger.info(toString);
    }
    
  
}

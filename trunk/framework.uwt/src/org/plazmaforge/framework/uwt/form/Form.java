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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.CallbackAdapter;
import org.plazmaforge.framework.core.data.DataResult;
import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.PropertyProviderFactory;
import org.plazmaforge.framework.core.data.provider.DataProvider;
import org.plazmaforge.framework.core.data.provider.DataProviderAsync;
import org.plazmaforge.framework.core.data.provider.ModelProviderAsync;
import org.plazmaforge.framework.core.service.ServiceCaller;
import org.plazmaforge.framework.core.service.ServiceCallerFactory;
import org.plazmaforge.framework.uwt.ApplicationContext;
import org.plazmaforge.framework.uwt.UWT;
import org.plazmaforge.framework.uwt.UWT.FormMode;
import org.plazmaforge.framework.uwt.action.Action;
import org.plazmaforge.framework.uwt.view.ContainerView;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Event;
import org.plazmaforge.framework.uwt.widget.IDecorator;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.Window;
import org.plazmaforge.framework.uwt.widget.menu.MenuBar;
import org.plazmaforge.framework.uwt.widget.tool.ButtonBar;
import org.plazmaforge.framework.uwt.widget.tool.ToolBar;

/**
 * Base form
 * 
 * @author ohapon
 *
 */
public abstract class Form<T> extends ContainerView<T> implements IForm<T> {


    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // DEFAULT VALUES
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final boolean DEFAULT_LAZY_LOAD = true; // By default LazyLoad mode
    
    public static final boolean DEFAULT_LOCAL_DATA = false;
    
    public static final boolean DEFAULT_CLIENT_DATA = true; // By default sorting and filtering on client side
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // CONFIG PROPERTIES
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    public static final String CONFIG_FORM_SERVICE_CALLER = "form.service.caller";
    public static final String CONFIG_FORM_RESOURCE_PROVIDER = "form.resource.provider";
    
    public static final String CONFIG_FORM_PRE_CUSTOMIZER = "form.pre.customizer";
    public static final String CONFIG_FORM_POST_CUSTOMIZER = "form.post.customizer";
    public static final String CONFIG_FORM_CUSTOMIZER = "form.customizer";

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // ICONS
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public static final String REFRESH_ICON_PATH = "form/refresh.gif";
    public static final String PRINT_ICON_PATH = "form/print.gif";
    public static final String SORT_ICON_PATH = "form/sort.gif";
    public static final String FIND_ICON_PATH = "form/find.png";

    public static final String FILTER_ICON_PATH = "form/filter.png";   
    public static final String FILTER_ADVANCED_ICON_PATH = "form/filter_advanced.png"; 
    public static final String FILTER_ADD_ICON_PATH = "form/filter_add.png";
    public static final String FILTER_REMOVE_ICON_PATH = "form/filter_remove.png";
    public static final String FILTER_CLEAR_ICON_PATH = "form/filter_clear.png";
    public static final String FILTER_PERIOD_ICON_PATH = "form/filter_period.png";
    public static final String EXPORT_ICON_PATH = "form/export.gif";
    public static final String SETTINGS_ICON_PATH = "form/setup.png";
    public static final String HELP_ICON_PATH = "form/help.gif";

    public static final String ADD_ICON_PATH = "form/item_add.png";
    public static final String COPY_ICON_PATH = "form/item_copy.png";
    public static final String DEL_ICON_PATH = "form/item_delete.png";
    public static final String EDIT_ICON_PATH = "form/item_edit.png";
    public static final String VIEW_ICON_PATH = "form/item_view.png";
    
    public static final String ITEM_ADD_ICON_PATH = ADD_ICON_PATH;
    public static final String ITEM_COPY_ICON_PATH = COPY_ICON_PATH;
    public static final String ITEM_DEL_ICON_PATH = DEL_ICON_PATH;
    public static final String ITEM_EDIT_ICON_PATH = EDIT_ICON_PATH;
    
    public static final String FIRST_PAGE_ICON_PATH = "form/page_first.gif";
    public static final String PREV_PAGE_ICON_PATH = "form/page_prev.gif";
    public static final String NEXT_PAGE_ICON_PATH = "form/page_next.gif";
    public static final String LAST_PAGE_ICON_PATH = "form/page_last.gif";
    
    
    
    /**
     * Decorator of the form (Window, Dialog, Frame, DesktopItem)
     */
    private IDecorator decorator;
    
    
    /**
     * Model of the form
     */
    private T model;
    
    
    /**
     * ID of the model of the form
     */
    private Serializable modelId;
    
    
    /**
     * Model name of the form
     */
    private String modelName;
    
    
    /**
     * PropertyProvider of the model of the form
     */
    private PropertyProvider<T> propertyProvider;
    
    
    /**
     * DataProvider of the form
     */
    private DataProvider<T> dataProvider;
    
    
    /**
     * DataProviderAsync of the form
     */
    private DataProviderAsync<T> dataProviderAsync;
    
    /**
     * Service of the form
     */
    private Object service;
    
    
    /**
     * Name of the service of the from
     */
    private String serviceName;
    
    
    /**
     * Service caller
     */
    private ServiceCaller serviceCaller;
    
    
    /**
     * Form mode (EDIT, ADD, COPY, VIEW) 
     */
    private FormMode mode;
    
    /**
     * True if the form has modal mode
     */
    private boolean modal;
    
    
    /**
     * True if need pack decorator after open the form
     */
    private boolean pack;
    
    
    /**
     * True if need center decorator after open the form
     */
    private boolean center;
    
    /**
     * True if open form without load data (lazy mode) 
     */
    private Boolean lazyLoad;
    
    
    /**
     * True if data manage without provider (only set/getModel) 
     */
    private Boolean localData;
    
    /**
     * True if sorting, filtering, paging processes uses on client side (without provider or service)
     */
    private Boolean clientData;

    
    /**
     * Load state flag
     */
    private boolean load;
    
    
    /**
     * Modify state flag
     */
    private boolean modify;
    
    
    /**
     * Initialize state flag
     */
    private boolean init;
    
    
    /**
     * Map of relation forms
     */
    private Map<String, String> relationFormMap = new HashMap<String, String>();
    
    /**
     * Listeners by type
     */
    private Map<String, List<Listener>> formListeners = new LinkedHashMap<String, List<Listener>>();
    
    /**
     * Closed flag
     */
    private boolean closed;
    
    public Form() {
	super();
	closed = true;
	//lazyLoad = true; // By default LazyLoad mode
	//clientData = true; // By default sorting and filtering on client side
    }

    public IDecorator getDecorator() {
        return decorator;
    }

    public void setDecorator(IDecorator decorator) {
        this.decorator = decorator;
    }

    public final void open() {
	//activate(); //TODO
	FormManager.open(this); //TODO Use Callback
	closed = false;
    }

    public final void close() {
	FormManager.close(this); //TODO Use Callback
	closed = true;
	deactivateUI();
    }

    public boolean isClosed() {
	return closed;
    }
    
    @Override
    protected void dispose() {
	super.dispose();
	if (formListeners != null) {
	    formListeners.clear();
	    formListeners = null;
	}
    }

   

    @Override
    public void setModel(T model) {
	this.model = model;	
    }
    

    @Override
    public T getModel() {
	return this.model;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    @Override
    public Serializable getModelId() {
        return modelId;
    }

    @Override
    public void setModelId(Serializable modelId) {
        this.modelId = modelId;
    }
    

    @Override
    public PropertyProvider<T> getPropertyProvider() {
        return propertyProvider;
    }

    @Override
    public void setPropertyProvider(PropertyProvider<T> propertyProvider) {
        this.propertyProvider = propertyProvider;
    }

    /**
     * Get PropertyProvider by type (class name)
     * @param type
     * @return
     */
    //@Override
    public PropertyProvider<?> getPropertyProvider(String type) {
	PropertyProviderFactory factory = getPropertyProviderFactory();
        return factory == null ? null : factory.getPropertyProvider(type);
    }
    
    /**
     * Get PropertyProvider by class
     * @param type
     * @return
     */
    //@Override
    public PropertyProvider<T> getPropertyProvider(Class<T> klass) {
	PropertyProviderFactory factory = getPropertyProviderFactory();
        return factory == null ? null : factory.getPropertyProvider(klass);
    }

    /**
     * Return PropertyProviderFactory from ApplicationContext
     * @return
     */
    public PropertyProviderFactory getPropertyProviderFactory() {
	return (PropertyProviderFactory) getConfigAttribute(ApplicationContext.CONFIG_PROPERTY_PROVIDER_FACTORY);
    }
    
    protected void checkDataProvider(DataProvider<T> dataProvider) {
	// do nothing
    }
    
    @Override
    public DataProvider<T> getDataProvider() {
        return dataProvider;
    }

    @Override
    public void setDataProvider(DataProvider<T> dataProvider) {
	checkDataProvider(dataProvider);
        this.dataProvider = dataProvider;
    }

    @Override
    public DataProviderAsync<T> getDataProviderAsync() {
        return dataProviderAsync;
    }

    @Override
    public void setDataProviderAsync(DataProviderAsync<T> dataProviderAsync) {
        this.dataProviderAsync = dataProviderAsync;
    }

    @Override
    public void setService(Object service) {
	this.service = service;
    }

    @Override
    public Object getService() {
	return this.service;
    }

    @Override
    public String getServiceName() {
        return serviceName;
    }

    @Override
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public ServiceCaller getServiceCaller() {
	if (serviceCaller == null) {
	    serviceCaller = createServiceCaller();
	}
	return serviceCaller;
    }

    protected ServiceCaller createServiceCaller() {
	
	// Lookup ServiceCaller by priority properties in context
	ServiceCaller serviceCaller = (ServiceCaller) getConfigAttributeByPriority(getServiceCallerProperties());

	// Lookup ServiceCallerFactory by priority properties in context
	if (serviceCaller == null) {
	    ServiceCallerFactory serviceCallerFactory = (ServiceCallerFactory) getConfigAttributeByPriority(getServiceCallerFactoryProperties());
	    if (serviceCallerFactory != null){
		serviceCaller = serviceCallerFactory.getServiceCaller();
	    }
	}
	return serviceCaller;
    }

    @Override
    public void setServiceCaller(ServiceCaller serviceCaller) {
        this.serviceCaller = serviceCaller;
    }
    
    protected String[] getServiceCallerProperties() {
	return new String[] {CONFIG_FORM_SERVICE_CALLER, CONFIG_SERVICE_CALLER}; 
    }
    
    protected String[] getServiceCallerFactoryProperties() {
	return new String[] {CONFIG_SERVICE_CALLER_FACTORY}; 
    }


    public ServiceCaller getOwnServiceCaller() {
	return serviceCaller;
    }
	

    @Override
    public FormMode getMode() {
        return mode;
    }

    @Override
    public void setMode(FormMode mode) {
        this.mode = mode;
    }

    public boolean isModify() {
        return modify;
    }

    public void setModify(boolean modify) {
        this.modify = modify;
    }

    @Override
    public boolean isEditMode() {
	return FormMode.EDIT == mode;
    }

    @Override
    public boolean isAddMode() {
	return FormMode.ADD == mode;
    }

    @Override
    public boolean isCopyMode() {
	return FormMode.COPY == mode;
    }

    @Override
    public boolean isViewMode() {
	return FormMode.VIEW == mode;
    }

    
    /////
    
    public final void init() {
	
	if (init) {
	    return;
	}
	init = true;
	
	initActions();
	
	/////////////////////////
	create();
	////////////////////////
	
	
    }
    
    @Override
    protected void createUI() {
	
	super.createUI();
	
	buildMenuBar();
	buildToolBar();
	buildHeader();
	buildContent();
	buildFooter();
	buildButtonBar();
	buildStatusBar();
    }
    
    @Override
    protected void configure() {
	super.configure();
	
	// General Customizer
	if (getCustomizer() != null) {
	    getCustomizer().customize(this);
	}
    }
    
    @Override
    protected void preConfigure() {
	super.preConfigure();
	
	// Pre Customizer
	if (getPreCustomizer() != null) {
	    getPreCustomizer().customize(this);
	}
    }
    
    
    @Override
    protected void postConfigure() {
	super.postConfigure();
	
	// Post Customizer
	if (getPostCustomizer() != null) {
	    getPostCustomizer().customize(this);
	}
    }
    
    
    protected void initActions() {
	// initialize action here
    }
    
    protected void buildMenuBar() {
	MenuBar menuBar = getMenuBar();
	if (menuBar == null) {
	    return;
	}
	buildMenuBar(menuBar);
    }

    protected void buildMenuBar(MenuBar menuBar) {
	// build MenuBar here
    }

    protected void buildToolBar() {
	ToolBar toolBar = getToolBar();
	if (toolBar == null) {
	    return;
	}
	buildToolBar(toolBar);
    }

    protected void buildToolBar(ToolBar toolBar) {
	// build ToolBar here
    }

    protected void buildHeader() {
	Composite header = getHeader();
	if (header == null) {
	    return;
	}
	buildHeader(header);
    }

    protected void buildHeader(Composite header) {
	// build ToolBar here
    }


    protected void buildContent() {
	Composite content = getContent();
	if (content == null) {
	    return;
	}
	buildContent(content);
    }
    
    protected void buildContent(Composite content) {
	// build Content here
    }

    protected void buildFooter() {
	Composite footer = getFooter();
	if (footer == null) {
	    return;
	}
	buildFooter(footer);
    }

    protected void buildFooter(Composite footer) {
	// build Footer here
    }

    protected void buildButtonBar() {
	ButtonBar buttonBar = getButtonBar();
	if (buttonBar == null) {
	    return;
	}
	buildButtonBar(buttonBar);
    }

    protected void buildButtonBar(ButtonBar buttonBar) {
	// build ButtonBar here
    }

    protected void buildStatusBar() {
	
    }

    


    /**
     * Action switcher
     * 
     * @param action
     */
    protected void doAction(String action) {
	try {
	    if (action == null) {
		return;
	    }
	    if (HELP_ACTION.equals(action)) {
		doHelpAction();
		return;
	    } else if (REFRESH_ACTION.equals(action)) {
		doRefreshAction();
		return;
	    } else if (CLOSE_ACTION.equals(action)) {
		doCloseAction();
		return;
	    } else if (SETTINGS_ACTION.equals(action)) {
		doSetupAction();
		return;
	    } else if (PRINT_ACTION.equals(action)) {
		doPrintAction();
		return;
	    }
	} catch (Exception e) {
	    handleError(e);
	}
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // ACTIONS IMPLEMENTATION
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void doHelpAction() {
	//do nothing
    }

    protected void doCloseAction() {
	close();
    }
    
    protected void doSetupAction() {
	//do nothing
    }
    
    protected void doPrintAction() {
	//do nothing
    }

    protected void doRefreshAction() {
	//do nothing
    }

    ////
    
    public final void load() {
	if (load) {
	    return;
	}
	if (isLazyLoad()) {
	    return;
	}
	openData(new CallbackAdapter<DataResult>() {
	    
	    @Override
	    public void onSuccess(DataResult result) {
		if (DataResult.isSuccess(result) ) {
		    load = true;
		}
	    }
	});
    }


    public boolean isNew() {
	return getModelId() == null;
    }
    
    
    ////
    
    
    public void cancelData() {
	doCancelData();
    }

    public void findData() {
	doFindData();
    }

    public void filterData() {
	doFilterData();
    }    
    
    // OPEN: NEW or LOAD
    public void openData(Callback<DataResult> resultCallback) {
	if (isNew()) {
	    newData(resultCallback);
	    return;
	}
	loadData(resultCallback);
    }
    
    public void newData(Callback<DataResult> resultCallback) {
	doNewData(resultCallback);
    }

    public void loadData(Callback<DataResult> resultCallback) {
	doLoadData(resultCallback);
    }
    
    

    // SAVE: CREATE or STORE
    public void saveData(Callback<DataResult> resultCallback) {
	if (isNew()) {
	    createData(resultCallback);
	    return;
	}
	storeData(resultCallback);
    }

    
    public void createData(Callback<DataResult> resultCallback) {
	doCreateData(resultCallback);
    }
    
    public void storeData(Callback<DataResult> resultCallback) {
	doStoreData(resultCallback);
    }


    public void removeData(Callback<DataResult> resultCallback) {
	doRemoveData(resultCallback);
    }

   
    /////

    protected void doNewData(Callback<DataResult> resultCallback) {
	
    }

    protected void doLoadData(Callback<DataResult> resultCallback) {
	
    }
    
    protected void doCreateData(Callback<DataResult> resultCallback) {
	
    }
    
    protected void doStoreData(Callback<DataResult> resultCallback) {
	
    }


    protected void doRemoveData(Callback<DataResult> resultCallback) {
	
    }

    protected void doCancelData() {
	
    }

    
    protected void doFindData() {
	
    }

    
    protected void doFilterData() {
	
    }

    ////
    
    protected void fireEvent(String type) {
	
	// Create new event
	Event event = new Event();
	event.setType(type);
	
	fireEvent(event);

    }
    
    protected void fireEvent(Event event) {
	if (formListeners == null || formListeners.isEmpty()) {
	    return;
	}
	
	// Get list of listeners by event type
	List<Listener> list = formListeners.get(event.getType());
	if (list == null || list.isEmpty()) {
	    return;
	}
	
	for (Listener l: list) {
	    l.handleEvent(event);
	}
    }
    
    
    
    
    // EVENT LOAD DATA
    protected void fireLoadEvent() {
	Event event = new Event();
	event.setType(FormEvents.LoadData);
	
	fireLoadEvent(event);
    }

    protected void fireLoadEvent(Event event) {

	// Update form fields
	updateForm();

	// Update state (Menu/ToolBar/StatusBar)
	updateState();
	
	fireEvent(FormEvents.LoadData);
	fireEvent(FormEvents.AfterLoadData);
    }
    
    // EVENT CREATE DATA
    protected void fireCreateEvent() {
	Event event = new Event();
	event.setType(FormEvents.CreateData);
	
	fireCreateEvent(event);
    }

    protected void fireCreateEvent(Event event) {

	// Update state (Menu/ToolBar/StatusBar)
	updateState();
	
	fireEvent(FormEvents.CreateData);
	fireEvent(FormEvents.AfterCreateData);
	fireEvent(FormEvents.SaveData);
	fireEvent(FormEvents.AfterSaveData);
	fireEvent(FormEvents.ModifyData);
	fireEvent(FormEvents.AfterModifyData);

    }

    // EVENT STORE DATA
    protected void fireStoreEvent() {
	Event event = new Event();
	event.setType(FormEvents.StoreData);
	
	fireStoreEvent(event);
    }

    protected void fireStoreEvent(Event event) {

	// Update state (Menu/ToolBar/StatusBar)
	updateState();
	
	//TODO
	// Fire new event because input event can be with other type 
	fireEvent(FormEvents.StoreData);
	fireEvent(FormEvents.AfterStoreData);
	fireEvent(FormEvents.SaveData);
	fireEvent(FormEvents.AfterSaveData);
	fireEvent(FormEvents.ModifyData);
	fireEvent(FormEvents.AfterModifyData);

    }

    
    // EVENT REMOVE DATA
    protected void fireRemoveEvent() {
	Event event = new Event();
	event.setType(FormEvents.RemoveData);
	
	fireRemoveEvent(event);
    }

    protected void fireRemoveEvent(Event event) {

	// Update state (Menu/ToolBar/StatusBar)
	updateState();
	
	fireEvent(FormEvents.RemoveData);
	fireEvent(FormEvents.AfterRemoveData);
	fireEvent(FormEvents.ModifyData);
	fireEvent(FormEvents.AfterModifyData);

    }

    protected void fireResultCallback(Callback<DataResult> resultCallback, Object data, boolean success) {
	if (resultCallback == null) {
	    return;
	}
	resultCallback.onSuccess(new DataResult(data, success));
    }

    protected void fireResultCallback(Callback<DataResult> resultCallback, Object data) {
	fireResultCallback(resultCallback, data, true);
    }
    
    public void updateForm() {
	// update form by data here
    }
       
    public void updateData() {
	// update data by form here
    }
    
    
    public boolean isLocalData() {
        return localData == null ? DEFAULT_LOCAL_DATA : localData;
    }

    public Boolean getLocalData() {
        return localData;
    }

    public void setLocalData(Boolean localData) {
        this.localData = localData;
    }

    public boolean isClientData() {
	
	// TODO
	// LocalData overrides ClientData
	if (localData != null && localData) {
	    return true;
	}
	
        return clientData == null ? DEFAULT_CLIENT_DATA : clientData;
    }

    public Boolean getClientData() {
        return clientData;
    }

    public void setClientData(Boolean clientData) {
        this.clientData = clientData;
    }

    public boolean isModal() {
        return modal;
    }

    public void setModal(boolean modal) {
        this.modal = modal;
    }

    public boolean isPack() {
        return pack;
    }

    public void setPack(boolean pack) {
        this.pack = pack;
    }

    public boolean isCenter() {
        return center;
    }

    public void setCenter(boolean center) {
        this.center = center;
    }

    public boolean isLoad() {
        return load;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public boolean isLazyLoad() {
        return lazyLoad == null ? DEFAULT_LAZY_LOAD : lazyLoad;
    }

    public Boolean getLazyLoad() {
        return lazyLoad;
    }


    public void setLazyLoad(Boolean lazyLoad) {
        this.lazyLoad = lazyLoad;
    }


    public boolean isForceLoad() {
	return !isLazyLoad();
    }
    
    /**
     * Update state of form (Menu/ToolBar/StatusBar)
     * Use after refresh data or change mode (edit/add/copy/view) of the form
     */
    protected void updateState() {
	//TODO: Menu/ToolBar/ButtonBar
	updateStatusBar();
    }

    
    protected void updateStatusBar() {
	//do nothing
    }

    
    protected void setEnabled(Action action, boolean enabled) {
  	if (action == null) {
  	    return;
  	}
  	action.setEnabled(enabled);
    }

    protected String[] getResourceProviderProperties() {
	return new String[] {CONFIG_FORM_RESOURCE_PROVIDER, CONFIG_RESOURCE_PROVIDER}; 
    }


    /**
     * Add relation form 
     * @param category
     * @param name
     */
    public void addRelationForm(String category, String name) {
	relationFormMap.put(category, name);
    }

    
    public String getRelationFormByCategory(String category) {
	return relationFormMap.get(category);
    }
    
    public IForm<?> createRelationFormByCategory(String category) {
	String name = getRelationFormByCategory(category);
	if (name == null) {
	    return null;
	}
	return FormManager.createForm(name);
    }

    
    public void canNotify(String message, final Callback<Boolean> callback) {
	if (callback == null) {
	    return;
	}
	//TODO: Can notify close ?
	// Always do it by default
	callback.onSuccess(true);
    }

    public void notify(String message) {
	if (Window.METHOD_CLOSE.equals(message)) {
	    doAction(CLOSE_ACTION);
	}
    }
    
    
    public void addSaveDataListener(Listener listener) {
	addFormListener(FormEvents.SaveData, listener);
    }

    public void addUpdateFormListener(Listener listener) {
	addFormListener(FormEvents.UpdateForm, listener);
    }

    public void addFormListener(String type, Listener listener) {
	List<Listener> listenerArray = doGetFormListeners().get(type);
	if (listenerArray == null) {
	    listenerArray = new ArrayList<Listener>();
	    doGetFormListeners().put(type, listenerArray);
	}
	listenerArray.add(listener);
	
    }
    
    protected Map<String, List<Listener>> doGetFormListeners() {
	if (formListeners == null) {
	    formListeners = new LinkedHashMap<String, List<Listener>>();
	}
	return formListeners;
    }

    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected Serializable getActiveModelId() {
	return getModelId();
    }

    protected T getActiveModel() {
	return getModel();
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////
    

    
    
    // NEW/INSTANCE DATA
    protected void newDataByProviderAsync(Callback<T> callback) {
	
   	DataProviderAsync<T> dataProviderAsync = getDataProviderAsync();
   	
   	// EMPTY ID or NO PROVIDER
   	if (dataProviderAsync == null || !(dataProviderAsync instanceof ModelProviderAsync)) {
   	    // EMPTY DATA
   	    callback.onSuccess(null);
   	    return;
   	}
   	
   	((ModelProviderAsync<T>) dataProviderAsync).instance(callback);
   	
    }
    
    
    // LOAD DATA
    protected void loadDataByProviderAsync(Callback<T> callback) {
	
   	DataProviderAsync<T> dataProviderAsync = getDataProviderAsync();
   	Serializable id = getActiveModelId();
   	
   	// EMPTY ID or NO PROVIDER
   	if (id == null || dataProviderAsync == null || !(dataProviderAsync instanceof ModelProviderAsync)) {
   	    // EMPTY DATA
   	    callback.onSuccess(null);
   	    return;
   	}
   	
   	((ModelProviderAsync<T>) dataProviderAsync).getById(id, callback);
   	
    }

    // CREATE DATA
    protected void createDataByProviderAsync(Callback<Serializable> callback) {
	
   	DataProviderAsync<T> dataProviderAsync = getDataProviderAsync();
   	T model = getActiveModel();
   	
   	// EMPTY ID or NO PROVIDER
   	if (model == null || dataProviderAsync == null || !(dataProviderAsync instanceof ModelProviderAsync)) {
   	    // EMPTY DATA
   	    callback.onSuccess(null);
   	    return;
   	}
   	
   	((ModelProviderAsync<T>) dataProviderAsync).create(model, callback);
   	
    }
    
    // STORE DATA
    protected void storeDataByProviderAsync(Callback<T> callback) {
	
   	DataProviderAsync<T> dataProviderAsync = getDataProviderAsync();
   	T model = getActiveModel();
   	
   	// EMPTY ID or NO PROVIDER
   	if (model == null || dataProviderAsync == null || !(dataProviderAsync instanceof ModelProviderAsync)) {
   	    // EMPTY DATA
   	    callback.onSuccess(null);
   	    return;
   	}
   	
   	((ModelProviderAsync<T>) dataProviderAsync).update(model, callback);
   	
    }    
    
    // REMOVE DATA
    protected void removeDataByProviderAsync(Callback<Serializable> callback) {
	
   	DataProviderAsync<T> dataProviderAsync = getDataProviderAsync();
   	Serializable id = getActiveModelId();
   	
   	// EMPTY ID or NO PROVIDER
   	if (id == null || dataProviderAsync == null || !(dataProviderAsync instanceof ModelProviderAsync)) {
   	    // EMPTY DATA
   	    callback.onSuccess(null);
   	    return;
   	}
   	
   	((ModelProviderAsync<T>) dataProviderAsync).removeById(id, callback);
   	
    }
    
    public String getFormMarker() {
	// By default form marker is class name
	String formClassName = getClass().getName();
	if (formClassName.endsWith(UWT.GEN_SUFFIX)) {
	    formClassName = formClassName.substring(0, formClassName.length() - UWT.GEN_SUFFIX.length());
	}
	return formClassName;
    }
}

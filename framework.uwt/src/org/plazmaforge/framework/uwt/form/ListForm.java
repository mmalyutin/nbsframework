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
import java.util.List;

import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.CallbackAdapter;
import org.plazmaforge.framework.core.data.Customizer;
import org.plazmaforge.framework.core.data.DataResult;
import org.plazmaforge.framework.core.data.Pager;
import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.ValuePresenter;
import org.plazmaforge.framework.core.data.ValuePresenterFactory;
import org.plazmaforge.framework.core.data.provider.DataProvider;
import org.plazmaforge.framework.core.data.provider.DataProviderAsync;
import org.plazmaforge.framework.core.data.provider.ListProvider;
import org.plazmaforge.framework.uwt.UIMessages;
import org.plazmaforge.framework.uwt.UWT.FormMode;
import org.plazmaforge.framework.uwt.data.store.DataFilter;
import org.plazmaforge.framework.uwt.data.store.DataSorter;
import org.plazmaforge.framework.uwt.data.store.StoreUtils;
import org.plazmaforge.framework.uwt.data.store.PropertyFilter;
import org.plazmaforge.framework.uwt.data.store.PropertySorter;
import org.plazmaforge.framework.uwt.event.EnterEvent;
import org.plazmaforge.framework.uwt.event.EnterListener;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.CallbackHandler;
import org.plazmaforge.framework.uwt.widget.CallbackResult;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.Event;
import org.plazmaforge.framework.uwt.widget.IViewer;
import org.plazmaforge.framework.uwt.widget.Listener;
import org.plazmaforge.framework.uwt.widget.MessageBox;
import org.plazmaforge.framework.uwt.widget.Viewer;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.table.Table;
import org.plazmaforge.framework.uwt.widget.table.TableColumn;
import org.plazmaforge.framework.uwt.widget.tool.ToolBar;

public abstract class ListForm<T> extends Form<T> implements IListForm<T> {
    
    
    public static final String CONFIG_LIST_FORM_SERVICE_CALLER = "list.form.service.caller";
    
    public static final String CONFIG_LIST_FORM_PRE_CUSTOMIZER = "list.form.pre.customizer";

    public static final String CONFIG_LIST_FORM_POST_CUSTOMIZER = "list.form.post.customizer";
    
    public static final String CONFIG_LIST_FORM_CUSTOMIZER = "list.form.customizer";

    
    /**
     * Data Criteria
     */
    private Criteria criteria;
    
    
    /**
     * Data Pager
     */
    private Pager pager;
    

    /**
     * Current viewer
     */
    private Viewer<T> viewer;

    
    /**
     * True if the form supports paging mode
     */
    private boolean supportPaging;
    
    /**
     * True if the form uses mode
     */
    private boolean pagingMode;
    
    
    // Only for one viewer
    private boolean autoViewer;
    
    private boolean initAutoViewer;
    
    
    public ListForm() {
	super();
	setSupportPaging(true);
	setPagingMode(true);
	setAutoViewer(true);
	addFormListener(FormEvents.RemoveData, new Listener() {

	    @Override
	    public void handleEvent(Event event) {
		doRefreshAction();
	    }
	    
	});
    }

    @Override
    protected void initActions() {
	registerAction(createAction(REFRESH_ACTION, UIMessages.FORM_REFRESH, new Image(REFRESH_ICON_PATH)));
	
	registerAction(createAction(ADD_ACTION, UIMessages.FORM_ADD, new Image(ADD_ICON_PATH)));
	registerAction(createAction(COPY_ACTION, UIMessages.FORM_COPY, new Image(COPY_ICON_PATH)));
	registerAction(createAction(EDIT_ACTION, UIMessages.FORM_EDIT, new Image(EDIT_ICON_PATH)));
	registerAction(createAction(VIEW_ACTION, UIMessages.FORM_VIEW, new Image(VIEW_ICON_PATH)));
	registerAction(createAction(DEL_ACTION, UIMessages.FORM_DELETE, new Image(DEL_ICON_PATH)));
	
	registerAction(createAction(SORT_ACTION, UIMessages.FORM_SORT, new Image(SORT_ICON_PATH)));
	registerAction(createAction(FIND_ACTION, UIMessages.FORM_FIND, new Image(FIND_ICON_PATH)));
	
	registerAction(createAction(FILTER_ACTION, UIMessages.FORM_FILTER, new Image(FILTER_ICON_PATH)));
	registerAction(createAction(FILTER_FAST_ACTION, UIMessages.FORM_FILTER_FAST));
	registerAction(createAction(FILTER_CLEAR_ACTION, UIMessages.FORM_FILTER_CLEAR, new Image(FILTER_CLEAR_ICON_PATH)));
	registerAction(createAction(FILTER_PERIOD_ACTION, UIMessages.FORM_FILTER_PERIOD, new Image(FILTER_PERIOD_ICON_PATH)));
	
	registerAction(createAction(PRINT_ACTION, UIMessages.FORM_PRINT, new Image(PRINT_ICON_PATH)));
	registerAction(createAction(EXPORT_ACTION, UIMessages.FORM_EXPORT, new Image(EXPORT_ICON_PATH)));
	
	registerAction(createAction(FIRST_PAGE_ACTION, UIMessages.FORM_PAGE_FIRST, new Image(FIRST_PAGE_ICON_PATH)));
	registerAction(createAction(PREV_PAGE_ACTION, UIMessages.FORM_PAGE_PREV, new Image(PREV_PAGE_ICON_PATH)));
	registerAction(createAction(NEXT_PAGE_ACTION, UIMessages.FORM_PAGE_NEXT, new Image(NEXT_PAGE_ICON_PATH)));
	registerAction(createAction(LAST_PAGE_ACTION, UIMessages.FORM_PAGE_LAST, new Image(LAST_PAGE_ICON_PATH)));
	
	registerAction(createAction(SETTINGS_ACTION, UIMessages.FORM_SETTINGS, new Image(SETTINGS_ICON_PATH)));
	registerAction(createAction(HELP_ACTION, UIMessages.FORM_HELP, new Image(HELP_ICON_PATH)));
    }

    @Override
    protected void buildToolBar(ToolBar toolBar) {
	
	addAction(toolBar, getAction(REFRESH_ACTION));
	
	buildManageToolBar(toolBar);
	
	addAction(toolBar, getAction(SORT_ACTION));
	addAction(toolBar, getAction(FIND_ACTION));

	buildFilterToolBar(toolBar);
	
	addAction(toolBar, getAction(PRINT_ACTION));
	addAction(toolBar, getAction(EXPORT_ACTION));
	
	buildPageToolBar(toolBar);
	
	addAction(toolBar, getAction(SETTINGS_ACTION));
	addAction(toolBar, getAction(HELP_ACTION));

    }

    protected void buildFilterToolBar(ToolBar toolBar) {
	addAction(toolBar, getAction(FILTER_ACTION));
	addAction(toolBar, getAction(FILTER_CLEAR_ACTION));
	addAction(toolBar, getAction(FILTER_PERIOD_ACTION));
    }

    protected void buildManageToolBar(ToolBar toolBar) {
	addAction(toolBar, getAction(ADD_ACTION));
	addAction(toolBar, getAction(COPY_ACTION));
	addAction(toolBar, getAction(EDIT_ACTION));
	addAction(toolBar, getAction(VIEW_ACTION));
	addAction(toolBar, getAction(DEL_ACTION));
    }
    
    protected void buildPageToolBar(ToolBar toolBar) {
	addAction(toolBar, getAction(FIRST_PAGE_ACTION));
	addAction(toolBar, getAction(PREV_PAGE_ACTION));
	addAction(toolBar, getAction(NEXT_PAGE_ACTION));
	addAction(toolBar, getAction(LAST_PAGE_ACTION));
    }

    
    @Override
    protected void checkDataProvider(DataProvider<T> provider) {
   	if (!(provider instanceof ListProvider)) {
   	    throw new IllegalArgumentException("ListForm must have only ListDataProvider");
   	}
    }
       
    
    public Viewer<T> getViewer() {
	if (viewer == null) {
	    // Auto find viewer
	    viewer = findAutoViewer();
	}
        return viewer;
    }

    public void setViewer(Viewer<T> viewer) {
        this.viewer = viewer;
    }

    // TODO: use MainViewer
    public Viewer<T> getActiveViewer() {
	return getViewer();
    }
    
    public T getSelectionItem() {
	Viewer<T> viewer = getActiveViewer();
	if (viewer == null) {
	    return null;
	}
	return viewer.getSelectionItem();
    }
    
    /**
     * Action switcher
     * 
     * @param action
     */
    @Override
    protected void doAction(String action) {
	try {
	    if (action == null) {
		return;
	    }
	    if (SORT_ACTION.equals(action)) {
		doSortAction();
		return;
	    } else if (FIND_ACTION.equals(action)) {
		// doFindAction();
		return;
	    } else if (FILTER_ACTION.equals(action)) {
		doFilterAction();
		return;
	    } else if (FILTER_CLEAR_ACTION.equals(action)) {
		doFilterClearAction();
		return;

		// Paging tool
	    } else if (FIRST_PAGE_ACTION.equals(action)) {
		doFirstPageAction();
		return;
	    } else if (PREV_PAGE_ACTION.equals(action)) {
		doPrevPageAction();
		return;
	    } else if (NEXT_PAGE_ACTION.equals(action)) {
		doNextPageAction();
		return;
	    } else if (LAST_PAGE_ACTION.equals(action)) {
		doLastPageAction();
		return;

		// Data manage tool
	    } else if (ADD_ACTION.equals(action)) {
		doAddAction();
	    } else if (COPY_ACTION.equals(action)) {
		doCopyAction();
	    } else if (EDIT_ACTION.equals(action)) {
		doEditAction();
		return;
	    } else if (VIEW_ACTION.equals(action)) {
		doViewAction();
	    } else if (DEL_ACTION.equals(action)) {
		doDelAction();

		// Enter: Edit/Choose
	    } else if (ENTER_ACTION.equals(action)) {
		doEnterAction();
		return;
	    }
	} catch (Exception e) {
	    handleError(e);
	}

	super.doAction(action);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // BASE FORM ACTIONS IMPLEMENTATION
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void doHelpAction() {
	//TODO
    }

    @Override
    protected void doSetupAction() {
	doSetupDialog();
    }
    
    @Override
    protected void doPrintAction() {
	//TODO
    }
    
    protected void doRefreshAction() {
	loadData(new CallbackAdapter<DataResult>() {
	    @Override
	    public void onSuccess(DataResult result) {
		//TODO
	    }
	});
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // LIST FORM ACTIONS
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void doSortAction() {
	doSortDialog();
    }
    
    protected void doFilterAction() {
	doFilterDialog();
    }
    

    protected void doFilterClearAction() {
	Criteria cretiria = getCriteria();
	cretiria.resetFilters(); // Reset filters
	loadData(new CallbackAdapter<DataResult>() {
	    @Override
	    public void onSuccess(DataResult result) {
		//TODO
	    }
	});
    }

    
    public void doFirstPageAction() {
	if (!isValidPagingMode() || !getPager().isEnableFirstPage()) {
	    return;
	}
	doNewPage(getPager().firstPage());
    }

    public void doPrevPageAction() {
	if (!isValidPagingMode() || !getPager().isEnablePrevPage()) {
	    return;
	}
	doNewPage(getPager().prevPage());
    }
    
    public void doNextPageAction() {
	if (!isValidPagingMode() || !getPager().isEnableNextPage()) {
	    return;
	}
	doNewPage(getPager().nextPage());
    }
    
    public void doLastPageAction() {
	if (!isValidPagingMode() || !getPager().isEnableLastPage()) {
	    return;
	}
	doNewPage(getPager().lastPage());
    }

    
    protected void doAddAction() {
	doEditAction(FormMode.ADD);
    }

    protected void doCopyAction() {
	doEditAction(FormMode.COPY);
    }
    
    protected void doEditAction() {
	doEditAction(FormMode.EDIT);
    }

    protected void doViewAction() {
	doEditAction(FormMode.VIEW);
    }

    protected void doEditAction(FormMode mode) {
	
	Serializable modelId = null;
	Object model = null;
	
	if (mode == FormMode.COPY 
		|| mode == FormMode.EDIT
		|| mode == FormMode.VIEW) {

	    Viewer<T> viewer = getActiveViewer();
	    if (viewer == null) {
		return;
	    }

	    T item = viewer.getSelectionItem();
	    if (item == null) {
		return;
	    }
	    PropertyProvider<T> propertyProvider = viewer.getPropertyProvider();
	    if (propertyProvider == null) {
		return;
	    }
	    modelId = (Serializable) propertyProvider.getValue(item, "id");
	}
	

	//TODO
	IForm form = createRelationFormByCategory("EditForm");
	if (form == null) {
	    return;
	}
	
	form.setModelId(modelId);
	form.setModel(model);
	form.addSaveDataListener(new Listener() {

	    @Override
	    public void handleEvent(Event event) {
		doRefreshAction();
	    }
	    
	});
	
	FormManager.open(form);
   	
    }
    
    protected void doEnterAction() {
	//TODO
	if (isModal()) {
	    //Choose
	    //doSelectionAction();
	} else {
	    doEditAction();
	}
   	
    }
    
    protected void doDelAction() {
	MessageBox.confirm("Confirm", "Are you sure you want to remove item?", new CallbackHandler() {
	    
	    @Override
	    public void handle(CallbackResult result) {
		if (!CallbackResult.isConfirm(result)) {
		    return;
		}
		removeData(new CallbackAdapter<DataResult>());
	    }
	});
	
  	
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
   
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // LIST FORM DIALOGS
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    protected boolean isOK(CallbackResult result) {
	return result != null && result.isConfirm();
    }
    
    /**
     * Open SortDialog and sort data
     */
    protected void doSortDialog() {
	List<DisplayField> displayFields = createSortFields();
	if (displayFields == null) {
	    // No fields
	    return;
	}
	SortDialog dialog = new SortDialog();
	dialog.setDisplayFields(displayFields);
	dialog.open(new CallbackHandler() {
	    
	    @Override
	    public void handle(CallbackResult result) {
		if (!isOK(result)) {
		    return;
		}
		doSort((List<OrderField>) result.getValue());
	    }
	});

    }

    /**
     * Open FilterDialog and filter data
     */
    protected void doFilterDialog() {
	List<DisplayField> displayFields = createFilterFields();
	if (displayFields == null) {
	    // No fields
	    return;
	}
	FilterDialog dialog = new FilterDialog();
	dialog.setDisplayFields(displayFields);
	dialog.open(new CallbackHandler() {
	    
	    @Override
	    public void handle(CallbackResult result) {
		if (!isOK(result)) {
		    return;
		}
		doFilter((List<FilterField>) result.getValue());
	    }
	});
    }

    /**
     * Open SetupDialog and view visible fields
     */
    protected void doSetupDialog() {
	List<DisplayField> displayFields = createSetupFields();
	if (displayFields == null) {
	    // No fields
	    return;
	}
	SetupDialog dialog = new SetupDialog();
	dialog.setDisplayFields(displayFields);
	dialog.open(new CallbackHandler() {
	    
	    @Override
	    public void handle(CallbackResult result) {
		if (!isOK(result)) {
		    return;
		}
		//doVisibleFields((List<DisplayField>) result.getValue());
	    }
	});	
    }
    
    

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // LIST FROM METHODS
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void openData(Callback<DataResult> resultCallback) {
   	loadData(resultCallback);
    }
    
    @Override
    protected void doLoadData(final Callback<DataResult> resultCallback) {
	Viewer<T> viewer = getActiveViewer();
	if (viewer == null) {
	    return;
	}
	
	initViewer(viewer);
	
	fireEvent(FormEvents.BeforeLoadData);
	
	viewer.refresh(new CallbackAdapter<List<T>>() {
	    @Override
	    public void onSuccess(List<T> result) {
		
		// TODO: Disabled because event.getData() is null
		// See configureActiveViewer(): viewer.addStoreListener
		
		// FIRE LOAD EVENT
		// fireLoadEvent();
		
		fireResultCallback(resultCallback, result);
	    }
	});
    }

    @Override
    protected void doRemoveData(final Callback<DataResult> resultCallback) {
	
	// Analyze DataProvider and DataProviderAsync
	
	fireEvent(FormEvents.BeforeRemoveData);
	
   	////////////////////////////////////////////////////////////////////////////////////////
   	// Create by DataProviderAsync
   	////////////////////////////////////////////////////////////////////////////////////////
	DataProviderAsync<T> dataProviderAsync = getDataProviderAsync();
	if (dataProviderAsync != null) {

	    
	    removeDataByProviderAsync(new Callback<Serializable>() {

		@Override
		public void onFailure(Throwable error) {
		    handleError(error);
		}

		@Override
		public void onSuccess(Serializable result) {
		    
		    //FIRE REMOVE EVENT
		    fireRemoveEvent();
		    fireResultCallback(resultCallback, result);
		}

	    });
	    
	    return;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	// Create by DataProvider
	////////////////////////////////////////////////////////////////////////////////////////
	DataProvider<T> dataProvider = getDataProvider();
	if (dataProvider != null) {
	    
	    //createDataByProvider();
	    
	    //FIRE REMOVE EVENT
	    fireRemoveEvent();
	    fireResultCallback(resultCallback, null);
	    return;
	}

	//FIRE REMOVE EVENT
	fireRemoveEvent();
	fireResultCallback(resultCallback, null);
	
    }
    
  


    protected void initViewer(Viewer<T> viewer) {
	Criteria criteria = getCriteria();
	Pager pager = getPager();
	
	initViewer(criteria, pager, viewer);
    }

    protected void initViewer(Criteria criteria, Pager pager, Viewer<T> viewer) {
	
	initPager(pager, criteria);
	populateCriteria(criteria, pager);
	
	// CRITERIA -> VIEWER
	transferCriteriaToViewer(criteria, viewer);
    }
    
    /**
     * Initialize pager by Criteria
     * @param pager
     * @param criteria
     */
    protected void initPager(Pager pager, Criteria criteria) {
	if (pager == null || criteria == null) {
	    return;
	}
	
	if (!pager.isNavigationState() && criteria.hasFilters()) {
	    pager.setFromRow(Pager.START_ROW_INDEX);
	}
	pager.setNavigationState(false);

    }
    
    /**
     * Populate Pager by data list
     * @param pager
     * @param dataList
     */
    protected void populatePager(Pager pager, List<T> dataList) {
	ToolUtils.populatePager(pager, dataList);
    }


    /**
     * Populate Criteria by Pager
     * @param criteria
     * @param pager
     */
    protected void populateCriteria(Criteria criteria, Pager pager) {
	ToolUtils.populateCriteria(criteria, pager, isPagingMode());
    }

    
    /**
     * Populate Criteria by filter fields
     * @param criteria
     * @param filterFields
     */
    protected void populateCriteriaFilters(Criteria criteria, List<FilterField> filterFields) {
	ToolUtils.populateCriteriaFilters(criteria, filterFields);
    }
    
    
    /**
     * Populate Criteria by order fields
     * @param criteria
     * @param orderFields
     */
    protected void populateCriteriaOrders(Criteria criteria, List<OrderField> orderFields) {
	ToolUtils.populateCriteriaOrders(criteria, orderFields);
    }
    
    
    /**
     * Filter data by list of FilterField from FilterDialog
     * - Reset old criteria filters
     * - Populate criteria filters by new fields (if need)
     * - Reload data
     * 
     * @param filterFields
     */
    protected void doFilter(List<FilterField> filterFields) {
	
	// Get criteria
	Criteria criteria = getCriteria();
	
	// Reset old filters
	criteria.resetFilters();
	
	populateCriteriaFilters(criteria, filterFields);
	
	// Reload data
	loadData(new CallbackAdapter<DataResult>() {
	    @Override
	    public void onSuccess(DataResult result) {
		//TODO
	    }
	});
    }

    /**
     * Sort data by list of OrderField from SortDialog
     * - Reset old criteria orders
     * - Populate criteria orders by new fields (if need)
     * - Reload data
     * 
     * @param orderFields
     */
    protected void doSort(List<OrderField> orderFields) {
	
	// Get criteria
	Criteria criteria = getCriteria();
	
	// Reset old orders
	criteria.resetOrders();
	
	populateCriteriaOrders(criteria, orderFields);
	
	// Reload data
	loadData(new CallbackAdapter<DataResult>() {
	    @Override
	    public void onSuccess(DataResult result) {
		//TODO
	    }
	});

    }

    
    /**
     * Transfer Criteria to Viewer (ONLY FOR CLIENT MODE)
     * @param criteria
     * @param viewer
     */
    protected void transferCriteriaToViewer(Criteria criteria, Viewer<T> viewer) {
	
	// Reset Filters and Sorter
	viewer.resetFilters();
	viewer.resetSorter();
	
	List<DataFilter<T>> dataFilters = createFilters(criteria);
	DataSorter<T> dataSorter = createSorter(criteria);
	
	// Set filter
	if (dataFilters != null) {
	    viewer.setFilters(dataFilters);
	}
	
	// Set sorter
	if (dataSorter != null) {
	    viewer.setSorter(dataSorter);
	}
	
	viewer.setOffset(criteria.getOffset());
	viewer.setLimit(criteria.getLimit());

    }
    
    
  
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // Paging
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected boolean isValidPagingMode() {
	return isPagingMode() && getPager() != null && !getPager().isDisablePager();
    }
    
    

    protected void doNewPage(boolean doit) {
	if (!doit) {
	    return;
	}
	getPager().setNavigationState(true); // !!!
	doRefreshAction();
    }

    public boolean isSupportPaging() {
        return supportPaging;
    }

    public void setSupportPaging(boolean supportPaging) {
        this.supportPaging = supportPaging;
    }

    public final boolean isPagingMode() {
	if (!supportPaging /*|| !FormEnvironment.isPagination() || !hasDataSource()*/ ) {
	    return false;
	}
        return pagingMode;
    }

    public final void setPagingMode(boolean pagingMode) {
        this.pagingMode = pagingMode;
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    
    
    /**
     * Create Filters by Criteria
     * @param criteria
     * @return
     */
    protected List<DataFilter<T>> createFilters(Criteria criteria) {
	List<DataFilter<T>> filters = StoreUtils.createDataFilters(criteria, (PropertyProvider<T>) null);
	if (filters == null) {
	    return filters;
	}
	
	// Configure filters
	for (DataFilter<T> filter: filters) {
	    if (filter instanceof PropertyFilter) {
		configure((PropertyFilter<T>) filter);
	    }
	}
	return filters;
    }  

    /**
     * Configure PropertyFilter
     * Set PropertyProvider
     * @param propertyFilter
     */
    protected void configure(PropertyFilter<T> propertyFilter) {
	if (propertyFilter == null || propertyFilter.getPropertyProvider() != null || getPropertyProvider() == null) {
	    return;
	}
	propertyFilter.setPropertyProvider(getPropertyProvider());
    }
    
    /**
     * Create Sorter by Criteria
     * @param criteria
     * @return
     */
    protected DataSorter<T> createSorter(Criteria criteria) {
	DataSorter<T>  sorter = StoreUtils.createDataSorter(criteria);
	if (sorter == null) {
	    return sorter;
	}

	// Configure sorter
	if (sorter instanceof PropertySorter) {
	    configure((PropertySorter<T>) sorter);
	}
	return sorter;
    }

    /**
     * Configure PropertySorter
     * Set PropertyProvider
     * @param propertySorter
     */
    protected void configure(PropertySorter<T> propertySorter) {
	if (propertySorter == null || propertySorter.getPropertyProvider() != null || getPropertyProvider() == null) {
	    return;
	}
	propertySorter.setPropertyProvider(getPropertyProvider());
    }
    
    /**
     * Return true if we use Criteria (Sorting and Filtering) on client side
     * @return
     */
    public boolean isClientCriteria() {
	return isClientData();
    }
    
    /**
     * Return criteria
     * @return
     */
    public Criteria getCriteria() {
	if (criteria == null) {
	    criteria = new Criteria();
	}
        return criteria;
    }

    
    /**
     * Return pager
     * @return
     */
    public Pager getPager() {
	if (pager == null) {
	    pager = new Pager();
	    pager.setRowLimit(10);
	}
        return pager;
    }


    /**
     * Create list of filter field by active viewer
     * Use to FilterDialog
     * 
     * @return
     */
    protected List<DisplayField> createFilterFields() {
	return ToolUtils.createFilterFields(createDisplayFields());
    }

    /**
     * Create list of sort field by active viewer
     * Use to FilterDialog
     * @return
     */
    protected List<DisplayField> createSortFields() {
	return ToolUtils.createSortFields(createDisplayFields());
    }


    /**
     * Create list of setup field by active viewer
     * Use to SetupDialog
     * @return
     */
    protected List<DisplayField> createSetupFields() {
	return createDisplayFields();
    }

    /**
     * Create list of display field by active viewer
     * Use in dialogs (FilterDislog, SortDialog)
     * 
     * @return
     */
    protected List<DisplayField> createDisplayFields() {
	// Get active viewer
	Viewer<T> viewer = getActiveViewer();
	return ToolUtils.createDisplayFields(viewer);
    }

 
    
    /**
     * Post configuration
     */
    protected void postConfigure() {
	// TODO: Must configure ALL viewers/views.Use getViews() or getViewers()
	
	IViewer<T> viewer = getActiveViewer();
	configureActiveViewer(viewer);
    }
    
    protected void configureActiveViewer(IViewer<T> viewer) {
	
	if (viewer == null) {
	    return;
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	// WARNING !!! ONLY ACTIVE VIEWER CAN GET CONFIGURATION FROM THE FORM
	////////////////////////////////////////////////////////////////////////////////////
	
	viewer.setClientData(isClientData());

	
	// AUTO DATA PROVIDER ASYNC: TRANSFER FROM FORM
	DataProviderAsync<T> dataProviderAsync = getDataProviderAsync(); // FORM DATA PROVIDER ASYNC
	if (dataProviderAsync != null && !viewer.hasDataProviderAsync()) {
	    viewer.setDataProviderAsync(dataProviderAsync);
	}
	
	// AUTO DATA PROVIDER: TRANSFER FROM FORM
	DataProvider<T> dataProvider = getDataProvider(); // FORM DATA PROVIDER
	if (dataProvider != null && !viewer.hasOwnDataProvider()) {
	    viewer.setDataProvider(dataProvider);
	}
	
	// AUTO PROPERTY PROVIDER: TRANSFER FROM FORM
	PropertyProvider<T> propertyProvider = getPropertyProvider(); // FORM DATA PROVIDER
	if (propertyProvider != null && !viewer.hasPropertyProvider()) {
	    viewer.setPropertyProvider(propertyProvider);
	}
	
	// ADD DATA STORE LISTENER: REFRESH DATA
	viewer.addStoreListener(new Listener() {
	    public void handleEvent(Event event) {
		fireLoadEvent(event);
	    }
	});
	
	initViewer((Viewer<T>) viewer);
	
	configure(viewer);
    }
    
    protected void fireLoadEvent(Event event) {
	
	// Populate Pager by data list
	populatePager(getPager(), (List<T>) event.getData());
	
	super.fireLoadEvent(event);
	
	// Update state (Menu/ToolBar/StatusBar)
	//updateState();
	
    }
    
    protected void configure(IViewer<T> viewer) {
	if (viewer == null) {
	    return;
	}
	if (viewer instanceof Table) {
	    configure((Table<T>) viewer);
	    return;
	}
	// TODO: Must implement for all viewers (Tree, List...)
    }
    
    protected void configure(final Table<T> table) {
	if (table == null) {
	    return;
	}
	
	// Set sortable mode by column (click on column header)
	table.setSortable(true);
	
	int columnCount = table.getColumnCount();
	for (int i = 0; i < columnCount; i++) {
	    TableColumn column = table.getColumn(i);
	    configure(column);
	}
	table.addEnterListener(new EnterListener() {
	    
	    @Override
	    public void enter(EnterEvent e) {
		doAction(ENTER_ACTION);
	    }
	});
	
	table.addInternalListener("UpdateSorter", new Listener() {

	    @Override
	    public void handleEvent(Event event) {
		
		// Update criteria orders after update sorter of the table
		Criteria criteria = getCriteria();
		criteria.resetOrders();

		StoreUtils.populateCriteriaDataSorter(criteria, table.getSorter());
	    }
	    
	});
	
    }
    
    protected void configure(TableColumn column) {
	if (column == null) {
	    return;
	}
	String dataType = column.getDataType();
	if (dataType == null) {
	    // Type is empty
	    return;
	}
	
	// AUTO FORMAT
	String format = column.getFormat();
	if (format == null) {
	    // TODO: May be auto set format by type
	}
	
	// AUTO VALUE PRESENTER
	ValuePresenter valuePresenter = column.getValuePresenter();
	if (valuePresenter != null) {
	    // Value presenter is not empty
	    return;
	}
	valuePresenter = getValuePresenterFactory().getValuePresenter(column.getDataType(), format);
	column.setValuePresenter(valuePresenter);
	
    }
    
    private ValuePresenterFactory valuePresenterFactory;
    
    protected  ValuePresenterFactory getValuePresenterFactory() {
	if (valuePresenterFactory == null) {
	    valuePresenterFactory = new ValuePresenterFactory();
	    valuePresenterFactory.registerDefaultValuePresenters();
	}
	return valuePresenterFactory;
    }

    @Override
    protected void updateState() {
	super.updateState();
	updatePagingState();
    }

    @Override
    protected void updateStatusBar() {
	super.updateStatusBar();
	updatePagingStatusText();
    }
    
    protected void updatePagingStatusText() {
	
	if (!isPagingMode()) {
	    return;
	}
	Pager pager = getPager();
	String text = ToolUtils.getPagingString(pager);
	setStatusText(text == null ? "" : text);
    }
    
    protected void updatePagingState() {
	
	if (!isPagingMode()) {
	    return;
	}
	
	Pager pager = getPager();
	if (pager == null || pager.isDisablePager()) {
	    setDisabledPagingTool();
	    return;
	}

	setEnabled(getAction(FIRST_PAGE_ACTION), pager.isEnableFirstPage());
	setEnabled(getAction(PREV_PAGE_ACTION), pager.isEnablePrevPage());
	setEnabled(getAction(NEXT_PAGE_ACTION), pager.isEnableNextPage());
	setEnabled(getAction(LAST_PAGE_ACTION), pager.isEnableLastPage());
    }
 
    protected void setDisabledPagingTool() {
   	setEnabled(getAction(FIRST_PAGE_ACTION), false);
   	setEnabled(getAction(PREV_PAGE_ACTION), false);
   	setEnabled(getAction(NEXT_PAGE_ACTION), false);
   	setEnabled(getAction(LAST_PAGE_ACTION), false);
   }
    

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // OVERRIDE CUSTOMIZERS
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public Customizer getPreCustomizer() {
	if (super.getPreCustomizer() != null) {
	    return super.getPreCustomizer();
	}
        return (Customizer) getConfigAttribute(CONFIG_LIST_FORM_PRE_CUSTOMIZER);
    }


    @Override
    public Customizer getCustomizer() {
	if (super.getCustomizer() != null) {
	    return super.getCustomizer();
	}
        return (Customizer) getConfigAttribute(CONFIG_LIST_FORM_CUSTOMIZER);
    }


    @Override
    public Customizer getPostCustomizer() {
	if (super.getPostCustomizer() != null) {
	    return super.getPostCustomizer();
	}
        return (Customizer) getConfigAttribute(CONFIG_LIST_FORM_POST_CUSTOMIZER);
    }
    
    
    protected String[] getServiceCallerProperties() {
   	return new String[] {CONFIG_LIST_FORM_SERVICE_CALLER, CONFIG_FORM_SERVICE_CALLER, CONFIG_SERVICE_CALLER}; 
    }

    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected PropertyProvider<T> getActivePropertyProvider() {
	Viewer<T> viewer = getActiveViewer();
	if (viewer == null) {
	    return null;
	}
	return viewer.getPropertyProvider();
    }

    protected Serializable getActiveModelId() {
	T model = getActiveModel();
	if (model == null) {
	    return null;
	}
	PropertyProvider<T> propertyProvider = getActivePropertyProvider();
	return (Serializable) propertyProvider.getValue(model, "id");
    }

    protected T getActiveModel() {
	return getSelectionItem();
    }

    public boolean isAutoViewer() {
        return autoViewer;
    }

    public void setAutoViewer(boolean autoViewer) {
        this.autoViewer = autoViewer;
    }
   
    /////////////////////////////////////////////////////////////////////////////////////////////////

    protected Viewer<T> findAutoViewer() {
	if (initAutoViewer || !autoViewer) {
	    return null;
	}
	initAutoViewer = true;
	List<Viewer<?>> viewers = findViewers();
	// Only single viewer
	if (viewers == null || viewers.size() != 1) {
	    return null;
	}
	return (Viewer<T>) viewers.get(0);
    }
    
    protected List<Viewer<?>> findViewers() {
	List<Viewer<?>> viewers = new ArrayList<Viewer<?>>();
	Composite content = getContent();
	if (content == null) {
	    return viewers;
	}
	List<Widget> children = content.getChildren();
	for (Widget child: children) {
	    if (child instanceof Viewer) {
		viewers.add((Viewer<?>) child);
	    }
	}
	return viewers;
    }

    
}

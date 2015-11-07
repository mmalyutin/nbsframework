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

import org.plazmaforge.framework.core.data.Callback;
import org.plazmaforge.framework.core.data.CallbackAdapter;
import org.plazmaforge.framework.core.data.Customizer;
import org.plazmaforge.framework.core.data.DataResult;
import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.provider.DataProvider;
import org.plazmaforge.framework.core.data.provider.DataProviderAsync;
import org.plazmaforge.framework.uwt.UIMessages;
import org.plazmaforge.framework.uwt.graphics.Image;
import org.plazmaforge.framework.uwt.widget.Composite;
import org.plazmaforge.framework.uwt.widget.IField;
import org.plazmaforge.framework.uwt.widget.Widget;
import org.plazmaforge.framework.uwt.widget.tool.ButtonBar;
import org.plazmaforge.framework.uwt.widget.tool.ToolBar;

public abstract class EditForm<T> extends Form<T> implements IEditForm<T> {

    
    public static final String CONFIG_EDIT_FORM_SERVICE_CALLER = "edit.form.service.caller";
    
    public static final String CONFIG_EDIT_FORM_PRE_CUSTOMIZER = "edit.form.pre.customizer";

    public static final String CONFIG_EDIT_FORM_POST_CUSTOMIZER = "edit.form.post.customizer";
    
    public static final String CONFIG_EDIT_FORM_CUSTOMIZER = "edit.form.customizer";

    
    
    @Override
    protected void configure() {
	super.configure();
	setSupportButtonBar(true);
	setPack(true); // Auto pack form by default
	setCenter(true); // Auto center form by default
	setLazyLoad(false); // Force load
    }
    
    @Override
    protected void initActions() {
	registerAction(createAction(REFRESH_ACTION, UIMessages.FORM_REFRESH, new Image(REFRESH_ICON_PATH)));
	registerAction(createAction(PRINT_ACTION, UIMessages.FORM_PRINT, new Image(PRINT_ICON_PATH)));
	registerAction(createAction(EXPORT_ACTION, UIMessages.FORM_EXPORT, new Image(EXPORT_ICON_PATH)));
	registerAction(createAction(HELP_ACTION, UIMessages.FORM_HELP, new Image(HELP_ICON_PATH)));
	
	registerAction(createAction(OK_ACTION, UIMessages.OK, null));
	registerAction(createAction(CANCEL_ACTION, UIMessages.CANCEL, null));
	registerAction(createAction(SAVE_ACTION, UIMessages.SAVE, null));
    }

    @Override
    protected void buildToolBar(ToolBar toolBar) {
	
	addAction(toolBar, getAction(REFRESH_ACTION));
	addAction(toolBar, getAction(PRINT_ACTION));
	addAction(toolBar, getAction(EXPORT_ACTION));
	addAction(toolBar, getAction(HELP_ACTION));

    }


    @Override
    protected void buildButtonBar(ButtonBar buttonBar) {
	
	addAction(buttonBar, getAction(OK_ACTION));
	addAction(buttonBar, getAction(CANCEL_ACTION));
	addAction(buttonBar, getAction(SAVE_ACTION));
	
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // OVERRIDE CUSTOMIZERS
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public Customizer getPreCustomizer() {
	if (super.getPreCustomizer() != null) {
	    return super.getPreCustomizer();
	}
        return (Customizer) getConfigAttribute(CONFIG_EDIT_FORM_PRE_CUSTOMIZER);
    }


    public Customizer getCustomizer() {
	if (super.getCustomizer() != null) {
	    return super.getCustomizer();
	}
        return (Customizer) getConfigAttribute(CONFIG_EDIT_FORM_CUSTOMIZER);
    }


    public Customizer getPostCustomizer() {
	if (super.getPostCustomizer() != null) {
	    return super.getPostCustomizer();
	}
        return (Customizer) getConfigAttribute(CONFIG_EDIT_FORM_POST_CUSTOMIZER);
    }
  
    protected String[] getServiceCallerProperties() {
   	return new String[] {CONFIG_EDIT_FORM_SERVICE_CALLER, CONFIG_FORM_SERVICE_CALLER, CONFIG_SERVICE_CALLER}; 
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
	    if (CANCEL_ACTION.equals(action)) {
		doCancelAction();
		return;
	    } else if (REFRESH_ACTION.equals(action)) {
		doRefreshAction();
		return;
	    } else if (OK_ACTION.equals(action)) {
		doOkAction();
		return;
	    } else if (CANCEL_ACTION.equals(action)) {
		doCancelAction();
		return;
	    } else if (SAVE_ACTION.equals(action)) {
		doSaveAction();
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

	super.doAction(action);
    }    
    
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // EDIT FORM ACTIONS IMPLEMENTATION
    ////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void doCancelAction() {
	doCloseAction();
    }

    protected void doCloseAction() {
	
	//Cancel/reset data and exit
	cancelData();
	
	close();
    }

    protected void doOkAction() {
	
	// Update data by form
	updateData();
	
	// Save data and exit
	saveData(new CallbackAdapter<DataResult>() {
	    @Override
	    public void onSuccess(DataResult result) {
		if (DataResult.isSuccess(result)) {
		    close();
		}
	    }
	});
	
    }
    
    protected void doSaveAction() {
	
	// Update data by form
	updateData();
	
	// Save data without close form
	saveData(new CallbackAdapter<DataResult>());
    }
    

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    // EDIT FORM METHODS IMPLEMENTATION
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    

    public boolean isNew() {
	return getModelId() == null;
    }
    
    
    @Override
    protected void doNewData(final Callback<DataResult> resultCallback) {
	
	// See BaseDataStore.loadDataList()
	// Analyze DataProvider and DataProviderAsync
	
	fireEvent(FormEvents.BeforeLoadData);
	
   	////////////////////////////////////////////////////////////////////////////////////////
   	// Load data by DataProviderAsync
   	////////////////////////////////////////////////////////////////////////////////////////
	DataProviderAsync<T> dataProviderAsync = getDataProviderAsync();
	if (dataProviderAsync != null) {

	    
	    newDataByProviderAsync(new Callback<T>() {

		@Override
		public void onFailure(Throwable error) {
		    handleError(error);
		}

		@Override
		public void onSuccess(T result) {
		    
		    // Set data to form
		    setModel(result);
		    
		    //FIRE LOAD EVENT
		    fireLoadEvent();
		    fireResultCallback(resultCallback, result);
		}

	    });
	    
	    return;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	// Load data by DataProvider
	////////////////////////////////////////////////////////////////////////////////////////
	DataProvider<T> dataProvider = getDataProvider();
	if (dataProvider != null) {
	    
	    //loadDataByProvider();
	    // Set data to form
	    setModel(null); // TODO: STUB
	    
	    //FIRE LOAD EVENT
	    fireLoadEvent();
	    fireResultCallback(resultCallback, null);
	    return;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	// Set empty data to form
	////////////////////////////////////////////////////////////////////////////////////////
	setModel(null);
	
	//FIRE LOAD EVENT
	fireLoadEvent();
	fireResultCallback(resultCallback, null);
	
    }   
    
    
    

    @Override
    protected void doLoadData(final Callback<DataResult> resultCallback) {
	
	// See BaseDataStore.loadDataList()
	// Analyze DataProvider and DataPProviderAsync
	
	fireEvent(FormEvents.BeforeLoadData);
	
   	////////////////////////////////////////////////////////////////////////////////////////
   	// Load data by DataProviderAsync
   	////////////////////////////////////////////////////////////////////////////////////////
	DataProviderAsync<T> dataProviderAsync = getDataProviderAsync();
	if (dataProviderAsync != null) {

	    
	    loadDataByProviderAsync(new Callback<T>() {

		@Override
		public void onFailure(Throwable error) {
		    handleError(error);
		}

		@Override
		public void onSuccess(T result) {
		    
		    // Set data to form
		    setModel(result);
		    
		    //FIRE LOAD EVENT
		    fireLoadEvent();
		    fireResultCallback(resultCallback, result);
		}

	    });
	    
	    return;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	// Load data by DataProvider
	////////////////////////////////////////////////////////////////////////////////////////
	DataProvider<T> dataProvider = getDataProvider();
	if (dataProvider != null) {
	    
	    //loadDataByProvider();
	    // Set data to form
	    setModel(null); // TODO: STUB
	    
	    //FIRE LOAD EVENT
	    fireLoadEvent();
	    fireResultCallback(resultCallback, null); // TODO: Why null?
	    return;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	// Set empty data to form
	////////////////////////////////////////////////////////////////////////////////////////
	setModel(null);
	
	//FIRE LOAD EVENT
	fireLoadEvent();
	fireResultCallback(resultCallback, null); // TODO: Why null?
	
    }
    

    @Override
    protected void doCreateData(final Callback<DataResult> resultCallback) {
	
	// Analyze DataProvider and DataProviderAsync
	
	fireEvent(FormEvents.BeforeCreateData);
	fireEvent(FormEvents.BeforeSaveData);
	
   	////////////////////////////////////////////////////////////////////////////////////////
   	// Create by DataProviderAsync
   	////////////////////////////////////////////////////////////////////////////////////////
	DataProviderAsync<T> dataProviderAsync = getDataProviderAsync();
	if (dataProviderAsync != null) {

	    
	    createDataByProviderAsync(new Callback<Serializable>() {

		@Override
		public void onFailure(Throwable error) {
		    handleError(error);
		}

		@Override
		public void onSuccess(Serializable result) {
		    
		    // Set new data ID
		    setModelId(result);
		    
		    //FIRE CREATE EVENT
		    fireCreateEvent();
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
	    // Set empty data ID
	    setModelId(null); // TODO: STUB
	    
	    //FIRE CREATE EVENT
	    fireCreateEvent();
	    fireResultCallback(resultCallback, null); // TODO: Why null?
	    return;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	// Set empty data ID
	////////////////////////////////////////////////////////////////////////////////////////
	setModelId(null);
	
	//FIRE CREATE EVENT
	fireCreateEvent();
	fireResultCallback(resultCallback, null); // TODO: Why null?
	
    }
    
    @Override
    protected void doStoreData(final Callback<DataResult> resultCallback) {
	
	// Analyze DataProvider and DataProviderAsync
	
	fireEvent(FormEvents.BeforeStoreData);
	fireEvent(FormEvents.BeforeSaveData);
	
   	////////////////////////////////////////////////////////////////////////////////////////
   	// Store by DataProviderAsync
   	////////////////////////////////////////////////////////////////////////////////////////
	DataProviderAsync<T> dataProviderAsync = getDataProviderAsync();
	if (dataProviderAsync != null) {

	    
	    storeDataByProviderAsync(new Callback<T>() {

		@Override
		public void onFailure(Throwable error) {
		    handleError(error);
		}

		@Override
		public void onSuccess(T result) {
		    
		    //FIRE STORE EVENT
		    fireStoreEvent();
		    fireResultCallback(resultCallback, result);
		}

	    });
	    
	    return;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	// Store by DataProvider
	////////////////////////////////////////////////////////////////////////////////////////
	DataProvider<T> dataProvider = getDataProvider();
	if (dataProvider != null) {
	    
	    //storeDataByProvider();
	    //FIRE STORE EVENT
	    fireStoreEvent();
	    fireResultCallback(resultCallback, null); // TODO: Why null?
	    return;
	}

	//FIRE STORE EVENT
	fireStoreEvent();
	fireResultCallback(resultCallback, null); // TODO: Why null?
	
    }
    

    /**
     * Find all <code>IField</code> controls
     * @return
     */
    
    public List<IField<?>> getFields() {
	return getFields(getContent());
    }
    
    protected List<IField<?>> getFields(Composite content) {
	List<IField<?>> fields = new ArrayList<IField<?>>();
	if (content == null) {
	    return fields;
	}
	List<Widget> children = content.getChildren();
	for (Widget child : children) {
	    if (child instanceof IField) {
		// Add field
		fields.add((IField<?>) child);
	    } else if (child instanceof Composite) {
		// Add fields of container
		List<IField<?>> childFields = getFields((Composite) child);
		if (childFields != null && !childFields.isEmpty() ) {
		    fields.addAll(childFields);
		}
	    }
	}
	return fields;
    }

    protected void updateBindingForm() {
	List<IField<?>> fields = getFields();
	if (fields == null || fields.isEmpty()) {
	    return;
	}
	PropertyProvider propertyProvider = getPropertyProvider();
	if (propertyProvider == null) {
	    return;
	}
	Object model = getModel();
	if (model == null) {
	    return;
	}
	for (IField field : fields) {
	    if (!isBindingField(field)) {
		continue;
	    }
	    field.setValue(propertyProvider.getValue(model, field.getProperty()));
	}
    }

    protected void updateBindingData() {
	List<IField<?>> fields = getFields();
	if (fields == null || fields.isEmpty()) {
	    return;
	}
	PropertyProvider propertyProvider = getPropertyProvider();
	if (propertyProvider == null) {
	    return;
	}
	Object model = getModel();
	if (model == null) {
	    return;
	}
	for (IField<?> field : fields) {
	    if (!isBindingField(field)) {
		continue;
	    }
	    propertyProvider.setValue(model, field.getProperty(), field.getValue());
	}
    }
    
    protected boolean isBindingField(IField<?> field) {
	if (field == null) {
	    return false;
	}
	//TODO: Maybe use isBinding property of IField
	return field.getProperty() != null;
    }

    @Override
    public void updateForm() {
	try {
	    // by default use binding mode to update form
	    updateBindingForm();
	} catch (Throwable e) {
	    handleError(e);
	}
    }

    @Override
    public void updateData() {
	try {
	    // by default use binding mode to update data
	    updateBindingData();
	} catch (Throwable e) {
	    handleError(e);
	}
    }

}

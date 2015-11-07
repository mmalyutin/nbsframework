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

import org.plazmaforge.framework.core.data.PropertyProvider;
import org.plazmaforge.framework.core.data.provider.DataProvider;
import org.plazmaforge.framework.core.data.provider.DataProviderAsync;
import org.plazmaforge.framework.core.resource.Resource;
import org.plazmaforge.framework.core.resource.ResourceProvider;
import org.plazmaforge.framework.core.service.ServiceCaller;
import org.plazmaforge.framework.uwt.UWT.FormMode;
import org.plazmaforge.framework.uwt.view.IContainerView;
import org.plazmaforge.framework.uwt.view.IView;
import org.plazmaforge.framework.uwt.widget.IDecorator;
import org.plazmaforge.framework.uwt.widget.Listener;

public interface IForm<T> extends IContainerView<T>, IFormConstants {

    
    
    int DEFAULT_FORM_WIDTH = 300;
    
    int DEFAULT_FORM_HEIGHT = 200;

    
    int MIN_FORM_WIDTH = 100;
    
    int MIN_FORM_HEIGHT = 100;
    
    /**
     * Initialize the form
     */
    void init();
    

    /**
     * Open the form
     */
    void open();
    
    /**
     * Close the form
     */
    void close();

    /**
     * Return true if the form is closed
     * @return
     */
    boolean isClosed();
    
    /**
     * Load data of the form
     */
    void load();
    
    /**
     * Set Decorator of the form
     * - DesktopItem
     * - Frame
     * - Dialog
     * @param decorator
     */
    void setDecorator(IDecorator decorator);

    /**
     * Return Decorator of the form
     * @return
     */
    IDecorator getDecorator();
    

    /**
     * Return first child <code>IView</code>
     * @return
     */
    IView getView();
    
    /**
     * Return all child <code>IView</code>'s
     * @return
     */
    IView[] getViews();
    
 
    /**
     * Set model object to the form
     * @param model
     */
    void setModel(T model);
    
    /**
     * Return model object from the form
     * @return
     */
    T getModel();
    
    /**
     * Get name of the model of the form
     * @return
     */
    String getModelName();

    /**
     * Set name of the model of the form
     * @param modelName
     */
    void setModelName(String modelName);

    /**
     * Get ID of the model of the form 
     * @return
     */
    Serializable getModelId();

    /**
     * Set ID of the model of the form
     * @param modelId
     */
    void setModelId(Serializable modelId);
    
    /**
     * Return PropertyProvider of the model
     * @return
     */
    PropertyProvider<T> getPropertyProvider();

    /**
     * Set PropertyProvider of the model
     * @param propertyProvider
     */
    void setPropertyProvider(PropertyProvider<T> propertyProvider);
    
    
    /**
     * Set DataProvider (internal data producer) to the form
     * @param provider
     */
    void setDataProvider(DataProvider<T> dataProvider);
    
    /**
     * Return DataProvider (internal data producer) of the form
     * @return
     */
    DataProvider<T> getDataProvider();
    

    /**
     * Return DataProviderAsync (wrapper of external data producer) of the form
     * @return
     */
    DataProviderAsync<T> getDataProviderAsync();

    /**
     * Set DataProviderAsync (wrapper of external data producer) to the form
     * @param dataProviderAsync
     */
    void setDataProviderAsync(DataProviderAsync<T> dataProviderAsync);

	
    /**
     * Set Service (external data producer) to the form
     * @param service
     */
    void setService(Object service);
    
    /**
     * Return Service (external data producer) of the form
     * @return
     */
    Object getService();
    
    /**
     * Get name of the service of the form
     * @return
     */
    String getServiceName();

    /**
     * Set name of the service of the form
     * @param serviceName
     */
    void setServiceName(String serviceName);

    /**
     * Return ServiceCaller of the form
     * @return
     */
    ServiceCaller getServiceCaller();

    /**
     * Set ServiceCaller of the form
     * @param serviceCaller
     */
    void setServiceCaller(ServiceCaller serviceCaller);

    
    String getLocaleName();
    
    void setLocaleName(String localeName);
    
    Resource getResource();

    void setResource(Resource resource);
    
    String getResourceName();
    
    void setResourceName(String resourceName);

    ResourceProvider getResourceProvider();
    
    void setResourceProvider(ResourceProvider resourceProvider);
    
    
    
    /**
     * Return true if model of the was modified
     * @return
     */
    boolean isModify();
    
    
    
    /**
     * Sets editable mode of the form
     * @param mode
     */
    void setMode(FormMode mode);
    
    /**
     * Return editable mode of the form
     * @return
     */
    FormMode getMode();
    
    /**
     * Return true if editable mode of form is EDIT
     * @return
     */
    boolean isEditMode();
    
    /**
     * Return true if editable mode of form is ADD
     * @return
     */
    boolean isAddMode();

    /**
     * Return true if editable mode of form is COPY
     * @return
     */
    boolean isCopyMode();

    /**
     * Return true if editable mode of form is VIEW
     * @return
     */
    boolean isViewMode();
    
    /**
     * Return true if form has modal mode
     * 
     * @return
     */
    boolean isModal();
    
    
    /**
     * Return true if need pack decorator after open the form
     * @return
     */
    boolean isPack();
    
    
    /**
     * Return true if need center decorator after open the form
     * @return
     */
    boolean isCenter();
    
    
    /**
     * Return width of the form
     * @return
     */
    int getWidth();
    
    /**
     * Set width of the form
     * @param width
     */
    void setWidth(int width);
    
    /**
     * Return height of the form
     * @return
     */
    int getHeight();
    
    
    /**
     * Set height of the form
     * @param height
     */
    void setHeight(int height);
    
    
    /**
     * Returns true if the form has data source or form works with entity service.
     * If the form doesn't have data source then you have to use <code>setModel</code> method.
     * @return
     */
    //boolean hasDataSource();
        
   
    boolean isLazyLoad();

    Boolean getLazyLoad();
    
    void setLazyLoad(Boolean lazyLoad);
    
    
    boolean isClientData();

    Boolean getClientData();
    
    void setClientData(Boolean clientData);
    
    
    void addSaveDataListener(Listener listener);

    
    void addUpdateFormListener(Listener listener);
    
    
    void addRelationForm(String category, String name);

    
    String getRelationFormByCategory(String category);
 
    String getFormMarker();
}

package org.plazmaforge.framework.uwt.gxt.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.extjs.gxt.ui.client.data.BeanModel;


public abstract class XBeanModelFactory {
    
    protected abstract XBeanModel newInstance();

    /**
     * Creates a new bean model instance.
     * 
     * @param bean creates a new model
     * @return the new model
     */
    public BeanModel createModel(Object bean) {
      XBeanModel model = newInstance();
      model.setBean(bean);
      return model;
    }

    /**
     * Creates a list new bean model instances.
     * 
     * @param beans the list of beans
     * @return the list of models
     */
    public List<BeanModel> createModel(Collection<?> beans) {
      List<BeanModel> models = new ArrayList<BeanModel>();
      for (Object obj : beans) {
        models.add(createModel(obj));
      }
      return models;
    }

}

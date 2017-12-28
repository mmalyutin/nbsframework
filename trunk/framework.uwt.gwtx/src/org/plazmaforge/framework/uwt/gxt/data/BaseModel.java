package org.plazmaforge.framework.uwt.gxt.data;



import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sencha.gxt.core.shared.FastMap;
import com.sencha.gxt.core.shared.FastSet;



/**
 * <code>Models</code> are generic data structures. 
 * The structure allows a form of 'introspection' as all property names
 * and values can be queried and retrieved at runtime.
 * 
 * 
 * <p>
 * Model objects implement <code>Serializable</code> and can therefore be used
 * with GWT RPC. A model's children are not marked transient and will be passed
 * in remote procedure calls.
 * </p>
 * 
 * @see Serializable
 */
public class BaseModel implements Model, Serializable {

  //protected transient ChangeEventSupport changeEventSupport;

  /**
   * Creates a new base model.
   */
  public BaseModel() {
    //changeEventSupport = new ChangeEventSupport();
  }

  /**
   * Creates a new base model.
   * 
   * @param properties the initial values
   */
  public BaseModel(Map<String, Object> properties) {
    this();
    setProperties(properties);
  }
  
  
  protected RpcMap map;
  protected boolean allowNestedValues = true;
//
//  /**
//   * Creates a new model data instance.
//   */
//  public BaseModelData() {
//  }
//
//  /**
//   * Creates a new model with the given properties.
//   * 
//   * @param properties the initial properties
//   */
//  public BaseModelData(Map<String, Object> properties) {
//    super();
//    setProperties(properties);
//  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  public <X> X get(String property) {
    if (allowNestedValues && NestedModelUtil.isNestedProperty(property)) {
      return (X) NestedModelUtil.getNestedValue(this, property);
    }
    if (map == null) {
      return null;
    }
    int start = property.indexOf("[");
    int end = property.indexOf("]");
    X obj = null;
    if (start > -1 && end > -1) {
      Object o = map.get(property.substring(0, start));
      String p = property.substring(start + 1, end);
      if (o instanceof Object[]) {
        obj = (X) ((Object[]) o)[Integer.valueOf(p)];
      } else if (o instanceof List) {
        obj = (X) ((List) o).get(Integer.valueOf(p));
      } else if (o instanceof Map) {
        obj = (X) ((Map) o).get(p);
      }
    } else {
      obj = (X) map.get(property);
    }
    return obj;
  }

  /**
   * Returns a property value.
   * 
   * @param property the property name
   * @param valueWhenNull
   * @return the value
   */
  @SuppressWarnings("unchecked")
  public <X> X get(String property, X valueWhenNull) {
    X value = (X) get(property);
    return (value == null) ? valueWhenNull : value;
  }

  public Map<String, Object> getProperties() {
    Map<String, Object> newMap = new FastMap<Object>();
    if (map != null) {
      newMap.putAll(map.getTransientMap());
    }
    return newMap;
  }

  public Collection<String> getPropertyNames() {
    Set<String> set = new FastSet();
    if (map != null) {
      set.addAll(map.keySet());
    }
    return set;
  }

  /**
   * Returns true if nested values are enabled.
   * 
   * @return the nested values state
   */
  public boolean isAllowNestedValues() {
    return allowNestedValues;
  }

  @SuppressWarnings("unchecked")
  public <X> X remove(String property) {
    return map == null ? null : (X) map.remove(property);
  }

  /**
   * Sets the property and fires an <i>Update</i> event.
   * 
   * @param property the property name
   * @param value the property value
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public <X> X set(String property, X value) {
    if (allowNestedValues && NestedModelUtil.isNestedProperty(property)) {
      return (X) NestedModelUtil.setNestedValue(this, property, value);
    }
    if (map == null) {
      map = new RpcMap();
    }

    int start = property.indexOf("[");
    int end = property.indexOf("]");

    if (start > -1 && end > -1) {
      Object o = get(property.substring(0, start));
      String p = property.substring(start + 1, end);
      if (o instanceof Object[]) {
        int i = Integer.valueOf(p);
        Object[] oa = (Object[]) o;
        X old = (X) oa[i];
        oa[i] = value;
        return old;
      } else if (o instanceof List) {
        int i = Integer.valueOf(p);
        List list = (List) o;
        return (X) list.set(i, value);
      } else if (o instanceof Map) {
        Map map = (Map) o;
        return (X) map.put(p, value);
      } else {
        // not supported
        return null;
      }
    } else {
      return (X) map.put(property, value);
    }

  }

  /**
   * Sets whether nested properties are enabled (defaults to true).
   * 
   * @param allowNestedValues true to enable nested properties
   */
  public void setAllowNestedValues(boolean allowNestedValues) {
    this.allowNestedValues = allowNestedValues;
  }

  /**
   * Sets the properties.
   * 
   * @param properties the properties
   */
  public void setProperties(Map<String, Object> properties) {
    for (String property : properties.keySet()) {
      set(property, properties.get(property));
    }
  }
  

//  /**
//   * Adds a listener to receive change events.
//   * 
//   * @param listener the listener to be added
//   */
//  public void addChangeListener(ChangeListener... listener) {
//    //changeEventSupport.addChangeListener(listener);
//  }
//
//  /**
//   * Adds the listeners to receive change events.
//   * 
//   * @param listeners the listeners to add
//   */
//  public void addChangeListener(List<ChangeListener> listeners) {
////    for (ChangeListener listener : listeners) {
////      changeEventSupport.addChangeListener(listener);
////    }
//  }

  /**
   * Returns true if change events are disabled.
   * 
   * @return true if silent
   */
  public boolean isSilent() {
    return false; //changeEventSupport.isSilent();
  }
//
//  public void notify(ChangeEvent evt) {
//    /changeEventSupport.notify(evt);
//  }

//  @Override
//  @SuppressWarnings("unchecked")
//  public <X> X remove(String name) {
//    if (map != null && map.containsKey(name)) {
//      X oldValue = (X) super.remove(name);
//      //notifyPropertyChanged(name, null, oldValue);
//      return oldValue;
//    }
//    return null;
//  }

  /**
   * Removes a previously added change listener.
   * 
   * @param listener the listener to be removed
   */
//  public void removeChangeListener(ChangeListener... listener) {
//    //changeEventSupport.removeChangeListener(listener);
//  }
//
//  public void removeChangeListeners() {
//    //changeEventSupport.removeChangeListeners();
//  }

//  @Override
//  public <X> X set(String name, X value) {
//    X oldValue = super.set(name, value);
//    notifyPropertyChanged(name, value, oldValue);
//    return oldValue;
//  }

  public void setSilent(boolean silent) {
    //changeEventSupport.setSilent(silent);
  }

//  protected void fireEvent(int type) {
//    //notify(new ChangeEvent(type, this));
//  }
//
//  protected void fireEvent(int type, Model item) {
//    //notify(new ChangeEvent(type, this, item));
//  }
//
  protected void notifyPropertyChanged(String name, Object value, Object oldValue) {
    //TODO:DISABLE:MIGRATION
    //if (!Util.equalWithNull(value, oldValue)) {
    //  notify(new PropertyChangeEvent(Update, this, name, oldValue, value));
    //}
  }

}

package org.plazmaforge.framework.uwt.gxt.data;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NestedModelUtil {

  @SuppressWarnings("unchecked")
  public static <X> X getNestedValue(Model model, String property) {
    return (X) getNestedValue(model, getPath(property));
  }

//  @Deprecated
//  public static Object convertIfNecessary(Object obj) {
//    if (obj == null || obj instanceof ModelData) {
//      return obj;
//    }
//    BeanModelLookup lookup = BeanModelLookup.get();
//    BeanModelFactory factory = lookup != null ? lookup.getFactory(obj.getClass()) : null;
//    return factory != null ? factory.createModel(obj) : null;
//  }

  @SuppressWarnings("unchecked")
  public static <X> X getNestedValue(Model model, List<String> paths) {
    Object obj = model.get(paths.get(0));
    if (paths.size() == 1) {
      return (X) obj;
    } else if (obj != null && obj instanceof Model) {
      List<String> tmp = new ArrayList<String>(paths);
      tmp.remove(0);
      return (X) getNestedValue((Model) obj, tmp);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public static <X> X setNestedValue(Model model, String property, Object value) {
    return (X) setNestedValue(model, getPath(property), value);
  }

  @SuppressWarnings("unchecked")
  public static <X> X setNestedValue(Model model, List<String> paths, Object value) {
    int index = paths.size() - 1;
    String path = paths.get(index);
    paths.remove(index);
    Model m = getNestedValue(model, paths);
    return (X) m.set(path, value);
  }

  public static boolean isNestedProperty(String property) {
    return property != null && property.contains(".");
  }

  private static List<String> getPath(String property) {
    return new ArrayList<String>(Arrays.asList(property.split("\\.")));
  }
}


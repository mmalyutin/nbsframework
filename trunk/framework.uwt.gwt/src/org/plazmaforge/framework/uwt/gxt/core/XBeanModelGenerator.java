package org.plazmaforge.framework.uwt.gxt.core;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.ModelMarker;

import com.extjs.gxt.ui.client.core.FastMap;

import com.extjs.gxt.ui.client.data.BeanModel;

//import com.extjs.gxt.ui.client.data.BeanModelFactory;
//import com.extjs.gxt.ui.client.data.BeanModelLookup;
//import com.extjs.gxt.ui.client.data.BeanModelMarker;
//import com.extjs.gxt.ui.client.data.BeanModelTag;

import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.NestedModelUtil;
import com.extjs.gxt.ui.client.data.BeanModelMarker.BEAN;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.NotFoundException;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;


public class XBeanModelGenerator extends Generator {

    protected TypeOracle oracle;
    
    /**
     * Bean marker by interface
     */
    protected JClassType beanMarkerType;
    
    
    //protected JClassType beanModelTagType;
    
    /**
     * GXT ModelData type
     */
    protected JClassType modelDataType;
    
    /**
     * List of bean packages
     */
    protected String[] beanPackages;
    
    /**
     * List of bean super classes
     */
    protected String[] beanClasses;

    
    protected JClassType[] beanTypes;
    
    protected List<JClassType> beans;


    /**
     * Return marker class (interface)
     * Override to use other marker
     * @return
     */
    protected Class<?> getBeanMarkerClass() {
	return ModelMarker.class;
    }

    /**
     * Return GXT ModelData class
     * Override to use other class
     * @return
     */
    protected Class<?> getModelDataClass() {
	return ModelData.class;
    }

    public String[] getBeanPackages() {
        return beanPackages;
    }
    
    public String[] getBeanClasses() {
        return beanClasses;
    }

    @Override
    public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
      oracle = context.getTypeOracle();
      
      // Bean marker interface
      beanMarkerType = oracle.findType(getBeanMarkerClass().getName());
      
      // GXT ModelData
      modelDataType = oracle.findType(getModelDataClass().getName());

      // TODO: STUB
      beanPackages = new String[] {
	      "org.plazmaforge.framework.config.object",
	      "org.plazmaforge.bs.base.shared.entities",
	      "org.plazmaforge.bs.organization.shared.entities",
	      "org.plazmaforge.bs.partner.shared.entities",
	      "org.plazmaforge.bs.contact.shared.entities",
	      "org.plazmaforge.bs.document.shared.entities",
	      "org.plazmaforge.bs.project.shared.entities"
	      };
      beanClasses = new String[] {
	      "org.plazmaforge.framework.config.object.ObjectConfig",
	      "java.io.Serializable"
	      };
      
      if (beanClasses != null) {
	  beanTypes = new JClassType[beanClasses.length];
	  for (int i = 0; i < beanClasses.length; i++ ) {
	      beanTypes[i] = oracle.findType(beanClasses[i]);
	  }
      }
      
      
      //oha-remove
      //beanModelMarkerType = oracle.findType(BeanModelMarker.class.getName());
      //beanModelTagType = oracle.findType(BeanModelTag.class.getName());

      try {
        // final all beans and bean markers
        beans = new ArrayList<JClassType>();
        JClassType[] types = oracle.getTypes();
        for (JClassType type : types) {
            
		if (isBeanMarker(type)) {
		    beans.add(type);
		}

		//oha-remove
		/*
		if (isBeanMarker(type)) {
		    beans.add(getMarkerBean(type));
		} else if (isBean(type)) {
		    beans.add(type);
		}
		*/
        }

        final String genPackageName = XBeanModelLookup.class.getPackage().getName();
        final String genClassName = "XBeanModelLookupImpl";

        ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(genPackageName, genClassName);
        
        composer.setSuperclass(XBeanModelLookup.class.getCanonicalName());
        composer.addImport(XBeanModelFactory.class.getName());
        composer.addImport(Map.class.getName());
        composer.addImport(FastMap.class.getName());

        PrintWriter pw = context.tryCreate(logger, genPackageName, genClassName);

        if (pw != null) {
          SourceWriter sw = composer.createSourceWriter(context, pw);

          sw.println("private Map<String, XBeanModelFactory> m;");

          //oha-add
          //sw.println("public XBeanModelFactory getFactory(Class b) {");
          //sw.indent();
          //sw.println("String n = b.getName();");
          //sw.println("return getFactory(n);");
          //sw.outdent();
          //sw.println("}");
          
          //sw.println("public XBeanModelFactory getFactory(Class b) {"); //oha-remove
          sw.println("public XBeanModelFactory getFactory(String n) {"); //oha-add
          sw.indent();
          //sw.println("String n = b.getName();"); //oha-remove
          sw.println("if (m == null) {");
          sw.indentln("m = new FastMap<XBeanModelFactory>();");
          sw.println("}");
          sw.println("if (m.get(n) == null) {");
          sw.indent();
          StringBuffer sb = new StringBuffer();
          for (int i = 0; i < beans.size(); i++) {
            JClassType bean = beans.get(i);
            String name = createBean(bean, logger, context);
            String factory = createFactory(bean, name, logger, context);

            if (i > 0) {
              sw.print(" else ");
            }
            sw.println("if (" + bean.getQualifiedSourceName() + ".class.getName().equals(n)) {");
            sw.indentln("m" + i + "();");

            sb.append("private void m" + i + "() {\n");
            sb.append("  m.put(" + bean.getQualifiedSourceName() + ".class.getName(), new " + factory + "());\n");
            sb.append("}\n");

            sw.print("}");
          }
          sw.outdent();
          sw.println("}");
          sw.println("return m.get(n);");
          sw.outdent();
          sw.println("}");

          sw.println(sb.toString());
          sw.commit(logger);
        }

        return composer.getCreatedClassName();

      } catch (Exception e) {
        logger.log(TreeLogger.ERROR, "Class " + typeName + " not found.", e);
        throw new UnableToCompleteException();
      }

    }

    protected String createFactory(JClassType bean, String beanModelName, TreeLogger logger, GeneratorContext context)
        throws Exception {
      final String genPackageName = XBeanModelLookup.class.getPackage().getName();
      final String genClassName = "XBeanModel_" + bean.getQualifiedSourceName().replace(".", "_") + "_Factory";

      ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(genPackageName, genClassName);
      composer.setSuperclass(XBeanModelFactory.class.getCanonicalName());
      PrintWriter pw = context.tryCreate(logger, genPackageName, genClassName);

      if (pw != null) {
        SourceWriter sw = composer.createSourceWriter(context, pw);
        sw.println("public XBeanModel newInstance() {");
        sw.println("return new " + beanModelName + "();");
        sw.println("}");
        sw.commit(logger);
      }
      return composer.getCreatedClassName();
    }

    protected String createBean(JClassType bean, TreeLogger logger, GeneratorContext context) throws Exception {
      final String genPackageName = bean.getPackage().getName();
      final String genClassName = "XBeanModel_" + bean.getQualifiedSourceName().replace(".", "_");

      ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(genPackageName, genClassName);
      composer.setSuperclass(XBeanModel.class.getCanonicalName());
      composer.addImport(BeanModel.class.getName());
      composer.addImport(XBeanModel.class.getName());
      composer.addImport(NestedModelUtil.class.getName());
      PrintWriter pw = context.tryCreate(logger, genPackageName, genClassName);

      if (pw != null) {
        List<JMethod> getters = findGetters(bean);
        List<JMethod> setters = findSetters(bean);
        SourceWriter sw = composer.createSourceWriter(context, pw);

        sw.println("public " + genClassName + "(){");
        for (JMethod method : getters) {
          String s = method.getName();
          String p = lowerFirst(s.substring(s.startsWith("g") ? 3 : 2)); // get or
          // is
          sw.println("beanProperties.add(\"" + p + "\");");
        }
        sw.println("}");

        createGetMethods(getters, sw, bean.getQualifiedSourceName());
        createSetMethods(setters, sw, bean.getQualifiedSourceName());

        // delegate equals to bean
        sw.println("public boolean equals(Object obj) {");
        sw.println("  if (obj instanceof " + "BeanModel" + ") {");
        sw.println("    obj = ((BeanModel)obj).getBean();");
        sw.println("  }");
        sw.println("  return bean.equals(obj);");
        sw.println("}");

        // delegate hashCode to bean
        sw.println("public int hashCode(){");
        sw.println("  return bean.hashCode();");
        sw.println("}");

        sw.commit(logger);
      }
      return composer.getCreatedClassName();
    }

    protected JClassType getMarkerBean(JClassType type) throws NotFoundException {
      BEAN pojo = type.getAnnotation(BEAN.class);
      return oracle.getType(pojo.value().getCanonicalName());
    }


    //oha-remove
    /*
    protected boolean isBean(JClassType type) {
      return !type.equals(beanModelTagType) && type.isAssignableTo(beanModelTagType)
          && !type.isAssignableTo(oracle.findType(ModelData.class.getName()));
    }
    */

    protected boolean isBeanMarker(JClassType type) {
	if (type.equals(beanMarkerType) || type.isAssignableTo(modelDataType)) {
	    // Return false because type is marker interface
	    // or type extends GXT ModelData
	    return false;
	}
	// Return true if type implements marker interface
	return type.isAssignableTo(beanMarkerType) || isBean(type);
	
	// return !type.equals(beanMarkerType) && type.isAssignableTo(beanMarkerType)
	// && !type.isAssignableTo(modelDataType);
	
    }
    
    protected boolean isBean(JClassType type) {
	if (beanPackages == null || beanTypes == null || type.isInterface() != null) {
	    return false;
	}
	String typePackage = type.getPackage().getName();
	for (String beanPackage : beanPackages) {
	    if (!beanPackage.equals(typePackage)) {
		continue;
	    }
	    
	    for (JClassType beanType : beanTypes) {
		if (beanType == null) {
		    continue;
		}
		if (type.isAssignableTo(beanType)) {
		    //System.out.println("BEAN-TYPE=" + type.getPackage().getName() + "." + type.getName());
		    return true; 
		}
	    }
	}
	return false;
    }

    protected void createGetMethods(List<JMethod> getters, SourceWriter sw, String typeName) {
      sw.println("public <X> X get(String s) {");

      // Special fix for 'toString'
      sw.println("if (s.equals(\"toString\")) {");
      sw.println("return (X) bean.toString();");
      sw.println("}");
      
      sw.println("if (allowNestedValues && NestedModelUtil.isNestedProperty(s)) {");
      sw.indentln("return (X)NestedModelUtil.getNestedValue(this, s);");
      sw.println("}");

      for (JMethod method : getters) {
        JClassType returnType = method.getReturnType().isClassOrInterface();
        String s = method.getName();
        String p = lowerFirst(s.substring(s.startsWith("g") ? 3 : 2)); // get or

        sw.println("if (s.equals(\"" + p + "\")) {");
        sw.println("Object value = ((" + typeName + ")bean)." + s + "();");

        try {
          if (returnType != null && returnType.isAssignableTo(oracle.getType(List.class.getName()))
              && returnType.isParameterized() != null) {
            JParameterizedType type = returnType.isParameterized();
            JClassType[] params = type.getTypeArgs();
            if (beans.contains(params[0])) {
              sw.println("if (value != null) {");
              sw.indent();
              sw.println("java.util.List list = (java.util.List)value;");
              sw.println("java.util.List list2 = " + XBeanModelLookup.class.getCanonicalName() + ".get().getFactory("
                  + params[0].getQualifiedSourceName() + ".class).createModel((java.util.Collection) list);");
              sw.outdent();
              sw.println("return (X) list2;");
              sw.println("}");
            }
          } else {
            // swap returnType as generic types were not matching
            // (beans.contains(returnType))
            if (returnType != null) {
              String t = returnType.getQualifiedSourceName();
              if (t.indexOf("extends") == -1) {
                returnType = oracle.getType(t);
              }
            }
            if (beans.contains(returnType)) {
              sw.println("if (value != null) {");
              sw.println("    BeanModel nestedModel = nestedModels.get(s);");
              sw.println("    if (nestedModel != null) {");
              sw.println("      Object bean = nestedModel.getBean();");
              sw.println("      if (!bean.equals(value)){");
              sw.println("        nestedModel = null;");
              sw.println("      }");
              sw.println("    }");
              sw.println("    if (nestedModel == null) {");
              sw.println("        nestedModel = " + XBeanModelLookup.class.getCanonicalName() + ".get().getFactory("
                  + returnType.getQualifiedSourceName() + ".class).createModel(value);");
              sw.println("        nestedModels.put(s, nestedModel);");
              sw.println("    }");
              sw.println("    return (X)processValue(nestedModel);");
              sw.println("}");
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }

        sw.println("return (X)processValue(value);");
        sw.println("}");
      }
      sw.println("return super.get(s);");
      sw.println("}");
    }

    protected String lowerFirst(String propName) {
      if (propName.length() == 0) {
        return propName;
      } else if (propName.length() == 1) {
        return propName.toLowerCase();
      } else {
        return propName.substring(0, 1).toLowerCase() + propName.substring(1);
      }
    }

    protected String getMethodAttributeType(JMethod method) {
      return method.getParameters()[0].getType().getQualifiedSourceName();
    }

    protected void createSetMethods(List<JMethod> properties, SourceWriter sw, String typeName) {
      sw.println("public <X> X set(String s, X val) {");
      sw.indent();
      sw.println("Object obj = val;");
      
      sw.println("if (obj instanceof BeanModel) {");
        sw.println("obj = ((BeanModel) obj).getBean();");
      sw.println("} else if (obj instanceof java.util.List) {");
        sw.println("java.util.List list = new java.util.ArrayList();");
        sw.println("for(Object o : (java.util.List) obj) {");
          sw.println("if(o instanceof BeanModel) {");
            sw.println("list.add(((BeanModel) o).getBean());");
          sw.println("} else {");
            sw.println("list.add(o);");
          sw.println("}");
        sw.println("}");
        sw.println("obj = list;");
      sw.println("}");
      
      sw.println("if (allowNestedValues && val instanceof BeanModel) {");
      sw.indent();
      sw.println("obj = ((BeanModel)val).getBean();");
      sw.println("if (nestedModels.containsKey(s)) {");
      sw.indent();
      sw.println("nestedModels.put(s, (BeanModel)val);");
      sw.outdent();
      sw.println("}");
      sw.outdent();
      sw.println("}");

      sw.println("if (allowNestedValues && NestedModelUtil.isNestedProperty(s)) {");
      sw.indent();
      sw.println("X old = (X) NestedModelUtil.setNestedValue(this, s, val);");
      sw.println("notifyPropertyChanged(s, val, old);");
      sw.println("return old;");
      sw.outdent();
      sw.println("}");

      for (JMethod method : properties) {
        String s = method.getName();
        String p = lowerFirst(s.substring(3));
        String type = getMethodAttributeType(method);

        if (type.indexOf("extends") != -1) {
          type = "java.lang.Object";
        }

        if (type.equals("byte")) {
          type = "Byte";
        } else if (type.equals("char")) {
          type = "Character";
        } else if (type.equals("short")) {
          type = "Short";
        } else if (type.equals("int")) {
          type = "Integer";
        } else if (type.equals("long")) {
          type = "Long";
        } else if (type.equals("float")) {
          type = "Float";
        } else if (type.equals("double")) {
          type = "Double";
        } else if (type.equals("boolean")) {
          type = "Boolean";
        }

        sw.println("if (s.equals(\"" + p + "\")) {");
        sw.indent();
        sw.println("Object old = get(s);");
             
        sw.println("((" + typeName + ")bean)." + s + "((" + type + ")obj);");
        sw.println("notifyPropertyChanged(s, val, old);");
        sw.println("return (X)old;");
        sw.outdent();
        sw.println("}");
      }
      sw.println("return super.set(s, val);");
      sw.outdent();
      sw.println("}");
    }

    protected List<JMethod> findGetters(JClassType cls) {
      List<JMethod> methods = new ArrayList<JMethod>();
      addGetters(cls, methods);
      return methods;
    }

    protected void addGetters(JClassType cls, List<JMethod> methods) {
      if (cls == null) {
        return;
      }
      // ignore methods of Object
      if (cls.isInterface() != null || cls.getSuperclass() != null) {
        addGetters(cls.getSuperclass(), methods);
        for (JMethod m : cls.getMethods()) {
          if (m.isPublic() || m.isProtected()) {
            String name = m.getName();
            if ((name.matches("get.*") || name.matches("is.*")) && m.getParameters().length == 0) {
              methods.add(m);
            }
          }
        }
      }

    }

    protected List<JMethod> findSetters(JClassType cls) {
      List<JMethod> methods = new ArrayList<JMethod>();
      addSetters(cls, methods);
      return methods;
    }

    protected void addSetters(JClassType cls, List<JMethod> methods) {
      if (cls.getSuperclass() != null) {
        addSetters(cls.getSuperclass(), methods);
      }
      for (JMethod m : cls.getMethods()) {
        if (m.isPublic() || m.isProtected()) {
          String name = m.getName();
          if (name.matches("set.*") && m.getParameters().length == 1) {
            methods.add(m);
          }
        }
      }
    }


}

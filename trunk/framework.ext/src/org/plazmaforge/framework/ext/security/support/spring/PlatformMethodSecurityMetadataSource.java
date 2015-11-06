/* Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.plazmaforge.framework.ext.security.support.spring;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aopalliance.intercept.MethodInvocation;
import org.plazmaforge.framework.ext.service.GenericEntityService;
import org.plazmaforge.framework.util.StringUtils;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.method.AbstractFallbackMethodSecurityMetadataSource;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

/**
 * Stores a list of <tt>ConfigAttribute</tt>s for a method or class signature.
 *
 * <p>
 * This class is the preferred implementation of {@link MethodSecurityMetadataSource} for XML-based
 * definition of method security metadata. To assist in XML-based definition, wildcard support
 * is provided.
 * </p>
 *
 * @author Ben Alex
 * @since 2.0
 */

//FIXED: [ohapon] MapBasedMethodSecurityMetadataSource
/*
 * RegisteredMethod -> String
 * addSecureMethod(String name, List<ConfigAttribute> attr) -> public 
 */
public class PlatformMethodSecurityMetadataSource extends AbstractFallbackMethodSecurityMetadataSource implements BeanClassLoaderAware {

    
    
    
    //~ Instance fields ================================================================================================
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     *  Map from registered method to ConfigAttribute list 
     *  * <Full method name (Java class and method name), Attributes)>
     */
    protected final Map<String, List<ConfigAttribute>> methodMap = new HashMap<String, List<ConfigAttribute>>();

    /** 
     * Map from registered method to name pattern used for registration 
     * <Full method name (Java class and method name), Full mapped name (Java class and mapped method)>
     * For example: <'mypackage.MyClass.myMethod', 'mypackage.MyClass.myM*'> 
     */
    private final Map<String, String> nameMap = new HashMap<String, String>();

    //~ Methods ========================================================================================================

    
    //  NEW
    private Map<String, String> interfaceMethodMap = new HashMap<String, String>();
    
    
    private String replacerClassName = "org.plazmaforge.framework.service.erm.ERMGenericEntityService";
    
    
    public PlatformMethodSecurityMetadataSource() {
	super();
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    /**
     * Implementation does not support class-level attributes.
     */
    protected Collection<ConfigAttribute> findAttributes(Class<?> clazz) {
        return null;
    }

    /**
     * Will walk the method inheritance tree to find the most specific declaration applicable.
     */
    protected Collection<ConfigAttribute> findAttributes(Method method, Class<?> targetClass) {
        if (targetClass == null) {
            return null;
        }

        return findAttributesSpecifiedAgainst(method, targetClass);
    }

    private List<ConfigAttribute> findAttributesSpecifiedAgainst(Method method, Class<?> clazz) {
        String registeredMethod = getRegisteredMethodKey(clazz, method);
        if (methodMap.containsKey(registeredMethod)) {
            return (List<ConfigAttribute>) methodMap.get(registeredMethod);
        }
        // Search superclass
        if (clazz.getSuperclass() != null) {
            return findAttributesSpecifiedAgainst(method, clazz.getSuperclass());
        }
        return null;
    }

    /**
     * Add configuration attributes for a secure method. Method names can end or start with <code>&#42</code>
     * for matching multiple methods.
     *
     * @param name type and method name, separated by a dot
     * @param attr the security attributes associated with the method
     */
    public void addSecureMethod(String name, List<ConfigAttribute> attr) {
        int lastDotIndex = name.lastIndexOf(".");

        if (lastDotIndex == -1) {
            throw new IllegalArgumentException("'" + name + "' is not a valid method name: format is FQN.methodName");
        }

        String methodName = name.substring(lastDotIndex + 1);
        Assert.hasText(methodName, "Method not found for '" + name + "'");

        String typeName = name.substring(0, lastDotIndex);
        
        //Class<?> type = ClassUtils.resolveClassName(typeName, this.beanClassLoader);
        
        Class<?> type = resolveClassName(typeName);
        if (type == null) {
            type = getReplacerClass(typeName);
        }
        if (type == null) {
            return;
        }

        addSecureMethod(type, typeName, methodName, attr);
    }

    /**
     * Add configuration attributes for a secure method. Mapped method names can end or start with <code>&#42</code>
     * for matching multiple methods.
     *
     * @param javaType target interface (or super interface) or class (or super class) the security configuration attribute applies to
     * @param javaTypeName class name of <code>javaType</code>. Class can be virtual. 
     * @param mappedName mapped method name, which the javaType has declared or inherited
     * @param attr required authorities associated with the method
     */
    public void addSecureMethod(Class<?> javaType, String javaTypeName, String mappedName, List<ConfigAttribute> attr) {
	
	//OHA
	//For example: 'mypackage.MyClass.myM*'
        //String name = javaType.getName() + '.' + mappedName;
        String name = javaTypeName + '.' + mappedName;

        if (logger.isDebugEnabled()) {
            logger.debug("Request to add secure method [" + name + "] with attributes [" + attr + "]");
        }

        Method[] methods = javaType.getMethods();
        List<Method> matchingMethods = new ArrayList<Method>();

        for (Method m : methods) {
            if (m.getName().equals(mappedName) || isMatch(m.getName(), mappedName)) {
                matchingMethods.add(m);
            }
        }

        if (matchingMethods.isEmpty()) {
            throw new IllegalArgumentException("Couldn't find method '" + mappedName + "' on '" + javaType + "'");
        }

        // register all matching methods
        for (Method method : matchingMethods) {
            
            //OHA
            //String registeredMethod = getRegisteredMethodKey(javaType, method);
            String registeredMethod = getRegisteredMethodKey(javaTypeName, method.getName());
            
            String regMethodName = (String) this.nameMap.get(registeredMethod);

            if ((regMethodName == null) || (!regMethodName.equals(name) && (regMethodName.length() <= name.length()))) {
                // no already registered method name, or more specific
                // method name specification now -> (re-)register method
                if (regMethodName != null) {
                    logger.debug("Replacing attributes for secure method [" + method + "]: current name [" + name
                        + "] is more specific than [" + regMethodName + "]");
                }

                this.nameMap.put(registeredMethod, name);
                addRegisteredMethod(registeredMethod, attr);
            } else {
                logger.debug("Keeping attributes for secure method [" + method + "]: current name [" + name
                    + "] is not more specific than [" + regMethodName + "]");
            }
        }
    }

    /**
     * Adds configuration attributes for a specific method, for example where the method has been
     * matched using a pointcut expression. If a match already exists in the map for the method, then
     * the existing match will be retained, so that if this method is called for a more general pointcut
     * it will not override a more specific one which has already been added.
     * <p>
     * This method should only be called during initialization of the {@code BeanFactory}.
     */
    public void addSecureMethod(Class<?> javaType, Method method, List<ConfigAttribute> attr) {
        String key = getRegisteredMethodKey(javaType, method);

        if (methodMap.containsKey(key)) {
            logger.debug("Method [" + method + "] is already registered with attributes [" + methodMap.get(key) + "]");
            return;
        }

        methodMap.put(key, attr);
    }

    /**
     * Add configuration attributes for a secure method.
     *
     * @param method the method to be secured
     * @param attr required authorities associated with the method
     */
    private void addRegisteredMethod(String method, List<ConfigAttribute> attr) {
        Assert.notNull(method, "RegisteredMethod required");
        Assert.notNull(attr, "Configuration attribute required");
        if (logger.isInfoEnabled()) {
            logger.info("Adding secure method [" + method + "] with attributes [" + attr + "]");
        }
        this.methodMap.put(method, attr);
    }

    /**
     * Obtains the configuration attributes explicitly defined against this bean.
     *
     * @return the attributes explicitly defined against this bean
     */
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();

        for (List<ConfigAttribute> attributeList : methodMap.values()) {
            allAttributes.addAll(attributeList);
        }

        return allAttributes;
    }

    /**
     * Return if the given method name matches the mapped name. The default implementation checks for "xxx" and
     * "xxx" matches.
     *
     * @param methodName the method name of the class
     * @param mappedName the name in the descriptor
     *
     * @return if the names match
     */
    private boolean isMatch(String methodName, String mappedName) {
        return (mappedName.endsWith("*") && methodName.startsWith(mappedName.substring(0, mappedName.length() - 1)))
        || (mappedName.startsWith("*") && methodName.endsWith(mappedName.substring(1, mappedName.length())));
    }

    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        Assert.notNull(beanClassLoader, "Bean class loader required");
        this.beanClassLoader = beanClassLoader;
    }

    /**
     * @return map size (for unit tests and diagnostics)
     */
    public int getMethodMapSize() {
        return methodMap.size();
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Collection<ConfigAttribute> getAttributes(Object object) {
        if (object instanceof MethodInvocation) {
            MethodInvocation mi = (MethodInvocation) object;
            Object target = mi.getThis();
            Class<?> targetClass = null;

            if (target != null) {
                targetClass = target instanceof Class<?> ? (Class<?>)target : AopProxyUtils.ultimateTargetClass(target);
            }

            String targetClassName = targetClass.getName();
            
            // STUB
            if (replacerClassName != null && replacerClassName.equals(replacerClassName)) {
        	if (target instanceof GenericEntityService) {
        	    GenericEntityService service = (GenericEntityService) target;
        	    Class<?> entityClass = service.getEntityClass();
        	    if (entityClass != null) {
        		String entityClassName = entityClass.getName();
        		targetClassName = StringUtils.replaceAll(entityClassName, ".beans.", ".services.") + "Service";
        	    }
        	    //"org.plazmaforge.bsolution.base.common.beans"
        	    //"org.plazmaforge.bsolution.base.common.services"
        	}
            }
            
            return getAttributes(mi.getMethod(), targetClass, targetClassName);
        }

        throw new IllegalArgumentException("Object must be a non-null MethodInvocation");
    }

    public Collection<ConfigAttribute> getAttributes(Method method, Class<?> targetClass) {
	return getAttributes(method, targetClass, null);
    }
    
    public Collection<ConfigAttribute> getAttributes(Method method, Class<?> targetClass, String targetClassName) {
	
	String methodName = method.getName();
	//Class<?> thisClass = targetClass;
	//String targetClassName = thisClass.getName();
	//String targetClassName = targetClass.getName();
	
	if (targetClassName == null) {
	    targetClassName = targetClass.getName();
	}
	
	
	String fullClassMethodName = getFullMethodName(targetClassName, methodName);

	// Get full interface method name from interfaceMethodMap
        String key = getInterfaceMethodName(fullClassMethodName);
        if (key != null) {
            // Get attributes by full interface method name
            return (List<ConfigAttribute>) methodMap.get(key);
        }

        // NEW
        // Get attributes by full class method name
        List<ConfigAttribute> value = methodMap.get(fullClassMethodName);
        if (value != null) {
            return value;
        }
        

        //String[] names = getVariantMethodNames(targetClass, methodName);
        String[] names = getInterfaceMethodNames(targetClass, methodName);
        for (int i = 0; i < names.length; i++) {
            value = methodMap.get(names[i]);
            if (value == null) {
                continue;
            }
            interfaceMethodMap.put(fullClassMethodName, names[i]);
            return value;
        }

        return null;
    }

 
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 
    // NEW
    protected String getRegisteredMethodKey(Class<?> clazz, Method method) {
	return getRegisteredMethodKey(clazz.getName(), method.getName());
    }

    protected String getRegisteredMethodKey(String className, String methodName) {
	return className + "." + methodName;
    }

    // NEW
    /**
     * Return all variant of methods (class and all interfaces)
     */
    protected String[] getVariantMethodNames(Class<?> klass, String methodName) {
        List<String> names = new ArrayList<String>();
        
        // Add class method
        names.add(getFullMethodName(klass.getName(), methodName));
        
        // Get all interfaces
        Class<?>[] interfaces = klass.getInterfaces();
        
        // Add all methods of interfaces
        if (interfaces != null) {
            for (int i = 0; i < interfaces.length; i++) {
                names.add(getFullMethodName(interfaces[i].getName(), methodName));
            }
        }
        return (String[]) names.toArray(new String[0]);
    }

    protected String[] getInterfaceMethodNames(Class<?> klass, String methodName) {
        List<String> names = new ArrayList<String>();
        
        // Add class method
        //names.add(getFullMethodName(klass.getName(), methodName));
        
        // Get all interfaces
        Class<?>[] interfaces = klass.getInterfaces();
        
        // Add all methods of interfaces
        if (interfaces != null) {
            for (int i = 0; i < interfaces.length; i++) {
                names.add(getFullMethodName(interfaces[i].getName(), methodName));
            }
        }
        return (String[]) names.toArray(new String[0]);
    }


    // NEW
    protected String getFullMethodName(Class<?> klass, String methodName) {
        return getFullMethodName(klass.getName(), methodName);
    }

    // NEW
    protected String getFullMethodName(String className, String methodName) {
        return className + "." + methodName;
    }

    // NEW
    protected String getInterfaceMethodName(String classMethodName) {
        return this.interfaceMethodMap.get(classMethodName);
    }    
    
    // NEW
    public void reset() {
	methodMap.clear();
	nameMap.clear();
	interfaceMethodMap.clear();
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private Class<?> getReplacerClass(String className) {
	// STUB
	if (className == null || !className.endsWith("Service")) {
	    return null;
	}
	return resolveClassName(replacerClassName);
    }
    
    private Class<?> resolveClassName(String className) {
	try {
	    return ClassUtils.resolveClassName(className, this.beanClassLoader);
	} catch (Exception ex) {
	    return null;
	}
    }
}

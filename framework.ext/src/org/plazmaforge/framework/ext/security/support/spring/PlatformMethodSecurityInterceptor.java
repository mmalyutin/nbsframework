/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
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
package org.plazmaforge.framework.ext.security.support.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.aopalliance.intercept.MethodInvocation;

import org.plazmaforge.framework.ext.security.UserContext;
import org.plazmaforge.framework.ext.security.UserContextHolder;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;


/**
 * Platform Security Interceptor
 * General Initialize method is setAttributes();
 * 
 */
public class PlatformMethodSecurityInterceptor extends MethodSecurityInterceptor {

    /**
     * Global static attributes 
     */
    private static Map<String, String> attributes;
    
    /**
     * Global static init flag of attributes 
     */
    private static boolean initAttributes;
    
    
    /** 
     * Special static list of interceptors
     * After creating new interceptor we add it to list   
     */
    private static List<PlatformMethodSecurityInterceptor> interceptors = new ArrayList<PlatformMethodSecurityInterceptor>();

    
    
    private boolean initInterceptor;
    

    public PlatformMethodSecurityInterceptor() {
	interceptors.add(this);
    }

    
    /**
     * Set attributes to interceptor
     * General initialize method
     * @param attrs
     */
    public static void setAttributes(Map<String, String> attrs) {
	attributes = attrs;
	notifyInterceptors(initAttributes);
	initAttributes = true;
    }

    /**
     * Notify interceptors after set attributes
     * @param isResetAttributes
     */
    private static void notifyInterceptors(boolean isResetAttributes) {
	for (PlatformMethodSecurityInterceptor interceptor:  interceptors) {
	    interceptor.reset(isResetAttributes);
	}
    }

    /**
     * Reset interceptor
     * @param isResetAttributes
     */
    protected void reset(boolean isResetAttributes) {
	initInterceptor = false;
	
	// If not reset attributes then return
	if (!isResetAttributes) {
	    return;
	}
	
	PlatformMethodSecurityMetadataSource source = getSource();
	if (source == null) {
	    return;
	}
	source.reset();
    }

    public Object invoke(MethodInvocation mi) throws Throwable {

	if (!initInterceptor) {
	    initSource();
	}
	initSecurityContext();
	
	return super.invoke(mi);
    }

    private void initSecurityContext() {
	UserContext ctx = UserContextHolder.getContext();
	UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(ctx.getUsername(), ctx.getPassword());

	SecurityContextImpl secureContext = new SecurityContextImpl();
	secureContext.setAuthentication(auth);
	
	SecurityContextHolder.setContext(secureContext);
    }
    

    /**
     * Initialize source
     */
    private void initSource() {
	initInterceptor = true;
	
	PlatformMethodSecurityMetadataSource source = getSource();
	
	if (attributes == null || attributes.isEmpty()) {
	    return;
	}
	
	Set<String> keys = attributes.keySet();

	for (String key : keys) {
	    source.addSecureMethod(key, createAtrtributes(attributes.get(key)));
	}

    }
    
    protected PlatformMethodSecurityMetadataSource getSource() {
	MethodSecurityMetadataSource obj = getSecurityMetadataSource();
	if (obj == null || !(obj instanceof PlatformMethodSecurityMetadataSource)) {
	    return null;
	}
	return (PlatformMethodSecurityMetadataSource) obj;
    }
    
     private List<ConfigAttribute> createAtrtributes(String roles) {
	List<ConfigAttribute> atrtributes = new ArrayList<ConfigAttribute>();
	StringTokenizer t = new StringTokenizer(roles);
	while (t.hasMoreTokens()) {
	    String attr = t.nextToken();
	    atrtributes.add(createAttribute(attr));
	}
	return atrtributes;
    }

    private ConfigAttribute createAttribute(String attr) {
	return new SecurityConfig(attr);
    }

    
}

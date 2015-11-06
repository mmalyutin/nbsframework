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
package org.plazmaforge.framework.ext.security;

public class UserContextHolder {

    private static boolean isGlobalContext = false;
    
    private static ThreadLocal<UserContext> contextHolder = new ThreadLocal<UserContext>();
    
    private static UserContext context;

    private UserContextHolder() {
    }

    public static UserContext getContext() {
	if (isGlobalContext) {
	    if (context == null) {
		context = new UserContext();
	    }
	    return context;
	}
	if (contextHolder.get() == null) {
	    contextHolder.set(new UserContext());
	}
        return contextHolder.get();
    }

    public static void setContext(UserContext context) {
	if (isGlobalContext) {
	    UserContextHolder.context = context;
	    return;
	}
	contextHolder.set(context);
    }

}

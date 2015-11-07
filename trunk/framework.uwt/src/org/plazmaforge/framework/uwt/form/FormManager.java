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

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.core.data.object.Creator;
import org.plazmaforge.framework.uwt.UWTException;

public class FormManager {

    
    
    /**
     * Form stack
     */
    private static List<IForm<?>> forms = new ArrayList<IForm<?>>();
    
    /**
     * Form creator. Create form by special name (path/class name/...)
     */
    private static Creator formCreator;
    
    /**
     * Provide open/close form action
     */
    private static FormProvider formProvider;
    
    
    public static Creator getFormCreator() {
        return formCreator;
    }

    public static void setFormCreator(Creator formCreator) {
        FormManager.formCreator = formCreator;
    }

    public static FormProvider getFormProvider() {
        return formProvider;
    }

    public static void setFormProvider(FormProvider formProvider) {
	FormManager.formProvider = formProvider;
    }
    
    
    /**
     * Open form by type (class name by default)
     * @param type
     */
    public static void open(String type) {
	if (type == null) {
	    throw new UWTException("Can't open form. Form type is null.");
	}
	if (formProvider == null) {
	    throw new UWTException("Can't open form. FormProvider is not initialized.");
	}	
	IForm<?> form = getForm(type);
	if (form == null) {
	    form = createForm(type);
	    if (form == null) {
		throw new UWTException("Can't create form by type '" + type + "'.");
	    }
	}
	doOpen(form);
    }
    

    /**
     * Open the form
     * @param form
     */
    public static void open(IForm<?> form) {
	if (form == null) {
	    throw new UWTException("Can't open form. Form is null.");
	}
	if (formProvider == null) {
	    throw new UWTException("Can't open form. FormProvider is not initialized.");
	}	
	doOpen(form);
    }
    
    
    private static void doOpen(IForm<?> form) {
	if (form == null || formProvider == null) {
	    return;
	}
	if (!FormManager.forms.contains(form)) {
	    FormManager.forms.add(form);
	}
	
	form.init();
	form.load();
	formProvider.open(form); //TODO use Callback
    }    
    
    
    /**
     * Close form by type (class name by default)
     * @param type
     */
    public static void close(String type) {
	if (type == null) {
	    throw new UWTException("Can't close form. Form type is null.");
	}
	if (formProvider == null) {
	    throw new UWTException("Can't close form. FormProvider is not initialized.");
	}	
	IForm<?> form = getForm(type);
	if (form == null) {
	    throw new UWTException("Can't close form. Form not found by type '" + type + "'." );
	}
	doClose(form);
    }
        
    /**
     * Close the form
     * @param form
     */
    public static void close(IForm<?> form) {
	if (form == null) {
	    throw new UWTException("Can't close form. Form is null.");
	}
	if (formProvider == null) {
	    throw new UWTException("Can't close form. FormProvider is not initialized.");
	}	
	doClose(form);
    }

    private static void doClose(IForm<?> form) {
   	if (form == null || formProvider == null) {
   	    return;
   	}
   	formProvider.close(form); // TODO use Callback to destroy form and remove from stack
   	FormManager.forms.remove(form);
    }
    
    public static List<IForm<?>> getForms() {
	return forms;
    }
    
    public static IForm<?> getForm(String type) {
	if (type == null) {
	    return null;
	}
	for (IForm<?> form: forms) {
	    String formMarker = getFormMarker(form);  
	    if (type.equals(formMarker)) {
		return form;
	    }
	}
	return null;
    }

    public static IForm<?> createForm(String type) {
	if (type == null) {
	    // TODO: ERROR: Can't create form. Form type is null.
	    return null;
	}
	if (formCreator == null) {
	    // TODO: ERROR: Can't create form. FormCreator is not initialized.
	    return null;
	}
	return (IForm<?>) formCreator.create(type);
    }
    
    private static String getFormMarker(IForm<?> form) {
	return form == null ? null : form.getFormMarker();
    }
    

}

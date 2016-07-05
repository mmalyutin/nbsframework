/*
 * Copyright (C) 2012-2015 Oleh Hapon ohapon@users.sourceforge.net
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

package org.plazmaforge.framework.script;

public class EvaluateContext {

    enum NullTranslator {
	UNKNOWN,	// Standard NULL
	EMPTY,		// Empty, like 0, ""
	EXCEPTION,	// Throw exception (NullPointerException) 
	INVALID		// Invalid/error value
    }

    private static final EvaluateContext defaultContext = new EvaluateContext(); 
    
    
    
    private NullTranslator nullTranslator;
    
    public EvaluateContext() {
	nullTranslator = NullTranslator.EMPTY;
    }

    public static EvaluateContext getDefaultContext() {
	return defaultContext;
    }
    
    public NullTranslator getNullTranslator() {
        return nullTranslator;
    }

    public void setNullTranslator(NullTranslator nullTranslator) {
        this.nullTranslator = nullTranslator;
    }
    
    public boolean isNullUnknown() {
	return nullTranslator == NullTranslator.UNKNOWN;
    }

    public boolean isNullEmpty() {
	return nullTranslator == NullTranslator.EMPTY;
    }

    public boolean isNullException() {
	return nullTranslator == NullTranslator.EXCEPTION;
    }
    
    public boolean isNullInvalid() {
	return nullTranslator == NullTranslator.INVALID;
    }
    
}

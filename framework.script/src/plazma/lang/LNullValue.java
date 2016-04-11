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

package plazma.lang;

public class LNullValue extends LValue {

    public LNullValue() {
    }


    
    // &&, and
    @Override
    public LValue _and(LValue a, LValue b) {
	// Only for NULL 
	if (!a.isNull()) {
	    raiseIllegalOperatorException("Must be only NULL");
	}
	
	LValue result = nullResult(a, "&&", b);
	if (result != null) {
	    return result; 
	}

	if (a == LValue.NULL && b  == LValue.NULL) {
	    return LBoolean.FALSE;
	}
	
	//IMPORTANT! b
	return b._and(a, b);	    
    }

    // ||, or
    @Override
    public LValue _or(LValue a, LValue b) {
	// Only for NULL 
	if (!a.isNull()) {
	    raiseIllegalOperatorException("Must be only NULL");
	}

	LValue result = nullResult(a, "||", b);
	if (result != null) {
	    return result; 
	}

	if (a == LValue.NULL && b  == LValue.NULL) {
	    return LBoolean.FALSE;
	}
	
	//IMPORTANT! b
	return b._or(a, b);	    
    }    
    
    // <
    public LValue _lt(LValue a, LValue b) {
	// Only for NULL 
	if (!a.isNull()) {
	    raiseIllegalOperatorException("Must be only NULL");
	}
	LValue result = nullResult(a, "<", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LBoolean.FALSE;
	}

	//IMPORTANT! b
	return b._lt(a, b);
    }
    
    // <=
    public LValue _lte(LValue a, LValue b) {
	// Only for NULL 
	if (!a.isNull()) {
	    raiseIllegalOperatorException("Must be only NULL");
	}
	LValue result = nullResult(a, "<=", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LBoolean.TRUE;
	}

	//IMPORTANT! b
	return b._lte(a, b);
    }

    // >
    public LValue _gt(LValue a, LValue b) {
	// Only for NULL 
	if (!a.isNull()) {
	    raiseIllegalOperatorException("Must be only NULL");
	}
	LValue result = nullResult(a, ">", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LBoolean.FALSE;
	}

	//IMPORTANT! b
	return b._gt(a, b);
    }
    
    // >=
    public LValue _gte(LValue a, LValue b) {
	// Only for NULL 
	if (!a.isNull()) {
	    raiseIllegalOperatorException("Must be only NULL");
	}
	LValue result = nullResult(a, ">=", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LBoolean.TRUE;
	}

	//IMPORTANT! b
	return b._gte(a, b);
    }
    
    ////
    
    // +
    @Override
    public LValue _add(LValue a, LValue b) {
	
	// Only for NULL 
	if (!a.isNull()) {
	    raiseIllegalOperatorException("Must be only NULL");
	}
	LValue result = nullResult(a, "+", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	
	//IMPORTANT! b
	return b._add(a, b);
	
    }

    // -
    @Override
    public LValue _sub(LValue a, LValue b) {

	// Only for NULL 
	if (!a.isNull()) {
	    raiseIllegalOperatorException("Must be only NULL");
	}
	LValue result = nullResult(a, "-", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	
	//IMPORTANT! b
	return b._sub(a, b);
	
    }
    
    // *
    @Override
    public LValue _mul(LValue a, LValue b) {
	
	// Only for NULL 
	if (!a.isNull()) {
	    raiseIllegalOperatorException("Must be only NULL");
	}
	LValue result = nullResult(a, "*", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	
	//IMPORTANT! b
	return b._mul(a, b);
    }
    
    // /
    @Override
    public LValue _div(LValue a, LValue b) {
	
	// Only for NULL 
	if (!a.isNull()) {
	    raiseIllegalOperatorException("Must be only NULL");
	}
	LValue result = nullResult(a, "/", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	
	//IMPORTANT! b
	return b._div(a, b);
    }
    
    // ^
    @Override
    public LValue _pow(LValue a, LValue b) {
	
	// Only for NULL 
	if (!a.isNull()) {
	    raiseIllegalOperatorException("Must be only NULL");
	}
	LValue result = nullResult(a, "^", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	
	//IMPORTANT! b
	return b._pow(a, b);
    }    
    

    // %
    @Override
    public LValue _mod(LValue a, LValue b) {
	
	// Only for NULL 
	if (!a.isNull()) {
	    raiseIllegalOperatorException("Must be only NULL");
	}
	LValue result = nullResult(a, "%", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	
	//IMPORTANT! b
	return b._mod(a, b);
    }
    
}

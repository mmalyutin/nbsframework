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

/**
 * 
 */
package org.plazmaforge.framework.script.lang;

import java.util.List;


public class LNumber extends LValue {

    
    public static final LValue NaN = new LNaN();
    public static final LValue Infinity = new LInfinity();
    
    
    // NaN
    public static class LNaN extends LNumber {

	public LNaN() {
	    super(Double.NaN);
	}
	
    }
    
    // Infinity
    public static class LInfinity extends LNumber {

	public LInfinity() {
	    super(Double.POSITIVE_INFINITY);
	}
	
    }
    
    
    /**
     * @param value
     */
    public LNumber(Number value) {
	super(Type.NUMBER, value);
    }

    @Override
    protected boolean isEqualsValue(LValue that) {
	if (that == null || getValue() == null) {
	    return false;
	}
	if (that.isNumber()) {
	    return compareNumberValue(this, that) == 0;
	}
	
        /*
        if (that.isNumber()) {
            double diff = Math.abs(this.asDouble() - that.asDouble());
            return diff < 0.00000000001;
        }
        */
	
	return super.isEqualsValue(that);
    }
    
    protected int compareNumberValue(LValue v1, LValue v2) {
	if (v1 == null && v2 == null) {
	    return 0;
	}
	if (v1 == null) {
	    return -1;
	}
	if (v2 == null) {
	    return 1;
	}
	
	Number n1 = v1.asNumber();
	Number n2 = v2.asNumber();

	if (n1 == null && n2 == null) {
	    return 0;
	}
	if (n1 == null) {
	    return -1;
	}
	if (n2 == null) {
	    return 1;
	}

	// Same type
	if (isEqualsValueType(n1, n2)) {
	    return ((Comparable) n1).compareTo(n2);
	}

	// Double
	Double d1 = v1.asDouble();
	Double d2 = v2.asDouble();
	if (isZero(d1) && isZero(d2)) { // -0.0, 0.0
	    return 0;
	}
	return d1.compareTo(d2);

    }
    
    @Override
    protected int compareValueTo(LValue that) {
        if (that.isNumber()) {
            if(this.equals(that)) { // TODO: ???
                return 0;
            } else {
                return this.asDouble().compareTo(that.asDouble());
            }
        }
        return super.compareValueTo(that);
    }

    protected boolean isZero(Double value) {
	return value == null ? false : (value == 0.0 || value == -0.0); 
    }
    
    
    // <
    @Override
    public LValue _lt(LValue a, LValue b) {
	LValue result = nullResult(a, "<", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL || b == LValue.NULL) {
	    return new LBoolean(ltNull(a, b));
	}	
	
	if (!a.isNumber() || !b.isNumber()) {
	    return super._lt(a, b);
	}
	
	//return new LBoolean(a.asDouble() < b.asDouble());
	
	int compare = compareNumberValue(a, b);
	return new LBoolean(compare == -1);
    }
    
    // <=
    @Override
    public LValue _lte(LValue a, LValue b) {
	LValue result = nullResult(a, "<=", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL || b == LValue.NULL) {
	    return new LBoolean(lteNull(a, b));
	}	
	
	if (!a.isNumber() || !b.isNumber()) {
	    return super._lte(a, b);
	}
	
	//return new LBoolean(a.asDouble() <= b.asDouble());
	
	int compare = compareNumberValue(a, b);
	return new LBoolean(compare == -1 || compare == 0);
    }

    // >
    @Override
    public LValue _gt(LValue a, LValue b) {
	LValue result = nullResult(a, ">", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL || b == LValue.NULL) {
	    return new LBoolean(gtNull(a, b));
	}	
	
	if (!a.isNumber() || !b.isNumber()) {
	    return super._gt(a, b);
	}
	
	//return new LBoolean(a.asDouble() > b.asDouble());
	
	int compare = compareNumberValue(a, b);
	return new LBoolean(compare == 1);
    }
    
    // >=
    @Override
    public LValue _gte(LValue a, LValue b) {
	LValue result = nullResult(a, ">=", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL || b == LValue.NULL) {
	    return new LBoolean(gteNull(a, b));
	}	
	
	if (!a.isNumber() || !b.isNumber()) {
	    return super._gt(a, b);
	}
	
	//return new LBoolean(a.asDouble() >= b.asDouble());
	
	int compare = compareNumberValue(a, b);
	return new LBoolean(compare == 1 || compare == 0);
    }
    
    //////
    
    // +
    @Override
    public LValue _add(LValue a, LValue b) {
	
	LValue result = nullResult(a, "+", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	if (a == LValue.NULL) {
	    a = new LNumber(0);
	}
	if (b == LValue.NULL) {
	    b = new LNumber(0);
	}
	
	if (!a.isNumber() || !b.isNumber()) {
	    return super._add(a, b);
	}
	
	// Integer
	if (a.isInteger() && b.isInteger()) {
	    return new LNumber(a.asInteger() + b.asInteger());
	}
	
	// BigDecimal
	if (a.isBigDecimal() && b.isBigDecimal()) {
	    return new LNumber(a.asBigDecimal().add(b.asBigDecimal()));
	}
	
	return new LNumber(a.asDouble() + b.asDouble());
    }
    
    
    
    
    // -
    @Override
    public LValue _sub(LValue a, LValue b) {
	
	LValue result = nullResult(a, "-", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	if (a == LValue.NULL) {
	    a = new LNumber(0);
	}
	if (b == LValue.NULL) {
	    b = new LNumber(0);
	}
	
	if (!a.isNumber() || !b.isNumber()) {
	    return super._sub(a, b);
	}
	
	// Integer
	if (a.isInteger() && b.isInteger()) {
	    return new LNumber(a.asInteger() - b.asInteger());
	}
	
	// BigDecimal
	if (a.isBigDecimal() && b.isBigDecimal()) {
	    return new LNumber(a.asBigDecimal().subtract(b.asBigDecimal()));
	}
	
	return new LNumber(a.asDouble() - b.asDouble());
    }
    
    // *
    @Override
    public LValue _mul(LValue a, LValue b) {
	
	LValue result = nullResult(a, "*", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	if (a == LValue.NULL) {
	    a = new LNumber(0);
	}
	if (b == LValue.NULL) {
	    b = new LNumber(0);
	}
	
	if (!a.isNumber() || !b.isNumber()) {
	    return super._mul(a, b);
	}
	
	// Integer
	if (a.isInteger() && b.isInteger()) {
	    return new LNumber(a.asInteger() * b.asInteger());
	}
	
	// BigDecimal
	if (a.isBigDecimal() && b.isBigDecimal()) {
	    return new LNumber(a.asBigDecimal().multiply(b.asBigDecimal()));
	}
	
	
	return new LNumber(a.asDouble() * b.asDouble());
    }
    
    // /
    @Override
    public LValue _div(LValue a, LValue b) {
	
	LValue result = nullResult(a, "/", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	if (a == LValue.NULL) {
	    a = new LNumber(0);
	}
	if (b == LValue.NULL) {
	    b = new LNumber(0);
	}

	if (!a.isNumber() || !b.isNumber()) {
	    return super._div(a, b);
	}
	
	// BigDecimal
	if (a.isBigDecimal() && b.isBigDecimal()) {
	    return new LNumber(a.asBigDecimal().divide(b.asBigDecimal()));
	}
	
	// Integer ???
	return new LNumber(a.asDouble() / b.asDouble());
    }
    
    // ^
    @Override
    public LValue _pow(LValue a, LValue b) {
	
	LValue result = nullResult(a, "^", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	if (a == LValue.NULL) {
	    a = new LNumber(0);
	}
	if (b == LValue.NULL) {
	    b = new LNumber(0);
	}

	if (!a.isNumber() || !b.isNumber()) {
	    return super._pow(a, b);
	}
	
	// Integer ???
	return new LNumber(Math.pow(a.asDouble(), b.asDouble()));
    }
    
    // %
    @Override
    public LValue _mod(LValue a, LValue b) {
	
	LValue result = nullResult(a, "%", b);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LValue.NULL;
	}
	if (a == LValue.NULL) {
	    a = new LNumber(0);
	}
	if (b == LValue.NULL) {
	    b = new LNumber(0);
	}
	
	if (!a.isNumber() || !b.isNumber()) {
	    return super._mod(a, b);
	}
	
	return new LNumber(a.asDouble() % b.asDouble());
    }

    // unary +
    @Override
    public LValue _unaryPlus(LValue a) {
	LValue result = nullResult("+", a);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL) {
	    return LValue.NULL;
	}
	
        if (!a.isNumber()) {
            raiseIllegalOperatorException("+", a);
        }
        
        // Integer: returns same value
        if (a.isInteger()) {
            return new LNumber(a.asInteger());

        }

        // BigDecimal
	if (a.isBigDecimal()) {
	    return new LNumber(a.asBigDecimal());
	}
        
        // Double: returns same value
        return new LNumber(a.asDouble());
    }
    
    // unary -
    @Override
    public LValue _unaryMinus(LValue a) {
	LValue result = nullResult("-", a);
	if (result != null) {
	    return result; 
	}
	
	if (a == LValue.NULL) {
	    return LValue.NULL;
	}
	
        if (!a.isNumber()) {
            raiseIllegalOperatorException("-", a);
        }
        
        // Integer
        if (a.isInteger()) {
            return new LNumber(-a.asInteger());
        }
        
	// BigDecimal
	if (a.isBigDecimal()) {
	    return new LNumber(a.asBigDecimal().negate());
	}
        

        return new LNumber(-a.asDouble());
    }
    
    public LValue _invoke(String method, List<LValue> parameters) {
	if ("isNaN".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LBoolean(asDouble().isNaN());
	} else if ("isInfinity".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LBoolean(asDouble().isInfinite()); // isInfinitE - > isInfinitY
	} else if ("toInteger".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asInteger());
	} else if ("toLong".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asLong());
	} else if ("toFloat".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asDouble());
	} else if ("toDouble".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(asDouble());
	}
	raiseIllegalMethodException(method);
	return null;
    }
}

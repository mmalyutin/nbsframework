package org.plazmaforge.framework.script.lang;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.plazmaforge.framework.script.EvaluateContext;
import org.plazmaforge.framework.script.PropertyAccessor;


public class LValue implements Comparable<LValue> {

    public static String[] STANDARD_PACKAGES = {"java.lang", "java.util", "java.math"};
    
    public enum Type {NUMBER, STRING, BOOLEAN, LIST, SET, MAP, DATE_TIME, DATE, TIME, DURATION, PERIOD, OBJECT, EXTERNAL_OBJECT}
    
    public static final LValue NULL = new LNullValue();
    public static final LValue VOID = new LValue();
    public static final LValue INVALID = new LValue();    
    public static final LValue BREAK = new LValue();
    public static final LValue CONTINUE = new LValue();

    private Type type;
    
    private Object value;

    protected LValue() {
        // private constructor: only used for NULL and VOID
        value = new Object();
    }

    protected LValue(Object value) {
	this(null, value);
    }
    
    protected LValue(Type type, Object value) {
        if (value == null) {
            throw new RuntimeException("v == null");
        }
        this.type = type;
        this.value = value;
        
        // only accept boolean, list, map, number or string types
        if(!(isBoolean() || isList() || isSet() || isMap() || isNumber() || isString() || isDate() || isDuration() || isPeriod() || isExtObject())) {
            throw new RuntimeException("invalid data type: " + value + " (" + value.getClass() + ")");
        }
    }

    public Type getType() {
        return type;
    }
    
    public boolean equalsType(LValue value) {
	if (value == null) {
	    return false;
	}
	return value.getType() == getType();
    }

    protected boolean isEqualsValueType(LValue that) {
	return isEqualsValueType(this.getValue(), that.getValue());
    }

    protected boolean isEqualsValueType(LValue value1 , LValue value2) {
	if (value1 == null || value2 == null) {
	    return false;
	}
	Object v1 = value1.getValue();
	Object v2 = value2.getValue();
	return isEqualsValueType(v1, v2);
    }
    
    protected boolean isEqualsValueType(Object v1, Object v2) {
	if (v1 == null || v2 == null) {
	    return false;
	}
	return v1.getClass().equals(v2.getClass());
    }
    
    ////

    public String asString() {
        return (String) value;
    }

    public Boolean asBoolean() {
        return (Boolean) value;
    }

    public Number asNumber() {
        return (Number) value;
    }
    
    public Integer asInteger() {
        return ((Number) value).intValue();
    }

    public Long asLong() {
        return ((Number) value).longValue();
    }

    public Float asFloat() {
        return ((Number) value).floatValue();
    }
    
    public Double asDouble() {
        return ((Number) value).doubleValue();
    }

    public BigDecimal asBigDecimal() {
        return ((BigDecimal) value);
    }

    public BigInteger asBigInteger() {
        return ((BigInteger) value);
    }
    
    public Date asDate() {
        return (Date) value;
    }

    @SuppressWarnings("unchecked")
    public Collection<LValue> asCollection() {
        return (Collection<LValue>) value;
    }
    
    @SuppressWarnings("unchecked")
    public List<LValue> asList() {
        return (List<LValue>) value;
    }

    @SuppressWarnings("unchecked")
    public Set<LValue> asSet() {
        return (Set<LValue>) value;
    }
    
    @SuppressWarnings("unchecked")
    public Map<LValue, LValue> asMap() {
        return (Map<LValue, LValue>) value;
    }


    @Override
    public int compareTo(LValue that) {
	return compareValueTo(that);
    }

    @Override
    public boolean equals(Object o) {
        if (this == VOID || o == VOID) {
            throw new RuntimeException("Can't use VOID: " + this + " ==/!= " + o);
        }
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        LValue that = (LValue) o;
        return isEqualsValue(that);
    }
    
    protected boolean isEqualsValue(LValue that) {
	if (that == null || value == null) {
	    return false;
	}
	// Default implementation
	return this.value.equals(that.value);
    }

    protected int compareValueTo(LValue that) {
	// Default implementation
	throw new RuntimeException("Illegal expression: can't compare `" + this + "` to `" + that + "`");
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isString() {
        return value instanceof String;
    }

    public boolean isBoolean() {
        return value instanceof Boolean;
    }

    public boolean isNumber() {
        return value instanceof Number;
    }
    public boolean isInteger() {
        return value instanceof Integer;
    }

    public boolean isFloat() {
        return value instanceof Float;
    }

    public boolean isDouble() {
        return value instanceof Double;
    }
    
    public boolean isBigDecimal() {
        return value instanceof BigDecimal;
    }
    
    public boolean isBigInteger() {
        return value instanceof BigInteger;
    }
    
    public boolean isCollection() {
        return value instanceof Collection<?>;
    }
    
    public boolean isList() {
        return value instanceof List<?>;
    }

    public boolean isSet() {
        return value instanceof Set<?>;
    }
    
    public boolean isMap() {
        return value instanceof Map<?, ?>;
    }

    public boolean isNull() {
        return this == NULL;
    }

    public boolean isVoid() {
        return this == VOID;
    }

    public boolean isInvalid() {
        return this == INVALID;
    }
    
    public boolean isDate() {
        return value instanceof Date;
    }

    public boolean isInstant() {
        return type == Type.DATE || type == Type.DATE_TIME || type == Type.TIME;
    }

    public boolean isDuration() {
        return type == Type.DURATION;
    }

    public boolean isPeriod() {
        return type == Type.PERIOD;
    }

    public boolean isInterval() {
        return type == Type.DURATION || type == Type.PERIOD;
    }
    
    public boolean isExtObject() {
        return type == Type.EXTERNAL_OBJECT;
    }
    
    //// SOFT TYPE
    
    public boolean isSoftFloat() {
	return isFloat() || isInteger();
    }
    
    ////
    
    public Object getValue() {
	return value;
    }

    @Override
    public String toString() {
	if (isNull()) {
	    return "null";
	}
	if (isVoid()) {
	    return "void";
	}
	if (isInvalid()) {
	    return "invalid";
	}
	return _toString();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // BASE
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public String _toString() {
    	return String.valueOf(value);
    }
    
    public int _hashCode() {
    	return value == null ? 0 : value.hashCode();
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // OPEATORS
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    protected boolean eqNull(LValue a, LValue b) {
	if (a == LValue.NULL && b == LValue.NULL) {
	    return true;
	}
	if (a == LValue.NULL || b == LValue.NULL) {
	    return false;
	}
	return a.equals(b);
    }

    // < values
    protected boolean ltNull(LValue a, LValue b) {
	if (a == LValue.NULL && b == LValue.NULL) {
	    return false;
	}
	if (a == LValue.NULL) {
	    return true;
	}
	if (b == LValue.NULL) {
	    return false;
	}
	raiseIllegalOperatorException(a, "<", b);
	return false;
    }

    // <= values
    protected boolean lteNull(LValue a, LValue b) {
	if (a == LValue.NULL && b == LValue.NULL) {
	    return true;
	}
	if (a == LValue.NULL) {
	    return true;
	}
	if (b == LValue.NULL) {
	    return false;
	}
	raiseIllegalOperatorException(a, "<=", b);
	return false;
    }

    // > values
    protected boolean gtNull(LValue a, LValue b) {
	if (a == LValue.NULL && b == LValue.NULL) {
	    return false;
	}
	if (a == LValue.NULL) {
	    return false;
	}
	if (b == LValue.NULL) {
	    return true;
	}
	raiseIllegalOperatorException(a, ">", b);
	return false;
    }

    // >= values
    protected boolean gteNull(LValue a, LValue b) {
	if (a == LValue.NULL && b == LValue.NULL) {
	    return true;
	}
	if (a == LValue.NULL) {
	    return false;
	}
	if (b == LValue.NULL) {
	    return true;
	}
	raiseIllegalOperatorException(a, ">=", b);
	return false;
    }
    
    // &&, and values
    protected LValue andNullResult(LValue a, LValue b) {
	if (a == LValue.NULL || b == LValue.NULL) {
	    return LBoolean.FALSE;
	}
	raiseIllegalOperatorException(a, "and", b);
	return LBoolean.FALSE;
    }

    // &
    protected LValue bitAndNullResult(LValue a, LValue b) {
	if (a == LValue.NULL || b == LValue.NULL) {
	    return LBoolean.FALSE;
	}
	raiseIllegalOperatorException(a, "&", b);
	return LBoolean.FALSE;
    }
    
    // ||, or values
    protected LValue orNullResult(LValue a, LValue b) {
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LBoolean.FALSE;
	}
	if (a == LValue.NULL) {
	    if (!b.isBoolean()) {
		raiseIllegalOperatorException(a, "or", b);
	    }
	    return b;
	}
	if (b == LValue.NULL) {
	    if (!a.isBoolean()) {
		raiseIllegalOperatorException(a, "or", b);
	    }
	    return a;
	}
	
	raiseIllegalOperatorException(a, "or", b);
	return LBoolean.FALSE;
    }    
    
    // |
    protected LValue bitOrNullResult(LValue a, LValue b) {
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LBoolean.FALSE;
	}
	if (a == LValue.NULL) {
	    if (!b.isBoolean()) {
		raiseIllegalOperatorException(a, "|", b);
	    }
	    return b;
	}
	if (b == LValue.NULL) {
	    if (!a.isBoolean()) {
		raiseIllegalOperatorException(a, "|", b);
	    }
	    return a;
	}
	
	raiseIllegalOperatorException(a, "|", b);
	return LBoolean.FALSE;
    }        

    // xor values
    protected LValue xorNullResult(LValue a, LValue b) {
	if (a == LValue.NULL && b == LValue.NULL) {
	    return LBoolean.FALSE;
	}
	if (a == LValue.NULL) {
	    if (!b.isBoolean()) {
		raiseIllegalOperatorException(a, "xor", b);
	    }
	    return b;
	}
	if (b == LValue.NULL) {
	    if (!a.isBoolean()) {
		raiseIllegalOperatorException(a, "xor", b);
	    }
	    return a;
	}
	
	raiseIllegalOperatorException(a, "xor", b);
	return LBoolean.FALSE;
    }    
    
    
    ////
    
    // ==
    public LValue _eq(LValue a, LValue b) {
	LValue result = nullResult(a, "==", b);
	if (result != null) {
	    return result; 
	}
	return new LBoolean(eqNull(a, b));
    }

    // !=
    public LValue _ne(LValue a, LValue b) {
	LValue result = nullResult(a, "!=", b);
	if (result != null) {
	    return result; 
	}
	return new LBoolean(!eqNull(a, b));
    }

    // in
    public LValue _in(LValue a, LValue b) {
	raiseIllegalOperatorException(a, "in", b);
	return null;
    }
    
    // <
    public LValue _lt(LValue a, LValue b) {
	raiseIllegalOperatorException(a, "<", b);
	return null;
    }
    
    // <=
    public LValue _lte(LValue a, LValue b) {
	raiseIllegalOperatorException(a, "<=", b);
	return null;
    }

    // >
    public LValue _gt(LValue a, LValue b) {
	raiseIllegalOperatorException(a, ">", b);
	return null;
    }
    
    // >=
    public LValue _gte(LValue a, LValue b) {
	raiseIllegalOperatorException(a, ">=", b);
	return null;
    }

    ////////

    // &&, and
    public LValue _and(LValue a, LValue b) {
	raiseIllegalOperatorException(a, "and", b);
	return null;
    }

    // &
    public LValue _bitAnd(LValue a, LValue b) {
	raiseIllegalOperatorException(a, "&", b);
	return null;
    }
    
    // ||, or
    public LValue _or(LValue a, LValue b) {
	raiseIllegalOperatorException(a, "or", b);
	return null;
    }
    
    // |
    public LValue _bitOr(LValue a, LValue b) {
	raiseIllegalOperatorException(a, "|", b);
	return null;
    }    

    // xor
    public LValue _xor(LValue a, LValue b) {
	raiseIllegalOperatorException(a, "xor", b);
	return null;
    }
    
    // !, not
    public LValue _not(LValue a) {
	raiseIllegalOperatorException("not", a);
	return null;
    }
    
    // ?
    public LValue _elvis(LValue exp, LValue a, LValue b) {
	raiseIllegalOperatorException(exp, "?", a, b);
	return null;
    }
    
    ////////
    
    // +
    public LValue _add(LValue a, LValue b) {
	if (a.isString() || b.isString()) {
	    //TODO
	    LValue result = nullResult(a, "+", b);
	    if (result != null) {
		return result; 
	    }
	    return new LString(a.toString() + b.toString());
	}
	raiseIllegalOperatorException(a, "+", b);
	return null;
    }
    
    // -
    public LValue _sub(LValue a, LValue b) {
	raiseIllegalOperatorException(a, "-", b);
	return null;
    }

    // *
    public LValue _mul(LValue a, LValue b) {
	raiseIllegalOperatorException(a, "*", b);
	return null;
    }
    
    // /
    public LValue _div(LValue a, LValue b) {
	raiseIllegalOperatorException(a, "/", b);
	return null;
    }

    // ^
    public LValue _pow(LValue a, LValue b) {
	raiseIllegalOperatorException(a, "^", b);
	return null;
    }

    // %
    public LValue _mod(LValue a, LValue b) {
	raiseIllegalOperatorException(a, "%", b);
	return null;
    }

    ////

    public LValue _unaryPlus(LValue a) {
	raiseIllegalOperatorException("+", a);
	return null;
    }
    
    public LValue _unaryMinus(LValue a) {
	raiseIllegalOperatorException("-", a);
	return null;
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public LValue _get(LValue index) {
	raiseIllegalMethodException("get");
	return null;
    }

    public void _set(LValue index, LValue value) {
	raiseIllegalMethodException("set");
    }

    ////
    
    protected LValue _get(String property) {
	if (property == null) {
	    raiseIllegalMethodException("get");
	    return LValue.NULL;
	}
	String method = "get" + PropertyAccessor.capitalize(property);
	return _invoke(method, null);
    }
    
    protected void _set(String property, LValue value) {
	if (property == null) {
	    raiseIllegalMethodException("get");
	    return;
	}
	String method = "set" + PropertyAccessor.capitalize(property);
	List<LValue> parameters = new ArrayList<LValue>();
	parameters.add(value);
	_invoke(method, parameters);
    }
    
    public LValue _invoke(String method, List<LValue> parameters) {
	if ("toString".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LString(_toString());
	} else 	if ("hashCode".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(_hashCode());
	} else	if ("getMetaTypeName".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LString(getMetaTypeName());
	} else 	if ("getMetaClassName".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LString(getMetaClassName());
	}
	raiseIllegalMethodException(method);
	return null;
    }
    
    // META INFO
    public String getMetaTypeName() {
	return type == null ? "[undefined]" : type.toString(); 
    }

    public String getMetaClassName() {
	return value == null ? "[undefined]" : normalizeSatnadrdPackage(value.getClass().getName()); 
    }
    
    protected String normalizeSatnadrdPackage(String className) {
	if (className == null) {
	    return null;
	}
	className = className.trim();
	for (String pkg: STANDARD_PACKAGES) {
	    if (className.startsWith(pkg + ".")) {
		return className.substring(pkg.length() + 1);
	    }
	}
	return className;
    }


    protected int getIndexValue(LValue index) {
        if (!index.isNumber()) {
            throw new RuntimeException("Illegal expression: " + /*+ expression +*/ "[" + index + "]");
        }
        return index.asLong().intValue();
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void checkMethod(String method, List<LValue> parameters, int count) {
	int parameterCount = parameters == null ? 0 : parameters.size(); 
	if (count != parameterCount) {
	    raiseIllegalMethodException(method, parameterCount);
	}
    }
    
//    protected void raiseIllegalOperatorException(String operator, LValue that) {
//	raiseIllegalOperatorException(toString(), operator, that.toString());
//    }

    protected void raiseIllegalOperatorException(LValue a, String operator, LValue b) {
	raiseIllegalOperatorException(a.toString(), operator, b.toString());
    }

    protected void raiseIllegalOperatorException(String operator, LValue a) {
	raiseIllegalOperatorException("", operator, a.toString());
    }
    

    // Binary
    protected void raiseIllegalOperatorException(String a, String operator, String b) {
	raiseIllegalOperatorException(a + " " + operator + " " + b);
    }

    // Ternary
    protected void raiseIllegalOperatorException(LValue exp, String operator, LValue a, LValue b) {
	raiseIllegalOperatorException(exp + " " + operator + " " + a + ", " + b);
    }
    
    protected void raiseIllegalOperatorException(String message) {
	throw new UnsupportedOperationException("Illegal operator: " + message);
    }

    ////
    
    protected void raiseNullPointerOperatorException(LValue a, String operator, LValue b) {
	raiseNullPointerOperatorException(a.toString(), operator, b.toString());
    }


    // Unary
    protected void raiseNullPointerOperatorException(String operator, LValue a) {
	throw new NullPointerException("Illegal null expression: " + operator + " " + a.toString());
    }
    
    // Binary
    protected void raiseNullPointerOperatorException(String a, String operator, String b) {
	throw new NullPointerException("Illegal null expression: " + a + " " + operator + " " + b);
    }

    // Ternary
    protected void raiseNullPointerOperatorException(LValue exp, String operator, LValue a, LValue b) {
	throw new NullPointerException("Illegal null expression: " + exp + " " + operator + " " + a + ", " + b);
    }
    
    ////
    
    protected void raiseIllegalMethodParameterTypeException(String type) {
	throw new UnsupportedOperationException("Illegal method parameter: Parameter must be '" + type + "'");
    }

    protected void raiseIllegalMethodParameterException(String message) {
	throw new UnsupportedOperationException("Illegal method parameter: " + message);
    }
    
    protected void raiseIllegalMethodException(String message) {
	throw new UnsupportedOperationException("Illegal method: " + message);
    }

    protected void raiseIllegalMethodException(String method, int count) {
	throw new UnsupportedOperationException("Illegal method: '" + method + "' with " + count + " parameter(s)");
    }

    ////
    

    // Binary
    protected LValue nullResult(LValue a, String operator, LValue b) {
	if (a == LValue.NULL || b == LValue.NULL) {
	    if (getEvaluateContext().isNullException()) {
		raiseNullPointerOperatorException(a, operator, b);
		return null;
	    }
	    if (getEvaluateContext().isNullUnknown()) {
		return LValue.NULL;
	    }
	    if (getEvaluateContext().isNullInvalid()) {
		return LValue.INVALID; // TOOD: Must return ERROR value
	    }
	}
	return null;
    }

    // Ternary
    protected LValue nullResult(LValue exp, String operator, LValue a, LValue b) {
	if (exp == LValue.NULL || a == LValue.NULL || b == LValue.NULL) {
	    if (getEvaluateContext().isNullException()) {
		raiseNullPointerOperatorException(exp, operator, a, b);
		return null;
	    }
	    if (getEvaluateContext().isNullUnknown()) {
		return LValue.NULL;
	    }
	    if (getEvaluateContext().isNullInvalid()) {
		return LValue.INVALID; // TOOD: Must return ERROR value
	    }
	}
	return null;
    }
    
    // Unary
    protected LValue nullResult(String operator, LValue a) {
	if (a == LValue.NULL) {
	    if (getEvaluateContext().isNullException()) {
		raiseNullPointerOperatorException(operator, a);
		return null;
	    }
	    if (getEvaluateContext().isNullUnknown()) {
		return LValue.NULL;
	    }
	    if (getEvaluateContext().isNullInvalid()) {
		return LValue.INVALID; // TOOD: Must return ERROR value
	    }
	}
	return null;
    }
    
    
    public EvaluateContext getEvaluateContext() {
	return EvaluateContext.getDefaultContext();
    }
}

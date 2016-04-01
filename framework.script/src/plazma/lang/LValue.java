package plazma.lang;

import java.util.Date;
import java.util.List;
import java.util.Map;

import plazma.ast.LNode;

public class LValue implements Comparable<LValue> {

    public enum Type {NUMBER, STRING, BOOLEAN, LIST, MAP, DATE, OBJ }
    
    //public static final LValue NULL = new LValue();
    
    
    public static final LValue NULL = new LValue();
    public static final LValue VOID = new LValue();
    public static final LValue BREAK = new LValue();
    public static final LValue CONTINUE = new LValue();

    private Type type;
    
    private Object value;

    private LValue() {
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
        if(!(isBoolean() || isList() || isMap() || isNumber() || isString() || isDate())) {
            throw new RuntimeException("invalid data type: " + value + " (" + value.getClass() + ")");
        }
    }

    public Type getType() {
        return type;
    }
    
    
    ////

    public String asString() {
        return (String) value;
    }

    public Boolean asBoolean() {
        return (Boolean) value;
    }

    public Integer asInteger() {
        return ((Number) value).intValue();
    }

    public Long asLong() {
        return ((Number) value).longValue();
    }
    
    public Double asDouble() {
        return ((Number) value).doubleValue();
    }

    public Date asDate() {
        return (Date) value;
    }
    
    @SuppressWarnings("unchecked")
    public List<LValue> asList() {
        return (List<LValue>) value;
    }
    
    @SuppressWarnings("unchecked")
    public Map<LValue, LValue> asMap() {
        return (Map<LValue, LValue>) value;
    }


    @Override
    public int compareTo(LValue that) {
        if (this.isNumber() && that.isNumber()) {
            if(this.equals(that)) {
                return 0;
            } else {
                return this.asDouble().compareTo(that.asDouble());
            }
        }  else if (this.isString() && that.isString()) {
            return this.asString().compareTo(that.asString());
        }
        throw new RuntimeException("illegal expression: can't compare `" + this + "` to `" + that + "`");
    }

    @Override
    public boolean equals(Object o) {
        if (this == VOID || o == VOID) {
            throw new RuntimeException("can't use VOID: " + this + " ==/!= " + o);
        }
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        LValue that = (LValue) o;
        if (this.isNumber() && that.isNumber()) {
            double diff = Math.abs(this.asDouble() - that.asDouble());
            return diff < 0.00000000001;
        } else {
            return this.value.equals(that.value);
        }
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

    public boolean isList() {
        return value instanceof List<?>;
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

    public boolean isDate() {
        return value instanceof Date;
    }
    
    public Object getValue() {
	return value;
    }

    @Override
    public String toString() {
        return isNull() ? "NULL" : isVoid() ? "VOID" : _toString();
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
    
    // ==
    public LValue _eq(LValue that) {
	return new LBoolean(equals(that));
    }

    // !=
    public LValue _ne(LValue that) {
	return new LBoolean(!equals(that));
    }

    // in
    public LValue _in(LValue that) {
	if (!that.isList()) {
	    raiseIllegalOperatorException("in", that);
	}
        List<LValue> list = that.asList();
        for (LValue val : list) {
            if (this.equals(val)) {
                return LBoolean.TRUE;
            }
        }
        return LBoolean.FALSE;
    }
    
    // <
    public LValue _lt(LValue that) {
	raiseIllegalOperatorException("<", that);
	return null;
    }
    
    // <=
    public LValue _lte(LValue that) {
	raiseIllegalOperatorException("<=", that);
	return null;
    }

    // >
    public LValue _gt(LValue that) {
	raiseIllegalOperatorException(">", that);
	return null;
    }
    
    // >=
    public LValue _gte(LValue that) {
	raiseIllegalOperatorException(">=", that);
	return null;
    }

    ////////

    // &&, and
    public LValue _and(LValue that) {
	raiseIllegalOperatorException("&&", that);
	return null;
    }

    // ||, or
    public LValue _or(LValue that) {
	raiseIllegalOperatorException("||", that);
	return null;
    }
    
    
    ////////
    
    // +
    public LValue _add(LValue that) {
	raiseIllegalOperatorException("+", that);
	return null;
    }
    
    // -
    public LValue _sub(LValue that) {
	raiseIllegalOperatorException("-", that);
	return null;
    }

    // *
    public LValue _mul(LValue that) {
	raiseIllegalOperatorException("*", that);
	return null;
    }
    
    // /
    public LValue _div(LValue that) {
	raiseIllegalOperatorException("/", that);
	return null;
    }

    // ^
    public LValue _pow(LValue that) {
	raiseIllegalOperatorException("^", that);
	return null;
    }

    // %
    public LValue _mod(LValue that) {
	raiseIllegalOperatorException("%", that);
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
    
    public LValue _get(String property) {
	raiseIllegalMethodException("get");
	return null;
    }
    
    public void _set(String property) {
	raiseIllegalMethodException("set");
    }
    
    public LValue _invoke(String method, List<LNode> parameters) {
	if ("toString".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LString(_toString());
	} else 	if ("hashCode".equals(method)) {
	    checkMethod(method, parameters, 0);
	    return new LNumber(_hashCode());
	}
	raiseIllegalMethodException(method);
	return null;
    }


    protected int getIndexValue(LValue index) {
        if (!index.isNumber()) {
            throw new RuntimeException("Illegal expression: " + /*+ expression +*/ "[" + index + "]");
        }
        return index.asLong().intValue();
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void checkMethod(String method, List<LNode> parameters, int count) {
	int parameterCount = parameters == null ? 0 : parameters.size(); 
	if (count != parameterCount) {
	    raiseIllegalMethodException(method, parameterCount);
	}
    }
    
    protected void raiseIllegalOperatorException(String operator, LValue that) {
	raiseIllegalOperatorException(toString(), operator, that.toString());
    }

    protected void raiseIllegalOperatorException(String a, String operator, String b) {
	raiseIllegalOperatorException(a + " " + operator + " " + b);
    }
    
    protected void raiseIllegalOperatorException(String message) {
	throw new UnsupportedOperationException("Illegal operator: " + message);
    }
    
    protected void raiseIllegalMethodException(String message) {
	throw new UnsupportedOperationException("Illegal method: " + message);
    }

    protected void raiseIllegalMethodException(String method, int count) {
	throw new UnsupportedOperationException("Illegal method: '" + method + "' with " + count + " parameter(s)");
    }

}

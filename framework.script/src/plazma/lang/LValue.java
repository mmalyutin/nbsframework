package plazma.lang;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LValue implements Comparable<LValue> {

    public static final LValue NULL = new LValue();
    public static final LValue VOID = new LValue();
    public static final LValue BREAK = new LValue();
    public static final LValue CONTINUE = new LValue();

    private Object value;

    private LValue() {
        // private constructor: only used for NULL and VOID
        value = new Object();
    }

    public LValue(Object value) {
        if (value == null) {
            throw new RuntimeException("v == null");
        }
        this.value = value;
        // only accept boolean, list, map, number or string types
        if(!(isBoolean() || isList() || isMap() || isNumber() || isString() || isDate())) {
            throw new RuntimeException("invalid data type: " + value + " (" + value.getClass() + ")");
        }
    }

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
        return isNull() ? "NULL" : isVoid() ? "VOID" : toStringValue();
    }
    
    protected String toStringValue() {
    	if (isDate()) {
    		Calendar calendar = Calendar.getInstance();
    		calendar.setTime((Date) value);
    		int year = calendar.get(Calendar.YEAR);
    		int month = calendar.get(Calendar.MONTH) + 1;
    		int day = calendar.get(Calendar.DAY_OF_MONTH);
    		return "" + year + "-" + (month < 9 ? ("0" + month) : month) + "-" + ((day < 9 ? ("0" + day) : day));
    	}
    	
    	return String.valueOf(value);
    }
}

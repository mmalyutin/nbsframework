package org.plazmaforge.framework.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author ohapon
 *
 */
public class ConverterUtils {

    private ConverterUtils() {
    }

    public static String toString(Object value) {
	if (value == null) {
	    return null;
	}	
	return value.toString();
    }
    
    public static Byte toByte(Object value) {
	if (value == null) {
	    return null;
	}	
	if (value instanceof Byte) {
	    return (Byte) value;
	} else if (value instanceof Number) {
	    return ((Number) value).byteValue();
	}
	handleConvertError(value, "Byte");
	return null;
    }

    public static Short toShort(Object value) {
	if (value == null) {
	    return null;
	}	
	if (value instanceof Short) {
	    return (Short) value;
	} else if (value instanceof Number) {
	    return ((Number) value).shortValue();
	}
	handleConvertError(value, "Short");
	return null;
    }
    
    public static Integer toInteger(Object value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof Integer) {
	    return (Integer) value;
	} else if (value instanceof Number) {
	    return ((Number) value).intValue();
	}
	handleConvertError(value, "Integer");
	return null;
    }

    public static Long toLong(Object value) {
	if (value == null) {
	    return null;
	}	
	if (value instanceof Long) {
	    return (Long) value;
	} else if (value instanceof Number) {
	    return ((Number) value).longValue();
	}
	handleConvertError(value, "Long");
	return null;
    }


    public static Float toFloat(Object value) {
	if (value == null) {
	    return null;
	}	
	if (value instanceof Float) {
	    return (Float) value;
	} else if (value instanceof Number) {
	    return ((Number) value).floatValue();
	}
	handleConvertError(value, "Float");
	return null;
    }

    public static Double toDouble(Object value) {
	if (value == null) {
	    return null;
	}	
	if (value instanceof Double) {
	    return (Double) value;
	} else if (value instanceof Number) {
	    return ((Number) value).doubleValue();
	}
	handleConvertError(value, "Double");
	return null;
    }

    public static BigInteger toBigInteger(Object value) {
	if (value == null) {
	    return null;
	}	
	if (value instanceof BigInteger) {
	    return (BigInteger) value;
	} else if (value instanceof Number) {
	    return BigInteger.valueOf(((Number) value).longValue());
	}
	handleConvertError(value, "java.math.BigInteger");
	return null;
    }

    public static BigDecimal toBigDecimal(Object value) {
	if (value == null) {
	    return null;
	}	
	if (value instanceof BigDecimal) {
	    return (BigDecimal) value;
	} else if (value instanceof Number) {
	    return BigDecimal.valueOf(((Number) value).doubleValue());
	}
	handleConvertError(value, "java.math.BigDecimal");
	return null;
    }

    public static Date toDate(Object value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof Date) {
	    // Date, Timestamp, Time
	    return (Date) value;
	} else if (value instanceof Calendar) {
	    return ((Calendar) value).getTime();
	}
	handleConvertError(value, "Date");
	return null;
    }
    
    public static long toTimeMillis(Object value) {
	if (value == null) {
	    return 0;
	}
	if (value instanceof Date) {
	    // Date, Timestamp, Time
	    return ((Date) value).getTime();
	} else if (value instanceof Calendar) {
	    return ((Calendar) value).getTimeInMillis();
	}
	handleConvertError(value, "Time in milliseconds (long)");
	return 0;
    }
    
    public static java.sql.Date toSQLDate(Object value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof java.sql.Date) {
	    return (java.sql.Date) value;
	} else if (value instanceof Date || value instanceof Calendar) {
	    // Date, Timestamp, Time, Calendar
	    return new java.sql.Date(toTimeMillis(value));
	}
	handleConvertError(value, "java.sql.Date");
	return null;
    }

    public static Timestamp toSQLTimestamp(Object value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof Timestamp) {
	    return (Timestamp) value;
	} else if (value instanceof Date || value instanceof Calendar) {
	    // Date, Timestamp, Time, Calendar
	    return new Timestamp(toTimeMillis(value));
	}
	handleConvertError(value, "java.sql.Timestamp");
	return null;
    }

    public static Time toSQLTime(Object value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof Time) {
	    return (Time) value;
	} else if (value instanceof Date) {
	    return new Time(((Date) value).getTime());
	}
	handleConvertError(value, "java.sql.Time");
	return null;
    }

    public static Blob toSQLBlob(Object value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof Blob) {
	    return (Blob) value;
	}
	handleConvertError(value, "java.sql.Blob");
	return null;
    }

    public static Clob toSQLClob(Object value) {
	if (value == null) {
	    return null;
	}
	if (value instanceof Clob) {
	    return (Clob) value;
	}
	handleConvertError(value, "java.sql.Clob");
	return null;
    }

    public static void handleConvertError(Object value, String type) {
	throw new RuntimeException("Can not convert value '" + value + "' to " + type);
    }

}

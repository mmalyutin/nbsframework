package org.plazmaforge.framework.erm.util;

public class QueryUtils {

    private QueryUtils() {
    }
    
    public static String getQueryColumnString(String columnName, String tableAlias) {
	if (columnName == null){
	    return null;
	}
	return (tableAlias == null) ? columnName : (tableAlias + "." + columnName);
    }

}

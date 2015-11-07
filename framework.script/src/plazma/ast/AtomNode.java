package plazma.ast;

import java.util.Calendar;
import java.util.Date;

import plazma.lang.LValue;


public class AtomNode implements LNode {

    private LValue value;

    public AtomNode(Object v) {
        value = v == null ? LValue.NULL : new LValue(v);
    }

    @Override
    public LValue evaluate() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
    
    //
    
    public static Date parseDate(String str) {
    	if (str == null) {
    		return null;
    	}
    	int start = "#Date{".length();
    	if (str.length() <= start + 1){
    		return null;
    	}
    	
    	String val = str.substring(start, str.length() - 1);
    	String[] array = val.split("-");
    	if (array == null || array.length < 3) {
    		return null;
    	}
    	
    	int year = Integer.parseInt(array[0]);
    	int month = Integer.parseInt(array[1]) - 1;
    	int day = Integer.parseInt(array[2]);
    	
    	Calendar calendar = Calendar.getInstance();
    	calendar.set(Calendar.YEAR, year);
    	calendar.set(Calendar.MONTH, month);
    	calendar.set(Calendar.DAY_OF_MONTH, day);
    	
    	return calendar.getTime();
    	
    }
}

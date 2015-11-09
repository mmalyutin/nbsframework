package org.plazmaforge.framework.uwt.swt.util;

public class ToolTipHelper {

    private static ToolTipHandler handler;
    
    public static ToolTipHandler getHandler() {
	if (handler == null) {
	    handler = new ToolTipHandler(null);
	}
	return handler;
    }
}

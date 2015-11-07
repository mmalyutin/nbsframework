package org.plazmaforge.framework.uwt.widget;

public class QuantityField extends NumberField {

    @Override
    protected String getConfigFormat() {
	return getConfigProperty(CONFIG_FORMAT_QUANTITY);
    }
    
}

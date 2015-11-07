package org.plazmaforge.framework.uwt.widget;

public class PercentField extends NumberField {

    @Override
    protected String getConfigFormat() {
	return getConfigProperty(CONFIG_FORMAT_PERCENT);
    }

}

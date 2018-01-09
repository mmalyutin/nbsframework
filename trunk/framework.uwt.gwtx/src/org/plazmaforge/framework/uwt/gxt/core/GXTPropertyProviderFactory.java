package org.plazmaforge.framework.uwt.gxt.core;

import org.plazmaforge.framework.core.data.CachePropertyProviderFactory;
import org.plazmaforge.framework.core.data.PropertyProvider;

public class GXTPropertyProviderFactory extends CachePropertyProviderFactory {

    /**
     * Create GXTPropertyProvider
     */
    protected PropertyProvider<?> createPropertyProvider(String type) {
	try {
	    return new GXTPropertyProvider(type);
	} catch (Throwable t) {
	    return new InvalidPropertyProvider(getErrorMessage(t));
	}
    }
  
  
}

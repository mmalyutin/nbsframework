package org.plazmaforge.framework.erm;

public enum LoadMode {
    
    /**
     * Auto Load:
     *  Simple attribute - load
     *  Reference attribute - lazy load by default
     *  Container attribute - lazy load by default
     */
    AUTO,
    
    /**
     * Lazy Load.
     * All references and containers are lazy.
     * LAZY mode overrides all non lazy references and containers. 
     */
    LAZY,
    
    /**
     * Force Load.
     * Load all attributes.
     */
    FORCE,
    
    /**
     * Level Load.
     * Load attributes by level.
     * See LoadLevel 
     */
    LEVEL

}

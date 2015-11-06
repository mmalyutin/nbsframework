package org.plazmaforge.framework.erm.mapping;

public enum EntityType {

    /**
     * Single table entity
     */
    SingleEntity,
    
    /**
     * Extends by own table entity
     */
    JoinedEntity,
    
    /**
     * Extends by current table
     */
    SubclassEntity,
    
    /**
     * Embedded entity (no own table)
     */
    EmbeddedEntity
    
}

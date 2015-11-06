package org.plazmaforge.framework.erm.mapping;

import org.plazmaforge.framework.erm.RelationType;

public interface IRelation extends IAttribute {

    
    /**
     * Relation type (one-to-one | many-to-one | one-to-many | many-to-many) 
     * @return
     */
    RelationType getRelationType();
    
}

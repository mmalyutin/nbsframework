package org.plazmaforge.framework.erm.mapping;

import org.plazmaforge.framework.erm.RelationType;

public class OneToMany extends Collection implements IRelation {

    public RelationType getRelationType() {
	return RelationType.OneToMany;
    }
    
}

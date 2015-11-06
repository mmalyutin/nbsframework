package org.plazmaforge.framework.erm.mapping;

import org.plazmaforge.framework.erm.RelationType;

public class ManyToMany extends Collection implements IRelation {

    public RelationType getRelationType() {
	return RelationType.ManyToMany;
    }
}

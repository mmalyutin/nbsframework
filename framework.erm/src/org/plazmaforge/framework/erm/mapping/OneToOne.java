package org.plazmaforge.framework.erm.mapping;

import org.plazmaforge.framework.erm.RelationType;

public class OneToOne extends Reference {

    public OneToOne() {
	super.setUnique(true);
    }

    public void setUnique(boolean unique) {
	throw new UnsupportedOperationException("Unique is true always");
    }

    public RelationType getRelationType() {
        return RelationType.OneToOne;
    }

}

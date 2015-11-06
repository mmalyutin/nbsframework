package org.plazmaforge.framework.erm.mapping;

import org.plazmaforge.framework.erm.RelationType;


public class ManyToOne extends Reference {

    public ManyToOne() {
	super.setUnique(false);
    }

    public void setUnique(boolean unique) {
	throw new UnsupportedOperationException("Unique is false always");
    }

    public RelationType getRelationType() {
        return RelationType.ManyToOne;
    }
}

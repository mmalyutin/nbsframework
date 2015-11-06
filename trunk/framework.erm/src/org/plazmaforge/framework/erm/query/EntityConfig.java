package org.plazmaforge.framework.erm.query;


import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.FetchMode;
import org.plazmaforge.framework.erm.LoadMode;
import org.plazmaforge.framework.erm.mapping.Association;
import org.plazmaforge.framework.erm.mapping.Attribute;

public class EntityConfig implements Cloneable {

	
    private FetchMode fetchMode;

    private LoadMode loadMode;

    private boolean lazyReference;
    
    private boolean lazyCollection;
    
    private int joinDepth;
    
    
    public EntityConfig() {
	lazyReference = false;
	lazyCollection = false;
	joinDepth = Configuration.DEFAULT_JOIN_DEPTH; // !!!!! OLD VALUE 1 !!!!!
    }

    public FetchMode getFetchMode() {
	return fetchMode;
    }

    public void setFetchMode(FetchMode fetchMode) {
	this.fetchMode = fetchMode;
    }

    public LoadMode getLoadMode() {
	return loadMode;
    }

    public void setLoadMode(LoadMode loadMode) {
	this.loadMode = loadMode;
    }

    public boolean isLazyReference() {
        return lazyReference;
    }

    public void setLazyReference(boolean lazyReference) {
        this.lazyReference = lazyReference;
    }

    public boolean isLazyCollection() {
        return lazyCollection;
    }

    public void setLazyCollection(boolean lazyCollection) {
        this.lazyCollection = lazyCollection;
    }

    public int getJoinDepth() {
        return joinDepth;
    }

    public void setJoinDepth(int joinDepth) {
	if (joinDepth < Configuration.MIN_JOIN_DEPTH) {
	    throw new IllegalArgumentException("JoinDepth must be >= " + Configuration.MIN_JOIN_DEPTH);
	}
        this.joinDepth = joinDepth;
    }

    public boolean isLoadJoinDepth(int level) {
	return level < joinDepth;
    }
    
    /**
     * Configure by <code>QueryInput</code>
     * @param queryInput
     */
    public void configure(QueryInput queryInput) {
	if (queryInput == null) {
	    return;
	}
	setFetchMode(queryInput.getFetchMode());
	setLoadMode(queryInput.getLoadMode());
	setLazyReference(queryInput.isLazyReference() == null ? false : queryInput.isLazyReference());
	setLazyCollection(queryInput.isLazyCollection() == null ? false : queryInput.isLazyCollection());
	setJoinDepth(queryInput.getJoinDepth() == null ? Configuration.DEFAULT_JOIN_DEPTH : queryInput.getJoinDepth());
    }
    
    /**
     * Configure by attribute
     * @param attribute
     */
    public void configure(Attribute attribute) {
	if (attribute == null) {
	    return;
	}
	if (attribute instanceof Association) {
	    configureByAssociation((Association) attribute);
	}
    }
    
    private void configureByAssociation(Association association) {
	if (association.getJoinDepth() > Configuration.MIN_JOIN_DEPTH) {
	    setJoinDepth(association.getJoinDepth());
	}
    }
    
    public Object clone() {
	try {
	    return super.clone();
	} catch (CloneNotSupportedException ex) {
	    ex.printStackTrace();
	}
	return null;
    }
    
}

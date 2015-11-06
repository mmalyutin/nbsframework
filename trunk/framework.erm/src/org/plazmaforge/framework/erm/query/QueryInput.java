package org.plazmaforge.framework.erm.query;

import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.FetchMode;
import org.plazmaforge.framework.erm.LoadMode;
import org.plazmaforge.framework.core.criteria.Criteria;
import org.plazmaforge.framework.erm.mapping.Attribute;
import org.plazmaforge.framework.erm.mapping.Key;

public class QueryInput {

    private Criteria criteria;

    private FetchMode fetchMode;
    
    private LoadMode loadMode;
    
    
    private Integer joinDepth;
    
    private Attribute[] attributes;
    
    private Key key;
    
    private DiscriminatorValue discriminatorValue;
    
    private String[] includeAttributes;
    
    private String[] excludeAttributes;
    
    
    private Boolean lazyReference;
    
    private Boolean lazyCollection;

    public QueryInput() {
	
    }

    public QueryInput(Criteria criteria, FetchMode fetchMode, LoadMode loadMode) {
	super();
	this.criteria = criteria;
	this.fetchMode = fetchMode;
	this.loadMode = loadMode;
    }

    public void configure(Configuration configuration) {
	if (fetchMode == null) {
	    fetchMode = Configuration.getFetchMode(configuration);
	}
	if (loadMode == null) {
	    loadMode = Configuration.getLoadMode(configuration);
	}
	if (joinDepth == null) {
	    joinDepth = Configuration.getJoinDepth(configuration);
	}
    }
    
    public Criteria getCriteria() {
        return criteria;
    }


    public FetchMode getFetchMode() {
        return fetchMode;
    }


    public LoadMode getLoadMode() {
        return loadMode;
    }

    public Integer getJoinDepth() {
        return joinDepth;
    }
    
    ////


    public Attribute[] getAttributes() {
        return attributes;
    }

    public void setAttributes(Attribute[] attributes) {
        this.attributes = attributes;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }


    ////
  
    public DiscriminatorValue getDiscriminatorValue() {
        return discriminatorValue;
    }

    public void setDiscriminatorValue(DiscriminatorValue discriminatorValue) {
        this.discriminatorValue = discriminatorValue;
    }

    ////
    
    public String[] getIncludeAttributes() {
        return includeAttributes;
    }

    public void setIncludeAttributes(String[] includeAttributes) {
        this.includeAttributes = includeAttributes;
    }

    public String[] getExcludeAttributes() {
        return excludeAttributes;
    }

    public void setExcludeAttributes(String[] excludeAttributes) {
        this.excludeAttributes = excludeAttributes;
    }

    public Boolean isLazyReference() {
        return lazyReference;
    }

    public void setLazyReference(Boolean lazyReference) {
        this.lazyReference = lazyReference;
    }

    public Boolean isLazyCollection() {
        return lazyCollection;
    }

    public void setLazyCollection(Boolean lazyCollection) {
        this.lazyCollection = lazyCollection;
    }
    
    
}

package org.plazmaforge.framework.erm.mapping;

import java.util.ArrayList;
import java.util.List;

import org.plazmaforge.framework.erm.CascadeType;
import org.plazmaforge.framework.erm.Configuration;
import org.plazmaforge.framework.erm.ErrorHandler;
import org.plazmaforge.framework.erm.FetchMode;

public class Association extends Attribute {
    
    private List<Column> joinColumns;
    
    private String joinAttributeName;
    
    private String joinEntityIdentifier;
    
    /**
     * Fetch Mode (JOIN, SELECT)
     */
    private FetchMode fetchMode;
    
    /**
     * Cascade Type (ALL, DELETE, UPDATE)
     */
    private CascadeType cascadeType;
    

    private Entity joinEntity; 
    
    private int joinDepth;
    
    public Column getJoinColumn() {
	if (!hasJoinColumns()) {
	    return null;
	}
        return doGetJoinColumns().get(0);
    }


    public void setJoinColumn(Column column) {
	if (hasJoinColumns()) {
	    doGetJoinColumns().set(0, column);
	} else {
	    doGetJoinColumns().add(column);
	}
    }


    public String getJoinColumnName() {
	Column column = getJoinColumn();
        return column == null ? null : column.getName();
    }


    public void setJoinColumnName(String columnName) {
	Column column = getJoinColumn();
	if (column == null) {
	    column = new Column();
	}
	column.setName(columnName);
	setJoinColumn(column);
    }

    protected List<Column> doGetJoinColumns() {
	if (joinColumns == null) {
	    joinColumns = new ArrayList<Column>();
	}
        return joinColumns;
    }

    public Column[] getJoinColumns() {
	if (joinColumns == null) {
	    return new Column[0];
	}
        return joinColumns.toArray(new Column[0]);
    }

    
    public void adJoinColumns(Column column) {
	doGetJoinColumns().add(column);
    }
    
    public boolean isMultiJoinColumn() {
	return hasColumns();
    }
    
    public boolean hasJoinColumns() {
	return joinColumns != null && !joinColumns.isEmpty();
    }
    
    ////////////////////////////////////////////////////////////////////////

    public String getJoinAttributeName() {
        return joinAttributeName;
    }


    public void setJoinAttributeName(String joinAttributeName) {
        this.joinAttributeName = joinAttributeName;
    }

    public String getJoinEntityIdentifier() {
        return joinEntityIdentifier;
    }


    public void setJoinEntityIdentifier(String joinEntityIdentifier) {
        this.joinEntityIdentifier = joinEntityIdentifier;
    }

    
    public void setJoinClassName(String joinClassName) {
	setJoinEntityIdentifier(joinClassName);
    }

    
    public Entity getJoinEntity() {
	return joinEntity;
	//String identifier = getJoinEntityIdentifier();
	//if (identifier == null) {
	//    return null;
	//}
	//return EntityRegister.getEntityByIdentifier(identifier);
    }
    
    
    public FetchMode getFetchMode() {
        return fetchMode;
    }


    public void setFetchMode(FetchMode fetchMode) {
        this.fetchMode = fetchMode;
    }


    public CascadeType getCascadeType() {
        return cascadeType;
    }


    public void setCascadeType(CascadeType cascadeType) {
        this.cascadeType = cascadeType;
    }

    public boolean isUpdateCascadeType() {
	return cascadeType != null && (CascadeType.ALL.equals(cascadeType) || CascadeType.UPDATE.equals(cascadeType));
    }

    public boolean isDeleteCascadeType() {
	return cascadeType != null && (CascadeType.ALL.equals(cascadeType) || CascadeType.DELETE.equals(cascadeType));
    }
    
    
    public int getJoinDepth() {
        return joinDepth;
    }


    public void setJoinDepth(int joinDepth) {
	if (joinDepth < 0) {
	    throw new IllegalArgumentException("JoinDepth must be >= 0");
	}
        this.joinDepth = joinDepth;
    }


    protected void compile(Configuration configuration) {
	super.compile(configuration);
	String identifier = getJoinEntityIdentifier();
	if (identifier != null) {
	    joinEntity = configuration.getEntityByIdentifier(identifier);
	    if (joinEntity == null) {
		ErrorHandler.handleJoinEntityNotMapped(this, identifier);
	    }
	}
    }



}

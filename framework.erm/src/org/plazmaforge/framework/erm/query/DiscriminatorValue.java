package org.plazmaforge.framework.erm.query;

import org.plazmaforge.framework.erm.mapping.Column;
import org.plazmaforge.framework.erm.mapping.SubclassEntity;

public class DiscriminatorValue {

    private SubclassEntity entity;

    private Column column;

    private String stringValue;

    public SubclassEntity getEntity() {
	return entity;
    }

    public void setEntity(SubclassEntity entity) {
	this.entity = entity;
    }

    public Column getColumn() {
	return column;
    }

    public void setColumn(Column column) {
	this.column = column;
    }

    public String getStringValue() {
	return stringValue;
    }

    public void setStringValue(String stringValue) {
	this.stringValue = stringValue;
    }
}

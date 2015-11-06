package org.plazmaforge.framework.erm.model;

public class MPersonPart extends MModel  {

    private String record;

    private MPerson person;
    
    
    public MPerson getPerson() {
        return person;
    }

    public void setPerson(MPerson person) {
        this.person = person;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
    
    
}

package org.plazmaforge.framework.erm.event;

public enum EventType {

    BeforeInsert,
    
    BeforeUpdate,
    
    BeforeSave, // BeforeInsert or BeforeUpdate
    
    BeforeDelete,
    
    BeforeLoad,
    
    BeforeSelect,
    
    
    AfterInsert,
    
    AfterUpdate,
    
    AfterSave, // AfterInsert or AfterUpdate
    
    AfterDelete,
    
    AfterLoad,
    
    AfterSelect
    
}

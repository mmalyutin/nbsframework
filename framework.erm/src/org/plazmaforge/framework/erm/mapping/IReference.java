package org.plazmaforge.framework.erm.mapping;


/**
 * Reference Attribute
 * 
 * @author ohapon
 *
 */
public interface IReference extends IRelation {
    

    String getJoinColumnName();


    void setJoinColumnName(String joinColumnName);


    String getJoinAttributeName();


    void setJoinAttributeName(String joinAttributeName);


    String getJoinEntityIdentifier();


    void setJoinEntityIdentifier(String joinEntityIdentifier);

    
    Entity getJoinEntity();
    

}

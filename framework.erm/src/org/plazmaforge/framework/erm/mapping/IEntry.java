package org.plazmaforge.framework.erm.mapping;

/**
 * Entry is input OneToOne relation
 * 
 * @author ohapon
 *
 */
public interface IEntry extends IRelation {

    String getJoinColumnName();


    void setJoinColumnName(String joinColumnName);


    String getJoinAttributeName();


    void setJoinAttributeName(String joinAttributeName);


    String getJoinEntityIdentifier();


    void setJoinEntityIdentifier(String joinEntityIdentifier);

    
    Entity getJoinEntity();
}

package org.plazmaforge.framework.erm.mapping;


/**
 * Composite Attribute
 * 
 * @author ohapon
 *
 */
public interface IComposite extends IAttribute, INode {

    Attribute[] getAttributes();

    void addAttribute(Attribute attribute);
 
    boolean hasAttributes();
    
    Attribute getAttributeByName(String name);
    
    boolean isPseudoComposite();
    
}

package org.plazmaforge.framework.erm.mapping;

/**
 * Attribute reference
 * Real position attribute in tree
 * Join entities have own attributes but path of the attribute depend parent entity
 *  
 * @author ohapon
 *
 */
public class AttributeRef {

    private Attribute attribute;
    
    private int level;
    
    private String path;

    
    public AttributeRef(Attribute attribute) {
	this.attribute = attribute;
    }


    public Attribute getAttribute() {
        return attribute;
    }


    public int getLevel() {
        return level;
    }


    public void setLevel(int level) {
        this.level = level;
    }


    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }
    
    
    
}

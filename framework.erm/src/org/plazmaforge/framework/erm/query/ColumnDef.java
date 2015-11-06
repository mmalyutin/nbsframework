package org.plazmaforge.framework.erm.query;


import org.plazmaforge.framework.core.sql.type.Type;
import org.plazmaforge.framework.erm.mapping.IComposite;

/**
 * Column definition
 * 
 * @author ohapon
 *
 */
public class ColumnDef extends AttributeDef {

    /**
     * Real index in data row (Object[])
     */
    private int index = -1;
    
    /**
     * True if the entity was started.
     * Only more entities or reference entity.
     */
    private boolean start; 
    
    /**
     * Size of entity block.
     * Only more entities or reference entity.
     */
    private int blockSize;
    

    /**
     * True if column was loaded
     */
    private boolean load;
    
    /**
     * Attribute path 
     */
    private String path;
    
    /**
     * Virtual (non table) column
     */
    private boolean virtual;
    

    private Type type;
    
    
    private EntityContext context;
    
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public IComposite getParentAttribute() {
        return getAttribute() == null ? null : (getAttribute().getParent() instanceof IComposite ? (IComposite) getAttribute().getParent() : null);
    }

    public String getCompositeClassName() {
	IComposite composite  = getParentAttribute();
        return composite == null ? null : composite.getClassName();
    }

    public boolean isReferenceType() {
        return getAttribute() == null ? null : getAttribute().isReferenceType();
    }

 
    
    ////////////////////////////////////////////////////////////////////////////////////////
    
    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    ////////////////////////////////////////////////////////////////////////////////////////

    public boolean isLoad() {
        return load;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    
   

    public boolean isVirtual() {
        return virtual;
    }

    public void setVirtual(boolean virtual) {
        this.virtual = virtual;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    ////
    
    public EntityContext getContext() {
        return context;
    }

    public void setContext(EntityContext context) {
        this.context = context;
    }

    ////
    
    
    public String toString() {
	return "Column: " + toPropertiesString(); 
    }

    
    
    
}

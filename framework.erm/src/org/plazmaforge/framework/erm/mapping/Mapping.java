package org.plazmaforge.framework.erm.mapping;

import java.util.ArrayList;
import java.util.List;

public class Mapping {

    private List<Entity> entities;

    public List<Entity> getEntities() {
	if (entities == null) {
	    entities = new ArrayList<Entity>();
	}
        return entities;
    }

    public void addEntity(Entity entity) {
        getEntities().add(entity);
    }

    
    public void addMapping(Mapping mapping) {
	if (mapping == null) {
	    return;
	}
	List<Entity> entities =  mapping.getEntities();
	for (Entity entity: entities) {
	    getEntities().add(entity);
	}
    }

    
}

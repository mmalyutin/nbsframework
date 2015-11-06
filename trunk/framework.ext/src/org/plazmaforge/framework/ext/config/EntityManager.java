/*
 * Copyright (C) 2005-2010 Oleh Hapon ohapon@users.sourceforge.net
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307, USA.
 *
 * Oleh Hapon
 * Kyiv, UKRAINE
 * ohapon@users.sourceforge.net
 */

package org.plazmaforge.framework.ext.config;


import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.HashMap;

import org.plazmaforge.framework.config.object.IEntityConfig;
import org.plazmaforge.framework.ext.association.Association;
import org.plazmaforge.framework.util.ClassUtils;

// TODO: Candidate to rename: EntityManager -> EntityRegister 
public class EntityManager {

    private static Map<String, IEntityConfig> entityMap = new HashMap<String, IEntityConfig>();

    public static IEntityConfig getEntityById(String id) {
	if (id == null) {
	    return null;
	}
	return (IEntityConfig) entityMap.get(id);
    }

    public static IEntityConfig getEntityByClass(String className) {
	if (className == null) {
	    return null;
	}
	className = ClassUtils.getSimpleClassName(className);
	if (className == null) {
	    return null;
	}
	String entityId = Association.getKeyString(className, false);
	return getEntityById(entityId);
    }
    
    public static String getEntityId(Object object) {
	if (object == null) {
	    return null;
	}
	IEntityConfig entity = getEntityByClass(object.getClass().getName());
	if (entity == null) {
	    return null;
	}
	return entity.getConfigId();
    }
    
    public static void addEntity(IEntityConfig entity) {
	entityMap.put(entity.getConfigId(), entity);
    }

    public static void addEntities(List<IEntityConfig> entities) {
	if (entities == null) {
	    return;
	}
	for (IEntityConfig entity :  entities) {
	    addEntity(entity);
	}
    }

    public static List<IEntityConfig> getEntities() {
	return new ArrayList<IEntityConfig>(entityMap.values());
    }

}

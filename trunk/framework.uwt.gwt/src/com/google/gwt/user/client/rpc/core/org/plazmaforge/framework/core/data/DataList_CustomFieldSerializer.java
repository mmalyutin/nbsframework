/*
 * Copyright (C) 2012-2013 Oleh Hapon ohapon@users.sourceforge.net
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

package com.google.gwt.user.client.rpc.core.org.plazmaforge.framework.core.data;


import java.util.List;

import org.plazmaforge.framework.core.data.DataList;

import com.google.gwt.user.client.rpc.CustomFieldSerializer;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

public class DataList_CustomFieldSerializer extends CustomFieldSerializer<DataList> {

    @Override
    public void deserializeInstance(SerializationStreamReader streamReader, DataList instance) throws SerializationException {
	deserialize(streamReader, instance);
    }

    public static void serialize(SerializationStreamWriter streamWriter, DataList instance) throws SerializationException {
	streamWriter.writeObject(instance.getList());
	streamWriter.writeInt(instance.getTotal());
    }
    
    
    @Override
    public void serializeInstance(SerializationStreamWriter streamWriter, DataList instance) throws SerializationException {
	serialize(streamWriter, instance);
    }
    
    @SuppressWarnings("unused")
    public static void deserialize(SerializationStreamReader streamReader, DataList instance) {
	 
    }
    
    public static DataList instantiate(SerializationStreamReader streamReader)  throws SerializationException {
	DataList dataList = new DataList((List) streamReader.readObject(), streamReader.readInt());
	return dataList;
    }


}

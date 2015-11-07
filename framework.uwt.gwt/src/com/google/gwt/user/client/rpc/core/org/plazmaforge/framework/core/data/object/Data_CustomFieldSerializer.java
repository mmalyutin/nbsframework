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

package com.google.gwt.user.client.rpc.core.org.plazmaforge.framework.core.data.object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.plazmaforge.framework.core.data.object.Data;

import com.google.gwt.user.client.rpc.CustomFieldSerializer;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;
import com.google.gwt.user.client.rpc.core.java.util.ArrayList_CustomFieldSerializer;
import com.google.gwt.user.client.rpc.core.java.util.Map_CustomFieldSerializerBase;

public class Data_CustomFieldSerializer extends CustomFieldSerializer<Data> {

    @Override
    public void deserializeInstance(SerializationStreamReader streamReader, Data instance) throws SerializationException {
	deserialize(streamReader, instance);
    }

    public static void serialize(SerializationStreamWriter streamWriter, Data instance) throws SerializationException {
	Map<String, Object> values = instance.getValues();
	//streamWriter.writeObject(values);
	//Map_CustomFieldSerializerBase.serialize(streamWriter, values);
	serializeMap(streamWriter, values);
    }
    
    
    @Override
    public void serializeInstance(SerializationStreamWriter streamWriter, Data instance) throws SerializationException {
	serialize(streamWriter, instance);
    }
    
    @SuppressWarnings("unused")
    public static void deserialize(SerializationStreamReader streamReader, Data instance) {
	 
    }
    
    public static Data instantiate(SerializationStreamReader streamReader)  throws SerializationException {
	//Data data = new Data((Map<String, Object>) streamReader.readObject());
	Map<String, Object> values = new HashMap<String, Object>();
	//Map_CustomFieldSerializerBase.deserialize(streamReader, values);
	deserializeMap(streamReader, values);
	Data data = new Data(values); 
	return data;
    }

    
    
    
    
    public static void deserializeMap(SerializationStreamReader streamReader,
	      Map instance) throws SerializationException {
	    int size = streamReader.readInt();

	    for (int i = 0; i < size; ++i) {
	      Object key = streamReader.readObject();
	      
	      /*
	      //TODO: STUB
	      if ("$children".equals(key)) {
		  ArrayList values = new ArrayList();
		  //ArrayList_CustomFieldSerializer.deserialize(streamReader, values);
		  deserializeCollection(streamReader, values);
		  instance.put(key, values);
	      } else {
		  Object value = streamReader.readObject();
		  instance.put(key, value);
	      }
	      */
	      
	      Object type = streamReader.readString();
	      
	      if ("L".equals(type)) {
		  ArrayList values = new ArrayList();
		  //ArrayList_CustomFieldSerializer.deserialize(streamReader, values);
		  deserializeCollection(streamReader, values);
		  instance.put(key, values);
	      } else {
		  Object value = streamReader.readObject();
		  instance.put(key, value);
	      }
	      
	      
	      
	    }
	  }
    
    
    public static void serializeMap(SerializationStreamWriter streamWriter,
        Map instance) throws SerializationException {
      int size = instance.size();
      streamWriter.writeInt(size);

      for (Entry entry : (Set<Entry>) instance.entrySet()) {
        
        Object key = entry.getKey();
        Object value = entry.getValue();
        
        streamWriter.writeObject(key);
        
        /*
        //TODO: STUB
        if ("$children".equals(key)) {
            //ArrayList_CustomFieldSerializer.serialize(streamWriter, (ArrayList) value);
            serializeCollection(streamWriter, (ArrayList) value);
        } else {
            streamWriter.writeObject(entry.getValue());
        }
        */
        
        
        
        if (value instanceof List) {
            streamWriter.writeString("L");
            //ArrayList_CustomFieldSerializer.serialize(streamWriter, (ArrayList) value);
            serializeCollection(streamWriter, (List) value);
        } else {
            streamWriter.writeString("-");
            streamWriter.writeObject(entry.getValue());
        }

        
      }
    }
    
    
    public static void deserializeCollection(
	    SerializationStreamReader streamReader, Collection instance)
	    throws SerializationException {
	int size = streamReader.readInt();
	for (int i = 0; i < size; ++i) {
	    Object obj = streamReader.readObject();
	    instance.add(obj);
	}
    }

    public static void serializeCollection(
	    SerializationStreamWriter streamWriter, Collection instance)
	    throws SerializationException {
	int size = instance.size();
	streamWriter.writeInt(size);
	for (Object obj : instance) {
	    streamWriter.writeObject(obj);
	}
    }
    
    
}

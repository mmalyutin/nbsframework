package com.google.gwt.user.client.rpc.core.org.plazmaforge.framework.core.data;

import org.plazmaforge.framework.core.data.DataWrapper;

import com.google.gwt.user.client.rpc.CustomFieldSerializer;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

public class DataWrapper_CustomFieldSerializer extends CustomFieldSerializer<DataWrapper> {

    @Override
    public void deserializeInstance(SerializationStreamReader streamReader, DataWrapper instance) throws SerializationException {
	deserialize(streamReader, instance);
    }

    public static void serialize(SerializationStreamWriter streamWriter, DataWrapper instance) throws SerializationException {
	streamWriter.writeObject(instance.getData());
    }
    
    
    @Override
    public void serializeInstance(SerializationStreamWriter streamWriter, DataWrapper instance) throws SerializationException {
	serialize(streamWriter, instance);
    }
    
    @SuppressWarnings("unused")
    public static void deserialize(SerializationStreamReader streamReader, DataWrapper instance) {
	 
    }
    
    public static DataWrapper instantiate(SerializationStreamReader streamReader)  throws SerializationException {
	DataWrapper dataWrapper = new DataWrapper(streamReader.readObject());
	return dataWrapper;
    }


}

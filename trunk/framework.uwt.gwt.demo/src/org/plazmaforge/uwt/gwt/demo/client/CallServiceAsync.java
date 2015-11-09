package org.plazmaforge.uwt.gwt.demo.client;

import java.util.List;
import java.util.Map;

import org.plazmaforge.framework.core.data.DataWrapper;
import org.plazmaforge.framework.core.data.Parameters;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CallServiceAsync {

    void addWhitelist(CallWhitelist whitelist, AsyncCallback<Void> asyncCallback);
    
    void call(String service, String method, Parameters parameters, String type, AsyncCallback<DataWrapper> asyncCallback);
    
}

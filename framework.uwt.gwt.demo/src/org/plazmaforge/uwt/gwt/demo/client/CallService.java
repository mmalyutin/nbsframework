package org.plazmaforge.uwt.gwt.demo.client;


import org.plazmaforge.framework.core.data.DataWrapper;
import org.plazmaforge.framework.core.data.Parameters;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("call")
public interface CallService extends RemoteService {

    void addWhitelist(CallWhitelist whitelist);
    
    DataWrapper call(String service, String method, Parameters parameters, String type);
    
}

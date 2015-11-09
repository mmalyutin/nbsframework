package org.plazmaforge.uwt.gwt.demo.server;

import org.plazmaforge.framework.core.data.DataWrapper;
import org.plazmaforge.framework.core.data.Parameters;
import org.plazmaforge.framework.uwt.demo.DemoServiceCaller;
import org.plazmaforge.uwt.gwt.demo.client.CallService;
import org.plazmaforge.uwt.gwt.demo.client.CallWhitelist;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class CallServiceImpl extends RemoteServiceServlet implements CallService {

    private DemoServiceCaller serviceCaller;

    public void addWhitelist(CallWhitelist whitelist) {}

    public DemoServiceCaller getServiceCaller() {
	if (serviceCaller == null) {
	    serviceCaller = new DemoServiceCaller();
	}
	return serviceCaller;
    }

    public DataWrapper call(String service, String method, Parameters parameters, String type) {

	// TODO: Must analyze type (byIndex/byName)
	
	// By Index
	// Call service and get data
	Object data = getServiceCaller().doCall(service, method, parameters == null ? null : parameters.toArray());
	
	// Wrap data because have problems with serialization
	return new DataWrapper(data);
	
    }

}

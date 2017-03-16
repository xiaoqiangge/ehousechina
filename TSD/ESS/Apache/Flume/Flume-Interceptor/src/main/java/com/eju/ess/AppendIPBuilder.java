package com.eju.ess;

import org.apache.flume.Context;
import org.apache.flume.interceptor.Interceptor;

public class AppendIPBuilder implements Interceptor.Builder{

	private String serviceId=null;
	public void configure(Context context) {
		String configServiceId=context.getString("serviceId");
		serviceId=configServiceId;
	}

	public Interceptor build() {
		return new AppendIPInterceptor(serviceId);
	}

}

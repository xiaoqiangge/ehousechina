package com.eju.ess;

import java.util.List;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import com.google.common.base.Charsets;

public class AppendIPInterceptor implements Interceptor {

	private String serviceId=null;
	
	public AppendIPInterceptor(String _serviceId){
		serviceId=_serviceId;
	}
	
	public Event intercept(Event arg0) {
		String eventBody = new String(arg0.getBody(),Charsets.UTF_8);
		String fmt="%s - %s";
		arg0.setBody(String.format(fmt, serviceId,eventBody).getBytes());
		return arg0;
	}

	public List<Event> intercept(List<Event> events) {
		for (Event event : events) {
			intercept(event);
		}
		return events;
	}
	
	public void close() {
		//~ null
	}

	public void initialize() {
		//~ null
	}
	
	/*public static class Builder implements Interceptor.Builder {

		public void configure(Context context) {
			
		}

		public Interceptor build() {
			return new AppendIPInterceptor();
		}  
    } */
}

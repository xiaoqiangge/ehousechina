package com.eju.ess;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eju.ess.service.WorkService;

@Configuration
public class ServiceObject {

	@Autowired
	private WorkService workService;
	@Bean("serviceMap")
	public Map<String,Object> getMap(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put(ServiceEnum.workService.toString(), workService);
		return map;
	}
}

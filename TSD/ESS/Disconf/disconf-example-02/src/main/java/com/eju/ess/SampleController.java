package com.eju.ess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eju.ess.config.JedisConfig;

@RestController
public class SampleController {
	
	@Autowired
	private JedisConfig jedisConfig;
	
	@RequestMapping(value="/build", method = RequestMethod.GET)
	public boolean build(){
		System.out.println(">>>>> "+jedisConfig.getHost());
		return true;
	}
}

package com.eju.ess.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eju.ess.config.JedisConfig;

@Component
public class SimpleRedisService implements InitializingBean, DisposableBean {

	@Autowired
	private JedisConfig jedisConfig;

	@Override
	public void destroy() throws Exception {
		System.out.println(">> destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println(">> afterPropertiesSet");
	}

	public void changeJedis() {
		System.out.println(">> 更改redis");
	}

}

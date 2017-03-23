package com.eju.ess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.disconf.client.common.annotations.DisconfUpdateService;
import com.baidu.disconf.client.common.update.IDisconfUpdate;
import com.eju.ess.config.JedisConfig;

@Component
@DisconfUpdateService(classes = { JedisConfig.class })
public class SimpleRedisServiceUpdateCallback implements IDisconfUpdate {
	@Autowired
	private SimpleRedisService simpleRedisService;

	@Override
	public void reload() throws Exception {
		System.out.println(">> 触发reload");
		simpleRedisService.changeJedis();
	}

}

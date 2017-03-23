package com.eju.ess.config;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.baidu.disconf.client.common.annotations.DisconfFile;
import com.baidu.disconf.client.common.annotations.DisconfFileItem;

@Component
@DisconfFile(filename = "redis.properties")
public class JedisConfig implements Serializable {
	private String port;
	private String host;
	@DisconfFileItem(name = "redis.port")
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	@DisconfFileItem(name = "redis.host")
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
}

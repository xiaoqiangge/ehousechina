package com.eju.ess;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class JenKinsTest {
	/**
	 * 无参数build
	 */
	@Test
	public void build(){
		JenKinsBuildService jenKinsBuildService=new JenKinsBuildService();
		jenKinsBuildService.build("test_zc_1");
	}
	
	/**
	 * 带参数发布
	 */
	@Test
	public void buildWithParameters(){
		Map<String,String> parameters=new HashMap<String,String>();
		parameters.put("version", "1.0.0");
		JenKinsBuildService jenKinsBuildService=new JenKinsBuildService();
		jenKinsBuildService.build("test_zc_1",parameters);
	}
	
	/**
	 * 获取最有一次job的编译状态
	 */
	@Test
	public void jobDetail(){
		JenKinsBuildService jenKinsBuildService=new JenKinsBuildService();
		jenKinsBuildService.getJobDetail("test_zc_1");
	}
}

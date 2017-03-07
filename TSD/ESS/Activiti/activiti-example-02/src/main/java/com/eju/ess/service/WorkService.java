package com.eju.ess.service;

import java.io.Serializable;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WorkService implements Serializable {

	public String show1() {
		log.info(">> 调用服务1");
		return "我是show1的返回值";
	}

	public void show2(String result) {
		log.info(">> 调用服务2");
		log.info("{},在show2中显示",result);
	}
}

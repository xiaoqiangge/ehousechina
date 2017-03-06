package com.eju.ess.service;

import java.io.Serializable;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WorkService implements Serializable {

	public void show1() {
		log.info(">> 调用服务1");
	}

	public void show2() {
		log.info(">> 调用服务2");
	}
}

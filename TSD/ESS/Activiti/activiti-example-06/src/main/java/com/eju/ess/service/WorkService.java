package com.eju.ess.service;

import java.io.Serializable;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WorkService implements Serializable {

	public int show1() {
		return 100;
	}

	public boolean show2() {
		return true;
	}
	
	public boolean show3() {
		return false;
	}
	
	public void show4() {
		log.info(">> 输出");
	}
}

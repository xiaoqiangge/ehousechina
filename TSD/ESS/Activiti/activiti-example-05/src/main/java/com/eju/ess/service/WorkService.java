package com.eju.ess.service;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.runtime.Execution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WorkService implements Serializable {
	@Autowired
	private RuntimeService runtimeService;
	public void show1(Execution execu) {
		// httpclient > pram
		
		log.info("11111111111111111");
		Map<String, Object> map =runtimeService.getVariables(execu.getId());
//		servicetask1 》 job
		// for
		log.info(">> show1 >> {}",String.valueOf(map.get("test")));
	}

	public void show2() {
		log.info(">> show2");
	}

	public void show3() {
		log.info("show3 doing");
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info(">> show3");
	}

	public void show4() {
		log.info(">> show4");
	}
}

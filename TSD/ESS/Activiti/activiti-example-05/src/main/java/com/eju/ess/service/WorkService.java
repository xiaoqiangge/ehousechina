package com.eju.ess.service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WorkService implements Serializable {

	public void show1() {
//		servicetask1 》 job
		// for
		log.info(">> show1");
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

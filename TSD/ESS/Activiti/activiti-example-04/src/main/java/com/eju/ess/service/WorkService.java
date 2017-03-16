package com.eju.ess.service;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WorkService implements Serializable {

	public void wyqj() {
		log.info("调用我要请假");
	}

	public void ldty() {
		log.info("正在等待领导审批...");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("领导终于同意了...");
	}

	public boolean rsty() {
		log.info("正在等待人事审批...");
		try {
			TimeUnit.SECONDS.sleep(8);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
//		log.info("人事终于同意了...");
		log.info("人事不同意，驳回...");
		return false;
	}

	public void xj() {
		log.info("请假成功，销假。");
	}
	
	public void bh() {
		log.info("人事驳回...");
	}
}

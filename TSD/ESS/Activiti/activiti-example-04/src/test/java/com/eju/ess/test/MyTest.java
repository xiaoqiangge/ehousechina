package com.eju.ess.test;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eju.ess.Startup;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Startup.class)
public class MyTest {
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Test
	public void test1() {
		/*ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("demo1");
		String activityId = processInstance.getActivityId();
		log.info(">> activityId >> {}",activityId);
		String id = processInstance.getId();
		log.info(">> id >> {}",id);
		String processInstanceId = processInstance.getProcessInstanceId();
		log.info(">> processInstanceId >> {}",processInstanceId);
		String processDefinitionId = processInstance.getProcessDefinitionId();
		log.info(">> processDefinitionId >> {}",processDefinitionId);*/
		
//		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
//		taskService.complete("11");
	}
	
	@Test
	public void test2(){
		ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey("demo1");
		log.info(">> {}",processInstance.getId());
	}
	@Test
	public void test4(){
		taskService.complete("11");
	}
	
	/*@Test
	public void test3(){
		Execution execution1 = runtimeService
                .createExecutionQuery()  
                .processInstanceId("5")//流程实例ID  
                .activityId("receivetask1")//当前活动的名称  
                .singleResult(); 
		runtimeService.signal(execution1.getId());
	}*/
}

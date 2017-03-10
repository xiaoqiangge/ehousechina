package com.eju.ess;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	public void test() {
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
}

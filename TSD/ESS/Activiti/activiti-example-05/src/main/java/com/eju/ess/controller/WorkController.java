package com.eju.ess.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class WorkController {
	
	@Autowired
    private ProcessEngine engine;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	@Qualifier(value="serviceMap")
	private Map<String,Object> serviceMap;
	
	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv=new ModelAndView();
		mv.setViewName("myView");
		List<ProcessDefinition> list= repositoryService.createProcessDefinitionQuery().list();
		mv.addObject("list", list);
		return mv;
	}
	
	@RequestMapping("/read")
	public void readResource(@RequestParam("pdid") String pdid,@RequestParam("resourceName") String resourceName,HttpServletResponse response) throws IOException {
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		ProcessDefinition pd=processDefinitionQuery.processDefinitionId(pdid).singleResult();
		InputStream resourceAsStream=repositoryService.getResourceAsStream(pd.getDeploymentId(), resourceName);
		byte [] b =new byte[1024];
		int len= -1;
		while((len=resourceAsStream.read(b,0,1024))!=-1){
			response.getOutputStream().write(b,0,len);
		}
	}
	@RequestMapping("/start")
	public ModelAndView start(@RequestParam("pdid") String pdid){
		runtimeService.startProcessInstanceById(pdid,serviceMap);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/");
		return mv;
	}
}

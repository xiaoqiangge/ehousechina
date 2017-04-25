package com.eju.ess.quartz;

import lombok.extern.slf4j.Slf4j;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
@Slf4j
public class ProcessJob implements Job {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
    	log.info(">>");
    }
}

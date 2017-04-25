package com.eju.ess.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SchedulerService {
	@Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
	
	
	public boolean setJob(String jobId,String timeStr){
		log.info(">> 添加计划任务 >> jobId >> {} >> 下次执行 >> {}",new Object[]{jobId,timeStr});
//		String jobId=getUUID();
		try {
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			scheduler.getContext().put("jobId", jobId);
			JobDetail job = JobBuilder.newJob(ProcessJob.class).withIdentity(jobId, "group1").build();
			CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobId, "group1").withSchedule(CronScheduleBuilder.cronSchedule(getCron(timeStr))).build();
			scheduler.scheduleJob(job, trigger);
			return true;
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private String getCron(String timeStr){
//		yyyy-MM-dd-HH-mm-ss
		String [] dateItem=timeStr.split("-");
		String year=dateItem[0];
		String month=dateItem[1];
		String day=dateItem[2];
		String hour=dateItem[3];
		String minute=dateItem[4];
		String second=dateItem[5];
		String cronStr="%s %s %s %s %s ? %s-%s";
//		0 52 14 26 8 ? 2016-2016
		return String.format(cronStr, new Object[]{second,minute,hour,day,month,year,year});
	}
}

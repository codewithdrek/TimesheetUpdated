package com.supraits.controller;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class RunMeJob extends QuartzJobBean {
	private TSReminderScheduler runMeTask;

	public void setRunMeTask(TSReminderScheduler runMeTask) {
		this.runMeTask = runMeTask;
	}
	protected void executeInternal(JobExecutionContext context)
		throws JobExecutionException {
		runMeTask.printMe();
	}
}

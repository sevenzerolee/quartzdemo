package org.sevenzero.quartz.quartzdemo;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;

public class HelloExample {
	
	private static final Logger log = Logger.getLogger(HelloExample.class);

	public static void main(String[] args) throws SchedulerException {

		log.info("------- Initializing ----------------------");

	    // First we must get a reference to a scheduler
		// 生成调度工厂
	    SchedulerFactory sf = new StdSchedulerFactory();
	    // 获取调度实例
	    Scheduler sched = sf.getScheduler();

	    log.info("------- Initialization Complete -----------");

	    // computer a time that is on the next round minute
//	    Date runTime = DateBuilder.evenMinuteDate(new Date());
	    Date runTime = TriggerUtils.getEvenMinuteDate(new Date());

	    log.info("------- Scheduling Job  -------------------");

	    // define the job and tie it to our HelloJob class
	    JobDetail job = new JobDetail("job1", "group1", HelloJob.class); 
	    //JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
	    JobDetail job2 = new JobDetail("job2", "group1", WorldJob.class);
	    //JobBuilder.newJob(WorldJob.class).withIdentity("job2", "group1").build();

	    // Trigger the job to run on the next round minute
	    Trigger trigger = new SimpleTrigger("trigger1", "group1", runTime, null, 1, 1000L);
	    //TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
	    Trigger trigger2 = new SimpleTrigger("trigger2", "group1", runTime, null, 1, 1000L);
	    //TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(runTime).build();

	    // Tell quartz to schedule the job using our trigger
	    sched.scheduleJob(job, trigger);
	    sched.scheduleJob(job2, trigger2);
	    log.info(job.getKey() + " will run at: " + runTime);

	    // Start up the scheduler (nothing can actually run until the
	    // scheduler has been started)
	    sched.start();

	    log.info("------- Started Scheduler -----------------");

	    // wait long enough so that the scheduler as an opportunity to
	    // run the job!
		log.info("------- Waiting 120 seconds... -------------");
		try {
			// wait 65 seconds to show job
			Thread.sleep(300L * 1000L);
			// executing...
		} 
		catch (Exception e) {
			//
			e.printStackTrace();
		}

	    // shut down the scheduler
	    log.info("------- Shutting Down ---------------------");
	    sched.shutdown(true);
	    log.info("------- Shutdown Complete -----------------");
		
	}

}

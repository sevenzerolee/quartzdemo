package org.sevenzero.quartz.quartzdemo;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloExample {
	
	private static final Logger log = Logger.getLogger(HelloExample.class);

	public static void main(String[] args) throws SchedulerException {

		log.warn("------- Initializing ----------------------");

	    // First we must get a reference to a scheduler
	    SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();
	    sched.clear();

	    log.warn("------- Initialization Complete -----------");

	    // computer a time that is on the next round minute
	    Date runTime = DateBuilder.evenMinuteDate(new Date());

	    log.warn("------- Scheduling Job  -------------------");

	    // define the job and tie it to our HelloJob class
	    JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
//	    JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").
//	    		usingJobData(HelloJob.KEY_NUM_EXECUTIONS, "0").build();
	    
//	    job.getJobDataMap().put(HelloJob.KEY_NUM_EXECUTIONS, "0");

	    // Trigger the job to run on the next round minute
//	    Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
	    CronScheduleBuilder csb = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");
	    Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
	    		.withSchedule(csb).build();

	    // Tell quartz to schedule the job using our trigger
	    sched.scheduleJob(job, trigger);
	    log.warn(job.getKey() + " will run at: " + runTime);

	    // Start up the scheduler (nothing can actually run until the
	    // scheduler has been started)
	    sched.start();

	    log.warn("------- Started Scheduler -----------------");

	    // wait long enough so that the scheduler as an opportunity to
	    // run the job!
		log.warn("------- Waiting 65 seconds... -------------");
		try {
			// wait 65 seconds to show job
			Thread.sleep(7200L * 1000L);
			// executing...
		} 
		catch (Exception e) {
			//
			e.printStackTrace();
		}

	    // shut down the scheduler
	    log.warn("------- Shutting Down ---------------------");
	    sched.shutdown(true);
	    log.warn("------- Shutdown Complete -----------------");
		
	}

}

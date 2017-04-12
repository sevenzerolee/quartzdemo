package org.sevenzero.quartz.quartzdemo;

import java.util.Date;

import org.apache.log4j.Logger;
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

		log.info("------- Initializing ----------------------");

	    // First we must get a reference to a scheduler
	    SchedulerFactory sf = new StdSchedulerFactory();
	    Scheduler sched = sf.getScheduler();

	    log.info("------- Initialization Complete -----------");

	    // computer a time that is on the next round minute
	    Date runTime = DateBuilder.evenMinuteDate(new Date());

	    log.info("------- Scheduling Job  -------------------");

	    // define the job and tie it to our HelloJob class
	    JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

	    // Trigger the job to run on the next round minute
	    Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();

	    // Tell quartz to schedule the job using our trigger
	    sched.scheduleJob(job, trigger);
	    log.info(job.getKey() + " will run at: " + runTime);

	    // Start up the scheduler (nothing can actually run until the
	    // scheduler has been started)
	    sched.start();

	    log.info("------- Started Scheduler -----------------");

	    // wait long enough so that the scheduler as an opportunity to
	    // run the job!
	    log.info("------- Waiting 65 seconds... -------------");
	    try {
	      // wait 65 seconds to show job
	      Thread.sleep(65L * 1000L);
	      // executing...
	    } catch (Exception e) {
	      //
	    }

	    // shut down the scheduler
	    log.info("------- Shutting Down ---------------------");
	    sched.shutdown(true);
	    log.info("------- Shutdown Complete -----------------");
		
	}

}

package org.sevenzero.quartz.quartzdemo;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class WorldJob implements Job {
	
	private static final Logger log = Logger.getLogger(WorldJob.class.getSimpleName());

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		log.info("World, +++" + new Date() );
		
	}

}

package org.sevenzero.quartz.quartzdemo;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {
	
	private static final Logger log = Logger.getLogger(HelloJob.class.getSimpleName());

	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		
		int count = 9;
		while ( count-- > 0 ) {
			log.info("Hello, " + new Date() + ", " + count);
			
			try {
				Thread.sleep(2000L);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}

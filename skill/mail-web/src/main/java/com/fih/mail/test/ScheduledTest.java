package com.fih.mail.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledTest {
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTest.class);
	
	@Scheduled(cron="10 * * * * ?") 
	public void executeMail(){
		log.info("================================");
		System.out.println("++++++++++++++++++++++++");
	}
	
}

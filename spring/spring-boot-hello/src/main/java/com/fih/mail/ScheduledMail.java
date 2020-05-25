package com.fih.mail;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledMail {

	@Scheduled(cron="0 25 11 * * ?") 
	public void executeMail(){
		ReceiveMail receiveMail = new ReceiveMail();
		SendTextMail sendTextMail = new SendTextMail();
		try {
			String body = receiveMail.receiveMail();
			System.out.println(body);
			sendTextMail.sendMail("准确下班时间！", "下班时间：" + body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

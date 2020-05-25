package com.fih.mail;

public class TestMail {

	public static void main(String[] args) {
		ReceiveMail receiveMail = new ReceiveMail();
		SendTextMail sendTextMail = new SendTextMail();
		try {
			String body = receiveMail.receiveMail();
			System.out.println(body);
			sendTextMail.sendMail("考勤数据", body);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
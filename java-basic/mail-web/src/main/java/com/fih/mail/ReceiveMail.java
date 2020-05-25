package com.fih.mail;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;

public class ReceiveMail {

	private StringBuffer bodytext;

	public String receiveMail() throws Exception {
		String body = "";
		
		// 定义连接POP3服务器的属性信息
		String pop3Server = "pop3.163.com";
		String protocol = "pop3";

		String user = "13618065552@163.com";
		// String password = "hasoptrtdtfvcbcj"; //QQ
		String password = "cxyzjp123456";

		// 创建一个有具体连接信息的Properties对象
		Properties props = new Properties();

		props.setProperty("mail.store.protocol", protocol); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.pop3.host", pop3Server); // 发件人的邮箱的 SMTP服务器地址

		// 获取连接
		Session session = Session.getDefaultInstance(props);
		session.setDebug(false);

		// 获取Store对象
		Store store = session.getStore(protocol);
		store.connect(pop3Server, user, password); // POP3服务器的登陆认证

		// 通过POP3协议获得Store对象调用这个方法时，邮件夹名称只能指定为"INBOX"
		Folder folder = store.getFolder("INBOX");// 获得用户的邮件帐户
		folder.open(Folder.READ_WRITE); // 设置对邮件帐户的访问权限

		Message[] messages = folder.getMessages();// 得到邮箱帐户中的所有邮件

		for (int i = messages.length - 1; i > messages.length - 10; i--) {
			Message message = messages[i];

			String subject = message.getSubject().trim();// 获得邮件主题
			if (subject.indexOf("考勤数据") > 0) {
				String dateStr = subject.substring(4, 12);

				SimpleDateFormat format = new SimpleDateFormat("MM-dd-yy");
				String dateNow = format.format(new Date());
				if (dateStr.equals(dateNow)) {
					bodytext = new StringBuffer();
					getMailContent(message);
					String bodyStr = bodytext.toString().trim();
					
					int timeStartIndex = bodyStr.indexOf("打卡时间是") + 5;
					String timeStr = dateNow + " " + bodyStr.substring(timeStartIndex, timeStartIndex + 5);
					SimpleDateFormat format2 = new SimpleDateFormat("MM-dd-yy HH:mm");
					Date signDate = format2.parse(timeStr);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(signDate);
					calendar.add(Calendar.HOUR_OF_DAY, 9);
					calendar.add(Calendar.MINUTE, 30);
					
					body = format2.format(calendar.getTime());
					break;
				}
			}
		}

		folder.close(false);// 关闭邮件夹对象
		store.close(); // 关闭连接对象
		
		return body;
	}

	public void getMailContent(Part part) throws Exception {
		String contenttype = part.getContentType();
		int nameindex = contenttype.indexOf("name");
		boolean conname = false;
		if (nameindex != -1)
			conname = true;
		if (part.isMimeType("text/plain") && !conname) {
			bodytext.append((String) part.getContent());
		} else if (part.isMimeType("multipart/*")) {
			Multipart multipart = (Multipart) part.getContent();
			int counts = multipart.getCount();
			for (int i = 0; i < counts; i++) {
				getMailContent(multipart.getBodyPart(i));
			}
		} else if (part.isMimeType("message/rfc822")) {
			getMailContent((Part) part.getContent());
		}
	}
}

package UTIL;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;

public class EmailService {
public EmailService() {
	// TODO Auto-generated constructor stub
}
private static final int MAX_GROUP_NUMBER;

/**
 * Send content to every address
 */
public static final int SINGLE;

/**
 * Send content to group toAddress
 */
public static final int TO_ADDRESS;
/**
 * Send content to group CC
 */
public static final int CC;

/**
 * Send content to group BCC, must define your main email for all BCC
 * Address (bccAddress, bccName property)
 */
public static final int BCC;

private static final int GMAIL_PORT;
private static final int CONNECTION_TIMEOUT;
private static final String DEFAULT_CHARSET;

public static final int SIMPLE_MAIL;
public static final int HTML_MAIL;
public static final int MULTIPART_MAIL;
static {
	MAX_GROUP_NUMBER = 10;
	SINGLE = 0;
	TO_ADDRESS = 1;
	CC = 2;
	BCC = 3;
	GMAIL_PORT = 465;
	CONNECTION_TIMEOUT = 30000;
	DEFAULT_CHARSET = "UTF-8";
	SIMPLE_MAIL = 1;
	HTML_MAIL = 2;
	MULTIPART_MAIL = 3;
}

private String sender = "";
private String senderName = "Test";
private String hostName = "smtp.gmail.com";
private String subject = "No subject";

private Integer port = GMAIL_PORT;
private Boolean ssl = true;
private String password;

public  Email getInstanceEmail(String toAddress,String receiverName, String content,
		Class<?> cls) throws Exception {
	Email em = (Email) cls.newInstance();
	setEmailConfig(em, true);
	em.setSubject(subject);
	em.setFrom(sender, senderName);
	em.setContent(content, Email.TEXT_HTML);
	em.addTo(toAddress,receiverName);
	return em;
}
private void setEmailConfig(Email em, boolean timeout) {
	em.setHostName(hostName);
	if (port != null) {
		em.setSmtpPort(port);
	}
	if (ssl != null) {
		em.setTLS(ssl);
		em.setSSL(ssl);
	}
	em.setCharset(DEFAULT_CHARSET);
	em.setAuthentication(sender, password);
	if (timeout) {
		em.setSocketConnectionTimeout(CONNECTION_TIMEOUT);
	}
}

public boolean sendMail(String receiver,String receiverName,String content) throws Exception{
	Email htmlEmail = getInstanceEmail(receiver, receiverName,content, HtmlEmail.class);
	try{
		htmlEmail.send();
		return true;
	}catch(Exception e){
		return false;
	}
}


public void setSender(String sender) {
	this.sender = sender;
}
public void setSenderName(String senderName) {
	this.senderName = senderName;
}
public void setHostName(String hostName) {
	this.hostName = hostName;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public void setPort(Integer port) {
	this.port = port;
}
public void setSsl(Boolean ssl) {
	this.ssl = ssl;
}
public void setPassword(String password) {
	this.password = password;
}
//public void transaction(){
//	String lang="MALL_EN";
//	TransactionMethod trMethod=new TransactionMethod(){
//		@Override
//		protected void doMethod(Session session, String langs) {
//			String lang="MALL_EN";
//				User sell01=UserBUS.getUser("sell01", lang);
//				sell01.setAddress("test2");
//				update(sell01, session);
//				Products pr=ProductBUS.getProducts(5, lang);
//				//delete(pr, session);					
//		}
//	};
//	trMethod.executeTransaction(lang);
//}
public static void main(String[] args) throws Exception {
	EmailService es=new EmailService();
	es.setSender("luanle19@hotmail.com");
	es.setPassword("");
	System.out.println(es.sendMail("luanle19@gmail.com", "luan", "test"));
//	t.transaction();
}
}

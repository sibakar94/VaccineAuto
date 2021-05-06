package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import page.HomePage;

public class CheckStatus extends BaseTest {
	String[] pinCode = { "751007", "751015", "752069" }; 

	@Test
	public void sendMailStatus() throws InterruptedException {
		HomePage homePage = PageFactory.initElements(driver, HomePage.class);
		List<String> mail = new ArrayList<String>();
		boolean vaccineFlag=false;
		homePage.chooseAge();
		for (int i = 0; i < pinCode.length; i++) {
			homePage.enterPinCode(pinCode[i]);
			homePage.clickOnsearchButton();
			Thread.sleep(5000);

			mail.add("pinCode:" + pinCode[i]);
			if(homePage.stopMsgCheck()) {
				mail.add("Vaccine Not available");
				continue;
			}
			int count = 1;
			int sum=0;
			while (!homePage.stopMsgCheck()) {
				int availableSlotsFor18 = homePage.findAvailableSlotsFor18();
				sum=sum+availableSlotsFor18;
				if (availableSlotsFor18 > 0) {
					vaccineFlag=true;
					mail.add("Vaccine found in page:" + count);
				}
				homePage.rightSwipe();
				count++;
			}
			mail.add("Number of centers :" + sum);
			for(int z=1;z<count;z++) {
				homePage.leftSwipe();
			}

		}
		System.out.println(vaccineFlag);
		System.out.println(mail.toString());
		if(vaccineFlag)
			sendEmail(mail.toString());

	}

	public void sendEmail(String mailsubject) {
		final String username = "siba2005rta@gmail.com";
		final String password = "deimiqbrvapkkncv";

		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("siba2005rta@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("siba2005rta@gmail.com"));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("laren.routray@gmail.com"));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("sauravdas.sapiendevil@gmail.com"));
			message.setSubject("Important!!!Vaccine is available.Hurray!!!!");
			message.setText(mailsubject);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}

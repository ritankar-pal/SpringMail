package in.ineuron.service;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class PurchaseOrderImpl implements IPurchaseOrder {
	
	@Autowired
	private JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private String fromEmail;

	
	@Override
	public String purchase(String[] items, double[] prices, String[] toEmails) throws Exception {
		
		double amt = 0.0;
		for (double price : prices) {
			amt = amt + price;
		}
		
		String msg = Arrays.toString(items) + " with prices: " + Arrays.toString(prices) + " with a bill amount of: " + amt;
		
		String sendMail = sendMail(msg, toEmails);
		
		return msg + " ---> " + sendMail;
	}


	private String sendMail(String msg, String[] toEmails) throws MessagingException {
		System.out.println(sender.getClass().getName());
		
		
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setFrom(fromEmail);
		helper.setCc(toEmails);
		helper.setSubject("Mail From Spring Boot");
		helper.setSentDate(new Date());
		helper.setText(msg);
		helper.addAttachment("ineuron.png", new ClassPathResource("ineuron.png"));
		
		sender.send(message);
		
		return "Sent";
		
	}

}

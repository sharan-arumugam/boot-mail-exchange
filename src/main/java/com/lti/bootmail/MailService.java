package com.lti.bootmail;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

@Service
public class MailService {

	private ExchangeService exchangeService;

	public MailService(@Value("${exchange.url}") String exchangeUrl,
			@Value("${exchange.username}") String username,
			@Value("${exchange.password}") String password) throws URISyntaxException {

		exchangeService = new ExchangeService();
		exchangeService.setUrl(new URI(exchangeUrl));
		exchangeService.setCredentials(new WebCredentials(username, password));
	}

	public void sendMail(String subject, String messageBody, List<String> emailAddresses) {
		try {
			EmailMessage email = new EmailMessage(exchangeService);

			email.setSubject(subject);
			email.setBody(new MessageBody(messageBody));
			
			for (String emailAddress : emailAddresses) {
				email.getToRecipients().add(emailAddress);
			}

			email.sendAndSaveCopy();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

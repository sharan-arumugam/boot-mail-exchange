package com.lti.bootmail;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail")
public class MailController {

	private MailService service;

	public MailController(MailService mailService) {
		service = mailService;
	}

	@RequestMapping("/send")
	public void sendMail() {
		String subject = "testSubject";

		List<EntryDate> list = Arrays.asList(new EntryDate("Sharan", "-3"), new EntryDate("Digant", "+4"));

		String htmlBody = "<table border='1px'>" +
				"<thead>" +
				"<tr>" +
				"<th>Name</th>" +
				"<th>Compliance</th>" +
				"</tr>" +
				"</thead>" +
				"<tbody>";

		for (EntryDate i : list) {
			htmlBody += "<tr><td>" + i.getName() + "</td><td>" + i.getCompliance() + "</td></tr>";
		}
		htmlBody += "</tbody>"
				+ "</table>";

		List<String> emailAddresses = asList("odc.embassy@lntinfotech.com", "sharan.arumugam@lntinfotech.com");

		service.sendMail(subject, htmlBody, emailAddresses);
	}

}

class EntryDate {

	public EntryDate(String name, String compliance) {
		super();
		this.name = name;
		this.compliance = compliance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompliance() {
		return compliance;
	}

	public void setCompliance(String compliance) {
		this.compliance = compliance;
	}

	private String name;
	private String compliance;

}

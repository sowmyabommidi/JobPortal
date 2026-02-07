package com.jobportal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String from;
	
	public void sendMailMesaage(String to,String subject,String body)
	{
		SimpleMailMessage smp=new SimpleMailMessage();
		smp.setFrom(from);
		smp.setTo(to);
		smp.setSubject(subject);
		smp.setText(body);
		mailSender.send(smp);
	}
}

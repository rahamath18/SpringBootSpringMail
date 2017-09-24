package com.test.springboot.springmail.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Component("javaMailSender")
public class JavaMailSender {

	@Autowired
	MailPropertiesService mailProperties;

	public JavaMailSenderImpl getMailSender() {
		
		System.out.println(mailProperties);
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		// Using gmail
		mailSender.setHost(mailProperties.getSpring_mail_host());
		mailSender.setPort(Integer.valueOf(mailProperties.getSpring_mail_port()));
		mailSender.setUsername(mailProperties.getSpring_mail_username());
		mailSender.setPassword(mailProperties.getSpring_mail_password());

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", mailProperties.getSpring_mail_properties_mail_smtp_starttls_enable());
		javaMailProperties.put("mail.smtp.auth", mailProperties.getSpring_mail_properties_mail_smtp_auth());
		javaMailProperties.put("mail.transport.protocol", mailProperties.getSpring_mail_protocol());
		javaMailProperties.put("mail.debug", mailProperties.getSpring_mail_debug());
		// Prints out everything on screen

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}
}

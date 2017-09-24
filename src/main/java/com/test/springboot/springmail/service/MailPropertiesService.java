package com.test.springboot.springmail.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component("mailProperties")
@PropertySource(value = { "classpath:application.properties" })
public class MailPropertiesService {
	
	//@Autowired
		//private Environment environment;
	
	@Value("${spring.mail.host}")
	private String spring_mail_host;

	public String getSpring_mail_host() {
		return spring_mail_host;
	}

	@Value("${spring.mail.port}")
	private String spring_mail_port;

	public String getSpring_mail_port() {
		return spring_mail_port;
	}

	@Value("${spring.mail.username}")
	private String spring_mail_username;

	public String getSpring_mail_username() {
		return spring_mail_username;
	}

	@Value("${spring.mail.password}")
	private String spring_mail_password;

	public String getSpring_mail_password() {
		return spring_mail_password;
	}

	@Value("${spring.mail.properties.mail.smtp.auth}")
	private String spring_mail_properties_mail_smtp_auth;

	public String getSpring_mail_properties_mail_smtp_auth() {
		return spring_mail_properties_mail_smtp_auth;
	}

	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
	private String spring_mail_properties_mail_smtp_starttls_enable;

	public String getSpring_mail_properties_mail_smtp_starttls_enable() {
		return spring_mail_properties_mail_smtp_starttls_enable;
	}

	@Value("${spring.mail.protocol}")
	private String spring_mail_protocol;

	public String getSpring_mail_protocol() {
		return spring_mail_protocol;
	}

	@Value("${spring.mail.debug}")
	private String spring_mail_debug;

	public String getSpring_mail_debug() {
		return spring_mail_debug;
	}
	
	@Value("${app.mail.from}")
	private String app_mail_from;

	public String getApp_mail_from() {
		return app_mail_from;
	}
	
	
	
	@Value("${path.to.store.auth.token}")
	private String path_to_store_auth_token;

	public String getPath_to_store_auth_token() {
		return path_to_store_auth_token;
	}
	
	@Override
	public String toString() {
		return "MailPropertiesBean" + "[" + this.spring_mail_host + "|" + 
				this.spring_mail_port + "|" + 
				this.spring_mail_username + "|" + 
				this.spring_mail_password + "|" + 
				this.spring_mail_properties_mail_smtp_auth + "|" + 
				this.spring_mail_properties_mail_smtp_starttls_enable + "|" + 
				this.spring_mail_protocol + "|" + 
				this.spring_mail_debug + "|" + 
				this.app_mail_from + "|" + 
				this.path_to_store_auth_token + "]";
	}
}

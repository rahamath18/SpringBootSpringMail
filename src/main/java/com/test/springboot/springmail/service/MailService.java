package com.test.springboot.springmail.service;

import com.test.springboot.springmail.model.User;

public interface MailService {

	public void sendEmail(User user, String token);
	
	public void sendEmail(String from, String recipients[], String cc[], 
			String subject, String body) throws Exception;

	public void sendEmail(String from, String recipients[], String cc[], String subject, String body,
			byte[] attachment, String attachmentFileName, String attachmentFileType) throws Exception;

}
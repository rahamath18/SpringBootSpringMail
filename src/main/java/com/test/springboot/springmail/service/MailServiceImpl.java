package com.test.springboot.springmail.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.test.springboot.springmail.model.User;

@Service("mailService")
public class MailServiceImpl implements MailService {
	
	private static Logger logger = Logger.getLogger(MailServiceImpl.class);

	@Autowired
	JavaMailSender mailSender;
	
	@Autowired
	MailPropertiesService mailProperties;
	
	@Override
	public void sendEmail(User user, String token) {

		try {

			mailSender.send(getMessagePreparator(user, token));
			System.out.println("Message Send Successfully");
			
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	@Override
	public void sendEmail(String from, String[] recipients, String[] cc, String subject, String body) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendEmail(String from, String[] recipients, String[] cc, String subject, String body, byte[] attachment,
			String attachmentFileName, String attachmentFileType) throws Exception {
		
		try {

			mailSender.send(
					getContentWtihAttachementMessagePreparator(from, recipients, cc, subject, body, 
							attachment, attachmentFileName, attachmentFileType)
					);
			System.out.println("Message Send...Hurrey");
			
		} catch (MailException ex) {
			System.err.println(ex.getMessage());
		}
		
	}
	
	private void replacePlaceHolders(BufferedReader reader, Map<Object, String> params, StringBuffer buffer) {
		try {
			for (String line = null; (line = reader.readLine()) != null;) {
				try {
					for (int tokenStart = 0; tokenStart >= 0;) {
						tokenStart = line.indexOf("[", tokenStart);
						String token = null;
						if (tokenStart >= 0) {
							int tokenEnd = line.indexOf("]", tokenStart);
							if (tokenEnd > 0) {
								String str1 = line.substring(0, tokenStart);
								String str2 = line.substring(tokenEnd + 1);
								token = line.substring(tokenStart + 1, tokenEnd);
								line = str1 + params.get(token) + str2;
							}
						}
					}

				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				buffer.append(line);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception exception1) {
				}
			}
		}
		return;
	}
	
	private MimeMessagePreparator getContentWtihAttachementMessagePreparator(
			String from, String[] recipients, String[] cc, String subject, String body, byte[] attachment,
			String attachmentFileName, String attachmentFileType) {
		 
	    MimeMessagePreparator preparator = new MimeMessagePreparator() {
	 
	        public void prepare(MimeMessage mimeMessage) throws Exception {
	            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
	 
	            helper.setSubject(subject);
	            helper.setFrom(from);
	            helper.setTo(recipients[0]);

	            helper.setText(body);
	 
	            // Add a resource as an attachment
	           // ByteArrayResource bar = new ByteArrayResource(attachment);
	            //helper.addAttachment(attachmentFileName, new InputStreamResource(bar.getInputStream()));
	            
	            
	            try {
	    			FileWriter fstream = new FileWriter(
	    					new File(mailProperties.getPath_to_store_auth_token()), false);
	    			BufferedWriter out = new BufferedWriter(fstream);
	    			out.write(new String(attachment).toString());
	    			out.close();
	    		} catch (Exception e) {
	    			System.err.println("Error: " + e.getMessage());
	    		}
	            
	            helper.addAttachment(attachmentFileName, new File(mailProperties.getPath_to_store_auth_token()));
	           // helper.addAttachment(attachmentFileName, new ClassPathResource("linux-icon.png"));
	            
	        }
	    };
	    return preparator;
	}
	
	 private MimeMessagePreparator getMessagePreparator(User user, String token) {
		 
	        MimeMessagePreparator preparator = new MimeMessagePreparator() {
	 
	            public void prepare(MimeMessage mimeMessage) throws Exception {
	                
	            	mimeMessage.setFrom(new InternetAddress(mailProperties.getApp_mail_from()));
	                
	                mimeMessage.setRecipient(Message.RecipientType.TO,
	                        new InternetAddress(user.getEmail()));
	                
	                mimeMessage.setText("Dear " + user.getName()
	                        + ", Your Auth token is " + token + ".");
	                
	                mimeMessage.setSubject("Your Auth Token");
	            }
	        };
	        return preparator;
	    }

}
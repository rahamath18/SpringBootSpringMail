/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.test.springboot.springmail;

import static java.lang.System.exit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.springboot.springmail.common.util.AuthTokenGenerator;
import com.test.springboot.springmail.model.User;
import com.test.springboot.springmail.service.MailPropertiesService;
import com.test.springboot.springmail.service.MailService;

@SpringBootApplication // = @Configuration + @ComponentScan + @EnableAutoConfiguration + ect....
public class SpringBootSpringMailApplication implements CommandLineRunner {
	
	@Autowired
	MailPropertiesService mailProperties;
	
	@Autowired
	MailService mailService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootSpringMailApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    	
    	
    	emailTest_1();
    	
    	emailTest_2();
    	
    	
        exit(0);
    }
    
	private void emailTest_2() {
		
		User user = new User();
    	user.setName("Rahamath");
    	user.setEmail("rahamath1805@gmail.com");
    	user.setAddress("India");
    	
    	String token = AuthTokenGenerator.generateAuthenticationToken(
    			user.getName(), 
    			user.getAddress(),
    			user.getAddress());
    	
    	// String from, String recipients[], String cc[], String subject, String body,
		// byte[] attachment, String attachmentFileName, String attachmentFileType
    	
    	try {
		mailService.sendEmail(
				mailProperties.getApp_mail_from(), 
				new String[]{user.getEmail()},
				new String[]{""},
				"Your Auth token is attached",
				"Please refer the attachement",
				token.getBytes(), "auth_token","application/text"
				);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	}

	private void emailTest_1() {

		User user = new User();
    	user.setName("Rahamath");
    	user.setEmail("rahamath1805@gmail.com");
    	user.setAddress("India");
    	
    	String token = AuthTokenGenerator.generateAuthenticationToken(
    			user.getName(), 
    			user.getAddress(),
    			user.getAddress());
    	
    	mailService.sendEmail(user, token);
		
	}
    
    

}
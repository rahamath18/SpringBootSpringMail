package com.test.springboot.springmail.common.util;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

import com.test.springboot.springmail.model.User;

public class AuthTokenGenerator {
	
	public static void main(String[] args) {

		User user = new User();

		user.setName("Rahamath");

		String BASIC = new AuthTokenGenerator().generateAuthenticationToken(user.getName());

		System.out.println("BASIC: " + BASIC);

	}

	public static String generateAuthenticationToken(String... parameters) {
		try {

			String randomNum = Math.random() + "";
			String randomNumPart1 = randomNum.substring(0, randomNum.length() / 2);
			String randomNumPart2 = randomNum.substring(randomNum.length() / 2, randomNum.length());
			String des_ = "343434";
			StringBuilder sb = new StringBuilder();
			sb.append(randomNumPart1);
			for (int i = 0; i < parameters.length; i++) {

				sb.append(parameters[i]);
				if (i + 1 != parameters.length)
					sb.append("@");
			}
			sb.append(des_);
			sb.append(randomNumPart2);

			return Base64.getEncoder().encodeToString(sb.toString().getBytes("utf-8"));

		} catch (UnsupportedEncodingException e) {
			System.out.println("Error :" + e.getMessage());
		}
		return null;
	}

}

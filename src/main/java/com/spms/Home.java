package com.spms;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.spms.bean.LoginBean;

public class Home {
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		   
		System.out.println(new Home().hashPassword("123456"));
	}

	public String hashPassword(String password)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");

		md.update(password.getBytes("UTF-8"));
		byte[] digest = md.digest();
		BigInteger bigInt = new BigInteger(1, digest);

		String hashedPassword = bigInt.toString(16);
		return hashedPassword;
	}

}

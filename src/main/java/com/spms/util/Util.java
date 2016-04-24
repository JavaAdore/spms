package com.spms.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util implements Serializable {

	private static final long serialVersionUID = 1L;

	public static String hashPassword(String password) {
		String paswdToStoreInDB = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String passwd = password;
			md.update(passwd.getBytes("UTF-8"));
			byte[] digest = md.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			paswdToStoreInDB = bigInt.toString(16);

		} catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return paswdToStoreInDB;
	}
	//
	// public static void decrypt(String password){
	// try{
	// MessageDigest md=MessageDigest.getInstance("SHA-256");
	// md.update(password.getBytes());
	// byte[] digest=md.digest();
	// byte[] salt=Arrays.copyOf(digest, 16);
	// System.out.println(Arrays.toString(salt));
	// AlgorithmParameterSpec aps=new PBEParameterSpec(salt, 20);
	// Cipher cipher=Cipher.getInstance(algo);
	// cipher.init(Cipher.DECRYPT_MODE, key, aps);
	// int reader=0;
	// while((reader=fis.read(buffer))!=-1)
	// {
	// System.out.println(reader);
	// byte[] out=cipher.doFinal(buffer);
	// fos.write(out, 0, reader);
	// }
	// fos.close();
	// fis.close();
	//
	// }
	// catch(Exception e){
	// e.printStackTrace();
	// }
	// }
	//
	// public static void main(String[] args) {
	// String hashedPassword = hashPassword("Mohamed");
	// System.out.println(hashPassword(hashedPassword));
	// decrypt(hashedPassword);
	// }
}

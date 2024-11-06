/**
 * 
 */
package no.hvl.dat152.obl4.util;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.digest.DigestUtils;


public class Crypto {

	
	public static String generateUUIDCode() {
		return UUID.randomUUID().toString();
	}
	
	public static String generateMD5Hash(String value) {
		return DigestUtils.md5Hex(value);
	}
	
	public static String generateSHA1Hash(String value) {
		
		return DigestUtils.sha1Hex(value);
	}
	
	public static String generateRandomCryptoCode() {
		
		SecureRandom sr = null;
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			//
		}
		byte[] ivsecret = new byte[16];
		sr.nextBytes(ivsecret);
		
		return DatatypeConverter.printHexBinary(ivsecret);
	}


}

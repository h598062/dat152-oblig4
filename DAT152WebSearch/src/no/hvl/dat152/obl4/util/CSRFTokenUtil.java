package no.hvl.dat152.obl4.util;

import org.apache.commons.codec.binary.Base64;

import java.security.SecureRandom;
import java.math.BigInteger;

public class CSRFTokenUtil {
	private static SecureRandom random = new SecureRandom();

	// Generate a new CSRF token
	public static String generateToken() {
		SecureRandom sr   = new SecureRandom();
		byte[]       csrf = new byte[16];
		sr.nextBytes(csrf);
		return Base64.encodeBase64URLSafeString(csrf);
	}
}

/**
 * 
 */
package no.hvl.dat152.obl4.blog.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import jakarta.xml.bind.DatatypeConverter;

/**
 * @author tdoy
 */

public class PasswordHash {
	
	private int iteration = 120000;
	private String hashAlgorithm = "SHA-1";			// default is SHA-1
	
	private String passwordHash;
	private byte[] passwordSalt;
	
	// Hashing Algorithms available in Java Security
	public static final String MD5 = "MD5";			// 16 bytes (16*8 = 128bits)
	public static final String SHA1 = "SHA-1";		// 20 bytes (160bits)
	public static final String SHA256 = "SHA-256";	// 32 bytes (256bits)
	public static final String SHA384 = "SHA-384";	// 48 bytes (384bits)
	public static final String SHA512 = "SHA-512";	// 64 bytes (512bits)
	
	/**
	 * @param hashAlgorithm (e.g. use PasswordHash.SHA1 to specify the hash algorithm)
	 */
	public PasswordHash(String hashAlgorithm) {
		this.hashAlgorithm = hashAlgorithm;
	}
	
	/**
	 * Use this constructor to harden the password hashing algorithm - iteration slows down the 
	 * hashing computation steps and salt disallow 2 passwords to be the same
	 * Make sure the salt and the hashed password are stored in a persistent storage for each user
	 * @param iteration - number of iteration (int) to slow down CPU computations of the hash
	 * @param hashAlgorithm - (e.g. use PasswordHash.SHA1 to specify the hash algorithm)
	 */
	public PasswordHash(final int iteration, String hashAlgorithm) {
		this.iteration = iteration;
		this.hashAlgorithm = hashAlgorithm;
	}
	
	/**
	 * Low password security
	 * @param password
	 * @throws NoSuchAlgorithmException
	 */
	public String generateHashWithoutSalt(final String password) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
		byte[] passbytes = password.getBytes();
		md.update(passbytes);											// pass the password to the hash function
		byte[] passhash = md.digest();									// obtain the hash value of the password
		
		passwordHash = DatatypeConverter.printHexBinary(passhash); 	// convert into hex value
		
		return passwordHash;
		
	}
	
	/**
	 * Medium password security
	 * @param password
	 * @param salt
	 * @throws NoSuchAlgorithmException
	 */
	public String generateHashWithSalt(final String password, byte[] salt) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance(hashAlgorithm);
		md.update(salt); 				// pass the salt into the update method
		byte[] passbytes = password.getBytes();
		byte[] passhash = md.digest(passbytes);	// pass the password to the digest method to finally obtain the hash value of the password + salt
		
		passwordHash = DatatypeConverter.printHexBinary(passhash);	// passhash is in decimal format - convert into hex

		
		return passwordHash;
	}
	
	/**
	 * Advanced password security - against brute force attack
	 * We use the Password-Based Key Derivation Function (PBKDF) specially designed to slow down hash computation
	 * without destroying the usability
	 * @param password
	 * @param salt
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	public String generateHashWithSaltAndIteration(final String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		char[] passchar = password.toCharArray();
		
		int keylength = 512;						// use 512 bits for the key length
		
		PBEKeySpec pks = new PBEKeySpec(passchar, salt, iteration, keylength);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] keyhash = skf.generateSecret(pks).getEncoded();
		
		passwordHash = DatatypeConverter.printHexBinary(keyhash);
		
		return passwordHash;
		
	}
	
	/**
	 * 
	 * @param password
	 * @param hashedPassword
	 * @return true or false value indicating that newly hashed password matches the stored hashed value
	 * @throws NoSuchAlgorithmException
	 */
	public boolean validatePasswordWithoutSalt(final String password, final String hashedPassword) throws NoSuchAlgorithmException {
		
		generateHashWithoutSalt(password);								// call the generate method
		
		boolean equal = passwordHash.equalsIgnoreCase(hashedPassword);	// compare the hashed password and the plaintext password
		
		return equal;
	}
	
	/**
	 * 
	 * @param password
	 * @param salt
	 * @return true or false value indicating that newly hashed password matches the stored hashed value
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean validatePasswordWithSalt(final String password, final String salt, final String hashedPassword) throws NoSuchAlgorithmException {
		
		byte[] saltbytes = DatatypeConverter.parseHexBinary(salt);	
		
		generateHashWithSalt(password, saltbytes);						// call the generate method
		
		boolean equal = passwordHash.equalsIgnoreCase(hashedPassword);	// compare the hashed password and the plaintext password
		
		return equal;		
		
	}
	
	/**
	 * 
	 * @param password
	 * @param salt
	 * @return true or false value indicating that newly hashed password matches the stored hashed value
	 * @throws InvalidKeySpecException 
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean validatePasswordWithSaltAndIteration(final String password, final String salt, final String hashedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		byte[] saltbytes = DatatypeConverter.parseHexBinary(salt);	
		
		generateHashWithSaltAndIteration(password, saltbytes);	// call the generate method
		
		boolean equal = passwordHash.equalsIgnoreCase(hashedPassword);	// compare the hashed password and the plaintext password
		
		return equal;
	}
	
	
	/**
	 * Convert byte array to hexadecimal format - Note that we can also use the javax.xml.bind.DatatypeConverter;
	 * @param bytes
	 * @return the hexadecimal value as a string
	 */
	/*private String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
		return sb.toString();
	}*/
	
	/**
	 * 
	 * @return salt as a byte array
	 */
	public byte[] getSalt() {
		
		//Always use a SecureRandom generator
	    SecureRandom sr;
	    passwordSalt = new byte[16];			//Create array for salt
	    
		try {
			sr = SecureRandom.getInstance("SHA1PRNG");
		    
		    sr.nextBytes(passwordSalt);					//Get a random salt
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		
	    return passwordSalt;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public String getPasswordSalt() {
		return DatatypeConverter.printHexBinary(passwordSalt);
	}

	public int getIteration() {
		return iteration;
	}

	/**
	 * @return the hashAlgorithm
	 */
	public String getHashAlgorithm() {
		return hashAlgorithm;
	}

}

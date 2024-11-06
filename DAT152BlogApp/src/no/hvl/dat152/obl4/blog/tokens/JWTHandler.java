/**
 * 
 */
package no.hvl.dat152.obl4.blog.tokens;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.SignatureException;

/**
 * @author tdoy
 */

public class JWTHandler {

	
	public static boolean verifyJWT(String jwt, String webpath) {
		
		// this is asymmetric key - public key is used to verify the signature
		
		// decode JWT
		// check issued at
		// check expiry date
		// check the signature
		
		String keypath = webpath+"keys/publickey-keycloak.enc";
		PublicKey puk = loadPublicKey(keypath);

		try {
			
			Jwts.parserBuilder().setSigningKey(puk).build().parse(jwt);		
			
			return true;
			
		}catch(ExpiredJwtException  e) {
			// token expired
			return false;
		}catch(SignatureException e) {
			// invalid signature
			return false;
		}catch(JwtException e) {
			// other problems with JWT
			return false;
		}

	}
	
	public static boolean verifyJWS(String jws, String webpath) {
		
		// this is asymmetric key - public key of the IdP is used to verify the signature
		
		// decode JWT
		// check issued at
		// check expiry date
		// check the signature
		
		String keypath = webpath+"keys/publickey-keycloak.enc";
		PublicKey puk = loadPublicKey(keypath);
		
		//Jws<Claims> jwsc;
		try {
			
			Jwts.parserBuilder().setSigningKey(puk).build().parseClaimsJws(jws);

			return true;
			
		}catch(ExpiredJwtException  e) {
			// token expired
			return false;
		}catch(SignatureException e) {
			// invalid signature
			return false;
		}catch(JwtException e) {
			// other problems with JWT
			return false;
		}

	}
	
	public static Claims getJwt(String jwt, String webpath) {
		
		String keypath = webpath+"keys/publickey-keycloak.enc";
		PublicKey puk = loadPublicKey(keypath);
		
		try {
			return (Claims) Jwts.parserBuilder().setSigningKey(puk).build().parse(jwt).getBody();
		}catch(Exception e) {

			return null;
		}
		
	}
	
	private static PublicKey loadPublicKey(String path) {
		
		PublicKey pubkey = null;
		KeyFactory kf;
		X509EncodedKeySpec x509spec;
		try {
			String publickey = readKeys(path);
			kf = KeyFactory.getInstance("RSA");
			byte[] publicKeyBytes = Decoders.BASE64.decode(publickey);
			x509spec = new X509EncodedKeySpec(publicKeyBytes);
			pubkey = kf.generatePublic(x509spec);
		} catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
			//
		}
			
		return pubkey;
	}
	
	private static String readKeys(String path) {

		String line = "";
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			line=br.readLine();			
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		return line;
	}
	
}

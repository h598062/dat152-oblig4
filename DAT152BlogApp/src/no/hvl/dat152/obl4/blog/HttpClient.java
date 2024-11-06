package no.hvl.dat152.obl4.blog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

/**
 * @author tdoy
 */

public class HttpClient {
	
	private String endpoint;
	
	public HttpClient(String endpoint) {
		this.endpoint = endpoint;
	}
	
	public String requestToken(String clientId, String data) {
		
		char[] buffer = new char[4096];
		URL url;

		try {
			
			// we will use this HTTP client (not a browser) to request for the JWT token
			String client_credentials = Base64.getUrlEncoder().encodeToString(clientId.getBytes());
			
			url = new URL(endpoint);
			
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Authorization", "Basic "+client_credentials);
			con.setDoOutput(true);
			
			PrintWriter pw = new PrintWriter(con.getOutputStream(), false);
			pw.write(data);		// pass the data in the body
			pw.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			
			br.read(buffer, 0, buffer.length);
			
			con.disconnect();
			
			return new String(buffer);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;

	}

}

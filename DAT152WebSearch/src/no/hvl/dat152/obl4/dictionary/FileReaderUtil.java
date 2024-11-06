package no.hvl.dat152.obl4.dictionary;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileReaderUtil {

	public static String getWebFile(String webAddress)
			throws MalformedURLException, IOException {

		StringBuffer inData = new StringBuffer();

		URL url = new URL(webAddress);
		URLConnection urlc = url.openConnection();

		InputStream input = urlc.getInputStream();

		byte[] binaryData = new byte[1000];
		while (true) {
			int bytesRead = input.read(binaryData);
			if (bytesRead == -1)
				break;
			inData.append(new String(binaryData, 0, bytesRead));
		}
		input.close();

		return inData.toString();
	}
}
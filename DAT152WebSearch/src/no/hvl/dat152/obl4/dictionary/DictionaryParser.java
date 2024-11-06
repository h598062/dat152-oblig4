package no.hvl.dat152.obl4.dictionary;

import java.util.ArrayList;
import java.util.List;

public class DictionaryParser {
	
	private String page;
	
	private boolean strict = true;

	public DictionaryParser(String page) {
		this.page = page;
	}
	
	public List<String> findMatchingEntries(String word) {

		List<String> matches = new ArrayList<String>();

		word = word.toUpperCase().charAt(0) + word.toLowerCase().substring(1);
		String searchstring = "<b>" + word;
		if (strict) {
			searchstring += "</b>";
		}

		int startIndex = 0;
		int endIndex = 0;
		while (startIndex >= 0) {

			startIndex = page.indexOf(searchstring, endIndex);
			endIndex = page.indexOf("</p>", startIndex);

			if (startIndex >= 0 && endIndex > startIndex) {
				matches.add(format(page.substring(startIndex, endIndex)));
			}
		}
		return matches;
		
	}

	private String format(String string) {
		
		String result = string;
		result = result.replace("<b>", "");
		result = result.replace("</b>", "");
		result = result.replace("<i>", "");
		result = result.replace("</i>", "");
		
		return result;
	}

}

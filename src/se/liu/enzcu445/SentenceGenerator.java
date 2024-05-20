package se.liu.enzcu445;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SentenceGenerator {
    private List<String> words;
    private int wordCount;
    private String excludeLetters;

    public SentenceGenerator(String jsonFilePath, int wordCount, String excludeLetters) {
	this.wordCount = wordCount;
	this.excludeLetters = excludeLetters;
	try {
	    // Load JSON data from a resource
	    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath);
	    if (inputStream == null) {
		throw new IllegalArgumentException("File not found: " + jsonFilePath);
	    }

	    // Use Gson to parse the JSON into a List of Strings
	    InputStreamReader reader = new InputStreamReader(inputStream);
	    Type listType = new TypeToken<List<String>>() {}.getType();
	    words = new Gson().fromJson(reader, listType);

	    reader.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void setWordCount(int wordCount) {
	this.wordCount = wordCount;
    }

    public int getWordCount() {
	return wordCount;
    }

    public void setExcludeLetters(String excludeLetters) {
	this.excludeLetters = excludeLetters;
    }

    public String getExcludeLetters() {
	return excludeLetters;
    }

    public String generateSentence() {
	if (words == null || words.isEmpty()) {
	    return "No words available for sentence generation.";
	}

	// Filter out words containing excluded letters using a for loop
	List<String> filteredWords = new ArrayList<>();
	for (String word : words) {
	    if (!containsExcludedLetters(word, excludeLetters)) {
		filteredWords.add(word);
	    }
	}

	if (filteredWords.isEmpty()) {
	    return "No words available for sentence generation.";
	}

	Random random = new Random();
	StringBuilder sentence = new StringBuilder();

	// Generate a random sentence with the specified number of words
	for (int i = 0; i < wordCount; i++) {
	    if (i < wordCount - 1) {
		sentence.append(filteredWords.get(random.nextInt(filteredWords.size()))).append("•");
	    } else {
		sentence.append(filteredWords.get(random.nextInt(filteredWords.size())));
	    }
	}

	return sentence.toString().trim() + ".";
    }

    private boolean containsExcludedLetters(String word, String excludeLetters) {
	for (char c : excludeLetters.toLowerCase().toCharArray()) {
	    if (word.toLowerCase().indexOf(c) != -1) {
		return true;
	    }
	}
	return false;
    }
}

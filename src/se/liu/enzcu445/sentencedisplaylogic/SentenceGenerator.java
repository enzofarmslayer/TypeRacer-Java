package se.liu.enzcu445.sentencedisplaylogic;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import se.liu.enzcu445.LoggingConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * SentenceGenerator is responsible for generating sentences based on a list of words loaded from a JSON file.
 * It can exclude words containing specified letters and generate sentences with a specified number of words.
 *
 * <p>Responsibilities:</p>
 * <ul>
 *   <li>Loads a list of words from a JSON file.</li>
 *   <li>Generates sentences with a specified number of words, excluding those with certain letters.</li>
 * </ul>
 *
 * @since 1.0
 */
public class SentenceGenerator {
    private static final Logger LOGGER = LoggingConfig.getLogger();
    private List<String> words;
    private int wordCount;
    private String excludeLetters;
    private static final Random RANDOM = new Random();

    public SentenceGenerator(String jsonFilePath, int wordCount, String excludeLetters) throws SentenceGeneratorException {
	this.wordCount = wordCount;
	this.excludeLetters = excludeLetters;
	try {
	    // Load JSON data from a resource
	    InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath);

	    // Use Gson to parse the JSON into a List of Strings
	    InputStreamReader reader = new InputStreamReader(inputStream);
	    Type listType = new TypeToken<List<String>>() {}.getType();
	    words = new Gson().fromJson(reader, listType);

	    reader.close();
	} catch (IOException e) {
	    e.printStackTrace();
	    LOGGER.severe("Exception occurred while loading JSON data: " + e.getMessage());
	    throw new SentenceGeneratorException("Failed to initialize SentenceGenerator", e);
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

    /**
     * Generates a random sentence with the specified number of words.
     * Filters out words containing excluded letters before generating the sentence.
     *
     * @return A generated sentence with the specified number of words.
     */
    public String generateSentence() {
	if (words == null || words.isEmpty()) {
	    LOGGER.info("No words available for sentence generation: Word list is empty or not loaded.");
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
	    LOGGER.info("No words available for sentence generation: All words contain excluded letters.");
	    return "No words available for sentence generation.";
	}

	StringBuilder sentence = new StringBuilder();

	// Generate a random sentence with the specified number of words
	for (int i = 0; i < wordCount; i++) {
	    if (i < wordCount - 1) {
		sentence.append(filteredWords.get(RANDOM.nextInt(filteredWords.size()))).append("•");
	    } else {
		sentence.append(filteredWords.get(RANDOM.nextInt(filteredWords.size())));
	    }
	}

	return sentence.toString().trim() + ".";
    }

    /**
     * Checks if a word contains any of the excluded letters.
     *
     * @param word The word to check.
     * @param excludeLetters The string of letters to exclude.
     * @return true if the word contains any of the excluded letters, false otherwise.
     */
    private boolean containsExcludedLetters(String word, String excludeLetters) {
	for (char c : excludeLetters.toLowerCase().toCharArray()) {
	    if (word.toLowerCase().indexOf(c) != -1) {
		return true;
	    }
	}
	return false;
    }
}

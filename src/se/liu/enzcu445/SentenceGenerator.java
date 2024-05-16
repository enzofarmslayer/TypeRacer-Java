package se.liu.enzcu445;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class SentenceGenerator {
    private List<String> words;

    public SentenceGenerator(String jsonFilePath) {
	try {
	    // Load JSON data from a file
	    String jsonData = new String(Files.readAllBytes(Paths.get(jsonFilePath)));

	    // Use Gson to parse the JSON into a List of Strings
	    Type listType = new TypeToken<List<String>>() {}.getType();
	    words = new Gson().fromJson(jsonData, listType);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public String generateSentence(int wordCount) {

	// kan ta bort
	if (words == null || words.isEmpty()) {
	    return "No words available for sentence generation.";
	}

	Random random = new Random();
	StringBuilder sentence = new StringBuilder();

	// Generate a random sentence of specified word count
	for (int i = 0; i < wordCount; i++) {
	    if (i < wordCount-1) {
		sentence.append(words.get(random.nextInt(words.size()))).append("•");
	    }
	    else{
		sentence.append(words.get(random.nextInt(words.size())));
	    }

	}

	return sentence.toString().trim() + ".";
    }

}


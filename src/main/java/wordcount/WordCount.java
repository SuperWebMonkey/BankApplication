package wordcount;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.*;

public class WordCount {

    private final static Logger LOGGER = LogManager.getLogger(WordCount.class);

    public static void main(String[] args) {

        countFrequency("Hello World");

        try {
            File file = new File("src/main/resources/lorem-ipsum.txt");
            String outPath = "src/main/resources/output.txt";
            Set<String> wordCount = new HashSet<>();
            List<String> lines = FileUtils.readLines(file, "UTF-8");
            String[] words = StringUtils.split(lines.get(0));

            for (int i = 0; i < words.length; i++) {
                // System.out.println("index " + i + ":" + lines.get(i));
                String word = words[i].toLowerCase()
                        .replace(",", "")
                        .replace(".", "")
                        .replace(":", "");
                wordCount.add(word);
            }

            String message = "The total number of unique words is ";
            LOGGER.info(message + wordCount.size());
            FileUtils.write(new File(outPath), message + wordCount.size(), "UTF-8");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public static void countFrequency(String word) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < word.length(); i++) {
            if (map.containsKey(word.charAt(i))) {
                map.put(word.charAt(i), map.get(word.charAt(i)) + 1);
            } else {
                map.put(word.charAt(i), 1);
            }
        }

        for (char key : map.keySet()) {
            int value = map.get(key);
            LOGGER.info(key + ":" + value + " ");
        }
    }
}

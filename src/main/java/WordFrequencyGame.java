import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String WHITE_SPACE_REGEX = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String NEXT_LINE = "\n";

    public String getResult(String sentence) {
        try {
            //split the input string with 1 to n pieces of spaces
            String[] words = sentence.split(WHITE_SPACE_REGEX);

            List<WordFrequency> wordFrequencyList = Arrays.asList(words).stream().distinct().map(word -> new WordFrequency(word,Collections.frequency(Arrays.asList(words),word))).collect(Collectors.toList());

            wordFrequencyList.sort((word1, word2) -> word2.getCount() - word1.getCount());

            StringJoiner joiner = new StringJoiner(NEXT_LINE);
            for (WordFrequency word : wordFrequencyList) {
                String wordFrequencyLine = word.getWord() + " " + word.getCount();
                joiner.add(wordFrequencyLine);
            }
            return joiner.toString();
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }
}

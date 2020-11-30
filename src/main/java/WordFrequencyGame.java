import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String WHITE_SPACE_REGEX = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String NEXT_LINE = "\n";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> wordFrequencyList = Arrays.asList(sentence.split(WHITE_SPACE_REGEX)).stream().distinct().map(word -> new WordFrequency(word,Collections.frequency(Arrays.asList(sentence.split(WHITE_SPACE_REGEX)),word))).collect(Collectors.toList());

            wordFrequencyList.sort((word1, word2) -> word2.getCount() - word1.getCount());

            return buildResult(wordFrequencyList);
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private String buildResult(List<WordFrequency> wordFrequencyList) {
        StringJoiner joiner = new StringJoiner(NEXT_LINE);
        for (WordFrequency word : wordFrequencyList) {
            String wordFrequencyLine = word.getWord() + " " + word.getCount();
            joiner.add(wordFrequencyLine);
        }
        return joiner.toString();
    }
}

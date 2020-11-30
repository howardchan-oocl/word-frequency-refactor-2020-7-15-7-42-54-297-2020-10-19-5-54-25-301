import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {

    public static final String WHITE_SPACE_REGEX = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String NEXT_LINE = "\n";

    public String getResult(String sentence) {
        try {
            List<WordFrequency> wordFrequencyList = Arrays.stream(sentence.split(WHITE_SPACE_REGEX))
                    .distinct().map(word -> new WordFrequency(word, Collections.frequency(Arrays.asList(sentence.split(WHITE_SPACE_REGEX)), word)))
                    .sorted((word1, word2) -> word2.getCount() - word1.getCount())
                    .collect(Collectors.toList());

            return buildResult(wordFrequencyList);
        } catch (Exception exception) {
            return CALCULATE_ERROR;
        }
    }

    private String buildResult(List<WordFrequency> wordFrequencyList) {
        StringJoiner result = new StringJoiner(NEXT_LINE);

        wordFrequencyList.forEach(word -> result.add(String.format("%s %d", word.getWord(), word.getCount())));

        return result.toString();
    }
}

import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String WHITE_SPACE_REGEX = "\\s+";
    public static final String NEXT_LINE = "\n";

    public String getResult(String sentence) throws CalculateErrorException {
        try {
            List<String> words = splitSentenceIntoWordList(sentence);

            List<WordFrequency> wordFrequencyList = getSortedWordFrequencyList(words);

            return buildResult(wordFrequencyList);
        } catch (Exception exception) {
            throw new CalculateErrorException();
        }
    }

    private List<String> splitSentenceIntoWordList(String sentence) {
        return Arrays.asList(sentence.split(WHITE_SPACE_REGEX));
    }

    private List<WordFrequency> getSortedWordFrequencyList(List<String> words){
        return words.stream().distinct().map(word -> new WordFrequency(word, Collections.frequency(words, word)))
                .sorted((word1, word2) -> word2.getCount() - word1.getCount())
                .collect(Collectors.toList());
    }

    private String buildResult(List<WordFrequency> wordFrequencyList) {
        StringJoiner result = new StringJoiner(NEXT_LINE);

        wordFrequencyList.forEach(word -> result.add(String.format("%s %d", word.getWord(), word.getCount())));

        return result.toString();
    }
}

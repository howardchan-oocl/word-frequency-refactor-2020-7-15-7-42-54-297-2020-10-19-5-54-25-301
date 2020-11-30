import java.util.*;

public class WordFrequencyGame {

    public static final String WHITE_SPACE_REGEX = "\\s+";

    public String getResult(String sentence) {
        try {
            //split the input string with 1 to n pieces of spaces
            String[] words = sentence.split(WHITE_SPACE_REGEX);

            List<WordFrequency> wordFrequencyList = new ArrayList<>();
            for (String s : words) {
                WordFrequency wordFrequency = new WordFrequency(s, 1);
                wordFrequencyList.add(wordFrequency);
            }

            //get the map for the next step of sizing the same word
            Map<String, List<WordFrequency>> map = getListMap(wordFrequencyList);

            List<WordFrequency> list = new ArrayList<>();
            for (Map.Entry<String, List<WordFrequency>> entry : map.entrySet()) {
                WordFrequency wordFrequency = new WordFrequency(entry.getKey(), entry.getValue().size());
                list.add(wordFrequency);
            }

            wordFrequencyList = list;

            wordFrequencyList.sort((word1, word2) -> word2.getCount() - word1.getCount());

            StringJoiner joiner = new StringJoiner("\n");
            for (WordFrequency w : wordFrequencyList) {
                String s = w.getWord() + " " + w.getCount();
                joiner.add(s);
            }
            return joiner.toString();
        } catch (Exception e) {
            return "Calculate Error";
        }

    }

    private Map<String, List<WordFrequency>> getListMap(List<WordFrequency> wordFrequencyList) {
        Map<String, List<WordFrequency>> map = new HashMap<>();
        for (WordFrequency wordFrequency : wordFrequencyList) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordFrequency.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordFrequency);
                map.put(wordFrequency.getWord(), arr);
            } else {
                map.get(wordFrequency.getWord()).add(wordFrequency);
            }
        }
        return map;
    }
}

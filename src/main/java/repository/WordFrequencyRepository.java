package src.main.java.repository;

import java.util.*;

public class WordFrequencyRepository {
    Map<String, Integer> wordFrequencyMap = new HashMap<>();

    public Map<String, Integer> countWordFrequency(String parsedData) {
        String[] words = parsedData.split("\\s+");
        for(String word: words){
            word = word.toLowerCase();
            word = word.replaceAll("[^a-zA-Z0-9]", "");
            if(!word.isEmpty()) {
                wordFrequencyMap.put(word, wordFrequencyMap.getOrDefault(word, 0) + 1);
            }
        }
        return wordFrequencyMap;
    }

    public Map<String, Integer> sortWords() {
        List<Map.Entry<String, Integer> > list
                = new LinkedList<>(wordFrequencyMap.entrySet());
        list.sort((e1, e2) -> {
            if (e1.getValue().equals(e2.getValue())) {
                return e1.getKey().compareToIgnoreCase(e2.getKey());
            } else {
                return (e1.getValue()).compareTo(e2.getValue());
            }
        });
        HashMap<String, Integer> result
                = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> me : list) {
            result.put(me.getKey(), me.getValue());
        }
        return result;
    }
}

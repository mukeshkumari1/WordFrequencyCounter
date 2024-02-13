package src.main.java.service;

import src.main.java.repository.WordFrequencyRepository;

import java.util.Map;

public class WordFrequencyService {
    WordFrequencyRepository wordFrequencyRepository = new WordFrequencyRepository();
    public Map<String, Integer> countWordFrequency(String parsedData) {

        return wordFrequencyRepository.countWordFrequency(parsedData);
    }

    public Map<String, Integer> sortWordFrequencies() {
        return wordFrequencyRepository.sortWords();
    }
}

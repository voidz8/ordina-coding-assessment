package nl.ordina.ordinacodingassessment.service;

import com.google.common.base.Strings;
import nl.ordina.ordinacodingassessment.exception.CalculationException;
import nl.ordina.ordinacodingassessment.model.WordFrequency;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordFrequencyService {

    public int calculateHighestFrequency(String text) throws CalculationException {
        if (Strings.isNullOrEmpty(text)) {
            throw new CalculationException(String.format("Error while calculating highest frequency of: %s", text));
        }
        var wordCount = getWordCount(text);
        return Collections.max(wordCount.values());
    }

    public int calculateFrequencyForWord(String text, String word) {
        if (Strings.isNullOrEmpty(text)) {
            return 0;
        }
        var wordCount = getWordCount(text);

        return wordCount.getOrDefault(word, 0);
    }

    public WordFrequency[] calculateMostFrequentNWords(String text, int n) throws CalculationException {
        if (Strings.isNullOrEmpty(text)) {
            throw new CalculationException(String.format("Can't calculate most frequent words for: %s", text));
        }
        var wordCount = getWordCount(text.toLowerCase());
        List<WordFrequency> wordFrequencies = new ArrayList<>();

        for (Map.Entry<String, Integer> word : wordCount.entrySet()) {
            wordFrequencies.add(new WordFrequency(word.getKey(), word.getValue()));
        }
        wordFrequencies.sort(Comparator.comparing(WordFrequency::getFrequency).reversed().thenComparing(WordFrequency::getWord));

        return wordFrequencies.stream().limit(n).toList().toArray(new WordFrequency[0]);
    }

    private Map<String, Integer> getWordCount(String text) {
        Map<String, Integer> wordCount = new HashMap<>();
        var words = text.split(" ");
        for (var word : words) {
            if (!wordCount.containsKey(word)) {
                wordCount.put(word, 1);
                continue;
            }
            wordCount.put(word, wordCount.get(word) + 1);
        }

        return wordCount;
    }
}

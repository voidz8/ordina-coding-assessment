package nl.ordina.ordinacodingassessment.controller;

import nl.ordina.ordinacodingassessment.dto.FrequencyForWordRequestDto;
import nl.ordina.ordinacodingassessment.dto.MostFrequentNWordsRequestDto;
import nl.ordina.ordinacodingassessment.exception.CalculationException;
import nl.ordina.ordinacodingassessment.model.WordFrequency;
import nl.ordina.ordinacodingassessment.service.WordFrequencyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordFrequencyController {
    private WordFrequencyService wordFrequencyService;

    public WordFrequencyController(WordFrequencyService wordFrequencyService) {
        this.wordFrequencyService = wordFrequencyService;
    }

    @PostMapping("/calculate-highest-frequency")
    public int calculateHighestFrequency(@RequestBody String text) throws CalculationException {
        return wordFrequencyService.calculateHighestFrequency(text);
    }

    @PostMapping("/calculate-frequency-for-word")
    public int calculateFrequencyForWord(@RequestBody FrequencyForWordRequestDto dto) throws CalculationException {
        return wordFrequencyService.calculateFrequencyForWord(dto.text(), dto.word());
    }

    @PostMapping("/calculate-most-frequent-n-words")
    public WordFrequency[] calculateMostFrequentNWords(@RequestBody MostFrequentNWordsRequestDto dto) throws CalculationException {
        return wordFrequencyService.calculateMostFrequentNWords(dto.text(), dto.n());
    }
}

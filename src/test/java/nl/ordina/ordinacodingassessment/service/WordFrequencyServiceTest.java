package nl.ordina.ordinacodingassessment.service;

import nl.ordina.ordinacodingassessment.exception.CalculationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WordFrequencyServiceTest {
    private WordFrequencyService wordFrequencyService;

    @BeforeEach
    void setUp() {
        this.wordFrequencyService = new WordFrequencyService();
    }

    @Test
    public void calculateHighestFrequencyTest() throws CalculationException {
        var highestFrequency = wordFrequencyService.calculateHighestFrequency(getTextThreeEqualWords());

        Assertions.assertEquals(3, highestFrequency);
    }

    @Test
    public void calculateHighestFrequencyExceptionTest() {
        var exception = Assertions.assertThrows(CalculationException.class, () -> wordFrequencyService.calculateHighestFrequency(null));
        var exceptionEmptyString = Assertions.assertThrows(CalculationException.class, () -> wordFrequencyService.calculateHighestFrequency(""));

        Assertions.assertEquals("Error while calculating highest frequency of: null", exception.getMessage());
        Assertions.assertEquals("Error while calculating highest frequency of: ", exceptionEmptyString.getMessage());
    }

    @Test
    public void calculateFrequencyForWordTest() {
        var countThree = wordFrequencyService.calculateFrequencyForWord(getTextThreeEqualWords(), "test");
        var countOne = wordFrequencyService.calculateFrequencyForWord(getTextThreeEqualWords(), "a");
        var countZeroTextNull = wordFrequencyService.calculateFrequencyForWord(null, "test");
        var countZeroWordNull = wordFrequencyService.calculateFrequencyForWord("test", null);
        var countZeroNull = wordFrequencyService.calculateFrequencyForWord(null, null);
        var countZeroEmptyString = wordFrequencyService.calculateFrequencyForWord("", null);
        var countZeroEmptyStringSpace = wordFrequencyService.calculateFrequencyForWord(" ", null);

        Assertions.assertEquals(3, countThree);
        Assertions.assertEquals(1, countOne);
        Assertions.assertEquals(0, countZeroTextNull);
        Assertions.assertEquals(0, countZeroWordNull);
        Assertions.assertEquals(0, countZeroNull);
        Assertions.assertEquals(0, countZeroEmptyString);
        Assertions.assertEquals(0, countZeroEmptyStringSpace);
    }

    @Test
    public void calculateMostFrequentNWordsTest() throws CalculationException {
        var nineWords = wordFrequencyService.calculateMostFrequentNWords(getTextThreeEqualWords(), 5);
        var zeroWords = wordFrequencyService.calculateMostFrequentNWords(getTextThreeEqualWords(), 0);

        Assertions.assertEquals(5, nineWords.length);
        Assertions.assertEquals("test", nineWords[0].getWord());
        Assertions.assertEquals(3, nineWords[0].getFrequency());
        Assertions.assertEquals("a", nineWords[1].getWord());
        Assertions.assertEquals(0, zeroWords.length);
    }

    public void calculateMostFrequentNWordsExceptionTest() {
        var exception = Assertions.assertThrows(CalculationException.class, () -> wordFrequencyService.calculateMostFrequentNWords(null, 0));
        var exceptionEmptyString = Assertions.assertThrows(CalculationException.class, () -> wordFrequencyService.calculateMostFrequentNWords("", 0));

        Assertions.assertEquals("Can't calculate most frequent words for: null", exception.getMessage());
        Assertions.assertEquals("Can't calculate most frequent words for: ", exceptionEmptyString.getMessage());

    }

    private String getTextThreeEqualWords() {
        return "This is a test string that contains three equal words test test";
    }
}

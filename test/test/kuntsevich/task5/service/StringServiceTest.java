package test.kuntsevich.task5.service;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.service.StringService;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StringServiceTest {

    private StringService stringService = new StringService();

    @Test
    public void testReplaceSymbolPositive() {
        String text = " abcde  ABCDE   абвгде  аб   ";
        char symbol = 'F';
        int position = 2;
        try {
            String actual = stringService.replaceSymbol(text, symbol, position);
            String expected = "abFde ABFDE абFгде аб";
            assertEquals(actual, expected);
        } catch (InvalidDataException e) {
            fail();
        }
    }

    @Test(expectedExceptions = InvalidDataException.class)
    public void testReplaceSymbolException() throws InvalidDataException {
        String text = null;
        char symbol = (char) -1;
        int position = 0;
        stringService.replaceSymbol(text, symbol, position);
    }

    @Test
    public void testReplaceSymbolAfterSymbolPositive() {
        String text = " абвр   абвРо абвро  АБроВ  ";
        String prevSubstring = "ро";
        String newSubstring = "ра";
        try {
            String actual = stringService.replaceSymbolAfterSymbol(text, prevSubstring, newSubstring);
            String expected = " абвр   абвРо абвра  АБраВ  ";
            assertEquals(actual, expected);
        } catch (InvalidDataException e) {
            fail();
        }
    }

    @Test(expectedExceptions = InvalidDataException.class)
    public void testReplaceSymbolAfterSymbolException() throws InvalidDataException {
        String text = null;
        String prevSubstring = null;
        String newSubstring = null;
        stringService.replaceSymbolAfterSymbol(text, prevSubstring, newSubstring);
    }

    @Test
    public void testReplaceWordsPositive() {
        String text = " abc abcd  abcde   ABCD  абвг  ";
        int length = 4;
        String newString = "F";
        try {
            String actual = stringService.replaceWords(text, length, newString);
            String expected = "abc F abcde F F";
            assertEquals(actual, expected);
        } catch (InvalidDataException e) {
            fail();
        }
    }

    @Test(expectedExceptions = InvalidDataException.class)
    public void testReplaceWordsException() throws InvalidDataException {
        String text = null;
        int length = 0;
        String newString = null;
        stringService.replaceWords(text, length, newString);
    }

    @Test
    public void testDeleteExceptLettersPositive() {
        String text = " ABC%, DC1a 2b3$jlkl ";
        try {
            String actual = stringService.deleteExceptLetters(text);
            String expected = "ABC   DC a  b  jlkl";
            assertEquals(actual, expected);
        } catch (InvalidDataException e) {
            fail();
        }
    }

    @Test(expectedExceptions = InvalidDataException.class)
    public void testDeleteExceptLettersException() throws InvalidDataException {
        String text = null;
        stringService.deleteExceptLetters(text);
    }

    @Test
    public void testDeleteWordsOnConsonantPositive() {
        String text = "Лорем ипсум долор Сит амет";
        int length = 5;
        try {
            String actual = stringService.deleteWordsOnConsonant(text, length);
            String expected = " ипсум  Сит амет";
            assertEquals(actual, expected);
        } catch (InvalidDataException e) {
            fail();
        }
    }

    @Test(expectedExceptions = InvalidDataException.class)
    public void testDeleteWordsOnConsonantException() throws InvalidDataException {
        String text = null;
        int length = 0;
        stringService.deleteWordsOnConsonant(text, length);
    }

    @Test
    public void testBreakText() {
        String text = " abcde  ABCDE   абвгде  аб   ";
        String[] actual = stringService.breakText(text);
        String[] expected = new String[] {"abcde", "ABCDE", "абвгде", "аб"};
        assertEquals(actual, expected);
    }
}
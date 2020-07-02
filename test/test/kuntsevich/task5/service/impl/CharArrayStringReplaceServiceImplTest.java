package test.kuntsevich.task5.service.impl;

import com.kuntsevich.task5.exception.InvalidDataException;
import com.kuntsevich.task5.service.impl.CharArrayStringReplaceServiceImpl;
import com.kuntsevich.task5.service.impl.DefaultStringReplaceServiceImpl;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.*;

public class CharArrayStringReplaceServiceImplTest {

    private CharArrayStringReplaceServiceImpl stringService = new CharArrayStringReplaceServiceImpl();

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
    public void testReplaceSubstringPositive() {
        String text = " абвр   абвРо абвро  АБроВ  ";
        String prevSubstring = "ро";
        String newSubstring = "ра";
        try {
            String actual = stringService.replaceSubstring(text, prevSubstring, newSubstring);
            String expected = "абвр абвРо абвра АБраВ";
            assertEquals(actual, expected);
        } catch (InvalidDataException e) {
            fail();
        }
    }

    @Test(expectedExceptions = InvalidDataException.class)
    public void testReplaceSubstringException() throws InvalidDataException {
        String text = null;
        String prevSubstring = null;
        String newSubstring = null;
        stringService.replaceSubstring(text, prevSubstring, newSubstring);
    }

    @Test
    public void testReplaceWordsWithLengthPositive() {
        String text = " abc abcd  abcde   ABCD  абвг  ";
        int length = 4;
        String newString = "F";
        try {
            String actual = stringService.replaceWordsWithLength(text, length, newString);
            String expected = "abc F abcde F F";
            assertEquals(actual, expected);
        } catch (InvalidDataException e) {
            fail();
        }
    }

    @Test(expectedExceptions = InvalidDataException.class)
    public void testReplaceWordsWithLengthException() throws InvalidDataException {
        String text = null;
        int length = 0;
        String newString = null;
        stringService.replaceWordsWithLength(text, length, newString);
    }
}